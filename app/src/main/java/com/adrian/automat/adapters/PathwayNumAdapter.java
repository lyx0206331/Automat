package com.adrian.automat.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adrian.automat.R;
import com.adrian.automat.pojo.PathwayBean;
import com.adrian.automat.tools.CommUtil;
import com.adrian.automat.widget.NumChooserView;

/**
 * Created by adrian on 17-6-12.
 */

public class PathwayNumAdapter extends MyBaseAdapter {
    public PathwayNumAdapter(Context context) {
        super(context);
    }

    public void setOneKeyMax() {
        for (int i = 0; i < getCount(); i++) {
            PathwayBean bean = (PathwayBean) getItem(i);
            bean.setCount(bean.getMax());
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
        final PathwayBean item = (PathwayBean) getItem(position);
        holder.mPathwayNumTV.setText(item.getPathwayNum());
        holder.mDrugNameTV.setText(item.getDrugName());
        holder.mNCV.setValue(item.getCount());
        holder.mNCV.setMax(item.getMax());
        holder.mNCV.setListener(new NumChooserView.IValueChangedListener() {
            @Override
            public void valueChanged(int value) {
                item.setCount(value);
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
