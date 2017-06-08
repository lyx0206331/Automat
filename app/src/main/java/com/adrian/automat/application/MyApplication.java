package com.adrian.automat.application;

import android.app.Application;

import com.adrian.automat.tools.CommUtil;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.URLConnectionNetworkExecutor;

/**
 * Created by ranqing on 2017/6/2.
 */

public class MyApplication extends Application {

    private static MyApplication instance;

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
        );

        Logger.setDebug(CommUtil.DEBUG);// 开启NoHttp的调试模式, 配置后可看到请求过程、日志和错误信息。
        Logger.setTag("NoHttpSample");// 设置NoHttp打印Log的tag。
    }

    public static MyApplication getInstance() {
        return instance;
    }
}
