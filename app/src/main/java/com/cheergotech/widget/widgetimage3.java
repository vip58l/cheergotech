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

public class widgetimage3 extends BaseFrameLayout {
    public widgetimage3(@NonNull Context context) {
        super(context);
    }

    public widgetimage3(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public widgetimage3(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.widgetimage3);
        String title_name = a.getString(R.styleable.widgetimage3_w31_name);
        Drawable drawable = a.getDrawable(R.styleable.widgetimage3_w31_src);
        int visibility1 = a.getInt(R.styleable.widgetimage3_w31_visibility1, -1);
        int visibility2 = a.getInt(R.styleable.widgetimage3_w31_visibility2, -1);
        a.recycle();

        inflate(context, R.layout.widgetimage3, this);
        TextView title = findViewById(R.id.title);
        ImageView image = findViewById(R.id.image);
        if (visibility1 == 2) {
            image.setVisibility(GONE);
        }
        title.setText(title_name);

    }

    @Override
    public void onClick(View v) {

    }
}
