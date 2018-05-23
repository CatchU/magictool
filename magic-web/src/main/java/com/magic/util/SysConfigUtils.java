package com.magic.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Properties;

/**
 * 系统配置类
 * @author qiangjiyi
 * @date 2017年7月20日 下午3:52:15
 */
public class SysConfigUtils {
	
	private static Logger logger = LoggerFactory.getLogger(SysConfigUtils.class);
	
	private static Properties properties = new Properties();
	
	static {
		try {
			properties.load(new ClassPathResource("sys-config.properties")//
					.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}

	/**
	 * 获取属性文件键值对key对应的value值
	 */
	public static String getValue(String key) {
		return properties.getProperty(key);
	}

}
