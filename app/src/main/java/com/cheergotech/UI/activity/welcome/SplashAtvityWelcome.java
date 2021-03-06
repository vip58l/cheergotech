package com.cheergotech.UI.activity.welcome;

import android.view.View;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.UI.activity.Login.activity_login;
import com.cheergotech.UI.activity.main.MainActivity1;
import com.cheergotech.UI.dialog.AgreeDialog;
import com.cheergotech.UI.model.Msgconfig;

public class SplashAtvityWelcome extends BaseActivity {

    private static final String TAG = SplashAtvityWelcome.class.getName();

    @Override
    protected int getview() {
        return R.layout.activity_welcome;
    }

    @Override
    public void iniview() {

      if (!Msgconfig.getInstance().isIslogin()){
          showAgree();
      }else {
          initData();
      }




    }

    @Override
    public void initData() {

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Msgconfig.getInstance() != null && Msgconfig.getInstance().isLogin()) {
                    //进入main主页
                    MainActivity1.setAction(context);
                } else {
                    //进入login页
                    activity_login.setAction(context);
                }

                finish();
            }
        }, 1500);
    }

    @Override
    public void OnClick(View v) {

    }

    @Override
    public void OnEorr() {

    }


    /**
     * 弹出隐私协议
     */
    private void showAgree() {
        AgreeDialog agreeDialog = new AgreeDialog(context);
        agreeDialog.show();
    }
}