package cn.figo.weixiuzhaijibian.shop.activity;

import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
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
import cn.figo.weixiuzhaijibian.shop.model.PostMsg;

/**
 * 系统消息内容详情界面
 *
 */
public class SystemInformDetailsActivity extends Activity {
	private PostMsg postMsg;
	
	
	@InjectView(R.id.return_iv)
		ImageView returnIV;

		@InjectView(R.id.title_text_tv)
		TextView titleTextTV;
		
		@InjectView(R.id.msg_title_tv)
		TextView msgTitleTV;
		
		@InjectView(R.id.content_tv)
		TextView contentTV;
		
		@InjectView(R.id.date_tv)
		TextView dateTV;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_system_inform_details);
			ButterKnife.inject(this);
			initView();
		}

		private void initView() {
			titleTextTV.setText("系统消息");
			returnIV.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					SystemInformDetailsActivity.this.finish();
				}
			});
			
			postMsg =  (PostMsg) getIntent().getSerializableExtra("PostMsg");
			msgTitleTV.setText(postMsg.getPM_TITLE());
			contentTV.setText(postMsg.getPM_CONTENT());
			dateTV.setText(postMsg.getPM_ADD_TIME());
			Integer hasRead = postMsg.getHasRead(); 
			if(!(hasRead >= 1)){//这个标识的意思是此消息尚未被阅读过
				try {
					readPostMsgPost();//标识此消息已被阅读
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		
		
		/**获取消息的内容详情
		 * @throws Exception
		 */
		private void getSysInfoDetailsPost() throws Exception {
			String pmID = postMsg.getPsot_MSG_ID();
			ApiServices.getAppService().getMsgDetail(pmID,
					new BaseCallback<BaseResponse>(SystemInformDetailsActivity.this) {

						@Override
						public void success(BaseResponse data, Response response) {
							// TODO Auto-generated method stub
							if(!SystemInformDetailsActivity.this.isFinishing()){
								
								if (data.getCode().equals("200")) {

									
								} else {
									Toast.makeText(SystemInformDetailsActivity.this,
											data.getMsg(), Toast.LENGTH_SHORT).show();
								}
								
								
							}
							
							super.success(data, response);
						}

						@Override
						public void failure(RetrofitError arg0) {
							Toast.makeText(SystemInformDetailsActivity.this, "無法訪問服務器",
									Toast.LENGTH_SHORT).show();
							super.failure(arg0);
						}

					});
		}
		
		
		
		/**标识此消息已被阅读
		 * @throws Exception
		 */
		private void readPostMsgPost() throws Exception {
			String pmID = postMsg.getPsot_MSG_ID();
			String mID = MyApplication.getInstance().getLoginUser().getMASTER_ID();
			String mType = "1";
			ApiServices.getAppService().readPostMsg(pmID,mID,mType,
					new BaseCallback<BaseResponse>(SystemInformDetailsActivity.this) {

						@Override
						public void success(BaseResponse data, Response response) {
							// TODO Auto-generated method stub
							if(!SystemInformDetailsActivity.this.isFinishing()){
								
								if (data.getCode().equals("200")) {

									
								} else {
									Toast.makeText(SystemInformDetailsActivity.this,
											data.getMsg(), Toast.LENGTH_SHORT).show();
								}
								
								
							}
							
							super.success(data, response);
						}

						@Override
						public void failure(RetrofitError arg0) {
							Toast.makeText(SystemInformDetailsActivity.this, "無法訪問服務器",
									Toast.LENGTH_SHORT).show();
							super.failure(arg0);
						}

					});
		}
}
