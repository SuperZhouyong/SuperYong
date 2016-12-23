package com.sxzx.base.bean;

import java.util.List;

/**
 * Created by Administrator
 * on 2016/11/23.
 */

public class PayBeanalI {
    /**
     * status : success
     * list : [{"result":"app_id=2016081601753857&biz_content=%7B%22timeout_express%22%3A%2230m%22%2C%22seller_id%22%3A%22wenshicaiwu%40sina.com%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22total_amount%22%3A%220.01%22%2C%22subject%22%3A%221%22%2C%22body%22%3A%22%E6%88%91%E5%9C%A8%E6%94%BF%E5%8D%8F%E8%BF%99%E4%B8%80%E5%B9%B4%22%2C%22out_trade_no%22%3A%22TSX147987370816%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F192.168.31.47%2Falipay.php%2FNotifyurl&sign_type=RSA&timestamp=2016-11-23+12%3A01%3A48&version=1.0&sign=F9JNtg1CP7FIgqO%2B56gRY2sowiwG2fLCzO%2BogEWx3sDgffXEf7Rwn%2FVioiVIQL2GNdHqu15w%2F9VXi6eZmLZdmoobcJvq7Jt%2FcGeOHbuoXipAJ7DoQVeeOizAquivq9NU4TdXSdx09%2FxAUy9CwIez22YvmYcraMDMj1SyaQMLMDo%3D"}]
     */

    private String status;
    /**
     * result : app_id=2016081601753857&biz_content=%7B%22timeout_express%22%3A%2230m%22%2C%22seller_id%22%3A%22wenshicaiwu%40sina.com%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22total_amount%22%3A%220.01%22%2C%22subject%22%3A%221%22%2C%22body%22%3A%22%E6%88%91%E5%9C%A8%E6%94%BF%E5%8D%8F%E8%BF%99%E4%B8%80%E5%B9%B4%22%2C%22out_trade_no%22%3A%22TSX147987370816%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F192.168.31.47%2Falipay.php%2FNotifyurl&sign_type=RSA&timestamp=2016-11-23+12%3A01%3A48&version=1.0&sign=F9JNtg1CP7FIgqO%2B56gRY2sowiwG2fLCzO%2BogEWx3sDgffXEf7Rwn%2FVioiVIQL2GNdHqu15w%2F9VXi6eZmLZdmoobcJvq7Jt%2FcGeOHbuoXipAJ7DoQVeeOizAquivq9NU4TdXSdx09%2FxAUy9CwIez22YvmYcraMDMj1SyaQMLMDo%3D
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
        private String result;

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }
    }
}
