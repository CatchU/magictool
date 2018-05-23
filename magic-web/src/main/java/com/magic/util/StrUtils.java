package com.magic.util;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * 备注：字符
 * 
 * @author liaoyy@belink.com
 */
public class StrUtils {

	/**
	 * 备注：
	 * 
	 * <pre>
	 * StringUtils.isBlank(null)      = true
	 * StringUtils.isBlank("")        = true
	 * StringUtils.isBlank(" ")       = true
	 * StringUtils.isBlank("bob")     = false
	 * StringUtils.isBlank("  bob  ") = false
	 * </pre>
	 * 
	 * @author liaoyy@belink.com
	 * @param obj
	 * @return
	 */
	public static boolean isBlank(Object obj) {
		if (null == obj) {
			return true;
		} else {
			return StringUtils.isBlank(obj.toString());
		}
	}

	/**
	 * 备注：是否包含{}、：特殊字符
	 * 
	 * @author liaoyy@belink.com
	 * @param obj
	 * @return
	 */
	public static boolean isSpecialCharacter(String str) {
		str.contains("{");
		boolean b = str.contains("{") && str.contains("}") && str.contains(":");
		return b;
	}

	/**
	 * 备注：取到签名md5
	 * 
	 * @author liaoyy@belink.com
	 * @param objs
	 * @param tiemestamp
	 * @param username
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static String getSignValue(List<Map> objs, String timestamp, String username, String token) {
		if (null != objs && objs.size() == 0) {
			throw new IllegalArgumentException("objs 不能为空！");
		}
		List<String> proNames = new ArrayList<String>(50);
		Map<String, Object> map = objs.get(0);
		StringBuffer bf = new StringBuffer(50);
		Set<String> keySet = map.keySet();
		for (String field : keySet) {
			proNames.add(field);
		}
		Collections.sort(proNames);
		for (Map<String, Object> obj : objs) {
			for (String proName : proNames) {
				bf.append(obj.get(proName));
			}
		}
		System.out.println("sort>>>>"+bf.toString() + timestamp + username + token);
		String md5Code = Md5Utils.getMD5Code(bf.toString() + timestamp + username + token);
		return md5Code;
	}

	public static void main(String[] args) throws Exception {
		Map hashMap = new HashMap();
		hashMap.put("finmallId", "1");
		hashMap.put("fundOutName", "1dasdas23");

		Map hashMap2 = new HashMap();
		hashMap2.put("finmallId", "2");
		hashMap2.put("fundOutName", "12412dasdasd23");

		List<Map> list = new ArrayList<Map>();

		list.add(hashMap);
		list.add(hashMap2);
		System.out.println(StrUtils.getSignValue(list, "20171115112233", "ychd188", "4e3d807085614df0814f65ef029b0498"));
	}
}
