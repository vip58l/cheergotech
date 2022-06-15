package com.cheergotech.UI.activity.joblist;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.ulist.ActivityLocation;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 录音界面相关
 */
public class Record2Activity extends BaseActivity {

    // 录音界面相关
    @BindView(R.id.btn_start)
    Button btnStart;
    @BindView(R.id.btn_stop)
    Button btnStop;
    @BindView(R.id.text_time)
    TextView textTime;
    @BindView(R.id.lin1)
    LinearLayout lin1;
    @BindView(R.id.play)
    ImageView play;

    //录音功能相关
    MediaRecorder mMediaRecorder;
    boolean miscplay;     //播放音频
    boolean isRecording; // 录音状态
    String fileName;     // 录音文件的名称
    String filePath;     // 录音文件存储路径
    Thread timeThread;   // 记录录音时长的线程
    int timeCount;       // 录音时长 计数
    final int TIME_COUNT = 0x101;
    myHandler myHandler = new myHandler();
    // 录音文件存放目录
    String audioSaveDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/audiodemo/";

    public static void setAction(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, Record2Activity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getview() {
        return R.layout.activity_record2;
    }

    @Override
    public void iniview() {
        boolean b = ActivityLocation.camera_audio_checkpermissions(activity);
        if (b) {
            File file = new File(audioSaveDir);
            if (!file.exists()) {
                file.mkdir();
            }
        }
    }

    @Override
    public void initData() {

    }

    @Override
    @OnClick({R.id.btn_start, R.id.btn_stop, R.id.play})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.play:
                miscplay = miscplay ? false : true;
                play.setImageDrawable(getDrawable(miscplay ? R.mipmap.mplay : R.mipmap.play1));
                if (miscplay) {
                    MediaPlayHelper.playHelper().startplay(filePath);
                } else {
                    MediaPlayHelper.playHelper().stop();
                }


                break;

            case R.id.btn_start: {
                // 开始录音
                btnStart.setEnabled(false);
                btnStop.setEnabled(true);
                isRecording = true;

                startRecord();//开始录音 使用amr格式

                // 初始化录音时长记录
                timeThread = new timeThread();
                timeThread.start();
                break;
            }

            case R.id.btn_stop: {
                // 停止录音
                btnStart.setEnabled(true);
                btnStop.setEnabled(false);
                isRecording = false;
                stopRecord();
                break;
            }

        }
    }

    @Override
    public void OnEorr() {

    }


    /**
     * 记录录音时长
     */
    private void countTime() {
        while (isRecording) {
            Log.d("mediaRe", "正在录音");
            timeCount++;
            Message msg = Message.obtain();
            msg.what = TIME_COUNT;
            msg.obj = timeCount;
            myHandler.sendMessage(msg);
            try {
                timeThread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Log.d("mediaRec", "结束录音");
        timeCount = 0;
        Message msg = Message.obtain();
        msg.what = TIME_COUNT;
        msg.obj = timeCount;
        myHandler.sendMessage(msg);
    }

    /**
     * 开始录音 使用amr格式
     * 录音文件
     *
     * @return
     */
    public void startRecord() {
        // 开始录音
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
            File file = new File(filePath);

            if (file.length() > 0) {
                lin1.setVisibility(View.VISIBLE);
            }

        } catch (RuntimeException e) {
            mMediaRecorder.reset();
            mMediaRecorder.release();
            mMediaRecorder = null;
            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
            }
            filePath = "";
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myHandler.removeCallbacksAndMessages(null);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (String permission : permissions) {
            int star = ContextCompat.checkSelfPermission(activity, permission);
            if (star != PackageManager.PERMISSION_GRANTED) {
                Log.d("TAG", "onRequestPermissionsResult: 2");
            } else {
                Log.d("TAG", "onRequestPermissionsResult: 1");
            }
        }
    }


    /**
     * 刷新UI线程
     */
    class myHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case TIME_COUNT:
                    int count = (int) msg.obj;
                    if (count <= 0) {
                        textTime.setText("停止录音");
                    } else {
                        textTime.setText("正在录音" + count);
                    }
                    break;
            }
        }
    }


    /**
     * 线程启动
     */
    class timeThread extends Thread {
        @Override
        public void run() {
            super.run();
            countTime();
        }
    }
}