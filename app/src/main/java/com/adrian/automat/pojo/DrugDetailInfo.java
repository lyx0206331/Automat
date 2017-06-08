package com.adrian.automat.pojo;

/**
 * Created by ranqing on 2017/6/6.
 */

public class DrugDetailInfo {
    private int id;
    private String thumbUrl;
    private String drugName;
    private float price;
    private int store;
    private int weight;
    private float discount; //折扣
    private String pathWayNum;  //货道号
    private String drugIntro;   //药品简介

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStore() {
        return store;
    }

    public void setStore(int store) {
        this.store = store;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public String getPathWayNum() {
        return pathWayNum;
    }

    public void setPathWayNum(String pathWayNum) {
        this.pathWayNum = pathWayNum;
    }

    public String getDrugIntro() {
        return drugIntro;
    }

    public void setDrugIntro(String drugIntro) {
        this.drugIntro = drugIntro;
    }
}
