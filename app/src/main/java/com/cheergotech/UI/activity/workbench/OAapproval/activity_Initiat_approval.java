package com.cheergotech.UI.activity.workbench.OAapproval;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.UI.dialog.DialogMessage21;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/7/1415:58
 * Description 请假
 */
public class activity_Initiat_approval extends BaseActivity {

    public static void setAction(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_Initiat_approval.class);
        context.startActivity(intent);

        activity_list_caht.setAction(context);

        activity_selecclass.setAction(context);

        activity_select_quer.setAction(context);

        //日期
        DialogMessage21.show(context, null);
    }

    @Override
    protected int getview() {
        return R.layout.activity_initiat_approval;
    }

    @Override
    public void iniview() {

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
