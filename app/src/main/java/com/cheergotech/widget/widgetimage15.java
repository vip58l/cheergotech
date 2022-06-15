package com.cheergotech.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cheergotech.Base.BaseFrameLayout;
import com.cheergotech.R;

/**
 * 按住说话
 */
public class widgetimage15 extends BaseFrameLayout {
    ImageView imgage;
    TextView s_name;
    Drawable src1;
    Drawable src2;
    boolean select;
    String title_name;

    public widgetimage15(@NonNull Context context) {
        super(context);
    }

    public widgetimage15(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public widgetimage15(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.widgetimage15);
        title_name = a.getString(R.styleable.widgetimage15_w15_name);
        src1 = a.getDrawable(R.styleable.widgetimage15_w15_src1);
        src2 = a.getDrawable(R.styleable.widgetimage15_w15_src2);
        a.recycle();

        inflate(context, R.layout.aumdilayout15, this);
        imgage = findViewById(R.id.imgage);
        s_name = findViewById(R.id.s_name);
        if (!TextUtils.isEmpty(title_name)) {
            s_name.setText(title_name);
        }
        if (src2 != null) {
            imgage.setImageDrawable(src2);
        }

    }

    @Override
    public void onClick(View v) {

    }

    public void setSelect(boolean select) {
        this.select = select;
        imgage.setImageDrawable(select ? src1 : src2);
        s_name.setTextColor(context.getResources().getColor(select ? R.color.shapa : R.color.B0BDE1));
    }

}
