package com.cheergotech.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cheergotech.Base.BaseFrameLayout;
import com.cheergotech.R;

/**
 * 导航控件
 */
public class widgetimage16 extends BaseFrameLayout {
    TextView tv1;
    View v1;
    String title_name;
    int visibility;
    boolean booleanbg;
    LinearLayout l2;
    TextView tnumber;

    public widgetimage16(@NonNull Context context) {
        super(context);
    }

    public widgetimage16(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public widgetimage16(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.widgetimage16);
        title_name = a.getString(R.styleable.widgetimage16_w16_name);
        visibility = a.getInt(R.styleable.widgetimage16_w16_visibility, 2);
        a.recycle();
        inflate(context, R.layout.aumdilayout16, this);
        tv1 = findViewById(R.id.tv1);
        v1 = findViewById(R.id.v1);
        l2 = findViewById(R.id.l2);
        tnumber = findViewById(R.id.tnumber);
        tv1.setText(title_name);
        v1.setVisibility(0 == visibility ? VISIBLE : GONE);
        tv1.setTextColor(context.getResources().getColor(0 == visibility ? R.color.shapa : R.color.bdd2));
    }

    @Override
    public void onClick(View v) {

    }

    public void setBooleanbg(boolean booleanbg) {
        this.booleanbg = booleanbg;
        v1.setVisibility(booleanbg ? VISIBLE : GONE);
        tv1.setTextSize(booleanbg ? 17 : 17);
        tv1.setTextColor(context.getResources().getColor(booleanbg ? R.color.shapa : R.color.bdd2));
    }


    /**
     * 显示类型
     * @param number
     */
    public void show(int number,int type){
        l2.setVisibility(VISIBLE);
        tnumber.setText(String.valueOf(number));
        if (type==1){
            tnumber.setTextColor(context.getResources().getColor(R.color.colorAccent));
        }else {
            tnumber.setTextColor(context.getResources().getColor(R.color.dbb));
        }
    }

    /**
     * 显示类型
     * @param number
     */
    public void show(int number,int type,int color){
        l2.setVisibility(VISIBLE);
        tnumber.setText(String.valueOf(number));
        if (type==1){
            tnumber.setTextColor(context.getResources().getColor(R.color.shapa));
        }else {
            tnumber.setTextColor(context.getResources().getColor(R.color.dbb));
        }
    }


}
