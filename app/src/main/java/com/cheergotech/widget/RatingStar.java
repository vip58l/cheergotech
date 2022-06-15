package com.cheergotech.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.cheergotech.R;
import com.cheergotech.listen.CallBcak;

/**
 * Created by Michael on 2019/11/1.
 */
public class RatingStar extends View {

    private int normalId;
    private int focusId;
    private Bitmap normalImg;
    private Bitmap focusImg;
    private int number;
    private int w1;
    private int h1;
    private int marginLeft;
    private int marginTop;
    private int marginBottom;
    private int marginRight;
    private int height;
    private int width;
    private int p;
    private float w0;
    private int i0;
    private int mGrade;
    public CallBcak callBcak;
    private TextView text;
    private String[] stringArray;

    public void setCallBcak(CallBcak callBcak) {
        this.callBcak = callBcak;
    }

    public RatingStar(Context context) {
        this(context, null);
    }

    public RatingStar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RatingStar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RatingStar);
        normalId = array.getResourceId(R.styleable.RatingStar_starNormal, 0);
        focusId = array.getResourceId(R.styleable.RatingStar_starFocus, 0);
        normalImg = BitmapFactory.decodeResource(getResources(), normalId);
        focusImg = BitmapFactory.decodeResource(getResources(), focusId);
        number = array.getInteger(R.styleable.RatingStar_starNumber, 5);
        array.recycle();
        i0 = -1;
        stringArray = context.getResources().getStringArray(R.array.table3);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        w1 = normalImg.getWidth();
        h1 = normalImg.getHeight();
        //中间间隔
        p = 30;
        marginTop = 20;
        marginBottom = 20;
        marginLeft = 20;
        marginRight = 20;
        height = h1 + marginTop + marginBottom;
        width = w1 * number + (number - 1) * p + marginLeft + marginRight;
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < number; i++) {
            if (i <= i0) {
                canvas.drawBitmap(focusImg, i * w1 + marginLeft + i * p, marginTop, null);
                mGrade = i + 1;
            } else {
                canvas.drawBitmap(normalImg, i * w1 + marginLeft + i * p, marginTop, null);
            }
        }

        if (callBcak != null) {
            callBcak.setOnClickListener(mGrade);
        }
        if (text != null) {
            if (mGrade > 0) {
                text.setText(stringArray[mGrade - 1]);
            }

        }

        Log.e("msg", "我被调用了！" + mGrade);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (isEnabled() == false) {
            return false;
        }

        float x = event.getX();//相对于控件自身的距离
        //event.getRawX() 相对于屏幕的距离
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                //case MotionEvent.ACTION_UP:
                w0 = getWidth() / 5;
                i0 = (int) (x / w0);
                //性能优化，减少onDraw()调用
                if (mGrade == i0 + 1) {
                    return true;
                }
                invalidate();
                break;
        }
        return true;
    }

    /**
     * 设置默认选中
     *
     * @param canvas
     */
    public void setnumber(int canvas) {
        i0 = canvas - 1;
        invalidate();
    }

    public int getmGrade() {
        return mGrade;
    }


    /**
     * 设置文字内容
     *
     * @param text
     */
    public void settextMsg(TextView text) {
        this.text = text;

    }

}
