package com.adrian.automat.activities;

import android.Manifest;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.adrian.automat.R;
import com.adrian.automat.tools.CommUtil;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class WelcomeActivity extends BaseActivity {

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

    }

    @Override
    protected void initViews() {
//        mHandler.sendEmptyMessageDelayed(0, 2000);
        WelcomeActivityPermissionsDispatcher.allowedPermissionWithCheck(this);
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

    @NeedsPermission({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void allowedPermission() {
        CommUtil.logE("PERMISSION", "权限已申请");
        mHandler.sendEmptyMessageDelayed(0, 2000);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        WelcomeActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
        CommUtil.logE("PERMISSION", "req result");
    }

    @OnShowRationale({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void showRationale(final PermissionRequest request) {
        CommUtil.logE("PERMISSION", "第一次被拒绝，再次申请");
        mHandler.sendEmptyMessageDelayed(0, 2000);
    }

    @OnPermissionDenied({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void permissionDenied() {
        CommUtil.logE("PERMISSION", "权限被拒绝");
        mHandler.sendEmptyMessageDelayed(0, 2000);
    }

    @OnNeverAskAgain({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void neverAskAgain() {
        CommUtil.logE("PERMISSION", "不再询问");
        mHandler.sendEmptyMessageDelayed(0, 2000);
    }
}
