package com.scholarship.module.conf;

/**
 * Description: 应用程序级别环境变量
 * 
 * @author 陈帅
 * @Version 1.0
 * @Created at 2016-04-01
 * @Modified by 
 */
public class AppConfig {

	// Debug flag
	public static final boolean SYSTEM_FLAG_DEBUG = true;
	
	// CTX
	public static String ctx;
	
	/***
	 * 是否允许奖助学金申请
	 */
	public static int APPLY=1;
	
	/***
	 * 警告信息
	 */
	public static String ALERT="请确保信息填写真实完整，一经核实失败，后果自负！";
	
	/***
	 * 登录验证码开关
	 */
	public static int CODES=0;
	
	/***
	 * 重置默认密码
	 */
	public static String DEFAULT_PWD="123456";
}
