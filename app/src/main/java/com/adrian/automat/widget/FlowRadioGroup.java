package com.adrian.automat.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioGroup;

/**
 * Created by ranqing on 2017/6/4.
 */

public class FlowRadioGroup extends RadioGroup {
    public FlowRadioGroup(Context context) {
        super(context, null);
    }

    public FlowRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(HORIZONTAL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int childCount = getChildCount();
        int x = 0;
        int y = 0;
        int row = 0;

        int maxWidth = MeasureSpec.getSize(widthMeasureSpec);   //获取最大宽度

        for (int i = 0; i < childCount; i++) {  //获取孩子，并对他们进行测量
            View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                child.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
                int width = child.getMeasuredWidth();   //计算宽度
                int height = child.getMeasuredHeight(); //计算高度
                x += width;
                y = height * (row + 1);

                if (x > maxWidth) { //大于最大宽度，则换行
                    row++;
                    x = width;
                    y = height * (row + 1);
                }
            }
            setMeasuredDimension(maxWidth, y);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int x = 0;
        int y = 0;
        int row = 0;

        int maxWidth = r - 1;

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                int width = child.getMeasuredWidth();
                int height = child.getMeasuredHeight();

                x += width;
                y = (row + 1) * height;
                if (x > maxWidth) {
                    row++;
                    x = width;
                    y = (row + 1) * height;
                }

                child.layout(x - width, y - height, x, y);
            }
        }
    }
}
