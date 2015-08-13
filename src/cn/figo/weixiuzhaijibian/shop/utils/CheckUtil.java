package cn.figo.weixiuzhaijibian.shop.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 电话格式、手机号码格式 、网络连接情况的检查工具类
 * 
 * 
 */
public class CheckUtil {

	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern
				.compile("^((13[0-9])|(15[^4,\\D])|(18[0,1-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();

	}

	public static boolean isPhoneNO(String phone) {
		// Pattern p = Pattern.compile("^\\d{3}-\\d{8}|\\d{4}-\\d{7}$");
		// //支持格式为010-12345678
		Pattern p = Pattern.compile("^\\d{3}\\d{8}|\\d{4}\\d{7}$"); // 支持格式为01012345678
		Matcher m = p.matcher(phone);
		return m.matches();
	}

	public static boolean isMail(String mail) {
		Pattern p = Pattern
				.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
		Matcher m = p.matcher(mail);
		return m.matches();

	}

	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (cm == null) {
		} else {
			NetworkInfo[] info = cm.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
