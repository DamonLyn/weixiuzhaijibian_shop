package cn.figo.weixiuzhaijibian.shop.activity;

import java.util.Set;

import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.figo.weixiuzhaijibian.shop.R;
import cn.figo.weixiuzhaijibian.shop.api.ApiServices;
import cn.figo.weixiuzhaijibian.shop.app.MyApplication;
import cn.figo.weixiuzhaijibian.shop.callback.BaseCallback;
import cn.figo.weixiuzhaijibian.shop.model.MasterResponse;
import cn.figo.weixiuzhaijibian.shop.ui.processdialog.WaitingProcessDialog;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.Validator.ValidationListener;
import com.mobsandgeeks.saripaar.annotation.Regex;
import com.mobsandgeeks.saripaar.annotation.Required;

/**
 * 登录界面
 * 
 */
public class LoginActivity extends Activity implements OnClickListener,
		ValidationListener {
	private MyApplication myApplication;
	private Validator mValidator;// 校验器
	// 邮箱输入值
	String mailArg = "";
	// 密码输入值
	String passwordArg = "";
	// 邮箱输入框
	@Required(order = 0, message = "郵箱不能為空")
	@Regex(order = 1, pattern = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$", message = "請輸入正確的郵箱")
	@InjectView(R.id.mail_et)
	EditText mailET;
	// 密码输入框
	@Required(order = 2, message = "密碼不能為空")
	@InjectView(R.id.password_et)
	EditText passwordET;

	// 注册按钮
	@InjectView(R.id.register_btn)
	Button rigisterBTN;
	// 注册按钮
	@InjectView(R.id.login_btn)
	Button loginBTN;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		ButterKnife.inject(this);
		initView();
	}

	private void initView() {
		myApplication = MyApplication.getInstance();
		mValidator = new Validator(this);
		mValidator.setValidationListener(this);
		rigisterBTN.setOnClickListener(this);
		loginBTN.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.register_btn:// 跳 注册页面
			Intent intent1 = new Intent();
			intent1.setClass(this, RegisterActivity.class);
			startActivity(intent1);
			break;
		case R.id.login_btn:// 登录操作
			mValidator.validate();
			break;

		default:
			break;
		}
	}

	@Override
	public void preValidation() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSuccess() {
		// 校验成功，进行网络访问

		try {
			WaitingProcessDialog.showRoundProcessDialog(LoginActivity.this, "正在登錄...");
			login();
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
	 * 师傅端用户登录
	 * 
	 * @throws Exception
	 */
	private void login() throws Exception {
		// 邮箱输入值
		mailArg = mailET.getText().toString();
		// 密码输入值
		passwordArg = passwordET.getText().toString();

		ApiServices.getAppService().masterLogin(mailArg, passwordArg,
				new BaseCallback<MasterResponse>(LoginActivity.this) {

					@Override
					public void success(MasterResponse data, Response response) {
						// TODO Auto-generated method stub
						if (data.getCode().equals("200")) {
							Toast.makeText(LoginActivity.this, data.getMsg(),
									Toast.LENGTH_SHORT).show();
							myApplication.cleanLoginInfo();
							// 判断在登录的过程中，登录界面没有被关闭时，才进行对用户信息的初始化
							if (!LoginActivity.this.isFinishing()) {
								if(data.getData() != null){
									// 设置用户信息到APP中
									myApplication.saveUserInfo(data.getData());// 保存用户信息到本地
									myApplication.initLogin();//重新初始化用户信息
									
									JPushInterface.setAlias(LoginActivity.this, data.getData().getMASTER_ID(), 
										new TagAliasCallback() {
											
											@Override
											public void gotResult(int arg0, String arg1, Set<String> arg2) {
												// TODO Auto-generated method stub
												switch (arg0) {//arg0 是返回码，值为0代表设置成功
												case 0:
								                   Log.d("setAlias", "Set tag and alias success");
													break;
												case 6002:
													Log.d("setAlias","Failed to set alias and tags due to timeout. Try again after 60s.");
													break;

												default:
													Log.e("setAlias", "Failed with errorCode = " + arg0);
													break;
												}
											}
										}	);
									LoginActivity.this.finish();
									
								}
								
								
							}

						} else {
							Toast.makeText(LoginActivity.this, data.getMsg(),
									Toast.LENGTH_SHORT).show();
						}
						super.success(data, response);
					}

					@Override
					public void failure(RetrofitError arg0) {
						Toast.makeText(LoginActivity.this, "訪問服務器超時，請稍後重試",
								Toast.LENGTH_SHORT).show();
						super.failure(arg0);
					}

				});
	}

}
