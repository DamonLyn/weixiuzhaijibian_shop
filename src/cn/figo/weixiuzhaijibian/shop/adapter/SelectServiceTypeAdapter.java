package cn.figo.weixiuzhaijibian.shop.adapter;

import java.util.List;
import java.util.Map;

import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.figo.weixiuzhaijibian.shop.R;
import cn.figo.weixiuzhaijibian.shop.model.ServiceType;

/**
 * 服务项目界面列表适配器
 */
public class SelectServiceTypeAdapter extends BaseAdapter {
	private Context mContext;
	private List<ServiceType> mList;
	private Map<String, String> isSelectedType;
	
	public SelectServiceTypeAdapter(Context mContext, List<ServiceType> mList) {
		super();
		this.mContext = mContext;
		this.mList = mList;
	}
	
	public SelectServiceTypeAdapter(Context mContext, List<ServiceType> mList,Map<String, String> isSelectedType) {
		super();
		this.mContext = mContext;
		this.mList = mList;
		this.isSelectedType = isSelectedType;
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
			convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_service_type, null);
			viewHolder = new ViewHolder(convertView);
			convertView.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		viewHolder.nameTV.setText(mList.get(position).getST_NAME());

		ImageLoader.getInstance().displayImage(mList.get(position).getST_ICON_GRAY(), viewHolder.imgIV);
		
		viewHolder.typeCB.setOnCheckedChangeListener(new ServiceTypeOnCheckedChangeListener(mList.get(position)));
		
		if(isSelectedType.containsKey(mList.get(position).getSERVICE_TYPE_ID())){
			viewHolder.typeCB.setChecked(true);
		}else {
			viewHolder.typeCB.setChecked(false);
		}
		return convertView;
	}
	
	
	
	public class ViewHolder {

		@InjectView(R.id.type_iv)
		ImageView imgIV;

		@InjectView(R.id.type_name_tv)
		TextView nameTV;

		@InjectView(R.id.type_cb)
		CheckBox typeCB;

		
		public ViewHolder(View view) {
			ButterKnife.inject(ViewHolder.this, view);
		}
	}
	
	private class ServiceTypeOnCheckedChangeListener implements OnCheckedChangeListener{

		ServiceType serviceType;
		
		public ServiceTypeOnCheckedChangeListener(ServiceType serviceType) {
			super();
			this.serviceType = serviceType;
		}
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			
			String service_TYPE_ID = serviceType.getSERVICE_TYPE_ID();
			if (isChecked) {
				if(!isSelectedType.containsKey(service_TYPE_ID)){
					isSelectedType.put(service_TYPE_ID, service_TYPE_ID);
				}
			}else {
				isSelectedType.remove(service_TYPE_ID);
			}
		}

	}
	
	
}
