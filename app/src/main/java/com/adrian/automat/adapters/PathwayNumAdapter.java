package com.adrian.automat.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adrian.automat.R;
import com.adrian.automat.application.MyApplication;
import com.adrian.automat.pojo.MachineBean;
import com.adrian.automat.pojo.PathwayBean;
import com.adrian.automat.tools.CommUtil;
import com.adrian.automat.widget.NumChooserView;

/**
 * Created by adrian on 17-6-12.
 */

public class PathwayNumAdapter extends MyBaseAdapter<MachineBean> {
    public PathwayNumAdapter(Context context) {
        super(context);
    }

    public void setOneKeyMax() {
        for (int i = 0; i < getCount(); i++) {
            MachineBean bean = (MachineBean) getItem(i);
            bean.setNowNum(bean.getMaxNum());
        }
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_pathway_num, null, false);
            holder = new ViewHolder();
            holder.mPathwayNumTV = (TextView) convertView.findViewById(R.id.tv_pathway_num);
            holder.mDrugNameTV = (TextView) convertView.findViewById(R.id.tv_drug_name);
            holder.mNCV = (NumChooserView) convertView.findViewById(R.id.ncv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
//        CommUtil.logE("PATHWAYNUM", "pos:" + position);
        final MachineBean item = (MachineBean) getItem(position);
        holder.mPathwayNumTV.setText(item.getOrdinal());
        holder.mDrugNameTV.setText(MyApplication.getInstance().getGoodsById(item.getNowGoodsId()).getName());
        holder.mNCV.setValue(item.getNowNum());
        holder.mNCV.setMax(item.getMaxNum());
        holder.mNCV.setListener(new NumChooserView.IValueChangedListener() {
            @Override
            public void valueChanged(int value) {
                item.setNowNum(value);
//                CommUtil.showToast(item.getPathwayNum() + " " + item.getDrugName() + " " + value);
            }
        });
        return convertView;
    }

    class ViewHolder {
        TextView mPathwayNumTV;
        TextView mDrugNameTV;
        NumChooserView mNCV;
    }
}
