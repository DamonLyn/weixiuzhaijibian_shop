package cn.figo.weixiuzhaijibian.shop.activity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.Activity;
import android.content.Context;
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
import cn.figo.weixiuzhaijibian.shop.adapter.PendingOrderAdapter;
import cn.figo.weixiuzhaijibian.shop.api.ApiServices;
import cn.figo.weixiuzhaijibian.shop.api.Constants;
import cn.figo.weixiuzhaijibian.shop.app.MyApplication;
import cn.figo.weixiuzhaijibian.shop.callback.BaseCallback;
import cn.figo.weixiuzhaijibian.shop.model.Order;
import cn.figo.weixiuzhaijibian.shop.model.Order6ServiceType;
import cn.figo.weixiuzhaijibian.shop.model.OrderResponse;
import cn.figo.weixiuzhaijibian.shop.ui.processdialog.WaitingProcessDialog;
import cn.figo.weixiuzhaijibian.shop.ui.xlistview.XListView;
import cn.figo.weixiuzhaijibian.shop.ui.xlistview.XListView.IXListViewListener;

/**
 * 待接订单界面
 *
 */
public class PendingOrderListActivity extends Activity implements IXListViewListener{

	private List<Order6ServiceType> orderList;
	private PendingOrderAdapter adapter;
	Context mContext;
	private MyApplication myApplication;
	private int page = 1;
	//返回按钮
	@InjectView(R.id.return_iv)
	ImageView returnIV;
			
	//标题文字
	@InjectView(R.id.title_text_tv)
	TextView titleTextTV;
	
	//XListView
	@InjectView(R.id.xListView)
	XListView xListView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pending_order_list);
		ButterKnife.inject(this);
		initView();
	}
	
	private void initView() {
		myApplication = MyApplication.getInstance();
		mContext = getApplicationContext();
		//标题栏
		titleTextTV.setText("待接訂單");
		returnIV.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				PendingOrderListActivity.this.finish();
			}
		});
		
		orderList = new ArrayList<Order6ServiceType>();
		
		xListView.setPullLoadEnable(true);//上拉加载功能启用
		xListView.setPullRefreshEnable(true);// 下拉刷新功能启用
		xListView.setXListViewListener(this);// 启用XListView上拉加载及下拉刷新功能
		xListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent();
				intent.putExtra("Order6ServiceType", orderList.get(position-1));
				intent.setClass(PendingOrderListActivity.this, PendingOrderDetailsActivity.class);
				startActivity(intent);
			}
		});
		
		try {//访问服务器
			WaitingProcessDialog.showRoundProcessDialog(PendingOrderListActivity.this, "數據加載中...");
			getUserDemandPost();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void onRefresh() {
		page =1;
		try {//访问服务器
			orderList.clear();
			adapter.clear();
			WaitingProcessDialog.showRoundProcessDialog(PendingOrderListActivity.this, "數據加載中...");
			getUserDemandPost();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 获取系统时间
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		String lastTime = df.format(new Date());
		xListView.setRefreshTime(lastTime);// 设置上次刷新的时间
		xListView.stopRefresh();
	}

	@Override
	public void onLoadMore() {
		page++;
		try {//访问服务器
			WaitingProcessDialog.showRoundProcessDialog(PendingOrderListActivity.this, "數據加載中...");
			getUserDemandPost();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		xListView.stopLoadMore();
	}
	
	
	
	
	
	/**获取未接订单请求
	 * @throws Exception
	 */
	private void getUserDemandPost()throws Exception{
		String mID = MyApplication.getInstance().getLoginUid();
		ApiServices.getAppService().getUserDemand(mID,page,Constants.pageSize,
				new BaseCallback<OrderResponse>(PendingOrderListActivity.this) {

					@Override
					public void success(OrderResponse data, Response response) {
						// TODO Auto-generated method stub
						if (data.getCode().equals("200")) {
							//对分页数据判空
							if(data.getData()!= null && !data.getData().isEmpty()){
								orderList.addAll(data.getData());
								if(adapter == null){
									adapter = new PendingOrderAdapter(mContext, orderList);
									xListView.setAdapter(adapter);
								}
								adapter.notifyDataSetChanged();
								
								if(page == data.getTotalPage()){
									Toast.makeText(PendingOrderListActivity.this, "已是最後一頁數據",
											Toast.LENGTH_SHORT).show();
									xListView.setPullLoadEnable(false);
								}
								
							}else{
								Toast.makeText(PendingOrderListActivity.this, data.getMsg()+"沒有訂單數據",
										Toast.LENGTH_SHORT).show();
							}
							

						} else {
							Toast.makeText(PendingOrderListActivity.this,
									data.getMsg(), Toast.LENGTH_SHORT).show();
						}
						super.success(data, response);
					}

					@Override
					public void failure(RetrofitError arg0) {
						// TODO Auto-generated method stub
						Toast.makeText(PendingOrderListActivity.this, "網絡訪問失敗",
								Toast.LENGTH_SHORT).show();
						super.failure(arg0);
					}
			
					
			
					
			
				});
		
		
		
	}
	
}
