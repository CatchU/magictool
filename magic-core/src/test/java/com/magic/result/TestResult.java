package com.magic.result;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.magic.model.User;
import org.junit.Test;
import java.util.Date;
import java.util.List;

public class TestResult {

    @Test
    public void test_ApiGeneralResult(){
        ApiGeneralResult<User> result = new ApiGeneralResult<User>();
        User user = new User(1L,"张三",18,new Date(),8000d,false);
        result.setValue(user);
        System.out.println("返回结果："+JSONObject.toJSONString(result));
    }

    @Test
    public void test_ApiGeneralListResult(){
        ApiGeneralListResult<User> result = new ApiGeneralListResult<User>();
        List<User> userList = Lists.newArrayList();
        userList.add(new User(1L,"张三",18,new Date(),8000d,false));
        userList.add(new User(2L,"李四",20,new Date(),10000d,false));
        userList.add(new User(3L,"王五",24,new Date(),15000d,true));
        result.setValue(userList,new Long(userList.size()));
        System.out.println("返回结果:"+JSONObject.toJSONString(result));

    }
}
