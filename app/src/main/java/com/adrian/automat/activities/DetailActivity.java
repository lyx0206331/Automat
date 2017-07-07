package com.adrian.automat.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.adrian.automat.R;
import com.adrian.automat.pojo.response.GoodsBean;
import com.adrian.automat.pojo.response.GoodsListResp;
import com.adrian.automat.pojo.response.GoodsTypesResp;
import com.adrian.automat.tools.CommUtil;
import com.adrian.automat.tools.Constants;
import com.adrian.automat.tools.HttpListener;
import com.adrian.automat.tools.NetUtil;
import com.adrian.automat.widget.NumChooserView;
import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends BaseActivity implements View.OnClickListener, HttpListener, NumChooserView.IValueChangedListener {

    private XBanner banner;
    private Button mBackBtn;
    private Button mMemberBtn;
    private ImageView mDrugImgIV;
    private TextView mDrugNameTV;
    private TextView mDrugPriceTV;
    private TextView mStoreNumTV;
    private TextView mTotalPriceTV;
    private NumChooserView mNumChooserView;
    private Button mZfbBtn;
    private Button mWXBtn;
    private Button mUnionBtn;
    private TextView mIntroTV;

    private List<String> localImages;

    private NetUtil util;

    private int goodsId;
    private int goodsType;
    private String ordinal;

    private float price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initVariables() {
        Bundle bundle = getIntent().getExtras();
        goodsId = bundle.getInt(Constants.PARAM_GOODSID);
        goodsType = bundle.getInt(Constants.PARAM_GOODSTYPE);
        ordinal = bundle.getString(Constants.PARAM_ORDINAL);

        util = new NetUtil(this, this);
        if (!CommUtil.getWifiStatus(this)) {
            CommUtil.showToast(R.string.connect_error);
        }
    }

    @Override
    protected void initViews() {
        banner = (XBanner) findViewById(R.id.banner);
        mBackBtn = (Button) findViewById(R.id.btn_back);
        mMemberBtn = (Button) findViewById(R.id.btn_member);
        mDrugImgIV = (ImageView) findViewById(R.id.iv_detail_img);
        mDrugNameTV = (TextView) findViewById(R.id.tv_drug_name);
        mDrugPriceTV = (TextView) findViewById(R.id.tv_drug_price);
        mStoreNumTV = (TextView) findViewById(R.id.tv_store_num);
        mTotalPriceTV = (TextView) findViewById(R.id.tv_total_price);
        mNumChooserView = (NumChooserView) findViewById(R.id.number_chooser);
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
        mNumChooserView.setListener(this);

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
        util.getGoodsList(ordinal, goodsId, goodsType, null);
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
                break;
            case R.id.btn_wx:
                break;
            case R.id.btn_union:
                break;
        }
    }

    @Override
    public void onSucceed(int what, Response response) {
        String respStr = response.get().toString();
        switch (what) {
            case Constants.GOODS_LIST_TAG:
                GoodsListResp goodsListResp = JSON.parseObject(respStr, GoodsListResp.class);
//                CommUtil.logE("GOODS_COUNT", "goods count = " + goodsListResp.getData().size());
//                CommUtil.logE("GOODS", goodsListResp.getData().get(0).toString());
                if (goodsListResp.getData() != null && goodsListResp.getData().size() > 0) {
                    GoodsBean bean = goodsListResp.getData().get(0);
                    Glide.with(this).load(Constants.IMG_DOMAIN + "/" + bean.getImg()).into(mDrugImgIV);
                    mDrugNameTV.setText(bean.getName());
                    price = bean.getPrice();
                    mDrugPriceTV.setText("￥" + price);
                    mIntroTV.setText("规格:" + bean.getStandard() + "\n生产商:" + bean.getFactory() + "\n简介:" + bean.getSummary());
                    mStoreNumTV.setText("库存:" + bean.getNowNum());
                    mNumChooserView.setMax(bean.getNowNum());
                    mTotalPriceTV.setText("￥" + price);
                }
                break;
        }
    }

    @Override
    public void onFailed(int what, Response response) {

    }

    @Override
    public void valueChanged(int value) {
        mTotalPriceTV.setText("￥" + value * price);
    }
}
