package cn.figo.weixiuzhaijibian.shop.activity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedFile;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.figo.weixiuzhaijibian.shop.R;
import cn.figo.weixiuzhaijibian.shop.api.ApiServices;
import cn.figo.weixiuzhaijibian.shop.api.Constants;
import cn.figo.weixiuzhaijibian.shop.app.MyApplication;
import cn.figo.weixiuzhaijibian.shop.callback.BaseCallback;
import cn.figo.weixiuzhaijibian.shop.fragment.InputOldPasswordDialogfragment;
import cn.figo.weixiuzhaijibian.shop.model.BaseResponse;
import cn.figo.weixiuzhaijibian.shop.model.ImagePathResponse;
import cn.figo.weixiuzhaijibian.shop.model.Master;
import cn.figo.weixiuzhaijibian.shop.ui.processdialog.WaitingProcessDialog;
import cn.figo.weixiuzhaijibian.shop.utils.CircularCutting;
import cn.figo.weixiuzhaijibian.shop.utils.GetPathFromUri4kitkat;
import cn.figo.weixiuzhaijibian.shop.utils.ImageScale;

/**
 * 账户与安全界面
 * 
 */
public class AccountAndSecurityActivity extends FragmentActivity implements OnClickListener {
		private MyApplication myApplication;
	
		//与图片设置有关的代码
		View parentView; 
		Bitmap bitmap;
		private Bitmap avatarBitmap;
		private static final int TO_MODIFYNAME = 11;
		private static final int TOOK_AVATAR_BY_CAMERA = 0;
		private static final int TOOK_AVATAR_BY_ALBUM = 2;
		private Uri mImageFileUri;
		
		private String name;
		
		private String headImgUpdatePath;
		// 返回按鈕
		@InjectView(R.id.return_iv)
		ImageView returnIV;
		// 界面标题文字
		@InjectView(R.id.title_text_tv)
		TextView titleTextTV;
		
		
		
		// 姓名文字
		@InjectView(R.id.name_tv)
		TextView nameTV;
		
		// 邮箱文字
		@InjectView(R.id.email_tv)
		TextView emailTV;
		

		
		//修改姓名
		@InjectView(R.id.modify_name_ll)
		LinearLayout modifyNameLL;
		
		//修改密码
		@InjectView(R.id.modify_password_ll)
		LinearLayout modifyPasswordLL;
		
		//用户头像
		@InjectView(R.id.account_avatar_iv)
		ImageView AccountAvatarIV;
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			parentView = LayoutInflater.from(this).inflate(R.layout.activity_account_and_security, null);
			setContentView(parentView);
			ButterKnife.inject(this);
			initView();
		}

		private void initView() {
			myApplication = MyApplication.getInstance();
			if (myApplication.isLogin()) {// 状态为已登录
				//姓名的文字设置
				if (!myApplication.getInstance().getLoginName().equals("null")
						&& null != myApplication.getInstance().getLoginName()) {
					nameTV.setText(myApplication.getLoginUser().getM_NAME());
					name = nameTV.getText().toString();
				}else{
					nameTV.setText("");
				}
				
				//邮箱的文字设置
				if (!myApplication.getInstance().getLoginMail().equals("null")
						&& null != myApplication.getInstance().getLoginMail()) {
					emailTV.setText(myApplication.getLoginUser().getM_LOGIN_MAIL());
				}else{
					emailTV.setText("");
				}
				

			} else {// 状态为未登录
				nameTV.setText("");
				emailTV.setText("");
			}
			
			
			titleTextTV.setText("帳戶與安全");
			returnIV.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					AccountAndSecurityActivity.this.finish();
					
				}
			});
			modifyNameLL.setOnClickListener(this);
			modifyPasswordLL.setOnClickListener(this);
			AccountAvatarIV.setOnClickListener(this);
			mImageFileUri = createTempFile("tempImage", "avatarImage.jpg");//初始化头像图片保存的路径
			
			
			if(null != myApplication.getLoginUser().getM_HEAD_IMG()|| !myApplication.getLoginUser().getM_HEAD_IMG().equals("null")){
			String headImgPath = Constants.server+myApplication.getLoginUser().getM_HEAD_IMG();
				ImageLoader.getInstance().loadImage(headImgPath,
						new SimpleImageLoadingListener(){

							@Override
							public void onLoadingComplete(
									String imageUri,
									View view,
									Bitmap loadedImage) {
								//把下载下来的bitmap显示为圆形
								AccountAvatarIV.setImageBitmap(CircularCutting.toRoundBitmap(loadedImage));
								super.onLoadingComplete(imageUri, view, loadedImage);
							}});
			}
			
		}

		
	
		
		
		@Override
		public void onClick(View v) {
			int id = v.getId();
			switch (id) {
			case R.id.modify_name_ll://修改姓名
				Intent intent1 = new Intent();
				intent1.setClass(AccountAndSecurityActivity.this, ModifyNameActivity.class);
				intent1.putExtra("name", name);
				startActivityForResult(intent1,TO_MODIFYNAME);
				break;
				
			case R.id.modify_password_ll://修改密码
				InputOldPasswordDialogfragment inputOldPasswordDialog = new InputOldPasswordDialogfragment();
				inputOldPasswordDialog.show(getSupportFragmentManager(),null);
				inputOldPasswordDialog.setCancelClickListener(
						new InputOldPasswordDialogfragment.onInputOldPasswordDialogClickListener() {
					
					@Override
					public void onClick(InputOldPasswordDialogfragment dialogfragment) {
						dialogfragment.dismiss();
					}
				});
				inputOldPasswordDialog.setConfirmClickListener(
						new InputOldPasswordDialogfragment.onInputOldPasswordDialogClickListener() {
					
					@Override
					public void onClick(InputOldPasswordDialogfragment dialogfragment) {
						
						dialogfragment.dismiss();
					}
				});
				break;
				
			case R.id.account_avatar_iv://设置头像
				String sdcardState = Environment.getExternalStorageState();
				if (Environment.MEDIA_MOUNTED.equals(sdcardState)) {
					new IamgeSettingPopupWindow(AccountAndSecurityActivity.this, parentView);
				} else {
					Toast.makeText(this.getApplicationContext(), "未安裝SDCard",
							Toast.LENGTH_SHORT).show();
				}
				
				break;
				
				

			default:
				break;
			}
		}
		
		
		
		/**
		 * 图片设置弹窗
		 *
		 */
		public class IamgeSettingPopupWindow extends PopupWindow {

			public IamgeSettingPopupWindow(Context mContext, View parent) {

				View view = View
						.inflate(mContext, R.layout.ppw_image_setting, null);
				view.startAnimation(AnimationUtils.loadAnimation(mContext,
						R.anim.fade_ins));
				LinearLayout ll_popup = (LinearLayout) view
						.findViewById(R.id.ll_popup);

				setWidth(LayoutParams.FILL_PARENT);
				setHeight(LayoutParams.FILL_PARENT);
				setBackgroundDrawable(new BitmapDrawable());
				setFocusable(true);
				setOutsideTouchable(true);
				setContentView(view);
				showAtLocation(parent, Gravity.BOTTOM, 0, 0);
				update();

				Button bt1 = (Button) view.findViewById(R.id.item_popupwindows_camera);
				Button bt2 = (Button) view.findViewById(R.id.item_popupwindows_Photo);
				Button bt3 = (Button) view.findViewById(R.id.item_popupwindows_cancel);
				bt1.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						camera();//跳 系统照相 功能界面
						dismiss();
					}
				});
				bt2.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						Album();//跳 系统相册 界面
						dismiss();
					}
				});
				bt3.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						dismiss();
					}
				});

			}
		}

		
		/**
		 * 拍照功能
		 * 
		 */
		private void camera() {
				mImageFileUri = createTempFile("tempImage", "avatarImage.jpg");// 初始化头像图片保存的路径
				if (mImageFileUri != null) {
					Intent openCameraIntent = new Intent(
							MediaStore.ACTION_IMAGE_CAPTURE);
					openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,
							mImageFileUri);
					startActivityForResult(openCameraIntent, TOOK_AVATAR_BY_CAMERA);
				}
		}

		/**
		 * 相册功能
		 * 
		 */
		private void Album() {
				Intent openAlbumIntent1 = new Intent(Intent.ACTION_PICK,
						Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(openAlbumIntent1, TOOK_AVATAR_BY_ALBUM);

		}
		
		
		
		public void onActivityResult(int requestCode, int resultCode, Intent data) {
			switch (requestCode) {
			case TOOK_AVATAR_BY_CAMERA:// 拍完用户头像照片返回
				if (resultCode == this.RESULT_OK) {

					try {
						String path = GetPathFromUri4kitkat.getPath(this,mImageFileUri);
						avatarBitmap = ImageScale.decodeSampledBitmapFromResource(path);//压缩图片
						Bitmap circularShapeBitmap = CircularCutting
								.toRoundBitmap(avatarBitmap);// 将bitmap对象设置为圆形
						AccountAvatarIV.setImageBitmap(circularShapeBitmap);
						Uri avatarSaclefileUri = saveAvatarBitmap2Saclefile(avatarBitmap, "scaleImage", "avatar.jpg");//保存bitmap成图片
						WaitingProcessDialog.showRoundProcessDialog(AccountAndSecurityActivity.this, "正在上傳頭像...");
						uploadHeadImgPost(avatarSaclefileUri);
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
						AccountAvatarIV.setImageBitmap(circularShapeBitmap);
						Uri avatarSaclefileUri = saveAvatarBitmap2Saclefile(avatarBitmap, "scaleImage", "avatar.jpg");//保存bitmap成图片
						WaitingProcessDialog.showRoundProcessDialog(AccountAndSecurityActivity.this, "正在上傳頭像...");
						uploadHeadImgPost(avatarSaclefileUri);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				break;
				
			case TO_MODIFYNAME:
				nameTV.setText(myApplication.getLoginName());
				name = nameTV.getText().toString();
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
			if (bitmap != null && !bitmap.isRecycled()) {
				bitmap.recycle();
				bitmap = null;
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
		
		
		
		
		/**
		 * 师傅上传新头像图片网络请求
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
								
								
								headImgUpdatePath = data.getData();//存储服务器返回的更新头像操作后，头像图片存放的位置
								try {
									updateMasterHeadImgPost();
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							}else{
								Toast.makeText(AccountAndSecurityActivity.this,
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
		
		
		
		/**師傅修改頭像网络请求
		 * @throws Exception
		 */
		private void updateMasterHeadImgPost() throws Exception {
			ApiServices.getAppService().updateMasterHeadImg(
					myApplication.getLoginUser().getMASTER_ID(),
					headImgUpdatePath,
					new BaseCallback<BaseResponse>(this) {

						@Override
						public void success(BaseResponse data, Response response) {
							if(data.getCode().equals("200")){
								
								//刷新应用缓存
								Master master = new Master();
								master.setM_HEAD_IMG(headImgUpdatePath);//本次网络操作改动的部分
								master.setM_NAME(myApplication.getLoginName());
								master.setM_LOGIN_MAIL(myApplication.getLoginMail());
								master.setMASTER_ID(myApplication.getLoginUid());
								master.setM_TEL(myApplication.getLoginPhone());
								master.setM_INTRO(myApplication.getLoginIntroduction());
								myApplication.saveUserInfo(master);//重新保存到缓存文件中
								
								
								
							}else{
								Toast.makeText(AccountAndSecurityActivity.this,
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
