package com.cheergotech.UI.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.cheergotech.Base.BaseDialogShow;
import com.cheergotech.R;
import com.cheergotech.listen.CallBcak;

/**
 * 发起审批-租借-选择品类
 */
public class DialogMessage21 extends BaseDialogShow {

    public static void show(Context context, CallBcak callBcak) {
        DialogMessage21 dialogMessage = new DialogMessage21(context, callBcak);
        dialogMessage.show();
    }

    public DialogMessage21(@NonNull Context context, CallBcak callBcak) {
        super(context);
        this.callBcak = callBcak;
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        getWindow().setGravity(Gravity.BOTTOM);
        getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
    }

    @Override
    public int getview() {
        return R.layout.dialoig_item21;
    }

    @Override
    public void OnClick(View v) {

    }


}
