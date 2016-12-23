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
public class ShuQianBook {
    @Id(autoincrement = true)
    private Long id ;
    @NotNull
    private String BookId ; //书本id

    private String bookName; //书名

    private String praNum;  //段落数

    private String content;  //标签内容

    private String charIndex;  //开始字符(做标签的时候这一页的开始的字符)

    private String elementIndex; //开始的单词下标

    public String getElementIndex() {
        return this.elementIndex;
    }

    public void setElementIndex(String elementIndex) {
        this.elementIndex = elementIndex;
    }

    public String getCharIndex() {
        return this.charIndex;
    }

    public void setCharIndex(String charIndex) {
        this.charIndex = charIndex;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPraNum() {
        return this.praNum;
    }

    public void setPraNum(String praNum) {
        this.praNum = praNum;
    }

    public String getBookName() {
        return this.bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookId() {
        return this.BookId;
    }

    public void setBookId(String BookId) {
        this.BookId = BookId;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ShuQianBook{" +
                "id=" + id +
                ", BookId='" + BookId + '\'' +
                ", bookName='" + bookName + '\'' +
                ", praNum='" + praNum + '\'' +
                ", content='" + content + '\'' +
                ", charIndex='" + charIndex + '\'' +
                ", elementIndex='" + elementIndex + '\'' +
                '}';
    }

    @Generated(hash = 1187917200)
    public ShuQianBook(Long id, @NotNull String BookId, String bookName,
            String praNum, String content, String charIndex, String elementIndex) {
        this.id = id;
        this.BookId = BookId;
        this.bookName = bookName;
        this.praNum = praNum;
        this.content = content;
        this.charIndex = charIndex;
        this.elementIndex = elementIndex;
    }

    @Generated(hash = 1033673737)
    public ShuQianBook() {
    }

}
