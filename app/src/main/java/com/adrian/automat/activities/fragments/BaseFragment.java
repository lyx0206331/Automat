package com.adrian.automat.activities.fragments;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {

    private static final int CLICK_CHECK = 200;

    protected boolean isVisible;

    private long lastClickTime;

    private boolean hasInit = false;

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint()) {
            init();
        }
    }

    protected void init() {
        if (hasInit)
            return;
        hasInit = true;
    }

    protected void onVisible() {
        lazyLoad();
    }

    protected abstract void lazyLoad();

    protected void onInvisible() {
    }

    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
    }

    protected void startActivity(Class<? extends Activity> dstAct) {
        Intent intent = new Intent(getContext(), dstAct);
        startActivity(intent);
    }

    protected void startActivity(Class<? extends Activity> dstAct, Bundle bundle) {
        Intent intent = new Intent(getContext(), dstAct);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 检查重复点击
     *
     * @return false为重复点击
     */
    protected boolean checkClickQuick() {
        if (System.currentTimeMillis() - lastClickTime < CLICK_CHECK) {
            lastClickTime = System.currentTimeMillis();
            return false;
        }
        lastClickTime = System.currentTimeMillis();
        return true;
    }

    /**
     * 控制键盘显示和隐藏
     */
    protected void keyboardControl(View view, boolean show) {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (show) {
            imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
        } else {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
