package com.cheergotech.UI.dialog;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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
public class DialogMessage18 extends BaseDialog {

    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.lypostsend1)
    EditText lypostsend1;

    public static void show(Context context, CallBcak callBcak) {
        DialogMessage18 dialogMessage = new DialogMessage18(context, callBcak);
        dialogMessage.show();
    }

    public DialogMessage18(@NonNull Context context, CallBcak callBcak) {
        super(context);
        this.callBcak = callBcak;
        WindowManagerCENTER();
    }

    @Override
    public int getview() {
        return R.layout.dialoig_item18;
    }

    @Override
    @OnClick({R.id.postsend1, R.id.postsend2})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.postsend1:
                if (callBcak != null) {
                    callBcak.setOnClickListener(1);
                }
                break;
            case R.id.postsend2:
                if (callBcak != null) {
                    callBcak.setOnClickListener(2);
                }
                break;
        }
        dismiss();
    }

}
