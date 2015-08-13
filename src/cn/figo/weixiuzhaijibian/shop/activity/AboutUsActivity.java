package cn.figo.weixiuzhaijibian.shop.activity;

import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.figo.weixiuzhaijibian.shop.R;
import cn.figo.weixiuzhaijibian.shop.api.ApiServices;
import cn.figo.weixiuzhaijibian.shop.app.MyApplication;
import cn.figo.weixiuzhaijibian.shop.callback.BaseCallback;
import cn.figo.weixiuzhaijibian.shop.model.CompanyInfomationResponse;
import cn.figo.weixiuzhaijibian.shop.ui.processdialog.WaitingProcessDialog;

/**
 * 关于我们界面
 * 
 */
public class AboutUsActivity extends Activity {

	private MyApplication myApplication;

//	// 返回按钮
//	@InjectView(R.id.return_iv)
//	ImageView returnIV;
//
//	// 标题栏文字
//	@InjectView(R.id.title_text_tv)
//	TextView titleTextTV;
	// 公司姓名
	@InjectView(R.id.company_name)
	TextView companyNameTV;
	// 公司简介
	@InjectView(R.id.company_intro)
	TextView companyIntroTV;
	// 公司电话
	@InjectView(R.id.company_tel)
	TextView companyelTV;
	// 公司地址
	@InjectView(R.id.company_address)
	TextView companyAddressTV;

	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		myApplication = MyApplication.getInstance();

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about_us);
		ButterKnife.inject(this);
		initView();
	}

	private void initView() {

		// 访问服务器
		try {
			WaitingProcessDialog.showRoundProcessDialog(AboutUsActivity.this, "正在加載...");
			getCompanyInformation();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 获取公司信息，也即是 关于我们
	 * 
	 * @throws Exception
	 */
	private void getCompanyInformation() throws Exception {

		ApiServices.getAppService().getCompany(
				new BaseCallback<CompanyInfomationResponse>(
						AboutUsActivity.this) {

					@Override
					public void success(CompanyInfomationResponse data,
							Response response) {
						// TODO Auto-generated method stub
						if (data.getCode().equals("200")) {
							if(data.getData() != null){
								companyNameTV.setText(data.getData().getCi_COMPANY_NAME());
								companyIntroTV.setText(data.getData().getCI_COMPANY_INTRO());
								companyelTV.setText(data.getData().getCI_COMPANY_TEL());
								companyAddressTV.setText(data.getData().getCI_COMPANY_ADDR());
								
							}
							
							
						} else {
							Toast.makeText(AboutUsActivity.this, data.getMsg(),
									Toast.LENGTH_SHORT).show();
						}
						super.success(data, response);
					}

					@Override
					public void failure(RetrofitError arg0) {
						Toast.makeText(AboutUsActivity.this, "数据请求失败",
								Toast.LENGTH_SHORT).show();
						super.failure(arg0);
					}

				});
	}

}
