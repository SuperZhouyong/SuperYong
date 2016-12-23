package com.sxzx.base.bean;

import java.util.List;

/**
 * Created by Administrator
 * on 2016/10/24.
 */

public class ReadBookHead {

    /**
     * status : success
     * list : [{"book_id":"13","book_name":"水浒传","book_pic":"/Uploads/bookfile/2016-10-14/5800c2591857d.jpg","committee_bgpic":"/Uploads/committee/58034ae949328.jpg","committee_first":"1","committee_ren_name":"段段","committee_id":"31"}]
     */

    private String status;
    /**
     * book_id : 13
     * book_name : 水浒传
     * book_pic : /Uploads/bookfile/2016-10-14/5800c2591857d.jpg
     * committee_bgpic : /Uploads/committee/58034ae949328.jpg
     * committee_first : 1
     * committee_ren_name : 段段
     * committee_id : 31
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
        private String book_id;
        private String book_name;
        private String book_pic;
        private String committee_bgpic;
        private String committee_first;
        private String committee_ren_name;
        private String committee_id;

        public String getBook_id() {
            return book_id;
        }

        public void setBook_id(String book_id) {
            this.book_id = book_id;
        }

        public String getBook_name() {
            return book_name;
        }

        public void setBook_name(String book_name) {
            this.book_name = book_name;
        }

        public String getBook_pic() {
            return book_pic;
        }

        public void setBook_pic(String book_pic) {
            this.book_pic = book_pic;
        }

        public String getCommittee_bgpic() {
            return committee_bgpic;
        }

        public void setCommittee_bgpic(String committee_bgpic) {
            this.committee_bgpic = committee_bgpic;
        }

        public String getCommittee_first() {
            return committee_first;
        }

        public void setCommittee_first(String committee_first) {
            this.committee_first = committee_first;
        }

        public String getCommittee_ren_name() {
            return committee_ren_name;
        }

        public void setCommittee_ren_name(String committee_ren_name) {
            this.committee_ren_name = committee_ren_name;
        }

        public String getCommittee_id() {
            return committee_id;
        }

        public void setCommittee_id(String committee_id) {
            this.committee_id = committee_id;
        }
    }
}
