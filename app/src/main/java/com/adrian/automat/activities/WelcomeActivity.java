package com.adrian.automat.activities;

import android.Manifest;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.adrian.automat.R;
import com.adrian.automat.application.MyApplication;
import com.adrian.automat.pojo.response.LoginResp;
import com.adrian.automat.tools.CommUtil;
import com.adrian.automat.tools.Constants;
import com.adrian.automat.tools.HttpListener;
import com.adrian.automat.tools.NetUtil;
import com.alibaba.fastjson.JSON;
import com.yanzhenjie.nohttp.rest.Response;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class WelcomeActivity extends BaseActivity implements HttpListener {

    private static final String TAG = "WelcomeActivity";

    private NetUtil util;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            startActivity(MainActivity.class);
            finish();
        }
    };

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
        if (CommUtil.getNetworkStatus(MyApplication.getInstance()) != -1) {
            WelcomeActivityPermissionsDispatcher.allowedPermissionWithCheck(this);
        } else {
            CommUtil.showToast(R.string.connect_error);
            finish();
        }
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_welcome;
    }

    @Override
    public void onBackPressed() {
        mHandler.removeMessages(0);
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        util.onDestroy();
        super.onDestroy();
    }

    @NeedsPermission({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE})
    void allowedPermission() {
        CommUtil.logE("PERMISSION", "权限已申请");
//        mHandler.sendEmptyMessageDelayed(0, 2000);
        util.loginDev(Constants.TEST_ACOUNT, Constants.TEST_PWD);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        WelcomeActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
        CommUtil.logE("PERMISSION", "req result");
    }

    @OnShowRationale({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE})
    void showRationale(final PermissionRequest request) {
        CommUtil.logE("PERMISSION", "第一次被拒绝，再次申请");
//        mHandler.sendEmptyMessageDelayed(0, 2000);
        util.loginDev(Constants.TEST_ACOUNT, Constants.TEST_PWD);
    }

    @OnPermissionDenied({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE})
    void permissionDenied() {
        CommUtil.logE("PERMISSION", "权限被拒绝");
//        mHandler.sendEmptyMessageDelayed(0, 2000);
        util.loginDev(Constants.TEST_ACOUNT, Constants.TEST_PWD);
    }

    @OnNeverAskAgain({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE})
    void neverAskAgain() {
        CommUtil.logE("PERMISSION", "不再询问");
//        mHandler.sendEmptyMessageDelayed(0, 2000);
        util.loginDev(Constants.TEST_ACOUNT, Constants.TEST_PWD);
    }

    @Override
    public void onSucceed(int what, Response response) {
        String respStr = response.get().toString();
        LoginResp resp = JSON.parseObject(respStr, LoginResp.class);
        MyApplication.getInstance().setLoginToken(resp.getData());
        CommUtil.logE(TAG, respStr);
        CommUtil.showToast("登录设备成功");
        mHandler.sendEmptyMessageDelayed(0, 2000);
    }

    @Override
    public void onFailed(int what, Response response) {
        CommUtil.showToast("登录设备失败");
        finish();
    }
}
