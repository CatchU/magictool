package com.magic.controller;

import com.alibaba.fastjson.JSONObject;
import com.magic.annotation.LogParam;
import com.magic.redis.RedisUtil;
import com.magic.request.user.UserRequest;
import com.magic.result.ApiGeneralResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

/**
 * 测试redis
 * @author 刘俊重
 * @Description
 * @date 18:04
 */
public class RedisTestController {

    private static final Logger logger = LoggerFactory.getLogger(RedisTestController.class);

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 测试
     */
    @RequestMapping(value = "/user/test", method = RequestMethod.POST ,name="测试")
    public ApiGeneralResult<Boolean> login(@LogParam @RequestBody UserRequest userRequest)throws IOException {
        logger.info("user info:{}", JSONObject.toJSONString(userRequest));
        return new ApiGeneralResult<>(true);
    }
}
