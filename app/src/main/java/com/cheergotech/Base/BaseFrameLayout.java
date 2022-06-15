package com.cheergotech.Base;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFrameLayout extends FrameLayout {
    public Context context;
    public Activity activity;
    public Unbinder bind;

    public BaseFrameLayout(@NonNull Context context) {
        this(context, null);
    }

    public BaseFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        this.activity = (Activity) context;
        init(context, attrs);
        bind = ButterKnife.bind(this);
    }

    public abstract void init(Context context, AttributeSet attrs);

    public abstract void onClick(View v);

}
