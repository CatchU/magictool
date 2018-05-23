package com.magic.util;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AESUtils {
	private static Logger logger = LoggerFactory.getLogger(AESUtils.class);

	/**
	 * 加密
	 * 
	 * @param content
	 *            需要加密的内容
	 * @param password
	 *            加密密码
	 * @return
	 */
	public static String encrypt(String content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(byteContent);
			String encryptResultStr = parseByte2HexStr(result);
			return encryptResultStr; // 加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			logger.error(">>>" + e);
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			logger.error(">>>" + e);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
			logger.error(">>>" + e);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			logger.error(">>>" + e);
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
			logger.error(">>>" + e);
		} catch (BadPaddingException e) {
			e.printStackTrace();
			logger.error(">>>" + e);
		}
		return null;
	}

	/**
	 * 解密
	 * 
	 * @param content
	 *            待解密内容
	 * @param password
	 *            解密密钥
	 * @return
	 */
	public static String decrypt(String content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(parseHexStr2Byte(content));
			return new String(result); // 加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			logger.error(">>>" + e);
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			logger.error(">>>" + e);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
			logger.error(">>>" + e);
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
			logger.error(">>>" + e);
		} catch (BadPaddingException e) {
			e.printStackTrace();
			logger.error(">>>" + e);
		}
		return null;
	}

	/**
	 * 将二进制转换成16进制
	 * 
	 * @param buf
	 * @return
	 */
	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 将16进制转换为二进制
	 * 
	 * @param hexStr
	 * @return
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("finmallId", "1.1");
		hashMap.put("fundOutName", "1dasdas23");

		HashMap<String, Object> hashMap2 = new HashMap<String, Object>();
		hashMap2.put("finmallId", 2.0);
		hashMap2.put("fundOutName", "12412dasdasd23");

		
		HashMap<String, Object> hashMap3 = new HashMap<String, Object>();
		hashMap3.put("timeStamp", "20171115112233");
		HashMap<String, Object> hashMap4 = new HashMap<String, Object>();
		hashMap4.put("userName", "ychd188");
		
		HashMap<String, Object> hashMap5 = new HashMap<String, Object>();
		hashMap5.put("token", "ec9e548e31464cefad00d9a21a6ece3c");
		List<Object> list = new ArrayList<Object>();

		list.add(hashMap);
		list.add(hashMap2);
		list.add(hashMap3);
		list.add(hashMap4);
		list.add(hashMap5);
		String jsonString = JSON.toJSONString(list);
		// String content = "test";
		String password = "12345678";
		// 加密
		System.out.println("加密前：" + jsonString);
		String encryptResult = encrypt(jsonString, password);
		System.out.println("加密了>>>" + encryptResult);
		// 解密
		String decryptResult = decrypt(encryptResult, password);
		System.out.println("解密后：" + decryptResult);
	}
}