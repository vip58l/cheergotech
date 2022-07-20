package com.cheergotech.UI.activity.adapter.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cheergotech.Base.BaseHolder;
import com.cheergotech.R;
import com.cheergotech.UI.model.shengpi;
import com.cheergotech.listen.CallBcak;

import butterknife.BindView;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/7/1815:59
 * Description
 */
public class holder125 extends BaseHolder {
    @BindView(R.id.headImg)
    ImageView headImg;
    @BindView(R.id.mtitle)
    TextView mtitle;
    @BindView(R.id.describe)
    TextView describe;


    public holder125(Context context, ViewGroup parent, int viewType) {
        super(LayoutInflater.from(context).inflate(R.layout.item_chat_shengpi, parent, false));
        this.context = context;
    }

    @Override
    public void bind(Object o, int position, CallBcak callBcak) {
        this.object = o;
        shengpi shengpi = (com.cheergotech.UI.model.shengpi) object;
        mtitle.setText(shengpi.getTitle());
        describe.setText(shengpi.getDescribe());
        headImg.setImageDrawable(context.getResources().getDrawable(shengpi.getBiticon()));
        if (callBcak != null) {
            itemView.setOnClickListener(view -> callBcak.setOnClickListener(position));
        }
    }


    /**
     * 新建事项
     * @param o
     * @param position
     * @param callBcak
     */
    public void bind2(Object o, int position, CallBcak callBcak) {
        this.object = o;
        shengpi shengpi = (com.cheergotech.UI.model.shengpi) object;
        mtitle.setText(shengpi.getTitle());
        describe.setText(shengpi.getDescribe());
        headImg.setImageDrawable(context.getDrawable(shengpi.getBiticon()));
        if (callBcak != null) {
            itemView.setOnClickListener(view -> callBcak.setOnClickListener(position));
        }
    }
}
