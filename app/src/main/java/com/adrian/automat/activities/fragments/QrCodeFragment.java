package com.adrian.automat.activities.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.adrian.automat.R;
import com.adrian.automat.activities.AllDrugActivity;
import com.adrian.automat.tools.CommUtil;
import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class QrCodeFragment extends BaseFragment implements View.OnClickListener {

    private XBanner banner;
    private EditText mInputET;
    private Button mNum0Btn;
    private Button mNum1Btn;
    private Button mNum2Btn;
    private Button mNum3Btn;
    private Button mNum4Btn;
    private Button mNum5Btn;
    private Button mNum6Btn;
    private Button mNum7Btn;
    private Button mNum8Btn;
    private Button mNum9Btn;
    private Button mDelBtn;
    private Button mConfirmBtn;

    private List<String> localImages;

    public QrCodeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qr_code, container, false);
        initView(view);
        return view;
    }

    @Override
    protected void lazyLoad() {

    }

    private void initView(View view) {
        banner = (XBanner) view.findViewById(R.id.banner);
        mInputET = (EditText) view.findViewById(R.id.et_input);
        mNum0Btn = (Button) view.findViewById(R.id.btn_0);
        mNum1Btn = (Button) view.findViewById(R.id.btn_1);
        mNum2Btn = (Button) view.findViewById(R.id.btn_2);
        mNum3Btn = (Button) view.findViewById(R.id.btn_3);
        mNum4Btn = (Button) view.findViewById(R.id.btn_4);
        mNum5Btn = (Button) view.findViewById(R.id.btn_5);
        mNum6Btn = (Button) view.findViewById(R.id.btn_6);
        mNum7Btn = (Button) view.findViewById(R.id.btn_7);
        mNum8Btn = (Button) view.findViewById(R.id.btn_8);
        mNum9Btn = (Button) view.findViewById(R.id.btn_9);
        mDelBtn = (Button) view.findViewById(R.id.btn_del);
        mConfirmBtn = (Button) view.findViewById(R.id.btn_confirm);
        mInputET.requestFocus();

        mNum0Btn.setOnClickListener(this);
        mNum1Btn.setOnClickListener(this);
        mNum2Btn.setOnClickListener(this);
        mNum3Btn.setOnClickListener(this);
        mNum4Btn.setOnClickListener(this);
        mNum5Btn.setOnClickListener(this);
        mNum6Btn.setOnClickListener(this);
        mNum7Btn.setOnClickListener(this);
        mNum8Btn.setOnClickListener(this);
        mNum9Btn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        mDelBtn.setOnClickListener(this);
        mDelBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mInputET.setText("");
                return false;
            }
        });

        localImages = new ArrayList<>();
        localImages.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496407271033&di=fa1deaabe2b4792240b4dde3fbcaacda&imgtype=0&src=http%3A%2F%2Fpic.90sjimg.com%2Fback_pic%2F00%2F04%2F13%2F75%2F8db5a6d5cc09a89f6dc6c9d8bf2e3770.jpg");
        localImages.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496408046885&di=46e1d41491d7df0cb2efea1e4a7ddf8c&imgtype=0&src=http%3A%2F%2Fpic.90sjimg.com%2Fback_pic%2F00%2F04%2F13%2F75%2F266e84bc0e5a43b8e39d397f648162c4.jpg");
        localImages.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496408140378&di=75ea602661479baa157d201362ee6c66&imgtype=0&src=http%3A%2F%2Fpic.90sjimg.com%2Fback_pic%2Fqk%2Fback_origin_pic%2F00%2F01%2F49%2F15068f16288b124067042212251d0d63.jpg");
        localImages.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496408237185&di=192b74ab6f1ff401448e3f46861c9119&imgtype=0&src=http%3A%2F%2Fpic.90sjimg.com%2Fback_pic%2F00%2F04%2F13%2F75%2F5412693453409bc2a8db36a9f70fc3b6.jpg");
        banner.setData(localImages, null);
        banner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, View view, int position) {
                Glide.with(getActivity()).load(localImages.get(position)).into((ImageView) view);
            }
        });
        banner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, int position) {
                CommUtil.showToast("点击了第" + position + "张图片");
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        banner.startAutoPlay();
    }

    @Override
    public void onPause() {
        super.onPause();
        banner.stopAutoPlay();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_0:
                mInputET.append("0");
                break;
            case R.id.btn_1:
                mInputET.append("1");
                break;
            case R.id.btn_2:
                mInputET.append("2");
                break;
            case R.id.btn_3:
                mInputET.append("3");
                break;
            case R.id.btn_4:
                mInputET.append("4");
                break;
            case R.id.btn_5:
                mInputET.append("5");
                break;
            case R.id.btn_6:
                mInputET.append("6");
                break;
            case R.id.btn_7:
                mInputET.append("7");
                break;
            case R.id.btn_8:
                mInputET.append("8");
                break;
            case R.id.btn_9:
                mInputET.append("9");
                break;
            case R.id.btn_del:
                String text = mInputET.getText().toString();
                if (!TextUtils.isEmpty(text)) {
                    text = text.substring(0, text.length() - 1);
                    mInputET.setText(text);
                }
                break;
            case R.id.btn_confirm:
                break;
            default:
                break;
        }
    }
}
