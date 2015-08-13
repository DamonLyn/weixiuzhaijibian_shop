package cn.figo.weixiuzhaijibian.shop.activity;

import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.Activity;
import android.os.Bundle;
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
import cn.figo.weixiuzhaijibian.shop.model.BaseResponse;
import cn.figo.weixiuzhaijibian.shop.model.Master;
import cn.figo.weixiuzhaijibian.shop.ui.processdialog.WaitingProcessDialog;

/**
 *修改用户姓名界面
 *
 */
public class ModifyNameActivity extends Activity {
	private MyApplication myApplication;
	String updateName;
		//返回按钮
		@InjectView(R.id.return_iv)
		ImageView returnIV;

		//标题栏文字
		@InjectView(R.id.title_text_tv)
		TextView titleTextTV;
		
		
		
		//姓名输入框
		@InjectView(R.id.name_et)
		EditText nameET;
		
		//确定
		@InjectView(R.id.confirm_tv)
		TextView confirmTV;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_modify_name);
			ButterKnife.inject(this);
			initView();
		}

		private void initView() {
			myApplication = MyApplication.getInstance();
			titleTextTV.setText("修改姓名");
			
			returnIV.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					ModifyNameActivity.this.finish();
				}
			});
			String name = (String) getIntent().getSerializableExtra("name");
			nameET.setText(name);
			
			confirmTV.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					try {
						WaitingProcessDialog.showRoundProcessDialog(ModifyNameActivity.this, "正在為你更新用戶姓名...");
						updateMasterNamePost();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			});
			
			
			
		}
		
		
		
		private void updateMasterNamePost() throws Exception {
			String mID =myApplication.getLoginUid();
			updateName = nameET.getText().toString();

			ApiServices.getAppService().updateMasterName(mID, updateName,
					new BaseCallback<BaseResponse>(ModifyNameActivity.this) {

						@Override
						public void success(BaseResponse data, Response response) {
							// TODO Auto-generated method stub
							if (data.getCode().equals("200")) {
								//刷新应用缓存
								Master master = new Master();
								master.setM_NAME(updateName);//本次网络操作改动的部分
								master.setM_LOGIN_MAIL(myApplication.getLoginMail());
								master.setMASTER_ID(myApplication.getLoginUid());
								master.setM_HEAD_IMG(myApplication.getLoginHeadImgPath());
								master.setM_TEL(myApplication.getLoginPhone());
								master.setM_INTRO(myApplication.getLoginIntroduction());
								myApplication.saveUserInfo(master);//重新保存到缓存文件中
								ModifyNameActivity.this.finish();
							} else {
								Toast.makeText(ModifyNameActivity.this, data.getMsg(),
										Toast.LENGTH_SHORT).show();
							}
							super.success(data, response);
						}

						@Override
						public void failure(RetrofitError arg0) {
							Toast.makeText(ModifyNameActivity.this, "訪問服務器超時，請稍後重試",
									Toast.LENGTH_SHORT).show();
							super.failure(arg0);
						}

					});
		}
		
		
		
		
		
}
