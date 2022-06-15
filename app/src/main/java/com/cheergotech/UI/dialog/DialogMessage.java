package com.cheergotech.UI.dialog;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.cheergotech.Base.BaseDialogShow;
import com.cheergotech.R;
import com.cheergotech.listen.CallBcak;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 弹窗提示消息
 */
public class DialogMessage extends BaseDialogShow {
    @BindView(R.id.ss0)
    TextView textView;

    public static void show(Context context, String name, CallBcak callBcak) {
        DialogMessage dialogMessage = new DialogMessage(context, name, callBcak);
        dialogMessage.show();
    }

    public DialogMessage(@NonNull Context context, String name, CallBcak callBcak) {
        super(context);
        this.callBcak = callBcak;
        this.textView.setText(String.format(context.getString(R.string.toaa1) + "", name));
    }

    @Override
    public int getview() {
        return R.layout.dialoig_item;
    }

    @Override
    @OnClick({R.id.ss1, R.id.ss2, R.id.ss3, R.id.ss4})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.ss1:
                if (callBcak != null) {
                    callBcak.setOnClickListener(1);
                }
                break;
            case R.id.ss2:
                if (callBcak != null) {
                    callBcak.setOnClickListener(2);
                }
                break;
            case R.id.ss3:
                if (callBcak != null) {
                    callBcak.setOnClickListener(3);
                }
                break;
            case R.id.ss4:
                if (callBcak != null) {
                    callBcak.setOnClickListener(4);
                }
                break;

        }

        dismiss();
    }
}
