package com.cheergotech.UI.activity.workbench.Clockin;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.UI.activity.adapter.Adapter;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.Config;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 统计
 */
public class activity_count_Statistics extends BaseActivity {

    private static final String TAG = activity_count_Statistics.class.getName();
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.tt1)
    TextView tt1;
    @BindView(R.id.tt2)
    TextView tt2;

    public static void setAction(Activity context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_count_Statistics.class);
        context.startActivityForResult(intent, Config.sussess);
    }

    @Override
    protected int getview() {
        return R.layout.activity_count_statistics;
    }

    @Override
    public void iniview() {
        String[] stringArray = getResources().getStringArray(R.array.table5);
        list.addAll(Arrays.asList(stringArray));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter = new Adapter(context, this.list, Adapter.Statistics, new CallBcak() {
            @Override
            public void Onfall() {

            }

            @Override
            public void setOnClickListener(int position) {
                Log.d(TAG, "setOnClickListener: ");
            }
        }));
    }

    @Override
    public void initData() {

    }

    @Override
    @OnClick({R.id.tt1, R.id.tt2, R.id.query})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.tt1:
                tt1.setBackground(getDrawable(R.drawable.itemleft));
                tt1.setTextColor(getResources().getColor(R.color.white));
                tt2.setBackground(null);
                tt2.setTextColor(getResources().getColor(R.color.ff202020));
                break;
            case R.id.tt2:
                tt1.setBackground(null);
                tt1.setTextColor(getResources().getColor(R.color.ff202020));
                tt2.setTextColor(getResources().getColor(R.color.white));
                tt2.setBackground(getDrawable(R.drawable.itemrig));
                break;
            case R.id.query:
                //查看规则
                activity_attendancerules.setAction(activity);
                break;
        }
    }

    @Override
    public void OnEorr() {

    }
}