package com.cheergotech.Base;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.cheergotech.R;
import com.cheergotech.UI.activity.adapter.Adapter;
import com.cheergotech.listen.CallBcak;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 弹窗基类
 */
public abstract class BaseDialogShow extends BottomSheetDialog {
    public Unbinder bind;
    public CallBcak callBcak;
    public Adapter adapter;
    public Context context;
    public Activity activity;
    public int totalPage;
    public List<Object> list = new ArrayList<>();

    public BaseDialogShow(@NonNull Context context) {
        super(context, R.style.dialog);
        setContentView(getview());
        this.bind = ButterKnife.bind(this);
        this.context = context;
        this.activity = (Activity) context;
    }

    public abstract int getview();

    public abstract void OnClick(View v);

    public void WindowManager() {
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        getWindow().setGravity(Gravity.CENTER);
        getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
    }


    @Override
    public void dismiss() {
        super.dismiss();
        if (bind != null) {
            bind.unbind();
        }
    }
}
