package com.cheergotech.UI.activity.workbench.Clockin;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.UI.dialog.DialogMessage18;
import com.cheergotech.ulist.Config;

import butterknife.OnClick;

/**
 * 查看规则
 */
public class activity_attendancerules extends BaseActivity {

    public static void setAction(Activity context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_attendancerules.class);
        context.startActivityForResult(intent, Config.sussess);
    }

    @Override
    protected int getview() {
        return R.layout.activity_attendancerules;
    }

    @Override
    public void iniview() {
        DialogMessage18.show(context, null);

    }

    @Override
    public void initData() {

    }

    @Override
    @OnClick({R.id.tv_count})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.tv_count:
                activity_count_Statistics.setAction(activity);
                break;
        }

    }

    @Override
    public void OnEorr() {

    }
}