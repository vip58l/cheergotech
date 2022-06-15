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
import com.cheergotech.ulist.Toasts;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 弹窗提示消息
 */
public class DialogMessage2 extends BaseDialog {
    @BindView(R.id.edit)
    EditText edit;
    @BindView(R.id.title)
    TextView title;

    public static void show(Context context, CallBcak callBcak) {
        DialogMessage2 dialogMessage = new DialogMessage2(context, callBcak);
        dialogMessage.show();
    }

    public DialogMessage2(@NonNull Context context, CallBcak callBcak) {
        super(context);
        this.callBcak = callBcak;

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
                String trim = edit.getText().toString().trim();
                if (TextUtils.isEmpty(trim)) {
                    Toasts.toastMessage(context.getString(R.string.mes1));
                    return;
                }

                if (callBcak != null) {
                    callBcak.setOnClickListener(edit.getText().toString());
                }
                dismiss();
                break;
            case R.id.send2:
                if (callBcak != null) {
                    callBcak.setOnClickListener(2);
                }
                dismiss();
                break;


        }

    }

}
