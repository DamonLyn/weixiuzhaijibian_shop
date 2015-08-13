package cn.figo.weixiuzhaijibian.shop.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.figo.weixiuzhaijibian.shop.R;
import cn.figo.weixiuzhaijibian.shop.adapter.ViewPagerAdapter;
import cn.figo.weixiuzhaijibian.shop.api.ApiServices;
import cn.figo.weixiuzhaijibian.shop.app.MyApplication;
import cn.figo.weixiuzhaijibian.shop.callback.BaseCallback;
import cn.figo.weixiuzhaijibian.shop.model.Advertise;
import cn.figo.weixiuzhaijibian.shop.model.AdvertiseResponse;
import cn.figo.weixiuzhaijibian.shop.model.Master;
import cn.figo.weixiuzhaijibian.shop.model.MasterResponse;
import cn.figo.weixiuzhaijibian.shop.model.UnReadMsgCountResponse;
import cn.figo.weixiuzhaijibian.shop.service.DownloadService;
import cn.jpush.android.api.JPushInterface;

/**
 * 首页界面
 * 
 * 
 * 
 */
public class IndexActivity extends Activity implements OnClickListener,
		OnPageChangeListener {

	private MyApplication myApplication;
	private static List<Advertise> mAdImageList = new ArrayList<Advertise>();
	private Context mContext;
	boolean isExit = false;
	// 返回按钮
	@InjectView(R.id.return_iv)
	ImageView returnIV;

	// 标题栏文字
	@InjectView(R.id.title_text_tv)
	TextView titleTextTV;

	// 未接订单
	@InjectView(R.id.pending_order_rl)
	RelativeLayout pendingOrderRL;

	// 已接订单
	@InjectView(R.id.accepted_order_rl)
	RelativeLayout acceptedOrderRL;

	// 系统消息
	@InjectView(R.id.system_message_rl)
	RelativeLayout systemMessageRL;
	//提示消息个数图
	@InjectView(R.id.msg_tip_rl)
	RelativeLayout msgTipRL;
	@InjectView(R.id.msg_count_tv)
	TextView msgCountTV;

	// 个人中心
	@InjectView(R.id.personal_center_rl)
	RelativeLayout personalCenterRL;

	// 设置
	@InjectView(R.id.settings_rl)
	RelativeLayout settingsRL;

	@InjectView(R.id.ll_dots)
	LinearLayout dots;

	private static ViewPager sViewPager;
	private static Handler sHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (sViewPager.getCurrentItem() + 1 == mAdImageList.size()) {
				sViewPager.setCurrentItem(0);
			} else {
				sViewPager.setCurrentItem(sViewPager.getCurrentItem() + 1);
			}
			sendEmptyMessageDelayed(0, 3000);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		myApplication = MyApplication.getInstance();

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);
		ButterKnife.inject(this);
		initView();
	}

	private void initView() {
		returnIV.setVisibility(View.GONE);
		titleTextTV.setText("首頁");
		mContext = IndexActivity.this;
		sViewPager = (ViewPager) findViewById(R.id.viewpager);
		pendingOrderRL.setOnClickListener(this);
		acceptedOrderRL.setOnClickListener(this);
		systemMessageRL.setOnClickListener(this);
		personalCenterRL.setOnClickListener(this);
		settingsRL.setOnClickListener(this);
		initViewPager();

	}

	
	@Override
	public void onClick(View v) {
		int id = v.getId();
		Intent intent;
		switch (id) {
		case R.id.pending_order_rl:// 跳 待接订单 界面
//			if (myApplication.isLogin()) {
//			intent = new Intent();
//			intent.setClass(this, PendingOrderListActivity.class);
//			startActivity(intent);
//			}else{
//				 intent = new Intent();
//				 intent.setClass(this, LoginActivity.class);
//				 startActivity(intent);
//			}
			if (myApplication.isLogin()) {
				
				try {
					getMasterInfomation();//获取师傅用户的个人信息中的审核状态 
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				 intent = new Intent();
				 intent.setClass(this, LoginActivity.class);
				 startActivity(intent);
			}
			
			break;
		case R.id.accepted_order_rl:// 跳 已接订单 界面
			if (myApplication.isLogin()) {
			intent = new Intent();
			intent.setClass(this, AcceptedOrderListActivity.class);
			startActivity(intent);
			}else{
				 intent = new Intent();
				 intent.setClass(this, LoginActivity.class);
				 startActivity(intent);
			}
			break;
		case R.id.system_message_rl:// 跳 系统消息界面
			intent = new Intent();
			intent.setClass(this, SystemInformListActivity.class);
			startActivity(intent);
			break;

		case R.id.personal_center_rl:// 跳 个人中心 界面

			 if (myApplication.isLogin()) {
			intent = new Intent();
			intent.setClass(this, PersonalCenterActivity.class);
			startActivity(intent);
			 }else {
			 intent = new Intent();
			 intent.setClass(this, LoginActivity.class);
			 startActivity(intent);
			 }

			break;

		case R.id.settings_rl:// 跳 设置 界面
			intent = new Intent();
			intent.setClass(this, SettingsActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}

	private void initViewPager() {
		// ---------------ViewPager-----------------
		// 获得屏幕像素相关信息
		DisplayMetrics dm = new DisplayMetrics();
		((Activity) mContext).getWindowManager().getDefaultDisplay()
				.getMetrics(dm);
		// 设置ViewPager宽高（由于布局中控制不了宽高）
		sViewPager.getLayoutParams().height = dm.widthPixels / 2;
		try {
			getAd();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sViewPager.setOnPageChangeListener(this);
		sViewPager.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (sHandler.hasMessages(0)) {
					sHandler.removeMessages(0);
				}
				if (event.getAction() == MotionEvent.ACTION_UP) {
					sHandler.sendEmptyMessageDelayed(0, 3000);
				}
				return false;
			}

		});
		for (int i = 0; i < mAdImageList.size(); i++) {
			ImageView dot = new ImageView(mContext);
			if (i == 0) {
				dot.setImageResource(R.drawable.ic_dot_light);
			} else {
				dot.setImageResource(R.drawable.ic_dot_dark);
			}
			dot.setId(i);
			dot.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					sViewPager.setCurrentItem(v.getId());
				}
			});
			dots.addView(dot);
		}
	}

	private void initAdDot() {
		for (int i = 0; i < mAdImageList.size(); i++) {
			ImageView dot = new ImageView(mContext);
			if (i == 0) {
				dot.setImageResource(R.drawable.ic_dot_light);
			} else {
				dot.setImageResource(R.drawable.ic_dot_dark);
			}
			dot.setId(i);
			dot.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					sViewPager.setCurrentItem(v.getId());
				}
			});
			dots.addView(dot);
		}
	}

	/**
	 * 请求获取广告
	 * 
	 * @throws Exception
	 */
	private void getAd() throws Exception {
		ApiServices.getAppService().getAd(1,
				new BaseCallback<AdvertiseResponse>((Activity) mContext) {
					@Override
					public void success(AdvertiseResponse data,
							Response response) {
						if (data.getCode().equals("200")) {
							if (data.getData() != null) {
								mAdImageList.clear();
								mAdImageList.addAll(data.getData());
								initAdDot();
								sViewPager.setAdapter(new ViewPagerAdapter(
										IndexActivity.this, mAdImageList));
							} else {
								Toast.makeText(IndexActivity.this, "獲取廣告列表失敗",
										0);
							}

						}
						super.success(data, response);
					}

					@Override
					public void failure(RetrofitError arg0) {
						super.failure(arg0);
					}
				});
	}
	
	
	/**获取系统消息未读的条目数量请求
	 * @throws Exception
	 */
	private void masterUnReadMsgCountPost() throws Exception {
		String mID = myApplication.getLoginUser().getMASTER_ID();
		ApiServices.getAppService().masterUnReadMsgCount(mID,
				new BaseCallback<UnReadMsgCountResponse>((Activity) mContext) {
					@Override
					public void success(UnReadMsgCountResponse data,
							Response response) {
						if (data.getCode().equals("200")) {
							if(data.getData() > 0){
								msgTipRL.setVisibility(View.VISIBLE);
//								msgCountTV.setText(data.getData().toString());	//消息的个数							
							}else{
								msgTipRL.setVisibility(View.INVISIBLE);
							}

						}else{
							msgTipRL.setVisibility(View.INVISIBLE);
						}
						super.success(data, response);
					}

					@Override
					public void failure(RetrofitError arg0) {
						super.failure(arg0);
					}
				});
	}
	
	
	
	/**
	 * 获取师傅个人信息请求
	 * 
	 * @throws Exception
	 */
	private void getMasterInfomation() throws Exception {
		
		ApiServices.getAppService().getMasterInfo(myApplication.loginUid,
				new BaseCallback<MasterResponse>(IndexActivity.this) {

					@Override
					public void success(MasterResponse data, Response response) {
						// TODO Auto-generated method stub
						if (data.getCode().equals("200")) {
							if (data.getData() != null) {//对页面进行设置
								if(null != data.getData().getM_AUDIT_STATUS() ){//判断这个师傅用户的审核状态
									switch (data.getData().getM_AUDIT_STATUS()) {
									case 0://不通过
										Toast.makeText(IndexActivity.this, "您尚未通過資料審核，暫時未能接單",
												Toast.LENGTH_LONG).show();
										break;
									case 1://通过
										Intent i = new Intent();
										i.setClass(IndexActivity.this, PendingOrderListActivity.class);
										startActivity(i);
										break;
									case 2://审核不通过
										Toast.makeText(IndexActivity.this, "您的資料審核不通过，暫時未能接單",
												Toast.LENGTH_LONG).show();
										break;
									default:
										break;
									}
								}
							}
						}
						
						super.success(data, response);
					}

					@Override
					public void failure(RetrofitError arg0) {
						Toast.makeText(IndexActivity.this, "訪問服務器超時，請稍後重試",
								Toast.LENGTH_SHORT).show();
						super.failure(arg0);
					}

				});
	}
	
	
	
	
	

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int arg0) {
		for (int i = 0; i < dots.getChildCount(); i++) {
			if (i == arg0) {
				((ImageView) dots.getChildAt(i))
						.setImageResource(R.drawable.ic_dot_light);
			} else {
				((ImageView) dots.getChildAt(i))
						.setImageResource(R.drawable.ic_dot_dark);
			}
		}

	}

	@Override
	public void onResume() {
		super.onResume();
		
		try {//访问网络
			masterUnReadMsgCountPost();//刷新未读的系统消息数量
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sHandler.sendEmptyMessageDelayed(0, 3000);
		JPushInterface.onResume(this);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		sHandler.removeMessages(0);
		JPushInterface.onPause(this);
	}

	/**
	 * 监听返回--是否退出程序
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// 是否退出应用
			exitBy2Click();
		}
		return false;
	}

	/**
	 *  * 双击退出函数  
	 */
	private void exitBy2Click() {
		Timer tExit = null;

		if (isExit == false) {
			isExit = true; // 准备退出
			Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
			tExit = new Timer();
			tExit.schedule(new TimerTask() {

				@Override
				public void run() {
					isExit = false;
				}
			}, 2000);
		} else {
			//取消指定的更新通知
			NotificationManager mNotificationManager = (NotificationManager) myApplication.getSystemService(android.content.Context.NOTIFICATION_SERVICE);
			mNotificationManager.cancel(DownloadService.NOTIFY_ID);
			//完全结束进程
			finish();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
		}
	}

}
