package com.adrian.automat.activities.fragments;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.Formatter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.adrian.automat.R;
import com.adrian.automat.activities.AllDrugActivity;
import com.adrian.automat.activities.DetailActivity;
import com.adrian.automat.activities.MapActivity;
import com.adrian.automat.pojo.response.GoodsListResp;
import com.adrian.automat.tools.CommUtil;
import com.adrian.automat.tools.HttpListener;
import com.adrian.automat.tools.NetUtil;
import com.alibaba.fastjson.JSON;
import com.amap.api.fence.GeoFence;
import com.amap.api.fence.GeoFenceListener;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.MyLocationStyle;
import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingFragment extends BaseFragment implements View.OnClickListener, HttpListener,
        LocationSource,
        AMapLocationListener {

    private static final String TAG = "ShoppingFragment";

    private XBanner banner;
    private Button mScanAttentionBtn;
    private Button mAllDrugBtn;
    private LinearLayout mRec0LL;
    private LinearLayout mRec1LL;
    private LinearLayout mRec2LL;
    private ImageView mRec0IV;
    private ImageView mRec1IV;
    private ImageView mRec2IV;
    private TextView mRecName0TV;
    private TextView mRecName1TV;
    private TextView mRecName2TV;
    private TextView mRecPrice0TV;
    private TextView mRecPrice1TV;
    private TextView mRecPrice2TV;
    private ImageButton mFullScreenIB;

    private AMapLocationClient mlocationClient;
    private LocationSource.OnLocationChangedListener mListener;
    private AMapLocationClientOption mLocationOption;

    private MapView mMapView;
    private AMap mAMap;

    private NetUtil util;

    private List<String> localImages;

    private String videoUrl = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";

    public ShoppingFragment() {
        // Required empty public constructor
        util = new NetUtil(getActivity(), this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping, container, false);
        initView(view);

        mMapView.onCreate(savedInstanceState);
        if (mAMap == null) {
            mAMap = mMapView.getMap();
            mAMap.getUiSettings().setRotateGesturesEnabled(false);
            mAMap.moveCamera(CameraUpdateFactory.zoomBy(6));
            setUpMap();
        }
        return view;
    }

    /**
     * 设置一些amap的属性
     */
    private void setUpMap() {
        mAMap.setLocationSource(this);// 设置定位监听
        mAMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
        // 自定义系统定位蓝点
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        // 自定义定位蓝点图标
        myLocationStyle.myLocationIcon(
                BitmapDescriptorFactory.fromResource(R.drawable.gps_point));
        // 自定义精度范围的圆形边框颜色
        myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0));
        // 自定义精度范围的圆形边框宽度
        myLocationStyle.strokeWidth(0);
        // 设置圆形的填充颜色
        myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));
        // 将自定义的 myLocationStyle 对象添加到地图上
        mAMap.setMyLocationStyle(myLocationStyle);
        mAMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        // 设置定位的类型为定位模式 ，可以由定位、跟随或地图根据面向方向旋转几种
        mAMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
    }

    @Override
    protected void lazyLoad() {

    }

    private void initView(View view) {
        banner = (XBanner) view.findViewById(R.id.banner);
        mScanAttentionBtn = (Button) view.findViewById(R.id.btn_scan_attention);
        mAllDrugBtn = (Button) view.findViewById(R.id.btn_all_drug);
        mMapView = (MapView) view.findViewById(R.id.map);
        mRec0LL = (LinearLayout) view.findViewById(R.id.ll_rec_0);
        mRec1LL = (LinearLayout) view.findViewById(R.id.ll_rec_1);
        mRec2LL = (LinearLayout) view.findViewById(R.id.ll_rec_2);
        mRec0IV = (ImageView) view.findViewById(R.id.iv_rec_0);
        mRec1IV = (ImageView) view.findViewById(R.id.iv_rec_1);
        mRec2IV = (ImageView) view.findViewById(R.id.iv_rec_2);
        mRecName0TV = (TextView) view.findViewById(R.id.tv_rec_name_0);
        mRecName1TV = (TextView) view.findViewById(R.id.tv_rec_name_1);
        mRecName2TV = (TextView) view.findViewById(R.id.tv_rec_name_2);
        mRecPrice0TV = (TextView) view.findViewById(R.id.tv_rec_price_0);
        mRecPrice1TV = (TextView) view.findViewById(R.id.tv_rec_price_1);
        mRecPrice2TV = (TextView) view.findViewById(R.id.tv_rec_price_2);
        mFullScreenIB = (ImageButton) view.findViewById(R.id.ib_fullscreen);

        localImages = new ArrayList<>();
        localImages.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496407271033&di=fa1deaabe2b4792240b4dde3fbcaacda&imgtype=0&src=http%3A%2F%2Fpic.90sjimg.com%2Fback_pic%2F00%2F04%2F13%2F75%2F8db5a6d5cc09a89f6dc6c9d8bf2e3770.jpg");
        localImages.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496408046885&di=46e1d41491d7df0cb2efea1e4a7ddf8c&imgtype=0&src=http%3A%2F%2Fpic.90sjimg.com%2Fback_pic%2F00%2F04%2F13%2F75%2F266e84bc0e5a43b8e39d397f648162c4.jpg");
        localImages.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496408140378&di=75ea602661479baa157d201362ee6c66&imgtype=0&src=http%3A%2F%2Fpic.90sjimg.com%2Fback_pic%2Fqk%2Fback_origin_pic%2F00%2F01%2F49%2F15068f16288b124067042212251d0d63.jpg");
        localImages.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496408237185&di=192b74ab6f1ff401448e3f46861c9119&imgtype=0&src=http%3A%2F%2Fpic.90sjimg.com%2Fback_pic%2F00%2F04%2F13%2F75%2F5412693453409bc2a8db36a9f70fc3b6.jpg");
        banner.setData(localImages, null);
        banner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, View view, int position) {
                Glide.with(getActivity()).load(localImages.get(position)).into((ImageView) view);
            }
        });
        banner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, int position) {
                CommUtil.showToast("点击了第" + position + "张图片");
            }
        });

        Glide.with(getActivity()).load("http://pic.baike.soso.com/p/20111017/bki-20111017223041-848836407.jpg").into(mRec0IV);
        Glide.with(getActivity()).load("http://img0.imgtn.bdimg.com/it/u=2890974895,2151831020&fm=214&gp=0.jpg").into(mRec1IV);
        Glide.with(getActivity()).load("http://img008.hc360.cn/g3/M08/FE/68/wKhQvlJWpKyEe6KUAAAAANIYEaE532.jpg..180x180.jpg").into(mRec2IV);
        mScanAttentionBtn.setOnClickListener(this);
        mAllDrugBtn.setOnClickListener(this);
        mMapView.setOnClickListener(this);
        mRec0LL.setOnClickListener(this);
        mRec1LL.setOnClickListener(this);
        mRec2LL.setOnClickListener(this);
        mFullScreenIB.setOnClickListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        banner.startAutoPlay();
        util.getGoodsList(null, 0, 0, null);
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        banner.stopAutoPlay();
        mMapView.onPause();
        deactivate();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();

        if (null != mlocationClient) {
            mlocationClient.onDestroy();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_scan_attention:
                CommUtil.showToast("扫码关注");
                break;
            case R.id.btn_all_drug:
//                CommUtil.showToast("所有药品");
                startActivity(AllDrugActivity.class);
                break;
            case R.id.ib_fullscreen:
//                CommUtil.showToast("推荐位");
                startActivity(MapActivity.class);
                break;
            case R.id.ll_rec_0:
//                CommUtil.showToast("推荐0");
                Bundle bundle0 = new Bundle();
                startActivity(DetailActivity.class, bundle0);
                break;
            case R.id.ll_rec_1:
//                CommUtil.showToast("推荐1");
                Bundle bundle1 = new Bundle();
                startActivity(DetailActivity.class, bundle1);
                break;
            case R.id.ll_rec_2:
//                CommUtil.showToast("推荐2");
                Bundle bundle2 = new Bundle();
                startActivity(DetailActivity.class, bundle2);
                break;
        }
    }

    @Override
    public void onSucceed(int what, Response response) {
        String respStr = response.get().toString();
        GoodsListResp resp = JSON.parseObject(respStr, GoodsListResp.class);
        CommUtil.logE(TAG, resp.toString());
    }

    @Override
    public void onFailed(int what, Response response) {

    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (mListener != null && amapLocation != null) {
            if (amapLocation != null && amapLocation.getErrorCode() == 0) {
//                tvResult.setVisibility(View.GONE);
                mListener.onLocationChanged(amapLocation);// 显示系统小蓝点
            } else {
                String errText = "定位失败," + amapLocation.getErrorCode() + ": "
                        + amapLocation.getErrorInfo();
                Log.e("AmapErr", errText);
                CommUtil.showToast(errText);
//                tvResult.setVisibility(View.VISIBLE);
//                tvResult.setText(errText);
            }
        }
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
        if (mlocationClient == null) {
            mlocationClient = new AMapLocationClient(getContext());
            mLocationOption = new AMapLocationClientOption();
            // 设置定位监听
            mlocationClient.setLocationListener(this);
            // 设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            // 只是为了获取当前位置，所以设置为单次定位
            mLocationOption.setOnceLocation(true);
            // 设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            mlocationClient.startLocation();
        }
    }

    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }
}
