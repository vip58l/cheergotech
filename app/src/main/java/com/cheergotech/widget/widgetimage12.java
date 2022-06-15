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
 * 选中未选中
 */
public class widgetimage12 extends BaseFrameLayout {

    widgetimage11 w1, w2, w3, w4, w5;
    TextView title, msg;

    public widgetimage12(@NonNull Context context) {
        this(context, null);
    }

    public widgetimage12(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public widgetimage12(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @Override
    public void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.widgetimage12);
        String textname = a.getString(R.styleable.widgetimage12_w12_name);
        String textmsg = a.getString(R.styleable.widgetimage12_w12_msg1);
        a.recycle();

        inflate(context, R.layout.item_img_true, this);
        title = findViewById(R.id.title);
        msg = findViewById(R.id.msg);
        msg = findViewById(R.id.msg);
        w1 = findViewById(R.id.w1);
        w2 = findViewById(R.id.w2);
        w3 = findViewById(R.id.w3);
        w4 = findViewById(R.id.w4);
        w5 = findViewById(R.id.w5);
        title.setText(textname);
        msg.setText(textmsg);

    }

    @Override
    public void onClick(View v) {

    }

}
