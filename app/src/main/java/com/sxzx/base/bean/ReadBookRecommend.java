package com.sxzx.base.bean;

import java.util.List;

/**
 * Created by Administrator
 * on 2016/10/24.
 */

public class ReadBookRecommend {


    /**
     * status : success
     * list : [{"committee_ren_id":"23","committee_ren_name":"刘荣华","committee_ren_head":"/Uploads/committee/582e77c924e8c.png","book_id":"45","book_name":"把握人民的意愿","book_pic":"/Uploads/bookfile/2016-11-18/582e696e664dc.png","committee_book_title":"把握人民的意愿","committee_book_details":"近些年来，社会各界对政协提案表示了广泛的关注，不少政协委员也希望把他们通过提案履行政协职能的成果结集留存下来。为此，2002年我们选编了《政协第九届全国委员会提案及复文选》，得到了政协委员的肯定和赞许。十届政协期间，我们将继续逐年选编出版《政协第十届全国委员会提案及复文选》。","committee_bgpic":"/Uploads/committee/582e788ae96a5.png","committee_first":"0","committee_id":"32"},{"committee_ren_id":"23","committee_ren_name":"刘荣华","committee_ren_head":"/Uploads/committee/582e77c924e8c.png","book_id":"50","book_name":"开天辟地的时刻","book_pic":"/Uploads/bookfile/2016-11-18/582e6ad5756e2.png","committee_book_title":"开天辟地的时刻","committee_book_details":"近些年来，社会各界对政协提案表示了广泛的关注，不少政协委员也希望把他们通过提案履行政协职能的成果结集留存下来。为此，2002年我们选编了《政协第九届全国委员会提案及复文选》，得到了政协委员的肯定和赞许。十届政协期间，我们将继续逐年选编出版《政协第十届全国委员会提案及复文选》。","committee_bgpic":"/Uploads/committee/582e78e2c59c3.png","committee_first":"0","committee_id":"37"},{"committee_ren_id":"23","committee_ren_name":"刘荣华","committee_ren_head":"/Uploads/committee/582e77c924e8c.png","book_id":"50","book_name":"开天辟地的时刻","book_pic":"/Uploads/bookfile/2016-11-18/582e6ad5756e2.png","committee_book_title":"开天辟地的时刻","committee_book_details":"近些年来，社会各界对政协提案表示了广泛的关注，不少政协委员也希望把他们通过提案履行政协职能的成果结集留存下来。为此，2002年我们选编了《政协第九届全国委员会提案及复文选》，得到了政协委员的肯定和赞许。十届政协期间，我们将继续逐年选编出版《政协第十届全国委员会提案及复文选》。","committee_bgpic":"/Uploads/committee/582e790d89880.png","committee_first":"0","committee_id":"38"}]
     */

    private String status;
    /**
     * committee_ren_id : 23
     * committee_ren_name : 刘荣华
     * committee_ren_head : /Uploads/committee/582e77c924e8c.png
     * book_id : 45
     * book_name : 把握人民的意愿
     * book_pic : /Uploads/bookfile/2016-11-18/582e696e664dc.png
     * committee_book_title : 把握人民的意愿
     * committee_book_details : 近些年来，社会各界对政协提案表示了广泛的关注，不少政协委员也希望把他们通过提案履行政协职能的成果结集留存下来。为此，2002年我们选编了《政协第九届全国委员会提案及复文选》，得到了政协委员的肯定和赞许。十届政协期间，我们将继续逐年选编出版《政协第十届全国委员会提案及复文选》。
     * committee_bgpic : /Uploads/committee/582e788ae96a5.png
     * committee_first : 0
     * committee_id : 32
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
        private String committee_book_title;
        private String committee_book_details;
        private String committee_bgpic;
        private String committee_first;
        private String committee_id;

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

        public String getCommittee_id() {
            return committee_id;
        }

        public void setCommittee_id(String committee_id) {
            this.committee_id = committee_id;
        }
    }
}
