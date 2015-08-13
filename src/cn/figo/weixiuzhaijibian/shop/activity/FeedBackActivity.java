package cn.figo.weixiuzhaijibian.shop.activity;


import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
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
import cn.figo.weixiuzhaijibian.shop.model.FeedBack;
import cn.figo.weixiuzhaijibian.shop.ui.processdialog.WaitingProcessDialog;

import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.Validator.ValidationListener;
import com.mobsandgeeks.saripaar.annotation.Required;


/**
 * 投诉建议界面
 * 
 * 
 */
public class FeedBackActivity extends Activity  implements ValidationListener {
	private Validator mValidator;//校验器
	private MyApplication myApplication;
	
	// 返回按钮
	@InjectView(R.id.return_iv)
	ImageView returnIV;

	// 标题文字
	@InjectView(R.id.title_text_tv)
	TextView titleTextTV;
	
	// 意见输入框
	@Required(order = 0, message = "請填寫反饋意見")
	@InjectView(R.id.suggestion_et)
	EditText suggestionET;
	// 投诉单号输入框
	@Required(order = 1, message = "請填寫單號")
	@InjectView(R.id.order_no_et)
	EditText orderNoET;
	// 投诉人姓名输入框
	@Required(order = 2, message = "請填寫投訴人姓名")
	@InjectView(R.id.name_et)
	EditText nameET;
	// 联系电话输入框
	@Required(order = 3, message = "請填寫聯繫電話")
	@InjectView(R.id.phone_et)
	EditText phoneET;
	// 确定发送
	@InjectView(R.id.commit_btn)
	Button commit_btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feedback);
		ButterKnife.inject(this);
		initView();
	}

	private void initView(){ 
		myApplication = MyApplication.getInstance();
		//标题栏
		titleTextTV.setText("投訴建議");
		returnIV.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				FeedBackActivity.this.finish();
			}
		});
		
		//校验器实例化
		mValidator = new Validator(this);
		mValidator.setValidationListener(this);
		
		
		//点击确定发送
		commit_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mValidator.validate();
				
			}

		});
	}


	@Override
	public void preValidation() {

	}

	@Override
	public void onSuccess() {
		//访问服务器
		try {
			WaitingProcessDialog.showRoundProcessDialog(FeedBackActivity.this, "正在提交反饋意見...");
			feedBack();
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

	}
	
	
	

	/**投诉建议反馈请求
	 * @throws Exception
	 */
	private void feedBack() throws Exception {
		String mID = myApplication.getInstance().getLoginUid();
		String suggestionStr = suggestionET.getText().toString();
		String orderNoStr = orderNoET.getText().toString();
		String nameStr = nameET.getText().toString();
		String phoneStr = phoneET.getText().toString();
		
		ApiServices.getAppService().feedback(mID, suggestionStr, orderNoStr, nameStr, phoneStr, 1,
				new BaseCallback<FeedBack>(FeedBackActivity.this) {

					@Override
					public void success(FeedBack data, Response response) {
						// TODO Auto-generated method stub
						if (data.getCode().equals("200")) {
							Toast.makeText(FeedBackActivity.this,
									data.getMsg(), Toast.LENGTH_SHORT).show();
							FeedBackActivity.this.finish();
						} else {
							Toast.makeText(FeedBackActivity.this,
									data.getMsg(), Toast.LENGTH_SHORT).show();
						}
						super.success(data, response);
					}

					@Override
					public void failure(RetrofitError arg0) {
						Toast.makeText(FeedBackActivity.this, "網絡訪問失敗",
								Toast.LENGTH_SHORT).show();
						super.failure(arg0);
					}

				});
	}

	
}
