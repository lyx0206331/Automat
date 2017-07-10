package com.adrian.automat.pojo;

import android.support.annotation.NonNull;
import android.text.TextUtils;

/**
 * Created by adrian on 17-6-15.
 */

public class GoodsBean implements Comparable {
    /**
     * ordinal : 1-1-2
     * gridId : 2
     * machineId : null
     * nowNum : null
     * maxNum : 13
     * goodsId : 6
     * goodsTypeId : 1
     * name : 复方草珊瑚含片
     * code : 301619
     * price : 12
     * summary : 疏风清热，消肿止痛，清利咽喉。用于外感风热所致的喉痹，症见咽喉肿痛、声哑失音；急性咽喉炎见上述证候者。疏风清热，消肿止痛，清利咽
     * factory : 江中药业股份有限公司
     * unit : 盒
     * standard : 1.0g*24片
     * img : zj82fdCDpi.jpg
     * weight : 1
     */

    private String ordinal;
    private int gridId;
    private String machineId;
    private int nowNum;
    private int maxNum;
    private int goodsId;
    private int goodsTypeId;
    private String name;
    private String code;
    private int price;
    private String summary;
    private String factory;
    private String unit;
    private String standard;
    private String img;
    private int weight;

    public String getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(String ordinal) {
        this.ordinal = ordinal;
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

    public int getNowNum() {
        return nowNum;
    }

    public void setNowNum(int nowNum) {
        this.nowNum = nowNum;
    }

    public int getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getGoodsTypeId() {
        return goodsTypeId;
    }

    public void setGoodsTypeId(int goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
    }

    public String getName() {
        return TextUtils.isEmpty(name) ? "无名商品,找后台" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "GoodsBean{" +
                "ordinal='" + ordinal + '\'' +
                ", gridId=" + gridId +
                ", machineId='" + machineId + '\'' +
                ", nowNum=" + nowNum +
                ", maxNum=" + maxNum +
                ", goodsId=" + goodsId +
                ", goodsTypeId=" + goodsTypeId +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", price=" + price +
                ", summary='" + summary + '\'' +
                ", factory='" + factory + '\'' +
                ", unit='" + unit + '\'' +
                ", standard='" + standard + '\'' +
                ", img='" + img + '\'' +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(@NonNull Object o) {
        Integer w = ((GoodsBean) o).getWeight();
        return (w).compareTo((Integer) weight);
    }
}
