package com.sxzx.base.bean;

/**
 * Created by Administrator
 * on 2016/11/2.
 */

public class newBookdetil {


    /**
     * status : success
     * list : {"book_id":"13","book_name":"我在政协这一年","book_pic":"/Uploads/bookfile/2016-11-18/582e69b21119a.png","book_author":"","book_description":"《我在政协这一年 2014 一个民主党派成员见证的中国民主政治进展》分为春天的约会之两会手记、年度提案、参政之声、调研笔记、议政网事、媒体关注几部分，主要内容包括：紧锣密鼓备两会、媒体为镜见民心、提案背后的故事、协商民主谱新篇、打老虎与拍苍蝇等。","book_read":"2","book_new_price":"0.01","book_epub_free":"/Uploads/bookfile/2016-11-14/582977c4a437a.epub","book_pdf_free":"/Uploads/bookfile/2016-11-02/58194b15aa5d2.epub","book_pdf_money":"/Uploads/bookfile/2016-11-02/58194b0e3856e.epub","book_epub_money":"/Uploads/bookfile/2016-11-14/582977a9df634.epub","size1":"2M","pay":"1","bookcase":"1"}
     */

    private String status;
    /**
     * book_id : 13
     * book_name : 我在政协这一年
     * book_pic : /Uploads/bookfile/2016-11-18/582e69b21119a.png
     * book_author :
     * book_description : 《我在政协这一年 2014 一个民主党派成员见证的中国民主政治进展》分为春天的约会之两会手记、年度提案、参政之声、调研笔记、议政网事、媒体关注几部分，主要内容包括：紧锣密鼓备两会、媒体为镜见民心、提案背后的故事、协商民主谱新篇、打老虎与拍苍蝇等。
     * book_read : 2
     * book_new_price : 0.01
     * book_epub_free : /Uploads/bookfile/2016-11-14/582977c4a437a.epub
     * book_pdf_free : /Uploads/bookfile/2016-11-02/58194b15aa5d2.epub
     * book_pdf_money : /Uploads/bookfile/2016-11-02/58194b0e3856e.epub
     * book_epub_money : /Uploads/bookfile/2016-11-14/582977a9df634.epub
     * size1 : 2M
     * pay : 1
     * bookcase : 1
     */

    private ListBean list;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ListBean getList() {
        return list;
    }

    public void setList(ListBean list) {
        this.list = list;
    }

    public static class ListBean {
        private String book_id;
        private String book_name;
        private String book_pic;
        private String book_author;
        private String book_description;
        private String book_read;
        private String book_new_price;
        private String book_epub_free;
        private String book_pdf_free;
        private String book_pdf_money;
        private String book_epub_money;
        private String size1;
        private String pay;
        private String bookcase;

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

        public String getBook_read() {
            return book_read;
        }

        public void setBook_read(String book_read) {
            this.book_read = book_read;
        }

        public String getBook_new_price() {
            return book_new_price;
        }

        public void setBook_new_price(String book_new_price) {
            this.book_new_price = book_new_price;
        }

        public String getBook_epub_free() {
            return book_epub_free;
        }

        public void setBook_epub_free(String book_epub_free) {
            this.book_epub_free = book_epub_free;
        }

        public String getBook_pdf_free() {
            return book_pdf_free;
        }

        public void setBook_pdf_free(String book_pdf_free) {
            this.book_pdf_free = book_pdf_free;
        }

        public String getBook_pdf_money() {
            return book_pdf_money;
        }

        public void setBook_pdf_money(String book_pdf_money) {
            this.book_pdf_money = book_pdf_money;
        }

        public String getBook_epub_money() {
            return book_epub_money;
        }

        public void setBook_epub_money(String book_epub_money) {
            this.book_epub_money = book_epub_money;
        }
        public String getSize1() {
            return size1;
        }

        public void setSize1(String size1) {
            this.size1 = size1;
        }

        public String getPay() {
            return pay;
        }

        public void setPay(String pay) {
            this.pay = pay;
        }

        public String getBookcase() {
            return bookcase;
        }

        public void setBookcase(String bookcase) {
            this.bookcase = bookcase;
        }
    }
}
