package com.magic.data;

import java.util.ResourceBundle;

/**
 * @Description 读取配置文件的工具类，本类存放系统级变量
 * @Author 刘俊重
 * @Date 2017/10/18
 */
public class SystemVar {
	//private static final String COMMON_PROPERTY_FILE_NAME = "resource/resource" ;
    private static final String COMMON_PROPERTY_FILE_NAME = "sys-config" ;

	private static ResourceBundle  commonBundle = ResourceBundle.getBundle(COMMON_PROPERTY_FILE_NAME) ;

	/**
	 * 获取配置文件数据 去掉收尾空格
	 * ***/
	public static String get(String key)
	{
		return commonBundle.getString(key).trim() ;
	}

    /**
     * 支付宝AppId
     */
    public static final String APP_ID = get("alipay.app.id");

    /**
     * 回调地址
     */
	public static final String REDIRECT_URL = get("redirect.url");


    /**
     * 我们自己网站的域名
     */
    public static final String WEBSITE = get("web.site");

    /**
     * 支付宝授权登录页
     */
    public static final String ALIPAY_URL = get("alipay.url");

    /**
     * 私钥
     */
    public static final String PRIVATE_KEY = get("alipay.private.key");

    /**
     * 公钥
     */
    public static final String ALIPAY_PUBLIC_KEY = get("alipay.public.key");

    /**
     * 网关
     */
    public static final String ALIPAY_GATEWAY = get("alipay.gateway");


}
