package com.adrian.automat.activities;

import android.net.DhcpInfo;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.adrian.automat.R;
import com.adrian.automat.tools.CommUtil;

public class SetIPActivity extends BaseActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private RadioGroup mSetIpTypeRG;
    private EditText mIpAddressET;
    private EditText mNetmaskET;
    private EditText mGatewayET;
    private EditText mDns1ET;
    private EditText mDns2ET;
    private Button mCancelBtn;
    private Button mConfirmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews() {
        mSetIpTypeRG = (RadioGroup) findViewById(R.id.rg_ip_type);
        mIpAddressET = (EditText) findViewById(R.id.et_ip_address);
        mNetmaskET = (EditText) findViewById(R.id.et_netmask);
        mGatewayET = (EditText) findViewById(R.id.et_gateway);
        mDns1ET = (EditText) findViewById(R.id.et_dns1);
        mDns2ET = (EditText) findViewById(R.id.et_dns2);
        mCancelBtn = (Button) findViewById(R.id.btn_cancel);
        mConfirmBtn = (Button) findViewById(R.id.btn_confirm);

        mSetIpTypeRG.setOnCheckedChangeListener(this);
        mCancelBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);

        if (CommUtil.isStaticIP(this)) {
            mSetIpTypeRG.check(R.id.rb_manual);
        } else {
            mSetIpTypeRG.check(R.id.rb_auto);
        }
    }

    @Override
    protected void loadData() {
        DhcpInfo dhcpInfo = CommUtil.getDhcpInfo(this);
        mIpAddressET.setText(CommUtil.int2ip(dhcpInfo.ipAddress));
        mNetmaskET.setText(CommUtil.int2ip(dhcpInfo.netmask));
        mGatewayET.setText(CommUtil.int2ip(dhcpInfo.gateway));
        mDns1ET.setText(CommUtil.int2ip(dhcpInfo.dns1));
        mDns2ET.setText(CommUtil.int2ip(dhcpInfo.dns2));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_set_ip;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancel:
                finish();
                break;
            case R.id.btn_confirm:
                CommUtil.setStaticIP(this, mIpAddressET.getText().toString(), mNetmaskET.getText().toString(), mGatewayET.getText().toString(),
                        mDns1ET.getText().toString(), mDns2ET.getText().toString());
                finish();
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.rb_auto:
                mIpAddressET.setEnabled(false);
                mNetmaskET.setEnabled(false);
                mGatewayET.setEnabled(false);
                mDns1ET.setEnabled(false);
                mDns2ET.setEnabled(false);
                break;
            case R.id.rb_manual:
                mIpAddressET.setEnabled(true);
                mNetmaskET.setEnabled(true);
                mGatewayET.setEnabled(true);
                mDns1ET.setEnabled(true);
                mDns2ET.setEnabled(true);
                break;
        }
    }
}
