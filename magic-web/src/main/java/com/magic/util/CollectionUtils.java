package com.magic.util;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.ComparatorUtils;
import org.apache.commons.collections.comparators.ComparableComparator;
import org.apache.commons.collections.comparators.ComparatorChain;

import java.util.*;

/**
 * 备注：集合
 * 
 * @author liaoyy@belink.com
 */
public class CollectionUtils {
	/**
	 * 备注：对象属性排序
	 * 
	 * @author liaoyy@belink.com
	 * @param list
	 * @param properties
	 * @param asc
	 */
	@SuppressWarnings("unchecked")
	public static <T> void sort(List<T> list, List<String> properties, boolean asc) {
		Comparator<?> comparator = ComparableComparator.getInstance();
		comparator = ComparatorUtils.nullLowComparator(comparator);
		if (!asc) {
			comparator = ComparatorUtils.reversedComparator(comparator);
		}

		@SuppressWarnings("rawtypes")
		List<BeanComparator> prop = new ArrayList<BeanComparator>();
		for (String p : properties) {
			prop.add(new BeanComparator<T>(p, comparator));
		}
		ComparatorChain multiSort = new ComparatorChain(prop);
		Collections.sort(list, multiSort);
	}

	/**
	 * 备注：默认对象属性升续排序
	 * 
	 * @author liaoyy@belink.com
	 * @param list
	 * @param obj
	 * @param asc
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> void sort(List<T> list, Object obj, boolean asc) throws Exception {
		Comparator<?> comparator = ComparableComparator.getInstance();
		comparator = ComparatorUtils.nullLowComparator(comparator);
		if (!asc) {
			comparator = ComparatorUtils.reversedComparator(comparator);
		}
		Map<String, Object> describe = PropertyUtils.describe(obj);
		Set<String> keySet = describe.keySet();
		List<String> plist = new ArrayList<String>(50);
		for (String key : keySet) {
			plist.add(key);
		}
		Collections.sort(plist);
		@SuppressWarnings("rawtypes")
		List<BeanComparator> prop = new ArrayList<BeanComparator>();
		for (String p : plist) {
			prop.add(new BeanComparator<T>(p, comparator));
		}
		ComparatorChain multiSort = new ComparatorChain(prop);
		Collections.sort(list, multiSort);
	}
}
