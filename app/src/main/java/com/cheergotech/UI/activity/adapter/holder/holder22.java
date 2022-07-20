package com.cheergotech.UI.activity.adapter.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cheergotech.Base.BaseHolder;
import com.cheergotech.R;
import com.cheergotech.listen.CallBcak;

import butterknife.BindView;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/7/1513:58
 * Description
 */
public class holder22 extends BaseHolder {
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.resuperior)
    TextView resuperior;
    @BindView(R.id.iv_selected)
    ImageView iv_selected;

    public holder22(Context context, ViewGroup parent, int viewType) {
        super(LayoutInflater.from(context).inflate(R.layout.item_chat_iv_list, parent, false));
    }

    @Override
    public void bind(Object o, int position, CallBcak callBcak) {

        if (callBcak != null) {
            itemView.setOnClickListener(view -> callBcak.setOnClickListener(position));
        }

    }
}
