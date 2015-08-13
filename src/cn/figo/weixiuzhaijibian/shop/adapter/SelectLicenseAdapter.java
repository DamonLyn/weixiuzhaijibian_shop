package cn.figo.weixiuzhaijibian.shop.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.figo.weixiuzhaijibian.shop.R;
import cn.figo.weixiuzhaijibian.shop.model.LicenseType;

/**
 *  认证牌照界面列表适配器
 */
public class SelectLicenseAdapter extends BaseAdapter {
	private Context mContext;
	private List<LicenseType> mList;
	private Map<String, String> isSelectedType;
	
	
	public SelectLicenseAdapter(Context mContext, List<LicenseType> mList) {
		super();
		this.mContext = mContext;
		this.mList = mList;
	}

	public SelectLicenseAdapter(Context mContext, List<LicenseType> mList,Map<String, String> isSelectedType) {
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
			convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_select_license, null);
			viewHolder = new ViewHolder(convertView);
			convertView.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.nameTV.setText(mList.get(position).getLT_NAME());
		
		viewHolder.licenseCB.setOnCheckedChangeListener(new licenseTypeOnCheckedChangeListener(mList.get(position)));
		
		if(isSelectedType.containsKey(mList.get(position).getLICENSE_TYPE_ID())){
			viewHolder.licenseCB.setChecked(true);
		}else {
			viewHolder.licenseCB.setChecked(false);
		}		
		
		
		
		
		return convertView;
	}
	
	
	
	public class ViewHolder {


		@InjectView(R.id.license_name_tv)
		TextView nameTV;

		@InjectView(R.id.license_cb)
		CheckBox licenseCB;

		
		public ViewHolder(View view) {
			ButterKnife.inject(ViewHolder.this, view);
		}
	}
	
	
	private class licenseTypeOnCheckedChangeListener implements OnCheckedChangeListener{

		LicenseType licenseType;
		
		public licenseTypeOnCheckedChangeListener(LicenseType licenseType) {
			super();
			this.licenseType = licenseType;
		}
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			
			 String license_TYPE_ID = licenseType.getLICENSE_TYPE_ID();
			if (isChecked) {
				if(!isSelectedType.containsKey(license_TYPE_ID)){
					isSelectedType.put(license_TYPE_ID, license_TYPE_ID);
				}
			}else {
				isSelectedType.remove(license_TYPE_ID);
			}
		}

	}	
}
