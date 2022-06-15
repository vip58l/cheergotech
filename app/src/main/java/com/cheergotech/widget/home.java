package com.cheergotech.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cheergotech.Base.BaseFrameLayout;
import com.cheergotech.R;

import butterknife.ButterKnife;

public class home extends BaseFrameLayout {

    public home(@NonNull Context context) {
        this(context, null);
    }

    public home(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public home(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.home);
        String title_name = a.getString(R.styleable.home_home_name);
        Drawable drawable = a.getDrawable(R.styleable.home_home_src);
        a.recycle();

        inflate(context, R.layout.home_footetr, this);
        bind = ButterKnife.bind(this);
        ImageView image = findViewById(R.id.image);
        TextView msg = findViewById(R.id.msg);
        image.setImageDrawable(drawable);
        msg.setText(title_name);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (bind != null) {
            bind.unbind();
        }
    }
}
