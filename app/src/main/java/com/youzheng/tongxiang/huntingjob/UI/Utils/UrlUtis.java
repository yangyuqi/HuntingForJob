package com.youzheng.tongxiang.huntingjob.UI.Utils;

/**
 * Created by qiuweiyu on 2018/2/11.
 */

public class UrlUtis {
    public static String FILE_URL = "http://101.132.125.42:8080/YoupinApi/";
    public static String BASE_URL = "http://101.132.125.42:8080/YoupinApi/api/app/v1/";
    public static String LOGIN_URL =BASE_URL+"user/loginMove";//登录
    public static String REGISTER_URL = BASE_URL+"user/registerMove";//注册
    public static String SEND_CODE = BASE_URL+"user/sendMsg";//短信接口
    public static String BASE_RESUME = BASE_URL+"resume/insertResume";//资料新增
    public static String WORK_TIAOJIAN = BASE_URL+"jobResume/getComClassByCtype";//类别查询下拉列表
    public static String ALL_ADDRESS = BASE_URL+"area/selectAreaAll";//获取地区地址
    public static String TOKEN_LOGIN = BASE_URL+"user/tokenValidate";//access_token登录
    public static String USER_INFO_SEE = BASE_URL+"user/selectPersonInfomation";//个人资料查询
    public static String DELIEVERY_MSG_LIST = BASE_URL+"jobResume/getResumeDeliveryByStatus";//根据状态获取投递职位列表
    public static String LOGIN_OUT = BASE_URL+"user/loginOut";//退出功能
    public static String JOB_DETAILS=BASE_URL+"job/getJobById";//职位详情
    public static String JOB_COLLECT = BASE_URL+"jobResume/saveJobCollect";//收藏职位/简历
    public static String UNSCRIBE_JOB = BASE_URL+"jobResume/deleteJobCollectByUidAndJid";//收藏职位/简历
    public static String TOUJIANLI_JOB = BASE_URL+"jobResume/saveResumeDelivery";//投递简历
    public static String CO_DETAILS = BASE_URL+"company/selectCompanyById";//企业资料查询
    public static String MY_RESUME = BASE_URL + "resume/getResumeDetailById";//我的简历详情
    public static String HOME_INFO = BASE_URL+"job/getHotJobList";//热门职位、最新职位、相似职位、名企
    public static String UPDATE_BASE_INFO = BASE_URL+"resume/updateResumeDetail";//简历修改基本信息
    public static String ALL_TRADE = BASE_URL+"trade/selectTradeAll";//所有行业
    public static String WORK_EXPERENCE= BASE_URL+"resume/addResumeExperience";//我的简历工作经历新增
    public static String UPDATE_EXPERENCE = BASE_URL+"resume/insertResumeExperience";//工作经历新增或修改
    public static String ADD_EDUCITON = BASE_URL+"resume/addResumeEducation";//添加教育经历
    public static String ADD_PROJECT = BASE_URL +"resume/addResumeProject";//添加项目经历
    public static String UPDATE_PROJECT = BASE_URL+"resume/insertResumeProject";//修改简历项目经验
    public static String UPDATE_EDU = BASE_URL+"resume/insertResumeEducation";//
    public static String JINENG_URL = BASE_URL+"resume/addResumeSkillLanguage";//添加技能
    public static String ADD_SELF_PINGJIA = BASE_URL+"resume/updateResumeSelfDescription";//自我评价
    public static String HOME_INTRODUCE = BASE_URL+"job/getAppRecommendList";//首页相关推荐
    public static String SHOUCAN_URL = BASE_URL+"jobResume/getCollectListByUid";//收藏职位/简历
    public static String GET_CO_JOB=BASE_URL+"job/getAllJobListByUid";//查询该公司所有的职位
    public static String SEARCH_JOB = BASE_URL+"job/getSearchJobList";//职位搜索
    public static String GET_ALL_CATEGORY=BASE_URL+"trade/selectOccupationAll";//获取所有职能
    public static String SCHOOL_JOB = BASE_URL+"job/getJobListByJobsNature";//招聘会  根据工作性质查询职位列表
    public static String SCHOOL_SPEAK = BASE_URL+"career/getnewcareer";//最新的宣讲会
    public static String SCHOOL_APEAK_DETAIL = BASE_URL+"career/gettalkinfo";//具体宣讲会信息
    public static String SPEAK_GO = BASE_URL+"career/enroll";//报名
    public static String NEWS_DETAILS = BASE_URL+"job/getnewinfo";//资讯详情
    public static String JOB_NEWS = BASE_URL +"job/getnewjobnews";//
    public static String INVITED_JOB_LIST = BASE_URL+"jobResume/getResumeInvitationList";//邀请的职位列表
    public static String GET_MY_SPEAK = BASE_URL+"career/getmycareer";//获取我已经报名的宣讲会信息
    public static String QUERY_BANNER = BASE_URL+"banner/queryBanner";//查询轮播图
    public static String MESSAGE_LIST = BASE_URL+"career/selectNewsList";//消息
    public static String UPLOAD_FILE = FILE_URL+"uploadFile";//上传文件
    public static String EDIT_PERSIOM_INFO = BASE_URL+"user/alertPersonInfomation";//个人资料修改
    public static String EDIT_RESUME_INFO = BASE_URL+"resume/updateResumePhoto";//上传简历头像
    public static String FIND_PWD = BASE_URL+"user/forgotPasswordMove";//找回密码
    public static String EDIT_PWD = BASE_URL+"user/alertPassWordMove";//修改密码
    public static String GET_NEWS = BASE_URL+"news/getnews";//获取类别下有哪些新闻

    public static String SCREAT_LANCHER = BASE_URL+"resume/getResumeHideByAccessToken";
    public static String OPEN_SECRET = BASE_URL+"resume/updateResumeFilter";//用户开放/隐藏 简历隐私
}
