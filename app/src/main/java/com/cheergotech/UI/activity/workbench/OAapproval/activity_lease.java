package com.cheergotech.UI.activity.workbench.OAapproval;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.UI.dialog.DialogMessage20;
import com.cheergotech.listen.CallBcak;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/7/199:32
 * Description 发起审批-租借 租借审批
 */
public class activity_lease extends BaseActivity {

    public static void setAction(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_lease.class);
        context.startActivity(intent);
    }

    @Override
    protected int getview() {
        return R.layout.activity_lease;
    }

    @Override
    public void iniview() {
        DialogMessage20.show(context, new CallBcak() {
            @Override
            public void Onfall() {

            }

            @Override
            public void setOnClickListener(int position) {

            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void OnClick(View v) {

    }

    @Override
    public void OnEorr() {

    }
}
