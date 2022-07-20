/**
 * Description :
 * 开发者 小清新 QQ804031885
 *
 * @author WSoban
 * @date 2021/3/3 0003
 */


package com.cheergotech.widget;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cheergotech.Base.BaseFrameLayout;
import com.cheergotech.R;
import com.cheergotech.listen.CallBcak;

public class Backtitle extends BaseFrameLayout {
    private static final String TAG = Backtitle.class.getName();
    public RelativeLayout layout0;
    public ImageView imgback, imgrhig, myonclick;
    public TextView backleft, title, backright;
    public LinearLayout line1;
    private String title_name, title_msg, title_right;
    float dimension;
    int titleview = 8;
    public boolean imgtop;
    public CallBcak callBcak;

    public CallBcak getCallBcak() {
        return callBcak;
    }

    public void setCallBcak(CallBcak callBcak) {
        this.callBcak = callBcak;
    }

    public Backtitle(@NonNull Context context) {
        super(context);
    }

    public Backtitle(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Backtitle(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @SuppressLint("WrongConstant")
    @Override
    public void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.backtitle);
        title_name = a.getString(R.styleable.backtitle_title_name);
        title_msg = a.getString(R.styleable.backtitle_title_msg);
        title_right = a.getString(R.styleable.backtitle_title_right);
        dimension = a.getDimension(R.styleable.backtitle_title_Size, 0);
        titleview = a.getInt(R.styleable.backtitle_title_visibility, 8);
        Drawable drawable = a.getDrawable(R.styleable.backtitle_title_src);
        a.recycle();

        inflate(getContext(), R.layout.item_var_back, this);
        layout0 = findViewById(R.id.layout0);
        imgback = findViewById(R.id.img_back);
        backleft = findViewById(R.id.backleft);
        line1 = findViewById(R.id.line1);
        imgrhig = findViewById(R.id.imgrhig);
        backleft = findViewById(R.id.backleft);
        title = findViewById(R.id.title);
        backright = findViewById(R.id.backright);
        myonclick = findViewById(R.id.myonclick);
        if (!TextUtils.isEmpty(title_name)) {
            title.setText(title_name);
        }
        if (!TextUtils.isEmpty(title_right)) {
            backright.setVisibility(VISIBLE);
            backright.setText(title_right);
        }
        if (dimension > 0) {
            title.setTextSize(dimension);
        }
        if (drawable != null) {
            myonclick.setImageDrawable(drawable);
        }
        imgback.setOnClickListener(this::onClick);
        line1.setOnClickListener(this::onClick);
        line1.setVisibility(titleview);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.img_back: {
                Context context = getContext();
                ((Activity) context).finish();
                break;
            }

            case R.id.line1: {
                //切换消息
                imgrhig.setImageResource(imgtop ? R.mipmap.boot : R.mipmap.top);
                imgtop = imgtop ? false : true;
                if (callBcak != null) {
                    callBcak.setOnboolean(imgtop);
                }
                break;
            }

        }
    }

    public void setBackleft(String msg) {
        backleft.setText(msg);
        backleft.setVisibility(VISIBLE);
    }

    public void setconter(String msg) {
        this.title.setText(msg);
    }

    public void setBackright(String msg) {
        backright.setText(msg);
        backright.setVisibility(VISIBLE);
    }

    /**
     * 透明背景
     */
    public void setBackground() {
        layout0.setBackground(getContext().getDrawable(R.color.transparent));
    }

    /**
     * 透明背景
     */
    public void settransparent() {
        layout0.setBackground(getContext().getDrawable(R.color.transparent));
    }

    public void settitleBackground(int corlo) {
        this.title.setTextColor(corlo);
    }

    public void setTitleName(String title_name) {
        this.title.setText(title_name);
    }
}
