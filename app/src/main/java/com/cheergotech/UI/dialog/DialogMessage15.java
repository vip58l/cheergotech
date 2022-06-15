package com.cheergotech.UI.dialog;

import android.content.Context;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.cheergotech.Base.BaseDialogShow;
import com.cheergotech.R;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.Config;
import com.cheergotech.ulist.Logi;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 弹窗提示消息
 */
public class DialogMessage15 extends BaseDialogShow {
    private static final String TAG = DialogMessage15.class.getName();
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.msg)
    TextView msg;
    @BindView(R.id.playicon)
    ImageView playicon;
    @BindView(R.id.voicemsg)
    LinearLayout voicemsg;
    @BindView(R.id.autostar)
    LinearLayout autostar;
    @BindView(R.id.autosound)
    LinearLayout autosound;
    boolean playrun;
    Thread threadtimout;
    myHandler myHandler = new myHandler();
    int Maxtime = 60 * 5;
    int timecount;

    //录音功能相关
    MediaRecorder mMediaRecorder;
    String fileName;     // 录音文件的名称
    String filePath;     // 录音文件存储路径
    // 录音文件存放目录
    String audioSaveDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/audiodemo/";

    public static void show(Context context, CallBcak callBcak) {
        DialogMessage15 dialogMessage = new DialogMessage15(context, callBcak);
        dialogMessage.setCanceledOnTouchOutside(false);
        dialogMessage.show();
    }

    public DialogMessage15(@NonNull Context context, CallBcak callBcak) {
        super(context);
        this.callBcak = callBcak;
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        getWindow().setGravity(Gravity.BOTTOM);
        getWindow().setWindowAnimations(R.style.BottomDialog_Animation);

    }


    /**
     * 开始录音 使用amr格式
     * 录音文件
     *
     * @return
     */
    public void startRecord() {
        if (mMediaRecorder == null) {
            mMediaRecorder = new MediaRecorder();
        }
        try {
            /* ②setAudioSource/setVedioSource */
            mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);// 设置麦克风
            /*
             * ②设置输出文件的格式：THREE_GPP/MPEG-4/RAW_AMR/Default THREE_GPP(3gp格式
             * ，H263视频/ARM音频编码)、MPEG-4、RAW_AMR(只支持音频且音频编码要求为AMR_NB)
             */
            mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            /* ②设置音频文件的编码：AAC/AMR_NB/AMR_MB/Default 声音的（波形）的采样 */
            mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);

            //fileName = DateFormat.format("yyyyMMdd_HHmmss", Calendar.getInstance(Locale.CHINA)) + ".m4a";
            fileName = DateFormat.format("yyyyMMdd_HHmmss", Calendar.getInstance(Locale.CHINA)) + ".mp3";
            //注意文件夹要创建之后才能使用
            filePath = audioSaveDir + fileName;

            /* ③准备 */
            mMediaRecorder.setOutputFile(filePath);
            mMediaRecorder.prepare();

            /* ④开始 */
            mMediaRecorder.start();

            Log.d("TAG", "startRecord: " + filePath);

        } catch (IllegalStateException e) {
            Log.i("mediaEr", "call startAmr(File mRecAudioFile) failed!" + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("mediaEr", "call startAmr(File mRecAudioFile) failed!" + e.getMessage());
        }
    }

    /**
     * 停止录音
     * //有一些网友反应在5.0以上在调用stop的时候会报错，翻阅了一下谷歌文档发现上面确实写的有可能会报错的情况，捕获异常清理一下就行了，感谢大家反馈！
     */
    public void stopRecord() {
        try {
            mMediaRecorder.stop();
            mMediaRecorder.release();
            mMediaRecorder = null;
            Log.d(TAG, "stopRecord: 111");
        } catch (RuntimeException e) {
            mMediaRecorder.reset();
            mMediaRecorder.release();
            mMediaRecorder = null;
            Log.d(TAG, "stopRecord: 222");
        }
    }


    @Override
    public int getview() {
        return R.layout.dialog_message15;
    }

    @Override
    @OnClick({R.id.autosound})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.autosound:
                if (playrun) {
                    //停止录音
                    msg.setText(R.string.auto1);
                    playrun = false;
                    playicon.setImageDrawable(context.getResources().getDrawable(R.mipmap.sound1));
                    stopRecord();
                    dismiss();
                } else {
                    //开始录音
                    timecount = 0;
                    playrun = true;
                    msg.setText(R.string.auto2);
                    voicemsg.setVisibility(View.GONE);
                    autostar.setVisibility(View.VISIBLE);
                    playicon.setImageDrawable(context.getResources().getDrawable(R.mipmap.sound2));
                    startRecord();// 开始录音
                    threadtimout = new myThread();
                    threadtimout.start();
                }


                break;
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
        playrun = false;
        if (myHandler != null) {
            myHandler.removeMessages(Config.sussess);
        }

        //音频文件回调
        if (filePath != null) {
            File file = new File(filePath);
            if (callBcak != null && file.length() > 0) {
                callBcak.OnSuccess(file);
                Logi.d(TAG, "dismiss:音频文件回调成功 ");
            } else {
                Logi.d(TAG, "dismiss: 音频文件回调失败");
            }
        }


    }

    class myThread extends Thread {
        @Override
        public void run() {
            super.run();
            while (playrun) {
                timecount++;
                if (timecount >= Maxtime) {
                    playrun = false;
                    Message obtain = Message.obtain();
                    obtain.obj = timecount;
                    obtain.what = Config.fail;
                    myHandler.sendMessage(obtain);
                    break;
                }
                Message obtain = Message.obtain();
                obtain.obj = timecount;
                obtain.what = Config.sussess;
                myHandler.sendMessage(obtain);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }



        }
    }

    class myHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Config.sussess:
                    time.setText(String.valueOf(msg.obj));
                    break;
                case Config.fail:
                    dismiss();
                    break;
            }
        }
    }

}
