package cn.figo.weixiuzhaijibian.shop.api;

import java.io.File;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import android.os.Environment;


/**
 * 应用配置类（存储服务器地址指向和下载的指定路径）
 */
public class Configs {
	
	public static final String BASE_URL = "APIService.restBaseUrl";

	private static final String BUNDLE_NAME = "cn.figo.weixiuzhaijibian.shop.api.configs";

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	// 默认存放apk文件下载的路径
    public final static String DEFAULT_SAVE_FILE_PATH = Environment
            .getExternalStorageDirectory()
            + File.separator
            + "WXZJB_SHOP"
            + File.separator + "download" + File.separator;
	
	
	
	private Configs() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
