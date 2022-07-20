package com.cheergotech.UI.activity.workbench.matter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.UI.activity.adapter.Adapter;
import com.cheergotech.UI.dialog.DialogMessage6;
import com.cheergotech.listen.CallBcak;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/7/1918:47
 * 我发起的事项-会议 –发起人查看页面
 * 事项-已完成-评优查看
 */
public class acetivity_pingyu extends BaseActivity {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    public static void setAction(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, acetivity_pingyu.class);
        context.startActivity(intent);
    }

    @Override
    protected int getview() {
        return R.layout.acetivity_pingyu;
    }

    @Override
    public void iniview() {
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
        recyclerview.setLayoutManager(new LinearLayoutManager(context));
        recyclerview.setAdapter(adapter = new Adapter(context, list, Adapter.type137, new CallBcak() {
            @Override
            public void setOnClickListener(int position) {

            }
        }));

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
