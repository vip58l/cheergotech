package com.cheergotech.UI.dialog;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.cheergotech.Base.BaseDialog;
import com.cheergotech.R;
import com.cheergotech.listen.CallBcak;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 弹窗提示消息
 */
public class DialogMessage4 extends BaseDialog {
    @BindView(R.id.eorr)
    ImageView eorr;
    @BindView(R.id.msg)
    TextView msg;

    public static void show(Context context, CallBcak callBcak) {
        DialogMessage4 dialogMessage = new DialogMessage4(context, callBcak);
        dialogMessage.show();
    }

    public DialogMessage4(@NonNull Context context, CallBcak callBcak) {
        super(context);
        this.callBcak = callBcak;
    }

    @Override
    public int getview() {
        return R.layout.dialog_mesg;
    }

    @Override
    @OnClick({R.id.send1, R.id.send2})
    public void OnClick(View v) {
//        switch (v.getId()) {
//            case R.id.send1:
//                if (callBcak != null) {
//                    callBcak.setOnClickListener(1);
//                }
//                break;
//            case R.id.send2:
//                if (callBcak != null) {
//                    callBcak.setOnClickListener(2);
//                }
//                break;
//
//
//        }
//        dismiss();
    }

}
