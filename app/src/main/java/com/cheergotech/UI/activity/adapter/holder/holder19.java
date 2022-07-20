package com.cheergotech.UI.activity.adapter.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cheergotech.Base.BaseHolder;
import com.cheergotech.R;
import com.cheergotech.listen.CallBcak;

import butterknife.BindView;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/7/1410:17
 * Description
 */
public class holder19 extends BaseHolder {
    private static final String TAG = holder19.class.getName();
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.msg)
    TextView msg;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.line)
    LinearLayout line;

    public holder19(Context context, ViewGroup parent, int viewType) {
        super(LayoutInflater.from(context).inflate(R.layout.item_list_count, null));
        this.context = context;
    }

    @Override
    public void bind(Object o, int position, CallBcak callBcak) {
        this.object = o;
        name.setText(object.toString());
        if (position > 3) {
            msg.setTextColor(context.getResources().getColor(R.color.wred));
        }

        //msg.setText("");

        if (callBcak != null) {
            itemView.setOnClickListener(view -> {
                callBcak.setOnClickListener(position);
                float rotation1 = image.getRotation();
                image.setRotation(rotation1 == 0 ? 180 : 0);
                float rotation2 = image.getRotation();
                line.setVisibility(rotation2 == 180f ? View.VISIBLE : View.GONE);

            });
        }

    }
}
