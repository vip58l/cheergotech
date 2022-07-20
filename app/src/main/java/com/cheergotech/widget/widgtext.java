package com.cheergotech.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cheergotech.Base.BaseFrameLayout;
import com.cheergotech.R;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/7/1414:26
 * Description
 */
public class widgtext extends BaseFrameLayout {

    TextView tv_count1;
    TextView tv_count2;

    public widgtext(@NonNull Context context) {
        super(context);
    }

    public widgtext(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public widgtext(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.widgtext);
        String title_name = a.getString(R.styleable.widgtext_t_name1);
        String title_msg = a.getString(R.styleable.widgtext_t_name2);
        a.recycle();

        inflate(context, R.layout.notification1, this);
        tv_count1 = findViewById(R.id.tv_count1);
        tv_count2 = findViewById(R.id.tv_count2);
        if (!TextUtils.isEmpty(title_name)) {
            tv_count1.setText(title_name);
        }
        if (!TextUtils.isEmpty(title_msg)) {
            tv_count2.setText(title_msg);
        }

    }

    @Override
    public void onClick(View v) {

    }

    public void setTv_count1(String tv_count1) {
        this.tv_count1.setText(tv_count1);
    }
}
