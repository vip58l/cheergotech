package com.cheergotech.UI.activity.adapter.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cheergotech.Base.BaseHolder;
import com.cheergotech.R;
import com.cheergotech.UI.model.amCourse;
import com.cheergotech.listen.CallBcak;

import butterknife.BindView;

public class holder8 extends BaseHolder {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.msg)
    TextView msg;

    public holder8(Context context, ViewGroup parent, int viewType) {
        super(LayoutInflater.from(context).inflate(R.layout.item_text_title, parent, false));
    }

    @Override
    public void bind(Object o, int position, CallBcak callBcak) {
        this.object = o;

        if (o!=null){
            amCourse amCourse = (com.cheergotech.UI.model.amCourse) o;
            title.setText(amCourse.getCourseName());
            msg.setText(amCourse.getStartTime() + "~" + amCourse.getOverTime());
        }

        if (callBcak != null) {
            itemView.setOnClickListener(v -> callBcak.setOnClickListener(position));
        }


    }


}
