package com.cheergotech.UI.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
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
public class DialogMessage6 extends BaseDialog {
    @BindView(R.id.edit)
    EditText edit;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.send1)
    TextView send1;
    @BindView(R.id.send2)
    TextView send2;

    public static void show(Context context, CallBcak callBcak) {
        DialogMessage6 dialogMessage = new DialogMessage6(context, callBcak);
        dialogMessage.show();
    }

    public DialogMessage6(@NonNull Context context, CallBcak callBcak) {
        super(context);
        this.callBcak = callBcak;

    }

    public static void show(Context context, String s1, String s2, String s3, CallBcak callBcak) {
        DialogMessage6 dialogMessage = new DialogMessage6(context, s1, s2, s3, callBcak);
        dialogMessage.show();
    }

    public static void show(Context context, String s1, String s2, String s3, String s4, CallBcak callBcak) {
        DialogMessage6 dialogMessage = new DialogMessage6(context, s1, s2, s3,s4, callBcak);
        dialogMessage.show();
    }

    public DialogMessage6(@NonNull Context context, String s1, String s2, String s3, CallBcak callBcak) {
        super(context);
        this.callBcak = callBcak;
        if (!TextUtils.isEmpty(s1)) {
            title.setText(s1);
        }
        if (!TextUtils.isEmpty(s2)) {
            send1.setText(s2);
        }
        if (!TextUtils.isEmpty(s3)) {
            send2.setText(s3);
        }
    }

    public DialogMessage6(@NonNull Context context, String s1, String s2, String s3, String s4, CallBcak callBcak) {
        super(context);
        this.callBcak = callBcak;
        if (!TextUtils.isEmpty(s1)) {
            title.setText(s1);
        }
        if (!TextUtils.isEmpty(s2)) {
            send1.setText(s2);
        }
        if (!TextUtils.isEmpty(s3)) {
            send2.setText(s3);
        }
        if (!TextUtils.isEmpty(s4)) {
            edit.setText(s4);
        }

    }

    @Override
    public int getview() {
        return R.layout.dialog_message;
    }

    @Override
    @OnClick({R.id.send1, R.id.send2})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.send1:
                String msg = edit.getText().toString();
                if (callBcak != null) {
                    callBcak.setMtext(msg);
                }
                break;
            case R.id.send2:
                break;



        }
        dismiss();
    }

}
