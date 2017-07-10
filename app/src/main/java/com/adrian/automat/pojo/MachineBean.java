package com.adrian.automat.pojo;

/**
 * Created by qing on 2017/7/10 0010.
 */

public class MachineBean {
    /**
     * batch :
     * gridId : 1
     * machineId : 2e9bc3db-e2d5-4116-99d7-0c6f5f092bea
     * maxNum : 11
     * minNum : 6
     * nowGoodsId : 6
     * nowGoodsPrice : 12
     * ordinal : 1-1-1
     * validity : 1560960000000
     * nowNum : 12
     */

    private String batch;
    private int gridId;
    private String machineId;
    private int maxNum;
    private int minNum;
    private int nowGoodsId;
    private int nowGoodsPrice;
    private String ordinal;
    private long validity;
    private int nowNum;

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public int getGridId() {
        return gridId;
    }

    public void setGridId(int gridId) {
        this.gridId = gridId;
    }

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public int getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

    public int getMinNum() {
        return minNum;
    }

    public void setMinNum(int minNum) {
        this.minNum = minNum;
    }

    public int getNowGoodsId() {
        return nowGoodsId;
    }

    public void setNowGoodsId(int nowGoodsId) {
        this.nowGoodsId = nowGoodsId;
    }

    public int getNowGoodsPrice() {
        return nowGoodsPrice;
    }

    public void setNowGoodsPrice(int nowGoodsPrice) {
        this.nowGoodsPrice = nowGoodsPrice;
    }

    public String getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(String ordinal) {
        this.ordinal = ordinal;
    }

    public long getValidity() {
        return validity;
    }

    public void setValidity(long validity) {
        this.validity = validity;
    }

    public int getNowNum() {
        return nowNum;
    }

    public void setNowNum(int nowNum) {
        this.nowNum = nowNum;
    }

    @Override
    public String toString() {
        return "MachineBean{" +
                "batch='" + batch + '\'' +
                ", gridId=" + gridId +
                ", machineId='" + machineId + '\'' +
                ", maxNum=" + maxNum +
                ", minNum=" + minNum +
                ", nowGoodsId=" + nowGoodsId +
                ", nowGoodsPrice=" + nowGoodsPrice +
                ", ordinal='" + ordinal + '\'' +
                ", validity=" + validity +
                ", nowNum=" + nowNum +
                '}';
    }
}
