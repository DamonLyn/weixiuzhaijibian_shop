package cn.figo.weixiuzhaijibian.shop.receiver;

import java.io.IOException;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import cn.figo.weixiuzhaijibian.shop.R;
import cn.figo.weixiuzhaijibian.shop.activity.IndexActivity;
import cn.figo.weixiuzhaijibian.shop.activity.SystemInformListActivity;
import cn.figo.weixiuzhaijibian.shop.model.PushExtra;
import cn.jpush.android.api.JPushInterface;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 自定义的广播接收器
 *
 */
public class PushReceiver extends BroadcastReceiver {
	
	
	@SuppressLint("NewApi") @Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();
		PushExtra extra = null;
		try {
			if(null != bundle.getString("cn.jpush.android.EXTRA")){
				
				extra = new ObjectMapper()
				.readValue(bundle.getString("cn.jpush.android.EXTRA"),
						PushExtra.class);
			}
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		Intent clickIntent = new Intent(context, IndexActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
				clickIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		
		NotificationCompat.Builder builder = new NotificationCompat.Builder(
				context);
		Notification notification = builder
				.setContentTitle(bundle.getString("cn.jpush.android.TITLE"))
				.setSmallIcon(R.drawable.ic_launcher)
				.setContentText(bundle.getString("cn.jpush.android.MESSAGE"))
				.setContentIntent(pendingIntent)
				.build();
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		NotificationManager nm = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		nm.notify(0, notification);
		
		
//		if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
//			Log.d("JPush", "[MyReceiver] 用户消息");
//			
//		}
		
		

	}

}
