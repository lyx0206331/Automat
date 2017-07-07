package com.adrian.automat.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.adrian.automat.R;
import com.adrian.automat.tools.CommUtil;
import com.adrian.automat.tools.HttpListener;
import com.adrian.automat.tools.NetUtil;
import com.yanzhenjie.nohttp.rest.Response;

public class SensorReportActivity extends BaseActivity implements HttpListener {

    private NetUtil util;

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

    }

    @Override
    protected void loadData() {
        util.getMachineInfo();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_sensor_report;
    }

    @Override
    public void onSucceed(int what, Response response) {
        String respStr = response.get().toString();
        CommUtil.logE("MAC_INFO", respStr);
    }

    @Override
    public void onFailed(int what, Response response) {

    }
}
