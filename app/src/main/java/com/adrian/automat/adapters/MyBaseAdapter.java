package com.adrian.automat.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by adrian on 17-6-12.
 */

public class MyBaseAdapter<T> extends BaseAdapter {

    private List<T> datas;
    protected Context context;
    protected LayoutInflater mInflater;

    public MyBaseAdapter(Context context) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        datas = new ArrayList<>();
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public void addDatas(List<T> items) {
        datas.addAll(items);
        notifyDataSetChanged();
    }

    public List<T> getDatas() {
        return datas;
    }

    public void addItem(T t) {
        datas.add(t);
        notifyDataSetChanged();
    }

    public void removeItem(T t) {
        if (datas.contains(t)) {
            datas.remove(t);
            notifyDataSetChanged();
        }
    }

    public void removeItem(int position) {
        if (position < datas.size()) {
            datas.remove(position);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
