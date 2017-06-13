package com.adrian.automat.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.adrian.automat.R;
import com.adrian.automat.pojo.OrderBean;
import com.adrian.automat.tools.CommUtil;
import com.bumptech.glide.Glide;

/**
 * Created by adrian on 17-6-13.
 */

public class NewestOrderAdapter extends MyBaseAdapter {
    public NewestOrderAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_newest_order, null, false);
            holder = new ViewHolder();
            holder.mOrderNumTV = (TextView) convertView.findViewById(R.id.tv_order_num);
            holder.mOrderImgIV = (ImageView) convertView.findViewById(R.id.iv_order_img);
            holder.mDrugNameTV = (TextView) convertView.findViewById(R.id.tv_name);
            holder.mOutStateTV = (TextView) convertView.findViewById(R.id.tv_out_state);
            holder.mCountTV = (TextView) convertView.findViewById(R.id.tv_count);
            holder.mTotalTV = (TextView) convertView.findViewById(R.id.tv_total);
            holder.mTimeTV = (TextView) convertView.findViewById(R.id.tv_time);
            holder.mReportBtn = (Button) convertView.findViewById(R.id.btn_report);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        OrderBean item = (OrderBean) getItem(position);
        Glide.with(context).load(item.getImgUrl()).into(holder.mOrderImgIV);
        holder.mOrderNumTV.setText("订单号:" + item.getOrderNum());
        holder.mTimeTV.setText(CommUtil.millis2date("yyyy-MM-dd HH:mm:ss", item.getTime()));
        holder.mTotalTV.setText("合计:￥" + item.getTotal());
        holder.mCountTV.setText("数量:" + item.getCount());
        holder.mDrugNameTV.setText(item.getName());
        if (item.getState() == 0) {
            holder.mOutStateTV.setText(R.string.out_normal);
            holder.mOutStateTV.setTextColor(context.getResources().getColor(R.color.normal));
        } else {
            holder.mOutStateTV.setText(R.string.out_except);
            holder.mOutStateTV.setTextColor(context.getResources().getColor(R.color.red));
        }
        return convertView;
    }

    class ViewHolder {
        TextView mOrderNumTV;
        ImageView mOrderImgIV;
        TextView mDrugNameTV;
        TextView mOutStateTV;
        TextView mCountTV;
        TextView mTotalTV;
        TextView mTimeTV;
        Button mReportBtn;
    }
}
