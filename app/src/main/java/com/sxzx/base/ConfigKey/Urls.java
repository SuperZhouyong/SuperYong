package com.sxzx.base.ConfigKey;

/**
 * Created by Administrator
 * on 2016/10/20.
 */

public class Urls {
    public static String URL_CONSTANT = "http://123.56.179.162/";
    // 新书
    public static String URL_NEWBOOK_recommed_recy = "/index.php/newBookRecommed/readRecord/";
    public static String URL_NEW_NEWBOOK_recommed_head = "/index.php/newBookRecommed/headerSetting";
    // 新书详情
    public static String URL_NNEWBOOK_DESCRIPTION = "/index.php/newBookRecommed/description/";

    // 委员读书
    public static  String URL_ReadBook_Recommend = "/index.php/committeeReadBook/Recommend/" ;

    // 委员读书头部
    public  static  String getURL_ReadBook_Head = "/index.php/committeeReadBook/HeaderView";
    //委员读书详情
    public static  String URL_ReadBook_Info = "/index.php/committeeReadBook/information/" ;
    //政协书库
    public static String URL_lirbray_fragmnet = "/index.php/politicalBookcase/bookcase/";


    //活动界面
    public static String URL_activity = "/index.php/newBookRecommed/activity" ;
    // 活动message
    public static String URL_Activity_message ="/index.php/html/active/id/" ;
    // 添加笔记
    public static String URL_AddNote = "/index.php/myStackroom/AddmyNotes" ;

    //我的书架
//    public static String URL_bookrack = "/index.php/myStackroom/myBookrack";
    public static String URL_bookrack = "/index.php/newBookRecommed/show_book";
    // 添加到 我的书库
    public static String URL_AddBookrack = "index.php/newBookRecommed/add_bookcas_new" ;
    //我的收藏
    public static String URL_Collect = "/index.php/myStackroom/myCollect" ;
    //添加到我的收藏
    public static  String URL_AddCollect = "/index.php/myStackroom/AddmyCollect" ;
    //购买记录
    public static String URL_buyRcored = "/index.php/myStackroom/buyRcored" ;

    //笔记详情
    public static String URL_myNotedetile = "/index.php/myStackroom/myNotesnotesDescription" ;

    //我的笔记
    public static String URL_myNote = "/index.php/myStackroom/myNotes" ;

    //我的书库
    public static String URL_myStackroom = "/index.php/myStackroom/myStackroom" ;

    //用户注册
    public static  String URL_Regist_user = "/index.php/login/register" ;
    //用户登陆
    public static  String URL_Login ="/index.php/login/login"  ;
    //第三方登录
    public static String URL_ThreeLogin = "/index.php/login/third" ;
    //ali_pay 接口
    public static String URL_aliPay = "/alipay.php/Zfbcreateorderww/createOrder" ;
    //告诉自己服务器 支付成功
    public static String URL_aLiServer = "/alipay.php/Zfbcreateorderww/result_sign" ;
    public static String URL_IconUpdate = "/index.php/myStackroom/Updatephoto" ;
    // 搜索
    public  static  String URL_Search = "/index.php/newBookRecommed/search" ;

    // 删除收藏
    public static String URL_DeleCollect = "/index.php/myStackroom/delcollect" ;
    // 删除书架
    public static String URL_DeleteStack = "index.php/newBookRecommed/del";
    // 修改面膜
    public static String URL_Resetpassword = "index.php/login/forgetps" ;
    // Photo Name
    public static String URl_Photo_Name = "index.php/login/getheadphoto" ;
}
