package cn.figo.weixiuzhaijibian.shop.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import cn.figo.weixiuzhaijibian.shop.activity.BrowserActivity;
import cn.figo.weixiuzhaijibian.shop.api.Constants;
import cn.figo.weixiuzhaijibian.shop.model.Advertise;

import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * 首页界面的广告页适配器
 */
public class ViewPagerAdapter extends PagerAdapter implements OnClickListener {
	private List<Advertise> mList;
	private Context mContext;

	public ViewPagerAdapter(Context context, List<Advertise> list) {
		this.mList = list;
		this.mContext = context;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		ImageView iv = new ImageView(mContext);
		iv.setId(position);
		iv.setOnClickListener(this);
		iv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		ImageLoader.getInstance().displayImage(Constants.server + mList.get(position).getAD_IMG(),
				iv);
		((ViewPager) container).addView(iv);
		return iv;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		((ViewPager) container).removeView((View) object);
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (mList.get(id).getAD_TYPE() == 1) {
			Intent intent = new Intent(mContext, BrowserActivity.class);
			intent.putExtra("url", mList.get(id).getAD_JUMP_TARGET());
			mContext.startActivity(intent);
		}
	}

}
