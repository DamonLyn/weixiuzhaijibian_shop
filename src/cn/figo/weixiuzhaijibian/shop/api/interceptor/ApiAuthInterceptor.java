package cn.figo.weixiuzhaijibian.shop.api.interceptor;


import retrofit.RequestInterceptor;
import cn.figo.weixiuzhaijibian.shop.app.MyApplication;

/**
 * 认证
 * 
 */
public class ApiAuthInterceptor implements RequestInterceptor {

	private MyApplication mApp;

	public ApiAuthInterceptor(MyApplication app) {
		this.mApp = app;
	}

	@Override
	public void intercept(RequestFacade request) {
		/*
		 * 默认有四个方�?
		 * void addHeader(String name, String value);
    void addPathParam(String name, String value);
    void addEncodedPathParam(String name, String value);
    void addQueryParam(String name, String value);
    void addEncodedQueryParam(String name, String value);
		 * */
	}
}