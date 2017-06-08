package com.adrian.automat.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    private ProgressDialog mPd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        initVariables();
        initViews();
        loadData();
    }

    protected void startActivity(Class<? extends Activity> dstAct) {
        Intent intent = new Intent(this, dstAct);
        startActivity(intent);
    }

    /**
     * 初始化变量
     */
    protected abstract void initVariables();

    /**
     * 初始化UI
     */
    protected abstract void initViews();

    /**
     * 数据加载
     */
    protected abstract void loadData();

    /**
     * 返回布局ID
     * @return
     */
    protected abstract int getLayoutResId();

    protected void startActivity(Class<? extends BaseActivity> cls, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void showProgress(String msg) {
        if (mPd == null) {
            mPd = new ProgressDialog(this);
            mPd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mPd.setIndeterminate(false);
            mPd.setCancelable(true);
            mPd.setCanceledOnTouchOutside(false);
        }
        mPd.setMessage(msg);
        if (!mPd.isShowing()) {
            mPd.show();
        }
    }

    public void hideProgress() {
        if (mPd == null) return;
        if (mPd.isShowing()) {
            mPd.dismiss();
        }
    }
}
