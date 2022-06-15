package com.cheergotech.UI.activity.adapter.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cheergotech.Base.BaseHolder;
import com.cheergotech.R;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.glide.ImageLoadHelper;

import butterknife.BindView;

public class holder5 extends BaseHolder {
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.clockwise)
    ImageView clockwise;

    public holder5(Context context, ViewGroup parent, int viewType) {
        super(LayoutInflater.from(context).inflate(R.layout.item_img, parent, false));
        this.context=context;
    }

    @Override
    public void bind(Object o, int position, CallBcak callBcak) {
        this.object = o;
        String path = o.toString();
        ImageLoadHelper.glideShowCornerImageWithUrl(context, path.trim(), image, 6);
        clockwise.setVisibility(View.GONE);

        if (callBcak != null) {
            itemView.setOnClickListener(v -> callBcak.setOnClickListener(position));
            clockwise.setOnClickListener(v -> callBcak.delete(position));
        }

    }


}
