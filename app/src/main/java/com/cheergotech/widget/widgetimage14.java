package com.cheergotech.widget;

import android.content.Context;
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
 *按住说话
 */
public class widgetimage14 extends BaseFrameLayout {

    ImageView imgage;
    TextView title;

    public widgetimage14(@NonNull Context context) {
        super(context);
    }

    public widgetimage14(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public widgetimage14(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.aumdilayout, this);
        imgage = findViewById(R.id.imgage);
        title = findViewById(R.id.title);
    }

    @Override
    public void onClick(View v) {

    }

    public void setTitle(String msg) {
        this.title.setText(msg);
    }

    public void setImgage(Drawable drawable) {
        this.imgage.setImageDrawable(drawable);
    }

}
