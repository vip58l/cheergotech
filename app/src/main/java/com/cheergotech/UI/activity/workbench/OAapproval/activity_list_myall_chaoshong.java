package com.cheergotech.UI.activity.workbench.OAapproval;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.UI.activity.adapter.Adapter;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.widget.Backtitle;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/7/1910:14
 * OA审批-抄送我的
 */
public class activity_list_myall_chaoshong extends BaseActivity {
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.backtitle)
    Backtitle backtitle;

    public static void setAction(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_list_myall_chaoshong.class);
        context.startActivity(intent);
    }

    @Override
    protected int getview() {
        return R.layout.activity_list_pending;
    }

    @Override
    public void iniview() {
        backtitle.setconter("抄送我的");
        for (int i = 0; i < 50; i++) {
            list.add("" + i);
        }
        recyclerview.setLayoutManager(new LinearLayoutManager(context));
        recyclerview.setAdapter(adapter = new Adapter(context, list, Adapter.type132, new CallBcak() {
            @Override
            public void Onfall() {

            }

            @Override
            public void setOnClickListener(int position) {

            }
        }));
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
