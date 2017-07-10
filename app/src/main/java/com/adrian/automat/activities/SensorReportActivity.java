package com.adrian.automat.activities;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.adrian.automat.BR;
import com.adrian.automat.R;
import com.adrian.automat.databinding.ActivitySensorReportBinding;
import com.adrian.automat.pojo.response.MachineInfoResp;
import com.adrian.automat.tools.CommUtil;
import com.adrian.automat.tools.Constants;
import com.adrian.automat.tools.HttpListener;
import com.adrian.automat.tools.NetUtil;
import com.alibaba.fastjson.JSON;
import com.yanzhenjie.nohttp.rest.Response;

public class SensorReportActivity extends BaseActivity implements HttpListener {

    private static final String TAG = SensorReportActivity.class.getSimpleName();

    private NetUtil util;
    private SensorReport report;

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
        ActivitySensorReportBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_sensor_report);
        report = new SensorReport();
        binding.setSensorReport(report);
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
        switch (what) {
            case Constants.MACHINE_INFO_TAG:
                MachineInfoResp resp = JSON.parseObject(respStr, MachineInfoResp.class);
                CommUtil.logE(TAG, resp.toString());
                report.setMachineInfo(resp.toString());
                break;
        }
    }

    @Override
    public void onFailed(int what, Response response) {

    }

    public class SensorReport extends BaseObservable {
        public String machineInfo;

        public SensorReport() {
        }

        @Bindable
        public String getMachineInfo() {
            return machineInfo;
        }

        public void setMachineInfo(String machineInfo) {
            this.machineInfo = machineInfo;
            notifyPropertyChanged(BR.machineInfo);
        }

        public void clickReport(View view) {
            CommUtil.showToast("report");
        }
    }
}
