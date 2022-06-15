package com.cheergotech.UI.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
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
public class DialogMessage7 extends BaseDialog {
    @BindView(R.id.title)
    TextView title;

    public static void show(Context context, Object obj, CallBcak callBcak) {
        DialogMessage7 dialogMessage = new DialogMessage7(context, obj, callBcak);
        dialogMessage.show();
    }

    public DialogMessage7(@NonNull Context context, Object obj, CallBcak callBcak) {
        super(context);
        this.callBcak = callBcak;
        this.object = obj;
        String msg = obj.toString();
        if (!TextUtils.isEmpty(msg)) {
            title.setText(msg);
        }
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        getWindow().setGravity(Gravity.CENTER);
    }

    @Override
    public int getview() {
        return R.layout.dialiog_t1;
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
