package com.cheergotech.UI.activity.adapter.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cheergotech.Base.BaseHolder;
import com.cheergotech.R;
import com.cheergotech.UI.model.schoolbust;
import com.cheergotech.listen.CallBcak;

import butterknife.BindView;

public class holder3 extends BaseHolder {
    @BindView(R.id.line1)
    LinearLayout line1;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.msg)
    TextView msg;
    @BindView(R.id.location)
    TextView location;

    public holder3(Context context, ViewGroup parent, int viewType) {
        super(LayoutInflater.from(context).inflate(R.layout.item_location, parent, false));


    }

    @Override
    public void bind(Object o, int position, CallBcak callBcak) {
        this.object = o;
        schoolbust schoolbust = (com.cheergotech.UI.model.schoolbust) object;
        name.setText(schoolbust.getBusCardId());
        title.setText(schoolbust.getBusName());
        location.setText(schoolbust.getBusName());
        msg.setText(String.format("%s--%s", schoolbust.getDepartureStation(), schoolbust.getTerminusStation()));

        if (position == 0) {
            line1.setDividerDrawable(context.getResources().getDrawable(R.drawable.e9));
        } else {
            line1.setDividerDrawable(context.getResources().getDrawable(R.drawable.e8));
        }

        if (callBcak != null) {
            itemView.setOnClickListener(v -> callBcak.setOnClickListener(position));
        }

    }


}
