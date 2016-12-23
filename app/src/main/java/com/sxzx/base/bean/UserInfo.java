package com.sxzx.base.bean;

/**
 * Created by Administrator
 * on 2016/10/28.
 */

/**
 * 用户实体类，用于登录，注册等一系列操作
 * Created by mdw on 2016/1/27.
 */
public class UserInfo  {

    // 头像
    private String userIcon;
    //性别
    private String sex;
    //生日
    private String birthday;
    //userID
    private String userId ;
    //昵称
    private String Nickname ;
    //
    private String Type ;
    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String nickname) {
        Nickname = nickname;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }



    @Override
    public String toString() {
        return "User{" +
                "userIcon='" + userIcon + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", userId=" + userId +
                '}';
    }
}