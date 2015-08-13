package cn.figo.weixiuzhaijibian.shop.activity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import cn.figo.weixiuzhaijibian.shop.api.Constants;
import cn.figo.weixiuzhaijibian.shop.app.MyApplication;
import cn.figo.weixiuzhaijibian.shop.callback.BaseCallback;
import cn.figo.weixiuzhaijibian.shop.model.BaseResponse;
import cn.figo.weixiuzhaijibian.shop.model.Order6ServiceType;
import cn.figo.weixiuzhaijibian.shop.model.OrderLimitResponse;
import cn.figo.weixiuzhaijibian.shop.ui.processdialog.WaitingProcessDialog;
import cn.figo.weixiuzhaijibian.shop.utils.GetPathFromUri4kitkat;

import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.Validator.ValidationListener;
import com.mobsandgeeks.saripaar.annotation.Required;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * 待接订单详情界面
 * 
 * 
 */
public class PendingOrderDetailsActivity extends Activity implements
		OnClickListener, ValidationListener {

	private Order6ServiceType o6ServicesType;
	private boolean isSuccessDownload = false;
	private String recordFileDir = "";
	private String recordFileName = "";
	
	private Validator mValidator;// 校验器
	//语音操作对象  
    private MediaPlayer mPlayer = null;  
    
    protected boolean isBeyoundStandard = false;//判断未接单量的标识
    private Integer max;
	// 返回按钮
	@InjectView(R.id.return_iv)
	ImageView returnIV;

	// 标题栏文字
	@InjectView(R.id.title_text_tv)
	TextView titleTextTV;
	
	@InjectView(R.id.repair_type_tv)
	TextView repairTypeTV;

	
	@InjectView(R.id.repair_content_tv)
	TextView repairContentTV;

	@InjectView(R.id.order_contact_name_tv)
	TextView orderContactNameTV;

	@InjectView(R.id.order_address_tv)
	TextView orderAddressTV;

	@InjectView(R.id.order_book_time_tv)
	TextView orderBookTimeTV;

	@InjectView(R.id.preview_iv)
	ImageView previewIV;

	@InjectView(R.id.play_recording_iv)
	ImageView playRecordingIV;
	
	//维修内容输入框
	@Required(order = 0 ,message="請輸入维修内容")
	@InjectView(R.id.repair_content_et)
	EditText repairContentET;
	
	//维修经费最低报价输入框
	@Required(order = 1 ,message="請輸入维修经费最低报价")
	@InjectView(R.id.min_price_et)
	EditText minPriceET;
	
	//维修经费最高输入框
	@Required(order = 2 ,message="請輸入维修经费上限报价")
	@InjectView(R.id.max_price_et)
	EditText maxPriceET;
	
	//提交按钮
	@InjectView(R.id.commit_btn)
	Button commitBtn;
	
	
	
	
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				System.out.println("------->>>>> ready to paly recording");
				
				break;

			default:
				break;
			}

		}
	};
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pending_order_details);
		ButterKnife.inject(this);
		initView();
	}

	private void initView() {
		// 标题栏
		titleTextTV.setText("待接訂單");
		returnIV.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				PendingOrderDetailsActivity.this.finish();
			}
		});
		
		o6ServicesType = (Order6ServiceType) getIntent().getSerializableExtra(
				"Order6ServiceType");
		
		
		repairTypeTV.setText(o6ServicesType.getST_NAME());
		repairContentTV.setText(o6ServicesType.getO_REPAIR_CONTENT());
		orderContactNameTV.setText(o6ServicesType.getO_USER_NAME());
		orderAddressTV.setText(o6ServicesType.getAREA_NAME());
		
		

		
		
		playRecordingIV.setOnClickListener(this);
		commitBtn.setOnClickListener(this);
		mValidator = new Validator(this);
		mValidator.setValidationListener(this);
		try {
			getMasterAcceptOrderCountPost();//获取师傅的未完成订单量请求
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//设置时间
		SimpleDateFormat tpl = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		
		SimpleDateFormat sdfTimeBegin = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm");
		SimpleDateFormat sdfTimeEnd = new SimpleDateFormat(
				"HH:mm");
		Date begin = null;
		Date end = null;
		
		try {
			begin = tpl.parse(o6ServicesType.getO_TIME_START());
			end = tpl.parse(o6ServicesType.getO_TIME_END());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(null != begin && null != end){
			String time = sdfTimeBegin.format(begin) + " 至 " + sdfTimeEnd.format(end);
			orderBookTimeTV.setText(time);
		}
		
		
		
		
		//如果客户有上传他需要维修的地方或物品的图片 ，则加载
		if (o6ServicesType.getO_SCENE_IMG() != null) {
			ImageLoader.getInstance().displayImage(
					Constants.server + o6ServicesType.getO_SCENE_IMG(),
					previewIV);
		}
		//如已对此订单报价，则会进入以下的if语句代码块
		if(null != o6ServicesType.getMasterPrice()){
			if(null != o6ServicesType.getMasterPrice().getMP_CONTENT()){
				repairContentET.setText(o6ServicesType.getMasterPrice().getMP_CONTENT());
				repairContentET.setFocusable(false);
//				repairContentET.setOnKeyListener(null);
			}
			
			if(null != o6ServicesType.getMasterPrice().getMP_LOW_PRICE()&& null != o6ServicesType.getMasterPrice().getMP_HEIGHT_PRICE()){
				minPriceET.setText(o6ServicesType.getMasterPrice().getMP_LOW_PRICE().toString());
				minPriceET.setFocusable(false);
//				minPriceET.setOnKeyListener(null);
				
				maxPriceET.setText(o6ServicesType.getMasterPrice().getMP_HEIGHT_PRICE());
				maxPriceET.setFocusable(false);
//				maxPriceET.setOnKeyListener(null);
			}
			
			commitBtn.setVisibility(View.INVISIBLE);
			Toast.makeText(PendingOrderDetailsActivity.this, "此需求訂單您已提交過一次報價", 0).show();
		}
		
		downloadTheRecord();//下载录音功能
		
	}

	/**下载录音文件功能
	 * @param downurl
	 * @param fileDir
	 * @param fileName
	 */
	public void downlaodRecording(String downurl, String fileDir,
			String fileName) {

		OutputStream output = null;
		try {
			/*
			 * 通过URL取得HttpURLConnection 要网络连接成功，需在AndroidMainfest.xml中进行权限配置
			 * <uses-permission android:name="android.permission.INTERNET" />
			 */
			URL url = new URL(downurl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 取得inputStream，并将流中的信息写入SDCard

			/*
			 * 写前准备 1.在AndroidMainfest.xml中进行权限配置 <uses-permission
			 * android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
			 * 取得写入SDCard的权限 2.取得SDCard的路径：
			 * Environment.getExternalStorageDirectory() 3.检查要保存的文件上是否已经存在
			 * 4.不存在，新建文件夹，新建文件 5.将input流中的信息写入SDCard 6.关闭流
			 */
			recordFileName = MyApplication.getInstance().getAppCacheFile().getAbsolutePath()
								+ "/" + fileDir + "/" + fileName;// 文件存储路径
			File file = new File(recordFileName);
			InputStream input = conn.getInputStream();

			if (!file.exists()) {
				
				recordFileDir = MyApplication.getInstance().getAppCacheFile()
				.getAbsolutePath()+ "/" + fileDir;
				new File(recordFileDir).mkdir();// 新建文件夹
			}
				file.createNewFile();// 新建文件
				output = new FileOutputStream(file);
				// 读取大文件
				byte[] buffer = new byte[4 * 1024]; 
				int len; 
				while ((len=input.read(buffer))!= -1) { 
					output.write(buffer,0,len);
				}
				output.flush();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != output) {
					isSuccessDownload = true;
					output.close();
					System.out.println("------->>>>> download record success");
				}
				
			} catch (IOException e) {
				System.out.println("------->>>>> download record fail");
				e.printStackTrace();
			}
		}

	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.play_recording_iv:
			
			playRecord();
			
			

			break;
		case R.id.commit_btn:
			if(isBeyoundStandard){//如果未完成订单量到达或超出系统限制未完成量
				Toast.makeText(PendingOrderDetailsActivity.this, "你的未完成訂單數量超出所允许的最大的未完成订单量("+max+"單)", 0).show();
			}else{//未完成订单量没达到系统限制量，则允许提交本次报价
				mValidator.validate();
			}
			
			
			break;
		default:
			break;
		}
	}
	
	
	
	/**
	 * 新建一条线程下载录音
	 */
	private void downloadTheRecord(){
		if (null != o6ServicesType.getO_VOICE() && !"".equals(o6ServicesType.getO_VOICE())) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					downlaodRecording(Constants.server + o6ServicesType.getO_VOICE(), "recordCache","record.3gp");
					
					if(isSuccessDownload){
						
						Message msg = handler.obtainMessage();
						msg.what = 0;
						handler.sendMessage(msg);
					}
					
				}
			}).start();
		}
	}	
	
	
	
	
	
	
	
	/**
	 *播放录音 
	 */
	private void playRecord(){
		mPlayer = new MediaPlayer();
		try {
			if(recordFileName.equals(MyApplication.getInstance().getAppCacheFile().getAbsolutePath()+"/recordCache/record.3gp")){
				File the3gpFile = new File(recordFileName);
				Uri fileUri = Uri.fromFile(the3gpFile);
				String path = GetPathFromUri4kitkat.getPath(this, fileUri);
				mPlayer.setDataSource(path);
				mPlayer.prepare();
				mPlayer.start();
			}else{
				Toast.makeText(this, "客戶並無上傳錄音", 0).show();
			}

		} catch (IOException e) {
			e.printStackTrace();
			Toast.makeText(this, "播放失敗", 0).show();
		}
//        try{
//			File the3gpFile = new File(recordFileName);
//			Uri fileUri = Uri.fromFile(the3gpFile);
//        	String path = GetPathFromUri4kitkat.getPath(this,fileUri);
//            mPlayer.setDataSource(path);
//            mPlayer.prepare();
//            mPlayer.start();
//        }catch(IOException e){
//            e.printStackTrace();
//            Toast.makeText(this, "播放失敗", 0).show();
//        }  
		
	}
	
	
	
	
	
	/**师傅对用户维修需求提交报价请求
	 * @throws Exception
	 */
	private void submitMasterPricePost() throws Exception {
		
		String order_ID = o6ServicesType.getORDER_ID();
		String loginUid = MyApplication.getInstance().getLoginUid();
		String repairContent = repairContentET.getText().toString();
		String minPrice = minPriceET.getText().toString();
		String maxPrice = maxPriceET.getText().toString();
		String st_NAME = o6ServicesType.getST_NAME();
		ApiServices.getAppService().submitMasterPrice(order_ID,loginUid,repairContent,minPrice,maxPrice,st_NAME,
						new BaseCallback<BaseResponse>(PendingOrderDetailsActivity.this) {

							@Override
							public void success(BaseResponse data,
									Response response) {
								// TODO Auto-generated method stub
								if (data.getCode().equals("200")) {
									Toast.makeText(
											PendingOrderDetailsActivity.this,
											data.getMsg(), Toast.LENGTH_SHORT)
											.show();
									PendingOrderDetailsActivity.this.finish();

								} else {
									Toast.makeText(
											PendingOrderDetailsActivity.this,
											data.getMsg(), Toast.LENGTH_SHORT)
											.show();
								}
								super.success(data, response);
							}

							@Override
							public void failure(RetrofitError arg0) {
								Toast.makeText(
										PendingOrderDetailsActivity.this,
										"訪問服務器超時，請稍後重試", Toast.LENGTH_SHORT)
										.show();
								super.failure(arg0);
							}

						});
	}
	
	/**获取师傅当前未完成订单数请求（用于设置接单的单量限制）
	 * @throws Exception
	 */
	private void getMasterAcceptOrderCountPost() throws Exception {
		
		String loginUid = MyApplication.getInstance().getLoginUid();

		ApiServices.getAppService().getMasterUnfinishOrderCount(loginUid,
				new BaseCallback<OrderLimitResponse>(PendingOrderDetailsActivity.this) {
			
			@Override
			public void success(OrderLimitResponse data,
					Response response) {
				// TODO Auto-generated method stub
				if (data.getCode().equals("200")) {
					Integer acceptedOrderCounts = data.getData();//这个师傅已经接了的订单数量
					max = data.getMax();//系统所允许的最大的未完成订单量
					if(acceptedOrderCounts >= max){
						Toast.makeText(PendingOrderDetailsActivity.this, "你的未完成訂單數量超出所允许的最大的未完成订单量("+max+"單)", 0).show();
						isBeyoundStandard = true;
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

	@Override
	public void preValidation() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSuccess() {
		String minPrice = minPriceET.getText().toString();
		String maxPrice = maxPriceET.getText().toString();
		Integer low = Integer.valueOf(minPrice);
		Integer height = Integer.valueOf(maxPrice);
		if(height >= low){
			try {
				WaitingProcessDialog.showRoundProcessDialog(PendingOrderDetailsActivity.this, "正在提交報價...");
				submitMasterPricePost();//师傅对用户维修需求提交报价请求
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			Toast.makeText(
					PendingOrderDetailsActivity.this,"左側為最低報價，右側為最高報價", Toast.LENGTH_LONG).show();
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
	
	
	
	
	
	
}
