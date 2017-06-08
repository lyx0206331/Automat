package com.adrian.automat.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.adrian.automat.R;
import com.adrian.automat.adapters.AllDrugAdapter;
import com.adrian.automat.pojo.DrugSimpleInfo;
import com.adrian.automat.tools.CommUtil;
import com.adrian.automat.widget.FlowRadioGroup;
import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

public class AllDrugActivity extends BaseActivity {

    private XBanner mBannerNet;
    private GridView mDrugsGV;
    private Button mBackBtn;
    private FlowRadioGroup mTypeFRG;
    private AllDrugAdapter mAdapter;

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

        mBannerNet = (XBanner) findViewById(R.id.banner_1);
        mBackBtn = (Button) findViewById(R.id.btn_back);
        mTypeFRG = (FlowRadioGroup) findViewById(R.id.frg_type);
//        mTypeFRG.check(R.id.rb_type_0);

//        mTypeFRG = new FlowRadioGroup(this);
        for (int i = 0; i < 8; i++) {
            RadioButton rb = new RadioButton(this);
            rb.setButtonDrawable(null);
            rb.setGravity(Gravity.CENTER);
            rb.setPadding(14, 12, 14, 12);
            rb.setBackgroundResource(R.drawable.btn_right_bg_selector);
            rb.setText("类别" + i);
            rb.setTextColor(getResources().getColor(R.color.normal));
            rb.setTextSize(12);
            rb.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.drug_type_selector), null, null, null);
            mTypeFRG.addView(rb);
        }
        ((RadioButton)mTypeFRG.getChildAt(0)).setChecked(true);

        mDrugsGV = (GridView) findViewById(R.id.gv_drug);
        mAdapter = new AllDrugAdapter(this);
        mDrugsGV.setAdapter(mAdapter);

        List drugs = new ArrayList();
        for (int i = 0; i< 20; i++) {
            drugs.add(new DrugSimpleInfo(i + "", "http://pic.baike.soso.com/p/20111017/bki-20111017223041-848836407.jpg", "药品名称" + i, 0, 12.6f, i%3, 4));
        }
        mAdapter.setData(drugs);

        localImages = new ArrayList<>();
        localImages.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496407271033&di=fa1deaabe2b4792240b4dde3fbcaacda&imgtype=0&src=http%3A%2F%2Fpic.90sjimg.com%2Fback_pic%2F00%2F04%2F13%2F75%2F8db5a6d5cc09a89f6dc6c9d8bf2e3770.jpg");
        localImages.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496408046885&di=46e1d41491d7df0cb2efea1e4a7ddf8c&imgtype=0&src=http%3A%2F%2Fpic.90sjimg.com%2Fback_pic%2F00%2F04%2F13%2F75%2F266e84bc0e5a43b8e39d397f648162c4.jpg");
        localImages.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496408140378&di=75ea602661479baa157d201362ee6c66&imgtype=0&src=http%3A%2F%2Fpic.90sjimg.com%2Fback_pic%2Fqk%2Fback_origin_pic%2F00%2F01%2F49%2F15068f16288b124067042212251d0d63.jpg");
        localImages.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496408237185&di=192b74ab6f1ff401448e3f46861c9119&imgtype=0&src=http%3A%2F%2Fpic.90sjimg.com%2Fback_pic%2F00%2F04%2F13%2F75%2F5412693453409bc2a8db36a9f70fc3b6.jpg");
        mBannerNet.setData(localImages, null);
        mBannerNet.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, View view, int position) {
                Glide.with(AllDrugActivity.this).load(localImages.get(position)).into((ImageView) view);
            }
        });
        mBannerNet.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, int position) {
                CommUtil.showToast("点击了第" + position + "张图片");
            }
        });

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mDrugsGV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AllDrugAdapter adapter = (AllDrugAdapter) parent.getAdapter();
                DrugSimpleInfo item = (DrugSimpleInfo) adapter.getItem(position);
                if (item.getStore() == 0) {
                    CommUtil.showToast("此药品已售罄!");
                } else {
                    startActivity(DetailActivity.class);
                }
            }
        });
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_all_drug;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBannerNet.startAutoPlay();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mBannerNet.stopAutoPlay();
    }
}
