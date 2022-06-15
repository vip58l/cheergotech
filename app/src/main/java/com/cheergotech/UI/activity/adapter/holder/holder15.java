package com.cheergotech.UI.activity.adapter.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cheergotech.Base.BaseHolder;
import com.cheergotech.R;
import com.cheergotech.UI.model.classlistter;
import com.cheergotech.listen.CallBcak;

import butterknife.BindView;

public class holder15 extends BaseHolder {
    @BindView(R.id.title)
    TextView title;

    public holder15(Context context, ViewGroup parent, int viewType) {
        super(LayoutInflater.from(context).inflate(R.layout.item_text_page, parent, false));
    }

    @Override
    public void bind(Object o, int position, CallBcak callBcak) {
        this.object = o;
        classlistter classlistter = (com.cheergotech.UI.model.classlistter) object;
        title.setText(classlistter.getNames());
        if (callBcak != null) {
            itemView.setOnClickListener(view -> callBcak.setOnClickListener(position));
        }


    }

}
