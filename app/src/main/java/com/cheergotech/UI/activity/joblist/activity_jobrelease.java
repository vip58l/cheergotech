package com.cheergotech.UI.activity.joblist;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.UI.dialog.DialogMessage10;
import com.cheergotech.UI.dialog.DialogMessage15;
import com.cheergotech.UI.model.Datamodule;
import com.cheergotech.UI.model.Msgconfig;
import com.cheergotech.UI.model.classTaskAddDTO;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.ActivityLocation;
import com.cheergotech.ulist.Config;
import com.cheergotech.ulist.JsonUitl;
import com.cheergotech.ulist.Toasts;
import com.cheergotech.widget.widgetimage14;
import com.steven.selectimage.model.Image;
import com.steven.selectimage.ui.PreviewImageActivity;
import com.steven.selectimage.ui.SelectImageActivity;
import com.steven.selectimage.ui.adapter.SelectedImageAdapter;
import com.steven.selectimage.utils.TDevice;
import com.steven.selectimage.widget.recyclerview.OnItemClicklistener2;
import com.steven.selectimage.widget.recyclerview.SpaceGridItemDecoration;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * 作业发布
 */
public class activity_jobrelease extends BaseActivity {
    private static final String TAG = activity_jobrelease.class.getName();
    @BindView(R.id.widgetimage7)
    com.cheergotech.widget.widgetimage7 widgetimage7;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.share)
    widgetimage14 share;
    @BindView(R.id.voice)
    LinearLayout voice;
    @BindView(R.id.imgage)
    ImageView imgage;
    @BindView(R.id.mtitle)
    TextView mtitle;
    @BindView(R.id.titles)
    EditText titles;
    @BindView(R.id.describes)
    EditText describes;
    @BindView(R.id.viecodelinyout)
    LinearLayout viecodelinyout;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.play)
    ImageView play;


    classTaskAddDTO classTaskAddDTO;

    private ArrayList<Image> mSelectImages = new ArrayList<>();
    private SelectedImageAdapter mAdapter;
    private final String[] PERMISSION = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    private Image image;
    private static final int PERMISSION_REQUEST_CODE = 0;
    private static final int SELECT_IMAGE_REQUEST = 0x0011;
    String mmtitle;
    String mdescribes;

    // 录音文件存放目录
    String audioSaveDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/audiodemo/";
    boolean miscplay;
    File videofile; //音频文件
    MediaPlayer mediaPlayer;
    myThread myThread;
    int timetout;//播放进度统计
    myHandler myHandler = new myHandler();

    /**
     * @param context
     */
    public static void setAction(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_jobrelease.class);
        context.startActivity(intent);
    }

    /**
     * @param context
     */
    public static void setAction(Activity context) {
        Intent intent = new Intent();
        intent.setClass(context, activity_jobrelease.class);
        context.startActivityForResult(intent, Config.sussess);
    }

    @Override
    protected int getview() {
        return R.layout.activity_jobrelease;
    }

    @Override
    public void iniview() {

        titles.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                widgetimage7.setMsg2(editable.length() + "/50");
                if (editable.length() > 30) {

                }

            }
        });
        //默认添加图片++
        image = new Image();
        image.setPath("");
        mSelectImages.add(image);

        widgetimage7.msg2.setVisibility(View.VISIBLE);
        recyclerview.setLayoutManager(new GridLayoutManager(context, 3));
        recyclerview.addItemDecoration(new SpaceGridItemDecoration((int) TDevice.dipToPx(getResources(), 1)));
        recyclerview.setAdapter(mAdapter = new SelectedImageAdapter(context, mSelectImages)); //适配器加入布局
        mItemTouchHelper.attachToRecyclerView(recyclerview);//用于移动吸附着item
        mAdapter.setOnItemClicklistener2(new OnItemClicklistener2() {
            @Override
            public void onAdd() {
                selectImage(); //监听图片添加事件
            }

            @Override
            public void onItemClick(int position) {
                //图片预览
                PreviewImageActivity.setAction(context, mSelectImages, position);
            }

            @Override
            public void delonItem(int postion) {
                mSelectImages.remove(postion);
                mAdapter.notifyDataSetChanged();
                //插入最后一个图片
                if (!isgetboolean()) {
                    mSelectImages.add(image);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });//图片添加或点击事件监听
    }

    @Override
    public void initData() {


    }

    @Override
    @OnClick({R.id.send_post, R.id.voice, R.id.play})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.send_post: {
                mmtitle = titles.getText().toString().trim();
                mdescribes = describes.getText().toString();
                if (TextUtils.isEmpty(mmtitle)) {
                    Toasts.toastMessage(getString(R.string.m1));
                    return;
                }
                if (TextUtils.isEmpty(mdescribes)) {
                    Toasts.toastMessage(getString(R.string.m2));
                    return;

                }
                DialogMessage10.show(context, getString(R.string.m3), getString(R.string.m4), getString(R.string.m5), getString(R.string.m6), new CallBcak() {
                    @Override
                    public void Onfall() {

                    }

                    @Override
                    public void setOnClickListener(int type) {

                        switch (type) {
                            case 1:
                                break;
                            case 2:
                                getImages();
                                break;
                        }


                    }

                });
                break;
            }
            case R.id.voice: {
                if (ActivityLocation.camera_audio_checkpermissions(activity)) {
                    File file = new File(audioSaveDir);
                    if (!file.exists()) {
                        file.mkdir();
                    }

                    //弹出录音提示框
                    DialogMessage15.show(context, new CallBcak() {
                        @Override
                        public void Onfall() {

                        }

                        @Override
                        public void OnSuccess(Object object) {
                            videofile = (File) object;
                            MediaPlayHelper mediaPlayHelper = MediaPlayHelper.playHelper();
                            mediaPlayHelper.myplay(videofile.getAbsolutePath());
                            mediaPlayer = mediaPlayHelper.getmMediaPlayer();
                            mediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                                @Override
                                public boolean onInfo(MediaPlayer mediaPlayer, int i, int i1) {
                                    miscplay = false;
                                    time.setText(String.format("0:00/%ss", Config.generateTime(mediaPlayer.getDuration())));
                                    play.setImageDrawable(getResources().getDrawable(R.mipmap.play1));
                                    return false;
                                }
                            });
                            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                @Override
                                public void onPrepared(MediaPlayer mediaPlayer) {
                                    miscplay = false;
                                    time.setText(String.format("0:00/%ss", Config.generateTime(mediaPlayer.getDuration())));
                                    play.setImageDrawable(getResources().getDrawable(R.mipmap.play1));

                                }
                            });
                        }
                    });

                }
                break;
            }
            case R.id.play: {
                if (videofile == null) {
                    Toasts.toastMessage(getString(R.string.toast17));
                    return;
                }
                if (mediaPlayer != null) {
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            miscplay = false;
                            time.setText(String.format("0:00/%ss", Config.generateTime(mediaPlayer.getDuration())));
                            play.setImageDrawable(getResources().getDrawable(R.mipmap.play1));
                        }
                    });
                }
                //开始播放
                if (!miscplay && videofile.length() > 0) {
                    miscplay = true;
                    play.setImageDrawable(getResources().getDrawable(R.mipmap.mplay));
                    MediaPlayHelper.playHelper().start();
                    myThread = new myThread();
                    myThread.start();

                } else {
                    //停止播放
                    miscplay = false;
                    play.setImageDrawable(getResources().getDrawable(R.mipmap.play1));
                    MediaPlayHelper.playHelper().pause();
                }
                break;
            }

        }
    }

    @Override
    public void OnEorr() {
    }

    /**
     * 我已确认发布作业
     */
    private void sendclassTask(String path) {
        //设置线程池 每次只允许运行10条线程 其他线程列队等待执行
        //ExecutorService executorService = Executors.newFixedThreadPool(1);
        //executorService.execute(new myThread(path, i));
        //执行线程池任务
        //executorService.shutdown();

        //音频文件已存在
        if (videofile != null) {
            //执行上传图片 1 图片 2 wave 3 mp4 4 docx
            Datamodule.getInstance().uploadImg(videofile, 2, new CallBcak() {
                @Override
                public void Onfall() {

                }

                @Override
                public void OnSuccess(Object obj) {
                    String Autos = (String) obj;
                    updatepaut(Autos, path);
                }
            });
        } else {
            //不带音频文件
            updatepaut("", path);
        }


    }

    private void updatepaut(String Autos, String path) {
        classTaskAddDTO = new classTaskAddDTO();
        classTaskAddDTO.setAutos(Autos); //音频
        classTaskAddDTO.setClassId(Msgconfig.getInstance().getClassId());//班级ID
        classTaskAddDTO.setDescribes(mdescribes);//作业描述
        classTaskAddDTO.setFiles("");  //文档
        classTaskAddDTO.setImages(path); //图片
        classTaskAddDTO.setRemarks("");//备注
        classTaskAddDTO.setTitles(mmtitle); //作业标题
        classTaskAddDTO.setVideos(""); //短视频
        Datamodule.getInstance().classtasksend(classTaskAddDTO, new CallBcak() {
            @Override
            public void Onfall() {
                Toasts.showeorr();

            }

            @Override
            public void OnSuccess() {
                dismissDialog();
                Toasts.success(getString(R.string.t8));
                setResult(Config.setResult);
                finish();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            //录音申请回调
            case ActivityLocation.OPEN_SET_REQUEST_CODE: {
                for (String permission : permissions) {
                    int star = ContextCompat.checkSelfPermission(activity, permission);
                    if (star != PackageManager.PERMISSION_GRANTED) {
                        Log.d(TAG, "申请失败: ");
                    } else {
                        Log.d(TAG, "申请成功: ");

                    }
                }
                break;
            }

            //相册申请回调
            case activity_jobrelease.PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startActivity();
                } else {
                    //申请权限
                    ActivityCompat.requestPermissions(activity, PERMISSION, PERMISSION_REQUEST_CODE);
                    break;
                }
            }

        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_IMAGE_REQUEST && data != null) {
                //获得选中的图片文件
                ArrayList<Image> selectImages = data.getParcelableArrayListExtra(SelectImageActivity.EXTRA_RESULT);
                mSelectImages.clear();

                //小于6张图片 加入添加相册+
                if (selectImages.size() < SelectImageActivity.MAX_SIZE) {
                    selectImages.add(image);
                }

                mSelectImages.addAll(selectImages);
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    /**
     * 图片拖动排列
     */
    private final ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

            // 获取触摸响应的方向   包含两个 1.拖动dragFlags 2.侧滑删除swipeFlags
            // 代表只能是向左侧滑删除，当前可以是这样ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT
            int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            int dragFlags;
            if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                dragFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            } else {
                dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            }
            return makeMovementFlags(dragFlags, swipeFlags);
        }

        /**
         * 拖动的时候不断的回调方法
         */
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            //获取到原来的位置
            int fromPosition = viewHolder.getAdapterPosition();
            //获取到拖到的位置
            int targetPosition = target.getAdapterPosition();
            if (fromPosition < targetPosition) {
                for (int i = fromPosition; i < targetPosition; i++) {
                    Collections.swap(mSelectImages, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > targetPosition; i--) {
                    Collections.swap(mSelectImages, i, i - 1);
                }
            }
            mAdapter.notifyItemMoved(fromPosition, targetPosition);
            return true;
        }

        /**
         * 侧滑删除后会回调的方法
         */
        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            mSelectImages.remove(position);
            mAdapter.notifyItemRemoved(position);

            //插入最后一个图片
            if (!isgetboolean()) {
                mSelectImages.add(image);
                mAdapter.notifyDataSetChanged();
            }
        }
    });

    private boolean isgetboolean() {
        for (Image mSelectImage : mSelectImages) {
            String path = mSelectImage.getPath();
            if (TextUtils.isEmpty(path)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 申请权限
     */
    private void selectImage() {
        int permission1 = ContextCompat.checkSelfPermission(activity, PERMISSION[0]);
        int permission2 = ContextCompat.checkSelfPermission(activity, PERMISSION[1]);
        if (permission1 == PackageManager.PERMISSION_GRANTED && permission2 == PackageManager.PERMISSION_GRANTED) {
            startActivity();
        } else {
            //申请读写权限
            ActivityCompat.requestPermissions(activity, PERMISSION, PERMISSION_REQUEST_CODE);
        }


    }

    /**
     * 转到相册选择页
     */
    private void startActivity() {
        SelectImageActivity.MAX_SIZE = 3;
        Intent intent = new Intent(context, SelectImageActivity.class);
        intent.putParcelableArrayListExtra("selected_images", mSelectImages);
        startActivityForResult(intent, SELECT_IMAGE_REQUEST);
    }

    private void getImages() {
        showDialog();
        for (Image image : mSelectImages) {
            //把默认空图排除
            if (TextUtils.isEmpty(image.getPath())) {
                mSelectImages.remove(image);
            }
        }

        //发布作业不带图片
        if (mSelectImages.size() == 0) {
            //发布作业提交
            sendclassTask("");
        } else {
            //发布作业有带图片
            List<String> arrayList = new ArrayList<>();
            for (int i = 0; i < mSelectImages.size(); i++) {
                Image selectImage = mSelectImages.get(i);
                String path = selectImage.getPath();
                File file = new File(path);
                //Android 图片压缩之鲁班Luban详解
                Luban.with(context).load(file).ignoreBy(100).filter(new CompressionPredicate() {
                    @Override
                    public boolean apply(String path) {
                        return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
                    }
                }).setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                        // TODO 压缩开始前调用，可以在方法内启动 loading UI
                    }

                    @Override
                    public void onSuccess(File file) {
                        // TODO 压缩成功后调用，返回压缩后的图片文件
                        //执行上传图片 1 图片 2 wave 3 mp4 4 docx
                        Datamodule.getInstance().uploadImg(file, 1, new CallBcak() {
                            @Override
                            public void Onfall() {

                            }

                            @Override
                            public void OnSuccess(Object o) {
                                //图片上传成功后回调
                                arrayList.add(o.toString().trim());
                                if (mSelectImages.size() == arrayList.size()) {
                                    //发布作业提交
                                    String json = JsonUitl.objectToString(arrayList);
                                    sendclassTask(json);
                                }
                            }
                        });
                    }

                    @Override
                    public void onError(Throwable e) {
                        // TODO 当压缩过程出现问题时调用
                    }
                }).launch();


            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MediaPlayHelper.playHelper().stop();
        miscplay = false;
        if (myHandler != null) {
            myHandler.removeMessages(Config.sussess);
        }
    }

    class myHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Config.sussess:
                    time.setText(String.format("%s/%ss", Config.generateTime(mediaPlayer.getCurrentPosition()), Config.generateTime(mediaPlayer.getDuration())));
                    break;
                case Config.fail:
                    time.setText(String.format("0:00/%ss", Config.generateTime(mediaPlayer.getDuration())));
                    break;
            }
        }
    }

    class myThread extends Thread {
        @Override
        public void run() {
            super.run();
            while (miscplay) {
                timetout++;
                Message obtain = Message.obtain();
                obtain.obj = timetout;
                obtain.what = Config.sussess;
                myHandler.sendMessage(obtain);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            //停止播放
            Message obtain = Message.obtain();
            obtain.obj = timetout;
            obtain.what = Config.fail;
            myHandler.sendMessage(obtain);
        }
    }
}