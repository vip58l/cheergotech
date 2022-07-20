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

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/7/1310:30
 * Description
 */
public class Folderfober extends BaseFrameLayout {
    TextView tv_selected1;
    TextView tv_selected2;

    public Folderfober(@NonNull Context context) {
        super(context);
    }

    public Folderfober(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Folderfober(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init(Context context, AttributeSet attrs) {

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Folderfober);
        String name1 = a.getString(R.styleable.Folderfober_f_name1);
        String name2 = a.getString(R.styleable.Folderfober_f_name2);
        String name3 = a.getString(R.styleable.Folderfober_f_name3);
        int textColor1 = a.getColor(R.styleable.Folderfober_f_textColor1, 0);
        int textColor2 = a.getColor(R.styleable.Folderfober_f_textColor2, 0);
        a.recycle();

        inflate(context, R.layout.folderfober, this);
        tv_selected1 = findViewById(R.id.tv_selected1);
        tv_selected2 = findViewById(R.id.tv_selected2);
        tv_selected1.setText(name1);
        tv_selected2.setText(name2);

        if (textColor1 > 0) {
            tv_selected1.setTextColor(textColor1);
        }

        if (textColor2 > 0) {
            tv_selected2.setTextColor(textColor2);
        }

    }

    @Override
    public void onClick(View v) {

    }
}
