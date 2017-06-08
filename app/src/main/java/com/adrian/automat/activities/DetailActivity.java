package com.adrian.automat.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.adrian.automat.R;
import com.adrian.automat.tools.CommUtil;
import com.adrian.automat.widget.NumChooserView;
import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends BaseActivity implements View.OnClickListener {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initVariables() {

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

        Glide.with(this).load("http://img2.ooopic.com/12/83/05/32bOOOPICab_202.jpg").into(mDrugImgIV);
    }

    @Override
    protected void loadData() {

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
}