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
import com.cheergotech.listen.CallBcak;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 错误显示界面
 */
public class widgetimage10 extends BaseFrameLayout {

    @BindView(R.id.lineyout)
    LinearLayout lineyout;

    @BindView(R.id.t1)
    TextView t1;
    @BindView(R.id.t2)
    TextView t2;
    @BindView(R.id.t3)
    TextView t3;
    @BindView(R.id.t4)
    TextView t4;
    CallBcak callBcak;

    public void setCallBcak(CallBcak callBcak) {
        this.callBcak = callBcak;
    }

    public widgetimage10(@NonNull Context context) {
        this(context, null);
    }

    public widgetimage10(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public widgetimage10(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @Override
    public void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.item_eorr);
        String name = a.getString(R.styleable.widgetimage10_w10_name);
        String msg1 = a.getString(R.styleable.widgetimage10_w10_msg1);
        String msg2 = a.getString(R.styleable.widgetimage10_w10_msg2);
        String msg3 = a.getString(R.styleable.widgetimage10_w10_msg3);
        a.recycle();
        inflate(context, R.layout.item_navigation, this);
    }

    public LinearLayout getLineyout() {
        return lineyout;
    }

    public TextView getT1() {
        return t1;
    }

    public TextView getT2() {
        return t2;
    }

    public TextView getT3() {
        return t3;
    }

    public TextView getT4() {
        return t4;
    }

    @Override
    @OnClick({R.id.t1, R.id.t2, R.id.t3, R.id.t4,})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.t1:
                if (callBcak != null) {
                    callBcak.setOnClickListener(1);
                }
                break;
            case R.id.t2:
                if (callBcak != null) {
                    callBcak.setOnClickListener(2);
                }
                break;
            case R.id.t3:
                if (callBcak != null) {
                    callBcak.setOnClickListener(3);
                }
                break;
            case R.id.t4:
                if (callBcak != null) {
                    callBcak.setOnClickListener(4);
                }
                break;

        }

    }
}
