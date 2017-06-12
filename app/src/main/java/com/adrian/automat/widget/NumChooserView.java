package com.adrian.automat.widget;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adrian.automat.R;
import com.adrian.automat.tools.CommUtil;

/**
 * Created by ranqing on 2017/6/5.
 */

public class NumChooserView extends LinearLayout implements View.OnClickListener {

    private Context context;
    private Button mMinusBtn;
    private Button mPlusBtn;
    private TextView mNumTV;
    private int max = 10;
    private int min = 1;
    private int curNum = 1;

    private IValueChangedListener listener;

    public interface IValueChangedListener {
        void valueChanged(int value);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_minus:
                if (curNum > min) {
                    setValue(--curNum);
                } else {
                    CommUtil.showToast("已达最小购买数量");
                }
                break;
            case R.id.btn_plus:
                if (curNum < max) {
                    setValue(++curNum);
                } else {
                    CommUtil.showToast("已达最大购买数量");
                }
                break;
        }
    }

    public NumChooserView(Context context) {
        super(context, null);
    }

    public NumChooserView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public NumChooserView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_num_chooser, null, false);
        mMinusBtn = (Button) view.findViewById(R.id.btn_minus);
        mNumTV = (TextView) view.findViewById(R.id.tv_num);
        mPlusBtn = (Button) view.findViewById(R.id.btn_plus);
        addView(view);

        setValue(1);

        mMinusBtn.setOnClickListener(this);
        mPlusBtn.setOnClickListener(this);
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getValue() {
        return curNum;
    }

    public void setValue(int value) {
        curNum = value;
        mNumTV.setText("" + value);
        if (listener != null) {
            listener.valueChanged(value);
        }
    }

    public void setListener(IValueChangedListener listener) {
        this.listener = listener;
    }
}
