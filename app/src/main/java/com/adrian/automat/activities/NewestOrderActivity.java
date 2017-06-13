package com.adrian.automat.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.adrian.automat.R;
import com.adrian.automat.adapters.NewestOrderAdapter;
import com.adrian.automat.pojo.OrderBean;

import java.util.ArrayList;
import java.util.List;

public class NewestOrderActivity extends BaseActivity {

    private ListView mOrderLV;
    private NewestOrderAdapter mAdapter;
    private Button mCloseBtn;

    private List<OrderBean> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews() {
        mCloseBtn = (Button) findViewById(R.id.btn_close);
        mOrderLV = (ListView) findViewById(R.id.lv_orders);
        mAdapter = new NewestOrderAdapter(this);

        mOrderLV.setAdapter(mAdapter);

        mCloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void loadData() {
        datas = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            OrderBean bean = new OrderBean();
            bean.setOrderNum("123412523554674588980");
            bean.setImgUrl("http://pic.baike.soso.com/p/20111017/bki-20111017223041-848836407.jpg");
            bean.setName("药品" + i);
            bean.setState(i % 2);
            bean.setCount(2);
            bean.setTime(System.currentTimeMillis());
            bean.setTotal(23.4f);
            datas.add(bean);
        }
        mAdapter.setDatas(datas);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_newest_order;
    }
}
