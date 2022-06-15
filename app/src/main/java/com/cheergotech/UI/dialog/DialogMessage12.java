package com.cheergotech.UI.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.cheergotech.Base.BaseDialog;
import com.cheergotech.R;
import com.cheergotech.listen.CallBcak;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 课程信息-分享并保存
 */
public class DialogMessage12 extends BaseDialog {
    @BindView(R.id.message_conter)
    TextView message_conter;
    @BindView(R.id.send1)
    TextView send1;
    @BindView(R.id.send2)
    TextView send2;

    public static void show(Context context, CallBcak callBcak) {
        DialogMessage12 dialogMessage = new DialogMessage12(context, callBcak);
        dialogMessage.show();
    }

    public static void show(Context context, String s1, String s2, String s3, String s4, CallBcak callBcak) {
        DialogMessage12 dialogMessage = new DialogMessage12(context, s1, s2, s3, s4, callBcak);
        dialogMessage.show();

    }

    public static DialogMessage12 showTo(Context context, String s1, String s2, String s3, String s4, CallBcak callBcak) {
        DialogMessage12 dialogMessage = new DialogMessage12(context, s1, s2, s3, s4, callBcak);
        dialogMessage.show();
        return dialogMessage;
    }

    public DialogMessage12(@NonNull Context context, CallBcak callBcak) {
        super(context);
        this.callBcak = callBcak;
    }

    public DialogMessage12(@NonNull Context context, String s1, String s2, String s3, String s4, CallBcak callBcak) {
        super(context);
        this.callBcak = callBcak;

        if (!TextUtils.isEmpty(s2)) {
            this.message_conter.setText(s2);
        }

    }

    @Override
    public int getview() {
        return R.layout.dialoig_item12;
    }

    @Override
    @OnClick({R.id.send1, R.id.send2})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.send1:
                if (callBcak != null) {
                    callBcak.setOnClickListener(1);
                    break;
                }
            case R.id.send2:
                if (callBcak != null) {
                    callBcak.setOnClickListener(2);
                    break;
                }


        }
        dismiss();
    }

}
