package cn.figo.weixiuzhaijibian.shop.utils;

import cn.figo.weixiuzhaijibian.shop.app.MyApplication;


public class ApiClient {

	public static final String UTF_8 = "UTF-8";
	public static final String DESC = "descend";
	public static final String ASC = "ascend";
	
	private final static int TIMEOUT_CONNECTION = 20000;
	private final static int TIMEOUT_SOCKET = 20000;
	private final static int RETRY_TIME = 3;

	private static String appCookie;
	private static String appUserAgent;

	public static void cleanCookie() {
		appCookie = "";
	}
	
	private static String getCookie(MyApplication LocationApplication) {
		if(appCookie == null || appCookie == "") {
			appCookie = LocationApplication.getProperty("cookie");
		}
		return appCookie;
	}
	

}