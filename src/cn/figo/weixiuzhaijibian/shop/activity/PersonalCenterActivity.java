package cn.figo.weixiuzhaijibian.shop.activity;

import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.figo.weixiuzhaijibian.shop.R;
import cn.figo.weixiuzhaijibian.shop.api.ApiServices;
import cn.figo.weixiuzhaijibian.shop.api.Constants;
import cn.figo.weixiuzhaijibian.shop.app.MyApplication;
import cn.figo.weixiuzhaijibian.shop.callback.BaseCallback;
import cn.figo.weixiuzhaijibian.shop.model.Area;
import cn.figo.weixiuzhaijibian.shop.model.Master;
import cn.figo.weixiuzhaijibian.shop.model.MasterLicense;
import cn.figo.weixiuzhaijibian.shop.model.MasterResponse;
import cn.figo.weixiuzhaijibian.shop.ui.processdialog.WaitingProcessDialog;
import cn.figo.weixiuzhaijibian.shop.utils.CircularCutting;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;

/**
 * 个人中心界面
 * 
 */
public class PersonalCenterActivity extends Activity implements OnClickListener {
	private MyApplication myApplication;
	private Master master;
	private List<Area> areaList;
	private List<MasterLicense> masterLicenseList;
	
	private String[] LicenseImgUrlStrArrary;//使用数组把多张证件图片下载地址存储在对象中
	
	@InjectView(R.id.return_iv)
	ImageView returnIV;
	@InjectView(R.id.title_text_tv)
	TextView titleTextTV;

	// 头像
	@InjectView(R.id.avatar_iv)
	ImageView avatar_iv;

	@InjectView(R.id.name_tv)
	TextView nameTV;
	
	//工作经验
	@InjectView(R.id.workyear_tv)
	TextView workyearTV;
	
	//评分
	@InjectView(R.id.score_tv)
	TextView scoreTV;

	@InjectView(R.id.self_intro_tv)
	TextView selfIntroTV;

	// 完善个人信息按钮
	@InjectView(R.id.complete_information_iv)
	ImageView completeInformationIV;

	@InjectView(R.id.ll_service_area)
	LinearLayout serviceArea;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_center);
		initView();
	}

	private void initView() {
		myApplication = MyApplication.getInstance();
		ButterKnife.inject(this);
		titleTextTV.setText("個人中心");
		returnIV.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				PersonalCenterActivity.this.finish();
			}
		});
		
		completeInformationIV.setOnClickListener(this);
		completeInformationIV.setClickable(false);
		
	}

	/**
	 * 服务地区彩色标签
	 * 
	 * @param dataList
	 */
	private void initServiceArea(List<Area> dataList) {
		
		int line = dataList.size() / 3 + 1;
		for (int i = 0; i < line; i++) {

			LinearLayout lineLayout = new LinearLayout(this);
			lineLayout.setOrientation(LinearLayout.HORIZONTAL);
			lineLayout.setGravity(Gravity.CENTER_HORIZONTAL);
			LayoutParams layoutParams1 = new LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
			lineLayout.setLayoutParams(layoutParams1);
			
			int list = 3;
			if (i == line - 1) {
				list = dataList.size() % 3;
			}
			for (int j = 0; j < list; j++) {
				TextView textView = new TextView(this);
				float density = getResources().getDisplayMetrics().density;
				textView.setPadding((int) (17 * density), (int) (4 * density),
						(int) (17 * density), (int) (4 * density));
				LayoutParams layoutParams = new LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				layoutParams.setMargins((int) (7 * density),
						(int) (7 * density), (int) (7 * density),
						(int) (7 * density));
				textView.setLayoutParams(layoutParams);
				textView.setTextColor(Color.WHITE);
				textView.setTextSize(12);
				String areaName = dataList.get(i * 3 + j).getAREA_NAME();
				textView.setText(areaName);
				if (areaName.length() < 3) {
					textView.setBackgroundResource(R.drawable.bg_corners_yellow);
				} else if (areaName.length() < 4) {
					textView.setBackgroundResource(R.drawable.bg_corners_purple);
				} else {
					textView.setBackgroundResource(R.drawable.bg_corners_blue);
				}
				lineLayout.addView(textView);
			}
			serviceArea.addView(lineLayout);
		}
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.complete_information_iv:// 跳 完善资料 界面
			Intent intent1 = new Intent();
			intent1.setClass(PersonalCenterActivity.this,
					CompleteInformationActivity.class);
			intent1.putExtra("master", master);
			intent1.putExtra("LicenseImgUrlStrArrary", LicenseImgUrlStrArrary);//存储证书图片下载地址的数组对象
			startActivity(intent1);
			break;
		default:
			break;
		}
	}

	
	/**
	 * 存储证书图片下载地址的数组对象
	 */
	private void downloadLicenseImgURL(){
		int size = masterLicenseList.size();
		LicenseImgUrlStrArrary = new String[size] ;
		for (int i = 0; i < size; i++) {
			LicenseImgUrlStrArrary[i] = masterLicenseList.get(i).getML_IMG();
		}
	}
	
	
	
	/**
	 * 获取师傅个人信息请求
	 * 
	 * @throws Exception
	 */
	private void getMasterInfomation() throws Exception {
		
		ApiServices.getAppService().getMasterInfo(myApplication.loginUid,
				new BaseCallback<MasterResponse>(PersonalCenterActivity.this) {

					@Override
					public void success(MasterResponse data, Response response) {
						// TODO Auto-generated method stub
						if (data.getCode().equals("200")) {
							Toast.makeText(PersonalCenterActivity.this,
									data.getMsg(), Toast.LENGTH_SHORT).show();
							if (data.getData() != null) {//对页面进行设置
								
								master =data.getData();
								areaList = data.getAreaList();
								masterLicenseList = data.getMasterLicenseList();
								downloadLicenseImgURL();
								//对师傅所辖地区列表的返回值判空
								if(areaList != null){
									
									initServiceArea(areaList);
									areaList.clear();
								}
								selfIntroTV.setText(master.getM_INTRO());//设置师傅个人简介
								nameTV.setText(master.getM_NAME());
								if(null != master.getM_WOK_YEAR()){
									workyearTV.setText(master.getM_WOK_YEAR().toString()+"歲");
								}
								if(null != master.getM_SCORE()){
									scoreTV.setText("评分"+master.getM_SCORE());
								}
								
								//设置头像
								if (null != master.getM_HEAD_IMG()&& !"".equals(master.getM_HEAD_IMG())) {
									ImageLoader.getInstance().loadImage(Constants.server+master.getM_HEAD_IMG(),
											new SimpleImageLoadingListener(){

												@Override
												public void onLoadingComplete(
														String imageUri,
														View view,
														Bitmap loadedImage) {
													//把下载下来的bitmap显示为圆形
													avatar_iv.setImageBitmap(CircularCutting.toRoundBitmap(loadedImage));
													super.onLoadingComplete(imageUri, view, loadedImage);
												}});
								}
								//刷新应用缓存
								master.setM_NAME(master.getM_NAME());//本次网络操作改动的部分
								master.setM_LOGIN_MAIL(master.getM_LOGIN_MAIL());
								String master_ID = master.getMASTER_ID();
								master.setMASTER_ID(master.getMASTER_ID());
								master.setM_HEAD_IMG(master.getM_HEAD_IMG());
								master.setM_TEL(master.getM_TEL());
								master.setM_INTRO(master.getM_INTRO());
								myApplication.saveUserInfo(master);//重新保存到缓存文件中
								
								completeInformationIV.setClickable(true);//避免在获取不到用户信息的情况下，出现空指针状况
							}
						} else {
							Toast.makeText(PersonalCenterActivity.this,
									data.getMsg(), Toast.LENGTH_SHORT).show();
							completeInformationIV.setClickable(false);
						}
						
						
						super.success(data, response);
					}

					@Override
					public void failure(RetrofitError arg0) {
						Toast.makeText(PersonalCenterActivity.this, "訪問服務器超時，請稍後重試",
								Toast.LENGTH_SHORT).show();
						completeInformationIV.setClickable(false);
						super.failure(arg0);
					}

				});
	}
	
	
	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//更新师傅的服务地区信息
		if(null != areaList && areaList.isEmpty()){
			serviceArea.removeAllViewsInLayout();
		}
		try {
			WaitingProcessDialog.showRoundProcessDialog(PersonalCenterActivity.this, "正在加載...");
			getMasterInfomation();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
