package com.magic.thread;

import com.alibaba.fastjson.JSONObject;
import com.magic.model.User;
import com.magic.result.ApiGeneralResult;
import org.junit.Test;

import java.util.Date;

public class RegisterHelperTest {

    @Test
    public void test_registerAsync(){
       RegisterHelper registerHelper = new RegisterHelper();
       registerHelper.registerAsync(5L);
    }
}
