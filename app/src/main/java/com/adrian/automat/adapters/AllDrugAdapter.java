package com.adrian.automat.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.adrian.automat.R;
import com.adrian.automat.pojo.DrugSimpleInfo;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ranqing on 2017/6/6.
 */

public class AllDrugAdapter extends BaseAdapter {

    private Context context;
    private List<DrugSimpleInfo> data;

    public AllDrugAdapter(Context context) {
        this.context = context;
    }

    public AllDrugAdapter(Context context, List<DrugSimpleInfo> data) {
        this.context = context;
        this.data = data;
    }

    public void addItem(DrugSimpleInfo item) {
        if (data == null) {
            data = new ArrayList<>();
        }
        data.add(item);
        notifyDataSetChanged();
    }

    public void setData(List<DrugSimpleInfo> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void addData(List<DrugSimpleInfo> items) {
        if (data == null) {
            data = new ArrayList<>();
        }
        data.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return data != null ? data.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_all_drug, null, false);
            holder = new ViewHolder();
            holder.mThumbIV = (ImageView) convertView.findViewById(R.id.iv_drug);
            holder.mSellOutTV = (TextView) convertView.findViewById(R.id.tv_shadow);
            holder.mNameTV = (TextView) convertView.findViewById(R.id.tv_name);
            holder.mPriceTV = (TextView) convertView.findViewById(R.id.tv_price);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        DrugSimpleInfo item = data.get(position);
        holder.mNameTV.setText(item.getDrugName());
        holder.mPriceTV.setText("￥" + item.getPrice());
        Glide.with(context).load(item.getThumbUrl()).into(holder.mThumbIV);
        if (item.getStore() == 0) {
            holder.mSellOutTV.setVisibility(View.VISIBLE);
        } else {
            holder.mSellOutTV.setVisibility(View.GONE);
        }
        return convertView;
    }

    class ViewHolder {
        ImageView mThumbIV;
        TextView mSellOutTV;
        TextView mNameTV;
        TextView mPriceTV;
    }
}
