package com.adrian.automat.pojo;

/**
 * Created by qing on 2017/6/23.
 */

public class GoodsTypeBean {
    /**
     * goodsTypeId : 1
     * franchiseeId : 1
     * name : 五官用药
     * img : W22FktyY3p.jpg
     * summary : 五官用药
     */

    private int goodsTypeId;
    private int franchiseeId;
    private String name;
    private String img;
    private String summary;

    public int getGoodsTypeId() {
        return goodsTypeId;
    }

    public void setGoodsTypeId(int goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
    }

    public int getFranchiseeId() {
        return franchiseeId;
    }

    public void setFranchiseeId(int franchiseeId) {
        this.franchiseeId = franchiseeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "GoodsTypeBean{" +
                "goodsTypeId=" + goodsTypeId +
                ", franchiseeId=" + franchiseeId +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", summary='" + summary + '\'' +
                '}';
    }
}
