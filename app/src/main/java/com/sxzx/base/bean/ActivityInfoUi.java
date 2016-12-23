package com.sxzx.base.bean;

import java.util.List;

/**
 * Created by Administrator
 * on 2016/11/3.
 */

public class ActivityInfoUi {
    /**
     * status : success
     * list : [{"activity_id":"1","activity_title":"庆祝政协","activity_pic":"/Uploads/activity/5818427ca0eb7.jpg","activity_author":"柱子","activity_time":"2016-09-29","activity_time2":"2016-09-30","activity_time3":"10:00","activity_time4":"22:00","activity_address":"北京市海淀区"},{"activity_id":"10","activity_title":"结束","activity_pic":"/Uploads/activity/57faf7472ae90.jpg","activity_author":"你好","activity_time":"2016-10-10","activity_time2":"2016-10-12","activity_time3":"8:00","activity_time4":"22:00","activity_address":"北京市"},{"activity_id":"12","activity_title":"结束2","activity_pic":"/Uploads/activity/5818383f89fd9.jpg","activity_author":"你好","activity_time":"2016-10-10","activity_time2":"2016-10-12","activity_time3":"8:00","activity_time4":"22:00","activity_address":"北京市"}]
     */

    private String status;
    /**
     * activity_id : 1
     * activity_title : 庆祝政协
     * activity_pic : /Uploads/activity/5818427ca0eb7.jpg
     * activity_author : 柱子
     * activity_time : 2016-09-29
     * activity_time2 : 2016-09-30
     * activity_time3 : 10:00
     * activity_time4 : 22:00
     * activity_address : 北京市海淀区
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
        private String activity_id;
        private String activity_title;
        private String activity_pic;
        private String activity_author;
        private String activity_time;
        private String activity_time2;
        private String activity_time3;
        private String activity_time4;
        private String activity_address;

        public String getActivity_id() {
            return activity_id;
        }

        public void setActivity_id(String activity_id) {
            this.activity_id = activity_id;
        }

        public String getActivity_title() {
            return activity_title;
        }

        public void setActivity_title(String activity_title) {
            this.activity_title = activity_title;
        }

        public String getActivity_pic() {
            return activity_pic;
        }

        public void setActivity_pic(String activity_pic) {
            this.activity_pic = activity_pic;
        }

        public String getActivity_author() {
            return activity_author;
        }

        public void setActivity_author(String activity_author) {
            this.activity_author = activity_author;
        }

        public String getActivity_time() {
            return activity_time;
        }

        public void setActivity_time(String activity_time) {
            this.activity_time = activity_time;
        }

        public String getActivity_time2() {
            return activity_time2;
        }

        public void setActivity_time2(String activity_time2) {
            this.activity_time2 = activity_time2;
        }

        public String getActivity_time3() {
            return activity_time3;
        }

        public void setActivity_time3(String activity_time3) {
            this.activity_time3 = activity_time3;
        }

        public String getActivity_time4() {
            return activity_time4;
        }

        public void setActivity_time4(String activity_time4) {
            this.activity_time4 = activity_time4;
        }

        public String getActivity_address() {
            return activity_address;
        }

        public void setActivity_address(String activity_address) {
            this.activity_address = activity_address;
        }
    }
}
