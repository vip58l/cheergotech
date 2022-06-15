package com.cheergotech.ulist;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.cheergotech.Base.DemoApplication;
import com.cheergotech.R;
import com.cheergotech.UI.model.okhttp;
import com.cheergotech.listen.CallBcak;

import java.io.File;
import java.util.Map;

import okhttp3.RequestBody;

public class PostModule extends Handler {
    private CallBcak callBcak;
    private final String TAG = PostModule.class.getSimpleName();

    /**
     * get请求数据
     *
     * @param path
     * @param callBcak
     */
    public static void getModule(String path, CallBcak callBcak) {
        if (!Config.isNetworkAvailable()) {
            Toasts.toastMessage(DemoApplication.instance().getString(R.string.eroot));
            return;
        }
        PostModule postModule = new PostModule(path);
        postModule.callBcak = callBcak;
    }

    /**
     * 发送POST数据
     *
     * @param path
     * @param requestBody
     * @param callBcak
     */
    public static void postModule(String path, RequestBody requestBody, CallBcak callBcak) {
        if (!Config.isNetworkAvailable()) {
            Toasts.toastMessage(DemoApplication.instance().getString(R.string.eroot));
            return;
        }
        PostModule module = new PostModule(path, requestBody);
        module.callBcak = callBcak;
    }

    /**
     * 提交put 更新数据
     */
    public static void putModule(String path, RequestBody requestBody, CallBcak callBcak) {
        if (!Config.isNetworkAvailable()) {
            Toasts.toastMessage(DemoApplication.instance().getString(R.string.eroot));
            return;
        }
        PostModule postModule = new PostModule(path, requestBody, 1);
        postModule.callBcak = callBcak;

    }

    /**
     * 提交delete删除数据
     */
    public static void deleteModule(String path, RequestBody requestBody, CallBcak callBcak) {
        if (!Config.isNetworkAvailable()) {
            Toasts.toastMessage(DemoApplication.instance().getString(R.string.eroot));
            return;
        }
        PostModule postModule = new PostModule(path, requestBody, 2);
        postModule.callBcak = callBcak;
    }

    /**
     * 提更新或删除数据
     *
     * @param path
     * @param requestBody
     * @param callBcak
     * @param type
     */
    public static void putdelModule(String path, RequestBody requestBody, CallBcak callBcak, int type) {
        switch (type) {
            case 1:
                putModule(path, requestBody, callBcak);
                break;
            case 2:
                deleteModule(path, requestBody, callBcak);
                break;
        }
    }

    /**
     * 提交上传文件
     *
     * @param file
     * @param hashMap
     * @param callBcak
     */
    public static void okhttpimage(Map<String, String> hashMap, File file, CallBcak callBcak) {

        PostModule module = new PostModule(hashMap, file);
        module.callBcak = callBcak;

    }

    /**
     * 提交上传文件
     *
     * @param hashMap
     * @param requestBody
     * @param callBcak
     */
    public static void okhttpimage(Map<String, String> hashMap, RequestBody requestBody, CallBcak callBcak) {
        PostModule module = new PostModule(hashMap, requestBody);
        module.callBcak = callBcak;
    }

    /**
     * GET请求参数
     *
     * @param path
     */
    private PostModule(String path) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String response = okhttp.get(path);
                    Message message = new Message();
                    message.obj = response;
                    message.what = Config.sussess;
                    sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * GET请求参数
     *
     * @param path
     * @param callBcak
     */
    private PostModule(String path, CallBcak callBcak) {
        this.callBcak = callBcak;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String response = okhttp.get(path);
                    Message message = new Message();
                    message.obj = response;
                    message.what = Config.sussess;
                    sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * POST
     *
     * @param path
     * @param requestBody
     */
    private PostModule(String path, RequestBody requestBody) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    String date = okhttp.POST(path, requestBody);
                    Message message = new Message();
                    message.what = Config.sussess;
                    message.obj = date;
                    sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                    sendEmptyMessage(Config.fail);
                }
            }
        }.start();
    }

    /**
     * PUT/Del提更新或删除数据
     *
     * @param path
     * @param requestBody
     */
    private PostModule(String path, RequestBody requestBody, int type) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    String date = type == 1 ? okhttp.put(path, requestBody) : okhttp.delete(path, requestBody);
                    Message message = new Message();
                    message.what = Config.sussess;
                    message.obj = date;
                    sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                    sendEmptyMessage(Config.fail);
                }
            }
        }.start();
    }

    /**
     * post图片
     *
     * @param hashMap
     * @param file
     */
    private PostModule(Map<String, String> hashMap, File file) {

        new Thread() {
            @Override
            public void run() {
                super.run();
                okhttp.okhttp_image(hashMap, file, PostModule.this);
            }
        }.start();
    }

    /**
     * 其他
     *
     * @param hashMap
     * @param requestBody
     */
    private PostModule(Map<String, String> hashMap, RequestBody requestBody) {

        new Thread() {
            @Override
            public void run() {
                super.run();
                okhttp.okhttpimage(hashMap, requestBody, PostModule.this);
            }
        }.start();
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what) {
            case Config.sussess:
                String s = msg.obj.toString();
                if (!TextUtils.isEmpty(s)) {
                    callBcak.success(s);
                }
                break;
            case Config.fail:
                callBcak.fall();
                break;
        }

    }

}
