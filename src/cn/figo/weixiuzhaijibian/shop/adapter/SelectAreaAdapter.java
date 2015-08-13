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
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.figo.weixiuzhaijibian.shop.R;
import cn.figo.weixiuzhaijibian.shop.model.Area;

/**
 * 师傅选择服务地区界面列表适配器
 *
 */
public class SelectAreaAdapter extends BaseAdapter {
	private Context mContext;
	private List<Area> mList;
	private Map<String, String> isSelectedType;
	
	
	public SelectAreaAdapter(Context mContext, List<Area> mList) {
		super();
		this.mContext = mContext;
		this.mList = mList;
	}
	
	public SelectAreaAdapter(Context mContext, List<Area> mList,Map<String, String> isSelectedType) {
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
			convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_select_area, null);
			viewHolder = new ViewHolder(convertView);
			convertView.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.nameTV.setText(mList.get(position).getAREA_NAME());
		
		viewHolder.areaCB.setOnCheckedChangeListener(new areaOnCheckedChangeListener(mList.get(position)));
		
		if(isSelectedType.containsKey(mList.get(position).getAREA_ID())){
			viewHolder.areaCB.setChecked(true);
		}else {
			viewHolder.areaCB.setChecked(false);
		}
		
		
		return convertView;
	}
	
	
	
	public class ViewHolder {

		@InjectView(R.id.type_iv)
		ImageView imgIV;

		@InjectView(R.id.area_name_tv)
		TextView nameTV;

		@InjectView(R.id.area_cb)
		CheckBox areaCB;

		
		public ViewHolder(View view) {
			ButterKnife.inject(ViewHolder.this, view);
		}
	}
	
	
	
	private class areaOnCheckedChangeListener implements OnCheckedChangeListener{

		Area area;
		
		public areaOnCheckedChangeListener(Area area) {
			super();
			this.area = area;
		}
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			
			String area_ID = area.getAREA_ID();
			if (isChecked) {
				if(!isSelectedType.containsKey(area_ID)){
					isSelectedType.put(area_ID, area_ID);
				}
			}else {
				isSelectedType.remove(area_ID);
			}
		}

	}
}
