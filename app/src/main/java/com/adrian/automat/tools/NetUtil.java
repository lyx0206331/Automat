package com.adrian.automat.tools;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.adrian.automat.R;
import com.adrian.automat.application.MyApplication;
import com.adrian.automat.pojo.PathwayDataBean;
import com.adrian.automat.pojo.request.LoginReq;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        if (CommUtil.getNetworkStatus(MyApplication.getInstance()) == -1) {
            CommUtil.showToast(R.string.connect_error);
        }
    }

    /**
     * 设备登录
     *
     * @param userName
     * @param pwd
     */
    public void loginDev(String userName, String pwd) {
        if (CommUtil.getNetworkStatus(MyApplication.getInstance()) == -1) {
            CommUtil.showToast(R.string.error_please_check_network);
            return;
        }
        Request<JSONObject> req = new FastJsonRequest(Constants.LOGIN_URL, RequestMethod.POST);
        req.add("userName", userName);
        req.add("password", pwd);
        request(Constants.LOGIN_TAG, req, jsonHttpListener, false, true);
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
        if (CommUtil.getNetworkStatus(MyApplication.getInstance()) == -1) {
            CommUtil.showToast(R.string.error_please_check_network);
            return;
        }
        String url = Constants.GOODS_LIST_URL + "?token=" + MyApplication.getInstance().getLoginToken();
        CommUtil.logE("GOODSLIST", url);
        Request<JSONObject> req = new FastJsonRequest(url, RequestMethod.GET);
        if (!TextUtils.isEmpty(ordinal)) {
            req.add("ordinal", ordinal);
        }
        if (goodsId != -1) {
            req.add("goodsId", goodsId);
        }
        if (goodsTypeId != -1) {
            req.add("goodsTypeId", goodsTypeId);
        }
        if (!TextUtils.isEmpty(name)) {
            req.add("name", name);
        }
        request(Constants.GOODS_LIST_TAG, req, jsonHttpListener, false, true);
    }

    /**
     * 获取商品类型
     *
     * @param pageNum
     * @param pageSize
     */
    public void getGoodsTypeList(int pageNum, int pageSize) {
        if (CommUtil.getNetworkStatus(MyApplication.getInstance()) == -1) {
            CommUtil.showToast(R.string.error_please_check_network);
            return;
        }
        String url = Constants.GOODS_TYPE_LIST_URL + "?token=" + MyApplication.getInstance().getLoginToken();
        CommUtil.logE("GOODSTYPELIST", url);
        Request<JSONObject> req = new FastJsonRequest(url, RequestMethod.GET);
        req.add("pageNum", pageNum);
        req.add("pageSize", pageSize);
        request(Constants.GOODS_TYPE_LIST_TAG, req, jsonHttpListener, false, true);
    }

    /**
     * 获取售货机详细信息
     */
    public void getMachineInfo() {
        if (CommUtil.getNetworkStatus(MyApplication.getInstance()) == -1) {
            CommUtil.showToast(R.string.error_please_check_network);
            return;
        }
        String url = Constants.MACHINE_INFO_URL + "?token=" + MyApplication.getInstance().getLoginToken();
        CommUtil.logE("MACHINEINFO", url);
        Request<JSONObject> req = new FastJsonRequest(url, RequestMethod.GET);
        request(Constants.MACHINE_INFO_TAG, req, jsonHttpListener, false, true);
    }

    /**
     * 上报售货机信息
     *
     * @param values
     */
    public void reportMachineInfo(Map<String, Object> values) {
        if (CommUtil.getNetworkStatus(MyApplication.getInstance()) == -1) {
            CommUtil.showToast(R.string.error_please_check_network);
            return;
        }
        String url = Constants.MACHINE_REPORT_URL + "?token=" + MyApplication.getInstance().getLoginToken();
//        CommUtil.logE("MACHINEINFO", url);
        Request<JSONObject> req = new FastJsonRequest(url, RequestMethod.PUT);
        String vStr = JSON.toJSONString(values);
        CommUtil.logE("REPORT_INFO", vStr);
        req.setDefineRequestBodyForJson(vStr);
        request(Constants.MACHINE_REPORT_TAG, req, jsonHttpListener, false, true);
    }

    /**
     * 批量修改轨道当前商品数据及最大商品数量
     *
     * @param datas
     */
    public void modifyPathwayData(List<PathwayDataBean> datas) {
        if (CommUtil.getNetworkStatus(MyApplication.getInstance()) == -1) {
            CommUtil.showToast(R.string.error_please_check_network);
            return;
        }
        String url = Constants.MODIFY_PATHWAY_DATA_URL + "?token=" + MyApplication.getInstance().getLoginToken();
        CommUtil.logE("MACHINEINFO", url);
        Request<JSONObject> req = new FastJsonRequest(url, RequestMethod.POST);
        req.setDefineRequestBodyForJson(JSON.toJSONString(datas));
//        CommUtil.logE("MODIFY", JSON.toJSONString(datas));
        request(Constants.MODIFY_PATHWAY_DATA_TAG, req, jsonHttpListener, false, true);
    }

    /**
     * 获取商品信息
     *
     * @param goodsId
     */
    public void getGoodsInfo(@NonNull int goodsId) {
        if (CommUtil.getNetworkStatus(MyApplication.getInstance()) == -1) {
            CommUtil.showToast(R.string.error_please_check_network);
            return;
        }
        String url = Constants.GET_GOODS_INFO_URL + "?token=" + MyApplication.getInstance().getLoginToken();
        Request<JSONObject> req = new FastJsonRequest(url, RequestMethod.GET);
        req.add("id", goodsId);
        request(Constants.GET_GOODS_INFO_TAG, req, jsonHttpListener, false, true);
    }

    /**
     * 取消订单
     *
     * @param orderId
     */
    public void cancelOrder(@NonNull String orderId) {
        if (CommUtil.getNetworkStatus(MyApplication.getInstance()) == -1) {
            CommUtil.showToast(R.string.error_please_check_network);
            return;
        }
        String url = Constants.CANCEL_ORDER_URL + "?token=" + MyApplication.getInstance().getLoginToken();
        Request<JSONObject> req = new FastJsonRequest(url, RequestMethod.GET);
        req.add("orderId", orderId);
        request(Constants.CANCEL_ORDER_TAG, req, jsonHttpListener, false, true);
    }

    /**
     * 创建订单
     *
     * @param userId
     * @param gridId
     */
    public void createOrder(int userId, @NonNull int gridId) {
        if (CommUtil.getNetworkStatus(MyApplication.getInstance()) == -1) {
            CommUtil.showToast(R.string.error_please_check_network);
            return;
        }
        String url = Constants.CREATE_ORDER_URL + "?token=" + MyApplication.getInstance().getLoginToken();
        Request<JSONObject> req = new FastJsonRequest(url, RequestMethod.POST);
        if (userId != -1) {
            req.add("userId", userId);
        }
        req.add("gridId", gridId);
        request(Constants.CREATE_ORDER_TAG, req, jsonHttpListener, false, true);
    }

    /**
     * 获取订单信息
     *
     * @param orderId
     */
    public void getOrderInfo(String orderId) {
        if (CommUtil.getNetworkStatus(MyApplication.getInstance()) == -1) {
            CommUtil.showToast(R.string.error_please_check_network);
            return;
        }
        String url = Constants.GET_ORDER_INFO_URL + "?token=" + MyApplication.getInstance().getLoginToken();
        Request<JSONObject> req = new FastJsonRequest(url, RequestMethod.GET);
        req.add("id", orderId);
        request(Constants.GET_ORDER_INFO_TAG, req, jsonHttpListener, false, true);
    }

    /**
     * 选择支付类型
     *
     * @param orderId
     * @param payType 支付类型:alipay或weixin
     */
    public void choosePayType(@NonNull String orderId, @NonNull String payType) {
        if (CommUtil.getNetworkStatus(MyApplication.getInstance()) == -1) {
            CommUtil.showToast(R.string.error_please_check_network);
            return;
        }
        String url = Constants.CHOOSE_PAY_TYPE_URL + "?token=" + MyApplication.getInstance().getLoginToken();
        Request<JSONObject> req = new FastJsonRequest(url, RequestMethod.GET);
        req.add("orderId", orderId);
        req.add("payType", payType);
        request(Constants.CHOOSE_PAY_TYPE_TAG, req, jsonHttpListener, false, true);
    }

    /**
     * 请求出货
     *
     * @param orderId
     */
    public void requestTakeDelivery(@NonNull String orderId) {
        if (CommUtil.getNetworkStatus(MyApplication.getInstance()) == -1) {
            CommUtil.showToast(R.string.error_please_check_network);
            return;
        }
        String url = Constants.REQUEST_TAKE_DELIVERY_URL + "?token=" + MyApplication.getInstance().getLoginToken();
        Request<JSONObject> req = new FastJsonRequest(url, RequestMethod.GET);
        req.add("orderId", orderId);
        request(Constants.REQUEST_TAKE_DELIVERY_TAG, req, jsonHttpListener, false, true);
    }

    /**
     * 出货
     *
     * @param orderId
     */
    public void takeDelivery(@NonNull String orderId) {
        if (CommUtil.getNetworkStatus(MyApplication.getInstance()) == -1) {
            CommUtil.showToast(R.string.error_please_check_network);
            return;
        }
        String url = Constants.TAKE_DELIVERY_URL + "?token=" + MyApplication.getInstance().getLoginToken();
        Request<JSONObject> req = new FastJsonRequest(url, RequestMethod.GET);
        req.add("orderId", orderId);
        request(Constants.TAKE_DELIVERY_TAG, req, jsonHttpListener, false, true);
    }

    /**
     * 发起请求
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
