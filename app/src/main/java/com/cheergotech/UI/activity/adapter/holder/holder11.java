package com.cheergotech.UI.activity.adapter.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cheergotech.Base.BaseHolder;
import com.cheergotech.R;
import com.cheergotech.UI.model.conters;
import com.cheergotech.listen.CallBcak;

import butterknife.BindView;

public class holder11 extends BaseHolder {
    @BindView(R.id.title)
    TextView title;


    public holder11(Context context, ViewGroup parent, int viewType) {
        super(LayoutInflater.from(context).inflate(R.layout.item_text_holder, parent, false));
    }

    @Override
    public void bind(Object o, int position, CallBcak callBcak) {
        this.object = o;
        conters conters = (com.cheergotech.UI.model.conters) o;
        title.setText(conters.getContent());
        if (callBcak != null) {
            itemView.setOnClickListener(v -> callBcak.setOnClickListener(position));
        }

    }

}
