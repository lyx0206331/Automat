package com.adrian.automat.pojo;

/**
 * Created by qing on 2017/7/14 0014.
 */

public class GoodsDetailBean {
    /**
     * code : 301619
     * factory : 江中药业股份有限公司
     * franchiseeId : 1
     * goodsId : 6
     * goodsTypeId : 1
     * img : zj82fdCDpi.jpg
     * name : 复方草珊瑚含片
     * price : 12
     * standard : 1.0g*24片
     * summary : 疏风清热，消肿止痛，清利咽喉。用于外感风热所致的喉痹，症见咽喉肿痛、声哑失音；急性咽喉炎见上述证候者。疏风清热，消肿止痛，清利咽
     * unit : 盒
     * weight : 1
     */

    private String code;
    private String factory;
    private int franchiseeId;
    private int goodsId;
    private int goodsTypeId;
    private String img;
    private String name;
    private int price;
    private String standard;
    private String summary;
    private String unit;
    private int weight;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public int getFranchiseeId() {
        return franchiseeId;
    }

    public void setFranchiseeId(int franchiseeId) {
        this.franchiseeId = franchiseeId;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "GoodsDetailBean{" +
                "code='" + code + '\'' +
                ", factory='" + factory + '\'' +
                ", franchiseeId=" + franchiseeId +
                ", goodsId=" + goodsId +
                ", goodsTypeId=" + goodsTypeId +
                ", img='" + img + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", standard='" + standard + '\'' +
                ", summary='" + summary + '\'' +
                ", unit='" + unit + '\'' +
                ", weight=" + weight +
                '}';
    }
}
