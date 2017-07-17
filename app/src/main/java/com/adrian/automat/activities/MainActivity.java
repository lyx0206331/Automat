package com.adrian.automat.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adrian.automat.R;
import com.adrian.automat.activities.fragments.ActivityFragment;
import com.adrian.automat.activities.fragments.DemoFragment;
import com.adrian.automat.activities.fragments.QrCodeFragment;
import com.adrian.automat.activities.fragments.ShoppingFragment;
import com.adrian.automat.application.MyApplication;
import com.adrian.automat.pojo.response.GoodsListResp;
import com.adrian.automat.receiver.JPushUtil;
import com.adrian.automat.receiver.LocalBroadcastManager;
import com.adrian.automat.tools.CommUtil;
import com.adrian.automat.tools.Constants;
import com.adrian.automat.tools.HttpListener;
import com.adrian.automat.tools.NetUtil;
import com.adrian.automat.widget.MngrLoginDialog;
import com.alibaba.fastjson.JSON;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class MainActivity extends BaseFragmentActivity implements RadioGroup.OnCheckedChangeListener, HttpListener {

    private static final int FRAGMENT_COUNT = 4;

    private Fragment[] fragments = new Fragment[FRAGMENT_COUNT];

    private RadioGroup mTabRG;
    private View mIndicatorView;
    private TextView mCompanyTV;
    private MngrLoginDialog mMngrDialog;
    private ImageButton mRefreshIB;

    private int curPos = 0;
    private int stepW;

    private NetUtil util;

    public static boolean isForeground = false;

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
        fragments[0] = new ShoppingFragment();
        fragments[1] = new DemoFragment();
        fragments[2] = new QrCodeFragment();
        fragments[3] = new ActivityFragment();
        switchFragment(fragments[0], R.id.fragment_container);
        mIndicatorView = findViewById(R.id.indicator_bottom);
        mRefreshIB = (ImageButton) findViewById(R.id.ib_refresh);
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mIndicatorView.getLayoutParams();
        stepW = CommUtil.getWindowWidth(this) / FRAGMENT_COUNT;
        lp.width = stepW;
        mTabRG = (RadioGroup) findViewById(R.id.rg_tab);
        mTabRG.check(R.id.rb_shopping);

        mTabRG.setOnCheckedChangeListener(this);
        mRefreshIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });

        mCompanyTV = (TextView) findViewById(R.id.tv_company);
        mCompanyTV.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
//                CommUtil.showToast("正在长按公司名称");
                if (mMngrDialog == null) {
                    mMngrDialog = new MngrLoginDialog(MainActivity.this);
                    mMngrDialog.setOnBtnClickListener(new MngrLoginDialog.IOnClickListener() {
                        @Override
                        public void OnConfirmClick(View v) {
                            startActivity(SettingsActivity.class);
                            mMngrDialog.dismiss();
                        }

                        @Override
                        public void onCancelClick(View v) {
                            mMngrDialog.dismiss();
                        }
                    });
                }
                mMngrDialog.show();
                return false;
            }
        });

        registerMessageReceiver();  // used for receive msg
    }

    private void getData() {
        util.getGoodsList(null, -1, -1, null);
    }

    public void reportInfo(Map<String, Object> map) {
        util.reportMachineInfo(map);
    }

    @Override
    protected void loadData() {
        getData();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.rb_shopping:
                switchFragment(fragments[0], R.id.fragment_container);
                startAnim(0);
                break;
            case R.id.rb_demo:
                switchFragment(fragments[1], R.id.fragment_container);
                startAnim(1);
                break;
            case R.id.rb_qr_code:
                switchFragment(fragments[2], R.id.fragment_container);
                startAnim(2);
                break;
            case R.id.rb_activity:
                switchFragment(fragments[3], R.id.fragment_container);
                startAnim(3);
                break;
        }
    }

    @Override
    protected void onResume() {
        isForeground = true;
        super.onResume();
    }

    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
        fragments[0] = null;
        fragments[1] = null;
        fragments[2] = null;
        fragments[3] = null;
        fragments = null;
    }

    private void startAnim(int pos) {
        TranslateAnimation anim = new TranslateAnimation(curPos * stepW, pos * stepW, 0, 0);
        anim.setDuration(200);
        anim.setFillAfter(true);
        mIndicatorView.startAnimation(anim);
        curPos = pos;
    }

    @Override
    public void onSucceed(int what, Response response) {
        String respStr = response.get().toString();
//        CommUtil.logE("ALL", respStr);
        switch (what) {
            case Constants.GOODS_LIST_TAG:
                GoodsListResp resp = JSON.parseObject(respStr, GoodsListResp.class);
                ((ShoppingFragment) fragments[0]).setData(resp.getData());
                MyApplication.getInstance().setAllGoodsList(resp.getData());
                break;
            case Constants.MACHINE_REPORT_TAG:
                try {
                    JSONObject json = new JSONObject(respStr);
                    if (json.optInt("code") == 0 && json.optBoolean("data")) {
                        CommUtil.logE("REPORT", "上报信息！");
                    } else {
                        CommUtil.showToast(json.optString("msg"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                ReportInfoResp reportInfoResp = JSON.parseObject(respStr, ReportInfoResp.class);
//                if (!reportInfoResp.isData()) {
//                    CommUtil.showToast("设备信息上报失败。" + reportInfoResp.getMsg());
//                }
                break;
        }
    }

    @Override
    public void onFailed(int what, Response response) {
//        hideProgress();
        CommUtil.showToast("数据请求失败！");
    }

    //===================================极光推送=====================================
    //for receive customer msg from jpush server
    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.adrian.automat.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, filter);
    }

    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                    String messge = intent.getStringExtra(KEY_MESSAGE);
                    String extras = intent.getStringExtra(KEY_EXTRAS);
                    StringBuilder showMsg = new StringBuilder();
                    showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                    if (!JPushUtil.isEmpty(extras)) {
                        showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                    }
                    setCostomMsg(showMsg.toString());
                }
            } catch (Exception e) {
            }
        }
    }

    private void setCostomMsg(String msg) {
        CommUtil.logE("JPUSH", msg);
        CommUtil.showToast(msg);
    }
}
