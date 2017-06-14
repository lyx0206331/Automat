package com.adrian.automat.application;

import android.app.Application;
import android.os.Handler;
import android.os.Message;

import com.adrian.automat.tools.CommUtil;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.URLConnectionNetworkExecutor;
import com.yanzhenjie.nohttp.cache.DiskCacheStore;

/**
 * Created by ranqing on 2017/6/2.
 */

public class MyApplication extends Application {

    private static MyApplication instance;

    private static long restInterval = 60000;

    private long lastTouchTime;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        NoHttp.initialize(this, new NoHttp.Config()
                // 设置全局连接超时时间，单位毫秒
                .setConnectTimeout(30 * 1000)
                // 设置全局服务器响应超时时间，单位毫秒
                .setReadTimeout(30 * 1000)
                // 使用HttpURLConnection
                .setNetworkExecutor(new URLConnectionNetworkExecutor())
                // 或者使用OkHttp
                // .setNetworkExecutor(new OkHttpNetworkExecutor())
                .setCacheStore( //配置缓存，保存到SD卡
                        new DiskCacheStore(this)
                )
        );

        Logger.setDebug(CommUtil.DEBUG);// 开启NoHttp的调试模式, 配置后可看到请求过程、日志和错误信息。
        Logger.setTag("NoHttpSample");// 设置NoHttp打印Log的tag。

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                CommUtil.createDimensXML("dimens.xml", 1, 1920);
//            }
//        }).start();
    }

    public long getLastTouchTime() {
        return lastTouchTime;
    }

    /**
     * 设置最后一次操作时间，一定时间未操作后弹出全屏广告
     *
     * @param lastTouchTime
     */
    public void setLastTouchTime(long lastTouchTime) {
        this.lastTouchTime = lastTouchTime;
        mHandler.removeMessages(0);
        mHandler.sendEmptyMessageDelayed(0, restInterval);
    }

    public static MyApplication getInstance() {
        return instance;
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            CommUtil.showToast("一分钟未操作，可以弹出视频广告了");
        }
    };
}
