package com.adrian.automat.pojo;

/**
 * Created by ranqing on 2017/6/6.
 */

public class DrugSimpleInfo {
    private String id;
    private String thumbUrl;
    private String drugName;
    private int type;
    private float price;
    private int store;
    private int weight;

    public DrugSimpleInfo() {
    }

    public DrugSimpleInfo(String id, String thumbUrl, String drugName, int type, float price, int store, int weight) {
        this.id = id;
        this.thumbUrl = thumbUrl;
        this.drugName = drugName;
        this.type = type;
        this.price = price;
        this.store = store;
        this.weight = weight;
    }

    public String  getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
}
