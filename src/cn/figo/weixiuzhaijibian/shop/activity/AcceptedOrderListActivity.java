package cn.figo.weixiuzhaijibian.shop.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.figo.weixiuzhaijibian.shop.R;
import cn.figo.weixiuzhaijibian.shop.fragment.CheckAllOrdersFragment;
import cn.figo.weixiuzhaijibian.shop.fragment.FinishedOrdersFragment;
import cn.figo.weixiuzhaijibian.shop.fragment.UnfinishedOrdersFragment;

/**
 * 已接訂單列表界面
 * 
 * 
 */
public class AcceptedOrderListActivity extends FragmentActivity implements
		OnClickListener {

	private CheckAllOrdersFragment checkAllOrdersFragment;
	private UnfinishedOrdersFragment unfinishedOrdersFragment;
	private FinishedOrdersFragment finishedOrdersFragment;

	// 返回按鈕
	@InjectView(R.id.return_iv)
	ImageView returnIV;

	// 标题文字
	@InjectView(R.id.title_text_tv)
	TextView titleTextTV;
	// 全部订单按钮
	@InjectView(R.id.checkall_btn)
	Button checkAllBtn;
	// 未完成按钮
	@InjectView(R.id.unfinished_btn)
	Button unfinishedBtn;
	// 已完成按钮
	@InjectView(R.id.finished_btn)
	Button finishedBtn;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accepted_orders);
		ButterKnife.inject(this);
		initView();
	}

	private void initView() {
		titleTextTV.setText("已接訂單");
		returnIV.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AcceptedOrderListActivity.this.finish();
			}
		});

		checkAllBtn.setOnClickListener(this);
		unfinishedBtn.setOnClickListener(this);
		finishedBtn.setOnClickListener(this);
		
		setDefaultFragment();  
	}
	/** 默认显示的Fragment **/
	private void setDefaultFragment()  
    {  
		if (checkAllOrdersFragment == null) {
			checkAllOrdersFragment = new CheckAllOrdersFragment();
			
			addFragment(checkAllOrdersFragment);
			showFragment(checkAllOrdersFragment);
		} else {
			if (checkAllOrdersFragment.isHidden()) {
				showFragment(checkAllOrdersFragment);
			}
		}
    }  

	/** 显示Fragment **/
	public void showFragment(Fragment fragment) {
		FragmentTransaction ft = this.getSupportFragmentManager()
				.beginTransaction();
		// 设置进入时的动画
		ft.setCustomAnimations(R.anim.cu_push_right_in, R.anim.cu_push_left_out);

		if (checkAllOrdersFragment != null) {
			ft.hide(checkAllOrdersFragment);
		}
		if (unfinishedOrdersFragment != null) {
			ft.hide(unfinishedOrdersFragment);
		}
		if (finishedOrdersFragment != null) {
			ft.hide(finishedOrdersFragment);
		}

		ft.show(fragment);
		ft.commitAllowingStateLoss();

	}

	/** 添加Fragment **/
	public void addFragment(Fragment fragment) {
		FragmentTransaction ft = this.getSupportFragmentManager()
				.beginTransaction();
		ft.add(R.id.display_orders_container, fragment);
		ft.commit();
	}

	/** 删除Fragment **/
	public void removeFragment(Fragment fragment) {
		FragmentTransaction ft = this.getSupportFragmentManager()
				.beginTransaction();
		ft.remove(fragment);
		ft.commit();
	}

	

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.checkall_btn:
			if (checkAllOrdersFragment == null) {
				checkAllOrdersFragment = new CheckAllOrdersFragment();
				addFragment(checkAllOrdersFragment);
				showFragment(checkAllOrdersFragment);
			} else {
				if (checkAllOrdersFragment.isHidden()) {
					showFragment(checkAllOrdersFragment);
				}
			}
			break;
		case R.id.unfinished_btn:
			if (unfinishedOrdersFragment == null) {
				unfinishedOrdersFragment = new UnfinishedOrdersFragment();
				addFragment(unfinishedOrdersFragment);
				showFragment(unfinishedOrdersFragment);
			} else {
				if (unfinishedOrdersFragment.isHidden()) {
					showFragment(unfinishedOrdersFragment);
				}
			}
			break;
		case R.id.finished_btn:
			if (finishedOrdersFragment == null) {
				finishedOrdersFragment = new FinishedOrdersFragment();
				addFragment(finishedOrdersFragment);
				showFragment(finishedOrdersFragment);
			} else {
				if (finishedOrdersFragment.isHidden()) {
					showFragment(finishedOrdersFragment);
				}
			}
			break;

		default:
			break;
		}
	}
}
