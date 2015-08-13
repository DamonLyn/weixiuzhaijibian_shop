package cn.figo.weixiuzhaijibian.shop.callback;

import cn.figo.weixiuzhaijibian.shop.ui.processdialog.WaitingProcessDialog;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.Activity;


/**
 * 网络请求回调方法基类
 * 
 */
public abstract class BaseCallback<T> implements Callback<T> {

	public Activity mContext;

	public BaseCallback(Activity context) {
		this.mContext = context;
	}

	@Override
	public void success(T data, Response response) {
		if(null != WaitingProcessDialog.mDialog && WaitingProcessDialog.mDialog.isShowing()){
			WaitingProcessDialog.mDialog.dismiss();
		}
	}

	@Override
	public void failure(RetrofitError arg0) {
		if(null != WaitingProcessDialog.mDialog && WaitingProcessDialog.mDialog.isShowing()){
			WaitingProcessDialog.mDialog.dismiss();
		}
	}
	
}
