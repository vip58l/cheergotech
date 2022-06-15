package com.cheergotech.UI.activity.adapter.holder;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cheergotech.Base.BaseHolder;
import com.cheergotech.R;
import com.cheergotech.UI.model.absence;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.glide.ImageLoadHelper;

import butterknife.BindView;

/**
 * 缺勤人数
 */
public class teacher2 extends BaseHolder {
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.image1)
    ImageView image1;
    @BindView(R.id.image2)
    ImageView image2;

    public teacher2(Context context, ViewGroup parent, int viewType) {
        super(LayoutInflater.from(context).inflate(R.layout.item_count2, parent, false));
        this.context = context;
    }

    @Override
    public void bind(Object obj, int position, CallBcak callBcak) {
        this.callBcak = callBcak;
        this.object = obj;
        absence absence = (com.cheergotech.UI.model.absence) obj;
        title.setText(absence.getStudentName());
        absence.getHeadImg();
        if (TextUtils.isEmpty(absence.getHeadImg())) {
            ImageLoadHelper.glideShowImageWithUrl(context, R.mipmap.avasss, image);
        } else {
            ImageLoadHelper.glideShowImageWithUrl(context, absence.getHeadImg(), image);
        }

        if (callBcak != null) {
            itemView.setOnClickListener(v -> callBcak.setOnClickListener(position));

            image.setOnClickListener(v -> callBcak.setOnClickListener1(position));

            image1.setOnClickListener(v -> callBcak.setOnClickListener2(position));

            image2.setOnClickListener(v -> callBcak.setOnClickListener3(position));
        }


    }


}
