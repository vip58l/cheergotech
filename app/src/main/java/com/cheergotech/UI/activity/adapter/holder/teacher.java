package com.cheergotech.UI.activity.adapter.holder;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cheergotech.Base.BaseHolder;
import com.cheergotech.R;
import com.cheergotech.UI.model.volist;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.Config;

import butterknife.BindView;

/**
 * 请假人数
 */
public class teacher extends BaseHolder {
    @BindView(R.id.headImg)
    ImageView headImg;
    @BindView(R.id.describes)
    TextView describes;
    @BindView(R.id.days)
    TextView days;
    @BindView(R.id.startTime)
    TextView startTime;
    @BindView(R.id.overTime)
    TextView overTime;
    @BindView(R.id.studentName)
    TextView studentName;
    @BindView(R.id.msg6)
    TextView msg6;

    public teacher(Context context, ViewGroup parent, int viewType) {
        super(LayoutInflater.from(context).inflate(R.layout.item_count1, parent, false));
        this.context = context;
    }

    @Override
    public void bind(Object obj, int position, CallBcak callBcak) {
        this.callBcak = callBcak;
        this.object = obj;
        volist volist = (com.cheergotech.UI.model.volist) obj;
        studentName.setText(volist.getStudentName());
        describes.setText(volist.getTitles());
        days.setText(String.valueOf(volist.getDays()));
        startTime.setText(Config.stampToDate(String.valueOf(volist.getStartTime()), null));
        overTime.setText(Config.stampToDate(String.valueOf(volist.getOverTime()), null));
        if (TextUtils.isEmpty(volist.getHeadImg())) {
            Glide.with(context).load(R.mipmap.avasss).into(headImg);
        } else {
            Glide.with(context).load(volist.getHeadImg()).into(headImg);
        }
        switch (volist.getStatus()) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
        }
        if (callBcak != null) {
            itemView.setOnClickListener(v -> callBcak.setOnClickListener(position));
        }
    }


}
