package cn.figo.weixiuzhaijibian.shop.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.figo.weixiuzhaijibian.shop.R;
import cn.figo.weixiuzhaijibian.shop.api.Constants;
import cn.figo.weixiuzhaijibian.shop.model.Order6ServiceTypePrice;
import cn.figo.weixiuzhaijibian.shop.model.OrderSTPriceResponse;
import cn.figo.weixiuzhaijibian.shop.model.ServiceType;

import com.nostra13.universalimageloader.core.ImageLoader;

/**
 *  已接订单界面列表适配器
 */
public class AcceptedOrderAdapter extends BaseAdapter {
	private Context mContext;
	private List<Order6ServiceTypePrice> mList;
	
	
	public AcceptedOrderAdapter(Context mContext, List<Order6ServiceTypePrice> mList) {
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
			convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_accepted_orders, null);
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
		
//		viewHolder.dateTV.setText(mList.get(position).getO_USER_COMFIRM_TIME());
		Integer i = mList.get(position).getO_STATUS();
		switch (mList.get(position).getO_STATUS()) {
		case 1:
			viewHolder.stateTV.setText("未完成");
			if(null != mList.get(position).getMasterPrice().getMP_LOW_PRICE()){
				viewHolder.priceTV.setText("HK$"+mList.get(position).getMasterPrice().getMP_LOW_PRICE());
			}
			if(null != mList.get(position).getO_USER_COMFIRM_TIME()){//用户采纳报价的时间
				viewHolder.dateTV.setText(mList.get(position).getO_USER_COMFIRM_TIME());
			}
			break;
			
		case 2:	
			viewHolder.stateTV.setText("已完成");
			if(null != mList.get(position).getO_FINAL_PRICE()){
				viewHolder.priceTV.setText("HK$"+mList.get(position).getO_FINAL_PRICE().toString());
			}
			if(null != mList.get(position).getO_MASTER_COMFIRM_TIME()){//订单最终完成时间
				viewHolder.dateTV.setText(mList.get(position).getO_USER_COMFIRM_TIME());
			}
			break;
		case 3:
			viewHolder.stateTV.setText("已完成");
			if(null != mList.get(position).getO_FINAL_PRICE()){
				viewHolder.priceTV.setText("HK$"+mList.get(position).getO_FINAL_PRICE().toString());
			}
			if(null != mList.get(position).getO_MASTER_COMFIRM_TIME()){//订单最终完成时间
				viewHolder.dateTV.setText(mList.get(position).getO_USER_COMFIRM_TIME());
			}
			break;

		default:
			break;
		}
		
		
		
		return convertView;
	}
	
	
	
	public class ViewHolder {

		@InjectView(R.id.repair_type_iv)
		ImageView imgIV;

		@InjectView(R.id.name_tv)
		TextView nameTV;

		@InjectView(R.id.content_tv)
		TextView contentTV;
		
		@InjectView(R.id.price_tv)
		TextView priceTV;
		
		@InjectView(R.id.state_tv)
		TextView stateTV;
		
		@InjectView(R.id.date_tv)
		TextView dateTV;

		
		public ViewHolder(View view) {
			ButterKnife.inject(ViewHolder.this, view);
		}
	}
}
