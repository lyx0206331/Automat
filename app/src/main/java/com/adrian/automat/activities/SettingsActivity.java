package com.adrian.automat.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.adrian.automat.R;

public class SettingsActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mQrCodeIV;
    private Button mModifyPathwayBtn;
    private Button mNewestOrderBtn;
    private Button mTestPathwayBtn;
    private Button mClearFileBtn;
    private Button mVolumnCtrlBtn;
    private Button mSetIpBtn;
    private Button mCheckTempBtn;
    private Button mSensorReportBtn;
    private Button mCloseBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews() {
        mQrCodeIV = (ImageView) findViewById(R.id.iv_qr_code);
        mModifyPathwayBtn = (Button) findViewById(R.id.btn_modify_pathway);
        mNewestOrderBtn = (Button) findViewById(R.id.btn_newest_order);
        mTestPathwayBtn = (Button) findViewById(R.id.btn_test_pathway);
        mClearFileBtn = (Button) findViewById(R.id.btn_clear_file);
        mVolumnCtrlBtn = (Button) findViewById(R.id.btn_volumn_ctrl);
        mSetIpBtn = (Button) findViewById(R.id.btn_set_ip);
        mCheckTempBtn = (Button) findViewById(R.id.btn_temp_state);
        mSensorReportBtn = (Button) findViewById(R.id.btn_sensor_report);
        mCloseBtn = (Button) findViewById(R.id.btn_close);

        mModifyPathwayBtn.setOnClickListener(this);
        mNewestOrderBtn.setOnClickListener(this);
        mTestPathwayBtn.setOnClickListener(this);
        mClearFileBtn.setOnClickListener(this);
        mVolumnCtrlBtn.setOnClickListener(this);
        mSetIpBtn.setOnClickListener(this);
        mCheckTempBtn.setOnClickListener(this);
        mSensorReportBtn.setOnClickListener(this);
        mCloseBtn.setOnClickListener(this);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_settings;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_modify_pathway:
                break;
            case R.id.btn_newest_order:
                break;
            case R.id.btn_test_pathway:
                break;
            case R.id.btn_clear_file:
                break;
            case R.id.btn_volumn_ctrl:
                break;
            case R.id.btn_set_ip:
                break;
            case R.id.btn_temp_state:
                break;
            case R.id.btn_sensor_report:
                break;
            case R.id.btn_close:
                finish();
                break;
            default:
                break;
        }
    }
}
