package com.sxzx.base.bean;

import java.util.List;

/**
 * Created by Administrator
 * on 2016/11/16.
 */

public class NoteBean {

    /**
     * status : success
     * list : [{"note_id":"5","user_id":"16","book_id":"0","note_count":"0","note_date":"2016-10-26 11:34:41 ","note_description":"用了整整一章的篇幅去详解维基百科。维基百科是完全互联网的内容组织与传播方式，它通过话题去凝聚兴趣人群，用规则去鼓励与管理内容，将技术与人的力量融合在一起。通常说到纸书的消亡，我们总是从技术更新的角度去理解。但其实更为重要的，互联网所打破的实际是以纸书为代表的知识凝聚与传播方式。也许当我们忘记书是\u201c一本本\u201d的时候，我们才会看到互联网时代图书的未来。  信息是人的镜子，它在技术更新与模式兴替中展现出变化万端的色彩。但我们回视人的心灵，却发现它在千百年来并没有太多的变化。\u201c科技的互联网\u201d不能描述信息的全部，信息只有作用于思维，才能显示出强大的力量。  最后回过头来谈谈这本书本身。阅读该书是一段美妙的历程，我时常会惊喜于作者裁剪材料、呈现细节的能力\u2014\u2014竟能把如此","note_remark":""},{"note_id":"6","user_id":"16","book_id":"0","note_count":"0","note_date":"2016-10-27 03:03:36 ","note_description":"十五回 三清观大圣留名 车迟国猴王显法    第四十六回 外道弄强欺正法 心猿显圣灭诸邪    第四十七回 圣僧夜阻通天水 金木垂慈救小童    第四十八回 魔弄寒风飘大雪 僧思拜佛履层冰    第四十九回 三藏有灾沉水宅 观音救难现鱼篮    第五十回 情乱性从因爱欲 神昏心动遇魔头    第五十一回 心猿空用千般计 水火无功难炼魔    第五十","note_remark":""},{"note_id":"8","user_id":"16","book_id":"0","note_count":"0","note_date":"123","note_description":"123","note_remark":""},{"note_id":"9","user_id":"16","book_id":"0","note_count":"0","note_date":"2016-12-08 06:32:49 ","note_description":"回 法身元运逢车力 心正妖邪度脊关    第四十五回 三清观大圣留名 车迟国猴王显法    第四十六回 外道弄强欺正法 心猿显圣灭诸邪    第四十七回 圣僧夜阻通天水 金木垂慈救小童    第四十八回 魔弄寒风飘大雪 僧思拜佛履层冰    第四十九回 三藏有灾沉水宅 观音救难现鱼篮    第五十回 情乱性从因爱欲 神昏心动遇魔头    第五十一回 心猿空用千般计 水火无功难炼魔    第五十二回 悟空大闹金山兜洞 如来暗示主人公    第五十三回 禅主吞餐怀鬼孕 黄婆运水解邪胎    第五十四回 法性西来逢女国 心猿定计脱烟花 ","note_remark":""},{"note_id":"10","user_id":"16","book_id":"0","note_count":"0","note_date":"2016-12-08 06:39:23 ","note_description":"此书献给有史以来最卓越的政治家之一周恩来。他的才干足以使他成为任何国家的领袖并为之作出非凡贡献。我也将此书献给备受崇敬的伟大的毛泽东主席和伟大的中国人民。我永远不会忘记你们的支援和","note_remark":""},{"note_id":"11","user_id":"16","book_id":"0","note_count":"0","note_date":"2016-12-08 07:53:10 ","note_description":"域的一些方面达到世界先进水平。人民受教育的程度有了很大的提高。人民群众的民族自尊心、自信心、自强精神和爱国主义热情不断增强。建设有中国特色社会主义的理想，成为推动亿万人民奋发图强的精神力","note_remark":""}]
     */

    private String status;
    /**
     * note_id : 5
     * user_id : 16
     * book_id : 0
     * note_count : 0
     * note_date : 2016-10-26 11:34:41
     * note_description : 用了整整一章的篇幅去详解维基百科。维基百科是完全互联网的内容组织与传播方式，它通过话题去凝聚兴趣人群，用规则去鼓励与管理内容，将技术与人的力量融合在一起。通常说到纸书的消亡，我们总是从技术更新的角度去理解。但其实更为重要的，互联网所打破的实际是以纸书为代表的知识凝聚与传播方式。也许当我们忘记书是“一本本”的时候，我们才会看到互联网时代图书的未来。  信息是人的镜子，它在技术更新与模式兴替中展现出变化万端的色彩。但我们回视人的心灵，却发现它在千百年来并没有太多的变化。“科技的互联网”不能描述信息的全部，信息只有作用于思维，才能显示出强大的力量。  最后回过头来谈谈这本书本身。阅读该书是一段美妙的历程，我时常会惊喜于作者裁剪材料、呈现细节的能力——竟能把如此
     * note_remark :
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
        private String note_id;
        private String user_id;
        private String book_id;
        private String note_count;
        private String note_date;
        private String note_description;
        private String note_remark;

        public String getNote_id() {
            return note_id;
        }

        public void setNote_id(String note_id) {
            this.note_id = note_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getBook_id() {
            return book_id;
        }

        public void setBook_id(String book_id) {
            this.book_id = book_id;
        }

        public String getNote_count() {
            return note_count;
        }

        public void setNote_count(String note_count) {
            this.note_count = note_count;
        }

        public String getNote_date() {
            return note_date;
        }

        public void setNote_date(String note_date) {
            this.note_date = note_date;
        }

        public String getNote_description() {
            return note_description;
        }

        public void setNote_description(String note_description) {
            this.note_description = note_description;
        }

        public String getNote_remark() {
            return note_remark;
        }

        public void setNote_remark(String note_remark) {
            this.note_remark = note_remark;
        }
    }
}
