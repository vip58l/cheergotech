package com.cheergotech.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cheergotech.Base.BaseFrameLayout;
import com.cheergotech.R;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/7/1817:31
 * Description
 */
public class widgeiimage32 extends BaseFrameLayout {
    LinearLayout line1;
    ImageView image;
    TextView describes;
    TextView rec1;
    TextView stitle;

    public widgeiimage32(@NonNull Context context) {
        super(context);
    }

    public widgeiimage32(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public widgeiimage32(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init(Context context, AttributeSet attrs) {

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.widgeiimage32);
        String tt_name1 = a.getString(R.styleable.widgeiimage32_tt_name1);
        String tt_name2 = a.getString(R.styleable.widgeiimage32_tt_name2);
        boolean isb = a.getBoolean(R.styleable.widgeiimage32_tt_show, false);
        a.recycle();

        inflate(context, R.layout.itemlayout, this);
        rec1 = findViewById(R.id.rec1);
        stitle = findViewById(R.id.stitle);
        describes = findViewById(R.id.describes);
        image = findViewById(R.id.image);
        line1 = findViewById(R.id.line1);
        if (!TextUtils.isEmpty(tt_name1)) {
            stitle.setText(tt_name1);
        }
        if (!TextUtils.isEmpty(tt_name2)) {
            describes.setText(tt_name2);
        }
        if (isb) {
            this.rec1.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onClick(View v) {

    }


    public void setRec1() {
        this.rec1.setVisibility(View.VISIBLE);
    }
}
