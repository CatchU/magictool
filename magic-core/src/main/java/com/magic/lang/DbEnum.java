package com.magic.lang;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 刘俊重
 * @Description 数据字典类(数据枚举)
 */
public class DbEnum {

    public static final Map<String,Object> DB_ENUM_MAP = new HashMap<String,Object>();

    static{
        fillMap(DB_ENUM_MAP,DbEnum.class);
    }

    public static void fillMap(Map<String,Object> objectMap,Class<?> objectClass){
        for(Class<?> propertyClass : objectClass.getClasses()){
            Map<String, Object> propertyMap = new HashMap<String, Object>();
            objectMap.put(propertyClass.getSimpleName(), propertyMap);

            for (Field field : propertyClass.getDeclaredFields()) {
                try {
                    if (Modifier.isPublic(field.getModifiers())) {
                        propertyMap.put(field.getName(), field.get(null));
                    }
                } catch (Exception e) {
                    //throw new SystemError(e);
                }
            }
            if (null != propertyClass.getClasses() && 0 < propertyClass.getClasses().length) {
                fillMap(propertyMap, propertyClass);
            }
        }
    }


    public static String getDesc(Map<String,String> desc,String value){
        if(null==value){
            return null;
        }
        if(!desc.containsKey(value)){
            throw new IllegalArgumentException(value + " doesn't exist!");
        }
        return desc.get(value);
    }

    /*****************************使用方法*********************************/
    /**如果存储数据不用1，而用类的形式如：user.setState(DbEnum.ImUser.State.NORMAL)*/
    /**如果从数据库查询出的是1，想向前端展示“正常”的汉字使用，DbEnum.Imuser.State.desc("1")即可获得*/

    /**
     * @author 刘俊重
     * @Description 用户表
     */
    public static final class ImUser {

        /**
         * @Description 用户状态
         */
        public static class State {
            public static final String NORMAL = "1";
            public static final String LOCK = "2";
            public static final String STOP = "3";

            public static Map<String, String> desc = new HashMap<String, String>();

            static {
                desc.put(NORMAL, "正常");
                desc.put(LOCK, "冻结");
                desc.put(STOP, "停用");
            }
            public static String desc(String value) {
                return getDesc(desc, value);
            }
        }
    }


}
