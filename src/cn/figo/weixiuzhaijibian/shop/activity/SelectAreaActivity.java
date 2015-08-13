package cn.figo.weixiuzhaijibian.shop.activity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.figo.weixiuzhaijibian.shop.R;
import cn.figo.weixiuzhaijibian.shop.adapter.SelectAreaAdapter;
import cn.figo.weixiuzhaijibian.shop.api.ApiServices;
import cn.figo.weixiuzhaijibian.shop.app.MyApplication;
import cn.figo.weixiuzhaijibian.shop.callback.BaseCallback;
import cn.figo.weixiuzhaijibian.shop.model.Area;
import cn.figo.weixiuzhaijibian.shop.model.AreaResponse;
import cn.figo.weixiuzhaijibian.shop.model.BaseResponse;
import cn.figo.weixiuzhaijibian.shop.ui.processdialog.WaitingProcessDialog;

/**
 * 选择服务地区界面
 * 
 * 
 */
public class SelectAreaActivity extends Activity implements OnClickListener {
	private MyApplication myApplication;
	private List<Area> mList;
	private SelectAreaAdapter adapter;
	private Map<String, String> isSelectedType;
	private StringBuilder sb;
	@InjectView(R.id.return_iv)
	ImageView returnIV;
	@InjectView(R.id.title_text_tv)
	TextView titleTextTV;

	// XListView
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
				SelectAreaActivity.this.finish();
			}
		});

		confirmTV.setOnClickListener(this);
		
		
		try {
			WaitingProcessDialog.showRoundProcessDialog(SelectAreaActivity.this, "正在獲取數據...");
			getAllAreaPost();//获取全部地区请求
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 获取全部地区请求
	 * 
	 * @throws Exception
	 */
	private void getAllAreaPost() throws Exception {

		ApiServices.getAppService().getArea(
				new BaseCallback<AreaResponse>(SelectAreaActivity.this) {

					@Override
					public void success(AreaResponse data, Response response) {
						// TODO Auto-generated method stub
						if (data.getCode().equals("200")) {
							Toast.makeText(SelectAreaActivity.this,
									data.getMsg(), Toast.LENGTH_SHORT).show();

							if (data.getData() != null) {// 对界面进行设置
								mList = data.getData();
//								if (adapter == null) {
//									adapter = new SelectAreaAdapter(
//											SelectAreaActivity.this, mList);
//								}
//								listView.setAdapter(adapter);
//								adapter.notifyDataSetChanged();
								
								try {
									getMasterAreaPost();//师傅获取自己的服务地区（获取自己可服务的地区）请求
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							}
						} else {
							Toast.makeText(SelectAreaActivity.this,
									data.getMsg(), Toast.LENGTH_SHORT).show();
						}
						super.success(data, response);
					}

					@Override
					public void failure(RetrofitError arg0) {
						Toast.makeText(SelectAreaActivity.this,
								"訪問服務器超時，請稍後重試", Toast.LENGTH_SHORT).show();
						super.failure(arg0);
					}

				});
	}

	
	
	
	/**师傅获取自己的服务地区（获取自己可服务的地区）请求
	 * @throws Exception
	 */
	private void getMasterAreaPost() throws Exception {

		ApiServices.getAppService().getMasterArea(
				myApplication.getLoginUser().getMASTER_ID(),
				new BaseCallback<AreaResponse>(
						SelectAreaActivity.this) {

					@Override
					public void success(AreaResponse data,
							Response response) {
						// TODO Auto-generated method stub
						if (data.getCode().equals("200")) {

							List<Area> areaList = data.getData();
							if (areaList != null) {
								for (int i = 0; i < areaList.size(); i++) {
									isSelectedType.put
										(areaList.get(i).getAREA_ID(),
											areaList.get(i).getAREA_ID());
								}
							}
							
							if (adapter == null) {// 对界面进行设置
								adapter = new SelectAreaAdapter(
										SelectAreaActivity.this,
										mList,isSelectedType);
							}
							listView.setAdapter(adapter);
							adapter.notifyDataSetChanged();

						} else {
							Toast.makeText(SelectAreaActivity.this,
									data.getMsg(), Toast.LENGTH_SHORT).show();
						}
						super.success(data, response);
					}

					@Override
					public void failure(RetrofitError arg0) {
						Toast.makeText(SelectAreaActivity.this,
								"訪問服務器超時，請稍後重試", Toast.LENGTH_SHORT).show();
						super.failure(arg0);
					}

				});
	}

	/**师傅修改自己的服务地区（修改自己可服务的地区）请求
	 * @throws Exception
	 */
	private void updateMasterAreaPost() throws Exception {
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
		
		ApiServices.getAppService().updateMasterArea(
				myApplication.getLoginUser().getMASTER_ID(),sb.toString(),
				new BaseCallback<BaseResponse>(
						SelectAreaActivity.this) {

					@Override
					public void success(BaseResponse data,
							Response response) {
						// TODO Auto-generated method stub
						if (data.getCode().equals("200")) {
							setResult(RESULT_OK);
							SelectAreaActivity.this.finish();
						} else {
							Toast.makeText(SelectAreaActivity.this,
									data.getMsg(), Toast.LENGTH_SHORT).show();
						}
						super.success(data, response);
					}

					@Override
					public void failure(RetrofitError arg0) {
						Toast.makeText(SelectAreaActivity.this,
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
				WaitingProcessDialog.showRoundProcessDialog(SelectAreaActivity.this, "正在提交你的修改...");
				updateMasterAreaPost();//提交师傅的服务地区更新修改
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
