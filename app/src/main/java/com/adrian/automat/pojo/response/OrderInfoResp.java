package com.adrian.automat.pojo.response;

import com.adrian.automat.pojo.OrderInfoBean;

/**
 * Created by qing on 2017/7/14 0014.
 */

public class OrderInfoResp extends BaseResp {
    /**
     * data : {"createTime":1500026285000,"franchiseeId":1,"franchiseeName":"山东潍坊丰华药业连锁公司","goodsName":"复方草珊瑚含片","gridOrdinal":"1-1-1","machineId":"2e9bc3db-e2d5-4116-99d7-0c6f5f092bea","machineSerial":"ZR201700001","orderId":"20170714175805357126151487865","orderStatus":1000,"statusName":"创建订单","totalPrice":12,"type":"BUY"}
     */

    private OrderInfoBean data;

    public OrderInfoBean getData() {
        return data;
    }

    public void setData(OrderInfoBean data) {
        this.data = data;
    }
}
