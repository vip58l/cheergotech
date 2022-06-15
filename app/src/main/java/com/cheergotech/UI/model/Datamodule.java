package com.cheergotech.UI.model;

import android.content.Context;
import android.text.TextUtils;

import com.cheergotech.Base.DemoApplication;
import com.cheergotech.R;
import com.cheergotech.UI.model.healthy.StudentJiankang;
import com.cheergotech.UI.model.healthy.health;
import com.cheergotech.UI.model.healthy.healthlist;
import com.cheergotech.listen.CallBcak;
import com.cheergotech.ulist.Config;
import com.cheergotech.ulist.Constants;
import com.cheergotech.ulist.Logi;
import com.cheergotech.ulist.PostModule;
import com.cheergotech.ulist.Toasts;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 封装请求类
 */
public class Datamodule {
    private static final String TAG = Datamodule.class.getName();
    private static Datamodule Instance;
    private Gson gson;
    private Context context;
    private MediaType JSON = MediaType.parse("application/json;charset=utf-8");

    public static Datamodule getInstance() {
        if (Instance == null) {
            Instance = new Datamodule(DemoApplication.instance());
        }
        return Instance;
    }

    public Datamodule(Context context) {
        this.context = context;
        this.gson = new Gson();
    }

    /**
     * 登录请求
     *
     * @param callBcak
     */
    public void logdin(appLoginDto loginDto, CallBcak callBcak) {
        String json = gson.toJson(loginDto);
        RequestBody requestBody = RequestBody.create(JSON, json);
        PostModule.postModule(CahtAPI.login, requestBody, new CallBcak() {
            @Override
            public void success(String s) {
                Logi.d(TAG, "响应：" + s);
                try {
                    Type objectType = new TypeToken<BackCallResult<Mesresult<UserInfo, List<childList>>>>
                            () {
                    }.getType();

                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();

                    if (callBcak != null) {
                        if (backCallResult.getCode().equals("200")) {
                            //本地保存登录信息
                            Mesresult mesresult = (Mesresult) backCallResult.getResult();
                            UserInfo userInfo = (UserInfo) mesresult.getUserInfo();
                            UserInfo.getInstance().setUserInfo(userInfo);
                            Msgconfig.getInstance().setAccessToken(mesresult.getAccessToken());
                            Msgconfig.getInstance().setRoleName(mesresult.getRoleName());
                            Msgconfig.getInstance().setLogin(true); //登录成功

                            //本地保存孩子信息
                            List<childList> childList = (List<childList>) mesresult.getChildList();
                            if (childList.size() > 0) {
                                Msgconfig.getInstance().setChildList(childList);
                                Msgconfig.getInstance().setClassId(childList.get(0).getClassId());
                            }

                            callBcak.OnSuccess();
                        } else {
                            Toasts.toastMessage(msg);
                            callBcak.Onfall();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {

            }
        });
    }

    /**
     * 用户退出登录
     *
     * @param callBcak
     */
    public void loginOut(loginOut loginOut, CallBcak callBcak) {
        RequestBody requestBody = new FormBody.Builder()
                .add(Constants.account, TextUtils.isEmpty(loginOut.getAccount()) ? "" : loginOut.getAccount()) //当前登录账号
                .build();
        PostModule.postModule(CahtAPI.loginOut, requestBody, new CallBcak() {
            @Override
            public void success(String s) {
                try {
                    Type objectType = new TypeToken<BackCallResult<Integer>>
                            () {
                    }.getType();

                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();
                    if (callBcak != null) {
                        if (backCallResult.getCode().equals("200")) {
                            callBcak.OnSuccess();
                        } else {
                            Toasts.toastMessage(msg);
                            callBcak.Onfall();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {

            }
        });
    }

    /**
     * 请假人数-列表
     *
     * @param classId
     * @param nowPage
     * @param pageSize
     * @param callBcak
     */
    public void leavelist(int classId, int nowPage, int pageSize, CallBcak callBcak) {
        Map<String, String> map = new HashMap<>();
        map.put("classId", String.valueOf(classId));
        map.put("nowPage", String.valueOf(nowPage));
        map.put("pageSize", String.valueOf(pageSize));
        String param = CahtAPI.param(CahtAPI.leavelist, map);
        PostModule.getModule(param, new CallBcak() {
            @Override
            public void success(String s) {
                try {
                    Type objectType = new TypeToken<BackCallResult<leavelist<volist, Integer>>>
                            () {
                    }.getType();

                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();
                    if (callBcak != null) {
                        if (backCallResult.getCode().equals("200")) {
                            callBcak.OnSuccess(backCallResult.getResult());
                        } else {
                            Toasts.toastMessage(msg);
                            callBcak.Onfall();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {

            }
        });

    }

    /**
     * 请假列表(新)
     * 查询类型 0：全部 1：待审批 2：已审批
     *
     * @param classId
     * @param nowPage
     * @param pageSize
     * @param callBcak
     */
    public void leavelistnew(int classId, int nowPage, int pageSize, int type, CallBcak callBcak) {
        Map<String, String> map = new HashMap<>();
        map.put(Constants.classId, String.valueOf(classId));
        map.put(Constants.nowPage, String.valueOf(nowPage));
        map.put(Constants.pageSize, String.valueOf(pageSize));
        map.put(Constants.type, String.valueOf(type));
        String param = CahtAPI.param(CahtAPI.leave, map);
        PostModule.getModule(param, new CallBcak() {
            @Override
            public void success(String s) {
                Logi.d(TAG, "success: " + s);
                try {
                    Type objectType = new TypeToken<BackCallResult<leavelist<volist, Integer>>>
                            () {
                    }.getType();

                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();
                    if (callBcak != null) {
                        if (backCallResult.getCode().equals("200")) {
                            callBcak.OnSuccess(backCallResult.getResult());
                        } else {
                            Toasts.toastMessage(msg);
                            callBcak.Onfall();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {

            }
        });

    }

    /**
     * 到校人数-列表
     *
     * @param classId
     * @param nowPage
     * @param pageSize
     * @param callBcak
     */
    public void normallist(int classId, int nowPage, int pageSize, int type, CallBcak callBcak) {
        Map<String, String> map = new HashMap<>();
        map.put(Constants.classId, String.valueOf(classId));
        map.put(Constants.nowPage, String.valueOf(nowPage));
        map.put(Constants.pageSize, String.valueOf(pageSize));
        map.put(Constants.type, String.valueOf(type));
        String param = CahtAPI.param(CahtAPI.normallist, map);
        PostModule.getModule(param, new CallBcak() {
            @Override
            public void success(String s) {
                try {
                    Type objectType = new TypeToken<BackCallResult<normalresult<PageInfo<Devrlist, Integer>>>>
                            () {
                    }.getType();

                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();
                    if (callBcak != null) {
                        if (backCallResult.getCode().equals("200")) {
                            callBcak.OnSuccess(backCallResult.getResult());
                        } else {
                            Toasts.toastMessage(msg);
                            callBcak.Onfall();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {

            }
        });

    }

    /**
     * 请假信息-详情
     */
    public void leaveinfo(int id, CallBcak callBcak) {
        Map<String, String> map = new HashMap<>();
        map.put("id", String.valueOf(id));
        String param = CahtAPI.param(CahtAPI.leaveinfo, map);
        PostModule.getModule(param, new CallBcak() {
            @Override
            public void success(String s) {
                try {
                    Type objectType = new TypeToken<BackCallResult<volist>>
                            () {
                    }.getType();

                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();
                    if (callBcak != null) {
                        if (backCallResult.getCode().equals("200")) {
                            callBcak.OnSuccess(backCallResult.getResult());
                        } else {
                            Toasts.toastMessage(msg);
                            callBcak.Onfall();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {

            }
        });
    }

    /**
     * 学生信息-学生健康
     * TYPE查询类型 0：全部 1：正常 2：异常 3:未采集
     *
     * @param classId
     * @param nowPage
     * @param pageSize
     * @param callBcak
     */
    public void stuinfohealth(int classId, int nowPage, int pageSize, int type, CallBcak callBcak) {
        Map<String, String> map = new HashMap<>();
        map.put(Constants.classId, String.valueOf(classId));
        map.put(Constants.nowPage, String.valueOf(nowPage));
        map.put(Constants.pageSize, String.valueOf(pageSize));
        map.put(Constants.type, String.valueOf(type));
        String param = CahtAPI.param(CahtAPI.stuinfohealth, map);
        PostModule.getModule(param, new CallBcak() {
            @Override
            public void success(String s) {
                try {
                    Type objectType = new TypeToken<BackCallResult<StudentJiankang<health<healthlist, Integer>>>>
                            () {
                    }.getType();

                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();
                    if (callBcak != null) {
                        if (backCallResult.getCode().equals("200")) {
                            callBcak.OnSuccess(backCallResult.getResult());
                        } else {
                            Toasts.toastMessage(msg);
                            callBcak.Onfall();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {

            }
        });

    }

    /**
     * 学生信息-学生健康-通知家长
     *
     * @param message
     * @param callBcak
     */
    public void notice(messageSendDTO message, CallBcak callBcak) {
        String json = gson.toJson(message);

        RequestBody requestBody = RequestBody.create(JSON, json);
        PostModule.postModule(CahtAPI.healthnotice, requestBody, new CallBcak() {
            @Override
            public void success(String s) {
                try {
                    Type objectType = new TypeToken<BackCallResult<Object>>() {
                    }.getType();
                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();
                    if (callBcak != null) {

                        switch (backCallResult.getCode()) {
                            case "200":
                                callBcak.OnSuccess();
                                break;
                            case "10000":
                                Toasts.toastMessage(msg);
                                break;
                            default:
                                Toasts.toastMessage(msg);
                                //callBcak.Onfall();
                                break;
                        }


                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {

            }
        });
    }

    /**
     * 学生信息-学生健康-生成报告
     *
     * @param message
     * @param callBcak
     */
    public void report(stuinfohealthreport message, CallBcak callBcak) {
        String json = gson.toJson(message);
        Logi.request(TAG, json);

        RequestBody requestBody = RequestBody.create(JSON, json);
        PostModule.postModule(CahtAPI.report, requestBody, new CallBcak() {
            @Override
            public void success(String s) {
                Logi.response(TAG, s);
                try {
                    Type objectType = new TypeToken<BackCallResult<Object>>() {
                    }.getType();
                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();
                    if (callBcak != null) {
                        switch (backCallResult.getCode()) {
                            case "200":
                                callBcak.OnSuccess();
                                break;
                            case "10000":
                                Toasts.toastMessage(msg);
                                break;
                            default:
                                Toasts.toastMessage(msg);
                                //callBcak.Onfall();
                                Logi.d(TAG, "Onfall: ");
                                break;
                        }


                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    //{"timestamp":1654850284330,"status":404,"error":"Not Found","message":"","path":"/home-page/stu-info-health/report"}
                    Type objectType = new TypeToken<Eorr>() {
                    }.getType();
                    Eorr eorr = gson.fromJson(s, objectType);
                    String message1 = eorr.getMessage();
                    Toasts.toastMessage(String.format("%s|%s|%s|找不到数据 请稍后再试", eorr.getStatus(), message1, eorr.getError()));
                }
            }

            @Override
            public void fall() {

            }
        });
    }

    /**
     * 学生信息-学生状态
     *
     * @param studentId
     * @param callBcak
     */
    public void stuinfostatus(int studentId, CallBcak callBcak) {
        String json = "{\"studentId\":" + studentId + "}";

        RequestBody requestBody = RequestBody.create(JSON, json);
        PostModule.postModule(CahtAPI.healthnotice, requestBody, new CallBcak() {
            @Override
            public void success(String s) {
                Logi.d(TAG, "success: " + s);
                try {
                    Type objectType = new TypeToken<BackCallResult<Object>>() {
                    }.getType();
                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();
                    if (callBcak != null) {
                        if (backCallResult.getCode().equals("200")) {
                            callBcak.OnSuccess();
                        } else {
                            Toasts.toastMessage(msg);
                            callBcak.Onfall();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {

            }
        });
    }

    /**
     * 缺勤人数-列表
     *
     * @param classId
     * @param nowPage
     * @param pageSize
     * @param callBcak
     */
    public void absencelist(int classId, int nowPage, int pageSize, CallBcak callBcak) {
        Map<String, String> map = new HashMap<>();
        map.put(Constants.classId, String.valueOf(classId));
        map.put(Constants.nowPage, String.valueOf(nowPage));
        map.put(Constants.pageSize, String.valueOf(pageSize));
        String param = CahtAPI.param(CahtAPI.absencelist, map);
        PostModule.getModule(param, new CallBcak() {
            @Override
            public void success(String s) {
                Logi.d(TAG, "success: " + s);
                try {
                    Type objectType = new TypeToken<BackCallResult<health<absence, Integer>>>
                            () {
                    }.getType();

                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();
                    if (callBcak != null) {
                        if (backCallResult.getCode().equals("200")) {
                            callBcak.OnSuccess(backCallResult.getResult());
                        } else {
                            Toasts.toastMessage(msg);
                            callBcak.Onfall();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {

            }
        });

    }

    /**
     * 缺勤人数-列表-状态处理
     * type 1:在校 2:请假 3:缺勤
     */
    public void handlestatus(int studentId, int type, CallBcak callBcak) {
        RequestBody requestBody = new FormBody.Builder()
                .add("studentId", String.valueOf(studentId))
                .add("type", String.valueOf(type))
                .build();
        PostModule.putModule(CahtAPI.handlestatus, requestBody, new CallBcak() {
            @Override
            public void success(String s) {
                try {
                    Type objectType = new TypeToken<BackCallResult<Object>>
                            () {
                    }.getType();
                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();
                    if (callBcak != null) {
                        if (backCallResult.getCode().equals("200")) {
                            callBcak.OnSuccess();
                        } else {
                            Toasts.toastMessage(msg);
                            callBcak.Onfall();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {

            }
        });
    }

    /**
     * 消息通知
     *
     * @param message
     * @param callBcak
     */
    public void sendmessage(messageSendDTO message, CallBcak callBcak) {

        String json = gson.toJson(message);
        Logi.d(TAG, "sendmessage: " + json);

        RequestBody requestBody = RequestBody.create(JSON, json);
        PostModule.postModule(CahtAPI.sendmessage, requestBody, new CallBcak() {
            @Override
            public void success(String s) {
                Logi.d(TAG, "success: " + s);

                try {
                    Type objectType = new TypeToken<BackCallResult<Object>>
                            () {
                    }.getType();
                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();
                    if (callBcak != null) {
                        if (backCallResult.getCode().equals("200")) {
                            callBcak.OnSuccess();
                        } else {
                            Toasts.toastMessage(msg);
                            // callBcak.Onfall();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {

            }
        });
    }

    /**
     * 班级圈
     *
     * @param classId
     * @param nowPage
     * @param pageSize
     * @param callBcak
     */
    public void classcircle(int classId, int nowPage, int pageSize, CallBcak callBcak) {
        Map<String, String> map = new HashMap<>();
        map.put(Constants.classId, String.valueOf(classId));
        map.put(Constants.nowPage, String.valueOf(nowPage));
        map.put(Constants.pageSize, String.valueOf(pageSize));
        String param = CahtAPI.param(CahtAPI.classcircle, map);
        PostModule.getModule(param, new CallBcak() {
            @Override
            public void success(String s) {
                try {
                    Type objectType = new TypeToken<BackCallResult<classcircle<PageInfo<kcirclelist, Integer>>>>
                            () {
                    }.getType();

                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();
                    if (callBcak != null) {
                        if (backCallResult.getCode().equals("200")) {
                            callBcak.OnSuccess(backCallResult.getResult());
                        } else {
                            Toasts.toastMessage(msg);
                            callBcak.Onfall();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {

            }
        });

    }


    /**
     * 班级圈-我的帖子
     *
     * @param classId
     * @param nowPage
     * @param pageSize
     * @param callBcak
     */
    public void circlemine(int classId, int nowPage, int pageSize, CallBcak callBcak) {
        Map<String, String> map = new HashMap<>();
        map.put(Constants.nowPage, String.valueOf(nowPage));
        map.put(Constants.pageSize, String.valueOf(pageSize));
        // map.put(Constants.type, String.valueOf(type));
        String param = CahtAPI.param(CahtAPI.circlemine, map);
        PostModule.getModule(param, new CallBcak() {
            @Override
            public void success(String s) {
                Logi.d(TAG, "success: " + s);
                try {
                    Type objectType = new TypeToken<BackCallResult<PageInfo<kcirclelist, Integer>>>
                            () {
                    }.getType();

                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();
                    if (callBcak != null) {
                        if (backCallResult.getCode().equals("200")) {
                            callBcak.OnSuccess(backCallResult.getResult());
                        } else {
                            Toasts.toastMessage(msg);
                            callBcak.Onfall();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {

            }
        });

    }


    /**
     * 今日评语-学生列表
     * 类型 0：全部 1：已表扬 2:待改进 3：未评
     *
     * @param classId
     * @param nowPage
     * @param pageSize
     * @param callBcak
     */
    public void stucomment(int classId, int nowPage, int pageSize, int type, CallBcak callBcak) {
        Map<String, String> map = new HashMap<>();
        map.put(Constants.classId, String.valueOf(classId));
        map.put(Constants.nowPage, String.valueOf(nowPage));
        map.put(Constants.pageSize, String.valueOf(pageSize));
        map.put(Constants.type, String.valueOf(type));
        String param = CahtAPI.param(CahtAPI.stucomment, map);
        PostModule.getModule(param, new CallBcak() {
            @Override
            public void success(String s) {
                Logi.d(TAG, "success: " + s);
                try {
                    Type objectType = new TypeToken<BackCallResult<studentunReviewNumber<PageInfo<studentunrcom, Integer>>>>
                            () {
                    }.getType();

                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();
                    if (callBcak != null) {
                        if (backCallResult.getCode().equals("200")) {
                            callBcak.OnSuccess(backCallResult.getResult());
                        } else {
                            Toasts.toastMessage(msg);
                            callBcak.Onfall();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {

            }
        });

    }

    /**
     * 今日评语-评语详情
     */
    public void commentinfo(int Id, CallBcak callBcak) {
        PostModule.getModule(CahtAPI.commentinfo + Id, new CallBcak() {
            @Override
            public void success(String s) {
                try {
                    Type objectType = new TypeToken<BackCallResult<schema>>
                            () {
                    }.getType();

                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();
                    if (callBcak != null) {
                        if (backCallResult.getCode().equals("200")) {
                            callBcak.OnSuccess(backCallResult.getResult());
                        } else {
                            Toasts.toastMessage(msg);
                            callBcak.Onfall();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {

            }
        });
    }

    /**
     * 学期评语-学生列表
     * 0：全部 1：已评 2:未评
     *
     * @param classId
     * @param nowPage
     * @param pageSize
     * @param callBcak
     */
    public void semester(int classId, int nowPage, int pageSize, int type, CallBcak callBcak) {
        Map<String, String> map = new HashMap<>();
        map.put(Constants.classId, String.valueOf(classId));
        map.put(Constants.nowPage, String.valueOf(nowPage));
        map.put(Constants.pageSize, String.valueOf(pageSize));
        map.put(Constants.type, String.valueOf(type));
        String param = CahtAPI.param(CahtAPI.semester, map);

        Logi.d(TAG, "semester: " + param);
        Logi.d(TAG, "token: " + Msgconfig.getInstance().getAccessToken());
        PostModule.getModule(param, new CallBcak() {
            @Override
            public void success(String s) {
                Logi.d(TAG, "success: " + s);
                try {
                    Type objectType = new TypeToken<BackCallResult<studentunReviewNumber<PageInfo<studentunrcom, Integer>>>>
                            () {
                    }.getType();

                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();
                    if (callBcak != null) {
                        if (backCallResult.getCode().equals("200")) {
                            callBcak.OnSuccess(backCallResult.getResult());
                        } else {
                            Toasts.toastMessage(msg);
                            callBcak.Onfall();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {

            }
        });

    }

    /**
     * 作业信息-列表
     *
     * @param classId
     * @param nowPage
     * @param pageSize
     * @param callBcak 查询类型 0：全部 1：未批改 2：已批改
     */
    public void classtasklist(int classId, int nowPage, int pageSize, int type, CallBcak callBcak) {
        Map<String, String> map = new HashMap<>();
        map.put(Constants.classId, String.valueOf(classId));
        map.put(Constants.nowPage, String.valueOf(nowPage));
        map.put(Constants.pageSize, String.valueOf(pageSize));
        map.put(Constants.type, String.valueOf(type));
        String param = CahtAPI.param(CahtAPI.classtasklist, map);
        PostModule.getModule(param, new CallBcak() {
            @Override
            public void success(String s) {
                try {
                    Type objectType = new TypeToken<BackCallResult<health<classtask, Integer>>>
                            () {
                    }.getType();

                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();
                    if (callBcak != null) {
                        if (backCallResult.getCode().equals("200")) {
                            callBcak.OnSuccess(backCallResult.getResult());
                        } else {
                            Toasts.toastMessage(msg);
                            callBcak.Onfall();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {

            }
        });

    }

    /**
     * 作业信息-列表
     *
     * @param callBcak 查询类型 0：全部 1：未批改 2：已批改
     */
    public void classtaskinfo(int Id, CallBcak callBcak) {
        Map<String, String> map = new HashMap<>();
        map.put(Constants.id, String.valueOf(Id));
        String param = CahtAPI.param(CahtAPI.classtaskinfo, map);
        PostModule.getModule(param, new CallBcak() {
            @Override
            public void success(String s) {
                try {
                    Type objectType = new TypeToken<BackCallResult<taskinfo>>
                            () {
                    }.getType();

                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();
                    if (callBcak != null) {
                        if (backCallResult.getCode().equals("200")) {
                            callBcak.OnSuccess(backCallResult.getResult());
                        } else {
                            Toasts.toastMessage(msg);
                            callBcak.Onfall();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {

            }
        });

    }

    /**
     * 班级圈-消息
     *
     * @param callBcak 查询类型 0：全部 1：未批改 2：已批改
     */
    public void messagemine(int nowPage, int pageSize, CallBcak callBcak) {
        Map<String, String> map = new HashMap<>();
        map.put(Constants.nowPage, String.valueOf(nowPage));
        map.put(Constants.pageSize, String.valueOf(pageSize));
        String param = CahtAPI.param(CahtAPI.messagemine, map);
        PostModule.getModule(param, new CallBcak() {
            @Override
            public void success(String s) {
                try {
                    Type objectType = new TypeToken<BackCallResult<PageInfo<banjiqian, Integer>>>
                            () {
                    }.getType();

                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();
                    if (callBcak != null) {
                        if (backCallResult.getCode().equals("200")) {
                            callBcak.OnSuccess(backCallResult.getResult());
                        } else {
                            Toasts.toastMessage(msg);
                            callBcak.Onfall();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {

            }
        });

    }

    /**
     * 班级圈-帖子详情
     */
    public void classcircleinfo(int id, CallBcak callBcak) {
        Map<String, String> map = new HashMap<>();
        map.put(Constants.id, String.valueOf(id));
        String param = CahtAPI.param(CahtAPI.classcircleinfo, map);
        Logi.d(TAG, "classcircleinfo: " + param);

        PostModule.getModule(param, new CallBcak() {
            @Override
            public void success(String s) {
                Logi.d(TAG, "success: " + s);
                try {
                    Type objectType = new TypeToken<BackCallResult<itembjqper<followerInfo>>>
                            () {
                    }.getType();

                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();
                    if (callBcak != null) {
                        if (backCallResult.getCode().equals("200")) {
                            callBcak.OnSuccess(backCallResult.getResult());
                        } else {
                            Toasts.toastMessage(msg);
                            callBcak.Onfall();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {

            }
        });

    }

    /**
     * 班级圈-消息-已读
     */
    public void messageisview(int circleId, CallBcak callBcak) {
        RequestBody requestBody = new FormBody.Builder()
                .add("messageId", String.valueOf(circleId))
                .build();
        String chatapi = String.format("%s/%s", CahtAPI.messageisview, circleId);
        PostModule.putModule(chatapi, requestBody, new CallBcak() {
            @Override
            public void success(String s) {
                Logi.d(TAG, "success: " + s);
                try {
                    Type objectType = new TypeToken<BackCallResult<PageInfo<banjiqian, Integer>>>
                            () {
                    }.getType();

                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();
                    if (callBcak != null) {
                        if (backCallResult.getCode().equals("200")) {
                            callBcak.OnSuccess(backCallResult.getResult());
                        } else {
                            Toasts.toastMessage(msg);
                            callBcak.Onfall();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {

            }
        });

    }


    /**
     * 首页考勤初始化
     *
     * @param callBcak 查询类型 0：全部 1：未批改 2：已批改
     */
    public void init(int classid, CallBcak callBcak) {
        Map<String, String> map = new HashMap<>();
        map.put(Constants.classid, String.valueOf(classid));
        String param = CahtAPI.param(CahtAPI.init, map);
        Logi.d(TAG, "init: " + param);
        Logi.d(TAG, "init: " + Msgconfig.getInstance().getAccessToken());
        PostModule.getModule(param, new CallBcak() {
            @Override
            public void success(String s) {
                Logi.d(TAG, "首页考勤初始化: " + s);
                try {
                    Type objectType = new TypeToken<BackCallResult<absinit>>
                            () {
                    }.getType();

                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();
                    if (callBcak != null) {
                        if (backCallResult.getCode().equals("200")) {
                            callBcak.OnSuccess(backCallResult.getResult());
                        } else {
                            Logi.d(TAG, "success: " + msg);
                            Toasts.toastMessage(msg);
                            callBcak.Onfall();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {

            }
        });

    }


    /**
     * 请假信息-待审批-同意
     *
     * @param callBcak 查询类型 0：全部 1：未批改 2：已批改
     */
    public void agree(int id, CallBcak callBcak) {
        RequestBody requestBody = new FormBody.Builder()
                .add("id", String.valueOf(id))
                .build();
        PostModule.putModule(CahtAPI.agree, requestBody, new CallBcak() {
            @Override
            public void success(String s) {
                try {
                    Type objectType = new TypeToken<BackCallResult<Object>>
                            () {
                    }.getType();

                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();
                    if (callBcak != null) {
                        if (backCallResult.getCode().equals("200")) {
                            callBcak.OnSuccess();
                        } else {
                            Toasts.toastMessage(msg);
                            callBcak.Onfall();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {

            }
        });
    }


    /**
     * 请假信息-待审批-驳回
     *
     * @param callBcak 查询类型 0：全部 1：未批改 2：已批改
     */
    public void refuse(int id, String convers, CallBcak callBcak) {
        ClassLeaveRefuseDto classLeaveRefuseDto = new ClassLeaveRefuseDto();
        classLeaveRefuseDto.setId(id);
        classLeaveRefuseDto.setConversation(convers);

        RequestBody requestBody = RequestBody.create(JSON, gson.toJson(classLeaveRefuseDto));


        PostModule.putModule(CahtAPI.refuse, requestBody, new CallBcak() {
            @Override
            public void success(String s) {
                Logi.d(TAG, "响应: " + s);
                try {
                    Type objectType = new TypeToken<BackCallResult<Object>>
                            () {
                    }.getType();

                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();
                    if (callBcak != null) {
                        if (backCallResult.getCode().equals("200")) {
                            callBcak.OnSuccess();
                        } else {
                            Toasts.toastMessage(msg);
                            callBcak.Onfall();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {

            }
        });
    }


    /**
     * 班级圈-发帖
     */
    public void classcirclesubmit(int id, String Describes, String DetailsImg, CallBcak callBcak) {

        classCircleAddDTO classCircleAddDTO = new classCircleAddDTO();
        classCircleAddDTO.setClassId(id);
        classCircleAddDTO.setDescribes(Describes);
        classCircleAddDTO.setDetailsImg(DetailsImg);
        String json = gson.toJson(classCircleAddDTO);

        RequestBody requestBody = RequestBody.create(JSON, json);
        PostModule.postModule(CahtAPI.classcirclesubmit, requestBody, new CallBcak() {
            @Override
            public void success(String s) {
                try {
                    Type objectType = new TypeToken<BackCallResult<Object>>
                            () {
                    }.getType();

                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();
                    if (callBcak != null) {
                        if (backCallResult.getCode().equals("200")) {
                            callBcak.OnSuccess();
                        } else {
                            Toasts.toastMessage(msg);
                            callBcak.Onfall();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {

            }
        });
    }


    /**
     * 学期评语-发布评语
     */
    public void semestersend(List<Integer> list, String Describes, CallBcak callBcak) {
        sendDTO sendto = new sendDTO();
        sendto.setCharacterLevel(0);
        sendto.setClassId(Msgconfig.getInstance().getClassId());
        sendto.setDescribes(Describes);
        sendto.setExtracurricularLevel(0);
        sendto.setInterpersonalLevel(0);
        sendto.setReceiveIds(list);
        sendto.setSportsLevel(0);
        sendto.setStudyLevel(0);
        sendto.setSynthesisLevel(0);

        RequestBody requestBody = RequestBody.create(JSON, gson.toJson(sendto));

        PostModule.postModule(CahtAPI.semestersend, requestBody, new CallBcak() {
            @Override
            public void success(String s) {
                try {
                    Type objectType = new TypeToken<BackCallResult<Object>>
                            () {
                    }.getType();

                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();
                    if (callBcak != null) {
                        if (backCallResult.getCode().equals("200")) {
                            callBcak.OnSuccess();
                        } else {
                            Toasts.toastMessage(msg);
                            callBcak.Onfall();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {

            }
        });
    }


    /**
     * 今日评语-发布评语
     */
    public void commentsend(List<Integer> list, String title, String Describes, int Status, CallBcak callBcak) {
        sendDTO2 sendDTO2 = new sendDTO2();
        sendDTO2.setClassId(Msgconfig.getInstance().getClassId());
        sendDTO2.setDescribes(Describes);
        sendDTO2.setReceiveIds(list);
        sendDTO2.setTitle(title);
        sendDTO2.setStatus(Status); //今日评价 1:值得表扬 2:仍需努力
        MediaType mediaType = MediaType.parse("application/json;charset=utf-8");
        String json = gson.toJson(sendDTO2);
        RequestBody requestBody = RequestBody.create(json, mediaType);
        PostModule.postModule(CahtAPI.commentsend, requestBody, new CallBcak() {
            @Override
            public void success(String s) {
                try {
                    Type objectType = new TypeToken<BackCallResult<Object>>
                            () {
                    }.getType();

                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();
                    if (callBcak != null) {
                        if (backCallResult.getCode().equals("200")) {
                            callBcak.OnSuccess();
                        } else {
                            Toasts.toastMessage(msg);
                            callBcak.Onfall();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {

            }
        });
    }


    /**
     * 班级圈-删除
     */
    public void circledelete(int id, CallBcak callBcak) {


        //MediaType JSON = MediaType.parse("application/json;charset=utf-8");
        //RequestBody requestBody = RequestBody.create(JSON, json);
        RequestBody requestBody = new FormBody.Builder()
                .add("id", String.valueOf(id))
                .build();
        PostModule.putModule(CahtAPI.circledelete, requestBody, new CallBcak() {
            @Override
            public void success(String s) {
                Logi.d(TAG, "success: " + s);
                try {
                    Type objectType = new TypeToken<BackCallResult<Object>>
                            () {
                    }.getType();

                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();
                    if (callBcak != null) {
                        if (backCallResult.getCode().equals("200")) {
                            callBcak.OnSuccess();
                        } else {
                            Toasts.toastMessage(msg);
                            callBcak.Onfall();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {

            }
        });
    }


    /**
     * 教师-获取班级信息
     */
    public void classlist(CallBcak callBcak) {
        PostModule.getModule(CahtAPI.classlist, new CallBcak() {
            @Override
            public void success(String s) {
                Logi.d(TAG, "教师-获取班级信息: " + s);
                try {
                    Type objectType = new TypeToken<BackCallResult<List<classlistter>>>
                            () {
                    }.getType();

                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();
                    if (callBcak != null) {
                        if (backCallResult.getCode().equals("200")) {
                            callBcak.OnSuccess(backCallResult.getResult());
                        } else {
                            Toasts.toastMessage(msg);
                            callBcak.Onfall();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {

            }
        });
    }

    /**
     * 评语短语-列表
     */
    public void phraselist(int nowPage, int pageSize, CallBcak callBcak) {
        Map<String, String> map = new HashMap<>();
        map.put(Constants.nowPage, String.valueOf(nowPage));
        map.put(Constants.pageSize, String.valueOf(pageSize));
        String param = CahtAPI.param(CahtAPI.phraselist, map);
        Logi.d(TAG, "phraselist: " + param);

        PostModule.getModule(param, new CallBcak() {
            @Override
            public void success(String s) {
                Logi.d(TAG, "success: " + s);
                try {
                    Type objectType = new TypeToken<BackCallResult<health<conters, Integer>>>
                            () {
                    }.getType();

                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();
                    if (callBcak != null) {
                        if (backCallResult.getCode().equals("200")) {
                            if (backCallResult.getResult() != null) {
                                callBcak.OnSuccess(backCallResult.getResult());
                            } else {
                                Toasts.toastMessage("请求eorr-->200成功获取数失败");
                            }

                        } else {
                            Toasts.toastMessage(msg);
                            callBcak.Onfall();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {

            }
        });

    }

    /**
     * 评语短语-添加
     */
    public void commentphrase(int id, String msg, CallBcak callBcak) {
        conters conters = new conters();
        conters.setId(id);
        conters.setContent(msg);
        String json = gson.toJson(conters);


        RequestBody requestBody = RequestBody.create(JSON, json);
        PostModule.postModule(CahtAPI.commentphrase, requestBody, new CallBcak() {
            @Override
            public void success(String s) {
                Logi.d(TAG, "success: 响应" + s);
                try {
                    Type objectType = new TypeToken<BackCallResult<Object>>
                            () {
                    }.getType();

                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();
                    if (callBcak != null) {
                        if (backCallResult.getCode().equals("200")) {
                            callBcak.OnSuccess();
                        } else {
                            Toasts.toastMessage(msg);
                            callBcak.Onfall();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {

            }
        });

    }

    /**
     * 评语短语-编辑
     */
    public void commentphrasedit(int id, String msg, CallBcak callBcak) {
        conters conters = new conters();
        conters.setId(id);
        conters.setContent(msg);
        String json = gson.toJson(conters);

        RequestBody requestBody = RequestBody.create(JSON, json);
        PostModule.putModule(CahtAPI.commentphrasedit, requestBody, new CallBcak() {
            @Override
            public void success(String s) {
                try {
                    Type objectType = new TypeToken<BackCallResult<Object>>
                            () {
                    }.getType();

                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();
                    if (callBcak != null) {
                        if (backCallResult.getCode().equals("200")) {
                            callBcak.OnSuccess();
                        } else {
                            Toasts.toastMessage(msg);
                            callBcak.Onfall();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {

            }
        });

    }


    /**
     * 评语短语-删除
     */
    public void commentphrase(String ids, CallBcak callBcak) {
        RequestBody requestBody = new FormBody.Builder()
                .add(Constants.ids, ids)
                .build();
        PostModule.deleteModule(CahtAPI.commentphrasedel, requestBody, new CallBcak() {
            @Override
            public void success(String s) {
                try {
                    Type objectType = new TypeToken<BackCallResult<Object>>
                            () {
                    }.getType();

                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();
                    if (callBcak != null) {
                        if (backCallResult.getCode().equals("200")) {
                            callBcak.OnSuccess();
                        } else {
                            Toasts.toastMessage(msg);
                            callBcak.Onfall();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {

            }
        });

    }


    /**
     * 课程信息-按星期获取
     */
    public void courseinfoweek(int classId, int week, CallBcak callBcak) {
        Map<String, String> map = new HashMap<>();
        map.put(Constants.classId, String.valueOf(classId));
        map.put(Constants.week, String.valueOf(week));
        String param = CahtAPI.param(CahtAPI.courseinfoweek, map);
        PostModule.getModule(param, new CallBcak() {
            @Override
            public void success(String s) {
                Logi.d(TAG, "success: '" + s);
                try {
                    Type objectType = new TypeToken<BackCallResult<infoweek<amCourse, amCourse>>>
                            () {
                    }.getType();

                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();
                    if (callBcak != null) {
                        if (backCallResult.getCode().equals("200")) {
                            callBcak.OnSuccess(backCallResult.getResult());
                        } else {
                            Toasts.toastMessage(msg);
                            callBcak.Onfall();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {

            }
        });
    }


    /**
     * 课程信息-总
     */
    public void courseinfo(int classId, CallBcak callBcak) {
        Map<String, String> map = new HashMap<>();
        map.put(Constants.classId, String.valueOf(classId));
        String param = CahtAPI.param(CahtAPI.courseinfo, map);
        PostModule.getModule(param, new CallBcak() {
            @Override
            public void success(String s) {
                Logi.d(TAG, "success: " + s);
                try {
                    Type objectType = new TypeToken<BackCallResult<List<courseinfo<infoweek<amCourse, amCourse>>>>>
                            () {
                    }.getType();

                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();
                    if (callBcak != null) {
                        if (backCallResult.getCode().equals("200")) {
                            callBcak.OnSuccess(backCallResult.getResult());
                        } else {
                            Toasts.toastMessage(msg);
                            callBcak.Onfall();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {

            }
        });
    }

    /**
     * 学期评语-发布评语
     */
    public void semestersend(sendDTO sendDTO, CallBcak callBcak) {

        Logi.d(TAG, "json:" + gson.toJson(sendDTO));
        Logi.d(TAG, "POST: " + CahtAPI.semestersend);


        RequestBody requestBody = RequestBody.create(JSON, gson.toJson(sendDTO));
        PostModule.postModule(CahtAPI.semestersend, requestBody, new CallBcak() {
            @Override
            public void success(String s) {
                Logi.d(TAG, "success: 响应" + s);
                try {
                    Type objectType = new TypeToken<BackCallResult<Object>>
                            () {
                    }.getType();

                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();
                    if (callBcak != null) {
                        if (backCallResult.getCode().equals("200")) {
                            callBcak.OnSuccess();
                        } else {
                            Toasts.toastMessage(msg);
                            callBcak.Onfall();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {

            }
        });

    }

    /**
     * 校车
     */
    public void schoolbus(String latitude, String longitude, int schoolId, CallBcak callBcak) {

        Map<String, String> map = new HashMap<>();
        map.put(Constants.latitude, latitude);
        map.put(Constants.longitude, longitude);
        map.put(Constants.schoolId, String.valueOf(schoolId));
        String param = CahtAPI.param(CahtAPI.schoolbus, map);
        PostModule.getModule(param, new CallBcak() {
            @Override
            public void success(String s) {
                try {
                    Type objectType = new TypeToken<BackCallResult<List<schoolbust>>>
                            () {
                    }.getType();

                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();
                    if (callBcak != null) {
                        if (backCallResult.getCode().equals("200")) {
                            callBcak.OnSuccess(backCallResult.getResult());
                        } else {
                            Toasts.toastMessage(msg);
                            callBcak.Onfall();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {

            }
        });

    }


    /**
     * 班级圈-点赞/取消点赞
     */
    public void attention(int CircleId, int type, CallBcak callBcak) {
        attentionDTO attentionDTO = new attentionDTO();
        attentionDTO.setCircleId(CircleId);
        attentionDTO.setType(type);

        RequestBody requestBody = RequestBody.create(JSON, gson.toJson(attentionDTO));
        PostModule.putModule(CahtAPI.attention, requestBody, new CallBcak() {
            @Override
            public void success(String s) {
                try {
                    Type objectType = new TypeToken<BackCallResult<Object>>
                            () {
                    }.getType();

                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();
                    if (callBcak != null) {
                        if (backCallResult.getCode().equals("200")) {
                            callBcak.OnSuccess();
                        } else {
                            Toasts.toastMessage(msg);
                            callBcak.Onfall();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {

            }
        });

    }

    /**
     * 班级圈-点赞/取消点赞
     */
    public void comments(int CircleId, String describes, CallBcak callBcak) {
        messageCommentsDTO attentionDTO = new messageCommentsDTO();
        attentionDTO.setCircleId(CircleId);
        attentionDTO.setDescribes(describes);

        RequestBody requestBody = RequestBody.create(JSON, gson.toJson(attentionDTO));
        PostModule.postModule(CahtAPI.comments, requestBody, new CallBcak() {
            @Override
            public void success(String s) {
                try {
                    Type objectType = new TypeToken<BackCallResult<Object>>
                            () {
                    }.getType();

                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();
                    if (callBcak != null) {
                        if (backCallResult.getCode().equals("200")) {
                            callBcak.OnSuccess();
                        } else {
                            Toasts.toastMessage(msg);
                            callBcak.Onfall();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {

            }
        });

    }

    /**
     * 作业信息-发布作业
     */
    public void classtasksend(classTaskAddDTO classTaskAddDTO, CallBcak callBcak) {

        String json = gson.toJson(classTaskAddDTO);
        Logi.d(TAG, "classtasksend: " + json);


        RequestBody requestBody = RequestBody.create(JSON, json);
        PostModule.postModule(CahtAPI.classtasksend, requestBody, new CallBcak() {
            @Override
            public void success(String s) {
                Logi.d(TAG, "success: " + s);
                try {
                    Type objectType = new TypeToken<BackCallResult<Object>>
                            () {
                    }.getType();

                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();
                    if (callBcak != null) {
                        if (backCallResult.getCode().equals("200")) {
                            callBcak.OnSuccess();
                        } else {
                            Toasts.toastMessage(msg);
                            callBcak.Onfall();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {

            }
        });

    }

    /**
     * 班级圈-删除跟帖
     */
    public void followerdelete(int CircleId, CallBcak callBcak) {
//
//        RequestBody requestBody = new FormBody.Builder()
//                .add("messageId", String.valueOf(circleId))
//                .build();


        RequestBody requestBody = RequestBody.create(JSON, "");
        PostModule.putModule(CahtAPI.followerdelete + CircleId, requestBody, new CallBcak() {
            @Override
            public void success(String s) {
                try {
                    Type objectType = new TypeToken<BackCallResult<Object>>
                            () {
                    }.getType();

                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();
                    if (callBcak != null) {
                        if (backCallResult.getCode().equals("200")) {
                            callBcak.OnSuccess();
                        } else {
                            Toasts.toastMessage(msg);
                            callBcak.Onfall();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {

            }
        });

    }


    /**
     * 学期评语-评语详情
     */
    public void semesterinfo(int id, CallBcak callBcak) {
        PostModule.getModule(CahtAPI.semesterinfo + id, new CallBcak() {
            @Override
            public void success(String s) {
                try {
                    Type objectType = new TypeToken<BackCallResult<semesterinfo>>
                            () {
                    }.getType();

                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();
                    if (callBcak != null) {
                        if (backCallResult.getCode().equals("200")) {
                            callBcak.OnSuccess(backCallResult.getResult());
                        } else {
                            Toasts.toastMessage(msg);
                            callBcak.Onfall();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {

            }
        });

    }

    /**
     * 作业信息-已提交作业-学生列表
     */
    public void submitstulist(int taskId, int nowPage, int pageSize, CallBcak callBcak) {

        Map<String, String> map = new HashMap<>();
        map.put(Constants.taskId, String.valueOf(taskId));
        map.put(Constants.nowPage, String.valueOf(nowPage));
        map.put(Constants.pageSize, String.valueOf(pageSize));
        String param = CahtAPI.param(CahtAPI.submitstulist, map);
        PostModule.getModule(param, new CallBcak() {
            @Override
            public void success(String s) {
                try {
                    Type objectType = new TypeToken<BackCallResult<submitstulist<submitstulist2>>>
                            () {
                    }.getType();

                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();
                    if (callBcak != null) {
                        if (backCallResult.getCode().equals("200")) {
                            callBcak.OnSuccess(backCallResult.getResult());
                        } else {
                            Toasts.toastMessage(msg);
                            callBcak.Onfall();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {

            }
        });

    }

    /**
     * 作业提交信息-详情
     */
    public void tasksubmitinfo(int id, CallBcak callBcak) {
        Map<String, String> map = new HashMap<>();
        map.put(Constants.id, String.valueOf(id));
        String param = CahtAPI.param(CahtAPI.tasksubmitinfo, map);
        PostModule.getModule(param, new CallBcak() {
            @Override
            public void success(String s) {
                Logi.d(TAG, "success: " + s);
                try {
                    Type objectType = new TypeToken<BackCallResult<classtaskinfo>>
                            () {
                    }.getType();

                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();
                    if (callBcak != null) {
                        if (backCallResult.getCode().equals("200")) {
                            callBcak.OnSuccess(backCallResult.getResult());
                        } else {
                            Toasts.toastMessage(msg);
                            callBcak.Onfall();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {

            }
        });

    }

    /**
     * 作业信息-批改作业-退回重做
     */
    public void classtaskreject(String json, CallBcak callBcak) {
        Logi.request(TAG, json);

        RequestBody requestBody = RequestBody.create(JSON, json);
        PostModule.putModule(CahtAPI.classtaskreject, requestBody, new CallBcak() {
            @Override
            public void success(String s) {
                Logi.response(TAG, s);
                try {
                    Type objectType = new TypeToken<BackCallResult<Object>>
                            () {
                    }.getType();

                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();
                    if (callBcak != null) {
                        if (backCallResult.getCode().equals("200")) {
                            callBcak.OnSuccess();
                        } else {
                            Toasts.toastMessage(msg);
                            callBcak.Onfall();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {

            }
        });
    }

    /**
     * 作业信息-批改作业-确认批改
     */
    public void classtaskexamines(String json, CallBcak callBcak) {

        RequestBody requestBody = RequestBody.create(JSON, json);
        PostModule.putModule(CahtAPI.classtaskexamines, requestBody, new CallBcak() {
            @Override
            public void success(String s) {
                Logi.d(TAG, "success: " + s);
                try {
                    Type objectType = new TypeToken<BackCallResult<Object>>
                            () {
                    }.getType();

                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();
                    if (callBcak != null) {
                        if (backCallResult.getCode().equals("200")) {
                            callBcak.OnSuccess();
                        } else {
                            Toasts.toastMessage(msg);
                            callBcak.Onfall();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {

            }
        });

    }

    /**
     * 作业信息-删除(撤销)
     */
    public void classtaskcancel(int id, CallBcak callBcak) {
        RequestBody requestBody = new FormBody.Builder()
                .add(Constants.id, String.valueOf(id))
                .build();
        PostModule.putModule(CahtAPI.classtaskcancel, requestBody, new CallBcak() {
            @Override
            public void success(String s) {
                Logi.d(TAG, "success: " + s);
                try {
                    Type objectType = new TypeToken<BackCallResult<Object>>
                            () {
                    }.getType();

                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();
                    if (callBcak != null) {
                        if (backCallResult.getCode().equals("200")) {
                            callBcak.OnSuccess();
                        } else {
                            Toasts.toastMessage(msg);
                            callBcak.Onfall();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {

            }
        });

    }

    /**
     * 作业信息-修改
     */
    public void classtaskedit(String json, CallBcak callBcak) {

        RequestBody requestBody = RequestBody.create(JSON, json);
        PostModule.putModule(CahtAPI.classtaskedit, requestBody, new CallBcak() {
            @Override
            public void success(String s) {
                Logi.d(TAG, "success: " + s);
                try {
                    Type objectType = new TypeToken<BackCallResult<Object>>
                            () {
                    }.getType();

                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();
                    if (callBcak != null) {
                        if (backCallResult.getCode().equals("200")) {
                            callBcak.OnSuccess();
                        } else {
                            Toasts.toastMessage(msg);
                            callBcak.Onfall();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {

            }
        });
    }


    /**
     * 普通单个文件上传
     * 1 图片 2 wave 3 mp4 4 docx
     */
    public void uploadImg(File file, int type, CallBcak callBcak) {
        //1.创建对应的MediaType
        MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
        //2.创建RequestBody
        RequestBody fileBody = RequestBody.create(MEDIA_TYPE_PNG, file);
        //RequestBody fileBody2 = MultipartBody.create(MediaType.parse("multipart/form-data"), file);

        //3.构建MultipartBody
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(), fileBody)
                .addFormDataPart("types", String.valueOf(type))
                .build();
        PostModule.postModule(CahtAPI.uploadImg, requestBody, new CallBcak() {
            @Override
            public void success(String s) {
                Logi.d(TAG, "success: " + s);
                try {
                    Type objectType = new TypeToken<BackCallResult<String>>
                            () {
                    }.getType();

                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();
                    if (callBcak != null) {
                        if (backCallResult.getCode().equals("200")) {
                            callBcak.OnSuccess(backCallResult.getResult());
                        } else {
                            Toasts.toastMessage(msg);
                            callBcak.Onfall();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {

            }
        });
    }

    /**
     * 多个文件上传
     * 1 图片 2 wave 3 mp4 4 docx
     */
    public void uploadArray(File file, int type, CallBcak callBcak) {
        //1.创建对应的MediaType
        MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
        //2.创建RequestBody
        RequestBody fileBody = RequestBody.create(MEDIA_TYPE_PNG, file);
        //RequestBody fileBody2 = MultipartBody.create(MediaType.parse("multipart/form-data"), file);

        //3.构建MultipartBody
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(), fileBody)
                .addFormDataPart("types", String.valueOf(type))
                .build();
        PostModule.postModule(CahtAPI.uploadArray, requestBody, new CallBcak() {
            @Override
            public void success(String s) {
                Logi.d(TAG, "success: " + s);
                try {
                    Type objectType = new TypeToken<BackCallResult<String>>
                            () {
                    }.getType();

                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();
                    if (callBcak != null) {
                        if (backCallResult.getCode().equals("200")) {
                            callBcak.OnSuccess(backCallResult.getResult());
                        } else {
                            Toasts.toastMessage(msg);
                            callBcak.Onfall();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {

            }
        });
    }

    /**
     * 用户提交反馈建议意见
     */
    public void subadvice(String json, CallBcak callBcak) {

        RequestBody requestBody = RequestBody.create(JSON, json);
        PostModule.postModule(CahtAPI.subadvice, requestBody, new CallBcak() {
            @Override
            public void success(String s) {
                Logi.d(TAG, "success: " + s);
                try {
                    Type objectType = new TypeToken<BackCallResult<Object>>
                            () {
                    }.getType();

                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();
                    if (callBcak != null) {
                        if (backCallResult.getCode().equals("200")) {
                            callBcak.OnSuccess();
                        } else {
                            Toasts.toastMessage(msg);
                            callBcak.Onfall();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {

            }
        });

    }

    /**
     * 后台分页获取用户反馈意见建议
     */
    public void queryadvice(int nowPage, int pageSize, CallBcak callBcak) {
        Map<String, String> map = new HashMap<>();
        map.put(Constants.nowPage, String.valueOf(nowPage));
        map.put(Constants.pageSize, String.valueOf(pageSize));
        String param = CahtAPI.param(CahtAPI.queryadvice, map);
        PostModule.getModule(param, new CallBcak() {
            @Override
            public void success(String s) {
                try {
                    Type objectType = new TypeToken<BackCallResult<userib<subadvice>>>
                            () {
                    }.getType();

                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    String msg = backCallResult.getMsg();
                    if (callBcak != null) {
                        if (backCallResult.getCode().equals("200")) {
                            callBcak.OnSuccess(backCallResult.getResult());
                        } else {
                            Toasts.toastMessage(msg);
                            callBcak.Onfall();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {

            }
        });

    }

    /**
     * APP-实时获取孩子最新位置
     */
    public void gpsappstu(String deviceId, CallBcak callBcak) {
        Map<String, String> map = new HashMap<>();
        map.put(Constants.deviceId, String.valueOf(deviceId));
        String param = CahtAPI.param(CahtAPI.gpsappstu, map);
        PostModule.getModule(param, new CallBcak() {
            @Override
            public void success(String s) {
                Logi.d(TAG, "success: ====" + s);
                if (TextUtils.isEmpty(s)) {
                    Toasts.toastMessage(context.getString(R.string.toast6) + "");
                    if (callBcak != null) {
                        callBcak.Onfall();
                    }
                    return;
                }
                try {
                    Type objectType = new TypeToken<device>
                            () {
                    }.getType();
                    device backCallResult = gson.fromJson(s, objectType);
                    if (callBcak != null) {
                        callBcak.OnSuccess(backCallResult);
                    } else {

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {
                if (callBcak != null) {
                    callBcak.Onfall();
                }
            }
        });

    }

    /**
     * APP-实时获取孩子最新位置
     */
    public void checkversionapp(CallBcak callBcak) {
        Map<String, String> map = new HashMap<>();
        map.put("product", String.valueOf(2));
        map.put("resource", String.valueOf(2));
        map.put("versionCode", String.valueOf(Config.getVersionCode()));
        map.put("versionName", Config.getVersionName(context));
        String param = CahtAPI.param(CahtAPI.checkversion, map);
        Logi.d(TAG, "checkversionapp: " + param);
        PostModule.getModule(param, new CallBcak() {
            @Override
            public void success(String s) {
                try {
                    Type objectType = new TypeToken<BackCallResult<checkversion>>
                            () {
                    }.getType();
                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    if (callBcak != null) {
                        callBcak.OnSuccess(backCallResult.getResult());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {
                if (callBcak != null) {
                    callBcak.Onfall();
                }
            }
        });
    }

    /**
     * APP-实时获取孩子最新位置
     */
    public void edituserinfo(CallBcak callBcak) {
        String toJson = gson.toJson(UserInfo.getInstance());

        RequestBody requestBody = RequestBody.create(JSON, toJson);
        PostModule.putModule(CahtAPI.edituserinfo, requestBody, new CallBcak() {
            @Override
            public void success(String s) {
                try {
                    Type objectType = new TypeToken<BackCallResult<Object>>
                            () {
                    }.getType();
                    BackCallResult backCallResult = gson.fromJson(s, objectType);
                    if (backCallResult.getCode().equals("200")) {
                        callBcak.OnSuccess();
                    } else {
                        callBcak.fall();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fall() {
                if (callBcak != null) {
                    callBcak.Onfall();
                }
            }
        });
    }

}


