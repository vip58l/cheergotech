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
public class DialogMessage10 extends BaseDialog {
    @BindView(R.id.message_title)
    TextView message_title;
    @BindView(R.id.message_conter)
    TextView message_conter;
    @BindView(R.id.postsend1)
    TextView postsend1;
    @BindView(R.id.postsend2)
    TextView postsend2;

    public static void show(Context context, CallBcak callBcak) {
        DialogMessage10 dialogMessage = new DialogMessage10(context, callBcak);
        dialogMessage.show();
    }

    public static void show(Context context, String s1, String s2, String s3, String s4, CallBcak callBcak) {
        DialogMessage10 dialogMessage = new DialogMessage10(context, s1, s2, s3, s4, callBcak);
        dialogMessage.show();

    }

    public static DialogMessage10 showTo(Context context, String s1, String s2, String s3, String s4, CallBcak callBcak) {
        DialogMessage10 dialogMessage = new DialogMessage10(context, s1, s2, s3, s4, callBcak);
        dialogMessage.show();
        return dialogMessage;
    }

    public DialogMessage10(@NonNull Context context, CallBcak callBcak) {
        super(context);
        this.callBcak = callBcak;
    }

    public DialogMessage10(@NonNull Context context, String s1, String s2, String s3, String s4, CallBcak callBcak) {
        super(context);
        this.callBcak = callBcak;

        if (!TextUtils.isEmpty(s1)) {
            this.message_title.setText(s1);
        }

        if (!TextUtils.isEmpty(s2)) {
            this.message_conter.setText(s2);
        }

        if (!TextUtils.isEmpty(s3)) {
            this.postsend1.setText(s3);
        }

        if (!TextUtils.isEmpty(s4)) {
            this.postsend2.setText(s4);
        }

    }

    @Override
    public int getview() {
        return R.layout.dialoig_item10;
    }

    @Override
    @OnClick({R.id.postsend1, R.id.postsend2})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.postsend1:
                if (callBcak != null) {
                    callBcak.setOnClickListener(1);
                    break;
                }
            case R.id.postsend2:
                if (callBcak != null) {
                    callBcak.setOnClickListener(2);
                    break;
                }


        }
        dismiss();
    }

    public void contextColor(int id) {
        message_title.setTextColor(context.getResources().getColor(id));
    }

    public void contextColor() {
        message_title.setTextColor(context.getResources().getColor(R.color.wred));
    }

}
