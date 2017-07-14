package com.adrian.automat.activities.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adrian.automat.R;
import com.adrian.automat.activities.AllDrugActivity;
import com.adrian.automat.activities.DetailActivity;
import com.adrian.automat.activities.MainActivity;
import com.adrian.automat.activities.MapActivity;
import com.adrian.automat.pojo.GoodsBean;
import com.adrian.automat.tools.CommUtil;
import com.adrian.automat.tools.Constants;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingFragment extends BaseFragment implements View.OnClickListener,
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

    private List<String> localImages;
    private List<GoodsBean> recList = new ArrayList<>();

    private String videoUrl = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";

    public ShoppingFragment() {
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
//        util.getGoodsList(null, -1, -1, null);
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
                if (recList.size() > 0) {
                    Bundle bundle0 = new Bundle();
                    bundle0.putInt(Constants.PARAM_GOODSID, recList.get(0).getGoodsId());
                    bundle0.putInt(Constants.PARAM_GOODSTYPE, recList.get(0).getGoodsTypeId());
                    bundle0.putInt(Constants.PARAM_GRIDID, recList.get(0).getGridId());
                    startActivity(DetailActivity.class, bundle0);
                }
                break;
            case R.id.ll_rec_1:
//                CommUtil.showToast("推荐1");
                if (recList.size() > 1) {
                    Bundle bundle1 = new Bundle();
                    bundle1.putInt(Constants.PARAM_GOODSID, recList.get(1).getGoodsId());
                    bundle1.putInt(Constants.PARAM_GOODSTYPE, recList.get(1).getGoodsTypeId());
                    bundle1.putInt(Constants.PARAM_GRIDID, recList.get(1).getGridId());
                    startActivity(DetailActivity.class, bundle1);
                }
                break;
            case R.id.ll_rec_2:
//                CommUtil.showToast("推荐2");
                if (recList.size() > 2) {
                    Bundle bundle2 = new Bundle();
                    bundle2.putInt(Constants.PARAM_GOODSID, recList.get(2).getGoodsId());
                    bundle2.putInt(Constants.PARAM_GOODSTYPE, recList.get(2).getGoodsTypeId());
                    bundle2.putInt(Constants.PARAM_GRIDID, recList.get(2).getGridId());
                    startActivity(DetailActivity.class, bundle2);
                }
                break;
        }
    }

    private List<GoodsBean> sortGoods(List<GoodsBean> list) {
//        for (GoodsBean bean :
//                list) {
//            bean.setWeight(((int) (Math.random() * 10)));
//        }
//        for (GoodsBean bean : list) {
//            CommUtil.logE(TAG, "befor weight:" + bean.getWeight());
//        }
        Collections.sort(list);
        for (GoodsBean bean : list) {
            CommUtil.logE(TAG, "after sort:" + bean.toString());
        }
        return list;
    }

    public void setData(List<GoodsBean> data) {
        recList = sortGoods(data);

        int count = recList.size();
        switch (count) {
            case 0:
                mRec0LL.setVisibility(View.INVISIBLE);
                mRec1LL.setVisibility(View.INVISIBLE);
                mRec2LL.setVisibility(View.INVISIBLE);
                break;
            case 1:
                mRec0LL.setVisibility(View.VISIBLE);
                mRec1LL.setVisibility(View.INVISIBLE);
                mRec2LL.setVisibility(View.INVISIBLE);
                mRecName0TV.setText(recList.get(0).getName());
                mRecPrice0TV.setText("￥" + recList.get(0).getPrice());
                if (TextUtils.isEmpty(recList.get(0).getImg())) {
                    mRec0IV.setImageResource(R.drawable.ic_img_failed);
                } else {
                    Glide.with(getActivity()).load(Constants.IMG_DOMAIN + "/" + recList.get(0).getImg()).into(mRec0IV).onLoadFailed(getResources().getDrawable(R.drawable.ic_img_failed));
                }
                break;
            case 2:
                mRec0LL.setVisibility(View.VISIBLE);
                mRec1LL.setVisibility(View.VISIBLE);
                mRec2LL.setVisibility(View.INVISIBLE);
                mRecName0TV.setText(recList.get(0).getName());
                mRecPrice0TV.setText("￥" + recList.get(0).getPrice());
                mRecName1TV.setText(recList.get(1).getName());
                mRecPrice1TV.setText("￥" + recList.get(1).getPrice());
                if (TextUtils.isEmpty(recList.get(0).getImg())) {
                    mRec0IV.setImageResource(R.drawable.ic_img_failed);
                } else {
                    Glide.with(getActivity()).load(Constants.IMG_DOMAIN + "/" + recList.get(0).getImg()).into(mRec0IV).onLoadFailed(getResources().getDrawable(R.drawable.ic_img_failed));
                }
                if (TextUtils.isEmpty(recList.get(1).getImg())) {
                    mRec1IV.setImageResource(R.drawable.ic_img_failed);
                } else {
                    Glide.with(getActivity()).load(Constants.IMG_DOMAIN + "/" + recList.get(1).getImg()).into(mRec1IV).onLoadFailed(getResources().getDrawable(R.drawable.ic_img_failed));
                }
//                Glide.with(getActivity()).load(Constants.IMG_DOMAIN + "/" + data.get(1).getImg()).into(mRec1IV)/*.onLoadFailed(getResources().getDrawable(R.mipmap.ic_launcher))*/;
                break;
            default:
                mRec0LL.setVisibility(View.VISIBLE);
                mRec1LL.setVisibility(View.VISIBLE);
                mRec2LL.setVisibility(View.VISIBLE);
                mRecName0TV.setText(recList.get(0).getName());
                mRecPrice0TV.setText("￥" + recList.get(0).getPrice());
                mRecName1TV.setText(recList.get(1).getName());
                mRecPrice1TV.setText("￥" + recList.get(1).getPrice());
                mRecName2TV.setText(recList.get(2).getName());
                mRecPrice2TV.setText("￥" + recList.get(2).getPrice());
                if (TextUtils.isEmpty(recList.get(0).getImg())) {
                    CommUtil.logE(TAG, "rec0 img is null");
                    mRec0IV.setImageResource(R.drawable.ic_img_failed);
                } else {
                    CommUtil.logE(TAG, "rec0 img is " + recList.get(0).getImg());
                    Glide.with(getActivity()).load(Constants.IMG_DOMAIN + "/" + recList.get(0).getImg()).into(mRec0IV);
                }
                if (TextUtils.isEmpty(recList.get(1).getImg())) {
                    CommUtil.logE(TAG, "rec1 img is null");
                    mRec1IV.setImageResource(R.drawable.ic_img_failed);
                } else {
                    CommUtil.logE(TAG, "rec1 img is " + recList.get(1).getImg());
                    Glide.with(getActivity()).load(Constants.IMG_DOMAIN + "/" + recList.get(1).getImg()).into(mRec1IV);
                }
                if (TextUtils.isEmpty(recList.get(2).getImg())) {
                    CommUtil.logE(TAG, "rec2 img is null");
                    mRec2IV.setImageResource(R.drawable.ic_img_failed);
                } else {
                    CommUtil.logE(TAG, "rec2 img is " + recList.get(2).getImg());
                    Glide.with(getActivity()).load(Constants.IMG_DOMAIN + "/" + recList.get(2).getImg()).into(mRec2IV);
                }
//                Glide.with(getActivity()).load(Constants.IMG_DOMAIN + "/" + data.get(0).getImg()).into(mRec0IV).onLoadFailed(getResources().getDrawable(R.drawable.ic_img_failed));
//                Glide.with(getActivity()).load(Constants.IMG_DOMAIN + "/" + data.get(1).getImg()).into(mRec1IV)/*.onLoadFailed(getResources().getDrawable(R.mipmap.ic_launcher))*/;
//                Glide.with(getActivity()).load(Constants.IMG_DOMAIN + "/" + data.get(2).getImg()).into(mRec2IV)/*.onLoadFailed(getResources().getDrawable(R.mipmap.ic_launcher))*/;
                break;
        }
    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (mListener != null && amapLocation != null) {
            if (amapLocation != null && amapLocation.getErrorCode() == 0) {
//                tvResult.setVisibility(View.GONE);
                mListener.onLocationChanged(amapLocation);// 显示系统小蓝点
//                CommUtil.logE("LOC", "longitude:" + amapLocation.getLongitude() + " latitude:" + amapLocation.getLatitude());
                Map<String, Object> map = new HashMap<>();
                map.put("jpushId", CommUtil.getLocalMacAddress().replace(":", ""));
                map.put("latitude", amapLocation.getLatitude());
                map.put("longitude", amapLocation.getLongitude());
                ((MainActivity) getActivity()).reportInfo(map);  //上传设备信息
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
