package com.cheergotech.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cheergotech.Base.BaseFrameLayout;
import com.cheergotech.R;

/**
 * 点赞控件
 */
public class widgetimage5 extends BaseFrameLayout {
    private Drawable drawable1;
    private Drawable drawable2;
    private ImageView image;
    private TextView msg;

    public widgetimage5(@NonNull Context context) {
        super(context);
    }

    public widgetimage5(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public widgetimage5(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.widgetimage5);
        String title_name = a.getString(R.styleable.widgetimage5_w5_name);
        String title_msg = a.getString(R.styleable.widgetimage5_w5_msg);
        drawable1 = a.getDrawable(R.styleable.widgetimage5_w5_src1);
        drawable2 = a.getDrawable(R.styleable.widgetimage5_w5_src2);
        a.recycle();

        inflate(context, R.layout.widgettext5, this);
        msg = findViewById(R.id.msg);
        image = findViewById(R.id.image);
        image.setImageDrawable(drawable1);
        msg.setText(title_msg);
    }

    @Override
    public void onClick(View v) {

    }

    public void setDrawable(boolean b) {
        image.setImageDrawable(b ? drawable1 : drawable2);
    }

    public void setMsg(String msg) {
        this.msg.setText(msg);
    }

    public void setDrawable(Drawable drawable){
        image.setImageDrawable(drawable);
    }


}
