package com.sxzx.base.bean;

import java.util.List;

/**
 * Created by Administrator
 * on 2016/12/2.
 */

public class LogSuccessPhoto {

    /**
     * status : success
     * list : [{"nickname":"","avatar":"/data/data/com.example.lxj/cache/ImagePicker/cropTemp/IMG_20161202_183916.png","type":"third"}]
     */

    private String status;
    /**
     * nickname :
     * avatar : /data/data/com.example.lxj/cache/ImagePicker/cropTemp/IMG_20161202_183916.png
     * type : third
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
        private String nickname;
        private String avatar;
        private String type;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
