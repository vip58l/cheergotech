package com.cheergotech.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cheergotech.Base.BaseFrameLayout;
import com.cheergotech.R;

public class widgetimage7 extends BaseFrameLayout {

    public LinearLayout line22;
    public TextView title, msg1, msg2;
    public Drawable src1, src2;



    public widgetimage7(@NonNull Context context) {
        super(context);
    }

    public widgetimage7(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public widgetimage7(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.widgetimage7);
        String title_name = a.getString(R.styleable.widgetimage7_w7_name);
        String title_msg1 = a.getString(R.styleable.widgetimage7_w7_msg1);
        String title_msg2 = a.getString(R.styleable.widgetimage7_w7_msg2);
        src1 = a.getDrawable(R.styleable.widgetimage7_w7_src1);
        src2 = a.getDrawable(R.styleable.widgetimage7_w7_src2);
        a.recycle();

        inflate(context, R.layout.widgettimage7, this);
        title = findViewById(R.id.title);
        msg1 = findViewById(R.id.msg1);
        msg2 = findViewById(R.id.msg2);
        line22 = findViewById(R.id.line22);
        title.setText(title_name);
        msg1.setText(title_msg1);
        msg2.setText(title_msg2);
    }

    @Override
    public void onClick(View v) {


    }

    public void setTitle(String msg) {
        this.title.setText(msg);
    }

    public void setMsg1(String msg) {
        this.msg1.setText(msg);
    }

    public void setMsg2(String msg) {
        this.msg2.setText(msg);
    }

    public LinearLayout getLine22() {
        return line22;
    }
}
