package cn.figo.weixiuzhaijibian.shop.activity;

import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.figo.weixiuzhaijibian.shop.R;
import cn.figo.weixiuzhaijibian.shop.api.ApiServices;
import cn.figo.weixiuzhaijibian.shop.callback.BaseCallback;
import cn.figo.weixiuzhaijibian.shop.model.CompanyInfomationResponse;
import cn.figo.weixiuzhaijibian.shop.ui.processdialog.WaitingProcessDialog;

/**
 * 服务条款界面
 */
public class ServiceTermsActivity extends Activity implements OnClickListener {
	@InjectView(R.id.title_text_tv)
	TextView title;
	@InjectView(R.id.return_iv)
	ImageView back;
	@InjectView(R.id.tv_service_terms)
	TextView serviceTerms;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service_terms);
		initView();
		try {
			WaitingProcessDialog.showRoundProcessDialog(ServiceTermsActivity.this, "正在加載服務條款內容...");
			getCompany();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initView() {
		ButterKnife.inject(this);
		title.setText("服務條款");
		back.setVisibility(View.VISIBLE);
		back.setOnClickListener(this);
	}

	private void getCompany() throws Exception{
		ApiServices.getAppService().getCompany(
				new BaseCallback<CompanyInfomationResponse>(this) {

					@Override
					public void success(CompanyInfomationResponse data, Response response) {
						if (data.getCode().equals("200")) {
							serviceTerms.setText(data.getData()
									.getCI_SERVICE_TERMS());
						}
						super.success(data, response);
					}

					@Override
					public void failure(RetrofitError arg0) {
						Toast.makeText(mContext, "訪問服務器超時，請稍後重試",
								Toast.LENGTH_SHORT).show();
						super.failure(arg0);
					}

				});
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.return_iv:
			finish();
			break;
		default:
			break;
		}
	}
}
