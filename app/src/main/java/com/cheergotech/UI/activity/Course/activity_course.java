package com.cheergotech.UI.activity.Course;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.Base.DemoApplication;
import com.cheergotech.R;
import com.cheergotech.UI.activity.adapter.viepagePagerAdapter;
import com.cheergotech.UI.dialog.DialogMessage9;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.Config;
import com.cheergotech.ulist.Toasts;
import com.cheergotech.widget.widgetimage8;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 课程信息
 */
public class activity_course extends BaseActivity {
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.w1)
    widgetimage8 w1;
    @BindView(R.id.w2)
    widgetimage8 w2;
    @BindView(R.id.w3)
    widgetimage8 w3;
    @BindView(R.id.w4)
    widgetimage8 w4;
    @BindView(R.id.w5)
    widgetimage8 w5;
    @BindView(R.id.w6)
    widgetimage8 w6;
    @BindView(R.id.w7)
    widgetimage8 w7;

    public static void setAction(Activity context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_course.class);
        context.startActivityForResult(intent, Config.sussess);
    }

    @Override
    protected int getview() {
        return R.layout.activity_course;
    }

    @Override
    public void iniview() {
        if (fragments.size() == 0) {
            fragments.add(new com.cheergotech.UI.activity.Course.fragment.page(1));
            fragments.add(new com.cheergotech.UI.activity.Course.fragment.page(2));
            fragments.add(new com.cheergotech.UI.activity.Course.fragment.page(3));
            fragments.add(new com.cheergotech.UI.activity.Course.fragment.page(4));
            fragments.add(new com.cheergotech.UI.activity.Course.fragment.page(5));
            fragments.add(new com.cheergotech.UI.activity.Course.fragment.page(6));
            fragments.add(new com.cheergotech.UI.activity.Course.fragment.page(7));
        }
        viewpager.setAdapter(new viepagePagerAdapter(getSupportFragmentManager(), fragments, 0));
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int type) {
                switch (type) {
                    case 0:
                        setpint(w1);
                        break;
                    case 1:
                        setpint(w2);
                        break;
                    case 2:
                        setpint(w3);
                        break;
                    case 3:
                        setpint(w4);
                        break;
                    case 4:
                        setpint(w5);
                        break;
                    case 5:
                        setpint(w6);
                        break;
                    case 6:
                        setpint(w7);
                        break;
                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        w1.performClick();
    }

    @Override
    public void initData() {


    }

    @Override
    @OnClick({R.id.share, R.id.w1, R.id.w2, R.id.w3, R.id.w4, R.id.w5, R.id.w6, R.id.w7,})
    public void OnClick(View v) {
        if (R.id.share != v.getId()) {
            tosettoBackground();
        }
        switch (v.getId()) {
            case R.id.share:
                //分享到朋友圈
                DialogMessage9.show(context, new CallBcak() {
                    @Override
                    public void setOnClickListener(int type) {
                        switch (type) {
                            case 1:
                                if (!isWeixinAvilible()) {
                                    Toasts.toastMessage(getString(R.string.tss));
                                }
                                break;
                            case 2:
                                break;
                        }

                    }
                });
                break;
            case R.id.w1:
                w1.settoBackground(true);
                viewpager.setCurrentItem(0);
                break;
            case R.id.w2:
                w2.settoBackground(true);
                viewpager.setCurrentItem(1);
                break;
            case R.id.w3:
                w3.settoBackground(true);
                viewpager.setCurrentItem(2);
                break;
            case R.id.w4:
                w4.settoBackground(true);
                viewpager.setCurrentItem(3);
                break;
            case R.id.w5:
                w5.settoBackground(true);
                viewpager.setCurrentItem(4);
                break;
            case R.id.w6:
                w6.settoBackground(true);
                viewpager.setCurrentItem(5);
                break;
            case R.id.w7:
                w7.settoBackground(true);
                viewpager.setCurrentItem(6);
                break;
        }
    }

    @Override
    public void OnEorr() {

    }


    /**
     * 全部时间默认未选中
     */
    public void tosettoBackground() {
        w1.settoBackground(false);
        w2.settoBackground(false);
        w3.settoBackground(false);
        w4.settoBackground(false);
        w5.settoBackground(false);
        w6.settoBackground(false);
        w7.settoBackground(false);
    }


    public void setpint(widgetimage8 w) {
        tosettoBackground();
        w.settoBackground(true);
    }

    /**
     * 判断手机是否安装微信
     *
     * @return
     */
    public static boolean isWeixinAvilible() {
        final PackageManager packageManager = DemoApplication.instance().getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String packageName = pinfo.get(i).packageName;
                if (packageName.equals("com.tencent.mm")) {
                    return true;
                }
            }

        }
        return false;

    }

    public void getWechatApi() {
        try {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            ComponentName cmp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setComponent(cmp);
            context.startActivity(intent);
        } catch (Exception e) {
            Toasts.show(context, "检查到您手机没有安装微信，请安装后使用该功能");
        }
    }

    public void getmobileqqApi() {

        try {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            ComponentName cmp = new ComponentName("com.tencent.mobileqq", "com.tencent.mobileqq.activity.SplashActivity");
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setComponent(cmp);
            context.startActivity(intent);
        } catch (Exception e) {
            Toasts.show(context, "检查到您手机没有安装QQ，请安装后使用该功能");
        }
    }


}