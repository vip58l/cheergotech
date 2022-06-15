package com.cheergotech.UI.dialog;

import android.content.Context;
import android.view.View;
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
public class DialogMessage1 extends BaseDialog {
    @BindView(R.id.title)
    TextView title;

    public static void show(Context context, CallBcak callBcak) {
        DialogMessage1 dialogMessage = new DialogMessage1(context, callBcak);
        dialogMessage.show();
    }

    public static void show(Context context, String s1, String s2, String s3, CallBcak callBcak) {
        DialogMessage1 dialogMessage = new DialogMessage1(context, s1, s2, s3, callBcak);
        dialogMessage.show();
    }

    public DialogMessage1(@NonNull Context context, String s1, String s2, String s3, CallBcak callBcak) {
        super(context);
        this.callBcak = callBcak;

    }

    public DialogMessage1(@NonNull Context context, CallBcak callBcak) {
        super(context);
        this.callBcak = callBcak;
        title.setText(String.format("是否拨打 %s 家长的电话？", "赵欢"));
    }

    @Override
    public int getview() {
        return R.layout.dialog_imessgag1;
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
