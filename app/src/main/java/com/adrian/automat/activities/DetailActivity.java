package com.adrian.automat.activities;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.adrian.automat.R;
import com.adrian.automat.pojo.GoodsDetailBean;
import com.adrian.automat.pojo.response.GoodsDetailResp;
import com.adrian.automat.pojo.response.OrderCreateResp;
import com.adrian.automat.pojo.response.OrderInfoResp;
import com.adrian.automat.pojo.response.PayTypeResp;
import com.adrian.automat.tools.CommUtil;
import com.adrian.automat.tools.Constants;
import com.adrian.automat.tools.HttpListener;
import com.adrian.automat.tools.NetUtil;
import com.adrian.automat.widget.QRCodeDialog;
import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends BaseActivity implements View.OnClickListener, HttpListener {

    private XBanner banner;
    private Button mBackBtn;
    private Button mMemberBtn;
    private ImageView mDrugImgIV;
    private TextView mDrugNameTV;
    private TextView mDrugPriceTV;
    private TextView mUnitTV;
    private TextView mStandardTV;
    private Button mZfbBtn;
    private Button mWXBtn;
    private Button mUnionBtn;
    private TextView mIntroTV;

    private QRCodeDialog qrCodeDialog;

    private List<String> localImages;

    private NetUtil util;

    private int goodsId;
    private int goodsType;
    private int gridId;

    private int userId = -1;
    private GoodsDetailBean detailBean;
    private String payType;
    private String orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initVariables() {
        Bundle bundle = getIntent().getExtras();
        goodsId = bundle.getInt(Constants.PARAM_GOODSID);
        goodsType = bundle.getInt(Constants.PARAM_GOODSTYPE);
        gridId = bundle.getInt(Constants.PARAM_GRIDID);

        util = new NetUtil(this, this);
    }

    @Override
    protected void initViews() {
        banner = (XBanner) findViewById(R.id.banner);
        mBackBtn = (Button) findViewById(R.id.btn_back);
        mMemberBtn = (Button) findViewById(R.id.btn_member);
        mDrugImgIV = (ImageView) findViewById(R.id.iv_detail_img);
        mDrugNameTV = (TextView) findViewById(R.id.tv_drug_name);
        mDrugPriceTV = (TextView) findViewById(R.id.tv_drug_price);
        mUnitTV = (TextView) findViewById(R.id.tv_unit);
        mStandardTV = (TextView) findViewById(R.id.tv_standard);
        mZfbBtn = (Button) findViewById(R.id.btn_zfb);
        mWXBtn = (Button) findViewById(R.id.btn_wx);
        mUnionBtn = (Button) findViewById(R.id.btn_union);
        mIntroTV = (TextView) findViewById(R.id.tv_intro);
        mIntroTV.setMovementMethod(ScrollingMovementMethod.getInstance());

        mBackBtn.setOnClickListener(this);
        mMemberBtn.setOnClickListener(this);
        mZfbBtn.setOnClickListener(this);
        mWXBtn.setOnClickListener(this);
        mUnionBtn.setOnClickListener(this);

        localImages = new ArrayList<>();
        localImages.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496407271033&di=fa1deaabe2b4792240b4dde3fbcaacda&imgtype=0&src=http%3A%2F%2Fpic.90sjimg.com%2Fback_pic%2F00%2F04%2F13%2F75%2F8db5a6d5cc09a89f6dc6c9d8bf2e3770.jpg");
        localImages.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496408046885&di=46e1d41491d7df0cb2efea1e4a7ddf8c&imgtype=0&src=http%3A%2F%2Fpic.90sjimg.com%2Fback_pic%2F00%2F04%2F13%2F75%2F266e84bc0e5a43b8e39d397f648162c4.jpg");
        localImages.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496408140378&di=75ea602661479baa157d201362ee6c66&imgtype=0&src=http%3A%2F%2Fpic.90sjimg.com%2Fback_pic%2Fqk%2Fback_origin_pic%2F00%2F01%2F49%2F15068f16288b124067042212251d0d63.jpg");
        localImages.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496408237185&di=192b74ab6f1ff401448e3f46861c9119&imgtype=0&src=http%3A%2F%2Fpic.90sjimg.com%2Fback_pic%2F00%2F04%2F13%2F75%2F5412693453409bc2a8db36a9f70fc3b6.jpg");
        banner.setData(localImages, null);
        banner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, View view, int position) {
                Glide.with(DetailActivity.this).load(localImages.get(position)).into((ImageView) view);
            }
        });
        banner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, int position) {
                CommUtil.showToast("点击了第" + position + "张图片");
            }
        });

//        Glide.with(this).load("http://img2.ooopic.com/12/83/05/32bOOOPICab_202.jpg").into(mDrugImgIV);
    }

    @Override
    protected void loadData() {
//        util.getGoodsList(ordinal, goodsId, goodsType, null);
        util.getGoodsInfo(goodsId);
    }

    @Override
    protected void onResume() {
        super.onResume();
        banner.startAutoPlay();
    }

    @Override
    protected void onPause() {
        super.onPause();
        banner.stopAutoPlay();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_detail;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_member:
                break;
            case R.id.btn_zfb:
                createOrder("alipay");
                break;
            case R.id.btn_wx:
                createOrder("weixin");
                break;
            case R.id.btn_union:
//                createOrder("union");
                break;
        }
    }

    private void createOrder(String payType) {
        if (detailBean != null) {
            this.payType = payType;
            util.createOrder(userId, gridId);
        }
    }

    @Override
    public void onSucceed(int what, Response response) {
        String respStr = response.get().toString();
        CommUtil.logE("GOODSINFO", respStr);
        switch (what) {
            case Constants.GET_GOODS_INFO_TAG:
                GoodsDetailResp goodsDetailResp = JSON.parseObject(respStr, GoodsDetailResp.class);
//                CommUtil.logE("GOODSINFO", goodsDetailResp.toString());
                if (goodsDetailResp != null && goodsDetailResp.getCode() == 0) {
                    detailBean = goodsDetailResp.getData();
                    Glide.with(this).load(Constants.IMG_DOMAIN + "/" + detailBean.getImg()).into(mDrugImgIV);
                    mDrugNameTV.setText(detailBean.getName());
                    mDrugPriceTV.setText("￥" + detailBean.getPrice());
                    mUnitTV.setText(detailBean.getUnit());
                    mStandardTV.setText(detailBean.getStandard());
                    mIntroTV.setText("生产商:" + detailBean.getFactory() + "\n简介:" + detailBean.getSummary());
                } else {
                    CommUtil.showToast(goodsDetailResp.getMsg());
                }
                break;
            case Constants.CREATE_ORDER_TAG:
                OrderCreateResp orderCreateResp = JSON.parseObject(respStr, OrderCreateResp.class);
                if (orderCreateResp != null && orderCreateResp.getCode() == 0) {
                    orderId = orderCreateResp.getData();
//                    util.getOrderInfo(orderCreateResp.getData());
//                    util.choosePayType(orderCreateResp.getData(), payType);
                    util.requestTakeDelivery(orderCreateResp.getData());
                } else {
                    CommUtil.showToast(orderCreateResp.getMsg());
                }
                break;
            case Constants.CANCEL_ORDER_TAG:
                try {
                    JSONObject json = new JSONObject(respStr);
                    if (json.optInt("code") == 0 && json.optBoolean("data")) {
                        orderId = null;
                        CommUtil.showToast("订单已取消!");
                    } else {
                        CommUtil.showToast(json.optString("msg"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                OrderCancelResp orderCancelResp = JSON.parseObject(respStr, OrderCancelResp.class);
//                if (orderCancelResp != null && orderCancelResp.isData()) {
//                    orderId = null;
//                    CommUtil.showToast("成功取消订单!");
//                } else {
//                    CommUtil.showToast("取消订单失败!");
//                }
                break;
            case Constants.GET_ORDER_INFO_TAG:
                OrderInfoResp orderInfoResp = JSON.parseObject(respStr, OrderInfoResp.class);
                if (orderInfoResp != null && orderInfoResp.getCode() == 0) {
                    util.cancelOrder(orderInfoResp.getData().getOrderId());
                } else {
                    CommUtil.showToast(orderInfoResp.getMsg());
                }
                break;
            case Constants.CHOOSE_PAY_TYPE_TAG:
                PayTypeResp payTypeResp = JSON.parseObject(respStr, PayTypeResp.class);
                if (payTypeResp != null) {
                    String qrCodeUrl = payTypeResp.getData();
                }
                break;
            case Constants.REQUEST_TAKE_DELIVERY_TAG:
                try {
                    JSONObject json = new JSONObject(respStr);
                    if (json.optInt("code") == 0 && json.optBoolean("data")) {
                        util.takeDelivery(orderId);
                    } else {
                        CommUtil.showToast(json.optString("msg"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                ReqTakeDeliveryResp reqTakeDeliveryResp = JSON.parseObject(respStr, ReqTakeDeliveryResp.class);
//                if (reqTakeDeliveryResp != null && reqTakeDeliveryResp.getCode() == 0) {
//                    CommUtil.logE("REQ_TAKE_DELIVERY", "请求出货");
//                    util.takeDelivery(orderId);
//                } else {
//                    CommUtil.showToast(reqTakeDeliveryResp.getMsg());
//                }
                break;
            case Constants.TAKE_DELIVERY_TAG:
                try {
                    JSONObject json = new JSONObject(respStr);
                    if (json.optInt("code") == 0 && json.optBoolean("data")) {
                        CommUtil.showToast("出货成功!");
                    } else {
                        CommUtil.showToast(json.optString("msg"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                TakeDeliveryResp takeDeliveryResp = JSON.parseObject(respStr, TakeDeliveryResp.class);
//                if (takeDeliveryResp != null && takeDeliveryResp.isData()) {
//                    CommUtil.showToast("出货成功!");
//                } else {
//                    CommUtil.showToast(takeDeliveryResp.getMsg());
//                }
                break;
        }
    }

    @Override
    public void onFailed(int what, Response response) {

    }

}
