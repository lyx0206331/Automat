package com.adrian.automat.activities.fragments;


import android.content.Context;
import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.adrian.automat.R;
import com.adrian.automat.activities.AllDrugActivity;
import com.adrian.automat.activities.DetailActivity;
import com.adrian.automat.pojo.response.GoodsListResp;
import com.adrian.automat.tools.CommUtil;
import com.adrian.automat.tools.HttpListener;
import com.adrian.automat.tools.NetUtil;
import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingFragment extends BaseFragment implements View.OnClickListener, HttpListener {

    private static final String TAG = "ShoppingFragment";

    private VideoView videoView;
    private ProgressBar mVideoLoading;

    private Button mScanAttentionBtn;
    private Button mAllDrugBtn;
    private ImageView mRecIV;
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

        videoView.setVideoPath(videoUrl);
        videoView.start();
        return view;
    }

    @Override
    protected void lazyLoad() {

    }

    private void initView(View view) {
        videoView = (VideoView) view.findViewById(R.id.videoView);
        mVideoLoading = (ProgressBar) view.findViewById(R.id.video_loading);
        mScanAttentionBtn = (Button) view.findViewById(R.id.btn_scan_attention);
        mAllDrugBtn = (Button) view.findViewById(R.id.btn_all_drug);
        mRecIV = (ImageView) view.findViewById(R.id.iv_recommend);
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

        Glide.with(getActivity()).load("http://img2.ooopic.com/12/83/05/32bOOOPICab_202.jpg").into(mRecIV);

        Glide.with(getActivity()).load("http://pic.baike.soso.com/p/20111017/bki-20111017223041-848836407.jpg").into(mRec0IV);
        Glide.with(getActivity()).load("http://img0.imgtn.bdimg.com/it/u=2890974895,2151831020&fm=214&gp=0.jpg").into(mRec1IV);
        Glide.with(getActivity()).load("http://img008.hc360.cn/g3/M08/FE/68/wKhQvlJWpKyEe6KUAAAAANIYEaE532.jpg..180x180.jpg").into(mRec2IV);
        mScanAttentionBtn.setOnClickListener(this);
        mAllDrugBtn.setOnClickListener(this);
        mRecIV.setOnClickListener(this);
        mRec0LL.setOnClickListener(this);
        mRec1LL.setOnClickListener(this);
        mRec2LL.setOnClickListener(this);

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.e("TAG", "play prepared!");
                mVideoLoading.setVisibility(View.GONE);
            }
        });
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.e("TAG", "play completion!");
                videoView.setVideoPath(videoUrl);
                videoView.start();
            }
        });
        videoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                Log.e("TAG", "what:" + what + " extra:" + extra + " duration:" + mp.getDuration() + " cur:" + mp.getCurrentPosition());
                if (what == 3) {
                    mVideoLoading.setVisibility(View.GONE);
                } else {
                    mVideoLoading.setVisibility(View.VISIBLE);
                }
                return false;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        util.getGoodsList("1-1-1", 0, 0, null);
    }

    @Override
    public void onPause() {
        super.onPause();
        videoView.pause();
    }

    @Override
    public void onStop() {
        super.onStop();
        videoView.stopPlayback();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
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
            case R.id.iv_recommend:
                CommUtil.showToast("推荐位");
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
}
