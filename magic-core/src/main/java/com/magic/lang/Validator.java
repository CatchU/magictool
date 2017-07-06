package com.magic.lang;

/**
 * @author 刘俊重
 * @Description 表单校验器
 */
public final class Validator {

    private Validator(){}


    /**
     * @Description 判断给定值是否为null
     * @Param obj 给定值
     * @return 是否为null
     */
    public static boolean isNull(Object obj){
        return null == obj;
    }


    /**
     * @Description 判断给定是否不为null
     * @param obj 给定值
     * @return 是否不为null
     */
    public static boolean isNotNull(Object obj){
        return null != obj;
    }
}
