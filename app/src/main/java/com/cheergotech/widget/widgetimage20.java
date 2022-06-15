package com.cheergotech.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cheergotech.Base.BaseFrameLayout;
import com.cheergotech.R;

public class widgetimage20 extends BaseFrameLayout {
    TextView username;
    ImageView del1;

    public widgetimage20(@NonNull Context context) {
        super(context);
    }

    public widgetimage20(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public widgetimage20(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.widgetimage20);
        String titlename = a.getString(R.styleable.widgetimage20_w20_name);
        String msg1 = a.getString(R.styleable.widgetimage20_w20_msg1);
        String sg2 = a.getString(R.styleable.widgetimage20_w20_msg2);
        a.recycle();

        inflate(context, R.layout.widgettext20, this);
        username = findViewById(R.id.username);
        del1 = findViewById(R.id.del1);
        username.setText(titlename);
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 0) {
                    del1.setVisibility(VISIBLE);
                } else {
                    del1.setVisibility(GONE);
                }

            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    public String getUsername() {
        return username.getText().toString().trim();
    }


}
