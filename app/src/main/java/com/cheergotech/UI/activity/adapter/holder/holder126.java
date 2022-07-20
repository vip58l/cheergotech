package com.cheergotech.UI.activity.adapter.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
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
 * Data 2022/7/1816:45
 * Description
 */
public class holder126 extends BaseHolder {
    @BindView(R.id.iv_left)
    ImageView iv_left;
    @BindView(R.id.iv_selected)
    ImageView iv_selected;
    @BindView(R.id.name1)
    TextView name1;
    @BindView(R.id.name2)
    TextView name2;
    @BindView(R.id.iv_right)
    ImageView iv_right;

    public holder126(Context context, ViewGroup parent, int viewType) {
        super(LayoutInflater.from(context).inflate(R.layout.item_chat_left, parent, false));
    }

    @Override
    public void bind(Object o, int position, CallBcak callBcak) {
        this.object = o;

        if (callBcak != null) {
            itemView.setOnClickListener(view -> callBcak.setOnClickListener(position));
        }


    }

    /**
     * 发起事项-通过聊天发送
     *
     * @param o
     * @param position
     * @param callBcak
     */
    public void bind2(Object o, int position, CallBcak callBcak) {
        this.object = o;
        iv_left.setVisibility(View.INVISIBLE);
        iv_right.setVisibility(View.VISIBLE);
        name1.setText("参会通知");
        name2.setText("张萌发起的会议事项");
        if (position == 0) {
            name1.setTextColor(context.getResources().getColor(R.color.shapa));
            iv_selected.setImageDrawable(context.getResources().getDrawable(R.mipmap.ss3));
        } else {
            name1.setTextColor(context.getResources().getColor(R.color.dbb));
            iv_selected.setImageDrawable(context.getResources().getDrawable(R.mipmap.clocs));
        }


        name2.setTextColor(context.getResources().getColor(R.color.bdd2));
        if (callBcak != null) {
            itemView.setOnClickListener(view -> callBcak.setOnClickListener(position));
        }
    }


}
