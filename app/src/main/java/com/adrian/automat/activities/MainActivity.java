package com.adrian.automat.activities;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adrian.automat.R;
import com.adrian.automat.activities.fragments.ActivityFragment;
import com.adrian.automat.activities.fragments.DemoFragment;
import com.adrian.automat.activities.fragments.QrCodeFragment;
import com.adrian.automat.activities.fragments.ShoppingFragment;
import com.adrian.automat.tools.CommUtil;
import com.adrian.automat.widget.MngrLoginDialog;

public class MainActivity extends BaseFragmentActivity implements RadioGroup.OnCheckedChangeListener {

    private static final int FRAGMENT_COUNT = 4;

    private Fragment[] fragments = new Fragment[FRAGMENT_COUNT];

    private RadioGroup mTabRG;
    private View mIndicatorView;
    private TextView mCompanyTV;
    private MngrLoginDialog mMngrDialog;

    private int curPos = 0;
    private int stepW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews() {
        fragments[0] = new ShoppingFragment();
        fragments[1] = new DemoFragment();
        fragments[2] = new QrCodeFragment();
        fragments[3] = new ActivityFragment();
        switchFragment(fragments[0], R.id.fragment_container);
        mIndicatorView = findViewById(R.id.indicator_bottom);
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mIndicatorView.getLayoutParams();
        stepW = CommUtil.getWindowWidth(this) / FRAGMENT_COUNT;
        lp.width = stepW;
        mTabRG = (RadioGroup) findViewById(R.id.rg_tab);
        mTabRG.check(R.id.rb_shopping);

        mTabRG.setOnCheckedChangeListener(this);

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
    }

    @Override
    protected void loadData() {

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
    protected void onDestroy() {
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
}
