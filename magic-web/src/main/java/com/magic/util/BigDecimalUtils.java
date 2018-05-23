package com.magic.util;

import java.math.BigDecimal;

/**
 * 用来处理BigDecimal数据类型的工具类
 * @author qiangjiyi
 * @date 2017年11月6日 下午2:45:01
 */
public class BigDecimalUtils {

	/**
	 * 将传入的源BigDecimal数据除以10000，再转换为货比类型的格式并返回
	 */
	public static String handleBigDecimalValue(BigDecimal srcBigDecimal) {
		// 定义一个被除数BigDecimal
		BigDecimal divideBigDecimal = new BigDecimal("10000");
		BigDecimal targetBigDecimal = srcBigDecimal.divide(divideBigDecimal, 2, BigDecimal.ROUND_HALF_UP);
		
		// 将处理过的targetBigDecimal转换为double数据类型
		double target = targetBigDecimal.doubleValue();
		
		//获取货币数值格式
		/*NumberFormat currency = NumberFormat.getCurrencyInstance();
		currency.setMinimumFractionDigits(2);//设置数的小数部分所允许的最小位数(如果不足后面补0)
		currency.setMaximumFractionDigits(4);//设置数的小数部分所允许的最大位数(如果超过会四舍五入)
		return currency.format(target);//￥12,343,171.60
		 */
		return target + "";
	}
	
}
