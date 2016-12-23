package com.sxzx.base.ConfigKey;

/**
 * Created by Administrator
 * on 2016/10/19.
 */

public class AppConfig {
    /**
     * The constant DEBUG_TAG.
     */
    public static final String DEBUG_TAG = "logger";// LogCat的标记

    /* 自动更新配置*/
    //fire.im的token
    public static String API_FIRE_TOKEN = "a4f8aa03dc120fc81fcc96464fd03a4b";
    //fire.im的应用id
    public static String APP_FIRE_ID = "57e8ccd4ca87a851e4001199";


    //appid 微信分配的公众账号ID
    public static String APP_ID = "";

    // 个人信息的sp
    public static String spName = "UserInfo";
    public static String spUserName = "userName";
    public static String spUserIcon = "userIcon";
    public static String spUserId = "userId";
    public static String spUserType = "userType";
    public static String spUserSex = "userSex";
    public static String spUserBri = "userBri";
    // 当前书籍的sp
    public static String spCurrentBook = "CurrentBook";
    public static String spBookId = "bookid";
    public static String spBookName = "bookname";
    public static String spFbBookId = "fbbookid" ;
}
