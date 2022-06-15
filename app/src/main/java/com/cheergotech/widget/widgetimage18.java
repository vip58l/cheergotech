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
 * 导航控件
 */
public class widgetimage18 extends BaseFrameLayout {
    ImageView imgage;
    TextView name;
    TextView rounded, rounded2;
    String title_name;

    public widgetimage18(@NonNull Context context) {
        super(context);
    }

    public widgetimage18(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public widgetimage18(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.widgetimage18);
        title_name = a.getString(R.styleable.widgetimage18_w18_name);
        Drawable drawable = a.getDrawable(R.styleable.widgetimage18_w18_src);
        a.recycle();
        inflate(context, R.layout.aumdilayout18, this);
        imgage = findViewById(R.id.imgage);
        name = findViewById(R.id.name);
        rounded = findViewById(R.id.rounded);
        rounded2 = findViewById(R.id.rounded2);
        name.setText(title_name);
        imgage.setImageDrawable(drawable);
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 消息条数
     *
     * @param rounded
     */
    public void setRounded(int rounded) {
        if (rounded > 0) {
            this.rounded.setVisibility(VISIBLE);
            this.rounded2.setVisibility(GONE);
        } else {
            this.rounded.setVisibility(GONE);
        }
        this.rounded.setText(rounded > 100 ? "99+" : String.valueOf(rounded));
    }

}
