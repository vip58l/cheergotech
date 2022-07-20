package com.cheergotech.UI.activity.adapter.holder;

import android.content.Context;
import android.util.Log;
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
 * Data 2022/7/1510:35
 * Description
 */
public class holder20 extends BaseHolder {
    @BindView(R.id.iv_left)
    ImageView iv_left;
    @BindView(R.id.iv_right)
    ImageView iv_right;
    @BindView(R.id.iv_selected)
    ImageView iv_selected;
    @BindView(R.id.name1)
    TextView name1;
    @BindView(R.id.name2)
    TextView name2;


    public holder20(Context context, ViewGroup parent, int viewType) {
        super(LayoutInflater.from(context).inflate(R.layout.item_chat_left, parent, false));
        this.context = context;
    }

    @Override
    public void bind(Object o, int position, CallBcak callBcak) {
        int i = position % 2;
        this.object = o;
        Log.d("TAG", "bind: " + i);
        if (i > 0) {
            iv_left.setVisibility(View.INVISIBLE);
            iv_right.setVisibility(View.VISIBLE);
        } else {
            iv_left.setVisibility(View.VISIBLE);
            iv_right.setVisibility(View.INVISIBLE);
        }

        switch (position) {
            case 0:
                iv_selected.setImageDrawable(context.getDrawable(R.mipmap.b11));
                iv_selected.setBackground(context.getDrawable(R.mipmap.b11));
                break;
            case 1:
                iv_selected.setImageDrawable(context.getDrawable(R.mipmap.b22));
                break;
            case 2:
                iv_selected.setImageDrawable(context.getDrawable(R.mipmap.b33));
                break;
            case 3:
                iv_selected.setImageDrawable(context.getDrawable(R.mipmap.b44));
                break;
        }

    }
}
