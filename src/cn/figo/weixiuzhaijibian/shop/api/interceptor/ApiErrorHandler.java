package cn.figo.weixiuzhaijibian.shop.api.interceptor;

import retrofit.ErrorHandler;
import retrofit.RetrofitError;

/**
 * 调用失效
 * 
 * 
 */
public class ApiErrorHandler implements ErrorHandler {
	
	@Override
	public Throwable handleError(RetrofitError cause) {
		if (cause.getResponse() != null) {
			int status = cause.getResponse().getStatus();

		}
		/*
		 * if (!TextUtils.isEmpty(str)) { str = appendHeader(str);
		 * setResponseDate(str);
		 * mapper.readerForUpdating(this).withView(super.getClass())
		 * .readValue(str); }
		 */

		return cause;
	}
	
	
}