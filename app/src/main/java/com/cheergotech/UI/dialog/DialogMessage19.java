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
 * 选择上传图片
 */
public class DialogMessage19 extends BaseDialogShow {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;


    public static void show(Context context, CallBcak callBcak) {
        DialogMessage19 dialogMessage = new DialogMessage19(context, callBcak);
        dialogMessage.show();
    }

    public DialogMessage19(@NonNull Context context, CallBcak callBcak) {
        super(context);
        this.callBcak = callBcak;
        WindowManager();
        for (int i = 0; i < 20; i++) {
            list.add(String.valueOf(i));
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter = new Adapter(context, list, Adapter.workbench_folder, new CallBcak() {
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
        return R.layout.dialoig_item19;
    }

    @Override
    public void OnClick(View v) {

    }


}
