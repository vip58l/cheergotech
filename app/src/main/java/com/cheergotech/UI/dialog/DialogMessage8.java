package com.cheergotech.UI.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.cheergotech.Base.BaseDialog;
import com.cheergotech.R;
import com.cheergotech.listen.CallBcak;

import butterknife.OnClick;

/**
 * 班级圈-我的帖子-删除
 */
public class DialogMessage8 extends BaseDialog {

    public static void show(Context context, CallBcak callBcak) {
        DialogMessage8 dialogMessage = new DialogMessage8(context, callBcak);
        dialogMessage.show();
    }

    public DialogMessage8(@NonNull Context context, CallBcak callBcak) {
        super(context);
        this.callBcak = callBcak;
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        getWindow().setGravity(Gravity.BOTTOM);
        getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
    }

    @Override
    public int getview() {
        return R.layout.dialoig_item2;
    }

    @Override
    @OnClick({R.id.send1, R.id.send2})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.send1:
                if (callBcak != null) {
                    callBcak.setOnClickListener(1);
                }
                break;
            case R.id.send2:
                if (callBcak != null) {
                    callBcak.setOnClickListener(2);
                }
                break;
        }
        dismiss();
    }

}
