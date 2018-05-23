package com.magic.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Properties;

/**
 * 备注：系统异常信息属性文件配置
 * 
 * @author liaoyy@belink.com
 */
public class ErPropertyUtils {
	private static Logger logger = LoggerFactory.getLogger(ErPropertyUtils.class);
	private static Properties properties = new Properties();
	static {
		try {
			properties.load(new ClassPathResource("resource/error-message.properties")//
					.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}

	/**
	 * 备注：
	 * 
	 * @author liaoyy@belink.com
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getStrValue(int key, String defaultValue) {
		return properties.getProperty(key + "", defaultValue);
	}

}
