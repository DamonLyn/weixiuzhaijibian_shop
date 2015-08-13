package cn.figo.weixiuzhaijibian.shop.activity;

import retrofit.RetrofitError;
import retrofit.client.Response;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.figo.weixiuzhaijibian.shop.R;
import cn.figo.weixiuzhaijibian.shop.api.ApiServices;
import cn.figo.weixiuzhaijibian.shop.api.Constants;
import cn.figo.weixiuzhaijibian.shop.app.MyApplication;
import cn.figo.weixiuzhaijibian.shop.callback.BaseCallback;
import cn.figo.weixiuzhaijibian.shop.fragment.VersionUpdateDialogfragment;
import cn.figo.weixiuzhaijibian.shop.model.VersionUpdateResponse;
import cn.figo.weixiuzhaijibian.shop.ui.processdialog.WaitingProcessDialog;
import cn.figo.weixiuzhaijibian.shop.utils.CircularCutting;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;

/**
 * 设置界面
 * 
 * 
 */
@SuppressLint("NewApi") public class SettingsActivity extends Activity implements OnClickListener {

	private MyApplication myApplication;
	private PackageInfo packInfo;
	
	@InjectView(R.id.return_iv)
	ImageView returnIV;

	@InjectView(R.id.title_text_tv)
	TextView titleTextTV;
	
	//用户头像
	@InjectView(R.id.account_avatar_iv)
	ImageView AccountAvatarIV;
	
	// 用户名
	@InjectView(R.id.name_tv)
	TextView nameTV;

	// 登录按钮
	@InjectView(R.id.login_btn)
	Button loginBtn;

	// 应用版本名
	@InjectView(R.id.version_tv)
	TextView versionTV;
	
	// 注销登录按钮
	@InjectView(R.id.logout_btn)
	Button logoutBTN;

	@InjectView(R.id.account_and_security_ll)
	LinearLayout accountAndSecurityLL;
	@InjectView(R.id.feedback_ll)
	LinearLayout feedbackyLL;
	@InjectView(R.id.version_update_ll)
	LinearLayout versionUpdateLL;
	@InjectView(R.id.company_info_ll)
	LinearLayout companyInfoLL;
	@InjectView(R.id.service_terms_ll)
	LinearLayout serviceTermsLL;

	private ImageLoader imLoader;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		ButterKnife.inject(this);
		initView();
	}

	private void initView() {
		myApplication = MyApplication.getInstance();
		
		if (myApplication.isLogin()) {// 状态为已登录
			loginBtn.setVisibility(View.INVISIBLE);
			nameTV.setVisibility(View.VISIBLE);
			if (null != myApplication.getInstance().getLoginName()&&
					!myApplication.getInstance().getLoginName().equals("null")) 
			{
				nameTV.setText(myApplication.getLoginUser().getM_NAME());
			}else{
				
				nameTV.setText("請在個人中心完善信息");
			}
			

			logoutBTN.setVisibility(View.VISIBLE);
		} else {// 状态为未登录
			loginBtn.setVisibility(View.VISIBLE);
			nameTV.setVisibility(View.VISIBLE);
			logoutBTN.setVisibility(View.INVISIBLE);
		}

		titleTextTV.setText("設置");
		returnIV.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				SettingsActivity.this.finish();
			}
		});

		
		try {
			packInfo = MyApplication.getPackageVersionInfo(this);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		loginBtn.setOnClickListener(this);
		logoutBTN.setOnClickListener(this);
		accountAndSecurityLL.setOnClickListener(this);
		feedbackyLL.setOnClickListener(this);
		versionUpdateLL.setOnClickListener(this);
		companyInfoLL.setOnClickListener(this);
		serviceTermsLL.setOnClickListener(this);
		
		
	}

	@SuppressLint("NewApi") @Override
	public void onClick(View v) {
		int id = v.getId();
		Intent intent;
		switch (id) {

		case R.id.login_btn:// 跳 登录界面 界面
			intent = new Intent();
			intent.setClass(this, LoginActivity.class);
			startActivity(intent);
			break;
		case R.id.logout_btn:// 跳 登录界面 界面
//			imLoader.clearMemoryCache();
			myApplication.cleanLoginInfo();
			intent = new Intent();
			intent.setClass(this, LoginActivity.class);
			startActivity(intent);
			break;
		case R.id.account_and_security_ll:// 跳 账户与安全 界面
			if(myApplication.isLogin()){
				intent = new Intent();
				intent.setClass(this, AccountAndSecurityActivity.class);
				startActivity(intent);
			}else{
				intent = new Intent();
				intent.setClass(this, LoginActivity.class);
				startActivity(intent);
			}
			break;
		case R.id.feedback_ll:// 跳 投诉建议 界面
			if(myApplication.isLogin()){
				intent = new Intent();
				intent.setClass(this, FeedBackActivity.class);
				startActivity(intent);
			}else{
				intent = new Intent();
				intent.setClass(this, LoginActivity.class);
				startActivity(intent);
			}
			break;
		case R.id.version_update_ll:// 版本更新
			// 访问服务器
			try {
				appVersionUpdate();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
		case R.id.company_info_ll:// 跳 关于我们 界面
			intent = new Intent();
			intent.setClass(this, AboutUsActivity.class);
			startActivity(intent);
			break;
		case R.id.service_terms_ll:// 跳 服务条款 界面
			intent = new Intent();
			intent.setClass(this, ServiceTermsActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}

	/**
	 * APP版本更新信息
	 * 
	 * @throws Exception
	 */
	private void appVersionUpdate() throws Exception {
		
		final Integer versionCode = packInfo.versionCode;// 版本代码
		String versionName = packInfo.versionName;// 版本名称
		ApiServices.getAppService().getAppUpdate(2, versionCode,
				versionName,
				new BaseCallback<VersionUpdateResponse>(SettingsActivity.this) {

					@Override
					public void success(VersionUpdateResponse data,
							Response response) {
						// TODO Auto-generated method stub
						if (data.getCode().equals("200")) {
							
							if(data.getData() != null){
								if(null != data.getData().getAU_DOWNLOAD_URL() && !"".equals(data.getData().getAU_DOWNLOAD_URL())){//判断下载地址是否有效
									final String downlaodUrl = Constants.server + data.getData().getAU_DOWNLOAD_URL();
									
									Integer versionCodeFromServer = data.getData().getAU_VERSION_CODE();
									//比较服务器的应用版本与应用本地版本号大小
									if(versionCodeFromServer > versionCode){//需要更新
										
										VersionUpdateDialogfragment versionUpdateDialogfragment = new VersionUpdateDialogfragment(SettingsActivity.this,"应用有新版本发布");
										FragmentManager fragmentManager = getFragmentManager();
										versionUpdateDialogfragment.show(fragmentManager, null);
										
										versionUpdateDialogfragment.setCancelClickListener(
												new VersionUpdateDialogfragment.onVersionUpdateDialogClickListener() {
											
											@SuppressLint("NewApi") @Override
											public void onClick(VersionUpdateDialogfragment dialogfragment) {
												dialogfragment.dismiss();
											}
										});
										versionUpdateDialogfragment.setConfirmClickListener(
												new VersionUpdateDialogfragment.onVersionUpdateDialogClickListener() {
											
											@SuppressLint("NewApi") @Override
											public void onClick(VersionUpdateDialogfragment dialogfragment) {
												Toast.makeText(SettingsActivity.this, "開始下載...", 0).show();
												//执行应用更新，开始下载新版本的应用
												new UIHelper().openDownLoadService(myApplication,downlaodUrl, "wxzjb.apk");
												dialogfragment.dismiss();
											}
										});
										
									}else{//不需要更新
										Toast.makeText(SettingsActivity.this,"目前應用程序為最新版本", Toast.LENGTH_SHORT).show();
									}
								}else{//服务器的下载地址错误的情况，如管理员删除了apk安装包
									Toast.makeText(SettingsActivity.this,"未能下載到應用安裝包", Toast.LENGTH_SHORT).show();
								}
								
							}
							
						} else {
							Toast.makeText(SettingsActivity.this,
									data.getMsg(), Toast.LENGTH_SHORT).show();
						}
						super.success(data, response);
					}

					@Override
					public void failure(RetrofitError arg0) {
						Toast.makeText(SettingsActivity.this, "版本更新檢查失敗",
								Toast.LENGTH_SHORT).show();
						super.failure(arg0);
					}

				});
	}

	

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		versionTV.setText("v" + packInfo.versionName);
		if (myApplication.isLogin()) {// 状态为已登录
			loginBtn.setVisibility(View.INVISIBLE);
			nameTV.setVisibility(View.VISIBLE);
			if (!myApplication.getInstance().getLoginName().equals("null")
					&& null != myApplication.getInstance().getLoginName()) {
				nameTV.setText(myApplication.getLoginUser().getM_NAME());
			}else{
				
				nameTV.setText("請在個人中心完善信息");
			}
			logoutBTN.setVisibility(View.VISIBLE);
		} else {// 状态为未登录
			loginBtn.setVisibility(View.VISIBLE);
			nameTV.setVisibility(View.INVISIBLE);
			logoutBTN.setVisibility(View.INVISIBLE);
		}
		
		if(null != myApplication.getLoginUser().getM_HEAD_IMG()|| !myApplication.getLoginUser().getM_HEAD_IMG().equals("null")){
		String headImgPath = Constants.server+myApplication.getLoginUser().getM_HEAD_IMG();
			imLoader = ImageLoader.getInstance();
			imLoader.loadImage(headImgPath,
					new SimpleImageLoadingListener(){

						@Override
						public void onLoadingComplete(
								String imageUri,
								View view,
								Bitmap loadedImage) {
							//把下载下来的bitmap显示为圆形
							AccountAvatarIV.setImageBitmap(CircularCutting.toRoundBitmap(loadedImage));
							super.onLoadingComplete(imageUri, view, loadedImage);
						}
						
			});
			
						
		}
		
	}

}
