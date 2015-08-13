package cn.figo.weixiuzhaijibian.shop.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;

public class ImageScale {

	/**
	 * 获取压缩后的图片
	 * 
	 * @param res
	 * @param resId
	 * @param reqWidth
	 *            所需图片压缩尺寸最小宽度
	 * @param reqHeight
	 *            所需图片压缩尺寸最小高度
	 * @return
	 */

	public static Bitmap decodeSampledBitmapFromResource(String path) {

		if (path == null || path == "") {
			// 文件不存在
			return null;
		}
		File file = new File(path);
		int scale = 1;
		System.out.println("--->" + file.length() / 1024);
		if (file.exists()
				&& (file.getName().indexOf(".jpg") > 0
						|| file.getName().indexOf(".JPG") > 0
						|| file.getName().indexOf(".png") > 0
						|| file.getName().indexOf(".PNG") > 0
						|| file.getName().indexOf(".JPEG") > 0 || file
						.getName().indexOf(".jpeg") > 0)) {
			if (file.length() / 1024 < 200) {
				return BitmapFactory.decodeFile(path);
			} else {
				scale = (int) ((file.length() / 1024) / 200);
			}
		} else {
			return null;
		}
		// 首先不加载图片,仅获取图片尺寸
		final BitmapFactory.Options options = new BitmapFactory.Options();
		// 当inJustDecodeBounds设为true时,不会加载图片仅获取图片尺寸信息
		options.inJustDecodeBounds = true;
		// 此时仅会将图片信息会保存至options对象内,decode方法不会返回bitmap对象
		BitmapFactory.decodeFile(path, options);
		// 计算压缩比例,如inSampleSize=4时,图片会压缩成原图的1/4
		options.inSampleSize = scale;

		// 当inJustDecodeBounds设为false时,BitmapFactory.decode...就会返回图片对象了
		options.inJustDecodeBounds = false;
		// 利用计算的比例值获取压缩后的图片对象
		return BitmapFactory.decodeFile(path, options);
	}
}
