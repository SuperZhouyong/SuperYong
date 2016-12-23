package com.sxzx.base.bean;

import java.util.List;

/**
 * Created by Administrator
 * on 2016/11/1.
 */

public class LibraryBook {
    /**
     * status : success
     * list : [{"book_id":"","book_name":"","book_pic":"","book_author":"","book_description":"","book_read":"","book_new_price":"","book_epub_free":"","book_epub_money":"","book_pdf_free":"","book_pdf_money":"","ranking_bang_id":"1"},{"book_id":"","book_name":"","book_pic":"","book_author":"","book_description":"","book_read":"","book_new_price":"","book_epub_free":"","book_epub_money":"","book_pdf_free":"","book_pdf_money":"","ranking_bang_id":"1"}]
     */

    private String status;
    /**
     * book_id :
     * book_name :
     * book_pic :
     * book_author :
     * book_description :
     * book_read :
     * book_new_price :
     * book_epub_free :
     * book_epub_money :
     * book_pdf_free :
     * book_pdf_money :
     * ranking_bang_id : 1
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
        private String book_author;
        private String book_description;
        private String book_read;
        private String book_new_price;
        private String book_epub_free;
        private String book_epub_money;
        private String book_pdf_free;
        private String book_pdf_money;
        private String ranking_bang_id;

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

        public String getBook_epub_money() {
            return book_epub_money;
        }

        public void setBook_epub_money(String book_epub_money) {
            this.book_epub_money = book_epub_money;
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

        public String getRanking_bang_id() {
            return ranking_bang_id;
        }

        public void setRanking_bang_id(String ranking_bang_id) {
            this.ranking_bang_id = ranking_bang_id;
        }
    }
}
