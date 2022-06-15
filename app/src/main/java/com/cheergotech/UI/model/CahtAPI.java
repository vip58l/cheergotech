package com.cheergotech.UI.model;

import com.cheergotech.BuildConfig;

import java.util.Iterator;
import java.util.Map;

/**
 * 作者QQ：804031885
 * Author yanhang
 * Data 2022/5/1414:26
 * Description 请求接口API
 */
public class CahtAPI {

    /**
     * 用户协议
     */
//    public final static String useing = "https://wcharpays.focusea.com/useing.html";
    public final static String useing = "https://wcharpays.focusea.com/teacher_useing.html";

    /**
     * 隐私协议
     */
//    public final static String toprivate = "https://wcharpays.focusea.com/private.html";
    public final static String toprivate = "https://wcharpays.focusea.com/teacher_private.html";

    /**
     * 加密方式 字符串
     */
    public final static String sHZ2020 = "HZ2020-EDUCTION-TJ";

    /**
     * 初始化请求网址
     */
    private final static String chatApi = BuildConfig.CHAT_API;

    /**
     * APP-安卓 IOS 版本更新检测
     */
    public final static String checkversion = BuildConfig.CHAT_API + "/ho/check-version-app";

    /**
     * APP-家长端-教师端-初始化首页
     */
    public final static String initHome = BuildConfig.CHAT_API + "/ho/initHome";

    /**
     * APP-根据经纬度获取天气
     */
    public final static String weather = BuildConfig.CHAT_API + "/ho/qu-weather";

    /**
     * APP用户登录
     */
    public final static String login = BuildConfig.CHAT_API + "/lo/app/login";

    /**
     * 用户退出登录
     */
    public final static String loginOut = BuildConfig.CHAT_API + "/lo/loginOut";

    /**
     * 后台分页获取用户反馈意见建议
     */
    public final static String queryadvice = BuildConfig.CHAT_API + "/lo/query-advice";

    /**
     * 用户提交反馈建议意见
     */
    public final static String subadvice = BuildConfig.CHAT_API + "/lo/sub-advice";

    /**
     * 换取微信登录code
     */
    public final static String wxcode = BuildConfig.CHAT_API + "/lo/wx/code";

    /**
     * 请假人数-列表
     */
    public final static String leavelist = BuildConfig.CHAT_API + "/attendance/leave-list";

    /**
     * 请假列表(新)
     */
    public final static String leave = BuildConfig.CHAT_API + "/attendance/leave";

    /**
     * 请假信息-详情
     */
    public final static String leaveinfo = BuildConfig.CHAT_API + "/attendance/leave-info";

    /**
     * 请假信息-已拒绝
     */
    public final static String leaverefused = BuildConfig.CHAT_API + "/attendance/leave-refused";

    /**
     * 到校人数-列表
     */
    public final static String normallist = BuildConfig.CHAT_API + "/attendance/normal-list";

    /**
     * 请假信息-待审批-拒绝
     */
    public final static String refuse = BuildConfig.CHAT_API + "/attendance/leave-approving/refuse";

    /**
     * 请假信息-待审批-同意
     */
    public final static String agree = BuildConfig.CHAT_API + "/attendance/leave-approving/agree";

    /**
     * 请假信息-待审批
     */
    public final static String agree2 = BuildConfig.CHAT_API + "/attendance/leave-approving";

    /**
     * 请假信息-已同意
     */
    public final static String leaveapproved = BuildConfig.CHAT_API + "/attendance/leave-approved";

    /**
     * 缺勤人数-列表-状态处理
     */
    public final static String handlestatus = BuildConfig.CHAT_API + "/attendance/handle-status";

    /**
     * 缺勤人数-列表
     */
    public final static String absencelist = BuildConfig.CHAT_API + "/attendance/absence-list";

    /**
     * 学生信息-学生健康
     */
    public final static String stuinfohealth = BuildConfig.CHAT_API + "/home-page/stu-info-health";

    /**
     * 学生信息-学生健康-通知家长
     */
    public final static String healthnotice = BuildConfig.CHAT_API + "/home-page/stu-info-health/notice";

    /**
     * 学生信息-学生状态
     */
    public final static String stuinfostatus = BuildConfig.CHAT_API + "/home-page/stu-info-status";

    /**
     * 消息通知
     */
    public final static String sendmessage = BuildConfig.CHAT_API + "/attendance/send-message/family";


    /**
     * 班级圈
     */
    public final static String classcircle = BuildConfig.CHAT_API + "/home-page/class-circle/list";

    /**
     * 今日已评论
     */
    public final static String stucomment = BuildConfig.CHAT_API + "/student-comment/stu-comment";

    /**
     * 学期评语-学生列表
     */
    public final static String semester = BuildConfig.CHAT_API + "/student-comment/semester";


    /**
     * 考勤初始化
     */
    public final static String init = BuildConfig.CHAT_API + "/attendance/init";

    /**
     * 班级圈-消息
     */
    public final static String messagemine = BuildConfig.CHAT_API + "/home-page/class-circle-message/mine";

    /**
     * 班级圈-消息-已读
     */
    public final static String messageisview = BuildConfig.CHAT_API + "/home-page/class-circle-message/is-view";

    /**
     * 班级圈-帖子详情
     */
    public final static String classcircleinfo = BuildConfig.CHAT_API + "/home-page/class-circle/info";

    /**
     * 班级圈-发帖
     */
    public final static String classcirclesubmit = BuildConfig.CHAT_API + "/home-page/class-circle/submit";

    /**
     * 班级圈-删除
     */
    public final static String circledelete = BuildConfig.CHAT_API + "/home-page/class-circle/delete";

    /**
     * 我的贴子
     */
    public final static String circlemine = BuildConfig.CHAT_API + "/home-page/class-circle/mine";

    /**
     * 教师-获取班级信息
     */
    public final static String classlist = BuildConfig.CHAT_API + "/home-page/class-list";

    /**
     * 学期评语-发布评语
     */
    public final static String semestersend = BuildConfig.CHAT_API + "/student-comment/semester/send";

    /**
     * 今日评语-发布评语
     */
    public final static String commentsend = BuildConfig.CHAT_API + "/student-comment/send";
    /**
     * 今日评语-评语详情
     */
    public final static String commentinfo = BuildConfig.CHAT_API + "/student-comment/info/";

    /**
     * 评语短语-列表
     */
    public final static String phraselist = BuildConfig.CHAT_API + "/student-comment/phrase-list";

    /**
     * 评语短语-添加
     */
    public final static String commentphrase = BuildConfig.CHAT_API + "/student-comment/phrase";

    /**
     * 评语短语-删除
     */
    public final static String commentphrasedel = BuildConfig.CHAT_API + "/student-comment/phrase";

    /**
     * 评语短语-编辑
     */
    public final static String commentphrasedit = BuildConfig.CHAT_API + "/student-comment/phrase";

    /**
     * 课程信息-按星期获取
     */
    public final static String courseinfoweek = BuildConfig.CHAT_API + "/home-page/course-info-week";

    /**
     * 课程信息-总
     */
    public final static String courseinfo = BuildConfig.CHAT_API + "/home-page/course-info";

    /**
     * 校车-获取全部校车列表
     */
    public final static String schoolbus = BuildConfig.CHAT_API + "/home-page/school-bus";
    /**
     * 班级圈-点赞/取消点赞
     */
    public final static String attention = BuildConfig.CHAT_API + "/home-page/class-circle/attention";

    /**
     * 班级圈-帖子评论
     */
    public final static String comments = BuildConfig.CHAT_API + "/home-page/class-circle/comments";

    /**
     * 班级圈-删除跟帖
     */
    public final static String followerdelete = BuildConfig.CHAT_API + "/home-page/class-circle-follower/delete/";

    /**
     * 学期评语-评语详情
     */
    public final static String semesterinfo = BuildConfig.CHAT_API + "/student-comment/semester-info/";

    /**
     * 作业信息-发布作业
     */
    public final static String classtasksend = BuildConfig.CHAT_API + "/home-page/class-task-send";


    /**
     * 作业信息-删除(撤销)
     */
    public final static String classtaskcancel = BuildConfig.CHAT_API + "/home-page/class-task-cancel";

    /**
     * 作业信息-修改
     */
    public final static String classtaskedit = BuildConfig.CHAT_API + "/home-page/class-task-edit";

    /**
     * 作业信息-批改作业-确认批改
     */
    public final static String classtaskexamines = BuildConfig.CHAT_API + "/home-page/class-task-examines";

    /**
     * 作业信息-详情
     */
    public final static String classtaskinfo = BuildConfig.CHAT_API + "/home-page/class-task-info";

    /**
     * 作业信息-列表
     */
    public final static String classtasklist = BuildConfig.CHAT_API + "/home-page/class-task-list";

    /**
     * 作业信息-批改作业-退回重做
     */
    public final static String classtaskreject = BuildConfig.CHAT_API + "/home-page/class-task-reject";

    /**
     * 作业信息-已提交作业列表
     */
    public final static String classtasksubmit = BuildConfig.CHAT_API + "/home-page/class-task-submit";

    /**
     * 作业提交信息-详情
     */
    public final static String tasksubmitinfo = BuildConfig.CHAT_API + "/home-page/class-task-submit-info";

    /**
     * 作业信息-已提交作业-学生列表
     */
    public final static String submitstulist = BuildConfig.CHAT_API + "/home-page/class-task/submit-stu-list";

    /**
     * 普通单个文件上传
     */
    public final static String uploadImg = BuildConfig.CHAT_API + "/up/uploadImg";

    /**
     * 多个文件上传
     */
    public final static String uploadArray = BuildConfig.CHAT_API + "/up/uploadArray";

    /**
     * 获取学生最新位置数据
     */
    public final static String getNewGps = BuildConfig.CHAT_API + "/sec/getNewGps";

    /**
     * APP-实时获取孩子最新位置
     */
    public final static String gpsappstu = BuildConfig.CHAT_API + "/sec/gps-app-stu";

    /**
     * APP-实时获取孩子最新位置
     */
    public final static String matchKm = BuildConfig.CHAT_API + "/sec/qu-notice-list";

    /**
     * 修改用户信息
     */
    public final static String edituserinfo = BuildConfig.CHAT_API + "/userAccount/edit-user-info";

    /**
     * 学生信息-学生健康-生成报告
     */
    public final static String report= BuildConfig.CHAT_API + "/home-page/stu-info-health/report";

    /**
     * 字符串拼接
     *
     * @param map
     * @param TYPE
     * @return
     */
    public static String getMap(Map<String, String> map, int TYPE) {
        StringBuffer sb = new StringBuffer();
        int is = 0;
        switch (TYPE) {
            case 0:
            case 1:
                //第一种：
                for (String key : map.keySet()) {
                    if (is > 0) {
                        sb.append("&" + key + "=" + map.get(key));
                    } else {
                        sb.append(key + "=" + map.get(key));
                    }
                    is++;
                }
                break;
            case 2:
                //第二种
                Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, String> entry = it.next();
                    if (is > 0) {
                        sb.append("&" + entry.getKey() + "=" + entry.getValue());
                    } else {
                        sb.append(entry.getKey() + "=" + entry.getValue());
                    }
                    is++;
                }
                break;
            case 3:
                //第三种：
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    if (is > 0) {
                        sb.append("&" + entry.getKey() + "=" + entry.getValue());
                    } else {
                        sb.append(entry.getKey() + "=" + entry.getValue());
                    }
                    is++;
                }
                break;
        }
        return sb.toString();
    }


    public static String param(String url, Map<String, String> map) {
        return url + "?" + getMap(map, 3);
    }

}
