package com.cheergotech.UI.activity.adapter.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.cheergotech.Base.BaseHolder;
import com.cheergotech.R;
import com.cheergotech.listen.CallBcak;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/7/1311:12
 * Description
 */
public class holder17 extends BaseHolder {

    public holder17(Context context, ViewGroup parent, int viewType) {
        super(LayoutInflater.from(context).inflate(R.layout.folderfober_item, parent, false));
        this.context = context;
    }

    @Override
    public void bind(Object o, int position, CallBcak callBcak) {
        if (callBcak != null) {
            itemView.setOnClickListener(view -> callBcak.setOnClickListener(position));
        }

    }
}
