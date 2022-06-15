package com.cheergotech.UI.activity.adapter.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cheergotech.Base.BaseHolder;
import com.cheergotech.R;
import com.cheergotech.UI.model.Devrlist;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.Config;

import butterknife.BindView;

public class holder1 extends BaseHolder {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.time)
    TextView time;
    Devrlist devrlist;

    public holder1(Context context, ViewGroup parent, int viewType) {
        super(LayoutInflater.from(context).inflate(R.layout.item_text, null));
    }

    @Override
    public void bind(Object o, int position, CallBcak callBcak) {
        this.object = o;
        devrlist = (Devrlist) o;
        title.setText(devrlist.getStudentName());
        time.setText(Config.stampToDateh(String.valueOf(devrlist.getCreateTime()), null, null) + " 到校");
        time.setTextColor(context.getResources().getColor(R.color.ff929dbb));
        time.setTextColor(context.getResources().getColor(devrlist.getStatus() == 4 ? R.color.wred : R.color.ff929dbb));
    }

}
