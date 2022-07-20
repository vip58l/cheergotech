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
 * Data 2022/7/1917:40
 * Description
 */
public class holder135 extends BaseHolder {
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.del)
    ImageView del;
    @BindView(R.id.name)
    TextView name;

    public holder135(Context context, ViewGroup parent, int viewType) {
        super(LayoutInflater.from(context).inflate(R.layout.item_img_def, parent, false));
    }

    @Override
    public void bind(Object o, int position, CallBcak callBcak) {

        if (callBcak != null) {
            itemView.setOnClickListener(view -> callBcak.setOnClickListener(position));
            del.setOnClickListener(view -> callBcak.delete(position));
        }

    }
}
