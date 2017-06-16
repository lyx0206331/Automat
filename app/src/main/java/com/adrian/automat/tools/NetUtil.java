package com.adrian.automat.tools;

import android.app.Activity;
import android.content.Context;

import com.adrian.automat.application.MyApplication;
import com.adrian.automat.pojo.request.LoginReq;
import com.alibaba.fastjson.JSONObject;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;

/**
 * Created by adrian on 17-6-15.
 */

public class NetUtil {

    private Activity activity;
    private HttpListener<JSONObject> jsonHttpListener;

    /**
     * 用来标记取消。
     */
    private Object object = new Object();

    /**
     * 请求队列。
     */
    private RequestQueue mQueue;

    public NetUtil(Activity activity, HttpListener<JSONObject> listener) {
        this.activity = activity;
        jsonHttpListener = listener;
        // 初始化请求队列，传入的参数是请求并发值。
        mQueue = NoHttp.newRequestQueue(1);
    }

    /**
     * 设备登录
     *
     * @param userName
     * @param pwd
     */
    public void loginDev(String userName, String pwd) {
        Request<JSONObject> req = new FastJsonRequest(Constants.LOGIN_URL, RequestMethod.POST);
        req.add("userName", userName);
        req.add("password", pwd);
        request(0, req, jsonHttpListener, false, true);
    }

    /**
     * 获取商品列表
     *
     * @param ordinal
     * @param goodsId
     * @param goodsTypeId
     * @param name
     */
    public void getGoodsList(String ordinal, int goodsId, int goodsTypeId, String name) {
        String url = Constants.GOODS_LIST_URL + "?token=" + MyApplication.getInstance().getLoginToken();
        CommUtil.logE("GOODSLIST", url);
        Request<JSONObject> req = new FastJsonRequest(url, RequestMethod.GET);
        req.add("ordinal", ordinal);
        req.add("goodsId", goodsId);
        req.add("goodsTypeId", goodsTypeId);
        req.add("name", name);
        request(1, req, jsonHttpListener, false, true);
    }

    /**
     * 发起请求。
     *
     * @param what      what.
     * @param request   请求对象。
     * @param callback  回调函数。
     * @param canCancel 是否能被用户取消。
     * @param isLoading 实现显示加载框。
     * @param <T>       想请求到的数据类型。
     */
    public <T> void request(int what, Request<JSONObject> request, HttpListener<JSONObject> callback, boolean canCancel, boolean
            isLoading) {
        request.setCancelSign(object);
        mQueue.add(what, request, new HttpResponseListener<>(activity, request, callback, canCancel, isLoading));
    }

    public void onDestroy() {
        // 和声明周期绑定，退出时取消这个队列中的所有请求，当然可以在你想取消的时候取消也可以，不一定和声明周期绑定。
        mQueue.cancelBySign(object);

        // 因为回调函数持有了activity，所以退出activity时请停止队列。
        mQueue.stop();
    }

    protected void cancelAll() {
        mQueue.cancelAll();
    }

    protected void cancelBySign(Object object) {
        mQueue.cancelBySign(object);
    }
}
