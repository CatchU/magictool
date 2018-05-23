package com.magic.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 刘俊重
 * @Description
 * @date 17:32
 */
public class DateUtil {

    /**标准日期格式：yyyy-MM-dd***/
    public static final String NORMAL_DATE_PATTERN = "yyyy-MM-dd";

    /**标准日期时间格式：yyyy-MM-dd HH:mm:ss**/
    public static final String NORMAL_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * @Description 获取当前时间，yyyy-MM-dd格式
     * @Author 刘俊重
     * @Date 2018/1/9
     */
    public static Date getDate(){
        SimpleDateFormat sdf = new SimpleDateFormat(NORMAL_DATE_PATTERN);
        Date date = null;
        try {
            date = sdf.parse(sdf.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * @Description 获取当前标准日期时间格式
     * @Author 刘俊重
     * @Date 2018/1/9
     */
    public static Date getDateTime(){
        SimpleDateFormat sdf = new SimpleDateFormat(NORMAL_DATETIME_PATTERN);
        Date dateTime = null;
        try {
            dateTime = sdf.parse(sdf.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateTime;
    }

    /**
     * @Description 根据传入的字符串类型的时间，输出Date类型的日期
     * @Author 刘俊重
     * @Date 2018/1/9
     * @param dateStr 字符串类型的日期
     * @param format 日期格式，如：yyyy-MM-dd或者yyyy-MM-dd HH:mm:ss
     */
    public static Date parseDate(String dateStr, String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
