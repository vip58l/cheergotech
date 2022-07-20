package com.cheergotech.UI.activity.workbench.Clockin;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.UI.activity.adapter.Adapter;
import com.cheergotech.UI.dialog.DialogMessage17;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.Config;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 外勤打卡
 */
public class activity_load_position extends BaseActivity {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    public static void setAction(Activity context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_load_position.class);
        context.startActivityForResult(intent, Config.sussess);
    }

    @Override
    protected int getview() {
        return R.layout.activity_load_position;
    }

    @Override
    public void iniview() {
        for (int i = 0; i < 5; i++) {
            list.add("https://img-blog.csdnimg.cn/20201014180756916.png?x-oss-process=image/resize,m_fixed,h_64,w_64");
        }
        recyclerview.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true));
        recyclerview.setAdapter(adapter = new Adapter(context, list, Adapter.bus_Img3, new CallBcak() {
            @Override
            public void delete(int position) {
                Object o = list.get(position);
                list.remove(o);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void setOnClickListener(int position) {
                Object o = list.get(position);
                adapter.notifyDataSetChanged();
            }
        }));
    }

    @Override
    public void initData() {

    }

    @Override
    @OnClick({R.id.send1})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.send1:
                DialogMessage17.show(context, "信息不能为空", "外勤信息须全部填写才能提交", null);
                break;
        }
    }

    @Override
    public void OnEorr() {

    }


}