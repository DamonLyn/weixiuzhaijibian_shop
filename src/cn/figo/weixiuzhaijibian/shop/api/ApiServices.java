package cn.figo.weixiuzhaijibian.shop.api;

import java.io.IOException;
import java.net.HttpURLConnection;

import cn.figo.weixiuzhaijibian.shop.api.interceptor.ApiAuthInterceptor;
import cn.figo.weixiuzhaijibian.shop.api.interceptor.ApiErrorHandler;
import cn.figo.weixiuzhaijibian.shop.app.MyApplication;

import retrofit.RestAdapter;
import retrofit.RestAdapter.Log;
import retrofit.RestAdapter.LogLevel;
import retrofit.client.Request;
import retrofit.client.UrlConnectionClient;
import android.os.Build;

/**
 * 封装所有API
 * 
 * 
 */
public class ApiServices {
	private static String mBaseUrl;

	private final MyApplication mApp;
	
	private  static AppService appService;

	public ApiServices(MyApplication app) {
		this.mApp = app;
		mBaseUrl = Configs.getString(Configs.BASE_URL);
		init();
	}

	public static void init() {
		mBaseUrl = Configs.getString(Configs.BASE_URL);

		RestAdapter.Builder builder = new RestAdapter.Builder().setClient(new MyUrlConnectionClient())
				.setEndpoint(mBaseUrl)
				.setConverter(new JacksonConverter())
				.setRequestInterceptor(
						new ApiAuthInterceptor(MyApplication.getInstance()))
				.setErrorHandler(new ApiErrorHandler());

		builder.setLogLevel(LogLevel.FULL).setLog(new Log() {
			@Override
			public void log(String arg) {
				android.util.Log.d("rest", arg);
			}
		});

		RestAdapter restAdapter = builder.build();

		appService=restAdapter.create(AppService.class);
	}


	
	public static AppService getAppService() {
		if(appService == null){
			init();
		}
		return appService;
	}

	public static void setAppService(AppService appService) {
		ApiServices.appService = appService;
	}


	
	public static class MyUrlConnectionClient extends UrlConnectionClient {

		@Override
		protected HttpURLConnection openConnection(Request request) throws IOException {
			HttpURLConnection connection = super.openConnection(request);
			connection.setConnectTimeout(30 * 1000);
			connection.setReadTimeout(30 * 1000);
			if (Build.VERSION.SDK != null && Build.VERSION.SDK_INT > 13) {
				connection.setRequestProperty("Connection", "close");
			}
			return connection;
		}

	}
}
