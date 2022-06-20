package com.cheergotech.UI.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.cheergotech.R;
import com.cheergotech.UI.activity.Login.activity_login;
import com.cheergotech.UI.activity.Web.DyWebActivity;
import com.cheergotech.UI.model.CahtAPI;
import com.cheergotech.UI.model.Msgconfig;


public class AgreeDialog extends Dialog {

    private TextView tv_content;
    private Context mContext;

    public AgreeDialog(@NonNull Context context) {
        super(context);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialgo_agreement);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCancelable(false);

        //自定义Dialog宽度
        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        Point size = new Point();
        d.getSize(size);
        p.width = (int) ((size.x) * 0.7);        //设置为屏幕的0.7倍宽度
        getWindow().setAttributes(p);
        tv_content = findViewById(R.id.tv_content);
        //同意进入
        findViewById(R.id.tv_yes).setOnClickListener(view -> {
            dismiss();
            Msgconfig.getInstance().setIslogin(true);
            activity_login.setAction(getContext());
        });
        //拒绝进入
        findViewById(R.id.tv_no).setOnClickListener(view -> {
            dismiss();
            activity_login.setAction(getContext());
        });

        tv_content.setText(mContext.getString(R.string.dialog4));
        tv_content.append("《");
        tv_content.append(getTv1());
        tv_content.append("》和《");
        tv_content.append(getTv2());
        tv_content.append("》");
        tv_content.append(mContext.getString(R.string.dialog5));
    }

    private SpannableStringBuilder getTv1() {
        String msg2 = getContext().getString(R.string.tm18);
        SpannableStringBuilder spannable1 = new SpannableStringBuilder(msg2);
        tv_content.setMovementMethod(LinkMovementMethod.getInstance());
        spannable1.setSpan(new ForegroundColorSpan(((Activity) mContext).getResources().getColor(R.color.shapa)), 0, spannable1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable1.setSpan(new TextClick1(), 0, spannable1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannable1;
    }

    private class TextClick1 extends ClickableSpan {
        @Override
        public void onClick(View widget) {
            DyWebActivity.starAction(getContext(), CahtAPI.useing, getContext().getString(R.string.my4));
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setUnderlineText(false);
        }
    }

    private SpannableStringBuilder getTv2() {
        String msg2 = getContext().getString(R.string.tm1811);
        SpannableStringBuilder spannable1 = new SpannableStringBuilder(msg2);
        tv_content.setMovementMethod(LinkMovementMethod.getInstance());
        spannable1.setSpan(new ForegroundColorSpan(((Activity) mContext).getResources().getColor(R.color.shapa)), 0, spannable1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable1.setSpan(new TextClick2(), 0, spannable1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannable1;
    }

    private class TextClick2 extends ClickableSpan {
        @Override
        public void onClick(View widget) {
            DyWebActivity.starAction(getContext(), CahtAPI.toprivate, getContext().getString(R.string.my5));
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setUnderlineText(false);
        }
    }
}
