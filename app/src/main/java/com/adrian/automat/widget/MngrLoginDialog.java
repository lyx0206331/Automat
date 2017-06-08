package com.adrian.automat.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.adrian.automat.R;

/**
 * Created by ranqing on 2017/6/8.
 */

public class MngrLoginDialog extends Dialog implements View.OnClickListener {

    private ImageButton mCloseIB;
    private EditText mUsrET;
    private EditText mPwdET;
    private Button mCancelBtn;
    private Button mConfirmBtn;
    private IOnClickListener listener;

    public interface IOnClickListener {
        void OnConfirmClick(View v);
        void onCancelClick(View v);
    }

    public MngrLoginDialog(@NonNull Context context) {
        super(context, R.style.Dialog);
    }

    public MngrLoginDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    public MngrLoginDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_mngr_login);
        mCloseIB = (ImageButton) findViewById(R.id.ib_close);
        mUsrET = (EditText) findViewById(R.id.et_input_usr_name);
        mPwdET = (EditText) findViewById(R.id.et_input_password);
        mCancelBtn = (Button) findViewById(R.id.btn_cancel);
        mConfirmBtn = (Button) findViewById(R.id.btn_confirm);

        mCloseIB.setOnClickListener(this);
        mCancelBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_close:
                dismiss();
                break;
            case R.id.btn_cancel:
                if (listener != null) {
                    listener.onCancelClick(v);
                }
//                dismiss();
                break;
            case R.id.btn_confirm:
                if (listener != null) {
                    listener.OnConfirmClick(v);
                }
//                dismiss();
                break;
        }
    }

    public void setOnBtnClickListener(IOnClickListener listener) {
        this.listener = listener;
    }
}
