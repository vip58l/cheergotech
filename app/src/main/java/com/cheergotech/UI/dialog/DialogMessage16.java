package com.cheergotech.UI.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.cheergotech.Base.BaseDialogShow;
import com.cheergotech.R;
import com.cheergotech.listen.CallBcak;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 选择上传图片
 */
public class DialogMessage16 extends BaseDialogShow {
    @BindView(R.id.send1)
    TextView send1;
    @BindView(R.id.send2)
    TextView send2;
    @BindView(R.id.send3)
    TextView send3;

    public static void show(Context context, CallBcak callBcak) {
        DialogMessage16 dialogMessage = new DialogMessage16(context, callBcak);
        dialogMessage.show();
    }

    public DialogMessage16(@NonNull Context context, CallBcak callBcak) {
        super(context);
        this.callBcak = callBcak;
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        getWindow().setGravity(Gravity.BOTTOM);
        getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
    }

    @Override
    public int getview() {
        return R.layout.dialoig_item16;
    }

    @Override
    @OnClick({R.id.send1, R.id.send2, R.id.send3})
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
            case R.id.send3:
                if (callBcak != null) {
                    callBcak.setOnClickListener(3);
                    break;
                }

        }
        dismiss();
    }

}
