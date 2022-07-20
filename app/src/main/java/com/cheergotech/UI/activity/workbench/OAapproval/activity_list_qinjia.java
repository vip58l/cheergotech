package com.cheergotech.UI.activity.workbench.OAapproval;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.ulist.Toasts;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/7/1910:14
 * Description
 */
public class activity_list_qinjia extends BaseActivity {
    @BindView(R.id.widgetimage24)
    com.cheergotech.widget.widgetimage24 widgetimage24;

    public static void setAction(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_list_qinjia.class);
        context.startActivity(intent);
    }

    @Override
    protected int getview() {
        return R.layout.activity_list_qinjia;
    }

    @Override
    public void iniview() {

    }

    @Override
    public void initData() {

    }

    @Override
    @OnClick({R.id.send1, R.id.send2})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.send1:
                Toasts.toastMessage("111");
                break;
            case R.id.send2:
                Toasts.toastMessage("222");
                break;
        }

    }

    @Override
    public void OnEorr() {

    }
}
