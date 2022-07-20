package com.cheergotech.UI.activity.adapter.holder;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cheergotech.Base.BaseHolder;
import com.cheergotech.R;
import com.cheergotech.listen.CallBcak;

import butterknife.BindView;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/7/129:47
 * Description
 */
public class holder16 extends BaseHolder {
    private static final String TAG = holder16.class.getSimpleName();
    @BindView(R.id.title)
    TextView title;

    public holder16(Context context, ViewGroup parent, int viewType) {
        super(LayoutInflater.from(context).inflate(R.layout.widgetimage, parent, false));
        this.context = context;

    }

    @Override
    public void bind(Object objet, int position, CallBcak callBcak) {
        this.object=objet;
        String name = (String) objet;
        Log.d(TAG, "bind: " + name);
        this.title.setText(name);
        if (callBcak != null) {
            itemView.setOnClickListener(view -> callBcak.setOnClickListener(position));
        }

    }

}
