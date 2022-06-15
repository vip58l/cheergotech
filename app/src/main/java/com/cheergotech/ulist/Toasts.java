package com.cheergotech.ulist;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cheergotech.Base.DemoApplication;
import com.cheergotech.R;

public class Toasts {

    public static android.widget.Toast mytoast, makeText;

    public static void show(String text) {
        if (mytoast != null) {
            mytoast.cancel();
            mytoast = null;
        }
        mytoast = android.widget.Toast.makeText(DemoApplication.instance(), text, android.widget.Toast.LENGTH_SHORT);
        mytoast.setGravity(Gravity.CENTER, 0, 0);
        mytoast.show();
    }

    public static void show(String t_title, String t_msg) {
        if (mytoast != null) {
            mytoast.cancel();
            mytoast = null;
        }
        View view = View.inflate(DemoApplication.instance(), R.layout.dialog_toast, null);
        TextView title = view.findViewById(R.id.title);
        TextView msg = view.findViewById(R.id.msg);
        title.setText(t_title);
        msg.setText(t_msg);


        mytoast = new android.widget.Toast(DemoApplication.instance());
        mytoast.setView(view);
        mytoast.setGravity(Gravity.CENTER, 0, 0);
        mytoast.show();
    }

    public static void show() {
        if (mytoast != null) {
            mytoast.cancel();
            mytoast = null;
        }
        View view = View.inflate(DemoApplication.instance(), R.layout.dialog_toast, null);
        TextView title = view.findViewById(R.id.title);
        TextView msg = view.findViewById(R.id.msg);


        mytoast = new android.widget.Toast(DemoApplication.instance());
        mytoast.setView(view);
        mytoast.setGravity(Gravity.CENTER, 0, 0);
        mytoast.show();
    }

    public static void success() {
        if (mytoast != null) {
            mytoast.cancel();
            mytoast = null;
        }
        View view = View.inflate(DemoApplication.instance(), R.layout.dialog_mesg, null);
        ImageView eorr = view.findViewById(R.id.eorr);
        TextView msg = view.findViewById(R.id.msg);
        mytoast = new android.widget.Toast(DemoApplication.instance());
        mytoast.setView(view);
        mytoast.setGravity(Gravity.CENTER, 0, 0);
        mytoast.show();
    }

    /**
     * 已发送成功
     *
     * @param type
     */
    public static void success(int type) {
        if (mytoast != null) {
            mytoast.cancel();
            mytoast = null;
        }
        View view = View.inflate(DemoApplication.instance(), R.layout.dialog_mesg, null);
        ImageView eorr = view.findViewById(R.id.eorr);
        TextView msg = view.findViewById(R.id.msg);
        if (type == 1) {
            eorr.setImageResource(R.mipmap.suessc);
        }


        mytoast = new android.widget.Toast(DemoApplication.instance());
        mytoast.setView(view);
        mytoast.setGravity(Gravity.CENTER, 0, 0);
        mytoast.show();
    }

    /**
     * 自定义设置成功
     *
     * @param content
     */
    public static void success(String content) {
        BackgroundTasks.getInstance().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mytoast != null) {
                    mytoast.cancel();
                    mytoast = null;
                }
                View view = View.inflate(DemoApplication.instance(), R.layout.dialog_mesg, null);
                ImageView eorr = view.findViewById(R.id.eorr);
                TextView msg = view.findViewById(R.id.msg);
                msg.setText(content);

                mytoast = new android.widget.Toast(DemoApplication.instance());
                mytoast.setView(view);
                mytoast.setGravity(Gravity.CENTER, 0, 0);
                mytoast.show();
            }
        });
    }

    public static void showeorr() {
        BackgroundTasks.getInstance().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mytoast != null) {
                    mytoast.cancel();
                    mytoast = null;
                }
                View view = View.inflate(DemoApplication.instance(), R.layout.dialiog_toast, null);
                ImageView eorr = view.findViewById(R.id.eorr);
                TextView msg = view.findViewById(R.id.msg);
                TextView msg2 = view.findViewById(R.id.msg2);

                mytoast = new android.widget.Toast(DemoApplication.instance());
                mytoast.setView(view);
                mytoast.setGravity(Gravity.CENTER, 0, 0);
                mytoast.show();
            }
        });
    }

    public static void showeorr(String mmsg1, String mmsg2) {
        BackgroundTasks.getInstance().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mytoast != null) {
                    mytoast.cancel();
                    mytoast = null;
                }
                View view = View.inflate(DemoApplication.instance(), R.layout.dialiog_toast, null);
                ImageView eorr = view.findViewById(R.id.eorr);
                TextView msg = view.findViewById(R.id.msg);
                TextView msg2 = view.findViewById(R.id.msg2);
                msg.setText(mmsg1);
                msg2.setText(mmsg2);
                mytoast = new android.widget.Toast(DemoApplication.instance());
                mytoast.setView(view);
                mytoast.setGravity(Gravity.CENTER, 0, 0);
                mytoast.show();
            }
        });
    }

    public static void showeorr(String mmsg1) {
        BackgroundTasks.getInstance().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mytoast != null) {
                    mytoast.cancel();
                    mytoast = null;
                }
                View view = View.inflate(DemoApplication.instance(), R.layout.dialiog_toast, null);
                ImageView eorr = view.findViewById(R.id.eorr);
                TextView msg = view.findViewById(R.id.msg);
                TextView msg2 = view.findViewById(R.id.msg2);
                msg.setText(mmsg1);


                mytoast = new android.widget.Toast(DemoApplication.instance());
                mytoast.setView(view);
                mytoast.setGravity(Gravity.CENTER, 0, 0);
                mytoast.show();
            }
        });
    }


    public static void show(int Smg) {
        if (makeText != null) {
            makeText.cancel();
            makeText = null;
        }
        View inflate = View.inflate(DemoApplication.instance(), R.layout.toastview, null);
        TextView tv1 = inflate.findViewById(R.id.tv1);
        tv1.setText(String.valueOf(Smg));
        makeText = android.widget.Toast.makeText(DemoApplication.instance(), String.valueOf(Smg), android.widget.Toast.LENGTH_SHORT);
        makeText.setView(inflate);
        makeText.setGravity(Gravity.CENTER, 0, 0);
        makeText.show();

    }

    public static void show(Context context, String Smg) {
        BackgroundTasks.getInstance().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (makeText != null) {
                    makeText.cancel();
                    makeText = null;
                }
                View inflate = View.inflate(DemoApplication.instance(), R.layout.toastview, null);
                TextView tv1 = inflate.findViewById(R.id.tv1);
                tv1.setText(String.valueOf(Smg));
                makeText = android.widget.Toast.makeText(DemoApplication.instance(), Smg, android.widget.Toast.LENGTH_SHORT);
                makeText.setView(inflate);
                makeText.setGravity(Gravity.CENTER, 0, 0);
                makeText.show();
            }

        });
    }

    public static void showShort(int Smg) {
        if (makeText != null) {
            makeText.cancel();
            makeText = null;
        }
        View inflate = View.inflate(DemoApplication.instance(), R.layout.toastview, null);
        TextView tv1 = inflate.findViewById(R.id.tv1);
        tv1.setText(String.valueOf(Smg));
        makeText = android.widget.Toast.makeText(DemoApplication.instance(), String.valueOf(Smg), android.widget.Toast.LENGTH_SHORT);
        makeText.setView(inflate);
        makeText.setGravity(Gravity.CENTER, 0, 0);
        makeText.show();

    }

    public static void showShort(String Smg) {
        BackgroundTasks.getInstance().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (makeText != null) {
                    makeText.cancel();
                    makeText = null;
                }
                View inflate = View.inflate(DemoApplication.instance(), R.layout.toastview, null);
                TextView tv1 = inflate.findViewById(R.id.tv1);
                tv1.setText(String.valueOf(Smg));
                makeText = android.widget.Toast.makeText(DemoApplication.instance(), String.valueOf(Smg), android.widget.Toast.LENGTH_SHORT);
                makeText.setView(inflate);
                makeText.setGravity(Gravity.CENTER, 0, 0);
                makeText.show();
            }
        });

    }

    public static void toastMessage(int Smg) {
        if (makeText != null) {
            makeText.cancel();
            makeText = null;
        }
        View inflate = View.inflate(DemoApplication.instance(), R.layout.toastview, null);
        TextView tv1 = inflate.findViewById(R.id.tv1);
        tv1.setText(String.valueOf(Smg));
        makeText = android.widget.Toast.makeText(DemoApplication.instance(), String.valueOf(Smg), android.widget.Toast.LENGTH_SHORT);
        makeText.setView(inflate);
        makeText.setGravity(Gravity.CENTER, 0, 0);
        makeText.show();
    }

    public static void toastMessage(String Smg) {
        if (makeText != null) {
            makeText.cancel();
            makeText = null;
        }
        View inflate = View.inflate(DemoApplication.instance(), R.layout.toastview, null);
        TextView tv1 = inflate.findViewById(R.id.tv1);
        tv1.setText(String.valueOf(Smg));
        makeText = android.widget.Toast.makeText(DemoApplication.instance(), String.valueOf(Smg), android.widget.Toast.LENGTH_SHORT);
        makeText.setView(inflate);
        makeText.setGravity(Gravity.CENTER, 0, 0);
        makeText.show();

    }


}
