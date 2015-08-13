package cn.figo.weixiuzhaijibian.shop.fragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.figo.weixiuzhaijibian.shop.R;
import cn.figo.weixiuzhaijibian.shop.activity.AcceptedOrderDetailsActivity;
import cn.figo.weixiuzhaijibian.shop.adapter.AcceptedOrderAdapter;
import cn.figo.weixiuzhaijibian.shop.api.ApiServices;
import cn.figo.weixiuzhaijibian.shop.api.Constants;
import cn.figo.weixiuzhaijibian.shop.app.MyApplication;
import cn.figo.weixiuzhaijibian.shop.callback.BaseCallback;
import cn.figo.weixiuzhaijibian.shop.model.Order6ServiceType;
import cn.figo.weixiuzhaijibian.shop.model.Order6ServiceTypePrice;
import cn.figo.weixiuzhaijibian.shop.model.OrderResponse;
import cn.figo.weixiuzhaijibian.shop.model.OrderSTPriceResponse;
import cn.figo.weixiuzhaijibian.shop.ui.processdialog.WaitingProcessDialog;
import cn.figo.weixiuzhaijibian.shop.ui.xlistview.XListView;
import cn.figo.weixiuzhaijibian.shop.ui.xlistview.XListView.IXListViewListener;

/**未完成订单
 * 
 */
public class UnfinishedOrdersFragment extends Fragment implements IXListViewListener {
	View rootView=null;
	private AcceptedOrderAdapter adapter;
	private List<Order6ServiceTypePrice> orderList;
	private int page = 1;	
	
	@InjectView(R.id.xListView)
	XListView xListView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		if(rootView==null){
			
            rootView=inflater.inflate(R.layout.fragment_unfinished_orders, null);
    }
      ViewGroup parent = (ViewGroup) rootView.getParent();
      if (parent != null) {
          parent.removeView(rootView);
           } 
      
            return rootView;
	}
	
	
	/* onViewCreated在onCreateView执行完后立即执行
     * @see android.support.v4.app.Fragment#onViewCreated(android.view.View, android.os.Bundle)
     */
    @Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
    	
    	initView(rootView);
		super.onViewCreated(view, savedInstanceState);
	}
    private void initView(View view)
    {	
    	ButterKnife.inject(this, view);
    	orderList = new ArrayList<Order6ServiceTypePrice>();
    	
		xListView.setPullLoadEnable(true);
		xListView.setPullRefreshEnable(true);
		xListView.setXListViewListener(this);
		try {//访问服务器
			WaitingProcessDialog.showRoundProcessDialog(getActivity(), "正在加載...");
			masterGetHistoryOrderPost();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		xListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
					Intent intent = new Intent();
					intent.putExtra("Order6ServiceType", orderList.get(position - 1));
					intent.setClass(getActivity(), AcceptedOrderDetailsActivity.class);
					startActivity(intent);
			}
		});

    }
    
    

	@Override
	public void onRefresh() {
		page =1;
		try {//访问服务器
			orderList.clear();
			adapter.clear();
			WaitingProcessDialog.showRoundProcessDialog(getActivity(), "正在加載...");
			masterGetHistoryOrderPost();
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
			masterGetHistoryOrderPost();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		xListView.stopLoadMore();
	}    
    
    
    
    /**师傅获取已接订单列表请求
	 * orderStatus = 0 代表是查  未完成订单
	 * @throws Exception
	 */
	private void masterGetHistoryOrderPost()throws Exception{
		String mID = MyApplication.getInstance().getLoginUid();
		int orderStatus = 0;
		ApiServices.getAppService().masterGetHistoryOrder(mID,orderStatus,page,Constants.pageSize,
				new BaseCallback<OrderSTPriceResponse>(getActivity()) {

					@Override
					public void success(OrderSTPriceResponse data, Response response) {
						// TODO Auto-generated method stub
						if (data.getCode().equals("200")) {
							if(UnfinishedOrdersFragment.this.isResumed()){
							//对分页数据判空
							if(data.getData()!= null && !data.getData().isEmpty()){
								orderList.addAll(data.getData());
								if(adapter == null){
									adapter = new AcceptedOrderAdapter(getActivity(), orderList);
									xListView.setAdapter(adapter);
								}
								adapter.notifyDataSetChanged();
								
								if(page == data.getTotalPage()){
									Toast.makeText(getActivity(), "已是最後一頁數據",
											Toast.LENGTH_SHORT).show();
									xListView.setPullLoadEnable(false);
								}
								
							}else{
								if(UnfinishedOrdersFragment.this.isResumed()){
								Toast.makeText(getActivity(), data.getMsg()+"沒有訂單數據",
										Toast.LENGTH_SHORT).show();
								}
							}
							

						} else {
							if(UnfinishedOrdersFragment.this.isResumed()){
							Toast.makeText(getActivity(),
									data.getMsg(), Toast.LENGTH_SHORT).show();
							}
						}
						}
						super.success(data, response);
					}

					@Override
					public void failure(RetrofitError arg0) {
						// TODO Auto-generated method stub
						if(UnfinishedOrdersFragment.this.isResumed()){
							
							Toast.makeText(getActivity(), "查看未完成訂單聯網請求失敗",
									Toast.LENGTH_SHORT).show();							
						}						
						super.failure(arg0);
					}
			
				});
	}
	
	
	
	
//	@Override
//	public void onResume() {
//		// TODO Auto-generated method stub
//		page =1;
//		try {//访问服务器
//			orderList.clear();
//			WaitingProcessDialog.showRoundProcessDialog(getActivity(), "正在加載...");
//			masterGetHistoryOrderPost();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		super.onResume();
//	}
}
