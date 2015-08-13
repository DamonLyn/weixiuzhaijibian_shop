package cn.figo.weixiuzhaijibian.shop.activity;

import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.figo.weixiuzhaijibian.shop.R;
import cn.figo.weixiuzhaijibian.shop.api.ApiServices;
import cn.figo.weixiuzhaijibian.shop.app.MyApplication;
import cn.figo.weixiuzhaijibian.shop.callback.BaseCallback;
import cn.figo.weixiuzhaijibian.shop.model.MailCode;
import cn.figo.weixiuzhaijibian.shop.model.Master;
import cn.figo.weixiuzhaijibian.shop.model.MasterResponse;
import cn.figo.weixiuzhaijibian.shop.ui.processdialog.WaitingProcessDialog;
import cn.figo.weixiuzhaijibian.shop.utils.CheckUtil;
import android.view.ViewGroup.LayoutParams;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.Validator.ValidationListener;
import com.mobsandgeeks.saripaar.annotation.Regex;
import com.mobsandgeeks.saripaar.annotation.Required;
import com.mobsandgeeks.saripaar.annotation.TextRule;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.PropertyValuesHolder;

/**
 * 注册界面
 * 
 */
public class RegisterActivity extends Activity implements OnClickListener,
		OnTouchListener, ValidationListener {
	static private final int VELOCITY = 400;
	private float density;
	private Boolean isChecked = false;// 输入框中的内容是否经检查的标识
	private Validator mValidator;// 校验器
	private float mStartY = 0;
	private int mEndTopY = 0;
	private DisplayMetrics mDM;

	private MyApplication myApplication;
	protected Master master;
	
	// 邮箱输入值
	String mailArg = "";
	// 密码输入值
	String passwordArg = "";
	// 验证码输入值
	String codeArg = "";

	@InjectView(R.id.iv_right_arrow1)
	ImageView rightArrow1;
	@InjectView(R.id.iv_right_arrow2)
	ImageView rightArrow2;
	@InjectView(R.id.iv_right_arrow3)
	ImageView rightArrow3;
	@InjectView(R.id.iv_right_arrow4)
	ImageView rightArrow4;
	@InjectView(R.id.iv_right_arrow5)
	ImageView rightArrow5;
	@InjectView(R.id.iv_right_arrow6)
	ImageView rightArrow6;
	@InjectView(R.id.iv_right_arrow7)
	ImageView rightArrow7;
	@InjectView(R.id.iv_right_arrow8)
	ImageView rightArrow8;
	@InjectView(R.id.iv_right_arrow9)
	ImageView rightArrow9;
	@InjectView(R.id.iv_left_arrow1)
	ImageView leftArrow1;
	@InjectView(R.id.iv_left_arrow2)
	ImageView leftArrow2;
	@InjectView(R.id.iv_left_arrow3)
	ImageView leftArrow3;
	@InjectView(R.id.iv_left_arrow4)
	ImageView leftArrow4;
	@InjectView(R.id.iv_left_arrow5)
	ImageView leftArrow5;
	@InjectView(R.id.iv_left_arrow6)
	ImageView leftArrow6;
	@InjectView(R.id.iv_left_arrow7)
	ImageView leftArrow7;
	@InjectView(R.id.iv_left_arrow8)
	ImageView leftArrow8;
	@InjectView(R.id.iv_left_arrow9)
	ImageView leftArrow9;

	@InjectView(R.id.ll_root)
	LinearLayout rootLayout;

	// 点击验证邮箱文字
	@InjectView(R.id.tv_verify_email)
	TextView verifyEmail;

	// 邮箱输入框
	@Required(order = 0, message = "郵箱不能為空")
	@Regex(order = 1, pattern = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$", message = "請輸入正確的郵箱")
	@InjectView(R.id.mail_et)
	EditText mailET;
	
	// 密码输入框
	@Required(order = 2, message = "密碼不能為空")
	@TextRule(order= 3,minLength = 6,message= "密碼長度至少為6位數" )
	@InjectView(R.id.password_et)
	EditText passwordET;

	// 验证码输入框
	@Required(order = 4, message = "驗證碼不能為空")
	@InjectView(R.id.code_et)
	EditText codeET;

	
	// 注册按钮
	@InjectView(R.id.next_step_btn)
	Button nextStepBTN;
	
	// 已有账户去登录提示
	@InjectView(R.id.tv_login)
	TextView toLogin;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		getWindow().setLayout(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		mDM = getResources().getDisplayMetrics();
		ButterKnife.inject(this);
		initView();
	}

	private void initView() {
		myApplication = MyApplication.getInstance();
		mValidator = new Validator(this);
		mValidator.setValidationListener(this);
		density = getResources().getDisplayMetrics().density;
		initLeftArrow();
		initRightArrow();
		verifyEmail.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
		verifyEmail.setOnClickListener(this);
		nextStepBTN.setOnClickListener(this);
		toLogin.setOnTouchListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.tv_verify_email:
			// 检查邮箱地址是否为空，还有格式是否正确
			checkMail();

			if (isChecked) {
				// 验证邮箱接口
				try {
					WaitingProcessDialog.showRoundProcessDialog(
							RegisterActivity.this, "正在發送驗證碼到驗證郵箱...");
					verifyMailbox();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			break;
			
			
		case R.id.next_step_btn:// 点击 下一步 按钮
			mValidator.validate();// 开始校验输入框中的内容
			break;

		default:
			break;
		}

	}

	/**
	 * 在发送验证用户的邮箱之前，先检查邮箱的格式是否正确
	 */
	public void checkMail() {
		mailArg = mailET.getText().toString();
		// 判断邮箱
		if (mailArg.equals("") || mailArg == null) {
			Toast.makeText(RegisterActivity.this, "請填寫郵箱", Toast.LENGTH_SHORT)
					.show();
			mailET.requestFocus();
			return;
		} else if (!CheckUtil.isMail(mailArg)) {
			Toast.makeText(RegisterActivity.this, "郵箱格式不正確", Toast.LENGTH_SHORT)
					.show();
			mailET.requestFocus();
			return;
		} else {
			// mailET.setKeyListener(null);//检查完毕设置不可点击
			isChecked = true;// 检查完毕,邮箱格式符合要求
		}

	}

	private void initLeftArrow() {
		PropertyValuesHolder p1 = PropertyValuesHolder.ofFloat("translationX",
				0, ((int) -9 * density));
		PropertyValuesHolder p2 = PropertyValuesHolder.ofFloat("alpha", 0, 1);
		PropertyValuesHolder p3 = PropertyValuesHolder
				.ofFloat("alpha", 1, 0.9F);
		PropertyValuesHolder p4 = PropertyValuesHolder.ofFloat("alpha", 0.9F,
				0.8F);
		PropertyValuesHolder p5 = PropertyValuesHolder.ofFloat("alpha", 0.8F,
				0.7F);
		PropertyValuesHolder p6 = PropertyValuesHolder.ofFloat("alpha", 0.7F,
				0.6F);
		PropertyValuesHolder p7 = PropertyValuesHolder.ofFloat("alpha", 0.6F,
				0.5F);
		PropertyValuesHolder p8 = PropertyValuesHolder.ofFloat("alpha", 0.5F,
				0.4F);
		PropertyValuesHolder p9 = PropertyValuesHolder.ofFloat("alpha", 0.4F,
				0.3F);
		PropertyValuesHolder p10 = PropertyValuesHolder.ofFloat("alpha", 0.3F,
				0);
		ObjectAnimator o1 = ObjectAnimator.ofPropertyValuesHolder(leftArrow1,
				p1, p2).setDuration(VELOCITY);
		o1.setRepeatCount(Animation.INFINITE);
		o1.start();
		ObjectAnimator o2 = ObjectAnimator.ofPropertyValuesHolder(leftArrow2,
				p1, p3).setDuration(VELOCITY);
		o2.setRepeatCount(Animation.INFINITE);
		o2.start();
		ObjectAnimator o3 = ObjectAnimator.ofPropertyValuesHolder(leftArrow3,
				p1, p4).setDuration(VELOCITY);
		o3.setRepeatCount(Animation.INFINITE);
		o3.start();
		ObjectAnimator o4 = ObjectAnimator.ofPropertyValuesHolder(leftArrow4,
				p1, p5).setDuration(VELOCITY);
		o4.setRepeatCount(Animation.INFINITE);
		o4.start();
		ObjectAnimator o5 = ObjectAnimator.ofPropertyValuesHolder(leftArrow5,
				p1, p6).setDuration(VELOCITY);
		o5.setRepeatCount(Animation.INFINITE);
		o5.start();
		ObjectAnimator o6 = ObjectAnimator.ofPropertyValuesHolder(leftArrow6,
				p1, p7).setDuration(VELOCITY);
		o6.setRepeatCount(Animation.INFINITE);
		o6.start();
		ObjectAnimator o7 = ObjectAnimator.ofPropertyValuesHolder(leftArrow7,
				p1, p8).setDuration(VELOCITY);
		o7.setRepeatCount(Animation.INFINITE);
		o7.start();
		ObjectAnimator o8 = ObjectAnimator.ofPropertyValuesHolder(leftArrow8,
				p1, p9).setDuration(VELOCITY);
		o8.setRepeatCount(Animation.INFINITE);
		o8.start();
		ObjectAnimator o9 = ObjectAnimator.ofPropertyValuesHolder(leftArrow9,
				p1, p10).setDuration(VELOCITY);
		o9.setRepeatCount(Animation.INFINITE);
		o9.start();
	}

	private void initRightArrow() {
		PropertyValuesHolder p1 = PropertyValuesHolder.ofFloat("translationX",
				0, ((int) 8 * density));
		PropertyValuesHolder p2 = PropertyValuesHolder.ofFloat("alpha", 0, 1);
		PropertyValuesHolder p3 = PropertyValuesHolder
				.ofFloat("alpha", 1, 0.9F);
		PropertyValuesHolder p4 = PropertyValuesHolder.ofFloat("alpha", 0.9F,
				0.8F);
		PropertyValuesHolder p5 = PropertyValuesHolder.ofFloat("alpha", 0.8F,
				0.7F);
		PropertyValuesHolder p6 = PropertyValuesHolder.ofFloat("alpha", 0.7F,
				0.6F);
		PropertyValuesHolder p7 = PropertyValuesHolder.ofFloat("alpha", 0.6F,
				0.5F);
		PropertyValuesHolder p8 = PropertyValuesHolder.ofFloat("alpha", 0.5F,
				0.4F);
		PropertyValuesHolder p9 = PropertyValuesHolder.ofFloat("alpha", 0.4F,
				0.3F);
		PropertyValuesHolder p10 = PropertyValuesHolder.ofFloat("alpha", 0.3F,
				0);
		ObjectAnimator o1 = ObjectAnimator.ofPropertyValuesHolder(rightArrow1,
				p1, p2).setDuration(VELOCITY);
		o1.setRepeatCount(Animation.INFINITE);
		o1.start();
		ObjectAnimator o2 = ObjectAnimator.ofPropertyValuesHolder(rightArrow2,
				p1, p3).setDuration(VELOCITY);
		o2.setRepeatCount(Animation.INFINITE);
		o2.start();
		ObjectAnimator o3 = ObjectAnimator.ofPropertyValuesHolder(rightArrow3,
				p1, p4).setDuration(VELOCITY);
		o3.setRepeatCount(Animation.INFINITE);
		o3.start();
		ObjectAnimator o4 = ObjectAnimator.ofPropertyValuesHolder(rightArrow4,
				p1, p5).setDuration(VELOCITY);
		o4.setRepeatCount(Animation.INFINITE);
		o4.start();
		ObjectAnimator o5 = ObjectAnimator.ofPropertyValuesHolder(rightArrow5,
				p1, p6).setDuration(VELOCITY);
		o5.setRepeatCount(Animation.INFINITE);
		o5.start();
		ObjectAnimator o6 = ObjectAnimator.ofPropertyValuesHolder(rightArrow6,
				p1, p7).setDuration(VELOCITY);
		o6.setRepeatCount(Animation.INFINITE);
		o6.start();
		ObjectAnimator o7 = ObjectAnimator.ofPropertyValuesHolder(rightArrow7,
				p1, p8).setDuration(VELOCITY);
		o7.setRepeatCount(Animation.INFINITE);
		o7.start();
		ObjectAnimator o8 = ObjectAnimator.ofPropertyValuesHolder(rightArrow8,
				p1, p9).setDuration(VELOCITY);
		o8.setRepeatCount(Animation.INFINITE);
		o8.start();
		ObjectAnimator o9 = ObjectAnimator.ofPropertyValuesHolder(rightArrow9,
				p1, p10).setDuration(VELOCITY);
		o9.setRepeatCount(Animation.INFINITE);
		o9.start();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mStartY = event.getRawY();
			break;
		case MotionEvent.ACTION_MOVE:
			float offset = event.getRawY() - mStartY;
			int topY = (int) (rootLayout.getTop() + offset);
			if (topY > 0) {
				topY = 0;
			}
			rootLayout.layout(rootLayout.getLeft(), topY,
					rootLayout.getRight(), topY + mDM.heightPixels);
			System.out.println(topY);
			mStartY = event.getRawY();
			rootLayout.postInvalidate();
			break;
		case MotionEvent.ACTION_UP:
			int uPTopY = rootLayout.getTop();
			if (mEndTopY - uPTopY > mDM.heightPixels / 4) {
				ObjectAnimator oa = ObjectAnimator.ofFloat(rootLayout,
						"translationY", -mEndTopY, -mDM.heightPixels - uPTopY)
						.setDuration(600);
				oa.addListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						super.onAnimationEnd(animation);
						finish();
					}
				});
				oa.start();
			} else {
				ObjectAnimator
						.ofFloat(rootLayout, "translationY", -mEndTopY, -uPTopY)
						.setDuration(300).start();
				mEndTopY = uPTopY;
			}
			break;

		default:
			break;
		}
		return true;
	}

	/**
	 * 获取验证码
	 * 
	 * @throws Exception
	 */
	private void verifyMailbox() throws Exception {

		ApiServices.getAppService().getMailCode(1, mailArg,
				new BaseCallback<MailCode>(RegisterActivity.this) {

					@Override
					public void success(MailCode data, Response response) {
						// TODO Auto-generated method stub
						if (data.getCode().equals("200")) {
							Toast.makeText(RegisterActivity.this,
									data.getMsg(), Toast.LENGTH_SHORT).show();
						} else {
							Toast.makeText(RegisterActivity.this,
									data.getMsg(), Toast.LENGTH_SHORT).show();
						}
						super.success(data, response);
					}

					@Override
					public void failure(RetrofitError arg0) {
						Toast.makeText(RegisterActivity.this, "訪問服務器超時，請稍後重試",
								Toast.LENGTH_SHORT).show();
						super.failure(arg0);
					}

				});
	}

	/**
	 * 师傅端用户注册
	 * 
	 * @throws Exception
	 */
	private void rigist() throws Exception {

		ApiServices.getAppService().masterRegist(mailArg, codeArg, passwordArg,
				new BaseCallback<MasterResponse>(RegisterActivity.this) {

					@Override
					public void success(MasterResponse data, Response response) {
						// TODO Auto-generated method stub
						if (data.getCode().equals("200")) {
							Toast.makeText(RegisterActivity.this,
									data.getMsg(), Toast.LENGTH_LONG).show();
							master = data.getData();
							// 设置用户信息到APP中
							myApplication.saveUserInfo(data.getData());// 保存用户信息到本地
							Intent intent = new Intent();
							intent.putExtra("master",master);
							intent.setClass(RegisterActivity.this,
									CompleteInformationActivity.class);
							startActivity(intent);
							RegisterActivity.this.finish();
						} else {
							Toast.makeText(RegisterActivity.this,
									data.getMsg(), Toast.LENGTH_SHORT).show();
						}
						super.success(data, response);
					}

					@Override
					public void failure(RetrofitError arg0) {
						Toast.makeText(RegisterActivity.this, "訪問服務器超時，請稍後重試",
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
		// 校验成功，进行网络访问
		// 邮箱输入值
		mailArg = mailET.getText().toString();
		// 密码输入值
		passwordArg = passwordET.getText().toString();
		// 验证码输入值
		codeArg = codeET.getText().toString();
		try {
			WaitingProcessDialog.showRoundProcessDialog(RegisterActivity.this,
					"註冊中，請稍後...");
			rigist();
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

}
