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
 * 选择上传图片
 */
public class DialogMessage17 extends BaseDialog {

    @BindView(R.id.s1)
    TextView s1;
    @BindView(R.id.s2)
    TextView s2;
    @BindView(R.id.s3)
    TextView s3;

    public static void show(Context context, CallBcak callBcak) {
        DialogMessage17 dialogMessage = new DialogMessage17(context, callBcak);
        dialogMessage.show();
    }

    public static void show(Context context, String title, String conter, CallBcak callBcak) {
        DialogMessage17 dialogMessage = new DialogMessage17(context, callBcak);
        dialogMessage.s1.setText(title);
        dialogMessage.s2.setText(conter);
        dialogMessage.show();
    }

    public DialogMessage17(@NonNull Context context, CallBcak callBcak) {
        super(context);
        this.callBcak = callBcak;
        WindowManagerCENTER();
    }

    @Override
    public int getview() {
        return R.layout.dialoig_item17;
    }

    @Override
    @OnClick({R.id.send1})
    public void OnClick(View v) {
        dismiss();
    }

}
