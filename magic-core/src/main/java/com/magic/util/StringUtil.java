package com.magic.util;

/**
 * @author 刘俊重
 * @Description 字符串工具
 */
public class StringUtil {

    private StringUtil(){}

    /**
     * @Description 判断字符串是否为空白。空白分为三种情况：1.null;2.不可见字符，如空格；3.""
     * @Author 刘俊重
     * @Date 2017/7/11
     * @param str 字符串
     */
    public static boolean isBlank(CharSequence str){
        int length;
        if(null == str || (length=str.length())==0){
            return true;
        }
        for(int i=0;i<length;i++){
            //只要有一个是非空说明就不是空白的
            if(false == isBlankChar(str.charAt(i))){
                return false;
            }
        }
        return true;

    }

    /**
     * @Description 判断字符串是否为非空白
     * @Author 刘俊重
     * @Date 2017/7/11
     * @Param str 字符串
     * @return 是否为非空白
     */
    public static boolean isNotBlank(CharSequence str){
        return false == isBlank(str);
    }

    /**
     * @Description 判断是否是空白字符
     * @Author 刘俊重
     * @Date 2017/7/11
     * @Param c 字符
     * @return 是否是空白字符
     */
    public static boolean isBlankChar(int c){
        return (Character.isWhitespace(c)||Character.isSpaceChar(c));
    }

    /**
     * @Description 判断字符串是否为空。空的定义如下：1.null；2.""
     * @Author 刘俊重
     * @Date 2017/7/11
     * @Param str 需要判断的字符串
     * @return  判断结果
     */
    public static boolean isEmpty(CharSequence str){
        return null==str || str.length()==0;
    }

    /**
     * @Description 判断字符串是否为非空
     * @Author 刘俊重
     * @Date 2017/7/11
     * @Param str 需要判断的字符串
     * @return  判断结果
     */
    public static boolean isNotEmpty(CharSequence str){
        return false==isEmpty(str);
    }
    
    public static void main(String args[]){
        System.out.print(StringUtil.isNotBlank("12"));
    }
}
