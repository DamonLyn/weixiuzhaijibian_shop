package cn.figo.weixiuzhaijibian.shop.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedFile;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
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
import cn.figo.weixiuzhaijibian.shop.model.ImagePathResponse;
import cn.figo.weixiuzhaijibian.shop.model.Order6ServiceTypePrice;
import cn.figo.weixiuzhaijibian.shop.ui.processdialog.WaitingProcessDialog;
import cn.figo.weixiuzhaijibian.shop.utils.GetPathFromUri4kitkat;
import cn.figo.weixiuzhaijibian.shop.utils.ImageScale;

import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.Validator.ValidationListener;
import com.mobsandgeeks.saripaar.annotation.Required;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;

/**
 * 已接订单订单
 * 
 * 
 */
public class AcceptedOrderDetailsActivity extends Activity implements
		OnClickListener, ValidationListener {

	private Order6ServiceTypePrice o6ServicesTypePrice;
	// 语音操作对象
	private MediaPlayer mPlayer = null;

	
	
	private String recordFileDir = "";
	private String recordFileName = "";
	private String mRecordUrl = "";
	private boolean isSuccessDownload = false;

	private ArrayAdapter<String> adapter = null;
	private String[] howLong = new String[] { "無", "三個月", "六個月", "九個月", "十二個月" };
	private String spinnerContent;// 存储被选中的保修期

	String AfterRepairImgPath;// 用于存储服务器返回的头像图片路径
	private boolean isHadRepairImgPost = false; // 图片是否已上传标识

	private PopupWindow mSelectImageWindow;
	private Uri mImageFileUri;
	private static final int TOOK_BY_CAMERA = 0;
	private static final int TOOK_BY_ALBUM = 2;
	private Bitmap afterRepairBitmap;
	
	private Validator mValidator;// 校验器
	
	@InjectView(R.id.ll_parent)
	LinearLayout parent;

	// 返回按鈕
	@InjectView(R.id.return_iv)
	ImageView returnIV;

	// 标题文字
	@InjectView(R.id.title_text_tv)
	TextView titleTextTV;

	// 订单号
	@InjectView(R.id.order_no_tv)
	TextView orderNoTV;

	// 订单状态
	@InjectView(R.id.order_state_tv)
	TextView orderStateTV;

	// 客户联系人
	@InjectView(R.id.order_contact_name_tv)
	TextView orderContactNameTV;

	// 客户联系人电话
	@InjectView(R.id.order_contact_phone_tv)
	TextView orderContactPhoneTV;

	// 维修类型
	@InjectView(R.id.repair_type_tv)
	TextView repairTypeTV;

	// 预约时间
	@InjectView(R.id.order_book_time_tv)
	TextView orderBookTimeTV;

	// 预约地址
	@InjectView(R.id.order_book_address_tv)
	TextView orderBookAddressTV;

	// 用户需处图
	@InjectView(R.id.preview_1_iv)
	ImageView needToRepairedImg;

	// 录音播放按钮
	@InjectView(R.id.play_recording_iv)
	ImageView playRecordingIV;

	// 维修后图片上传提示图
	@InjectView(R.id.preview_2_iv)
	ImageView afterRepairedImg;

	// 保修期
	@InjectView(R.id.spinner)
	Spinner spinner;

	// 维修内容输入框
	@Required(order = 0 ,message="請輸入维修内容")
	@InjectView(R.id.repair_content_et)
	EditText repairContentET;

	// 维修交易经费输入框
	@Required(order = 1 ,message="請輸入此次实际的维修费用")
	@InjectView(R.id.price_et)
	EditText priceET;

	// 提交按钮
	@InjectView(R.id.commit_btn)
	Button commitBtn;

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case 0://播放录音
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
		setContentView(R.layout.activity_accepted_orders_details);
		ButterKnife.inject(this);
		initView();
	}

	private void initView() {
		// 标题栏
		titleTextTV.setText("已接訂單");
		returnIV.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AcceptedOrderDetailsActivity.this.finish();
			}
		});
		o6ServicesTypePrice = (Order6ServiceTypePrice) getIntent()
				.getSerializableExtra("Order6ServiceType");
		// 设置视图内容
		orderNoTV.setText(o6ServicesTypePrice.getO_NO());
		
		orderContactNameTV.setText(o6ServicesTypePrice.getO_USER_NAME());
		orderContactPhoneTV.setText(o6ServicesTypePrice.getO_USER_TEL());
		repairTypeTV.setText(o6ServicesTypePrice.getST_NAME());
		orderBookTimeTV.setText(o6ServicesTypePrice.getO_USER_COMFIRM_TIME());
		orderBookAddressTV.setText(o6ServicesTypePrice.getO_ADDRESS());

		playRecordingIV.setOnClickListener(this);
		initSpinner();//初始化Spinner
		
		//设置客户的需处图片
		if(null != o6ServicesTypePrice.getO_SCENE_IMG() && !"".equals(o6ServicesTypePrice.getO_SCENE_IMG())){
				String imgPath = Constants.server+o6ServicesTypePrice.getO_SCENE_IMG();
				ImageLoader.getInstance().displayImage(imgPath, needToRepairedImg);
		}
		//判断订单的完成状态	
		Integer o_STATUS = o6ServicesTypePrice.getO_STATUS();
			switch (o_STATUS) {
			case 1://未完成的订单
				orderStateTV.setText("未完成");
				break;
				
			case 2://已完成的订单
				orderStateTV.setText("已完成");
				commitBtn.setVisibility(View.INVISIBLE);
				break;
				
			case 3://已完成的订单
				orderStateTV.setText("已完成");
				commitBtn.setVisibility(View.INVISIBLE);
				break;

			default:
				break;
			}	
			

			
	    initSelectImageWindow();//初始化拍照以及从相册选择的弹出窗
		afterRepairedImg.setOnClickListener(this);
		
		commitBtn.setOnClickListener(this);
		mValidator = new Validator(this);
		mValidator.setValidationListener(this);
		
		downloadTheRecord();//下载这个订单的录音文件
		
		
		
		//设置完成的需处图片
		if(null != o6ServicesTypePrice.getO_REPAIRED_IMG() && !"".equals(o6ServicesTypePrice.getO_REPAIRED_IMG())){
			ImageLoader.getInstance().loadImage(Constants.server+o6ServicesTypePrice.getO_REPAIRED_IMG(),
					new SimpleImageLoadingListener(){

						@Override
						public void onLoadingComplete(
								String imageUri,
								View view,
								Bitmap loadedImage) {
							afterRepairedImg.setImageBitmap(loadedImage);
							afterRepairedImg.setClickable(false);
							super.onLoadingComplete(imageUri, view, loadedImage);
						}});
		}
		
		
		//设置已完成的订单的文本内容
		if(null != o6ServicesTypePrice.getO_REPAIR_CONTENT() && !"".equals(o6ServicesTypePrice.getO_REPAIR_CONTENT())){//获取到的已完成的订单的维修内容
			repairContentET.setText(o6ServicesTypePrice.getO_REPAIR_CONTENT());
			repairContentET.setKeyListener(null);
		}
		if(null != o6ServicesTypePrice.getO_FINAL_PRICE() && !"".equals(o6ServicesTypePrice.getO_FINAL_PRICE())){//获取到的已完成的订单的实际成交价
			priceET.setText(o6ServicesTypePrice.getO_FINAL_PRICE().toString());
			priceET.setKeyListener(null);
		}
		//设置已完成的订单的文本内容
		if(null != o6ServicesTypePrice.getO_MAINTENANCE_CYCLE() && !"".equals(o6ServicesTypePrice.getO_MAINTENANCE_CYCLE())){//获取到的已完成的订单的实际成交价
			String o_MAINTENANCE_CYCLE = o6ServicesTypePrice.getO_MAINTENANCE_CYCLE();
			if(o_MAINTENANCE_CYCLE.equals("無")){
				spinner.setSelection(0, true);//设置spinner的显示内容
			}
			if(o_MAINTENANCE_CYCLE.equals("三個月")){
				spinner.setSelection(1, true);//设置spinner的显示内容
			}
			if(o_MAINTENANCE_CYCLE.equals("六個月")){
				spinner.setSelection(2, true);//设置spinner的显示内容
			}
			if(o_MAINTENANCE_CYCLE.equals("九個月")){
				spinner.setSelection(3, true);//设置spinner的显示内容
			}
			if(o_MAINTENANCE_CYCLE.equals("十二個月")){
				spinner.setSelection(4, true);//设置spinner的显示内容
			}
			spinner.setClickable(false);
		}
	}

	private void initSpinner() {
		adapter = new ArrayAdapter<String>(AcceptedOrderDetailsActivity.this,
				R.layout.spinner_item, howLong);
		spinner.setAdapter(adapter);
		spinner.setSelection(0, true);//设置第一行为默认显示
		spinnerContent = spinner.getItemAtPosition(0).toString();//默认获取值为第一行的值
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				spinnerContent = spinner.getSelectedItem().toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}

		});

	}

	
	/**
	 * 新建一条线程下载录音
	 */
	private void downloadTheRecord(){
//		mRecordUrl = Constants.server + o6ServicesTypePrice.getO_VOICE();
//			if (mRecordUrl != null && !mRecordUrl.equals("")) {
//
//				new Thread(new Runnable() {
//
//					@Override
//					public void run() {
//						downlaodRecording(mRecordUrl, "recordCache",
//								"record.3gp");
//
//						if (isSuccessDownload) {
//
//							Message msg = handler.obtainMessage();
//							msg.what = 0;
//							handler.sendMessage(msg);
//						}
//
//					}
//				}).start();
//			}
			
			if (null != o6ServicesTypePrice.getO_VOICE() && !"".equals(o6ServicesTypePrice.getO_VOICE())) {
				new Thread(new Runnable() {

					@Override
					public void run() {
						downlaodRecording(Constants.server + o6ServicesTypePrice.getO_VOICE(), "recordCache","record.3gp");
						
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
	 * 下载录音文件功能
	 * 
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
			recordFileName = MyApplication.getInstance().getAppCacheFile()
					.getAbsolutePath()
					+ "/" + fileDir + "/" + fileName;// 文件存储路径
			File file = new File(recordFileName);
			InputStream input = conn.getInputStream();

			if (!file.exists()) {

				recordFileDir = MyApplication.getInstance().getAppCacheFile()
						.getAbsolutePath()
						+ "/" + fileDir;
				new File(recordFileDir).mkdir();// 新建文件夹
			}
			file.createNewFile();// 新建文件
			output = new FileOutputStream(file);
			// 读取大文件
			byte[] buffer = new byte[4 * 1024];
			int len;
			while ((len = input.read(buffer)) != -1) {
				output.write(buffer, 0, len);
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

	/**
	 * 播放录音
	 */
	private void playRecord() {
			mPlayer = new MediaPlayer();//录音对象初始化
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
	}

	

	
	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.play_recording_iv://点击播放录音操作
			playRecord();
			break;

		case R.id.preview_2_iv:
			mSelectImageWindow.showAtLocation(parent, Gravity.FILL, 0, 0);// 上传设置弹出窗
			break;
		case R.id.commit_btn:
			mValidator.validate();//校验器开始校验
			
			break;
		default:
			break;
		}
	}

	

	/**
	 * 点击上传头像就会弹出
	 */
	private void initSelectImageWindow() {
		View view = getLayoutInflater().inflate(R.layout.ppw_image_setting,
				null);
		Button camera = (Button) view
				.findViewById(R.id.item_popupwindows_camera);
		Button album = (Button) view.findViewById(R.id.item_popupwindows_Photo);
		Button cancel = (Button) view
				.findViewById(R.id.item_popupwindows_cancel);
		camera.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				camera();
				mSelectImageWindow.dismiss();
			}
		});
		album.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 从相册中选择
				Album();
				mSelectImageWindow.dismiss();
			}
		});
		cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 取消
				mSelectImageWindow.dismiss();
			}
		});
		mSelectImageWindow = new PopupWindow(view, LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT, true);
		mSelectImageWindow.setBackgroundDrawable(new ColorDrawable());
		mSelectImageWindow.setTouchable(true);
	}

	/**
	 * 拍照功能
	 */
	private void camera() {
		mImageFileUri = createTempFile("afterRepairImage", "afterRepairImage.jpg");// 初始化头像图片保存的路径
		if (mImageFileUri != null) {
			Intent openCameraIntent = new Intent(
					MediaStore.ACTION_IMAGE_CAPTURE);
			openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, mImageFileUri);
			startActivityForResult(openCameraIntent, TOOK_BY_CAMERA);
		}
	}

	/**
	 * 相册功能
	 * 
	 */
	private void Album() {
		Intent openAlbumIntent1 = new Intent(Intent.ACTION_PICK,
				Media.EXTERNAL_CONTENT_URI);
		startActivityForResult(openAlbumIntent1, TOOK_BY_ALBUM);

	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case TOOK_BY_CAMERA:// 拍完维修后需处图片返回
			if (resultCode == this.RESULT_OK) {

				try {
					String path = GetPathFromUri4kitkat.getPath(this,
							mImageFileUri);
					afterRepairBitmap = ImageScale
							.decodeSampledBitmapFromResource(path);// 压缩图片
					afterRepairedImg.setImageBitmap(afterRepairBitmap);
					Uri imgSaclefileUri = saveBitmap2Saclefile(
							afterRepairBitmap, "afterRepairImage", "afterRepairImage.jpg");// 保存afterRepairBitmap成图片
					uploadAfterRepairedImgPost(imgSaclefileUri);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			break;
		case TOOK_BY_ALBUM:// 从相册中选择完维修后需处图片返回
			if (resultCode == RESULT_OK && data != null) {

				try {
					mImageFileUri = data.getData();
					String path = GetPathFromUri4kitkat.getPath(this,
							mImageFileUri);
					afterRepairBitmap = ImageScale
							.decodeSampledBitmapFromResource(path);// 压缩图片
					afterRepairedImg.setImageBitmap(afterRepairBitmap);
					Uri imgSaclefileUri = saveBitmap2Saclefile(
							afterRepairBitmap, "afterRepairImage", "afterRepairImage.jpg");// 保存afterRepairBitmap成图片
					uploadAfterRepairedImgPost(imgSaclefileUri);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			break;

		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

	}

	@Override
	protected void onDestroy() {
		if (afterRepairBitmap != null && !afterRepairBitmap.isRecycled()) {
			afterRepairBitmap.recycle();
			afterRepairBitmap = null;
		}
		if(null != mPlayer && mPlayer.isPlaying()){
			mPlayer.stop();
			mPlayer.release();
		}
		
		super.onDestroy();
	}

	/**
	 * 创建Environment.getExternalStorageDirectory().getAbsolutePath() +
	 * "/WeiXiuZhaiJiBian_Shop"下的文件夹
	 * 
	 * @param dir
	 * @param fileName
	 * @return
	 */
	private Uri createTempFile(String dir, String fileName) {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			String tempDirPath = MyApplication.getInstance().getAppCacheFile()
					.getAbsolutePath()
					+ "/" + dir;
			File tempDirFile = new File(tempDirPath);
			if (!tempDirFile.exists()) {
				tempDirFile.mkdir();
			}
			File tempFile = new File(tempDirFile + "/" + fileName);
			Uri tempFileUri = Uri.fromFile(tempFile);
			return tempFileUri;
		} else {
			Toast.makeText(this, "未安裝SDCard", Toast.LENGTH_SHORT).show();
			return null;
		}
	}

	/**
	 * 保存头像压缩图片
	 * 
	 * @param bmp
	 * @param dir
	 * @param type
	 * @return
	 */
	static Uri saveBitmap2Saclefile(Bitmap bmp, String dir,
			String filename) {
		CompressFormat format = Bitmap.CompressFormat.JPEG;
		int quality = 100;
		OutputStream stream = null;
		Uri scaleFileUri = null;
		try {
			String tempDirPath = MyApplication.getInstance().getAppCacheFile()
					.getAbsolutePath()
					+ "/" + dir;
			File scaleDirFile = new File(tempDirPath);
			if (!scaleDirFile.exists()) {
				scaleDirFile.mkdir();
			}
			String imgPath = tempDirPath + "/" + filename;
			stream = new FileOutputStream(imgPath);
			// 这个文件的URI
			File scaleFile = new File(imgPath);
			scaleFileUri = Uri.fromFile(scaleFile);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		bmp.compress(format, quality, stream);

		return scaleFileUri;
	}

	/**师傅维修之后的用户需处确认的图片上传请求
	 * @param uri
	 * @throws Exception
	 */
	private void uploadAfterRepairedImgPost(Uri uri) throws Exception {
		ApiServices.getAppService().uploadImg(
				new TypedFile("image/jpeg", new File(
						GetPathFromUri4kitkat.getPath(this, uri))),
				new BaseCallback<ImagePathResponse>(this) {

					@Override
					public void success(ImagePathResponse data,
							Response response) {
						if (data.getCode().equals("200")) {

							AfterRepairImgPath = data.getData();// 保存服务器返回的头像图片访问位置
							isHadRepairImgPost = true;
						} else {
							Toast.makeText(AcceptedOrderDetailsActivity.this,
									"圖片上傳失敗，請重新選擇圖片", Toast.LENGTH_SHORT)
									.show();
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
	 * 师傅对用户维修需求提交报价请求
	 * 
	 * @throws Exception
	 */
	private void masterConfirmOrderPost() throws Exception {

		String order_ID = o6ServicesTypePrice.getORDER_ID();

		String price = priceET.getText().toString();
		String repairContent = repairContentET.getText().toString();

		ApiServices.getAppService().masterConfirmOrder(
				order_ID,
				price,
				repairContent,
				spinnerContent,
				AfterRepairImgPath,
				new BaseCallback<BaseResponse>(
						AcceptedOrderDetailsActivity.this) {

					@Override
					public void success(BaseResponse data, Response response) {
						// TODO Auto-generated method stub
						if (data.getCode().equals("200")) {
							Toast.makeText(AcceptedOrderDetailsActivity.this,
									data.getMsg(), Toast.LENGTH_SHORT).show();
							AcceptedOrderDetailsActivity.this.finish();
						} else {
							Toast.makeText(AcceptedOrderDetailsActivity.this,
									data.getMsg(), Toast.LENGTH_SHORT).show();
						}
						super.success(data, response);
					}

					@Override
					public void failure(RetrofitError arg0) {
						Toast.makeText(AcceptedOrderDetailsActivity.this,
								"訪問服務器超時，請稍後重試", Toast.LENGTH_SHORT).show();
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
		
		if(isHadRepairImgPost){
			try {
				WaitingProcessDialog.showRoundProcessDialog(AcceptedOrderDetailsActivity.this, "正在提交...");
				masterConfirmOrderPost();// 师傅对用户维修需求提交报价请求
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			Toast.makeText(this, "請先拍下處理好的維修處的圖片", Toast.LENGTH_SHORT).show();
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
