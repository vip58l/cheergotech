package com.cheergotech.UI.activity.adapter.holder;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cheergotech.Base.BaseHolder;
import com.cheergotech.R;
import com.cheergotech.UI.model.healthy.healthlist;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.glide.ImageLoadHelper;

import butterknife.BindView;

public class holder2 extends BaseHolder {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.healthStatusName)
    TextView healthStatusName;
    @BindView(R.id.line)
    LinearLayout line;

    public holder2(Context context, ViewGroup parent, int viewType) {
        super(LayoutInflater.from(context).inflate(R.layout.item_count3, null));
    }

    @Override
    public void bind(Object o, int position, CallBcak callBcak) {
        this.object = o;
        healthlist healthlist = (healthlist) object;
        title.setText(String.valueOf(healthlist.getStudentName()));
        healthStatusName.setText(healthlist.getHealthStatusName());
        if (TextUtils.isEmpty(healthlist.getHeadImg())) {
            ImageLoadHelper.glideShowImageWithUrl(context, R.mipmap.avasss, image);
        } else {
            ImageLoadHelper.glideShowImageWithUrl(context, healthlist.getHeadImg(), image);
        }

        switch (healthlist.getHealthStatus()) {
            case 1:
            default:
                healthStatusName.setTextColor(context.getResources().getColor(R.color.dbb));
                line.setBackground(context.getDrawable(R.drawable.ite5_1));
                break;
            case 2:
                healthStatusName.setTextColor(context.getResources().getColor(R.color.shape));
                line.setBackground(context.getDrawable(R.drawable.ite5_2));
                break;
            case 3:
                healthStatusName.setTextColor(context.getResources().getColor(R.color.bdd2));
                line.setBackground(context.getDrawable(R.drawable.ite5_3));
                break;


        }

        if (callBcak != null) {
            itemView.setOnClickListener(v -> callBcak.setOnClickListener(position));
        }

    }

}
