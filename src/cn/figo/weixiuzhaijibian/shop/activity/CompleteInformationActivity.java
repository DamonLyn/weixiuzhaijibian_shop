package cn.figo.weixiuzhaijibian.shop.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedFile;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.util.Log;
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
import android.widget.ImageView.ScaleType;
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
import cn.figo.weixiuzhaijibian.shop.model.Master;
import cn.figo.weixiuzhaijibian.shop.ui.processdialog.WaitingProcessDialog;
import cn.figo.weixiuzhaijibian.shop.utils.CircularCutting;
import cn.figo.weixiuzhaijibian.shop.utils.GetPathFromUri4kitkat;
import cn.figo.weixiuzhaijibian.shop.utils.ImageScale;

import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.Validator.ValidationListener;
import com.mobsandgeeks.saripaar.annotation.Required;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;

/**
 * 完善资料界面
 */
public class CompleteInformationActivity extends Activity implements
		OnClickListener, ValidationListener {
	private MyApplication myApplication;
	private Validator mValidator;// 校验器
	Master master;
	private String[] LicenseImgUrlStrArrary;//使用数组把多张证件图片下载地址存储在对象中
	private int positionFlag;
	
	private PopupWindow mSelectImageWindow;
	private PopupWindow mLicensePictureSettingWindow;
	private static final int TOOK_AVATAR_BY_CAMERA = 0;
	private static final int TOOK_LICENSEPICTURE_BY_CAMERA = 1;
	private static final int TOOK_AVATAR_BY_ALBUM = 2;
	private static final int TOOK_LICENSEPICTURE_BY_ALBUM = 3;
	
	private static final int TO_SELECT_SERVICE = 11;
	private static final int TO_SELECT_AREA = 12;
	private static final int TO_SELECT_LICENSE = 13;
	
	private static final int TO_PREVIEW_PHOTO = 20;//到图片预览界面
	private Uri mImageFileUri;
	private Bitmap avatarBitmap;
	private Bitmap licensePictureBitmap;
	public List<String> drr = new ArrayList<String>();

	private ArrayAdapter<String> adapter = null;
	private String[] howLong = new String[] { "三個月", "六個月", "九個月", "十二個月" };
	private String spinnerContent;// 存储被选中的保修期
	private String name;
	private String phone;
	private String workYear;
	private String intro;

	String headImgpath;//用于存储服务器返回的头像图片路径
	Map<String,String> licenseImgPath;//用于存储服务器返回的执业证书图片路径
	
	
	//校验标识
	private boolean isHadHeadImgPost = false; //图片是否已上传标识
	private boolean isHadLicenseImgPost = false;
	private boolean isHadSelectService = false;
	private boolean isHadSelectArea = false;
	private boolean isHadSelectLicense = false;
	
	// 返回按鈕
	@InjectView(R.id.return_iv)
	ImageView returnIV;

	// 标题文字
	@InjectView(R.id.title_text_tv)
	TextView titleTextTV;

	// 头像图片
	@InjectView(R.id.avatar_iv)
	ImageView avatarIV;
	
	// 用户姓名输入框
	@Required(order = 0 ,message="請輸入用戶姓名")
	@InjectView(R.id.name_et)
	EditText nameET;

	// 电话号码输入框
	@Required(order = 1 ,message="請輸入電話號碼")
	@InjectView(R.id.phone_et)
	EditText phoneET;

	// 工作经验输入框
	@Required(order = 3 ,message="請輸入工作經驗")
	@InjectView(R.id.work_year_et)
	EditText workYearET;

	// 维修项目
	@InjectView(R.id.service_type_ll)
	LinearLayout serviceTypeLL;
	// 服务地区
	@InjectView(R.id.service_area_ll)
	LinearLayout serviceAreaLL;
	// 证书牌照
	@InjectView(R.id.service_license_ll)
	LinearLayout ServiceLicenseLL;

	// 保修期
	@InjectView(R.id.spinner)
	Spinner spinner;

	// 个人简介输入框
	@Required(order = 4 ,message="請輸入個人簡介")
	@InjectView(R.id.intro_et)
	EditText introET;

	// 职业证书上传提示图片
	@InjectView(R.id.preview_iv)
	ImageView previewIV;

	//放置从服务器端下载过来的证书图片的图片布局
	@InjectView(R.id.existed_license_pic_ll)
	LinearLayout existedLicensePicLL;
	
	//放置从拍照或相册选择  返回的操作所生成的图片布局
	@InjectView(R.id.license_pic_ll)
	LinearLayout lineLayout;
	
	
	@InjectView(R.id.commit_btn)
	Button commitBtn;

	@InjectView(R.id.ll_parent)
	LinearLayout parent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_complete_information);
		ButterKnife.inject(this);
		initView();
	}

	private void initView() {
		master = (Master) getIntent().getSerializableExtra("master");
		LicenseImgUrlStrArrary =  (String[]) getIntent().getSerializableExtra("LicenseImgUrlStrArrary");
		myApplication = MyApplication.getInstance();
		mValidator = new Validator(this);
		mValidator.setValidationListener(this);
		titleTextTV.setText("完善資料");
		returnIV.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				CompleteInformationActivity.this.finish();

			}
		});
		//设置界面输入框的初始显示
		if(null != master.getM_NAME()){
			nameET.setText(master.getM_NAME());
		}
		if(null != master.getM_TEL()){
			phoneET.setText(master.getM_TEL());
		}
		
		if(null != master.getM_INTRO()){
			introET.setText(master.getM_INTRO());
		}
		
		if(null != master.getM_WOK_YEAR()){
			workYearET.setText(master.getM_WOK_YEAR().toString());
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
							avatarIV.setImageBitmap(CircularCutting.toRoundBitmap(loadedImage));
							isHadHeadImgPost = true;//需要把isHadHeadImgPost设置为ture;
							super.onLoadingComplete(imageUri, view, loadedImage);
						}});
			headImgpath = master.getM_HEAD_IMG();
		}
		
		
		
		
		
		//设置spinner的初始显示
		
		
		avatarIV.setOnClickListener(this);
		serviceTypeLL.setOnClickListener(this);
		serviceAreaLL.setOnClickListener(this);
		ServiceLicenseLL.setOnClickListener(this);
		previewIV.setOnClickListener(this);
		commitBtn.setOnClickListener(this);
		licenseImgPath = new HashMap<String, String>();
		initSelectImageWindow();// 弹出窗的初始化
		initLicensePictureSettingWindow();
		initSpinner();
		
		downlaodLicenseImg();//初始化已有证件图片下载
	}

	private void initSpinner() {
		adapter = new ArrayAdapter<String>(CompleteInformationActivity.this,
				R.layout.spinner_item, howLong);
		spinner.setAdapter(adapter);
		spinner.setSelection(0, true);
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

	@Override
	public void onClick(View v) {
		int id = v.getId();
		Intent intent;
		switch (id) {
		case R.id.avatar_iv:// 设置头像
			mSelectImageWindow.showAtLocation(parent, Gravity.FILL, 0, 0);// 上传头像设置弹出窗
			break;
		case R.id.service_type_ll:// 跳 选择维修项目 界面
			intent = new Intent();
			intent.setClass(CompleteInformationActivity.this,
					SelectServiceTypeActivity.class);
			startActivityForResult(intent,TO_SELECT_SERVICE);
			break;
		case R.id.service_area_ll:// 跳 选择服务地区 界面
			intent = new Intent();
			intent.setClass(CompleteInformationActivity.this,
					SelectAreaActivity.class);
			startActivityForResult(intent,TO_SELECT_AREA);
			break;
		case R.id.service_license_ll:// 跳 选择证书牌照 界面
			intent = new Intent();
			intent.setClass(CompleteInformationActivity.this,
					SelectLicenseActivity.class);
			startActivityForResult(intent,TO_SELECT_LICENSE);
			break;
		case R.id.preview_iv:// 点击上传职业证书
			mLicensePictureSettingWindow.showAtLocation(parent, Gravity.FILL,
					0, 0);// 上传证书图片设置弹出窗
			break;
		case R.id.commit_btn:// 点击提交审核按钮
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
				camera(TOOK_AVATAR_BY_CAMERA);
				mSelectImageWindow.dismiss();
			}
		});
		album.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 从相册中选择
				Album(TOOK_AVATAR_BY_ALBUM);
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
	 * 点击上传证书图片就会弹出
	 */
	private void initLicensePictureSettingWindow() {
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
				camera(TOOK_LICENSEPICTURE_BY_CAMERA);
				mLicensePictureSettingWindow.dismiss();

			}
		});
		album.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 从相册中选择
				Album(TOOK_LICENSEPICTURE_BY_ALBUM);
				mLicensePictureSettingWindow.dismiss();
			}
		});
		cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 取消
				mLicensePictureSettingWindow.dismiss();
			}
		});
		mLicensePictureSettingWindow = new PopupWindow(view,
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, true);
		mLicensePictureSettingWindow.setBackgroundDrawable(new ColorDrawable());
		mLicensePictureSettingWindow.setTouchable(true);
	}

	/**
	 * 拍照功能
	 * 
	 * @param requestCode
	 *            根据传入的值判断是拍头像照片，还是职业证件照片。
	 */
	private void camera(int requestCode) {
		switch (requestCode) {
		case TOOK_AVATAR_BY_CAMERA:
			mImageFileUri = createTempFile("tempImage", "avatarImage.jpg");// 初始化头像图片保存的路径
			if (mImageFileUri != null) {
				Intent openCameraIntent = new Intent(
						MediaStore.ACTION_IMAGE_CAPTURE);
				openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,
						mImageFileUri);
				startActivityForResult(openCameraIntent, TOOK_AVATAR_BY_CAMERA);
			}
			break;
		case TOOK_LICENSEPICTURE_BY_CAMERA:
//			// 获取系统时间 然后将图片保存至指定的文件夹
			SimpleDateFormat sDateFormat = new SimpleDateFormat(
					"yyyyMMddhhmmss");
			String address = sDateFormat.format(new java.util.Date());

			mImageFileUri = createTempFile("tempImage", address + ".jpg");// 初始化证书图片保存的路径
			if (mImageFileUri != null) {
				Intent openCameraIntent = new Intent(
						MediaStore.ACTION_IMAGE_CAPTURE);
				openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,
						mImageFileUri);
				startActivityForResult(openCameraIntent,
						TOOK_LICENSEPICTURE_BY_CAMERA);
			}
			break;

		default:
			break;
		}

	}

	/**
	 * 相册功能
	 * 
	 * @param requestCode
	 *            根据传入的值判断是选头像图片，还是职业证件图片。
	 */
	private void Album(int requestCode) {
		switch (requestCode) {
		case TOOK_AVATAR_BY_ALBUM:
			Intent openAlbumIntent1 = new Intent(Intent.ACTION_PICK,
					Media.EXTERNAL_CONTENT_URI);
			startActivityForResult(openAlbumIntent1, TOOK_AVATAR_BY_ALBUM);
			break;
		case TOOK_LICENSEPICTURE_BY_ALBUM:
			Intent openAlbumIntent2 = new Intent(Intent.ACTION_PICK,
					Media.EXTERNAL_CONTENT_URI);
			startActivityForResult(openAlbumIntent2,
					TOOK_LICENSEPICTURE_BY_ALBUM);
			break;

		default:
			break;
		}

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

	
	/**获取压缩文件的路径
	 * @param dir
	 * @param fileName
	 * @return
	 */
	private Uri getScaleFile(String dir, String fileName) {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			String scaleDirPath = MyApplication.getInstance().getAppCacheFile()
					.getAbsolutePath()
					+ "/" + dir;
			File scaleDirFile = new File(scaleDirPath);
			
			File scaleFile = new File(scaleDirFile + "/" + fileName);
			Uri scaleFileUri = Uri.fromFile(scaleFile);
			return scaleFileUri;
		} else {
			Toast.makeText(this, "找不到此文件", Toast.LENGTH_SHORT).show();
			return null;
		}
	}
	
	
	
	/**保存职业证书照片
	 * @param bmp
	 * @param dir
	 * @param type
	 * @return
	 */
	static Uri saveLicenseBitmap2Scalefile(Bitmap bmp, String dir,String type) {
		CompressFormat format = Bitmap.CompressFormat.JPEG;
		int quality = 100;
		OutputStream stream = null;
		Uri scaleFileUri = null;
		SimpleDateFormat sDateFormat = new SimpleDateFormat(
				"yyyyMMddhhmmss");
		String filename = sDateFormat.format(new java.util.Date());
		try {
			String tempDirPath = MyApplication.getInstance().getAppCacheFile()
					.getAbsolutePath()
					+ "/" + dir;
			File scaleDirFile = new File(tempDirPath);
			if (!scaleDirFile.exists()) {
				scaleDirFile.mkdir();
			}
			String imgPath = tempDirPath + "/"+ filename+type;
			stream = new FileOutputStream(imgPath);
			//这个文件的URI
			File scaleFile = new File(imgPath);
			scaleFileUri = Uri.fromFile(scaleFile);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		bmp.compress(format, quality, stream);
		return  scaleFileUri;
	}
	
	
	
	/**保存头像压缩图片
	 * @param bmp
	 * @param dir
	 * @param type
	 * @return
	 */
	static Uri saveAvatarBitmap2Saclefile(Bitmap bmp, String dir,String filename) {
		CompressFormat format = Bitmap.CompressFormat.JPEG;
		int quality = 100;
		OutputStream stream = null;
		Uri scaleFileUri  = null;
		try {
			String tempDirPath = MyApplication.getInstance().getAppCacheFile()
					.getAbsolutePath()
					+ "/" + dir;
			File scaleDirFile = new File(tempDirPath);
			if (!scaleDirFile.exists()) {
				scaleDirFile.mkdir();
			}
			String imgPath = tempDirPath + "/"+ filename;
			stream = new FileOutputStream(imgPath);
			//这个文件的URI
			File scaleFile = new File(imgPath);
			scaleFileUri = Uri.fromFile(scaleFile);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		 bmp.compress(format, quality, stream);
			
			
		 return scaleFileUri; 
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case TOOK_AVATAR_BY_CAMERA:// 拍完用户头像照片返回
			if (resultCode == this.RESULT_OK) {

				try {
					String path = GetPathFromUri4kitkat.getPath(this,mImageFileUri);
					avatarBitmap = ImageScale.decodeSampledBitmapFromResource(path);//压缩图片
					Bitmap circularShapeBitmap = CircularCutting
							.toRoundBitmap(avatarBitmap);// 将bitmap对象设置为圆形
					avatarIV.setImageBitmap(circularShapeBitmap);
//					saveAvatarBitmap2file(avatarBitmap, "scaleImage", "avatar.jpg");//保存bitmap成图片
					Uri avatarSaclefileUri = saveAvatarBitmap2Saclefile(avatarBitmap, "scaleImage", "avatar.jpg");//保存bitmap成图片
					WaitingProcessDialog.showRoundProcessDialog(CompleteInformationActivity.this, "正在上傳頭像...");
					uploadHeadImgPost(avatarSaclefileUri);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			break;
		case TOOK_LICENSEPICTURE_BY_CAMERA:// 拍完证件照片照片返回
			if (resultCode == this.RESULT_OK) {
				try {
					
					String path = GetPathFromUri4kitkat.getPath(this,mImageFileUri);
					licensePictureBitmap = ImageScale.decodeSampledBitmapFromResource(path);//压缩图片
					//createLicenseImageView(licensePictureBitmap);
					Uri licenseSaclefileUri = saveLicenseBitmap2Scalefile(licensePictureBitmap, "scaleImage", ".jpg");//保存bitmap成图片
					WaitingProcessDialog.showRoundProcessDialog(CompleteInformationActivity.this, "添加的證件照片上傳中...");
					uploadLicenseImgPost(licenseSaclefileUri, licensePictureBitmap);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			break;
		case TOOK_AVATAR_BY_ALBUM:// 从相册中选择完用户头像照片返回
			if (resultCode == RESULT_OK && data != null) {

				try {
					mImageFileUri = data.getData();
					String path = GetPathFromUri4kitkat.getPath(this,mImageFileUri);
					avatarBitmap = ImageScale.decodeSampledBitmapFromResource(path);//压缩图片
					Bitmap circularShapeBitmap = CircularCutting
							.toRoundBitmap(avatarBitmap);// 将bitmap对象设置为圆形
					avatarIV.setImageBitmap(circularShapeBitmap);
					Uri avatarSaclefileUri = saveAvatarBitmap2Saclefile(avatarBitmap, "scaleImage", "avatar.jpg");//保存bitmap成图片
					WaitingProcessDialog.showRoundProcessDialog(CompleteInformationActivity.this, "正在上傳頭像...");
					uploadHeadImgPost(avatarSaclefileUri);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			break;
		case TOOK_LICENSEPICTURE_BY_ALBUM:// 从相册中选择完职业证书照片返回
			if (resultCode == RESULT_OK && data != null) {

				try {
					mImageFileUri = data.getData();
					String path = GetPathFromUri4kitkat.getPath(this,mImageFileUri);
					licensePictureBitmap = ImageScale.decodeSampledBitmapFromResource(path);//压缩图片
					// 接下来设置动态增加的控件
					//createLicenseImageView(licensePictureBitmap);
					Uri licenseSaclefileUri = saveLicenseBitmap2Scalefile(licensePictureBitmap, "scaleImage", ".jpg");//保存bitmap成图片
					WaitingProcessDialog.showRoundProcessDialog(CompleteInformationActivity.this, "添加的證件照片上傳中...");
					uploadLicenseImgPost(licenseSaclefileUri, licensePictureBitmap);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			break;

			
		case TO_SELECT_SERVICE:	//从 选择 维修项目界面返回
			if(resultCode == RESULT_OK){
				isHadSelectService = true;
			}
			break;
		case TO_SELECT_AREA:	//从 选择地区界面 返回
			if(resultCode == RESULT_OK){
				isHadSelectArea = true;
			}
			break;
		case TO_SELECT_LICENSE:	//从 选择 许可证界面返回
			if(resultCode == RESULT_OK){
				isHadSelectLicense = true;
			}
			break;
		
				
		case TO_PREVIEW_PHOTO:	//从 预览图片界面返回
			if(resultCode == RESULT_OK){
				//existedLicensePicLL.removeView(existedLicensePicLL.getChildAt(positionFlag));
				if(null != data){
					Bundle bundle = data.getExtras();
					if(null != bundle){
						String imgUrl = bundle.getString("imgUrl");
						 existedLicensePicLL.removeView(existedLicensePicLL.findViewWithTag(imgUrl));
					}
				}
			}
			break;	
			
		default:
			break;
		}
	}

	
	
	@Override
	protected void onDestroy() {
		if (avatarBitmap != null && !avatarBitmap.isRecycled()) {
			avatarBitmap.recycle();
			avatarBitmap = null;
		}
		if (licensePictureBitmap != null && !licensePictureBitmap.isRecycled()) {
			licensePictureBitmap.recycle();
			licensePictureBitmap = null;
		}

		super.onDestroy();
	}

	/**
	 * 师傅修改个人信息请求
	 * 
	 * @throws Exception
	 */
	private void updateMasterInfoPost() throws Exception {
		name = nameET.getText().toString();//姓名
		phone = phoneET.getText().toString();//电话
		workYear = workYearET.getText().toString();//工作经验
		intro = introET.getText().toString();//个人简介
/*	由于更新了单独上传证件照片的接口，这段代码就不需要了	
 * StringBuilder allLicenseImgPath = new StringBuilder();//存储所有服务器返回的执业证书图片路径，以逗号分隔
		int i = 0;
		for (String path : licenseImgPath.values()) {
			if(i == 0){
				allLicenseImgPath.append(path);
			}else{
				allLicenseImgPath.append(",");
				allLicenseImgPath.append(path);
			}
			i++;
		}
		String allLicenseAddress = allLicenseImgPath.toString();*/
		ApiServices.getAppService()
				.updateMasterInfo(
						myApplication.loginUid,
						headImgpath,
						name,
						phone,
						workYear,
						intro,
						"",
						new BaseCallback<BaseResponse>(
								CompleteInformationActivity.this) {

							@Override
							public void success(BaseResponse data,
									Response response) {
								// TODO Auto-generated method stub
								if (data.getCode().equals("200")) {
									Toast.makeText(
											CompleteInformationActivity.this,
											data.getMsg(), Toast.LENGTH_SHORT)
											.show();
//									myApplication.setLoginName(name);
									//刷新应用缓存
									Master master = new Master();
									master.setM_NAME(name);//本次网络操作改动的部分
									master.setM_TEL(phone);//本次网络操作改动的部分
									master.setM_INTRO(intro);//本次网络操作改动的部分
									master.setM_LOGIN_MAIL(myApplication.getLoginMail());
									master.setMASTER_ID(myApplication.getLoginUid());
									master.setM_HEAD_IMG(myApplication.getLoginHeadImgPath());
									myApplication.saveUserInfo(master);//重新保存到缓存文件中
									CompleteInformationActivity.this.finish();

								} else {
									Toast.makeText(
											CompleteInformationActivity.this,
											data.getMsg(), Toast.LENGTH_SHORT)
											.show();
								}
								super.success(data, response);
							}

							@Override
							public void failure(RetrofitError arg0) {
								Toast.makeText(
										CompleteInformationActivity.this,
										"訪問服務器超時，請稍後重試", Toast.LENGTH_SHORT)
										.show();
								super.failure(arg0);
							}

						});
	}

	/**
	 * 师傅上传头像图片网络请求
	 * 
	 * @throws Exception
	 */
	private void uploadHeadImgPost(Uri uri) throws Exception {
		ApiServices.getAppService().uploadImg(
				new TypedFile("image/jpeg", new File(
						GetPathFromUri4kitkat.getPath(this, uri))),
				new BaseCallback<ImagePathResponse>(this) {

					@Override
					public void success(ImagePathResponse data, Response response) {
						if(data.getCode().equals("200")){
							
							headImgpath = data.getData();//保存服务器返回的头像图片访问位置
							isHadHeadImgPost = true;
						}else{
							Toast.makeText(CompleteInformationActivity.this,
									"頭像圖片上傳失敗，請重新選擇圖片", Toast.LENGTH_SHORT).show();
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
	 * 师傅上传证书图片网络请求
	 * 
	 * @throws Exception
	 */
	private void uploadLicenseImgPost(Uri uri, final Bitmap bitmap) throws Exception {
		ApiServices.getAppService().uploadImg(
				new TypedFile("image/jpeg", new File(
						GetPathFromUri4kitkat.getPath(this, uri))),
						new BaseCallback<ImagePathResponse>(this) {
					
					@Override
					public void success(ImagePathResponse data, Response response) {
						if(data.getCode().equals("200")){
							if(data.getData() != null){
								//保存服务器返回的头像图片访问位置
								licenseImgPath.put(data.getData(), data.getData());

								try {
									addMasterLicensePost(data.getData());
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								isHadLicenseImgPost = true;
								
								//king
								createLicenseImageView(bitmap, data.getData());
							}
							
						}else{
							Toast.makeText(CompleteInformationActivity.this,
									"頭像圖片上傳失敗，請重新選擇圖片", Toast.LENGTH_SHORT).show();
						}
						
						
						super.success(data, response);
					}
					
					@Override
					public void failure(RetrofitError arg0) {
						super.failure(arg0);
					}
					
				});
		
	}
	
	
	/**师傅添加一個执照照片请求
	 * @param licenseImgPath
	 * @throws Exception
	 */
	private void addMasterLicensePost(String licenseImgPath) throws Exception {
		String mID = myApplication.getInstance().getLoginUid();
		ApiServices.getAppService().addMasterLicense(mID,licenseImgPath,
						new BaseCallback<BaseResponse>(this) {
					
					@Override
					public void success(BaseResponse data, Response response) {
						if(data.getCode().equals("200")){
							Log.d("test", "msg--->>"+data.getMsg());
						}else{
							Toast.makeText(CompleteInformationActivity.this,
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

	
	
	/**
	 * 下载已经师傅用户上传过的证书照片
	 */
	private void downlaodLicenseImg(){
		String LicenseImgPath = new String("");
		if(null != LicenseImgUrlStrArrary){
			int length = LicenseImgUrlStrArrary.length;
//			final int baseCount = 200;//控件的id基数
			for (int i = 0; i<length;i++) {
				LicenseImgPath = LicenseImgUrlStrArrary[i];
//				final int increseCount = i;//increseCount 是使控件的id值动态增加的部分，最终控件的id值为baseCount + increseCount 的和
				final int imgPosition = i;//每一张图片所放置的ImageView的id值
				ImageLoader.getInstance().loadImage(Constants.server + LicenseImgPath, 
						new SimpleImageLoadingListener(){

							@Override
							public void onLoadingComplete(String imageUri,
									View view, Bitmap loadedImage) {
								// TODO Auto-generated method stub
								super.onLoadingComplete(imageUri, view, loadedImage);
								createFromUrlLicenseImageView(loadedImage,imgPosition);
							}
								
				});
				
			}
		}

		
	}
	
	
	
	
	
	/**创建师傅用户已经上传过到服务器中的职业证书照片视图
	 * @param bitmap
	 */
	private void createFromUrlLicenseImageView(Bitmap bitmap,final int postion) {
		
		ImageView iV = new ImageView(this);
		float density = getResources().getDisplayMetrics().density;
		LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,
				(int) (150 * density));
		iV.setLayoutParams(layoutParams);
		iV.setScaleType(ScaleType.FIT_XY);
		iV.setImageBitmap(bitmap);
		iV.setId(postion);
		iV.setTag(LicenseImgUrlStrArrary[postion]);
		positionFlag = postion;
		//设置监听器
		iV.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//String imgUrl = LicenseImgUrlStrArrary[postion];
				String imgUrl = null == v.getTag() ? "" :v.getTag().toString(); 
				Intent intent = new Intent();
				intent.putExtra("imgUrl", imgUrl);
				intent.setClass(CompleteInformationActivity.this, PreviewPhotoActivity.class);
				startActivityForResult(intent, TO_PREVIEW_PHOTO);
			}
		});
		existedLicensePicLL.addView(iV);
	}
	
	/**创建职业证书照片视图
	 * @param bitmap
	 */
	private void createLicenseImageView(Bitmap bitmap, String imgUrl) {

		ImageView iV = new ImageView(this);
		float density = getResources().getDisplayMetrics().density;
		LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,
				(int) (150 * density));
		iV.setLayoutParams(layoutParams);
		iV.setScaleType(ScaleType.FIT_XY);
		iV.setImageBitmap(bitmap);
		
		iV.setTag(imgUrl);
		
		iV.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(CompleteInformationActivity.this, "你点击了新上传的图片", 0).show();
				String imgUrl = null == v.getTag() ? "" :v.getTag().toString(); 
				Intent intent = new Intent();
				intent.putExtra("imgUrl", imgUrl);
				intent.setClass(CompleteInformationActivity.this, PreviewPhotoActivity.class);
				startActivityForResult(intent, TO_PREVIEW_PHOTO);
			}
		});
		
		existedLicensePicLL.addView(iV);
		
		//lineLayout.addView(iV);

	}

	/*校验器的校验功能代码***********************/
	
	@Override
	public void preValidation() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSuccess() {
		//完成简单的输入框判空操作，还需要对其余非输入框控件进行逐项校验
		
		if(!isHadSelectService){
			Toast.makeText(CompleteInformationActivity.this,
					"請選擇服務項目", Toast.LENGTH_SHORT).show();
			return;
		}
		if(!isHadSelectArea){
			Toast.makeText(CompleteInformationActivity.this,
					"請選擇服務地區", Toast.LENGTH_SHORT).show();
			return;
		}
		if(!isHadSelectLicense){
			Toast.makeText(CompleteInformationActivity.this,
					"請選擇認可執照", Toast.LENGTH_SHORT).show();
			return;
		}
		
		
		
		if(isHadHeadImgPost){
			if(isHadLicenseImgPost){
				try {
					updateMasterInfoPost();//师傅提交个人信息请求
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				Toast.makeText(CompleteInformationActivity.this,
						"請上傳職業證件圖片", Toast.LENGTH_SHORT).show();
			}
		}else{
			Toast.makeText(CompleteInformationActivity.this,
					"請上傳頭像圖片", Toast.LENGTH_SHORT).show();
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
