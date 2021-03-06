package com.cheergotech.UI.activity.adapter;

import android.content.Context;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class pageadapter extends PagerAdapter {
    private Context context;
    public List<View> list;

    public pageadapter(Context context, List<View> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(list.get(position));

    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(list.get(position));
        return list.get(position);
    }

    /**
     * 刷新数据
     *
     * @param
     */
    public void setDataSetChanged(List<View> list) {
        this.list = list;
        notifyDataSetChanged();
    }


}
