package com.cheergotech.UI.activity.joblist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.UI.activity.adapter.Adapter;
import com.cheergotech.UI.activity.adapter.holder.holder4;
import com.cheergotech.UI.activity.viecode.activity_picbage;
import com.cheergotech.UI.dialog.DialogMessage10;
import com.cheergotech.UI.model.Datamodule;
import com.cheergotech.UI.model.classTaskExaminesDTO;
import com.cheergotech.UI.model.classtaskinfo;
import com.cheergotech.UI.model.imglist;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.Config;
import com.cheergotech.ulist.Constants;
import com.cheergotech.ulist.Toasts;
import com.cheergotech.widget.Backtitle;
import com.cheergotech.widget.RatingStar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作业提交信息-详情
 */
public class activity_job_correct2 extends BaseActivity {
    @BindView(R.id.backtitle)
    Backtitle backtitle;
    @BindView(R.id.e1)
    EditText e1;
    @BindView(R.id.e2)
    EditText e2;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.ratingStar)
    RatingStar ratingStar;
    @BindView(R.id.sexmsg)
    TextView sexmsg;
    @BindView(R.id.play)
    LinearLayout play;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.play1)
    ImageView play1;
    int id;
    classtaskinfo classtaskinfo;
    String[] stringArray;

    MediaPlayer mediaPlayer;
    MediaPlayHelper mediaPlayHelper;
    String autos;
    boolean automatic;
    boolean timout;
    Thread thread;
    myHandler myHandler = new myHandler();

    public static void setAction(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_job_correct2.class);
        context.startActivity(intent);
    }

    public static void setAction(Context context, int id) {
        Intent intent = new Intent();
        intent.setClass(context, activity_job_correct2.class);
        intent.putExtra(Constants.id, id);
        context.startActivity(intent);
    }

    public static void setAction(Activity context, int id) {
        Intent intent = new Intent();
        intent.setClass(context, activity_job_correct2.class);
        intent.putExtra(Constants.id, id);
        context.startActivityForResult(intent, Config.sussess);
    }

    @Override
    protected int getview() {
        return R.layout.activity_job_correct2;
    }

    @Override
    public void iniview() {
        stringArray = getResources().getStringArray(R.array.table3);
        id = getIntent().getIntExtra(Constants.id, 0);
        ratingStar.setEnabled(false);
        recyclerview.setLayoutManager(new GridLayoutManager(context, 3));
        recyclerview.setAdapter(adapter = new Adapter(context, list, Adapter.bus_Img, new CallBcak() {
            @Override
            public void setOnClickListener(int position) {
                //图片预览
                List<imglist> mSelectedImages = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    imglist image = new imglist();
                    String path = (String) list.get(i);
                    image.setId(String.valueOf(i));
                    image.setBgpic(path);
                    image.setPic(path);
                    mSelectedImages.add(image);
                }
                activity_picbage.setAction(context, mSelectedImages, position);

            }

            @Override
            public void Onfall() {

            }
        }));
        initData();
    }

    @Override
    public void initData() {
        Datamodule.getInstance().tasksubmitinfo(id, new CallBcak() {
            @Override
            public void OnSuccess(Object object) {
                classtaskinfo = (com.cheergotech.UI.model.classtaskinfo) object;
                e1.setText(classtaskinfo.getDescribes());
                backtitle.setconter(classtaskinfo.getStudentName());
                ratingStar.setnumber(classtaskinfo.getCourseInfo());
                e2.setText(classtaskinfo.getRemarks());

                if (!TextUtils.isEmpty(classtaskinfo.getImages())) {
                    List<String> getkcirclelist = holder4.getkcirclelist(classtaskinfo.getImages());
                    list.addAll(getkcirclelist);
                    adapter.notifyDataSetChanged();
                }
                if (classtaskinfo.getCourseInfo() > 0) {
                    sexmsg.setText(stringArray[classtaskinfo.getCourseInfo() - 1]);
                }
                autos = classtaskinfo.getAutos();

                //测试音频
                //autos="https://img-qn.51miz.com/preview/sound/00/26/62/51miz-S266258-D3509F082.mp3";

                //是否存在音频文件
                if (!TextUtils.isEmpty(autos) && (autos.toLowerCase().startsWith("http://") || autos.toLowerCase().startsWith("https://"))) {
                    play.setVisibility(View.VISIBLE);
                    mediaPlayHelper = MediaPlayHelper.playHelper();
                    mediaPlayHelper.myplay(autos.trim());
                    mediaPlayer = mediaPlayHelper.getmMediaPlayer();
                    mediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                        @Override
                        public boolean onInfo(MediaPlayer mediaPlayer, int i, int i1) {
                            Log.d("TAG", "onInfo:==== " + i + "====" + i1);
                            return false;
                        }
                    });
                    mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                        @Override
                        public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                            Log.d("TAG", "onError: " + i + i1);
                            return false;
                        }
                    });
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mediaPlayer) {
                            if (time != null) {
                                time.setText(Config.generateTime(mediaPlayer.getDuration()));
                            }

                        }
                    });
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            try {
                                timout = false;
                                automatic = false;
                                if (play1 != null) {
                                    play1.setImageDrawable(getDrawable(automatic ? R.mipmap.mplay : R.mipmap.play1));
                                }
                                if (time != null) {
                                    time.setText(Config.generateTime(mediaPlayer.getDuration()));
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }

            @Override
            public void Onfall() {

            }
        });

    }

    @Override
    @OnClick({R.id.send_back, R.id.send_post, R.id.play1})
    public void OnClick(View v) {
        //退回重做 作业批改
        classTaskExaminesDTO classTaskExaminesDTO = new classTaskExaminesDTO();
        classTaskExaminesDTO.setIds(String.valueOf(classtaskinfo.getId()));
        classTaskExaminesDTO.setCourseInfo(ratingStar.getmGrade());
        classTaskExaminesDTO.setRemarks(e2.getText().toString());
        switch (v.getId()) {
            case R.id.send_back: {
                DialogMessage10 dialogMessage10 = DialogMessage10.showTo(context, "作业退回", "是否退回 " + classtaskinfo.getStudentName() + " 的作业？", "取消", "退回", new CallBcak() {
                    @Override
                    public void Onfall() {

                    }

                    @Override
                    public void setOnClickListener(int type) {
                        if (type == 2) {
                            Datamodule.getInstance().classtaskreject(gson.toJson(classTaskExaminesDTO), this);
                        }

                    }

                    @Override
                    public void OnSuccess() {
                        setResult(Config.setResult);
                        Toasts.success("作业退出成功");
                        finish();
                    }
                });
                dialogMessage10.contextColor();
                break;
            }
            case R.id.send_post: {
                Datamodule.getInstance().classtaskexamines(gson.toJson(classTaskExaminesDTO), new CallBcak() {
                    @Override
                    public void OnSuccess() {
                        setResult(Config.setResult);
                        Toasts.success("已批改");
                        finish();
                    }

                    @Override
                    public void Onfall() {

                    }
                });
                break;
            }
            case R.id.play1: {
                automatic = automatic ? false : true;
                play1.setImageDrawable(getDrawable(automatic ? R.mipmap.mplay : R.mipmap.play1));
                if (automatic && !TextUtils.isEmpty(autos)) {
                    MediaPlayHelper.playHelper().start();
                    //开始播放
                    timout = true;
                    thread = new myThread();
                    thread.start();

                } else {
                    //停止播放
                    timout = false;
                    MediaPlayHelper.playHelper().pause();
                }
                break;
            }

        }
    }

    @Override
    public void OnEorr() {

    }

    class myThread extends Thread {
        @Override
        public void run() {
            while (timout) {
                int currentPosition = MediaPlayHelper.playHelper().getmMediaPlayer().getCurrentPosition();
                int duration = MediaPlayHelper.playHelper().getmMediaPlayer().getDuration();
                Message obtain = Message.obtain();
                obtain.obj = activity_josbper.generateTime(currentPosition);
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
                    time.setText(msg.obj.toString());
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timout = false;
        MediaPlayHelper.playHelper().stop();
        if (myHandler != null) {
            myHandler.removeMessages(Config.sussess);
        }
    }
}