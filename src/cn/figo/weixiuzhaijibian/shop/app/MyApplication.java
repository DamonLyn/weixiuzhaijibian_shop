package cn.figo.weixiuzhaijibian.shop.app;

import java.io.File;
import java.util.Properties;

import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Environment;
import android.view.KeyEvent;
import cn.figo.weixiuzhaijibian.shop.R;
import cn.figo.weixiuzhaijibian.shop.model.Master;
import cn.jpush.android.api.JPushInterface;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * 全局应用程序类：用于保存和调用全局应用配置及访问网络数据
 * 
 */
public class MyApplication extends Application {

	// this
	private static MyApplication sInstance;
	public boolean login = false; // 登录状态
	public String loginUid = ""; // 登录用户的id
	public String loginMail = "";
	public String loginName ="";
	public String loginHeadImgPath ="";//需注意，此路径只是图片存储的部分路径，需要与Constant类中的server常量进行字符串拼接才可得到完整的图片存储路径
	public String loginPhone ="";
	public String loginIntroduction ="";
	
	private File mAppCacheFile;
	

	
	@Override
	public void onCreate() {
		super.onCreate();
		sInstance = this;
		// ApiServices.init();
		
		initLogin();//启动应用则初始化用户的配置信息
		initAppCacheFilePath();//初始化缓存文件夹
		
		initImageLoader(getApplicationContext());// 初始化图片加载功能
		JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
		JPushInterface.init(this);     		// 初始化 JPush
	}

	/**
	 * 初始化缓存文件夹
	 */
	private void initAppCacheFilePath() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			String fildPath = Environment.getExternalStorageDirectory()
					.getAbsolutePath() + "/WeiXiuZhaiJiBian_Shop";
			mAppCacheFile = new File(fildPath);
			if (!mAppCacheFile.exists()) {
				mAppCacheFile.mkdir();
			}
		}
	}
	
	
	
	
	/**
	 * 初始化图片加载功能
	 * 
	 * @param context
	 */
	private void initImageLoader(Context context) {
		/*
		 * 图片异步加载的配置初始化
		 */
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
				.cacheInMemory().cacheOnDisc().build();
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context).defaultDisplayImageOptions(defaultOptions)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO).build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}

	// --------------------getters & setters------------------------------

	public static MyApplication getInstance() {
		return sInstance;
	}

	public String getLoginUid() {
		return loginUid;
	}

	public String getLoginMail() {
		return loginMail;
	}

	public String getLoginName() {
		return loginName;
	}
	
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	public String getLoginHeadImgPath() {
		return loginHeadImgPath;
	}
	

	public String getLoginPhone() {
		return loginPhone;
	}

	public void setLoginPhone(String loginPhone) {
		this.loginPhone = loginPhone;
	}

	public String getLoginIntroduction() {
		return loginIntroduction;
	}

	public void setLoginIntroduction(String loginIntroduction) {
		this.loginIntroduction = loginIntroduction;
	}

	public void setLogin(boolean login) {
		this.login = login;
	}

	public void setLoginUid(String loginUid) {
		this.loginUid = loginUid;
	}

	public void setLoginMail(String loginMail) {
		this.loginMail = loginMail;
	}

	public void setLoginHeadImgPath(String loginHeadImgPath) {
		this.loginHeadImgPath = loginHeadImgPath;
	}

	public File getAppCacheFile() {
		return mAppCacheFile;
	}

	public boolean isLogin() {
		return login;
	}
	

	public void setProperties(Properties ps) {
		AppConfig.getAppConfig(this).set(ps);
	}

	public Properties getProperties() {
		return AppConfig.getAppConfig(this).get();
	}

	public void setProperty(String key, String value) {
		AppConfig.getAppConfig(this).set(key, value);
	}

	/**
	 * 获取cookie时传AppConfig.CONF_COOKIE
	 */
	public String getProperty(String key) {
		String res = AppConfig.getAppConfig(this).get(key);
		return res;
	}

	public void removeProperty(String... key) {
		AppConfig.getAppConfig(this).remove(key);
	}

	/**
	 * 保存用户信息
	 */
	public void saveUserInfo(final Master loginUser) {
		this.loginUid = loginUser.getMASTER_ID();
		this.loginMail = loginUser.getM_LOGIN_MAIL();
		this.loginName = loginUser.getM_NAME();
		this.loginHeadImgPath = loginUser.getM_HEAD_IMG();
		this.loginPhone= loginUser.getM_TEL();
		this.loginIntroduction = loginUser.getM_INTRO();
		
		this.login = true;
		setProperties(new Properties() {
			{
				setProperty("user.uid",judge(loginUser.getMASTER_ID()));//用户ID
				setProperty("user.mail", judge(loginUser.getM_LOGIN_MAIL()));//用户邮箱号
				setProperty("user.name", judge(loginUser.getM_NAME()));//用户姓名
				setProperty("user.HeadImgPath", judge(loginUser.getM_HEAD_IMG()));//用户头像地址
				setProperty("user.phone", judge(loginUser.getM_TEL()));//用户手机号
				setProperty("user.introdction", judge(loginUser.getM_INTRO()));//用户个人简介
				
//				setProperty(
//						"user.pwd",
//						CyptoUtils.encode("weixiuzhaijibian",
//								loginUser.getM_PWD()));// 加密存储在缓存中的密码

			}
		});
	}

	/**
	 * 初始化登录的用户信息
	 */
	public void initLogin() {
		Master loginUser = getLoginUser();
		if (null != loginUser && !loginUser.getMASTER_ID().equals("null")&&	loginUser.getMASTER_ID()!= null) {
			login = true;
			loginUid = loginUser.getMASTER_ID();
			loginMail = loginUser.getM_LOGIN_MAIL();
			loginName = loginUser.getM_NAME();
			loginHeadImgPath = loginUser.getM_HEAD_IMG();
			loginPhone = loginUser.getM_TEL();
			loginIntroduction = loginUser.getM_INTRO();
		} else {
			this.cleanLoginInfo();
		}
	}

	/**
	 * 从Property中获得登录用户的信息
	 */
	public Master getLoginUser() {
		Master loginUser = new Master();
		
		loginUser.setMASTER_ID(judge(getProperty("user.uid")));
		
		loginUser.setM_LOGIN_MAIL(judge(getProperty("user.mail"))); 

		loginUser.setM_NAME(judge(getProperty( "user.name")));
		
		loginUser.setM_HEAD_IMG( judge(getProperty("user.HeadImgPath")));
		
		loginUser.setM_TEL( judge(getProperty("user.phone")));
		
		loginUser.setM_INTRO( judge(getProperty("user.introdction")));
		
		
//		loginUser.setM_PWD(CyptoUtils.decode("weixiuzhaijibian", 
//					getProperty( "user.pwd")));// 解密存储在缓存中的密码
		
		return loginUser;
	}

	/**
	 * 清除登录信息
	 */
	public void cleanLoginInfo() {
		this.loginUid = "";
		this.login = false;
		removeProperty("user.uid", "user.mail","user.name","user.HeadImgPath","user.phone","user.introdction");
	}

	
	/**
     * 用户注销
     */
    public void Logout() {
        cleanLoginInfo();
        this.cleanCookie();
        this.login = false;
        this.loginUid = "null";
    }

    /**
     * 清除保存的缓存
     */
    public void cleanCookie() {
        removeProperty(AppConfig.CONF_COOKIE);
    }
	
    
    
    
    /**自定义判空工具类
     * key为null时，返回一个字符串 "null"
     * key不为null，返回它的本身
     */
    private String judge(String key){
    		return String.valueOf(key);
    }
    
    
    public static PackageInfo getPackageVersionInfo(Context context) throws NameNotFoundException{
    	// 获取packagemanager的实例
		PackageManager packageManager = context.getPackageManager();
		// getPackageName()是你当前类的包名，0代表是获取版本信息
		PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(),
					0);
		return packInfo;
    }
    
    
    
    
    

}
