package com.cheergotech.UI.dialog;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cheergotech.Base.BaseDialogShow;
import com.cheergotech.R;
import com.cheergotech.UI.activity.adapter.Adapter;
import com.cheergotech.listen.CallBcak;

import butterknife.BindView;

/**
 * 发起审批-租借-选择品类
 */
public class DialogMessage20 extends BaseDialogShow {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    public static void show(Context context, CallBcak callBcak) {
        DialogMessage20 dialogMessage = new DialogMessage20(context, callBcak);
        dialogMessage.show();
    }

    public DialogMessage20(@NonNull Context context, CallBcak callBcak) {
        super(context);
        this.callBcak = callBcak;
        WindowManager();
        for (int i = 0; i < 20; i++) {
            list.add(String.valueOf(i));
        }
        recyclerview.setLayoutManager(new LinearLayoutManager(context));
        recyclerview.setAdapter(adapter = new Adapter(context, list, Adapter.type129, new CallBcak() {
            @Override
            public void setOnClickListener(int position) {
                list.get(position);

            }

            @Override
            public void Onfall() {

            }
        }));
    }

    @Override
    public int getview() {
        return R.layout.dialoig_item20;
    }

    @Override
    public void OnClick(View v) {

    }


}
