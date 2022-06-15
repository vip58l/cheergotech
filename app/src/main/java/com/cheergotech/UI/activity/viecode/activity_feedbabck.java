package com.cheergotech.UI.activity.viecode;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.UI.model.Datamodule;
import com.cheergotech.UI.model.subadvice;
import com.cheergotech.UI.model.userib;
import com.cheergotech.listen.CallBcak;

import java.util.List;

import butterknife.OnClick;

/**
 * 反馈与建议已提交
 */
public class activity_feedbabck extends BaseActivity {
    public static void setAction(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_feedbabck.class);
        context.startActivity(intent);
    }

    @Override
    protected int getview() {
        return R.layout.activity_feedbabck;
    }

    @Override
    public void iniview() {
        Datamodule.getInstance().queryadvice(1, 20, new CallBcak() {
            @Override
            public void OnSuccess(Object object) {
                userib userib = (com.cheergotech.UI.model.userib) object;
                List<subadvice> list = userib.getList();
            }

            @Override
            public void Onfall() {
            }
        });
    }


    @Override
    public void initData() {

    }

    @Override
    @OnClick({R.id.send_post})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.send_post:
                finish();
                break;
        }

    }

    @Override

    public void OnEorr() {

    }
}