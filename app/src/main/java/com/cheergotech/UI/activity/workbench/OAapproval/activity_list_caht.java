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
import com.cheergotech.ulist.Toasts;
import com.cheergotech.widget.Backtitle;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/7/158:49
 * Description 发起审批-事假-通过聊天发送审批
 */
public class activity_list_caht extends BaseActivity {

    @BindView(R.id.backtitle)
    Backtitle backtitle;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    public static void setAction(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_list_caht.class);
        context.startActivity(intent);
    }

    @Override
    protected int getview() {
        return R.layout.activity_list_caht;
    }

    @Override
    public void iniview() {
        for (int i = 0; i < 20; i++) {
            list.add("https://pics3.baidu.com/feed/ac6eddc451da81cbaa6415b752fc731c0824316a.jpeg?token=47becdadf2a019281a93152e4396d0ac");
        }
        backtitle.findViewById(R.id.imgrhig).setVisibility(View.GONE);
        backtitle.findViewById(R.id.backtitle).setVisibility(View.VISIBLE);
        recyclerview.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true));
        recyclerview.setAdapter(adapter = new Adapter(context, list, Adapter.chatright, new CallBcak() {
            @Override
            public void Onfall() {

            }

            @Override
            public void setOnClickListener(int position) {
                Object o = list.get(position);
                Toasts.toastMessage("" + position);

            }
        }));
    }

    @Override
    public void initData() {

    }

    @Override
    @OnClick({R.id.myonclick})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.myonclick:
                break;
        }
    }

    @Override
    public void OnEorr() {

    }
}
