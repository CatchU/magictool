package com.magic.util;

import java.util.UUID;

/**
 * 备注：
 * @author liaoyy@belink.com
 */
public class UUIDUtils {

	/**
	 * 备注：token
	 * 
	 * @author liaoyy@belink.com
	 * @return
	 */
	public static String genToken() {
		String token = UUID.randomUUID().toString();
		return token.replaceAll("-", "");
	}
}
