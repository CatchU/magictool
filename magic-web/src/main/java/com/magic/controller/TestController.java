package com.magic.controller;

import com.alibaba.fastjson.JSONObject;
import com.magic.annotation.LogParam;
import com.magic.constant.ResultCode;
import com.magic.exception.BusinessException;
import com.magic.request.user.UserRequest;
import com.magic.result.ApiGeneralResult;
import com.magic.util.ShiroUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

/**
 * @author 刘俊重
 * @Description
 * @date 18:04
 */
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * 测试
     */
    @RequestMapping(value = "/user/test", method = RequestMethod.POST ,name="测试")
    public ApiGeneralResult<Boolean> login(@LogParam @RequestBody UserRequest userRequest)throws IOException {
        logger.info("user info:{}", JSONObject.toJSONString(userRequest));
        return new ApiGeneralResult<>(true);
    }
}
