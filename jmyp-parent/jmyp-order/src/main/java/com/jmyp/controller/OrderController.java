package com.jmyp.controller;

import com.jmyp.pojo.Address;
import com.jmyp.service.OrderService;
import com.jmyp.utils.JmypBaseResult;
import com.jmyp.utils.JwtUtils;
import com.jmyp.utils.UserInfo;
import com.jmyp.vo.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Max;
import java.util.List;

/**
 * Created by on 2019/1/3.
 */
@RestController
public class OrderController {

    //注入service对象
    @Autowired
    private OrderService orderService;

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
    @RequestMapping("/createOrder")
    public JmypBaseResult createOrder(@RequestBody OrderRequest orderRequest, HttpServletRequest request){
        try {
            //1,获取token
            String token = request.getHeader("Authorization");
            //2,解析token，获取用户名，用户id
            UserInfo user = JwtUtils.getInfoFromToken(token, "czxy");
            //3,保存订单数据
            JmypBaseResult result =  orderService.createOrder(orderRequest,user.getId());

            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return  null;

    }

    /**
     * 需求：查询地址列表
     * 请求：/findAddr
     * 参数：Integer userId
     * 返回值：List<Address>
     */
    @RequestMapping("/findAddr")
    public List<Address> findAddList(Integer userId){
        //调用service查询地址列表
        List<Address> addList = orderService.findAddList(userId);
        return addList;
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
    @RequestMapping("/pay")
    public JmypBaseResult createOrCodeURL(Long sn){

        //调用service服务，实现支付
        JmypBaseResult result =  orderService.createOrCodeURL(sn);
        return  result;

    }



}
