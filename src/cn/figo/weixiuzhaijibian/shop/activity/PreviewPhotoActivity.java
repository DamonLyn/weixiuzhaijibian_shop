package cn.figo.weixiuzhaijibian.shop.activity;

import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.figo.weixiuzhaijibian.shop.R;
import cn.figo.weixiuzhaijibian.shop.api.ApiServices;
import cn.figo.weixiuzhaijibian.shop.api.Constants;
import cn.figo.weixiuzhaijibian.shop.app.MyApplication;
import cn.figo.weixiuzhaijibian.shop.callback.BaseCallback;
import cn.figo.weixiuzhaijibian.shop.model.BaseResponse;

import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * 图片预览界面
 *
 */
public class PreviewPhotoActivity extends Activity{
	private MyApplication myApplication;
	private String imgUrl;
	//装载被预览的图片控件
	@InjectView(R.id.priview_photo_iv)
	ImageView priviewPhotoIV;
	
	@InjectView(R.id.delete_btn)
	Button deleteBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		myApplication = MyApplication.getInstance();

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_preview_photo);
		ButterKnife.inject(this);
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		imgUrl = (String) getIntent().getSerializableExtra("imgUrl");//在个人中心界面被点击的图片的Url
		ImageLoader.getInstance().displayImage(Constants.server + imgUrl, priviewPhotoIV);
		
		deleteBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					deleteMasterLicensePost();//删除图片请求
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	
	
	
	/**删除图片操作请求
	 * @throws Exception
	 */
	private void deleteMasterLicensePost() throws Exception {
		String mID = myApplication.getInstance().getLoginUid();
		ApiServices.getAppService().deleteMasterLicense(mID,imgUrl,
						new BaseCallback<BaseResponse>(this) {
					
					@Override
					public void success(BaseResponse data, Response response) {
						if(data.getCode().equals("200")){
							Log.d("test", "msg--->>"+data.getMsg());
							
							Intent intent = new Intent();
							intent.putExtra("imgUrl", imgUrl);
							setResult(RESULT_OK, intent);
							
							PreviewPhotoActivity.this.finish();
						}else{
							Toast.makeText(PreviewPhotoActivity.this,
									data.getMsg(), Toast.LENGTH_SHORT).show();
						}
						
						
						super.success(data, response);
					}
					
					@Override
					public void failure(RetrofitError arg0) {
						super.failure(arg0);
					}
					
				});
		
	}
}
