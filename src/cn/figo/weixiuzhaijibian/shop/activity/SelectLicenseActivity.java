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
import cn.figo.weixiuzhaijibian.shop.adapter.SelectLicenseAdapter;
import cn.figo.weixiuzhaijibian.shop.api.ApiServices;
import cn.figo.weixiuzhaijibian.shop.app.MyApplication;
import cn.figo.weixiuzhaijibian.shop.callback.BaseCallback;
import cn.figo.weixiuzhaijibian.shop.model.BaseResponse;
import cn.figo.weixiuzhaijibian.shop.model.LicenseType;
import cn.figo.weixiuzhaijibian.shop.model.LicenseTypeResponse;
import cn.figo.weixiuzhaijibian.shop.ui.processdialog.WaitingProcessDialog;

/**
 * 
 * 选择证书牌照界面
 * 
 */
public class SelectLicenseActivity extends Activity implements OnClickListener {
	private MyApplication myApplication;
	private List<LicenseType> mList;
	private SelectLicenseAdapter adapter;
	private Map<String, String> isSelectedType;
	private StringBuilder sb;	
	
	@InjectView(R.id.return_iv)
	ImageView returnIV;

	@InjectView(R.id.title_text_tv)
	TextView titleTextTV;

	
	//XListView
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
				SelectLicenseActivity.this.finish();
			}
		});

		confirmTV.setOnClickListener(this);
		
		try {
			WaitingProcessDialog.showRoundProcessDialog(SelectLicenseActivity.this, "正在獲取數據...");
			getAllLicensePost();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	/**获取所有许可执照类型列表请求
	 * @throws Exception
	 */
	private void getAllLicensePost() throws Exception {

		ApiServices.getAppService().getLicenseType(
				new BaseCallback<LicenseTypeResponse>(SelectLicenseActivity.this) {

					@Override
					public void success(LicenseTypeResponse data, Response response) {
						// TODO Auto-generated method stub
						if (data.getCode().equals("200")) {
							Toast.makeText(SelectLicenseActivity.this,
									data.getMsg(), Toast.LENGTH_SHORT).show();

							if (data.getData() != null) {// 对界面进行设置
								mList = data.getData();
								
								try {
									getMasterLicenseTypePost();//师傅获取自己的许可执照列表请求
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						} else {
							Toast.makeText(SelectLicenseActivity.this,
									data.getMsg(), Toast.LENGTH_SHORT).show();
						}
						super.success(data, response);
					}

					@Override
					public void failure(RetrofitError arg0) {
						Toast.makeText(SelectLicenseActivity.this,
								"訪問服務器超時，請稍後重試", Toast.LENGTH_SHORT).show();
						super.failure(arg0);
					}

				});
	}

	/**师傅获取自己的许可执照列表请求
	 * @throws Exception
	 */
	private void getMasterLicenseTypePost() throws Exception {

		ApiServices.getAppService().getMasterLicenseType(
				myApplication.getLoginUser().getMASTER_ID(),
				new BaseCallback<LicenseTypeResponse>(
						SelectLicenseActivity.this) {

					@Override
					public void success(LicenseTypeResponse data,
							Response response) {
						// TODO Auto-generated method stub
						if (data.getCode().equals("200")) {

							List<LicenseType> list = data.getData();
							if (list != null) {
								for (int i = 0; i < list.size(); i++) {
									isSelectedType.put
										(list.get(i).getLICENSE_TYPE_ID(),
												list.get(i).getLICENSE_TYPE_ID());
								}
							}
							
							if (adapter == null) {// 对界面进行设置
								adapter = new SelectLicenseAdapter(
										SelectLicenseActivity.this,
										mList,isSelectedType);
							}
							listView.setAdapter(adapter);
							adapter.notifyDataSetChanged();

						} else {
							Toast.makeText(SelectLicenseActivity.this,
									data.getMsg(), Toast.LENGTH_SHORT).show();
						}
						super.success(data, response);
					}

					@Override
					public void failure(RetrofitError arg0) {
						Toast.makeText(SelectLicenseActivity.this,
								"訪問服務器超時，請稍後重試", Toast.LENGTH_SHORT).show();
						super.failure(arg0);
					}

				});
	}
	
	
	
	
	/**师傅修改自己的许可执照列表请求
	 * @throws Exception
	 */
	private void updateMasterLicensePost() throws Exception {
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
		
		ApiServices.getAppService().updateMasterLicenseType(
				myApplication.getLoginUser().getMASTER_ID(),sb.toString(),
				new BaseCallback<BaseResponse>(
						SelectLicenseActivity.this) {

					@Override
					public void success(BaseResponse data,
							Response response) {
						// TODO Auto-generated method stub
						if (data.getCode().equals("200")) {
							setResult(RESULT_OK);
							SelectLicenseActivity.this.finish();
						} else {
							Toast.makeText(SelectLicenseActivity.this,
									data.getMsg(), Toast.LENGTH_SHORT).show();
						}
						super.success(data, response);
					}

					@Override
					public void failure(RetrofitError arg0) {
						Toast.makeText(SelectLicenseActivity.this,
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
				WaitingProcessDialog.showRoundProcessDialog(SelectLicenseActivity.this, "正在提交你的修改...");
				updateMasterLicensePost();//师傅修改自己的许可执照列表请求
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
