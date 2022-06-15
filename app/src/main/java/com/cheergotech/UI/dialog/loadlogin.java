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

/**
 * 弹窗提示消息
 */
public class loadlogin extends BaseDialog {
    @BindView(R.id.title)
    TextView title;

    public static loadlogin show(Context context, String msg, CallBcak callBcak) {
        loadlogin loadlogin = new loadlogin(context, msg, callBcak);
        loadlogin.show();
        return loadlogin;
    }

    public loadlogin(@NonNull Context context, String msg, CallBcak callBcak) {
        super(context);
        this.callBcak = callBcak;
        if (!TextUtils.isEmpty(msg)){
            this.title.setText(msg);
        }
    }

    @Override
    public int getview() {
        return R.layout.dialoig_load;
    }

    @Override
    public void OnClick(View v) {

    }


}
