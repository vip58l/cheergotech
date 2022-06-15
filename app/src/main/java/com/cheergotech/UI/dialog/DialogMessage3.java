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
public class DialogMessage3 extends BaseDialog {
    @BindView(R.id.title)
    TextView title;

    public static void show(Context context, String name, CallBcak callBcak) {
        DialogMessage3 dialogMessage = new DialogMessage3(context, name, callBcak);
        dialogMessage.show();
    }

    public DialogMessage3(@NonNull Context context, String name, CallBcak callBcak) {
        super(context);
        this.callBcak = callBcak;
        title.setText(String.format(context.getString(R.string.titmsg) + "", name));
    }

    @Override
    public int getview() {
        return R.layout.dialog_imessgag;
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
