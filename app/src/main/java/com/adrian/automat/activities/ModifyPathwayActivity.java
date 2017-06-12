package com.adrian.automat.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.adrian.automat.R;
import com.adrian.automat.adapters.PathwayNumAdapter;
import com.adrian.automat.pojo.PathwayBean;
import com.adrian.automat.tools.CommUtil;

import java.util.ArrayList;
import java.util.List;

public class ModifyPathwayActivity extends BaseActivity implements View.OnClickListener {

    private ListView mPathwayLV;
    private PathwayNumAdapter mAdapter;
    private Button mCancelBtn;
    private Button mOneKeyBtn;
    private Button mConfirmBtn;

    private List<PathwayBean> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews() {
        mPathwayLV = (ListView) findViewById(R.id.lvc_pathway);
        mCancelBtn = (Button) findViewById(R.id.btn_cancel);
        mOneKeyBtn = (Button) findViewById(R.id.btn_one_key);
        mConfirmBtn = (Button) findViewById(R.id.btn_confirm);

        mAdapter = new PathwayNumAdapter(this);
        mPathwayLV.setAdapter(mAdapter);

        mCancelBtn.setOnClickListener(this);
        mOneKeyBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
    }

    @Override
    protected void loadData() {
        datas = new ArrayList<>();
        for (int i = 0; i < 72; i++) {
            PathwayBean bean = new PathwayBean();
            bean.setPathwayNum("1-" + (i / 9 + 1) + "-" + (i % 9 + 1));
            bean.setDrugName("药品" + i);
            bean.setCount(i);
            bean.setMax(72);
            datas.add(bean);
        }
        mAdapter.setDatas(datas);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_modify_pathway;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancel:
                finish();
                break;
            case R.id.btn_one_key:
                mAdapter.setOneKeyMax();
                break;
            case R.id.btn_confirm:
                finish();
                break;
        }
    }
}
