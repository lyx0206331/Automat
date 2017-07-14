package com.adrian.automat.pojo;

/**
 * Created by qing on 2017/7/14 0014.
 */

public class OrderInfoBean {
    /**
     * createTime : 1500026285000
     * franchiseeId : 1
     * franchiseeName : 山东潍坊丰华药业连锁公司
     * goodsName : 复方草珊瑚含片
     * gridOrdinal : 1-1-1
     * machineId : 2e9bc3db-e2d5-4116-99d7-0c6f5f092bea
     * machineSerial : ZR201700001
     * orderId : 20170714175805357126151487865
     * orderStatus : 1000
     * statusName : 创建订单
     * totalPrice : 12
     * type : BUY
     */

    private long createTime;
    private int franchiseeId;
    private String franchiseeName;
    private String goodsName;
    private String gridOrdinal;
    private String machineId;
    private String machineSerial;
    private String orderId;
    private int orderStatus;
    private String statusName;
    private int totalPrice;
    private String type;

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getFranchiseeId() {
        return franchiseeId;
    }

    public void setFranchiseeId(int franchiseeId) {
        this.franchiseeId = franchiseeId;
    }

    public String getFranchiseeName() {
        return franchiseeName;
    }

    public void setFranchiseeName(String franchiseeName) {
        this.franchiseeName = franchiseeName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGridOrdinal() {
        return gridOrdinal;
    }

    public void setGridOrdinal(String gridOrdinal) {
        this.gridOrdinal = gridOrdinal;
    }

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public String getMachineSerial() {
        return machineSerial;
    }

    public void setMachineSerial(String machineSerial) {
        this.machineSerial = machineSerial;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
