package com.magic.lang;

import java.util.HashMap;

/**
 * @author 刘俊重
 * @Description 封装后台向前端返回的数据
 */
public class WebResult extends HashMap<String,Object>{

    public WebResult(){
        put("code",200);
    }

    /**
     * @Description 返回成功结果
     * @Author 刘俊重
     */
    public static WebResult ok(){
        return new WebResult();
    }

    public static WebResult ok(String msg){
        WebResult result = new WebResult();
        result.put("msg",msg);
        return result;
    }

    public static WebResult ok(String code,String msg){
        WebResult result = new WebResult();
        result.put("code",code);
        result.put("msg",msg);
        return result;
    }
    /**
     * @Description 返回错误
     * @Author 刘俊重
     */
    public static WebResult error(){
        return error(500,"未知错误，请稍后重试!");
    }

    public static WebResult error(String msg){
        return error(500,msg);
    }

    public static WebResult error(int code,String msg){
        WebResult result = new WebResult();
        result.put("code",code);
        result.put("msg",msg);
        return result;
    }

    public WebResult put(String key,Object value){
        super.put(key,value);
        return this;
    }

}
