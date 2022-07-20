package com.cheergotech.UI.activity.adapter.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cheergotech.Base.BaseHolder;
import com.cheergotech.R;
import com.cheergotech.listen.CallBcak;

import butterknife.BindView;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/7/1919:26
 * Description
 */
public class holder137 extends BaseHolder {
    @BindView(R.id.unction)
    TextView unction;

    public holder137(Context context, ViewGroup parent, int viewType) {
        super(LayoutInflater.from(context).inflate(R.layout.item_list_phb, parent, false));
    }

    @Override
    public void bind(Object o, int position, CallBcak callBcak) {
        unction.setText("" + position);

    }

}
