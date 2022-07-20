package com.cheergotech.UI.activity.workbench.matter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.UI.dialog.DialogMessage6;
import com.cheergotech.listen.CallBcak;

import butterknife.OnClick;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/7/1918:47
 * 我发起的事项-会议 –发起人查看页面
 */
public class acetivity_kuihuiyi extends BaseActivity {
    public static void setAction(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, acetivity_kuihuiyi.class);
        context.startActivity(intent);
    }

    @Override
    protected int getview() {
        return R.layout.acetivity_kuihuiyi;
    }

    @Override
    public void iniview() {

    }

    @Override
    public void initData() {

    }

    @Override

    @OnClick({R.id.closest})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.closest:
                DialogMessage6.show(context, "作废原因", "", "", "", new CallBcak() {
                    @Override
                    public void setMtext(String mtext) {

                    }
                });
                DialogMessage6.show(context, "取消原因", "", "", "", new CallBcak() {
                    @Override
                    public void setMtext(String mtext) {

                    }
                });
                break;
        }

    }

    @Override
    public void OnEorr() {

    }
}
