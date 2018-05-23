package com.magic.shiro;

import com.alibaba.fastjson.JSON;
import com.magic.annotation.NoLogin;
import com.magic.constant.ResultCode;
import com.magic.response.BaseResponse;
import com.magic.result.ApiGeneralListResult;
import com.magic.result.ApiGeneralResult;
import com.magic.util.R;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 刘俊重
 * @Description 系统路径匹配过滤器，继承shiro的PathMatchingFilter
 * @date 17:17
 */
public class SysPathMatchingFilter extends PathMatchingFilter{

    private static final Logger logger = LoggerFactory.getLogger(SysPathMatchingFilter.class);
    @Override
    protected boolean onPreHandle(ServletRequest servletRequest,
         ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        NoLogin noLogin = request.getMethod().getClass().getAnnotation(NoLogin.class);
        logger.info("nologin:"+noLogin);
        if(null !=request.getMethod().getClass().getAnnotation(NoLogin.class)){
            logger.info("直接放行");
            return true;
        }

        //从缓存中获取用户的登录信息和他的功能菜单，判断有没有这个的操作权限
        String requestURI = request.getRequestURI();
        //模拟从数据库取的功能url
        String mockUrl = "/user/login;/user/logout";
        if(!mockUrl.contains(requestURI)){
            out(response, ResultCode.NO_DATA_PERMISSION);
            return false;
        }
        return true;
    }

    /**
     * 向前端返回异常信息
     * @throws IOException
     */
    private void out(ServletResponse response, ResultCode resultCode) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(new ApiGeneralResult<Boolean>(
                resultCode.getCodeNo(),resultCode.getCustomerMessage(),resultCode.getCustomerMessage())));
        writer.flush();
        writer.close();
    }
}
