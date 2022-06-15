package com.cheergotech.UI.activity.leave;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.UI.activity.adapter.viepagePagerAdapter;
import com.cheergotech.UI.activity.leave.fragment.leave_page;
import com.cheergotech.ulist.Config;
import com.cheergotech.widget.widgetimage16;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 请假列表 等待审批
 */
public class activity_Leave_approval extends BaseActivity {
    @BindView(R.id.t1)
    widgetimage16 t1;
    @BindView(R.id.t2)
    widgetimage16 t2;
    @BindView(R.id.t3)
    widgetimage16 t3;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    leave_page page1 = new leave_page(0);
    leave_page page2 = new leave_page(1);
    leave_page page3 = new leave_page(2);

    public static void setAction(Activity context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_Leave_approval.class);
        context.startActivityForResult(intent, Config.sussess);
    }

    @Override
    protected int getview() {
        return R.layout.activity_leave_approval;
    }

    @Override
    public void iniview() {
        if (fragments.size() == 0) {
            fragments.add(page1);
            fragments.add(page2);
            fragments.add(page3);
        }
        viewpager.setAdapter(new viepagePagerAdapter(getSupportFragmentManager(), fragments, 0));
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                paycolor(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewpager.setOffscreenPageLimit(3);
    }

    @Override
    public void initData() {

    }

    @Override
    public void OnEorr() {

    }

    @Override
    @OnClick({R.id.t1, R.id.t2, R.id.t3})
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Config.sussess && resultCode == Config.setResult) {
            page1.OnLoad(1);
            page2.OnLoad(1);
            page3.OnLoad(1);
        }
    }
}