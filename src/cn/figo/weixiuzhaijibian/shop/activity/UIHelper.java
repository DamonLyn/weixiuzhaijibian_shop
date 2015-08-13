package cn.figo.weixiuzhaijibian.shop.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import cn.figo.weixiuzhaijibian.shop.callback.ICallbackResult;
import cn.figo.weixiuzhaijibian.shop.service.DownloadService;
import cn.figo.weixiuzhaijibian.shop.service.DownloadService.DownloadBinder;


/**
 * 与下载apk包功能相关的界面帮助类
 * 
 */
public class UIHelper {


    public static final String WEB_LOAD_IMAGES = "<script type=\"text/javascript\"> var allImgUrls = getAllImgSrc(document.body.innerHTML);</script>";

    private static final String SHOWIMAGE = "ima-api:action=showImage&data=";


    public static void openDownLoadService(Context context, String downurl,
            String tilte) {
        final ICallbackResult callback = new ICallbackResult() {

            @Override
            public void OnBackResult(Object s) {}
        };
        ServiceConnection conn = new ServiceConnection() {

            @Override
            public void onServiceDisconnected(ComponentName name) {}

            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                DownloadBinder binder = (DownloadBinder) service;
                binder.addCallback(callback);
                binder.start();

            }
        };
        Intent intent = new Intent(context, DownloadService.class);
        intent.putExtra(DownloadService.BUNDLE_KEY_DOWNLOAD_URL, downurl);
        intent.putExtra(DownloadService.BUNDLE_KEY_TITLE, tilte);
        context.startService(intent);
        context.bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }






}
