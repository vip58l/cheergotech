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

public class widgetimage2 extends BaseFrameLayout {
    public widgetimage2(@NonNull Context context) {
        super(context);
    }

    public widgetimage2(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public widgetimage2(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.widgetimage2);
        String title_name = a.getString(R.styleable.widgetimage2_w211_name);
        Drawable drawable = a.getDrawable(R.styleable.widgetimage2_w211_src);
        int visibility1 = a.getInt(R.styleable.widgetimage2_w211_visibility1, -1);
        a.recycle();

        inflate(context, R.layout.widgetimage2, this);
        ImageView image = findViewById(R.id.img);
        TextView title = findViewById(R.id.title);
        title.setText(title_name);
        if (drawable != null) {
            image.setImageDrawable(drawable);
        }

    }

    @Override
    public void onClick(View v) {

    }
}
