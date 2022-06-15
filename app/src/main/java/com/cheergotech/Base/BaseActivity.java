package com.cheergotech.Base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.cheergotech.R;
import com.cheergotech.UI.activity.adapter.Adapter;
import com.cheergotech.UI.dialog.loadlogin;
import com.cheergotech.UI.model.Msgconfig;
import com.cheergotech.UI.model.UserInfo;
import com.cheergotech.ulist.Constants;
import com.cheergotech.ulist.StatusBarUtil;
import com.google.gson.Gson;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {
    public Context context;
    public Activity activity;
    public Unbinder bind;
    public UserInfo userinfo;
    public Msgconfig msgconfig;
    public List<Object> list = new ArrayList<>();
    public List<Fragment> fragments = new ArrayList<>();
    public Adapter adapter;
    public loadlogin dialogLoading;
    public ProgressDialog progressDialog;
    public int totalPage;
    public Handler handler = new Handler();
    public int delayMillis = 1000; //倒计时
    public int delay = 60;          //验证码倒计时
    public Bundle savedInstanceState;
    public Gson gson = new Gson();

    public void setAction(Context context, Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(context, cls);
        context.startActivity(intent);
    }

    public void setAction(Context context, Class<?> cls, int id) {
        Intent intent = new Intent();
        intent.setClass(context, cls);
        intent.putExtra(Constants.id, id);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.transparencyBar(this, 2);
        setContentView(getview());
        bind = ButterKnife.bind(this);
        context = this;
        activity = this;
        this.savedInstanceState = savedInstanceState;
        progressDialog = new ProgressDialog(context);
        userinfo = UserInfo.getInstance();   //初始化用户相关
        msgconfig = Msgconfig.getInstance(); //初始化登录或注册选中及配置
        iniview();


    }

    protected abstract int getview();

    public abstract void iniview();

    public abstract void initData();

    public abstract void OnClick(View v);

    public abstract void OnEorr();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bind != null) {
            bind.unbind();
        }
    }

    /**
     * 弹出加载中
     *
     * @param msg
     */
    public void showdialog(String msg) {
        if (dialogLoading != null && dialogLoading.isShowing()) {
            dialogLoading.dismiss();
        }
        dialogLoading = loadlogin.show(context, msg, null);
    }

    /**
     * 弹出加载中
     *
     * @param
     */
    public void showdialog() {
        if (dialogLoading != null && dialogLoading.isShowing()) {
            dialogLoading.dismiss();
        }
        dialogLoading = loadlogin.show(context, "", null);
    }

    /**
     * 关闭弹窗
     */
    public void mydismiss() {
        if (dialogLoading != null) {
            dialogLoading.dismiss();
        }

    }

    /**
     * 加载中弹窗...
     */
    public void showDialog() {
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(true);
        progressDialog.setMessage(getString(R.string.tv_ss_dialog_msg2));
        progressDialog.show();
    }

    /**
     * 关加加载中...
     */
    public void dismissDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }


}
