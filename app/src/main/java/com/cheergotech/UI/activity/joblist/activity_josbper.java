package com.cheergotech.UI.activity.joblist;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
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
import com.cheergotech.UI.model.Datamodule;
import com.cheergotech.UI.model.imglist;
import com.cheergotech.UI.model.taskinfo;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.Config;
import com.cheergotech.ulist.Constants;
import com.cheergotech.ulist.Logi;
import com.cheergotech.widget.Backtitle;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作业详情
 */
public class activity_josbper extends BaseActivity {
    private static final String TAG = activity_josbper.class.getName();
    @BindView(R.id.backtitle)
    Backtitle backtitle;
    @BindView(R.id.zhuoyie)
    TextView zhuoyie;
    @BindView(R.id.titles)
    TextView titles;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.play1)
    ImageView play1;
    @BindView(R.id.show_play)
    LinearLayout show_play;
    @BindView(R.id.time)
    TextView time;
    boolean automatic;

    taskinfo taskinfo;
    com.cheergotech.UI.model.classtask classtask;
    MediaPlayer mediaPlayer;
    MediaPlayHelper mediaPlayHelper;

    boolean timout;
    Thread thread;
    myHandler myHandler = new myHandler();

    public static void setAction(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_josbper.class);
        context.startActivity(intent);
    }

    public static void setAction(Context context, String json) {
        Intent intent = new Intent();
        intent.setClass(context, activity_josbper.class);
        intent.putExtra(Constants.JSON, json);
        context.startActivity(intent);
    }

    @Override
    protected int getview() {
        return R.layout.activity_josbper;
    }

    @Override
    public void iniview() {
        String json = getIntent().getStringExtra(Constants.JSON);
        classtask = gson.fromJson(json, com.cheergotech.UI.model.classtask.class);
        recyclerview.setLayoutManager(new GridLayoutManager(context, 3));
        recyclerview.setAdapter(adapter = new Adapter(context, list, Adapter.bus_Img, new CallBcak() {

            @Override
            public void delete(int position) {

            }

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

                //进入图片浏览
                activity_picbage.setAction(context, mSelectedImages, position);
            }
        }));
        initData();

    }

    @Override
    public void initData() {
        Datamodule.getInstance().classtaskinfo(classtask.getId(), new CallBcak() {
            @Override
            public void OnSuccess(Object object) {
                taskinfo = (com.cheergotech.UI.model.taskinfo) object;
                zhuoyie.setText(Html.fromHtml(String.format(getString(R.string.toast12) + "", taskinfo.getNumbers(), taskinfo.getToDoNumbers())));
                titles.setText(taskinfo.getTitles());
                content.setText(taskinfo.getDescribes());
                String autos = taskinfo.getAutos();
                Logi.d("TAG", "OnSuccess: " + taskinfo);

                //是否存在音频文件
                if (!TextUtils.isEmpty(autos) && (autos.toLowerCase().startsWith("http://") || autos.toLowerCase().startsWith("https://"))) {
                    show_play.setVisibility(View.VISIBLE);
                    mediaPlayHelper = MediaPlayHelper.playHelper();
                    mediaPlayHelper.myplay(autos.trim());
                    mediaPlayer = mediaPlayHelper.getmMediaPlayer();
                    mediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                        @Override
                        public boolean onInfo(MediaPlayer mediaPlayer, int i, int i1) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    time.setText(String.format("0:00/%ss", generateTime(mediaPlayer.getDuration())));
                                }
                            });
                            return false;
                        }
                    });
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mediaPlayer) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    time.setText(String.format("0:00/%ss", generateTime(mediaPlayer.getDuration())));
                                }
                            });
                        }
                    });

                }
                if (!TextUtils.isEmpty(taskinfo.getImages())) {
                    List<String> getkcirclelist = holder4.getkcirclelist(taskinfo.getImages());
                    list.addAll(getkcirclelist);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void Onfall() {

            }
        });
    }

    @Override
    @OnClick({R.id.play1, R.id.template})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.play1: {
                automatic = automatic ? false : true;
                String autos = taskinfo.getAutos();
                if (automatic && !TextUtils.isEmpty(autos) && (autos.toLowerCase().startsWith("http://") || autos.toLowerCase().startsWith("https://"))) {
                    //开始播放
                    timout = true;
                    thread = new myThread();
                    thread.start();
                    play1.setImageDrawable(getDrawable(R.mipmap.mplay));
                    //监听播放完成了
                    MediaPlayHelper.playHelper().start();
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            Logi.d(TAG, "onCompletion: 播放完成");
                            timout = false;
                            automatic = false;
                            play1.setImageDrawable(getDrawable(R.mipmap.play1));
                            time.setText(String.format("0:00/%ss", generateTime(mediaPlayer.getDuration())));
                        }
                    });
                } else {
                    //停止播放
                    timout = false;
                    play1.setImageDrawable(getDrawable(R.mipmap.play1));
                    MediaPlayHelper.playHelper().pause();
                }

                break;
            }

            case R.id.template:
                //作业信息-已提交作业列表
                activity_alljob.setAction(context, classtask.getId());
                break;
        }
    }

    @Override
    public void OnEorr() {

    }


    /**
     * 播放视频时间处理
     */
    public static String generateTime(long position) {
        int totalSeconds = (int) (position / 1000); //除1000
        int seconds = totalSeconds % 60; //时
        int minutes = (totalSeconds / 60) % 60; //分
        int hours = totalSeconds / 3600; //秒
        return hours > 0 ? String.format(Locale.US, "%02d:%02d:%02d", hours, minutes, seconds) : String.format(Locale.US, "%02d:%02d", minutes, seconds);
    }

    class myThread extends Thread {
        @Override
        public void run() {
            while (timout) {
                int currentPosition = MediaPlayHelper.playHelper().getmMediaPlayer().getCurrentPosition();
                int duration = MediaPlayHelper.playHelper().getmMediaPlayer().getDuration();
                Message obtain = Message.obtain();
                obtain.obj = generateTime(currentPosition);
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
                    time.setText(String.format("%s/%ss", msg.obj.toString(), generateTime(mediaPlayer.getDuration())));
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
        if (mediaPlayer != null) {
            mediaPlayer = null;
        }
        if (mediaPlayHelper != null) {
            mediaPlayHelper = null;
        }
    }
}