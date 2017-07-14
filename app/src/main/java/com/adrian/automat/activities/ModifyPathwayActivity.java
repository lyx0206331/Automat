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
import com.adrian.automat.pojo.MachineBean;
import com.adrian.automat.pojo.PathwayBean;
import com.adrian.automat.pojo.PathwayDataBean;
import com.adrian.automat.pojo.response.MachineInfoResp;
import com.adrian.automat.pojo.response.ModifyPathwayResp;
import com.adrian.automat.tools.CommUtil;
import com.adrian.automat.tools.Constants;
import com.adrian.automat.tools.HttpListener;
import com.adrian.automat.tools.NetUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.ArrayList;
import java.util.List;

public class ModifyPathwayActivity extends BaseActivity implements View.OnClickListener, HttpListener {

    private static final String TAG = ModifyPathwayActivity.class.getSimpleName();

    private ListView mPathwayLV;
    private PathwayNumAdapter mAdapter;
    private Button mCancelBtn;
    private Button mOneKeyBtn;
    private Button mConfirmBtn;

    private List<PathwayBean> datas;

    private NetUtil util;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initVariables() {
        util = new NetUtil(this, this);
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
        util.getMachineInfo();
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
                List<MachineBean> list = mAdapter.getDatas();
                List<PathwayDataBean> data = new ArrayList<>();
                for (MachineBean bean :
                        list) {
                    PathwayDataBean item = new PathwayDataBean();
                    item.setGridId(bean.getGridId());
                    item.setNowNum(bean.getNowNum());
                    item.setMaxNum(bean.getMaxNum());
                    data.add(item);
                }
                util.modifyPathwayData(data);
                break;
        }
    }

    @Override
    public void onSucceed(int what, Response response) {
        String respStr = response.get().toString();
        CommUtil.logE(TAG, respStr);
        switch (what) {
            case Constants.MACHINE_INFO_TAG:
                MachineInfoResp resp = JSON.parseObject(respStr, MachineInfoResp.class);
//                CommUtil.logE(TAG, resp.toString());
                if (resp != null && resp.getData() != null && resp.getData().getGrids() != null) {
                    mAdapter.setDatas(resp.getData().getGrids());
                }
                break;
            case Constants.MODIFY_PATHWAY_DATA_TAG:
                JSONObject json = JSON.parseObject(respStr);
                ModifyPathwayResp resp1 = JSON.parseObject(respStr, ModifyPathwayResp.class);
                if (resp1.getData() != null && resp1.getData().size() > 0) {
                    CommUtil.showToast("成功修改货道数据!");
                    finish();
                } else {
                    CommUtil.showToast("修改货道数据失败:" + resp1.getMsg());
                }
                break;
        }
    }

    @Override
    public void onFailed(int what, Response response) {
        CommUtil.showToast("数据提交失败!");
    }
}
