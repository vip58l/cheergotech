package com.cheergotech.UI.activity.Login;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.UI.activity.Web.DyWebActivity;
import com.cheergotech.UI.activity.main.MainActivity1;
import com.cheergotech.UI.model.CahtAPI;
import com.cheergotech.UI.model.Datamodule;
import com.cheergotech.UI.model.Handlertimeout;
import com.cheergotech.UI.model.Msgconfig;
import com.cheergotech.UI.model.appLoginDto;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.Config;
import com.cheergotech.ulist.KeyboardUtil;
import com.cheergotech.ulist.Logi;
import com.cheergotech.ulist.SystemUtil;
import com.cheergotech.ulist.Toasts;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 用户登录
 */
public class activity_login extends BaseActivity {

    private static final String TAG = activity_login.class.getName();
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.passwod)
    EditText passwod;
    @BindView(R.id.check_login)
    CheckBox check_login;
    @BindView(R.id.tv_privacy)
    TextView tv_privacy;
    @BindView(R.id.del1)
    ImageView del1;
    @BindView(R.id.del2)
    ImageView del2;

    public static void setAction(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_login.class);
        context.startActivity(intent);
    }

    public static void setAction(Context context, int type) {
        Intent intent = new Intent();
        intent.setClass(context, activity_login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected int getview() {
        return R.layout.activity_login;
    }

    @Override
    public void iniview() {
        check_login.setChecked(msgconfig.isCheck_login() ? true : false);
        interceptHyperLink(tv_privacy); //点击转换并打开连接
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                del1.setVisibility(editable.length() > 0 ? View.VISIBLE : View.GONE);
            }
        });
        passwod.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                del2.setVisibility(editable.length() > 0 ? View.VISIBLE : View.GONE);
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    @OnClick({R.id.sendreg, R.id.login, R.id.check_login, R.id.del1, R.id.del2})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.sendreg:
                //注册用户
                //activity_register.setAction(context);
                break;
            case R.id.login:
                loginit();
                break;
            case R.id.check_login:
                Msgconfig.getInstance().setCheck_login(check_login.isChecked() ? true : false);
                break;
            case R.id.del1:
                username.setText(null);
                break;
            case R.id.del2:
                passwod.setText(null);
                break;
        }

    }

    @Override
    public void OnEorr() {

    }


    /**
     * 初始登录平台
     */
    public void loginit() {
        KeyboardUtil.hideSoftInput(activity);
        String user = username.getText().toString().trim();
        String pwd = passwod.getText().toString().trim();
        if (TextUtils.isEmpty(user)) {
            Toasts.toastMessage(getString(R.string.t1));
            return;
        }

        if (TextUtils.isEmpty(pwd)) {
            Toasts.toastMessage(getString(R.string.t2));
            return;
        }

        if (user.length() < 11) {
            Logi.d(TAG, "loginit: 111");
            Toasts.toastMessage(getString(R.string.tass1));
            return;
        }

        if (!isMobileNO(user)) {
            Logi.d(TAG, "loginit:2222 ");
            Toasts.toastMessage(getString(R.string.tass1));
            return;
        }

        if (!check_login.isChecked()) {
            Toasts.toastMessage(getString(R.string.t3));
            return;
        }

        showdialog(getString(R.string.login));
        handlertimeout.sendEmptyMessage(Config.sussess);

        String format = String.format("%s%s", pwd.trim(), CahtAPI.sHZ2020);
        String md5 = Config.md5(format);
        appLoginDto appLoginDto = new appLoginDto();
        appLoginDto.setAccount(user.trim());
        appLoginDto.setChannel(2); //渠道:1-家长端,2-教师端,3-小程序家长端,4-小程序教师端
        appLoginDto.setDeviceId(TextUtils.isEmpty(SystemUtil.getandroid(context)) ? "0" : SystemUtil.getandroid(context));
        appLoginDto.setOsType(1);  //设备类型 1-安卓,2-ios
        appLoginDto.setCode("");
        appLoginDto.setPassword(md5);
        appLoginDto.setSharer("");
        appLoginDto.setShareCode("");
        appLoginDto.setValid("");
        Datamodule.getInstance().logdin(appLoginDto, new CallBcak() {
            @Override
            public void OnSuccess() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mydismiss();
                        MainActivity1.setAction(context);
                        finish();
                    }
                }, delayMillis);
            }

            @Override
            public void Onfall() {
                mydismiss();
            }
        });
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handlertimeout != null) {
            handlertimeout.removeMessages(Config.sussess);
        }
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    /**
     * 登录超时处理
     */
    private Handlertimeout handlertimeout = new Handlertimeout(30, new CallBcak() {
        @Override
        public void OnSuccess() {
            if (dialogLoading != null && !dialogLoading.isShowing()) {
                handlertimeout.removeMessages(Config.sussess);
            }
            Logi.d(TAG, "登录中请稍后");
        }

        @Override
        public void Onfall() {
            mydismiss();
            Toasts.toastMessage(context.getString(R.string.loging_msg));
        }
    });


    /**
     * 判断手机号
     * @param mobiles
     * @return
     */
    public boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(17[0-9])|(18[0-9])|(19[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

}
