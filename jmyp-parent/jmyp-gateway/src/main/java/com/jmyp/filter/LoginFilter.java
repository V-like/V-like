package com.jmyp.filter;

import com.jmyp.config.FilterConfig;
import com.jmyp.config.JwtConfig;
import com.jmyp.utils.JwtUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by on 2019/1/2.
 */
@Component
@EnableConfigurationProperties({JwtConfig.class, FilterConfig.class})
public class LoginFilter extends ZuulFilter {


    @Autowired
    private JwtConfig jwtConfig;

    //注入
    @Autowired
    private FilterConfig filterConfig;

    //路由之前执行
    @Override
    public String filterType() {
        return "pre";
    }

    //指定过滤器之间的执行顺序
    //filter1 3 fielter2 2
    @Override
    public int filterOrder() {
        return 2;
    }


    //指定此过滤器是否执行
    @Override
    public boolean shouldFilter() {

        //获取请求对象
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        String requestURI = request.getRequestURI();

        boolean allowPath = isAllowPath(requestURI);

        return !allowPath;
    }

    //判断当前请求路径是否是放行路径
    public boolean isAllowPath(String URL){
        //定义标识，用来标识是否包含请求路径
        boolean flag = false;
        //判断
        //获取放行路径
        List<String> allowPaths = filterConfig.getAllowPaths();
        //循环放行路径
        for (String allowPath : allowPaths) {

            //判断请求路径是否包含放行路径
            //如果包含，放行，否则不放行
            if(URL.contains(allowPath)){
                flag = true;
                break;
            }

        }

        return  flag;
    }

    //过滤器真正执行的方法
    @Override
    public Object run() throws ZuulException {

        //获取请求对象的上下文对象
        RequestContext requestContext = RequestContext.getCurrentContext();
        //获取请求对象
        HttpServletRequest request = requestContext.getRequest();
        try {
            //获取请求头中token
            String token = request.getHeader("Authorization");
            //解析token
            JwtUtils.getInfoFromToken(token,jwtConfig.getKey());

        } catch (Exception e) {
            e.printStackTrace();
            // 校验出现异常，返回403
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(403);

        }


        return null;
    }
}
