package com.cheergotech.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cheergotech.Base.BaseFrameLayout;
import com.cheergotech.R;

public class widgetimage8 extends BaseFrameLayout {
    TextView title;
    TextView msg;


    public widgetimage8(@NonNull Context context) {
        super(context);
    }

    public widgetimage8(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public widgetimage8(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.widgetimage8);
        String title_name = a.getString(R.styleable.widgetimage8_w8_name);
        String title_msg = a.getString(R.styleable.widgetimage8_w8_msg1);
        a.recycle();

        inflate(context, R.layout.widgettimage8, this);
        title = findViewById(R.id.title);
        msg = findViewById(R.id.msg);
        title.setText(title_name);
        msg.setText(title_msg);
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 设置背景切换
     */
    public void settoBackground(boolean b) {

        if (b) {
            setBackground(context.getDrawable(R.drawable.itemw));
            title.setTextColor(context.getResources().getColor(R.color.white));
            msg.setTextColor(context.getResources().getColor(R.color.white));
        } else {
            setBackground(context.getDrawable(R.drawable.e11));
            title.setTextColor(context.getResources().getColor(R.color.ff202020));
            msg.setTextColor(context.getResources().getColor(R.color.ff202020));
        }


    }
}
