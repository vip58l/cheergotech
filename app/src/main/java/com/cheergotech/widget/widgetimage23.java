package com.cheergotech.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
public class widgetimage23 extends BaseFrameLayout {
    TextView name;
    EditText msg;
    ImageView iv_selected;
    LinearLayout line;

    public widgetimage23(@NonNull Context context) {
        super(context);
    }

    public widgetimage23(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public widgetimage23(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.widgetimage23);
        String title_name1 = a.getString(R.styleable.widgetimage23_t23_name1);
        String title_name2 = a.getString(R.styleable.widgetimage23_t23_name2);
        boolean booleans1 = a.getBoolean(R.styleable.widgetimage23_t23_show1, false);
        boolean booleans2 = a.getBoolean(R.styleable.widgetimage23_t23_show2, false);
        a.recycle();

        inflate(context, R.layout.widgetimage23, this);
        name = findViewById(R.id.name);
        msg = findViewById(R.id.msg);
        line = findViewById(R.id.line);
        iv_selected = findViewById(R.id.iv_selected);
        if (!TextUtils.isEmpty(title_name1)) {
            name.setText(title_name1);
        }
        if (!TextUtils.isEmpty(title_name2)) {
            msg.setText(title_name2);
        }
        if (booleans1) {
            line.setVisibility(VISIBLE);
        }
        if (booleans2){
            name.setTextColor(getResources().getColor(R.color.color_ffb0bde1));
            msg.setTextColor(getResources().getColor(R.color.color_ff202020));
        }
    }

    public ImageView getIv_selected() {
        return iv_selected;
    }

    @Override
    public void onClick(View v) {

    }

    public void setonClick(String message) {
        this.msg.setText(message);
    }

    public void setName(String name) {
        this.name.setText(name);
    }
}
