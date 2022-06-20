package com.cheergotech.UI.activity.Login;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.UI.activity.Web.DyWebActivity;
import com.cheergotech.UI.activity.main.MainActivity1;
import com.cheergotech.UI.model.CahtAPI;
import com.cheergotech.ulist.Toasts;
import com.cheergotech.ulist.Config;

import butterknife.BindView;
import butterknife.OnClick;

public class activity_register extends BaseActivity {
    private static final String TAG = activity_register.class.getName();
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.passwod)
    EditText passwod;
    @BindView(R.id.code)
    EditText code;
    @BindView(R.id.getcode)
    TextView getcode;
    @BindView(R.id.check_login)
    CheckBox check_login;
    @BindView(R.id.tv_privacy)
    TextView tv_privacy;


    public static void setAction(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_register.class);
        context.startActivity(intent);
    }

    @Override
    protected int getview() {
        return R.layout.activity_login_reg;
    }

    @Override
    public void iniview() {
        handler = new myHandler();
        check_login.setChecked(msgconfig.isCheck_register() ? true : false);
        interceptHyperLink(tv_privacy); //点击转换并打开连接
    }

    @Override
    public void initData() {

    }

    @Override
    @OnClick({R.id.sendreg, R.id.getcode, R.id.check_login, R.id.back})
    public void OnClick(View v) {
        String user = phone.getText().toString().trim();
        String pwd = passwod.getText().toString().trim();
        String textcode = code.getText().toString().trim();
        switch (v.getId()) {
            case R.id.sendreg:
                reginit(user, pwd, textcode);
                break;
            case R.id.getcode:
                regcode(user, pwd);
                break;
            case R.id.check_login:
                msgconfig.setCheck_register(check_login.isChecked() ? true : false);
                break;
            case R.id.back:
                finish();
                break;
        }

    }

    /**
     * 获取验证码
     *
     * @param user
     * @param pwd
     */
    private void regcode(String user, String pwd) {
        if (TextUtils.isEmpty(user)) {
            Toasts.toastMessage(getString(R.string.t7));
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            Toasts.toastMessage(getString(R.string.t6));
            return;
        }
        handler.sendEmptyMessageDelayed(Config.sussess, delayMillis);
    }

    @Override
    public void OnEorr() {

    }

    /**
     * 提交帐号注册
     *
     * @param user
     * @param pwd
     * @param textcode
     */
    public void reginit(String user, String pwd, String textcode) {

        if (TextUtils.isEmpty(user)) {
            Toasts.toastMessage(getString(R.string.t1));
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            Toasts.toastMessage(getString(R.string.t2));
            return;
        }
        if (TextUtils.isEmpty(textcode)) {
            Toasts.toastMessage(getString(R.string.t4));
            return;
        }
        if (!check_login.isChecked()) {
            Toasts.toastMessage(getString(R.string.t3));
            return;
        }

        showdialog("正在注册");

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mydismiss();
                handler.removeMessages(Config.sussess);
                MainActivity1.setAction(context);
            }
        }, 1500);

    }

    class myHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case Config.sussess:
                    delay--;
                    if (delay > 0) {
                        getcode.setText(String.format("%s秒", delay));
                        sendEmptyMessageDelayed(Config.sussess, delayMillis);
                        getcode.setEnabled(false);
                    } else {
                        delay = 60;
                        getcode.setText(R.string.t5);
                        getcode.setEnabled(true);
                    }
                    break;
            }
        }
    }


    /**
     * 用于跳转隐私协议
     * 拦截超链接
     *
     * @param tv
     */
    public void interceptHyperLink(TextView tv) {
        //添加这句话，否则点击不生效
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        CharSequence text = tv.getText();
        if (text instanceof Spannable) {
            int end = text.length();
            Spannable spannable = (Spannable) tv.getText();
            NoUnderlineSpan noUnderlineSpan = new NoUnderlineSpan();
            spannable.setSpan(noUnderlineSpan, 0, text.length(), Spanned.SPAN_MARK_MARK);
            URLSpan[] urlSpans = spannable.getSpans(0, end, URLSpan.class);
            if (urlSpans.length == 0) {
                return;
            }
            SpannableStringBuilder ssb = new SpannableStringBuilder(text);
            // 循环遍历并拦截 所有http://开头的链接
            for (URLSpan uri : urlSpans) {
                String url = uri.getURL();
                if (url.indexOf("https://") == 0 || url.indexOf("http://") == 0) {
                    CustomUrlSpan customUrlSpan = new CustomUrlSpan(context, url);
                    //给链接设置样式等，例如链接处的下划线，字体颜色等，及其单击事件的添加
                    ssb.setSpan(customUrlSpan, spannable.getSpanStart(uri), spannable.getSpanEnd(uri), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

                    //设置文字颜色
                    ForegroundColorSpan span1 = new ForegroundColorSpan(Color.parseColor("#000000"));
                    ForegroundColorSpan span2 = new ForegroundColorSpan(Color.parseColor("#000000"));
                    ssb.setSpan(span1, 7, 19, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    ssb.setSpan(span2, 20, 33, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    ssb.removeSpan(uri);//解决方法 不然ClickableSpan会无效无法拦截
                }
            }
            tv.setText(ssb);
        }
    }

    /**
     * // 在这里可以做任何自己想要的处理
     */
    public class CustomUrlSpan extends ClickableSpan {
        private final Context context;
        private final String url;

        public CustomUrlSpan(Context context, String url) {
            this.context = context;
            this.url = url;
        }

        @Override
        public void onClick(View widget) {
            boolean abc1 = url.contains("abc1");
            boolean abc2 = url.contains("abc2");

            if (abc1) {
                //用户协议
                DyWebActivity.starAction(context, CahtAPI.useing, getString(R.string.public0));
            }

            if (abc2) {
                //隐私协议
                DyWebActivity.starAction(context, CahtAPI.toprivate, getString(R.string.public1));
            }

        }
    }

    /**
     * 去除超链接的下划线
     */
    public class NoUnderlineSpan extends UnderlineSpan {

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setUnderlineText(false);
        }
    }

}
