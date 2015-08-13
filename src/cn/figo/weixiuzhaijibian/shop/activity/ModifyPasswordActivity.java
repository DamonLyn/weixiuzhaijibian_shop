package cn.figo.weixiuzhaijibian.shop.activity;

import java.io.Serializable;

import retrofit.RetrofitError;
import retrofit.client.Response;

import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.Validator.ValidationListener;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Required;
import com.mobsandgeeks.saripaar.annotation.TextRule;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.figo.weixiuzhaijibian.shop.R;
import cn.figo.weixiuzhaijibian.shop.api.ApiServices;
import cn.figo.weixiuzhaijibian.shop.app.MyApplication;
import cn.figo.weixiuzhaijibian.shop.callback.BaseCallback;
import cn.figo.weixiuzhaijibian.shop.model.MasterResponse;
import cn.figo.weixiuzhaijibian.shop.model.UpdatePwd;
import cn.figo.weixiuzhaijibian.shop.ui.processdialog.WaitingProcessDialog;

/**
 * 修改密码界面
 *
 */
public class ModifyPasswordActivity extends Activity implements ValidationListener{
	private MyApplication myApplication;
	private Validator mValidator;// 校验器
	private String oldPassword;
	//返回按钮
	@InjectView(R.id.return_iv)
	ImageView returnIV;

	//标题栏文字
	@InjectView(R.id.title_text_tv)
	TextView titleTextTV;

	// 确定按钮
	@InjectView(R.id.confirm_tv)
	TextView confirmTV;
	
	// 邮箱
	@InjectView(R.id.mail_tv)
	TextView mailTV;
	
	// 密码输入框
	@Required(order = 0, message = "密碼不能為空")
	@TextRule(order= 1,minLength = 6,message= "密碼長度至少為6位數" )
	@Password(order = 3)
	@InjectView(R.id.password_et)
	EditText passwordET;
	// 确认密码输入框
	@Required(order = 2, message = "請確認密碼")
	@ConfirmPassword(order = 4,message = "兩次輸入的密碼不一致")
	@InjectView(R.id.confirm_password_et)
	EditText confirmPasswordET;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modify_password);
		ButterKnife.inject(this);
		initView();
	}

	private void initView() {
		myApplication = MyApplication.getInstance();
		oldPassword = (String) getIntent().getSerializableExtra("oldPassword");
		//初始化校验器
		mValidator = new Validator(this);
		mValidator.setValidationListener(this);
		titleTextTV.setText("修改密碼");
		String loginMail = myApplication.getLoginMail();
		mailTV.setText(loginMail);
		returnIV.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ModifyPasswordActivity.this.finish();
			}
		});
		
		confirmTV.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mValidator.validate();
			}
		});
	}

	@Override
	public void preValidation() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSuccess() {
		// 校验成功，进行网络访问
		try {
			WaitingProcessDialog.showRoundProcessDialog(ModifyPasswordActivity.this, "正在更新你的新密碼...");
			updatePwdPost();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onFailure(View failedView, Rule<?> failedRule) {
		String message = failedRule.getFailureMessage();
		if (failedView instanceof EditText) {
			failedView.requestFocus();
			Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
		}
		
	}

	@Override
	public void onValidationCancelled() {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
	/**
	 * 师傅端修改密码请求
	 */
	private void updatePwdPost()throws Exception{
		String mID = myApplication.getInstance().getLoginUid();
		String oldPwd = oldPassword;
		String newPwd = passwordET.getText().toString();
		ApiServices.getAppService().masterUpdatePwd(mID, oldPwd, newPwd, 
				new BaseCallback<UpdatePwd>(ModifyPasswordActivity.this) {

			@Override
			public void success(UpdatePwd data, Response response) {
				// TODO Auto-generated method stub
				if (data.getCode().equals("200")) {
					Toast.makeText(ModifyPasswordActivity.this,
							data.getMsg(), Toast.LENGTH_SHORT).show();
					ModifyPasswordActivity.this.finish();
				} else {
					Toast.makeText(ModifyPasswordActivity.this,
							data.getMsg(), Toast.LENGTH_SHORT).show();
				}
				super.success(data, response);
			}

			@Override
			public void failure(RetrofitError arg0) {
				Toast.makeText(ModifyPasswordActivity.this, "訪問服務器超時，請稍後重試",
						Toast.LENGTH_SHORT).show();
				super.failure(arg0);
			}

		});
	}
}
