package com.sxzx.GreenDao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator
 * on 2016/12/8.
 */
@Entity
public class BuyBook {
    @Id(autoincrement = true)
    private Long id ;
    @NotNull
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
    private String pay;
    private String bookcase;
    private String book_path ;
    private String book_size ;
    public String getBook_path() {
        return this.book_path;
    }
    public void setBook_path(String book_path) {
        this.book_path = book_path;
    }
    public String getBookcase() {
        return this.bookcase;
    }
    public void setBookcase(String bookcase) {
        this.bookcase = bookcase;
    }
    public String getPay() {
        return this.pay;
    }
    public void setPay(String pay) {
        this.pay = pay;
    }
    public String getBook_epub_money() {
        return this.book_epub_money;
    }
    public void setBook_epub_money(String book_epub_money) {
        this.book_epub_money = book_epub_money;
    }
    public String getBook_pdf_money() {
        return this.book_pdf_money;
    }
    public void setBook_pdf_money(String book_pdf_money) {
        this.book_pdf_money = book_pdf_money;
    }
    public String getBook_pdf_free() {
        return this.book_pdf_free;
    }
    public void setBook_pdf_free(String book_pdf_free) {
        this.book_pdf_free = book_pdf_free;
    }
    public String getBook_epub_free() {
        return this.book_epub_free;
    }
    public void setBook_epub_free(String book_epub_free) {
        this.book_epub_free = book_epub_free;
    }
    public String getBook_new_price() {
        return this.book_new_price;
    }
    public void setBook_new_price(String book_new_price) {
        this.book_new_price = book_new_price;
    }
    public String getBook_read() {
        return this.book_read;
    }
    public void setBook_read(String book_read) {
        this.book_read = book_read;
    }
    public String getBook_description() {
        return this.book_description;
    }
    public void setBook_description(String book_description) {
        this.book_description = book_description;
    }
    public String getBook_author() {
        return this.book_author;
    }
    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }
    public String getBook_pic() {
        return this.book_pic;
    }
    public void setBook_pic(String book_pic) {
        this.book_pic = book_pic;
    }
    public String getBook_name() {
        return this.book_name;
    }
    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }
    public String getBook_id() {
        return this.book_id;
    }
    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1141733463)
    public BuyBook(Long id, @NotNull String book_id, String book_name,
            String book_pic, String book_author, String book_description,
            String book_read, String book_new_price, String book_epub_free,
            String book_pdf_free, String book_pdf_money, String book_epub_money,
            String pay, String bookcase, String book_path, String book_size) {
        this.id = id;
        this.book_id = book_id;
        this.book_name = book_name;
        this.book_pic = book_pic;
        this.book_author = book_author;
        this.book_description = book_description;
        this.book_read = book_read;
        this.book_new_price = book_new_price;
        this.book_epub_free = book_epub_free;
        this.book_pdf_free = book_pdf_free;
        this.book_pdf_money = book_pdf_money;
        this.book_epub_money = book_epub_money;
        this.pay = pay;
        this.bookcase = bookcase;
        this.book_path = book_path;
        this.book_size = book_size;
    }
    @Generated(hash = 2088151248)
    public BuyBook() {
    }

    @Override
    public String toString() {
        return "BuyBook{" +
                "id=" + id +
                ", book_id='" + book_id + '\'' +
                ", book_name='" + book_name + '\'' +
                ", book_pic='" + book_pic + '\'' +
                ", book_author='" + book_author + '\'' +
                ", book_description='" + book_description + '\'' +
                ", book_read='" + book_read + '\'' +
                ", book_new_price='" + book_new_price + '\'' +
                ", book_epub_free='" + book_epub_free + '\'' +
                ", book_pdf_free='" + book_pdf_free + '\'' +
                ", book_pdf_money='" + book_pdf_money + '\'' +
                ", book_epub_money='" + book_epub_money + '\'' +
                ", pay='" + pay + '\'' +
                ", bookcase='" + bookcase + '\'' +
                ", book_path='" + book_path + '\'' +
                '}';
    }
    public String getBook_size() {
        return this.book_size;
    }
    public void setBook_size(String book_size) {
        this.book_size = book_size;
    }
}
