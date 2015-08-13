package cn.figo.weixiuzhaijibian.shop.fragment;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.figo.weixiuzhaijibian.shop.R;

/**
 * 版本更新对话框
 *
 */
@SuppressLint("NewApi") public class VersionUpdateDialogfragment extends DialogFragment implements
		OnClickListener{
	private onVersionUpdateDialogClickListener mCancelClickListener;
	private onVersionUpdateDialogClickListener mConfirmClickListener;
	private Context mContext;
	private String tip;
	
	
	
	public VersionUpdateDialogfragment(Context mContext,String tip){
		this.mContext = mContext;
		this.tip = tip;
	}
	
	
	//取消按钮与确认按钮
	@InjectView(R.id.tv_cancel)
	TextView cancel;
	@InjectView(R.id.tv_ok)
	TextView ok;
	//分割线
	@InjectView(R.id.split_line)
	View splitLineView;
	
	
	@InjectView(R.id.version_tv)
	TextView versionTV;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
		getDialog().getWindow().setBackgroundDrawableResource(
				android.R.color.transparent);
		View view = inflater.inflate(
				R.layout.dialogfragment_version_check, null);
		initView(view);
		return view;
	}

	private void initView(View view) {
		ButterKnife.inject(this, view);
		mContext = getActivity();
		versionTV.setText(tip);//对话框提示文字设置
		setCancelable(false);// 设置点击对话框以外不被取消
		cancel.setOnClickListener(this);
		ok.setOnClickListener(this);
		
	}

	public static interface onVersionUpdateDialogClickListener {
		public void onClick(VersionUpdateDialogfragment dialogfragment);
	}

	public VersionUpdateDialogfragment setCancelClickListener(
			onVersionUpdateDialogClickListener listener) {
		mCancelClickListener = listener;
		return this;
	}
	public VersionUpdateDialogfragment setConfirmClickListener(
			onVersionUpdateDialogClickListener listener) {
		mConfirmClickListener = listener;
		return this;
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.tv_cancel:
			if(mCancelClickListener != null ){
				mCancelClickListener.onClick(VersionUpdateDialogfragment.this);
			}else{
				dismiss();
			}
			
			break;
		case R.id.tv_ok:
			if(mConfirmClickListener != null){
				mConfirmClickListener.onClick(VersionUpdateDialogfragment.this);
			}else{
				dismiss();
			}
			break;

		default:
			break;
		}
	}

	

	
	
	
}
