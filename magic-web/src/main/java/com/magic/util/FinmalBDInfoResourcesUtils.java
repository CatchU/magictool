package com.magic.util;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * 备注：获取finmal-BD信息
 * 
 * @author liaoyy@belink.com
 */
public class FinmalBDInfoResourcesUtils {
	private static ResourceBundle commonBundle = ResourceBundle.getBundle("finmal-BD-info");

	/**
	 * 获取配置文件数据 去掉收尾空格
	 ***/
	public static String get(String key) {
		return commonBundle.getString(key).trim();
	}

	/**
	 * 备注：获取电话号码
	 * 
	 * @author liaoyy@belink.com
	 * @return
	 */
	public static List<String> getPhones() {
		String[] split = get("bds").split(";");
		List<String> phones = new ArrayList<String>(100);
		for (String str : split) {
			phones.add(str.split("-")[2]);
		}
		return phones;
	}

	/**
	 * 备注：获取邮箱
	 * 
	 * @author liaoyy@belink.com
	 * @return
	 */
	public static List<String> getMails() {
		String[] split = get("bds").split(";");
		List<String> mails = new ArrayList<String>(100);
		for (String str : split) {
			mails.add(str.split("-")[1]);
		}
		return mails;
	}

	/**
	 * 备注：已 int 方式获取
	 * @author liaoyy@belink.com
	 * @param key
	 * @return
	 */
	public static int getInt(String key) {
		return Integer.parseInt(get(key));
	}
}
