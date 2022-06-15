package com.cheergotech.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cheergotech.Base.BaseFrameLayout;
import com.cheergotech.R;
import com.cheergotech.ulist.Toasts;

/**
 * 选中未选中
 */
public class widgetimage11 extends BaseFrameLayout {

    ImageView image;
    Drawable drawable1, drawable2;
    boolean is;

    public widgetimage11(@NonNull Context context) {
        this(context, null);
    }

    public widgetimage11(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public widgetimage11(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @Override
    public void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.widgetimage11);
        drawable1 = a.getDrawable(R.styleable.widgetimage11_w11_src1);
        drawable2 = a.getDrawable(R.styleable.widgetimage11_w11_src1);
        a.recycle();

        inflate(context, R.layout.item_img_dbxs, this);
        image = findViewById(R.id.image);
        image.setImageDrawable(drawable1);
        setOnClickListener(this::onClick);


    }

    @Override
    public void onClick(View v) {
        Toasts.show("" + is);
        is = is ? false : true;
        setDrawable(is);
    }

    public void setDrawable(boolean b) {
        image.setImageDrawable(b ? drawable1 : drawable2);
    }

}
