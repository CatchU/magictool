package com.magic.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Calendar;
import java.util.UUID;

/**
 * session访问token生成策略
 * @author qiangjiyi
 * @date 2017年11月27日 下午3:40:08
 */
public class TokenUtils {

	// 加密 算法--获取随机数
	public static String getRandomCode(String username, String mobile) throws Exception {
		Calendar calendar = Calendar.getInstance();
		String time = String.valueOf(calendar.getTimeInMillis());

		String encodeString = username + mobile + time + Math.round(10);

		return DigestUtils.md5Hex(encodeString.getBytes("UTF-8"));
	}

	/**
	 * @Description 根据UUID生成token
	 * @Author 刘俊重
	 * @Date 2017/11/28
	 */
	public static String getToken(){
		String str = UUID.randomUUID().toString();
		return str.replace("-", "");
	}
}
