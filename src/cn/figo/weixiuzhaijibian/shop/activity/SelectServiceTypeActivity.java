package cn.figo.weixiuzhaijibian.shop.activity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.figo.weixiuzhaijibian.shop.R;
import cn.figo.weixiuzhaijibian.shop.adapter.SelectServiceTypeAdapter;
import cn.figo.weixiuzhaijibian.shop.api.ApiServices;
import cn.figo.weixiuzhaijibian.shop.app.MyApplication;
import cn.figo.weixiuzhaijibian.shop.callback.BaseCallback;
import cn.figo.weixiuzhaijibian.shop.model.BaseResponse;
import cn.figo.weixiuzhaijibian.shop.model.ServiceType;
import cn.figo.weixiuzhaijibian.shop.model.ServiceTypeResponse;
import cn.figo.weixiuzhaijibian.shop.ui.processdialog.WaitingProcessDialog;

/**
 * 
 * 选择服务项目界面
 * 
 */
public class SelectServiceTypeActivity extends Activity implements OnClickListener {
	private MyApplication myApplication;
	private List<ServiceType> mList;
	private SelectServiceTypeAdapter adapter;
	private Map<String, String> isSelectedType;
	private StringBuilder sb;
	@InjectView(R.id.return_iv)
	ImageView returnIV;

	@InjectView(R.id.title_text_tv)
	TextView titleTextTV;
	

	@InjectView(R.id.listView)
	ListView listView;
	
	//确定
	@InjectView(R.id.confirm_tv)
	TextView confirmTV;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_type);
		ButterKnife.inject(this);
		initView();
	}

	private void initView() {
		myApplication = MyApplication.getInstance();
		
		isSelectedType = new HashMap<String, String>();
		
		titleTextTV.setText("分類選擇");
		returnIV.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				SelectServiceTypeActivity.this.finish();
			}
		});

		confirmTV.setOnClickListener(this);
		
		
		
		try {
			WaitingProcessDialog.showRoundProcessDialog(SelectServiceTypeActivity.this, "正在獲取數據...");
			getAllServiceTypePost();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	/**
	 * 获取所有的维修服务项请求
	 * 
	 * @throws Exception
	 */
	private void getAllServiceTypePost() throws Exception {

		ApiServices.getAppService().getServiceType(
				new BaseCallback<ServiceTypeResponse>(
						SelectServiceTypeActivity.this) {

					@Override
					public void success(ServiceTypeResponse data,
							Response response) {
						// TODO Auto-generated method stub
						if (data.getCode().equals("200")) {

							if (data.getData() != null) {
								mList = data.getData();
								try {
									getMasterServiceTypePost();// 获取师傅对应的维修服务项请求
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}

							

						} else {
							Toast.makeText(SelectServiceTypeActivity.this,
									data.getMsg(), Toast.LENGTH_SHORT).show();
						}
						super.success(data, response);
					}

					@Override
					public void failure(RetrofitError arg0) {
						Toast.makeText(SelectServiceTypeActivity.this,
								"訪問服務器超時，請稍後重試", Toast.LENGTH_SHORT).show();
						super.failure(arg0);
					}

				});
	}

	/**
	 * 获取师傅对应的维修服务项请求
	 * 
	 * @throws Exception
	 */
	private void getMasterServiceTypePost() throws Exception {
		ApiServices.getAppService().getMasterServiceType(
				myApplication.getLoginUser().getMASTER_ID(),
				new BaseCallback<ServiceTypeResponse>(
						SelectServiceTypeActivity.this) {

					@Override
					public void success(ServiceTypeResponse data,
							Response response) {
						// TODO Auto-generated method stub
						if (data.getCode().equals("200")) {

							List<ServiceType> serviceTypes = data.getData();
							if (serviceTypes != null) {
								for (int i = 0; i < serviceTypes.size(); i++) {
									isSelectedType.put(serviceTypes.get(i)
											.getSERVICE_TYPE_ID(), serviceTypes
											.get(i).getSERVICE_TYPE_ID());
								}
							}
							
							if (adapter == null) {// 对界面进行设置
								adapter = new SelectServiceTypeAdapter(
										SelectServiceTypeActivity.this,
										mList,isSelectedType);
							}
							listView.setAdapter(adapter);
							adapter.notifyDataSetChanged();

						} else {
							Toast.makeText(SelectServiceTypeActivity.this,
									data.getMsg(), Toast.LENGTH_SHORT).show();
						}
						super.success(data, response);
					}

					@Override
					public void failure(RetrofitError arg0) {
						Toast.makeText(SelectServiceTypeActivity.this,
								"訪問服務器超時，請稍後重試", Toast.LENGTH_SHORT).show();
						super.failure(arg0);
					}

				});
	}
	
	
	
	
	
	
	
	/**师傅修改维修项目（修改自己可提供的服务类型）请求
	 * @throws Exception
	 */
	private void updateMasterServiceTypePost() throws Exception {
		sb = new StringBuilder();
		int i = 0;
		  for (String v : isSelectedType.values()) {

			  if(i == 0){
				  sb.append(v);
			  }else{
				  sb.append(",");
				  sb.append(v);
			  }
			  i++;
		  }
		
		ApiServices.getAppService().updateMasterServiceType(
				myApplication.getLoginUser().getMASTER_ID(),sb.toString(),
				new BaseCallback<BaseResponse>(
						SelectServiceTypeActivity.this) {

					@Override
					public void success(BaseResponse data,
							Response response) {
						// TODO Auto-generated method stub
						if (data.getCode().equals("200")) {
							setResult(RESULT_OK);
							SelectServiceTypeActivity.this.finish();
						} else {
							Toast.makeText(SelectServiceTypeActivity.this,
									data.getMsg(), Toast.LENGTH_SHORT).show();
						}
						super.success(data, response);
					}

					@Override
					public void failure(RetrofitError arg0) {
						Toast.makeText(SelectServiceTypeActivity.this,
								"訪問服務器超時，請稍後重試", Toast.LENGTH_SHORT).show();
						super.failure(arg0);
					}

				});
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.confirm_tv:
			
			
			try {
				WaitingProcessDialog.showRoundProcessDialog(SelectServiceTypeActivity.this, "正在提交你的修改...");
				updateMasterServiceTypePost();//提交师傅的服务项目更新修改
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			break;

		default:
			break;
		}
	}
	
	
	
	
}
