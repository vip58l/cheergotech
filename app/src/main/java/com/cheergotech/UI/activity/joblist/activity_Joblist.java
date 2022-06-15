package com.cheergotech.UI.activity.joblist;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.UI.activity.adapter.viepagePagerAdapter;
import com.cheergotech.UI.activity.joblist.fragment.pagejob;
import com.cheergotech.ulist.Config;
import com.cheergotech.widget.widgetimage16;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作业列表
 * https://blog.csdn.net/qq_19688207/article/details/115666841
 */
public class activity_Joblist extends BaseActivity {
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.t1)
    widgetimage16 t1;
    @BindView(R.id.t2)
    widgetimage16 t2;
    @BindView(R.id.t3)
    widgetimage16 t3;

    public static void setAction(Activity context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_Joblist.class);
        context.startActivityForResult(intent, Config.sussess);
    }

    @Override
    protected int getview() {
        return R.layout.activity_joblist;
    }

    @Override
    public void iniview() {
        if (fragments.size() == 0) {
            //查询类型 0：全部 1：未批改 2：已批改
            fragments.add(new pagejob(0));
            fragments.add(new pagejob(1));
            fragments.add(new pagejob(2));
        }
        viewpager.setAdapter(new viepagePagerAdapter(getSupportFragmentManager(), fragments, 0));
        viewpager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                paycolor(position);
            }
        });
        viewpager.setOffscreenPageLimit(3);
    }


    @Override
    public void initData() {
    }

    @Override
    @OnClick({R.id.t1, R.id.t2, R.id.t3, R.id.mysubbotton})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.t1:
                paycolor(0);
                break;
            case R.id.t2:
                paycolor(1);
                break;
            case R.id.t3:
                paycolor(2);
                break;
            case R.id.mysubbotton:
                //发布作业
                activity_jobrelease.setAction(activity);
                break;
        }

    }

    private void paycolor(int type) {
        {
            viewpager.setCurrentItem(type);
            t1.setBooleanbg(false);
            t2.setBooleanbg(false);
            t3.setBooleanbg(false);
        }

        switch (type) {
            case 0:
                t1.setBooleanbg(true);
                break;
            case 1:
                t2.setBooleanbg(true);
                break;
            case 2:
                t3.setBooleanbg(true);
                break;
        }
    }

    @Override
    public void OnEorr() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Config.sussess && resultCode == Config.setResult) {
            for (Fragment fragment : fragments) {
                pagejob pagejob = (com.cheergotech.UI.activity.joblist.fragment.pagejob) fragment;
                pagejob.OnLoad(1);
                viewpager.setCurrentItem(0);
            }

        }
    }
}