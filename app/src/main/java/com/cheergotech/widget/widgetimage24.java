package com.cheergotech.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cheergotech.Base.BaseFrameLayout;
import com.cheergotech.R;

import butterknife.OnClick;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/7/1117:00
 * Description
 */
public class widgetimage24 extends BaseFrameLayout {
    public TextView send1, send2, send_text, send_text2;
    private LinearLayout line;

    public widgetimage24(@NonNull Context context) {
        super(context);
    }

    public widgetimage24(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public widgetimage24(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.widgetimage24);
        String title_name1 = a.getString(R.styleable.widgetimage24_t24_name1);
        String title_name2 = a.getString(R.styleable.widgetimage24_t24_name2);
        String title_name3 = a.getString(R.styleable.widgetimage24_t24_name3);
        a.recycle();

        inflate(context, R.layout.widgetimage24, this);
        send1 = findViewById(R.id.send1);
        send2 = findViewById(R.id.send2);
        send_text = findViewById(R.id.send_text);
        send_text2 = findViewById(R.id.send_text2);
        line = findViewById(R.id.line);

        if (!TextUtils.isEmpty(title_name1)) {
            send1.setText(title_name1);
        }
        if (!TextUtils.isEmpty(title_name2)) {
            send2.setText(title_name2);
        }
        if (!TextUtils.isEmpty(title_name3)) {
            send_text.setText(title_name3);
        }
    }

    @Override
    @OnClick({R.id.send1, R.id.send2})
    public void onClick(View v) {
    }

}
