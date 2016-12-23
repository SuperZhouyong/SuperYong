package com.sxzx.base.bean;

import java.util.List;

/**
 * Created by Administrator
 * on 2016/11/21.
 */

public class NoteBuybean {

    /**
     * status : success
     * list : [{"user_id":"16","order_id":"32","book_id":"13","book_pic":"/Uploads/bookfile/2016-11-16/582bea60ebeed.jpg","book_name":"姘存祾浼燖@@","book_author":"","book_add_bookcase":"0"},{"user_id":"16","order_id":"33","book_id":"13","book_pic":"/Uploads/bookfile/2016-11-16/582bea60ebeed.jpg","book_name":"姘存祾浼燖@@","book_author":"","book_add_bookcase":"0"},{"user_id":"16","order_id":"34","book_id":"13","book_pic":"/Uploads/bookfile/2016-11-16/582bea60ebeed.jpg","book_name":"姘存祾浼燖@@","book_author":"","book_add_bookcase":"1"},{"user_id":"16","order_id":"35","book_id":"13","book_pic":"/Uploads/bookfile/2016-11-16/582bea60ebeed.jpg","book_name":"姘存祾浼燖@@","book_author":"","book_add_bookcase":"1"},{"user_id":"16","order_id":"36","book_id":"13","book_pic":"/Uploads/bookfile/2016-11-16/582bea60ebeed.jpg","book_name":"姘存祾浼燖@@","book_author":"","book_add_bookcase":"1"}]
     */

    private String status;
    /**
     * user_id : 16
     * order_id : 32
     * book_id : 13
     * book_pic : /Uploads/bookfile/2016-11-16/582bea60ebeed.jpg
     * book_name : 姘存祾浼燖@@
     * book_author :
     * book_add_bookcase : 0
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
        private String user_id;
        private String order_id;
        private String book_id;
        private String book_pic;
        private String book_name;
        private String book_author;
        private String book_add_bookcase;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getBook_id() {
            return book_id;
        }

        public void setBook_id(String book_id) {
            this.book_id = book_id;
        }

        public String getBook_pic() {
            return book_pic;
        }

        public void setBook_pic(String book_pic) {
            this.book_pic = book_pic;
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

        public String getBook_add_bookcase() {
            return book_add_bookcase;
        }

        public void setBook_add_bookcase(String book_add_bookcase) {
            this.book_add_bookcase = book_add_bookcase;
        }
    }
}
