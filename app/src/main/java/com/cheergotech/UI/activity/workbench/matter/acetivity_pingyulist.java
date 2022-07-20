package com.cheergotech.UI.activity.workbench.matter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
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
 * 事项-我参与的事项-评优查看(请投票)
 */
public class acetivity_pingyulist extends BaseActivity {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    public static void setAction(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, acetivity_pingyulist.class);
        context.startActivity(intent);
    }

    @Override
    protected int getview() {
        return R.layout.acetivity_pingyulist;
    }

    @Override
    public void iniview() {
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
        recyclerview.setLayoutManager(new GridLayoutManager(context,3));
        recyclerview.setAdapter(adapter = new Adapter(context, list, Adapter.type139, new CallBcak() {
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
