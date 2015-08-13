package cn.figo.weixiuzhaijibian.shop.activity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.figo.weixiuzhaijibian.shop.R;
import cn.figo.weixiuzhaijibian.shop.adapter.SystemInformAdapter;
import cn.figo.weixiuzhaijibian.shop.api.ApiServices;
import cn.figo.weixiuzhaijibian.shop.api.Constants;
import cn.figo.weixiuzhaijibian.shop.app.MyApplication;
import cn.figo.weixiuzhaijibian.shop.callback.BaseCallback;
import cn.figo.weixiuzhaijibian.shop.model.PostMsg;
import cn.figo.weixiuzhaijibian.shop.model.SystemMsgResponse;
import cn.figo.weixiuzhaijibian.shop.ui.processdialog.WaitingProcessDialog;
import cn.figo.weixiuzhaijibian.shop.ui.xlistview.XListView;
import cn.figo.weixiuzhaijibian.shop.ui.xlistview.XListView.IXListViewListener;

/**
 *
 *系统消息列表界面
 */
public class SystemInformListActivity extends Activity implements IXListViewListener{
	private List<PostMsg> mList;
	private SystemInformAdapter adapter;
	private MyApplication myApplication;
	@InjectView(R.id.return_iv)
	ImageView returnIV;
			
	@InjectView(R.id.title_text_tv)
	TextView titleTextTV;
	
	//XListView
	@InjectView(R.id.xListView)
	XListView xListView;
	private Integer page = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_system_inform_list);
		ButterKnife.inject(this);
		initView();
	}
	
	private void initView() {
		myApplication = MyApplication.getInstance();
		mList = new ArrayList<PostMsg>();
		titleTextTV.setText("系统消息");
		returnIV.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SystemInformListActivity.this.finish();
			}
		});
		
		

		xListView.setPullLoadEnable(true);
		xListView.setPullRefreshEnable(true);
		xListView.setXListViewListener(this);
		xListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				PostMsg postMsg = mList.get(position -1);
				Intent intent = new Intent();
				intent.putExtra("PostMsg", postMsg);
				intent.setClass(SystemInformListActivity.this, SystemInformDetailsActivity.class);
				startActivity(intent);
			}
		});
	}
	
	@Override
	public void onRefresh() {

		page = 1;//请求的页码重新置为1
		
		//获取网络数据
		try {//访问服务器
			mList.clear();
			adapter.clear();
			WaitingProcessDialog.showRoundProcessDialog(SystemInformListActivity.this, "數據加載中...");
			getSysInfoPost();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 获取系统时间
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		String lastTime = df.format(new Date());
		xListView.setRefreshTime(lastTime);
		xListView.stopRefresh();
	}

	@Override
	public void onLoadMore() {
		page++;//请求的页码自增
		//获取网络数据
		try {//访问服务器
			getSysInfoPost();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		xListView.stopLoadMore();
	}
	
	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		page = 1;//请求的页码重新置为1
		
		try {//访问服务器
			mList.clear();
			WaitingProcessDialog.showRoundProcessDialog(SystemInformListActivity.this, "數據加載中...");
			getSysInfoPost();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.onResume();
	}
	
	
	
	
	/**
	 * 获取系统消息列表请求
	 * 
	 * @throws Exception
	 */
	private void getSysInfoPost() throws Exception {
		
		ApiServices.getAppService().getMasterMsg(myApplication.getLoginUser().getMASTER_ID(),page ,Constants.pageSize,
				new BaseCallback<SystemMsgResponse>(SystemInformListActivity.this) {

					@Override
					public void success(SystemMsgResponse data, Response response) {
						// TODO Auto-generated method stub
						if(!SystemInformListActivity.this.isFinishing()){
							
							if (data.getCode().equals("200")) {
								if(null != data.getData() && data.getData().size() >0){
									
									mList.addAll(data.getData());
									if(adapter == null){
										adapter = new SystemInformAdapter(mContext, mList);
										xListView.setAdapter(adapter);
									}
									adapter.notifyDataSetChanged();
									
									if(page == data.getTotalPage()){
										Toast.makeText(SystemInformListActivity.this, "已是最後一頁數據",
												Toast.LENGTH_SHORT).show();
										xListView.setPullLoadEnable(false);
									}
								}
								

							} else {
								Toast.makeText(SystemInformListActivity.this,
										data.getMsg(), Toast.LENGTH_SHORT).show();
							}
							
							
						}
						
						super.success(data, response);
					}

					@Override
					public void failure(RetrofitError arg0) {
						Toast.makeText(SystemInformListActivity.this, "發送失敗",
								Toast.LENGTH_SHORT).show();
						super.failure(arg0);
					}

				});
	}
	
	
	
	
	
	
	
}
