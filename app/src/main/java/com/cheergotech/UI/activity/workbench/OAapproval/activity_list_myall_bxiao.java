package com.cheergotech.UI.activity.workbench.OAapproval;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.UI.dialog.DialogMessage10;
import com.cheergotech.UI.dialog.DialogMessage6;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.widget.Backtitle;

import butterknife.BindView;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/7/1910:14
 * Description
 */
public class activity_list_myall_bxiao extends BaseActivity {

    @BindView(R.id.backtitle)
    Backtitle backtitle;

    public static void setAction(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_list_myall_bxiao.class);
        context.startActivity(intent);
    }

    @Override
    protected int getview() {
        return R.layout.activity_list_myall_bxiao;
    }

    @Override
    public void iniview() {

        backtitle.setconter("我发起的");
        for (int i = 0; i < 50; i++) {
            list.add("" + i);
        }





        DialogMessage6.show(context, "驳回原因", "", "", "", new CallBcak() {
            @Override
            public void Onfall() {

            }

            @Override
            public void setMtext(String msg) {


            }
        });
        DialogMessage6.show(context, "评论", "", "", "", "回复 组长：", new CallBcak() {
            @Override
            public void Onfall() {

            }

            @Override
            public void setMtext(String msg) {


            }
        });
        DialogMessage10.show(context, "通过申请", "是否通过本次申请？", "取消", "通过", new CallBcak() {
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
