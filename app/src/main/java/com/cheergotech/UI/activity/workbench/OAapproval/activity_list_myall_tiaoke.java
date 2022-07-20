package com.cheergotech.UI.activity.workbench.OAapproval;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.widget.Backtitle;

import butterknife.BindView;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/7/1910:14
 * Description
 */
public class activity_list_myall_tiaoke extends BaseActivity {

    @BindView(R.id.backtitle)
    Backtitle backtitle;

    public static void setAction(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_list_myall_tiaoke.class);
        context.startActivity(intent);
    }

    @Override
    protected int getview() {
        return R.layout.activity_list_myall_tiaoke;
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
