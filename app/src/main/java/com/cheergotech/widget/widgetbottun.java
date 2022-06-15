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

public class widgetbottun extends BaseFrameLayout {

    public widgetbottun(@NonNull Context context) {
        super(context);
    }

    public widgetbottun(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public widgetbottun(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init(Context context, AttributeSet attrs) {

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.widgetbottun);
        String title_name = a.getString(R.styleable.widgetbottun_w_name);
        Drawable drawable = a.getDrawable(R.styleable.widgetbottun_w_src);
        int visibility1 = a.getInt(R.styleable.widgetbottun_w_visibility1, -1);
        int visibility2 = a.getInt(R.styleable.widgetbottun_w_visibility2, -1);
        a.recycle();

        inflate(context, R.layout.widgetbottun, this);
        TextView name = findViewById(R.id.name);
        ImageView image = findViewById(R.id.image);
        ImageView backright = findViewById(R.id.backright);

        name.setText(title_name);
        image.setImageDrawable(drawable);
        image.setVisibility(visibility1);
        backright.setVisibility(visibility2);

    }

    @Override
    public void onClick(View v) {

    }
}
