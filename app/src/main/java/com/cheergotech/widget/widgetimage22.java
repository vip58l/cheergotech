package com.cheergotech.widget;

import android.content.Context;
import android.content.res.TypedArray;
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
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/7/1117:00
 * Description
 */
public class widgetimage22 extends BaseFrameLayout {
    TextView name, msg, money, money2, tips;
    ImageView iv_selected;

    public widgetimage22(@NonNull Context context) {
        super(context);
    }

    public widgetimage22(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public widgetimage22(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.widgetimage22);
        String title_name1 = a.getString(R.styleable.widgetimage22_w22_name1);
        String title_name2 = a.getString(R.styleable.widgetimage22_w22_name2);
        int rotation = a.getIndex(R.styleable.widgetimage22_w22_rotation);
        boolean booleans1 = a.getBoolean(R.styleable.widgetimage22_w22_boot1, false);
        boolean booleans2 = a.getBoolean(R.styleable.widgetimage22_w22_boot2, false);
        boolean booleans3 = a.getBoolean(R.styleable.widgetimage22_w22_boot3, false);
        boolean booleans4 = a.getBoolean(R.styleable.widgetimage22_w22_boot4, false);
        a.recycle();

        inflate(context, R.layout.widgetmage22, this);
        name = findViewById(R.id.name);
        msg = findViewById(R.id.msg);
        money = findViewById(R.id.money);
        money2 = findViewById(R.id.money2);
        iv_selected = findViewById(R.id.iv_selected);
        tips = findViewById(R.id.tips);

        if (!TextUtils.isEmpty(title_name1)) {
            name.setText(title_name1);
        }
        if (!TextUtils.isEmpty(title_name2)) {
            msg.setText(title_name2);
        }
        if (booleans1) {
            money.setVisibility(VISIBLE);
            msg.setVisibility(GONE);
        }
        if (booleans2) {
            money2.setVisibility(View.VISIBLE);
            iv_selected.setVisibility(View.GONE);
        }
        if (booleans3) {
            iv_selected.setVisibility(View.GONE);
        }
        if (booleans4) {
            tips.setVisibility(GONE);
        }
    }

    @Override
    public void onClick(View v) {

    }

    public void setonClick(String message) {
        this.msg.setText(message);


    }
}
