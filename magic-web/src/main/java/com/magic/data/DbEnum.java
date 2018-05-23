package com.magic.data;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 刘俊重
 * @Description 数据字典
 */
public class DbEnum {
	public static final Map<String, Object> DB_ENUM_MAP = new HashMap<String, Object>();

	static {
		fillMap(DB_ENUM_MAP, DbEnum.class);
	}

	public static void fillMap(Map<String, Object> objectMap, Class<?> objectClass) {
		for (Class<?> propertyClass : objectClass.getClasses()) {
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

	private static String getDesc(Map<String, String> desc, String value) {
		if (value == null) {
			return null;
		}

		if (!desc.containsKey(value)) {
			throw new IllegalArgumentException(value + " doesn't exist!");
		}
		return desc.get(value);
	}
	
	/**
	 * @author 刘俊重
	 * @Description 回调状态
	 */
	public static final class CallBack {
		/**
		 * @Description 回调状态
		 */
		public static class Type {
			
			public static final String LOGIN = "1";
			public static final String UPDATE = "2";
			
			public static Map<String, String> desc = new HashMap<String, String>();

			static {
				desc.put(LOGIN, "登录");
				desc.put(UPDATE, "修改支付宝账号");
			}
			public static String desc(String value) {
				return getDesc(desc, value);
			}
		}
	}

	/**
	 * @Description  核心企业
	 * @Author 刘俊重
	 * @Date 2017/12/1
	 */
	public static final class CoreCorpInfo {

		/**
		 * @Description 状态
		 * @Author 刘俊重
		 */
		public static class State {
			public static final String LOCK = "0";
			public static final String NORMAL = "1";

			public static Map<String, String> desc = new HashMap<String, String>();
			static {
				desc.put(LOCK, "终止");
				desc.put(NORMAL, "正常 ");
			}
			public static String desc(String value) {
				return getDesc(desc, value);
			}
		}
	}

	/**
	 * @Description 入住审核结果
	 * @Author 刘俊重
	 * @Date 2017/10/16
	 */
	public static final class JoinReview {

		/**
		 * @Description 审核结果
		 * @Author 刘俊重
		 * @Date 2017/10/16
		 */
		public static class State {
			public static final String SUCCESS = "1";
			public static final String FAIL = "2";

			public static Map<String, String> desc = new HashMap<String, String>();
			static {
				desc.put(SUCCESS, "审核通过");
				desc.put(FAIL, "审核拒绝 ");
			}
			public static String desc(String value) {
				return getDesc(desc, value);
			}
		}
	}

	/**
	 * @Description  供应商
	 * @Author 刘俊重
	 * @Date 2017/12/1
	 */
	public static final class SuppliersInfo {

		/**
		 * @Description 状态
		 * @Author 刘俊重
		 */
		public static class AuditState {
			public static final String WAIT_EXAM = "0";
			public static final String SSUCCESS = "1";
			public static final String FAIL = "2";


			public static Map<String, String> desc = new HashMap<String, String>();
			static {
				desc.put(WAIT_EXAM, "待审核");
				desc.put(SSUCCESS, "通过");
				desc.put(FAIL, "拒绝");
			}
			public static String desc(String value) {
				return getDesc(desc, value);
			}
		}
	}

	/**
	 * @Description 平台用户类型
	 * @Author 刘俊重
	 * @Date 2017/12/27
	 */
	public static final class SystemUser {

		/**
		 * @Description 用户类型
		 * @Author 刘俊重
		 */
		public static class UserType {
			public static final String CORE_USER = "1";
			public static final String SUPPLIER_USER = "2";
			public static final String PLATFORM_USER = "3";


			public static Map<String, String> desc = new HashMap<String, String>();
			static {
				desc.put(CORE_USER, "核心企业");
				desc.put(SUPPLIER_USER, "供应商");
				desc.put(PLATFORM_USER, "平台用户");
			}
			public static String desc(String value) {
				return getDesc(desc, value);
			}
		}
	}
}
