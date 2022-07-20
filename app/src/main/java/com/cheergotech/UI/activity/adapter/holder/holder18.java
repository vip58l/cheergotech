package com.cheergotech.UI.activity.adapter.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cheergotech.Base.BaseHolder;
import com.cheergotech.R;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.glide.ImageLoadHelper;

import butterknife.BindView;

public class holder18 extends BaseHolder {
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.clockwise)
    ImageView clockwise;

    public holder18(Context context, ViewGroup parent, int viewType) {
        super(LayoutInflater.from(context).inflate(R.layout.item_img18, parent, false));
    }

    @Override
    public void bind(Object o, int position, CallBcak callBcak) {
        this.object = o;
        String path = o.toString().trim();
        ImageLoadHelper.glideShowCornerImageWithUrl(context, path, image, 6);
        if (callBcak != null) {

            //查看大图
            itemView.setOnClickListener(v -> callBcak.setOnClickListener(position));

            //删除图片
            clockwise.setOnClickListener(v -> callBcak.delete(position));
        }


    }

    public void bind2(Object o, int position, CallBcak callBcak) {
        this.object = o;
        String path = o.toString().trim();
        ImageLoadHelper.glideShowCornerImageWithUrl(context, path, image, 6);
        if (callBcak != null) {

            //查看大图
            itemView.setOnClickListener(v -> callBcak.setOnClickListener(position));

            //删除图片
            clockwise.setOnClickListener(v -> callBcak.delete(position));
        }


    }

}