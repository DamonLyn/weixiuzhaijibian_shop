package cn.figo.weixiuzhaijibian.shop.adapter;

import java.util.List;

import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.ButterKnife;
import butterknife.InjectView;

import cn.figo.weixiuzhaijibian.shop.R;
import cn.figo.weixiuzhaijibian.shop.api.Constants;
import cn.figo.weixiuzhaijibian.shop.model.Order;
import cn.figo.weixiuzhaijibian.shop.model.Order6ServiceType;
import cn.figo.weixiuzhaijibian.shop.model.RepairBean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 *  待接订单界面列表适配器
 */
public class PendingOrderAdapter extends BaseAdapter {
	private Context mContext;
	private List<Order6ServiceType> mList;
	
	
	
	public PendingOrderAdapter(Context mContext, List<Order6ServiceType> mList) {
		super();
		this.mContext = mContext;
		this.mList = mList;
	}
	
	public void clear(){
		mList.clear();
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if(convertView == null){
			convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_pending_order, null);
			viewHolder = new ViewHolder(convertView);
			convertView.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		if(null != mList.get(position).getServiceType()){
			String st_IMG_HEIGHT = mList.get(position).getServiceType().getST_IMG_HEIGHT();
			if(st_IMG_HEIGHT != null){
				ImageLoader.getInstance().displayImage(Constants.server + st_IMG_HEIGHT, viewHolder.imgIV);
			}
		}
		
		viewHolder.nameTV.setText(mList.get(position).getST_NAME());
		viewHolder.contentTV.setText(mList.get(position).getO_REPAIR_CONTENT());
		viewHolder.areaTV.setText(mList.get(position).getAREA_NAME());
		
		return convertView;
	}
	
	
	
	public class ViewHolder {

		@InjectView(R.id.repair_type_iv)
		ImageView imgIV;

		@InjectView(R.id.name_tv)
		TextView nameTV;

		@InjectView(R.id.content_tv)
		TextView contentTV;

		@InjectView(R.id.area_tv)
		TextView areaTV;

		public ViewHolder(View view) {
			ButterKnife.inject(ViewHolder.this, view);
		}
	}
}
