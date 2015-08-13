package cn.figo.weixiuzhaijibian.shop.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.figo.weixiuzhaijibian.shop.R;
import cn.figo.weixiuzhaijibian.shop.model.PostMsg;

/**
 * 系统消息列表适配器
 */
public class SystemInformAdapter extends BaseAdapter {
	private Context mContext;
	private List<PostMsg> mList;
	
	
	
	public SystemInformAdapter(Context mContext, List<PostMsg> mList) {
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
			convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_system_inform, null);
			viewHolder = new ViewHolder(convertView);
			convertView.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.messageTitleTV.setText(mList.get(position).getPM_TITLE());
		viewHolder.messageContentTV.setText(mList.get(position).getPM_CONTENT());
		Integer hasRead = mList.get(position).getHasRead();
		if(null != hasRead && hasRead == 0){
			viewHolder.unReadMsgTipIV.setVisibility(View.VISIBLE);
		}else{
			viewHolder.unReadMsgTipIV.setVisibility(View.INVISIBLE);
		}
		
		return convertView;
	}
	
	
	public class ViewHolder {
		
		@InjectView(R.id.message_title_tv)
		TextView messageTitleTV;

		@InjectView(R.id.message_content_tv)
		TextView messageContentTV;

		
		//消息数量提示图
		@InjectView(R.id.unReadMsg_tip_iv)
		ImageView unReadMsgTipIV;
		
		
		
		public ViewHolder(View view) {
			ButterKnife.inject(ViewHolder.this, view);
		}
	}
}
