package cn.figo.weixiuzhaijibian.shop.fragment;

import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.figo.weixiuzhaijibian.shop.R;
import cn.figo.weixiuzhaijibian.shop.activity.AccountAndSecurityActivity;
import cn.figo.weixiuzhaijibian.shop.activity.ModifyPasswordActivity;
import cn.figo.weixiuzhaijibian.shop.activity.SelectServiceTypeActivity;
import cn.figo.weixiuzhaijibian.shop.api.ApiServices;
import cn.figo.weixiuzhaijibian.shop.app.MyApplication;
import cn.figo.weixiuzhaijibian.shop.callback.BaseCallback;
import cn.figo.weixiuzhaijibian.shop.model.MasterResponse;
import cn.figo.weixiuzhaijibian.shop.ui.processdialog.WaitingProcessDialog;

import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.Validator.ValidationListener;
import com.mobsandgeeks.saripaar.annotation.Required;

/**
 * 验证旧密码对话框（即修改密码）
 *
 */
public class InputOldPasswordDialogfragment extends DialogFragment implements
		OnClickListener,ValidationListener{
	private onInputOldPasswordDialogClickListener mCancelClickListener;
	private onInputOldPasswordDialogClickListener mConfirmClickListener;
	private Context mContext;
	private Validator mValidator;
	private String password;
	@Required(order = 0 ,message = "請輸入舊密碼")
	@InjectView(R.id.et_password)
	EditText passwordET;
	@InjectView(R.id.tv_cancel)
	TextView cancel;
	@InjectView(R.id.tv_ok)
	TextView ok;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
		getDialog().getWindow().setBackgroundDrawableResource(
				android.R.color.transparent);
		View view = inflater.inflate(
				R.layout.dialogfragment_input_old_password, null);
		initView(view);
		return view;
	}

	private void initView(View view) {
		ButterKnife.inject(this, view);
		mValidator = new Validator(this);
		mValidator.setValidationListener(this);
		mContext = getActivity();
		setCancelable(false);// 设置点击对话框以外不被取消
		cancel.setOnClickListener(this);
		ok.setOnClickListener(this);
	}

	public static interface onInputOldPasswordDialogClickListener {
		public void onClick(InputOldPasswordDialogfragment dialogfragment);
	}

	public InputOldPasswordDialogfragment setCancelClickListener(
			onInputOldPasswordDialogClickListener listener) {
		mCancelClickListener = listener;
		return this;
	}
	public InputOldPasswordDialogfragment setConfirmClickListener(
			onInputOldPasswordDialogClickListener listener) {
		mConfirmClickListener = listener;
		return this;
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.tv_cancel:
			if(mCancelClickListener != null ){
				mCancelClickListener.onClick(InputOldPasswordDialogfragment.this);
			}else{
				dismiss();
			}
			
			break;
		case R.id.tv_ok:
			mValidator.validate();
			break;

		default:
			break;
		}
	}

	
	private void verdictOldPassword() throws Exception{
		password = passwordET.getText().toString();
		ApiServices.getAppService().masterLogin(
				MyApplication.getInstance().getLoginMail(),password,
				new BaseCallback<MasterResponse>((Activity) mContext) {
					@Override
					public void success(MasterResponse data, Response response) {
						if (data.getCode().equals("200")) {
//							if(mConfirmClickListener != null){
//								mConfirmClickListener.onClick(InputOldPasswordDialogfragment.this);
//							}else{
//								dismiss();
//							}
							
							Intent intent = new Intent();
							intent.putExtra("oldPassword", password);
							intent.setClass(getActivity(), ModifyPasswordActivity.class);
							startActivity(intent);
							dismiss();
						} else {
							Toast.makeText(mContext, "輸入密碼有誤",
									Toast.LENGTH_SHORT).show();
						}
						super.success(data, response);
					}

					@Override
					public void failure(RetrofitError arg0) {
						Toast.makeText(mContext, "訪問服務器超時，請稍後重試",
								Toast.LENGTH_SHORT).show();
						super.failure(arg0);
					}

				});
	}
	
	
	
	
	
	@Override
	public void preValidation() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSuccess() {
		try {
			WaitingProcessDialog.showRoundProcessDialog(getActivity(), "正在驗證你的密碼...");

			verdictOldPassword();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void onFailure(View failedView, Rule<?> failedRule) {
		String message = failedRule.getFailureMessage();
		Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
		
	}

	@Override
	public void onValidationCancelled() {
		// TODO Auto-generated method stub
		
	}
}
