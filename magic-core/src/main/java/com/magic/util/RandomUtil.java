package com.magic.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @author 刘俊重
 * @Description 随机数工具
 */
public final class RandomUtil {

    private RandomUtil(){}

    private final static String BASE_NUMBER = "1234567890";

    private final static String BASE_CHAR = "abcdefghijklmnopqrstuvwxyz";

    private final static String BASE_NUMBER_CHAR = BASE_NUMBER + BASE_CHAR;


    /**
     * @Description 获取指定范围内的随机数
     * @Author 刘俊重
     * @Date 2017/7/10
     * @Param min 最小数
     * @Param max 最大数
     * @return 随机数
     */
    public static int randomInt(int min,int max){
        Random random = new Random();
        return random.nextInt(max-min)+min;
    }

    /**
     * @Description 生成一个随机数
     * @Author 刘俊重
     * @Date 2017/7/10
     * random.nextInt()生成无范围的随机数，random.nextInt(n)生成[0,n)的随机数
     */
    public static int randomInt(){
        Random random = new Random();
        return random.nextInt();
    }

    /**
     * @Description 生成0到某一范围的随机数
     * @Author 刘俊重
     * @Date 2017/7/10
     * @param limit 最大范围
     */
    public static int randomInt(int limit){
        Random random = new Random();
        return random.nextInt(limit);
    }

    /**
     * @Description 生成一个只包含数字的随机字符串
     * @Author 刘俊重
     * @Date 2017/7/10
     * @param length 生成字符串的长度
     */
    public static String randomNumber(int length){
        return randomString(BASE_NUMBER,length);
    }

    /**
     * @Description 生成一个包含数字和字符的随机字符串
     * @Author 刘俊重
     * @Date 2017/7/10
     * @param length 生成字符串的长度
     */
    public static String randomString(int length){
        return randomString(BASE_NUMBER_CHAR,length);
    }

    /**
     * @Description 从字符串中生成具有指定长度的字符串
     * @Author 刘俊重
     * @Date 2017/7/10
     * @Param baseStr 要生成字符串的原始字符串
     * @Param length 要生成字符串的长度
     */
    public static String randomString(String baseStr,int length){
        final Random random = new Random();
        final StringBuffer sb = new StringBuffer();

        if(length<1){
            length=1;
        }

        int baseLength = baseStr.length();
        for(int i=0;i<length;i++){
            int number = random.nextInt(baseLength);
            sb.append(baseStr.charAt(number));
        }
        return sb.toString();
    }

    /**
     * @Description 生成UUID的字符串
     * @Author 刘俊重
     * @Date 2017/7/10
     */
    public static String randomUUID(){
       return UUID.randomUUID().toString();
    }

    /**
     * @Description 随机获取列表中的元素
     * @Author 刘俊重
     * @Date 2017/7/10
     */
    public static <T> T randomEle(List<T> list){
        return randomEle(list,list.size());
    }

    /**
     * @Description 获取列表中某一项的元素
     * @Author 刘俊重
     * @Date 2017/7/10
     * @Param list 列表
     * @Param <T> 列表元素的类型
     * @Param limit 待获取的元素的区间
     */
    public static <T> T randomEle(List<T> list,int limit){
        return list.get(randomInt(limit));
    }

    /**
     * @Description 从列表中获取指定长度的元素
     * @Author 刘俊重
     * @Date 2017/7/10
     * @Param list 列表
     * @Param count 要获取元素的长度
     */
    public static <T> List<T> randomEles(List<T> list,int count){
        final List<T> result = new ArrayList<T>(count);
        int size = list.size();
        while (count-->0){
            result.add(randomEle(list,size));
        }
        return result;
    }
}
