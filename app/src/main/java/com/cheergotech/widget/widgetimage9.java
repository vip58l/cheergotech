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

/**
 * 错误显示界面
 */
public class widgetimage9 extends BaseFrameLayout {

    public widgetimage9(@NonNull Context context) {
        this(context, null);
    }

    public widgetimage9(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public widgetimage9(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @Override
    public void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.item_eorr);
        String title_name = a.getString(R.styleable.item_eorr_item_eorr_name);
        Drawable drawable = a.getDrawable(R.styleable.item_eorr_item_eorr_src1);
        a.recycle();

        inflate(context, R.layout.item_eorr, this);
        TextView title = findViewById(R.id.title);
        ImageView image = findViewById(R.id.image);
        title.setText(title_name);
        if (drawable != null) {
            image.setImageDrawable(drawable);
        }
    }

    @Override
    public void onClick(View v) {

    }
}
