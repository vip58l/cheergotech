package com.cheergotech.UI.activity.adapter.holder;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cheergotech.Base.BaseHolder;
import com.cheergotech.R;
import com.cheergotech.UI.model.banjiqian;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.Config;

import butterknife.BindView;

public class holder6 extends BaseHolder {
    @BindView(R.id.tips)
    LinearLayout tips;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.msge)
    TextView msge;

    public holder6(Context context, ViewGroup parent, int viewType) {
        super(LayoutInflater.from(context).inflate(R.layout.item_text_msg, parent, false));
    }

    @Override
    public void bind(Object o, int position, CallBcak callBcak) {
        this.object = o;
        banjiqian banjiqian = (com.cheergotech.UI.model.banjiqian) o;
        tips.setVisibility(banjiqian.getIsView() == 0 ? View.VISIBLE : View.GONE);
        if (!TextUtils.isEmpty(banjiqian.getCreateTime())) {
            time.setText(Config.timestamp(banjiqian.getCreateTime(), null));
        }
        msge.setText(banjiqian.getDescribe());
        if (callBcak != null) {
            itemView.setOnClickListener(v -> callBcak.setOnClickListener(position));
        }
    }


}
