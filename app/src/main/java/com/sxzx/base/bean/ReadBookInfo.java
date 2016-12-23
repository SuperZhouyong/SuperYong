package com.sxzx.base.bean;

import java.util.List;

/**
 * Created by Administrator
 * on 2016/11/7.
 */

public class ReadBookInfo {
    /**
     * status : success
     * list : [{"committee_ren_id":"24","committee_ren_name":"娈垫","committee_ren_head":"/Uploads/committee/57e0ffbad2813.JPG","book_id":"","book_name":"","book_pic":"","book_sell":"","book_copyright":"","committee_id":"31","committee_book_title":"123","committee_book_details":"213123"}]
     */

    private String status;
    /**
     * committee_ren_id : 24
     * committee_ren_name : 娈垫
     * committee_ren_head : /Uploads/committee/57e0ffbad2813.JPG
     * book_id :
     * book_name :
     * book_pic :
     * book_sell :
     * book_copyright :
     * committee_id : 31
     * committee_book_title : 123
     * committee_book_details : 213123
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
        private String committee_ren_id;
        private String committee_ren_name;
        private String committee_ren_head;
        private String book_id;
        private String book_name;
        private String book_pic;
        private String book_sell;
        private String book_copyright;
        private String committee_id;
        private String committee_book_title;
        private String committee_book_details;

        public String getCommittee_ren_id() {
            return committee_ren_id;
        }

        public void setCommittee_ren_id(String committee_ren_id) {
            this.committee_ren_id = committee_ren_id;
        }

        public String getCommittee_ren_name() {
            return committee_ren_name;
        }

        public void setCommittee_ren_name(String committee_ren_name) {
            this.committee_ren_name = committee_ren_name;
        }

        public String getCommittee_ren_head() {
            return committee_ren_head;
        }

        public void setCommittee_ren_head(String committee_ren_head) {
            this.committee_ren_head = committee_ren_head;
        }

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

        public String getBook_sell() {
            return book_sell;
        }

        public void setBook_sell(String book_sell) {
            this.book_sell = book_sell;
        }

        public String getBook_copyright() {
            return book_copyright;
        }

        public void setBook_copyright(String book_copyright) {
            this.book_copyright = book_copyright;
        }

        public String getCommittee_id() {
            return committee_id;
        }

        public void setCommittee_id(String committee_id) {
            this.committee_id = committee_id;
        }

        public String getCommittee_book_title() {
            return committee_book_title;
        }

        public void setCommittee_book_title(String committee_book_title) {
            this.committee_book_title = committee_book_title;
        }

        public String getCommittee_book_details() {
            return committee_book_details;
        }

        public void setCommittee_book_details(String committee_book_details) {
            this.committee_book_details = committee_book_details;
        }
    }
}
