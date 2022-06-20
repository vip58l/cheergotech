package com.cheergotech.UI.activity.main.fragment;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.cheergotech.Base.BaseFragment;
import com.cheergotech.R;
import com.cheergotech.UI.activity.Login.activity_login;
import com.cheergotech.UI.activity.Web.DyWebActivity;
import com.cheergotech.UI.activity.viecode.activity_feedback;
import com.cheergotech.UI.activity.viecode.activity_viecode;
import com.cheergotech.UI.dialog.DialogMessage1;
import com.cheergotech.UI.dialog.DialogMessage13;
import com.cheergotech.UI.model.CahtAPI;
import com.cheergotech.UI.model.Datamodule;
import com.cheergotech.UI.model.Msgconfig;
import com.cheergotech.UI.model.loginOut;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.CacheUtil;
import com.cheergotech.ulist.Config;
import com.cheergotech.ulist.DataCleanManager;
import com.cheergotech.ulist.Toasts;
import com.cheergotech.ulist.glide.ImageLoadHelper;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * 作者QQ：804031885
 * Author 我的
 * Data 2022/5/1515:30
 * Description 首页1
 */
public class page5 extends BaseFragment {
    private static final String TAG = page5.class.getName();
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.school)
    TextView school;
    @BindView(R.id.teacher)
    TextView teacher;
    @BindView(R.id.avatar)
    ImageView avatar;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.clear)
    TextView clear;
    private Uri imageUri;
    private final String[] PERMISSION = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
    private final int PERMISSION_REQUEST_CODE = 100;
    private final int PERMISSION_REQUEST_CODE1 = 101;
    private final int REQUEST_IMAGE_CAPTURE = 102;

    @Override
    protected int getview() {
        return R.layout.fragment5;
    }

    @Override
    public void iniview() {
        clear.setText(DataCleanManager.getTotalCacheSize());
        phone.setText(userinfo.getPhone());
        school.setText(userinfo.getSchoolName());
        name.setText(userinfo.getRealName());
        if (!TextUtils.isEmpty(Msgconfig.getInstance().getRoleName())) {
            teacher.setText(Msgconfig.getInstance().getRoleName());
        }
        ImageLoadHelper.glideShowImageWithUrl(context, userinfo.getHeadImg(), avatar);
    }

    @Override
    public void initData() {
    }

    @Override
    public void OnEorr() {

    }

    @Override
    @OnClick({R.id.line0, R.id.line1, R.id.line2, R.id.line3, R.id.line4, R.id.line5, R.id.exit})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.line0:
                //更新头像
                DialogMessage13.show(context, new CallBcak() {
                    @Override
                    public void Onfall() {

                    }

                    @Override
                    public void setOnClickListener(int type) {
                        switch (type) {
                            case 1:
                            case 2:
                                selectImage(type);
                                break;
                        }
                    }
                });
                break;
            case R.id.line1:
                //关于我们
                activity_viecode.setAction(context);
                break;
            case R.id.line2:
                //反馈与建议
                activity_feedback.setAction(context);
                break;
            case R.id.line3: {
                //正在清理缓存
                mydialogshow(getString(R.string.t10));
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mydismiss();
                        DataCleanManager.clearAllCache(context);
                        clear.setText(DataCleanManager.getTotalCacheSize());
                        Toasts.showShort(getString(R.string.text22));
                    }
                }, 1000);

            }
            break;
            case R.id.line4:
                //用户协议
                DyWebActivity.starAction(context, CahtAPI.useing, getString(R.string.public0));
                break;
            case R.id.line5:
                //隐私协议
                DyWebActivity.starAction(context, CahtAPI.toprivate, getString(R.string.public1));
                break;
            case R.id.exit:
                //安全退出
                DialogMessage1.show(context, new CallBcak() {
                    @Override
                    public void OnClick(View view) {

                    }

                    @Override
                    public void setOnClickListener(int position) {
                        switch (position) {
                            case 1:
                                break;
                            case 2:
                                loginOut loginOut = new loginOut();
                                loginOut.setAccount(userinfo.getAccount());
                                Datamodule.getInstance().loginOut(loginOut, null);
                                exit();
                                break;
                        }
                    }
                });
                break;
        }
    }

    /**
     * 进入相册
     */
    private void toactionpick() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setDataAndType(MediaStore.Images.Media.INTERNAL_CONTENT_URI, "image/*");//相片类型
        startActivityForResult(intent, PERMISSION_REQUEST_CODE1);
    }

    /**
     * 启动相机程序拍照
     */
    private void toCamera() {
        imageUri = Uri.fromFile(new File(CacheUtil.getCacheDirectory(context, null), "noCroppedImage.jpg"));
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        } else {
            Toasts.toastMessage(getString(R.string.Toast09));
        }
    }

    /**
     * 申请权限
     */
    private void selectImage(int type) {
        this.type = type;
        int permission1 = ContextCompat.checkSelfPermission(context, PERMISSION[0]);
        int permission2 = ContextCompat.checkSelfPermission(context, PERMISSION[1]);
        int permission3 = ContextCompat.checkSelfPermission(context, PERMISSION[2]);
        if (permission1 == PackageManager.PERMISSION_GRANTED && permission2 == PackageManager.PERMISSION_GRANTED && permission3 == PackageManager.PERMISSION_GRANTED) {
            updateImage();
        } else {
            //申请读写权限
            requestPermissions(PERMISSION, PERMISSION_REQUEST_CODE);
        }
    }


    /**
     * 进行裁剪图片保存到文件中
     *
     * @param imageUri
     */
    private void startUCrop(Uri imageUri) {
        //缓存文件
        String fileName = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".jpg";
        Uri destinationUri = Uri.fromFile(new File(CacheUtil.getCacheDirectory(context, null), fileName));
        UCrop uCrop = UCrop.of(imageUri, destinationUri);
        UCrop.Options options = new UCrop.Options();
        //设置裁剪图片可操作的手势
        options.setAllowedGestures(UCropActivity.SCALE, UCropActivity.ROTATE, UCropActivity.ALL);
        //设置toolbar颜色
        options.setToolbarColor(ActivityCompat.getColor(context, R.color.shapa));
        //设置状态栏颜色
        options.setStatusBarColor(ActivityCompat.getColor(context, R.color.shapa));
        //是否能调整裁剪框
        //options.setFreeStyleCropEnabled(false);
        uCrop.withOptions(options);
        uCrop.withAspectRatio(1, 1); //比例
        uCrop.start(context, this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                //转到相册
                case PERMISSION_REQUEST_CODE1: {
                    startUCrop(data.getData());
                    break;
                }

                //转到相机拍照
                case REQUEST_IMAGE_CAPTURE: {
//                    Bundle extras = data.getExtras();
//                    Bitmap imageBitmap = (Bitmap) extras.get("data");
//                    avatar.setImageBitmap(imageBitmap);
                    startUCrop(imageUri);
                    break;
                }

                //裁切后得到的图片
                case UCrop.REQUEST_CROP: {
                    startUCrop(data);
                    break;
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                updateImage();
            } else {
                //申请读写权限
                requestPermissions(PERMISSION, PERMISSION_REQUEST_CODE);
            }
        }
    }


    /**
     * 保存文件到本地
     *
     * @param bitmap
     */
    public void saveBitmapFile(Bitmap bitmap) {
        File file = new File("storage/emulated/legacy/s.jpg");//将要保存图片的路径
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 读取本地文图片
     *
     * @param data
     */
    private void update(Intent data) {
        Uri selectedImage = data.getData();
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(selectedImage, filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();
        Bitmap bm = BitmapFactory.decodeFile(picturePath);
        avatar.setImageBitmap(bm);
    }

    private void exit() {
        Config.DeLeteUserinfo(context);
        Msgconfig.getInstance().setLogin(false);
        Msgconfig.getInstance().setAccessToken("");
        Msgconfig.getInstance().setNames("");
        Msgconfig.getInstance().setClassId(0);
        activity_login.setAction(context, 0);
    }


    /**
     * 拍照或上传图片处理
     */
    private void updateImage() {
        switch (type) {
            case 1:
                //拍照
                toCamera();
                break;
            case 2:
                //相册
                toactionpick();
                break;
        }
    }

    /**
     * 第三方裁剪库回调处理
     *
     * @param data
     */
    private void startUCrop(Intent data) {
        showDialog();
        Uri croppedUri = UCrop.getOutput(data);
        if (croppedUri != null) {
            Bitmap bit = null;
            try {
                //bit = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(croppedUri));
                //avatar.setImageBitmap(bit);

                File file = new File(new URI(croppedUri.toString()));

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
                    public void onSuccess(File files) {
                        //type 1 图片 2 wave 3 mp4 4 docx
                        Datamodule.getInstance().uploadImg(files, 1, new CallBcak() {
                            @Override
                            public void Onfall() {

                            }

                            @Override
                            public void OnSuccess(Object o) {
                                //修改用户信息
                                userinfo.setHeadImg(o.toString().trim());
                                Datamodule.getInstance().edituserinfo(this);
                            }

                            @Override
                            public void OnSuccess() {
                                dismissDialog();
                                Toasts.toastMessage(getString(R.string.toast9));
                                ImageLoadHelper.glideShowImageWithUrl(context, userinfo.getHeadImg(), avatar);
                            }
                        });
                    }

                    @Override
                    public void onError(Throwable e) {
                        // TODO 当压缩过程出现问题时调用
                    }
                }).launch();


            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

}