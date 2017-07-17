package com.adrian.automat.activities;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.adrian.automat.R;
import com.adrian.automat.adapters.AllDrugAdapter;
import com.adrian.automat.application.MyApplication;
import com.adrian.automat.pojo.GoodsTypeBean;
import com.adrian.automat.pojo.GoodsBean;
import com.adrian.automat.pojo.response.GoodsListResp;
import com.adrian.automat.pojo.response.GoodsTypesResp;
import com.adrian.automat.tools.CommUtil;
import com.adrian.automat.tools.Constants;
import com.adrian.automat.tools.HttpListener;
import com.adrian.automat.tools.NetUtil;
import com.adrian.automat.widget.FlowRadioGroup;
import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.rest.CacheMode;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;
import com.yanzhenjie.nohttp.rest.SimpleResponseListener;

import java.util.ArrayList;
import java.util.List;

public class AllDrugActivity extends BaseActivity implements HttpListener {

    private static final String TAG = "AllDrugActivity";

    private XBanner mBannerNet;
    private GridView mDrugsGV;
    private Button mBackBtn;
    private FlowRadioGroup mTypeFRG;
    private AllDrugAdapter mAdapter;
    private ImageButton mRefreshIB;

    private List<String> localImages;

    private NetUtil util;
    private int cutType = -1;   //当前药品类型，-1为所有药品

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initVariables() {
        util = new NetUtil(this, this);
    }

    @Override
    protected void initViews() {

        mBannerNet = (XBanner) findViewById(R.id.banner_1);
        mBackBtn = (Button) findViewById(R.id.btn_back);
        mTypeFRG = (FlowRadioGroup) findViewById(R.id.frg_type);
        mRefreshIB = (ImageButton) findViewById(R.id.ib_refresh);
        mRefreshIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataByType(cutType);
            }
        });

        RadioButton rb = new RadioButton(this);
        rb.setButtonDrawable(null);
        rb.setGravity(Gravity.CENTER);
        int left = (int) getResources().getDimension(R.dimen.dp20);
        int top = (int) getResources().getDimension(R.dimen.dp16);
        rb.setPadding(left * 2, top * 3 / 2, left * 2, top * 2);
        rb.setBackgroundResource(R.drawable.btn_right_bg_selector);
        rb.setText("所有");
        rb.setTextColor(getResources().getColor(R.color.normal));
        rb.setTextSize(12);
//        rb.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.drug_type_selector), null, null, null);
        mTypeFRG.addView(rb);
        rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    getDataByType(-1);
                }
            }
        });
        ((RadioButton) mTypeFRG.getChildAt(0)).setChecked(true);

        mDrugsGV = (GridView) findViewById(R.id.gv_drug);
        mAdapter = new AllDrugAdapter(this);
        mDrugsGV.setAdapter(mAdapter);

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
                GoodsBean item = (GoodsBean) adapter.getItem(position);
                if (item.getNowNum() == 0) {
                    CommUtil.showToast("此药品已售罄!");
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putInt(Constants.PARAM_GRIDID, item.getGridId());
                    bundle.putInt(Constants.PARAM_GOODSID, item.getGoodsId());
                    bundle.putInt(Constants.PARAM_GOODSTYPE, item.getGoodsTypeId());
                    startActivity(DetailActivity.class, bundle);
                }
            }
        });
    }

    @Override
    protected void loadData() {
        util.getGoodsTypeList(1, 1000);
//        getData();
    }

    private RequestQueue requestQueues;

    private void loadIcon(String iconUrl, final RadioButton rb) {
        //第一步：创建Nohttp请求对列（如果是本类使用的比较频繁，在onCreate的时候初始化一次就行了，这里是为了怕忘记这个步骤）
        requestQueues = NoHttp.newRequestQueue();
        //第二步：创建请求对象（url是请求路径， RequestMethod.POST是请求方式）
        final Request<Bitmap> imageRequest = NoHttp.createImageRequest(iconUrl);//这里 RequestMethod.GET可以不写（删除掉即可），默认的是Get方式请求
        //REQUEST_NETWORK_FAILED_READ_CACHE请求失败返回上次缓存的数据
        imageRequest.setCacheMode(CacheMode.REQUEST_NETWORK_FAILED_READ_CACHE);
        //第三步：加入到请求对列中，requestQueues.add()分别是请求列的请求标志，请求对象，监听回调
        requestQueues.add(4, imageRequest, new SimpleResponseListener<Bitmap>() {
            @Override//成功后的回调
            public void onSucceed(int i, Response<Bitmap> response) {
//                imageView.setImageBitmap(response.get());
                Bitmap bmp = response.get();
//                CommUtil.logE(TAG, "bmp w:" + bmp.getWidth() + " h:" + bmp.getHeight());
                BitmapDrawable db = new BitmapDrawable(Bitmap.createBitmap(bmp, 0, 0, 48, 48));
                rb.setCompoundDrawablesWithIntrinsicBounds(db, null, null, null);
            }

            @Override
            public void onFailed(int what, Response<Bitmap> response) {
                super.onFailed(what, response);
            }
        });
    }

    private void getDataByType(int type) {
//        showProgress("正在加载数据...");
        cutType = type;
        util.getGoodsList(null, -1, type, null);
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

    @Override
    protected void onDestroy() {
        util.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onSucceed(int what, Response response) {
//        hideProgress();
        String respStr = response.get().toString();
        switch (what) {
            case Constants.GOODS_TYPE_LIST_TAG:
                GoodsTypesResp resp = JSON.parseObject(respStr, GoodsTypesResp.class);
//                CommUtil.logE(TAG, resp.toString());
                List<GoodsTypeBean> types = resp.getData().getList();
                if (types == null || types.size() == 0) {
                    return;
                }
                for (final GoodsTypeBean type : types) {
                    RadioButton rb = new RadioButton(this);
                    rb.setButtonDrawable(null);
                    rb.setGravity(Gravity.CENTER);
                    int left = (int) getResources().getDimension(R.dimen.dp20);
                    int top = (int) getResources().getDimension(R.dimen.dp16);
                    rb.setPadding(left, top, left, top);
                    rb.setBackgroundResource(R.drawable.btn_right_bg_selector);
                    rb.setText(type.getName());
                    rb.setTextColor(getResources().getColor(R.color.normal));
                    rb.setTextSize(12);
//                    rb.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.drug_type_selector), null, null, null);
                    loadIcon(Constants.IMG_DOMAIN + "/" + type.getImg(), rb);
                    mTypeFRG.addView(rb);
                    rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                            CommUtil.logE(TAG, "isChecked:" + isChecked);
                            if (isChecked) {
                                getDataByType(type.getGoodsTypeId());
                            }
                        }
                    });
                }
//                ((RadioButton) mTypeFRG.getChildAt(0)).setChecked(true);
                break;
            case Constants.GOODS_LIST_TAG:
                GoodsListResp goodsListResp = JSON.parseObject(respStr, GoodsListResp.class);
//                CommUtil.logE("GOODS_COUNT", "goods count = " + goodsListResp.getData().size());
                CommUtil.logE(TAG, respStr);
                if (cutType == -1) {
                    MyApplication.getInstance().setAllGoodsList(goodsListResp.getData());
                }
                mAdapter.setData(goodsListResp.getData());
                break;
        }
    }

    @Override
    public void onFailed(int what, Response response) {
//        hideProgress();
        CommUtil.logE(TAG, "数据请求失败!");
    }
}
