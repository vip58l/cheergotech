package com.cheergotech.UI.dialog;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.cheergotech.Base.BaseDialog;
import com.cheergotech.R;
import com.cheergotech.listen.CallBcak;

import butterknife.OnClick;

/**
 * 课程信息-分享并保存
 */
public class DialogMessage9 extends BaseDialog {

    public static void show(Context context, CallBcak callBcak) {
        DialogMessage9 dialogMessage = new DialogMessage9(context, callBcak);
        dialogMessage.show();
    }

    public DialogMessage9(@NonNull Context context, CallBcak callBcak) {
        super(context);
        this.callBcak = callBcak;

    }

    @Override
    public int getview() {
        return R.layout.dialoig_item3;
    }

    @Override
    @OnClick({R.id.wx, R.id.qq, R.id.colos})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.wx:
                if (callBcak != null) {
                    callBcak.setOnClickListener(1);
                }
                break;
            case R.id.qq:
                if (callBcak != null) {
                    callBcak.setOnClickListener(2);
                }
            case R.id.colos:
                if (callBcak != null) {
                    callBcak.setOnClickListener(3);
                }
                break;
        }
        dismiss();
    }

}
