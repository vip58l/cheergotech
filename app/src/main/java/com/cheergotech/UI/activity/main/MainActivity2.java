package com.cheergotech.UI.activity.main;

import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.UI.activity.adapter.viepagePagerAdapter;
import com.cheergotech.UI.activity.main.fragment.page1;
import com.cheergotech.UI.activity.main.fragment.page5;
import com.cheergotech.UI.model.Datamodule;
import com.cheergotech.UI.model.checkversion;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.Config;
import com.cheergotech.ulist.Toasts;
import com.cheergotech.ulist.update;
import com.cheergotech.widget.widgetimage15;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 主页
 */
public class MainActivity2 extends BaseActivity {
    private static final String TAG = MainActivity2.class.getName();
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.widgetimage1)
    widgetimage15 widgetimage1;
    @BindView(R.id.widgetimage2)
    widgetimage15 widgetimage2;
    page1 page1;
    page5 page5;
    private float mExitTime;

    public static void setAction(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MainActivity2.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected int getview() {
        return R.layout.activity_main2;
    }

    @Override
    public void iniview() {
        if (fragments.size() == 0) {
            fragments.add(page1 = new page1());
            fragments.add(page5 = new page5());
        }
        viewpager.setAdapter(new viepagePagerAdapter(getSupportFragmentManager(), fragments, 0));
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    widgetimage1.setSelect(true);
                    widgetimage2.setSelect(false);
                } else {
                    widgetimage1.setSelect(false);
                    widgetimage2.setSelect(true);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewpager.setCurrentItem(0);
        widgetimage1.setSelect(true);
        widgetimage2.setSelect(false);
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, page1).commit();
    }

    @Override
    public void initData() {

    }

    @Override
    @OnClick({R.id.widgetimage1, R.id.widgetimage2})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.widgetimage1:
                viewpager.setCurrentItem(0);
                widgetimage1.setSelect(true);
                widgetimage2.setSelect(false);
                //处理地图闪屏黑色
                if (page1.share != null) {
                    page1.share.setVisibility(View.GONE);
                }
                setfragment(page1);
                break;
            case R.id.widgetimage2:
                //处理地图闪屏黑色
                if (page1.share != null) {
                    page1.share.setVisibility(View.VISIBLE);
                }
                viewpager.setCurrentItem(1);
                widgetimage1.setSelect(false);
                widgetimage2.setSelect(true);
                setfragment(page5);
                break;
        }
    }


    public void setfragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, fragment).commit();
        Datamodule.getInstance().checkversionapp(callBcak);
    }

    @Override
    public void OnEorr() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (System.currentTimeMillis() - mExitTime > 2000) {
                Toasts.toastMessage(getString(R.string.exit));
                mExitTime = System.currentTimeMillis();//获取BACK返回当前时间
                return true;
            }


        }
        if (keyCode == KeyEvent.KEYCODE_MENU) {

        }
        if (keyCode == KeyEvent.KEYCODE_BACK) {

        }
        if (keyCode == KeyEvent.KEYCODE_HOME) {

        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 升级APP提示
     */
    private CallBcak callBcak = new CallBcak() {
        @Override
        public void Onfall() {

        }

        @Override
        public void OnSuccess(Object object) {
            checkversion checkversion = (com.cheergotech.UI.model.checkversion) object;
            //网络版本大于本地版本
            if (checkversion.getServerCode() > Config.getVersionCode()) {
                update.showUpdaloadDialog(activity, checkversion.getLoadUrl());

            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 800:
                //拿到回调权限开始更新APK
                update.updateapk(context, update.files);
                break;
            default:
                if (page1 != null) {
                    page1.onActivityResult(requestCode, resultCode, data);
                }
                break;
        }


    }


}