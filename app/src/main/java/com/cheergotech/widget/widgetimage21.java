package com.cheergotech.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cheergotech.Base.BaseFrameLayout;
import com.cheergotech.R;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/7/1117:00
 * Description
 */
public class widgetimage21 extends BaseFrameLayout {
    TextView t1, t2, t3;
    ImageView iv_selected;

    public widgetimage21(@NonNull Context context) {
        super(context);
    }

    public widgetimage21(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public widgetimage21(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init(Context context, AttributeSet attrs) {

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.widgetimage21);
        String title_name1 = a.getString(R.styleable.widgetimage21_w21_name1);
        String title_name2 = a.getString(R.styleable.widgetimage21_w21_name2);
        String title_name3 = a.getString(R.styleable.widgetimage21_w21_name3);
        a.recycle();

        inflate(context, R.layout.activity_widedgtimages, this);
        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        t3 = findViewById(R.id.t3);
        iv_selected = findViewById(R.id.iv_selected);
        t1.setText(title_name1);
        t2.setText(title_name2);
        t3.setText(title_name3);

    }

    @Override
    public void onClick(View v) {

    }

    public void setonClick(String t1, String t2, String t3) {
        this.t1.setText(t1);
        this.t2.setText(t2);
        this.t3.setText(t3);


    }
}
