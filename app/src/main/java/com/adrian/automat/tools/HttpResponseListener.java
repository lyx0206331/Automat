/*
 * Copyright 2015 Yan Zhenjie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.adrian.automat.tools;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import com.adrian.automat.R;
import com.adrian.automat.widget.WaitDialog;
import com.alibaba.fastjson.JSONObject;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.error.NetworkError;
import com.yanzhenjie.nohttp.error.NotFoundCacheError;
import com.yanzhenjie.nohttp.error.TimeoutError;
import com.yanzhenjie.nohttp.error.URLError;
import com.yanzhenjie.nohttp.error.UnKnownHostError;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

/**
 * Created in Nov 4, 2015 12:02:55 PM.
 *
 * @author Yan Zhenjie.
 */
public class HttpResponseListener<T> implements OnResponseListener<JSONObject> {

    private static final String TAG = "NET_UTIL";

    private Activity mActivity;

    /**
     * Dialog.
     */
    private WaitDialog mWaitDialog;
    /**
     * Request.
     */
    private Request<JSONObject> mRequest;
    /**
     * 结果回调.
     */
    private HttpListener<JSONObject> callback;

    /**
     * @param activity     context用来实例化dialog.
     * @param request      请求对象.
     * @param httpCallback 回调对象.
     * @param canCancel    是否允许用户取消请求.
     * @param isLoading    是否显示dialog.
     */
    public HttpResponseListener(Activity activity, Request<JSONObject> request, HttpListener<JSONObject> httpCallback, boolean
            canCancel, boolean isLoading) {
        this.mActivity = activity;
        this.mRequest = request;
        if (activity != null && isLoading) {
            mWaitDialog = new WaitDialog(activity);
            mWaitDialog.setCancelable(canCancel);
            mWaitDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    mRequest.cancel();
                }
            });
        }
        callback = httpCallback;
    }

    @Override
    public void onStart(int what) {
        CommUtil.logE(TAG, "start " + what);
        if (mWaitDialog != null && !mActivity.isFinishing() && !mWaitDialog.isShowing()) {
            mWaitDialog.show();
        }
    }

    /**
     * 成功回调.
     */
    @Override
    public void onSucceed(int what, Response<JSONObject> response) {
        if (callback != null) {
            // 这里判断一下http响应码，这个响应码问下你们的服务端你们的状态有几种，一般是200成功。
            // w3c标准http响应码：http://www.w3school.com.cn/tags/html_ref_httpmessages.asp

            callback.onSucceed(what, response);
        }
    }

    /**
     * 失败回调.
     */
    @Override
    public void onFailed(int what, Response<JSONObject> response) {
        Exception exception = response.getException();
        if (exception instanceof NetworkError) {// 网络不好
            CommUtil.showToast(R.string.error_please_check_network);
        } else if (exception instanceof TimeoutError) {// 请求超时
            CommUtil.showToast(R.string.error_timeout);
        } else if (exception instanceof UnKnownHostError) {// 找不到服务器
            CommUtil.showToast(R.string.error_not_found_server);
        } else if (exception instanceof URLError) {// URL是错的
            CommUtil.showToast(R.string.error_url_error);
        } else if (exception instanceof NotFoundCacheError) {
            // 这个异常只会在仅仅查找缓存时没有找到缓存时返回
            CommUtil.showToast(R.string.error_not_found_cache);
        } else {
            CommUtil.showToast(R.string.error_unknow);
        }
        Logger.e("错误：" + exception.getMessage());
        if (callback != null)
            callback.onFailed(what, response);
    }

    @Override
    public void onFinish(int what) {
        CommUtil.logE(TAG, "finish " + what);
        if (mWaitDialog != null && mWaitDialog.isShowing()) {
            mWaitDialog.dismiss();
        }
    }

}
