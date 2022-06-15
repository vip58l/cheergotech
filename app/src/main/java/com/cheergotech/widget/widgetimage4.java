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

public class widgetimage4 extends BaseFrameLayout {
    TextView title;
    TextView msg;

    public widgetimage4(@NonNull Context context) {
        super(context);
    }

    public widgetimage4(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public widgetimage4(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.widgetimage4);
        String title_name = a.getString(R.styleable.widgetimage4_w2_name);
        String title_msg = a.getString(R.styleable.widgetimage4_w2_msg);
        a.recycle();

        inflate(context, R.layout.widgettext, this);
        title = findViewById(R.id.title);
        msg = findViewById(R.id.msg);
        title.setText(title_name);
        msg.setText(title_msg);
    }

    @Override
    public void onClick(View v) {

    }

    public void setMsg(String msg) {
        this.msg.setText(msg);
    }
}
