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
import com.cheergotech.listen.CallBcak;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 选中未选中
 */
public class widgetimage13 extends BaseFrameLayout {

    @BindView(R.id.t111)
    TextView t1;
    @BindView(R.id.t222)
    TextView t2;
    @BindView(R.id.t333)
    TextView t3;
    @BindView(R.id.t444)
    TextView t4;
    CallBcak callback;

    public widgetimage13(@NonNull Context context) {
        super(context);
    }

    public widgetimage13(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public widgetimage13(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.widgetimage12);
        a.recycle();
        inflate(context, R.layout.item_img_true_tip, this);

    }


    @Override
    @OnClick({R.id.t111, R.id.t222, R.id.t333, R.id.t444,})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.t111:
                if (callback != null) {
                    callback.setOnClickListener(1);
                }
                break;
            case R.id.t222:
                if (callback != null) {
                    callback.setOnClickListener(2);
                }
                break;
            case R.id.t333:
                if (callback != null) {
                    callback.setOnClickListener(3);
                }
                break;
            case R.id.t444:
                if (callback != null) {
                    callback.setOnClickListener(4);
                }
                break;
        }
    }


    public void setCallback(CallBcak callback) {
        this.callback = callback;
    }


}
