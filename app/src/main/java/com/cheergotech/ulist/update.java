package com.cheergotech.ulist;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;

import androidx.core.content.FileProvider;

import com.cheergotech.BuildConfig;
import com.cheergotech.R;
import com.cheergotech.UI.model.okhttp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class update {

    public static File files;

    /**
     * 获取版本号
     *
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
        int version = 0;
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            version = packInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return version;
    }

    /**
     * 提示下载
     *
     * @param context
     * @param
     */
    public static void showUpdaloadDialog(final Context context, final String path) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("版本升级")
                .setIcon(R.mipmap.ic_launcher)
                .setMessage("发现新版本！请及时更新")
                .setPositiveButton("确定", new sopm(context, path))
                .setNegativeButton("取消", null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    /**
     * 下载进度条创建
     *
     * @param context
     * @param downloadUrl
     */
    private static void loadUpload(Context context, String downloadUrl) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("版本升级");
        progressDialog.setIcon(R.mipmap.ic_launcher);
        progressDialog.setMessage("正在下载新版本");
        progressDialog.setCanceledOnTouchOutside(false);//不能手动取消下载进度对话框
        //progressDialog.setCanceledOnTouchOutside(false);//不能手动取消下载进度对话框
        progressDialog.show();
        getload(context, downloadUrl, progressDialog);//执行下载
    }

    /**
     * 执行下载
     *
     * @param context
     * @param url
     */
    private static void getload(final Context context, final String url, final ProgressDialog progressDialog) {
        Call call = okhttp.call(url);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream inputStream = response.body().byteStream();//输入流
                //在本地创建文件
                File file = new File(Environment.getExternalStorageDirectory(), getFileName(url));// 在本机创建文件
                files = file;//便于调开启权限返回自动安装应用
                FileOutputStream outputStream = new FileOutputStream(file);//输出流
                long length = response.body().contentLength();//下载文件大小
                progressDialog.setMax((int) length); //最大值
                if (inputStream != null) {
                    byte[] buf = new byte[10240];
                    int len = -1;
                    int process = 0;
                    while ((len = inputStream.read(buf)) != -1) {
                        outputStream.write(buf, 0, len);
                        process += len;//进度
                        progressDialog.setProgress(process);//正在下载中
                    }

                    //下载完成
                    progressDialog.cancel();
                    //progressDialog.dismiss();
                    outputStream.flush();
                    if (!file.exists()) {
                        Toasts.toastMessage("安装失败 不存在");
                        return;
                    }
                    //应用自动安装01
                    //updateapk(context,file);

                    //应用自动安装02
                    openAPK(context, file);

                }

            }
        });

    }

    //调用系统浏览器下载文件
    private static void updatedown(Context context, String path) {
        Intent date = new Intent(Intent.ACTION_VIEW, Uri.parse(path));
        context.startActivity(date);
    }

    //下载完成安装apk，系统发送一个intent
    public static void updateapk(Context context, File file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        String authority = context.getPackageName() + ".fileprovider";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Uri contentUri = FileProvider.getUriForFile(context, authority, file);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        }
        context.startActivity(intent);
    }

    //下载完成安装apk，系统发送一个intent
    private static void openAPK(Context context, File file) {
        //8.0以上的系统
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            boolean Installs = context.getPackageManager().canRequestPackageInstalls();
            if (Installs) {
                instarapk(context, file); //8.0以上正在安装应用
            } else {
                openeorr(context);//8.0去打开未知应用安装权限
            }
        } else {
            //8.0以下正在安装应用
            installApk(context, file);
        }


    }

    //android 8.0以上
    public static void instarapk(Context context, File file) {
        try {
            //fileprovider 这里需要和清单文件一致
            Uri uri = FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", file);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
            context.startActivity(intent);
        } catch (Exception e) {
            Log.d("TAG", "inistall 安装失败");
            e.printStackTrace();
        }

    }

    public static void installApk(Context context, String downloadApk) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        File file = new File(downloadApk);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Uri apkUri = FileProvider.getUriForFile(Objects.requireNonNull(context), BuildConfig.APPLICATION_ID + ".fileProvider", file);//这句很重要
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Uri uri = Uri.fromFile(file);
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
        }
        context.startActivity(intent);

    }

    //android8.0以下
    public static void installApk(Context context, File file) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    //打开未知应用权限
    private static void openeorr(Context context) {
        Uri packageURI = Uri.parse("package:" + context.getPackageName());//获取应用包名称
        Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI);//去打开未知应用权限
        Activity activity = (Activity) context;
        activity.startActivityForResult(intent, 800);
    }

    //获取文件名称
    public static String getFileName(String path) {
        String[] result = path.split("/");
        return result[result.length - 1];
    }

    /**
     * 下载APK执行
     */
    private static class sopm implements DialogInterface.OnClickListener {
        private final Context context;
        private final String path;

        public sopm(Context context, String path) {
            this.context = context;
            this.path = path;
        }

        @Override
        public void onClick(DialogInterface dialog, int which) {

            if (path.toLowerCase().endsWith(".apk")) {
                loadUpload(context, path);//应用自动安装
            } else {
                //调用系统浏览器下载数据
                updatedown(context, path);
            }


            ;
        }
    }

}


