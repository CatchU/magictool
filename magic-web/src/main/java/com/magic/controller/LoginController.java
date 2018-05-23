package com.magic.controller;

import com.alibaba.fastjson.JSONObject;
import com.magic.annotation.LogParam;
import com.magic.constant.ResultCode;
import com.magic.exception.BusinessException;
import com.magic.request.user.UserRequest;
import com.magic.util.ShiroUtils;
import com.magic.result.ApiGeneralResult;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author 刘俊重
 * @Description 登录控制器
 * @date 12:22
 */
@RestController
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * 登录
     */
    @RequestMapping(value = "/user/login", method = RequestMethod.POST ,name="注册")
    public ApiGeneralResult<Boolean> login(@LogParam @RequestBody UserRequest userRequest)throws IOException {
        logger.info("user info:{}", JSONObject.toJSONString(userRequest));
        try{
            Subject subject = ShiroUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(
                    userRequest.getUsername(), userRequest.getPassword());
            //主体提交至认证器进行认证
            subject.login(token);
        }catch (Exception e) {
            throw new BusinessException(ResultCode.SYSTEM_ERROR);
        }
        return new ApiGeneralResult<>(true);
    }
}
