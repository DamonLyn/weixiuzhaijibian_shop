package cn.figo.weixiuzhaijibian.shop.ui.processdialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import cn.figo.weixiuzhaijibian.shop.R;

public class WaitingProcessDialog {

	public static Dialog mDialog;//圆形进度条等待提示对话框对象
	
    /**显示进度条对话框
     * @param mContext
     * @param contentText
     */
    public static void showRoundProcessDialog(final Context mContext, String contentText) {
		OnKeyListener keyListener = new OnKeyListener() {
			@Override
			public boolean onKey(DialogInterface dialog, int keyCode,
					KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_HOME
						|| keyCode == KeyEvent.KEYCODE_SEARCH) {
					return true;
				}
				return false;
			}
		};
		
		int dialogWaitingProgressLayout = R.layout.dialog_waiting_progress_layout;
		
		View parent = LayoutInflater.from(mContext).inflate(dialogWaitingProgressLayout, null);
		TextView waitingTipTV = (TextView) parent.findViewById(R.id.waiting_tip_tv);
		waitingTipTV.setText(contentText);
		
		mDialog = new AlertDialog.Builder(mContext).create();
		mDialog.setCanceledOnTouchOutside(false);
		mDialog.setOnKeyListener(keyListener);
		mDialog.show();
		mDialog.setContentView(parent);
	}
    
}
