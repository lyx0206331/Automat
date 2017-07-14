package com.adrian.automat.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;

import com.adrian.automat.R;

/**
 * Created by qing on 2017/7/14 0014.
 */

public class QRCodeDialog extends Dialog {
    public QRCodeDialog(@NonNull Context context) {
        super(context, R.style.Dialog);
    }

    public QRCodeDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    public QRCodeDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
}
