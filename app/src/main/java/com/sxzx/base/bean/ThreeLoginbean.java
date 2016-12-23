package com.sxzx.base.bean;

import java.util.List;

/**
 * Created by Administrator
 * on 2016/11/16.
 */

public class ThreeLoginbean {
    /**
     * status : success
     * list : [{"result":"ok","avatar":"http://q.qlogo.cn/qqapp/1105486631/5048EAE17259E83DA7D32AE0A520BC05/100","registerTime":"1479177411","nickname":"Phantom","qqBinding":"QQ","user_id":"698","token":"8720C163D669BB4711319A6F0F70F536","sex":"1"}]
     */

    private String status;
    /**
     * result : ok
     * avatar : http://q.qlogo.cn/qqapp/1105486631/5048EAE17259E83DA7D32AE0A520BC05/100
     * registerTime : 1479177411
     * nickname : Phantom
     * qqBinding : QQ
     * user_id : 698
     * token : 8720C163D669BB4711319A6F0F70F536
     * sex : 1
     */

    private List<ListBean> list;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private String result;
        private String avatar;
        private String registerTime;
        private String nickname;
        private String qqBinding;
        private String user_id;
        private String token;
        private String sex;

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getRegisterTime() {
            return registerTime;
        }

        public void setRegisterTime(String registerTime) {
            this.registerTime = registerTime;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getQqBinding() {
            return qqBinding;
        }

        public void setQqBinding(String qqBinding) {
            this.qqBinding = qqBinding;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }
}
