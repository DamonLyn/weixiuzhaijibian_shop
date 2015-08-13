package cn.figo.weixiuzhaijibian.shop.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.figo.weixiuzhaijibian.shop.R;

/**
 * webview 广告界面
 */
public class BrowserActivity extends Activity implements OnClickListener {
	@InjectView(R.id.title_text_tv)
	TextView titleTV;
	@InjectView(R.id.return_iv)
	ImageView returnIV;
	@InjectView(R.id.webview)
	WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_browser);
		ButterKnife.inject(this);
		initView();
	}

	private void initView() {
		
		titleTV.setText("詳情");
		returnIV.setVisibility(View.VISIBLE);
		returnIV.setOnClickListener(this);
		String url = getIntent().getStringExtra("url");
		webView.loadUrl(url);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
			webView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
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
