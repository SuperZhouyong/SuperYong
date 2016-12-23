package com.sxzx.base.bean;

import java.util.List;

/**
 * Created by Administrator
 * on 2016/10/20.
 */

public class NewbookRcy {

    /**
     * status : success
     * list : [{"book_id":"47","book_name":"三国演义","book_pic":"/Uploads/bookfile/2016-10-14/5800c29840882.jpg","book_author":"11","book_description":"11","book_read":"0","book_new_price":"1","book_epub_free":"/Uploads/bookfile/2016-10-14/5800c29104dc4.epub","book_epub_money":"/Uploads/bookfile/2016-10-14/5800c28d9cab3.epub","book_pdf_free":"/Uploads/bookfile/2016-10-14/5800bf2e043a6.pdf","book_pdf_money":"/Uploads/bookfile/2016-10-14/5800bf2e02c36.pdf","readRecord":"1"},{"book_id":"9","book_name":"西游记","book_pic":"/Uploads/bookfile/2016-09-19/57df5599421b5.jpg","book_author":"unun","book_description":"水浒","book_read":"23","book_new_price":"106","book_epub_free":"/Uploads/bookfile/2016-10-14/5800c65675b51.epub","book_epub_money":"/Uploads/bookfile/2016-10-14/5800c652022a9.epub","book_pdf_free":"/Uploads/bookfile/2016-09-14/57d8eec9a9270.pdf","book_pdf_money":"/Uploads/bookfile/2016-10-14/5800be98d1e8b.epub","readRecord":"1"},{"book_id":"13","book_name":"水浒传","book_pic":"/Uploads/bookfile/2016-10-14/5800c2591857d.jpg","book_author":"你才","book_description":"12","book_read":"2","book_new_price":"122","book_epub_free":"/Uploads/bookfile/2016-10-14/5800c8516d296.epub","book_epub_money":"/Uploads/bookfile/2016-10-14/5800c84d673f0.epub","book_pdf_free":"/Uploads/bookfile/2016-09-14/57d8eee249ab4.pdf","book_pdf_money":"/Uploads/bookfile/2016-09-14/57d8f1d68b870.pdf","readRecord":"1"},{"book_id":"45","book_name":"红楼梦","book_pic":"/Uploads/bookfile/2016-10-14/5800c5f4ba694.jpg","book_author":"很纠结啊","book_description":"123","book_read":"0","book_new_price":"133","book_epub_free":"/Uploads/bookfile/2016-10-14/5800c2b1e9978.epub","book_epub_money":"/Uploads/bookfile/2016-10-14/5800c2acc0bf8.epub","book_pdf_free":"/Uploads/bookfile/2016-10-13/57ff6790c96a8.pdf","book_pdf_money":"/Uploads/bookfile/2016-10-13/57ff6790c8320.pdf","readRecord":"1"}]
     */

    private String status;
    /**
     * book_id : 47
     * book_name : 三国演义
     * book_pic : /Uploads/bookfile/2016-10-14/5800c29840882.jpg
     * book_author : 11
     * book_description : 11
     * book_read : 0
     * book_new_price : 1
     * book_epub_free : /Uploads/bookfile/2016-10-14/5800c29104dc4.epub
     * book_epub_money : /Uploads/bookfile/2016-10-14/5800c28d9cab3.epub
     * book_pdf_free : /Uploads/bookfile/2016-10-14/5800bf2e043a6.pdf
     * book_pdf_money : /Uploads/bookfile/2016-10-14/5800bf2e02c36.pdf
     * readRecord : 1
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
        private String readRecord;

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

        public String getReadRecord() {
            return readRecord;
        }

        public void setReadRecord(String readRecord) {
            this.readRecord = readRecord;
        }
    }
}
