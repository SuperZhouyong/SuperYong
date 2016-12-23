package com.sxzx.base.bean;

import java.util.List;

/**
 * Created by Administrator
 * on 2016/11/25.
 */

public class SearchBean {
    /**
     * status : success
     * list : [{"book_id":"51","book_name":"璋 ","book_author":"璋噾鏃 "},{"book_id":"50","book_name":"鏃摜鍝 ","book_author":"213"}]
     */

    private String status;
    /**
     * book_id : 51
     * book_name : 璋
     * book_author : 璋噾鏃
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
        private String book_author;

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

        public String getBook_author() {
            return book_author;
        }

        public void setBook_author(String book_author) {
            this.book_author = book_author;
        }
    }
}
