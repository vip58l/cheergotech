package com.cheergotech.ulist;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;

import com.cheergotech.Base.DemoApplication;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Config {
    private final static String info = "info";
    private final static String edit = "edit";
    public static final int sussess = 200;
    public static final int on = 201;
    public static final int fail = 400;
    public static final int setResult = 800;

    public static int a(Context context, String String1, String String2) {
        return context.getResources().getIdentifier(String2, String1, context.getPackageName());
    }

    public static boolean ok(String paramString) {
        return (paramString != null) && (!paramString.equals("")) && (!paramString.equals("null") && (paramString.contains("|")));
    }

    public static String mp(String paramString) {
        int i = paramString.lastIndexOf("/");
        int j = paramString.lastIndexOf(".");
        if ((i != -1) && (j != -1))
            return paramString.substring(i + 1, j);
        return "";
    }

    public static int Display(Context context, float paramFloat) {
        return (int) (0.5F + paramFloat * context.getResources().getDisplayMetrics().density);
    }

    /**
     * ????????????????????????
     *
     * @param context
     * @param Model
     * @param device
     * @param Version
     * @param IMEI
     */
    public static void putString(Context context, String Model, String device, String Version, String IMEI) {
        SharedPreferences sp = context.getSharedPreferences(info, MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("Model", Model);
        edit.putString("device", device);
        edit.putString("Version", Version);
        edit.putString("IMEI", IMEI);
        edit.commit();
    }

    /**
     * ?????????????????????
     *
     * @param key
     * @param value
     */
    public static void putString(String key, Object value) {
        SharedPreferences sp = DemoApplication.instance().getSharedPreferences(edit, MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(key, new Gson().toJson(value));
        edit.commit();
    }

    /**
     * ?????????????????????
     *
     * @param context
     * @param key
     * @param value
     */
    public static void putString(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(info, MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(key, value);
        edit.commit();
    }

    /**
     * ????????????
     *
     * @param context
     * @param key
     * @return
     */
    public static void putString(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences(info, MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean(key, value);
        edit.commit();
    }

    /**
     * ????????????
     *
     * @param context
     * @param key
     * @return
     */
    public static void putString(Context context, String key, int value) {
        SharedPreferences sp = context.getSharedPreferences(info, MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt(key, value);
        edit.commit();
    }

    /**
     * ??????
     *
     * @param context
     * @param key
     * @return
     */
    public static String getString(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(info, MODE_PRIVATE);
        return sp.getString(key, "");
    }


    /**
     * ?????? true false
     *
     * @param context
     * @param key
     * @return
     */
    public static boolean getboolean(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(info, MODE_PRIVATE);
        return sp.getBoolean(key, false);
    }

    public static int getint(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(info, MODE_PRIVATE);
        return sp.getInt(key, 0);
    }

    /**
     * ????????????
     *
     * @param context
     * @param key
     */
    public static void DeleteString(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(info, MODE_PRIVATE);
        sp.edit().remove(key).commit();
    }

    /**
     * ????????????
     */
    public static void DeLeteall(Context context) {
        SharedPreferences sp = context.getSharedPreferences(info, MODE_PRIVATE);
        sp.edit().clear().commit();

    }

    /**
     * DeLeteUserinfo????????????
     */
    public static void DeLeteUserinfo(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Constants.USERINFO, MODE_PRIVATE);
        sp.edit().clear().commit();

    }

    /**
     * DeLeteUserinfo????????????
     */
    public static void DeLeteUserinfo(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(Constants.USERINFO, MODE_PRIVATE);
        sp.edit().remove(key).commit();

    }


    /**
     * MD5?????????
     *
     * @param key
     * @return
     */
    public static String md532(String key) {
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = key.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str).toLowerCase();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * MD5?????????
     *
     * @param key
     * @return
     */
    public static String md5(String key) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(key.getBytes(StandardCharsets.UTF_8));
            return toHex(bytes).toLowerCase();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String toHex(byte[] bytes) {
        final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
            ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
        }
        return ret.toString();
    }

    /**
     * [????????????????????????????????????]
     *
     * @return ???????????????????????????
     */
    public static int getVersionCode() {
        try {
            PackageManager packageManager = DemoApplication.instance().getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(DemoApplication.instance().getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * [????????????????????????????????????]
     *
     * @param context
     * @return ???????????????????????????
     */
    public static String getVersionName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * ????????????????????????
     */
    public static String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }

    /**
     * ???????????? bitmap
     *
     * @param context
     */
    public static Bitmap getBitmap(Context context) {
        PackageManager packageManager = null;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = context.getApplicationContext()
                    .getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(
                    context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            applicationInfo = null;
        }
        Drawable d = packageManager.getApplicationIcon(applicationInfo); //xxx???????????????????????????drawable
        BitmapDrawable bd = (BitmapDrawable) d;
        Bitmap bm = bd.getBitmap();
        return bm;
    }

    public static String url() {
        return "http://v.6.cn/minivideo/getlist.php?act=recommend&pageSize=20&page=1&";
    }

    /**
     * ???????????????????????????
     *
     * @throws PackageManager.NameNotFoundException
     */
    public static String[] getPackageversion(Context context) {
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo info = pm.getPackageInfo(context.getPackageName(), 0);
            String versionName = info.versionName;
            String packageName = info.packageName;
            int code = info.versionCode;

            //?????????????????????????????????????????????
            String[] array = new String[3];
            array[0] = versionName;
            array[1] = packageName;
            array[2] = String.valueOf(code);
            return array;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * ????????????????????????
     *
     * @return
     */
    public static String DateTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//??????????????????
        String format = df.format(new Date());// new Date()???????????????????????????
        return format;
    }


    /**
     * ????????????????????????
     *
     * @return
     */
    public static String DateTime(boolean key) {
        SimpleDateFormat df = new SimpleDateFormat(key ? "yyyyMMddHHmmss" : "yyyyMMdd");//??????????????????
        String format = df.format(new Date());// new Date()???????????????????????????
        return format;
    }

    /**
     * ????????????????????????
     *
     * @return
     */
    public static String Datehh() {
        SimpleDateFormat df = new SimpleDateFormat("HH");//??????????????????
        String format = df.format(new Date());// new Date()???????????????????????????
        return format;
    }


    /**
     * ???????????????????????????
     */
    public static String dateToStamp(String s) {
        String res;
        Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = simpleDateFormat.parse(s);
        } catch (Exception e) {
        }
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }

    /**
     * ???????????????????????????
     */
    public static String stampToDate(String s) {
        try {
            String res;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long lt = new Long(s) * 1000;
            Date date = new Date(lt);
            res = simpleDateFormat.format(date);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * ???????????????????????????
     */
    public static String stampToDate(String Time, String s) {
        try {
            String res;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long lt = new Long(Time);
            Date date = new Date(lt);
            res = simpleDateFormat.format(date);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * ???????????????????????????
     */
    public static String stampToDateh(String Time, String s) {
        try {
            String res;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm");
            long lt = new Long(Time);
            Date date = new Date(lt);
            res = simpleDateFormat.format(date);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * ???????????????????????????
     */
    public static String stampToDatehmmdd(String Time) {
        try {
            String res;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM???dd??? EEEE");
            long lt = new Long(Time);
            Date date = new Date(lt);
            res = simpleDateFormat.format(date);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * ???????????????????????????
     */
    public static String stampToDatehmm(String Time) {
        try {
            String res;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy???MM???dd???");
            long lt = new Long(Time);
            Date date = new Date(lt);
            res = simpleDateFormat.format(date);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * ???????????????????????????
     */
    public static String stampToDateh(String Time, String h, String s) {
        try {
            String res;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            long lt = new Long(Time);
            Date date = new Date(lt);
            res = simpleDateFormat.format(date);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * ???????????????????????????
     */
    public static String stampToDate(String s, int f) {
        try {
            String res;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            long lt = new Long(s) * 1000;
            Date date = new Date(lt);
            res = simpleDateFormat.format(date);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * ???????????????????????????
     */
    public static String stampToDate(String beginDate, boolean istime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(istime ? "yyyy-MM-dd HH:mm:ss" : "yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date(Long.parseLong(beginDate) * 1000)); // ?????????????????????
    }

    /**
     * ???????????????????????????
     */
    public static String stampToTime(long stamp) {
        String time;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(stamp * 1000);
        time = simpleDateFormat.format(date);
        return time;
    }

    /**
     * ????????????????????????+???????????????
     */
    public static String getRandomFileName() {
        SimpleDateFormat simpleDateFormat;
        simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        String str = simpleDateFormat.format(date);
        Random random = new Random();
        int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// ??????5????????????
        return str + rannum;// ???????????? + ??????5??????????????????
    }

    /**
     * ????????????????????????????????????10????????????8???????????????????????????
     *
     * @param timestamp
     * @return
     */
    public static String timestamp(String timestamp) {
        long lt = new Long(timestamp) * 1000;
        Date date = new Date(lt);
        return TimeUtil.getTimeFormatText(date);

    }

    /**
     * ????????????????????????????????????10????????????8???????????????????????????
     *
     * @param timestamp
     * @return
     */
    public static String timestamp(String timestamp, String s) {
        long lt = new Long(timestamp);
        Date date = new Date(lt);
        return TimeUtil.getTimeFormatText(date);

    }


    /**
     * Android???????????????????????????
     *
     * @param activity
     */
    public static void mysetFlags(Activity activity) {
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
    }

    /**
     * ????????????
     * ??????????????????
     *
     * @param context
     * @return
     */
    public static boolean networkConnected(Context context) {
        return wifimone.isNetworkConnected(context);
    }

    /**
     * ????????????
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    /**
     * ????????????
     *
     * @return
     */
    public static boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) DemoApplication.instance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }


    /**
     * ?????????????????????????????????
     */
    public static void hideBottomUIMenu(Activity activity) {
        //?????????????????????????????????
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = activity.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            View decorView = activity.getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    /**
     * ??????????????????
     *
     * @param activity
     */
    public static void AsetctivityBLACK(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setNavigationBarColor(Color.BLACK);
        }
    }

    /**
     * ??????????????????
     *
     * @param activity
     */
    public static void AsetctivityBLACK(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setNavigationBarColor(color);
        }
    }

    /**
     * ??????????????????
     *
     * @param path
     * @return
     */
    public static String getFileName(String path) {
        String[] result = path.split("/");
        return result[result.length - 1];
    }

    /**
     * ??????????????????
     *
     * @param path
     * @return
     */
    public static String getFileName(String path, String j) {
        String[] result = path.split(j);
        return result[result.length - 1];
    }

    /**
     * ????????????????????????
     */
    public static String generateTime(long position) {
        int totalSeconds = (int) (position / 1000); //???1000
        int seconds = totalSeconds % 60; //???
        int minutes = (totalSeconds / 60) % 60; //???
        int hours = totalSeconds / 3600; //???
        return hours > 0 ? String.format(Locale.US, "%02d:%02d:%02d", hours, minutes, seconds) : String.format(Locale.US, "%02d:%02d", minutes, seconds);
    }

    /**
     * ????????????-???
     *
     * @param distance
     */
    public static String getdistance(float distance) {
        float Kilometer = (distance / 1000); //??????
        float rice = (distance % 1000); //???
        StringBuffer sb = new StringBuffer();
        if (Kilometer > 0.1f) {
            sb.append(String.format("%.1f", Kilometer) + "Km");
        } else {
            sb.append(String.format("%.2f", rice) + "???");
        }
        return sb.toString();
    }

    /*
     * ????????????????????????,context??????????????? ???className?????????name
     */
    public static boolean isServiceRunning(String className) {
        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager) DemoApplication.instance().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList = activityManager.getRunningServices(30);
        if (!(serviceList.size() > 0)) {
            return false;
        }
        for (int i = 0; i < serviceList.size(); i++) {
            if (serviceList.get(i).service.getClassName().contains(className) == true) {
                isRunning = true;
                break;
            }
        }
        return isRunning;
    }

    /**
     * ??????Cookie
     */
    public static String GetCOOKIE(String key) {
        SharedPreferences preference = DemoApplication.instance().getSharedPreferences("COOKIE", Context.MODE_PRIVATE);
        return preference.getString(key, "");
    }

    /**
     * ??????Cookie
     */
    public static void SetCOOKIE(String key, String value) {
        SharedPreferences preference = DemoApplication.instance().getSharedPreferences("COOKIE", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * ??????assets ????????????
     *
     * @param fileName
     * @param context
     * @return
     */
    public static String getJson(String fileName, Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //??????assets???????????????
            AssetManager assetManager = context.getAssets();
            InputStreamReader inputStreamReader = new InputStreamReader(assetManager.open(fileName), StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            inputStreamReader.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /**
     * ????????????
     */
    public static boolean loginByGet() throws IOException {
        URL url = new URL("BuildConfig.HTTP_API)");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
        int code = conn.getResponseCode();
        return code == 200;
    }

    /**
     * ??????????????????
     *
     * @param context
     */
    public static void openactiity(Context context, String appPackageName) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(appPackageName);
        context.startActivity(intent);
    }


    /**
     * ????????????
     *
     * @return
     */
    public static String getName() {
        String[] stringArray = {"11", ""};
        int result = new Random().nextInt(stringArray.length);
        return stringArray[result];
    }

    /**
     * ????????????
     *
     * @return
     */
    public static int random() {
        return (int) (Math.random() * 1000 + 1);
    }

    /**
     * ?????????????????????
     *
     * @param text
     * @return
     */
    public static String FindPhoneNumber(String text) {
        if (TextUtils.isEmpty(text)) {
            return FindQqOrWxNumber("");
        }
        Pattern pattern = Pattern.compile("(?<!\\d)(?:(?:1[345689]\\d{9})|(?:861[35689]\\d{9}))(?!\\d)");
        Matcher matcher = pattern.matcher(text);
        //StringBuffer bf = new StringBuffer(64);
        while (matcher.find()) {
            text = text.replace(matcher.group(), matcher.group().substring(0, 3) + "********");
        }
        return FindQqOrWxNumber(text);
    }

    /**
     * ??????qq????????????
     */
    private static String FindQqOrWxNumber(String text) {
        if (TextUtils.isEmpty(text)) {
            return "";
        }
        Pattern pattern = Pattern.compile("(??????|QQ|qq|weixin|WX|wx|1[0-9]{10}|[a-zA-Z0-9\\-\\_]{6,16}|[0-9]\n{6,11})+");
        Matcher matcher = pattern.matcher(text);
        //StringBuffer bf = new StringBuffer(64);
        while (matcher.find()) {
            text = text.replace(matcher.group(), "******");
        }
        return text;
    }

    /**
     * ?????????????????????
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        //"[1]"?????????1????????????1???"[358]"????????????????????????3???5???8???????????????"\\d{9}"????????????????????????0???9???????????????9??????
        String telRegex = "[1][3456789]\\d{9}";
        if (TextUtils.isEmpty(mobiles)) return false;
        else return mobiles.matches(telRegex);
    }

    /**
     * QQ????????????????????????
     *
     * @param qqorwx
     * @return
     */
    public static boolean isQQOrWX(String qqorwx) {
        if (qqorwx.equals("")) {
            return false;
        }
        //QQ?????????5????????????????????????6?????????20???
        Pattern p = Pattern.compile("^[a-zA-Z0-9_-]{5,19}$");
        Matcher m = p.matcher(qqorwx);
        return m.matches();
    }

    /**
     * ?????????????????????????????????????????????????????????+???????????????????????????????????????????????????
     *
     * @param name
     * @return
     */
    public static boolean isName(String name) {
        if (TextUtils.isEmpty(name)) {
            return false;
        }

//??????????????????????????????????????????????????????????????????????????????Android EditText??????????????????????????????????????????
        Pattern p = Pattern.compile("^[\u4E00-\u9FA5a-zA-Z]+");
        Matcher m = p.matcher(name);
        return m.matches();
    }

    /**
     * ?????????:?????????????????????????????????,?????????????????????????????????????????????????????????
     *
     * @param phone
     * @return
     */
    public boolean isPhoneNumber1(String phone) {
        String regExp = "^[1]([3][0-9]{1}|50|51|52|53|54|55|56|57|58|59|47|77|80|81|82|83|84|85|86|87|88|89)[0-9]{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(phone);
        return m.find();
    }

    /**
     * ?????????:?????????????????????????????????????????????,???140,141??????????????????????????????????????????
     *
     * @param phone
     * @return
     */
    public boolean isPhoneNumber2(String phone) {
        String regExp = "^1[3|4|5|7|8]\\d{9}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(phone);
        return m.find();
    }


}

