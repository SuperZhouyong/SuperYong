package com.sxzx.base.bean;

import java.util.List;

/**
 * Created by Administrator
 * on 2016/11/21.
 */

public class MyBookStack {


    /**
     * status : success
     * list : [{"isPay":"no","book_name":"","book_pic":"","book_new_price":"","book_epub_money":"","book_epub_free":"","book_id":"","book_author":"","book_description":"","size1":"","book_read":""},{"isPay":"no","book_name":"我在政协这一年","book_pic":"/Uploads/bookfile/2016-11-18/582e69b21119a.png","book_new_price":"0.01","book_epub_money":"/Uploads/bookfile/2016-11-14/582977a9df634.epub","book_epub_free":"/Uploads/bookfile/2016-12-16/58534f90c910c.epub","book_id":"13","book_author":"","book_description":"《我在政协这一年 2014 一个民主党派成员见证的中国民主政治进展》分为春天的约会之两会手记、年度提案、参政之声、调研笔记、议政网事、媒体关注几部分，主要内容包括：紧锣密鼓备两会、媒体为镜见民心、提案背后的故事、协商民主谱新篇、打老虎与拍苍蝇等。","size1":"0","book_read":"2"}]
     */

    private String status;
    /**
     * isPay : no
     * book_name :
     * book_pic :
     * book_new_price :
     * book_epub_money :
     * book_epub_free :
     * book_id :
     * book_author :
     * book_description :
     * size1 :
     * book_read :
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
        private String isPay;
        private String book_name;
        private String book_pic;
        private String book_new_price;
        private String book_epub_money;
        private String book_epub_free;
        private String book_id;
        private String book_author;
        private String book_description;
        private String size1;
        private String book_read;

        public String getIsPay() {
            return isPay;
        }

        public void setIsPay(String isPay) {
            this.isPay = isPay;
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

        public String getBook_new_price() {
            return book_new_price;
        }

        public void setBook_new_price(String book_new_price) {
            this.book_new_price = book_new_price;
        }

        public String getBook_epub_money() {
            return book_epub_money;
        }

        public void setBook_epub_money(String book_epub_money) {
            this.book_epub_money = book_epub_money;
        }

        public String getBook_epub_free() {
            return book_epub_free;
        }

        public void setBook_epub_free(String book_epub_free) {
            this.book_epub_free = book_epub_free;
        }

        public String getBook_id() {
            return book_id;
        }

        public void setBook_id(String book_id) {
            this.book_id = book_id;
        }

        public String getBook_author() {
            return book_author;
        }

        public void setBook_author(String book_author) {
            this.book_author = book_author;
        }

        public String getBook_description() {
            return book_description;
        }

        public void setBook_description(String book_description) {
            this.book_description = book_description;
        }

        public String getSize1() {
            return size1;
        }

        public void setSize1(String size1) {
            this.size1 = size1;
        }

        public String getBook_read() {
            return book_read;
        }

        public void setBook_read(String book_read) {
            this.book_read = book_read;
        }
    }
}
