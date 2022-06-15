package com.cheergotech.Base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cheergotech.R;
import com.cheergotech.UI.activity.adapter.Adapter;
import com.cheergotech.UI.dialog.loadlogin;
import com.cheergotech.UI.model.Msgconfig;
import com.cheergotech.UI.model.UserInfo;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {
    public Context context;
    public Activity activity;
    public Unbinder bind;
    public Adapter adapter, adapter2;
    public UserInfo userinfo;
    public Msgconfig msgconfig;
    public int type;
    public List<Object> list = new ArrayList<>();
    public List<Object> list2 = new ArrayList<>();
    public loadlogin dialogLoading;
    public ProgressDialog progressDialog;
    public Bundle savedInstanceState;
    public Handler handler = new Handler();
    public View info;
    public Gson gson = new Gson();
    public int totalPage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getview(), null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        activity = (Activity) getContext();
        bind = ButterKnife.bind(this, view);
        this.info = view;
        this.savedInstanceState = savedInstanceState;
        userinfo = UserInfo.getInstance();   //初始化用户相关
        msgconfig = Msgconfig.getInstance(); //初始化登录或注册选中及配置
        progressDialog = new ProgressDialog(context);
        iniview();
    }

    protected abstract int getview();

    public abstract void iniview();

    public abstract void initData();

    public abstract void OnClick(View v);

    public abstract void OnEorr();

    @Override
    public void onDestroy() {
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
    public void mydialogshow(String msg) {
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
    public void mydialogshow() {
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
        progressDialog.setMessage(getString(R.string.tv_ss_dialog_msg));
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
