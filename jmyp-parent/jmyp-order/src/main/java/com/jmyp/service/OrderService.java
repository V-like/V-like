package com.jmyp.service;

import com.github.wxpay.sdk.WXPayUtil;
import com.jmyp.config.WxPayConfig;
import com.jmyp.mapper.AddressMapper;
import com.jmyp.mapper.OrderItemMapper;
import com.jmyp.mapper.OrderMapper;
import com.jmyp.pojo.Address;
import com.jmyp.pojo.Order;
import com.jmyp.pojo.OrderItem;
import com.jmyp.utils.HttpClient;
import com.jmyp.utils.IdWorker;
import com.jmyp.utils.JmypBaseResult;
import com.jmyp.vo.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by on 2019/1/3.
 */
@Service
@EnableConfigurationProperties(WxPayConfig.class)
public class OrderService {

    //注入地址mapper接口代理对象
    @Autowired
    private AddressMapper addressMapper;

    //注入订单mapper接口代理对象
    @Autowired
    private OrderMapper orderMapper;

    //注入redis模板对象
    @Autowired
    private RedisTemplate redisTemplate;

    //注入订单明细maper接口代理对象
    @Autowired
    private OrderItemMapper orderItemMapper;


    //注入支付配置类
    @Autowired
    private WxPayConfig wxPayConfig;


    /**
     * 需求：提交订单
     * 请求：/createOrder
     * 参数：OrderRequest
     * 返回值：JmypBaseResult
     * 方法名：createOrder
     * 业务流程：
     * 1 生成sn，唯一编号
     * 2 解析token，获取当前登录用户信息
     * 3 获取用户前台传输过来的收货地址和收货人信息，生成订单，保存到数据库
     * 4 获取当前用户在redis中存储的购物车信息
     * 5 将购物车中打钩的商品信息保存到orderitem表中
     * 6 返回sn
     */
    public JmypBaseResult createOrder(OrderRequest orderRequest, Integer userId) {


        try {
            //1 生成sn，唯一编号
            //每秒下单量，可能很大，为了防止订单号重复，如何解决？
            IdWorker idWorker = new IdWorker();
            Long orderId = idWorker.nextId();

            //创建订单对象封装订单数据，以便于保存
            Order order = new Order();
            //设置订单号
            order.setOrder_id(orderId);
            //支付方式
            order.setPayment_type(orderRequest.getPay_method());

            //免邮费
            order.setPost_fee("0");
            //提交订单-- 未付款
            //状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭,7、待评价
            order.setStatus("1");
            //创建时间对象
            Date date = new Date();
            order.setCreate_time(date);
            order.setUpdate_time(date);

            //订单地址
            //根据地址id查询出收货人地址
            Address address = addressMapper.selectByPrimaryKey(orderRequest.getAddressId());
            //添加收货人地址到订单对象中
            order.setReceiver(address.getShr_name());
            order.setReceiver_area_name(address.getShr_address());
            order.setReceiver_mobile(address.getShr_mobile());


            //再保存订单明细
            //1,先从购物车中查询出购物明细
            List<Map> cartList = redisTemplate.boundHashOps(userId).values();

            //定义变量
            Float totalPrice = 0f;


            //2,循环购物车列表，把购物车数据放入订单明细
            for (Map map : cartList) {
                //定义总金额对象
                Float total_fee = 0f;
                //创建订单明细对象
                OrderItem orderItem = new OrderItem();
                //生成明细id
                IdWorker idWorker1 = new IdWorker();
                Long orderItemId = idWorker1.nextId();
                //设置主键
                orderItem.setId(orderItemId);
                //设置订单id
                orderItem.setOrder_id(orderId);

                //获取spuid
                Long spuid = (Long)map.get("spuid");
                orderItem.setGoods_id(spuid);

                //获取skuid
                Integer skuid = (Integer) map.get("skuid");
                orderItem.setItem_id(skuid.longValue());

                //获取商品名称
                String title = (String)map.get("goods_name");
                orderItem.setTitle(title);

                //获取价格
                Integer price = (Integer)map.get("price");
                orderItem.setPrice(price.floatValue());

                //获取购买数量
                Integer num = (Integer) map.get("count");
                orderItem.setNum(num);

                //计算购物车单个商品总金额
                total_fee=price.floatValue()*num;
                orderItem.setTotal_fee(total_fee);

                //计算总价
                totalPrice+=total_fee;

                //获取图片地址
                String pic = (String)map.get("midlogo");
                orderItem.setPic_path(pic);

                //保存订单明细
                orderItemMapper.insertSelective(orderItem);

            }


            //设置订单总价
            order.setPayment(totalPrice);
            //先保存订单
            orderMapper.insertSelective(order);

            //返回订单号

            return new JmypBaseResult(0,"提交订单成功",orderId+"");

        } catch (Exception e) {
            e.printStackTrace();
            return new JmypBaseResult(1,"提交订单失败",null);
        }


    }

    /**
     * 需求：查询地址列表
     * 请求：/findAddr
     * 参数：Integer userId
     * 返回值：List<Address>
     */
    public List<Address> findAddList(Integer userId) {

        //创建example对象
        Example example = new Example(Address.class);
        //创建criteria对象
        Example.Criteria criteria = example.createCriteria();
        //设置查询参数
        criteria.andEqualTo("user_id",userId);

        //执行查询
        List<Address> addressList = addressMapper.selectByExample(example);

        return  addressList;


    }

    /**
     * 需求：生成二维码（返回支付链接）
     * 请求：/pay
     * 参数：订单号
     * 返回值：JmypBaseResult
     * 方法名：createOrCodeURL
     * 生成二维码支付链接业务流程：
     * 1，使用httpClient调用远程微信支付接口即可
     * 2，封装微信支付接口所需要的参数
     * 3，发送请求
     * 4，返回支付链接地址
     */
    public JmypBaseResult createOrCodeURL(Long sn) {

        try {
            //根据订单号查询支付金额
            // Order order = orderMapper.selectByPrimaryKey(sn);
            //获取支付金额
            //  float payment = order.getPayment();


            //创建map对象，封装参数
            Map<String,String> paramMap = new HashMap();
            //appid
            paramMap.put("appid",wxPayConfig.getAppid());
            //商户号
            paramMap.put("mch_id",wxPayConfig.getPartner());
            //随机字符串
            paramMap.put("nonce_str", WXPayUtil.generateNonceStr());
            //商品描述
            paramMap.put("body", "聚美优品");
            //商户订单号
            paramMap.put("out_trade_no", sn+"");
            //总金额（分）
            paramMap.put("total_fee","1");
            //IP
            paramMap.put("spbill_create_ip", "127.0.0.1");
            //回调地址(随便写)
            paramMap.put("notify_url", "http://test.itcast.cn");
            //交易类型
            paramMap.put("trade_type", "NATIVE");

            //使用工具把参数转换为xml格式（腾讯支付品台要求接受xml格式参数）
            String xmlParam = WXPayUtil.generateSignedXml(paramMap, wxPayConfig.getPartnerkey());

            //创建httpClient对象，调用微信支付接口，传递参数
            HttpClient httpClient = new HttpClient(wxPayConfig.getPayUrl());
            //设置请求方式
            httpClient.setHttps(true);
            //设置参数
            httpClient.setXmlParam(xmlParam);

            //发送请求
            httpClient.post();

            //获取返回结果
            String result = httpClient.getContent();


            //把返回值结果转换成map
            Map<String, String> urlMap = WXPayUtil.xmlToMap(result);

            //获取支付链接地址
            String code_url = urlMap.get("code_url");

            //返回成功
           return new JmypBaseResult(0,"下单成功",code_url);
        } catch (Exception e) {
            e.printStackTrace();
            return new JmypBaseResult(1,"下单失败",null);
        }


    }
}
