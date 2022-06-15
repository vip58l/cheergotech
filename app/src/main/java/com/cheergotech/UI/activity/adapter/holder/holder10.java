package com.cheergotech.UI.activity.adapter.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.cheergotech.Base.BaseHolder;
import com.cheergotech.R;
import com.cheergotech.UI.model.conters;
import com.cheergotech.listen.CallBcak;

import butterknife.BindView;

public class holder10 extends BaseHolder {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.checkbox)
    CheckBox checkbox;
    @BindView(R.id.image)
    ImageView image;

    public holder10(Context context, ViewGroup parent, int viewType) {
        super(LayoutInflater.from(context).inflate(R.layout.item_text_edit, parent, false));
    }

    @Override
    public void bind(Object o, int position, CallBcak callBcak) {
        this.object = o;
        conters conters = (com.cheergotech.UI.model.conters) o;
        title.setText(conters.getContent());
        checkbox.setChecked(conters.isIs());
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                checkbox.setChecked(b);
                conters.setIs(b);
            }
        });

        if (callBcak != null) {

            itemView.setOnClickListener(v -> callBcak.setOnClickListener(position));

            image.setOnClickListener(view -> callBcak.setOnClickListener1(position));
        }

    }

}
