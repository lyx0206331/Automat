package com.adrian.automat.pojo;

/**
 * Created by adrian on 17-6-12.
 */

public class PathwayBean {
    private String pathwayNum;
    private String drugName;
    private int count;
    private int max;
    private int min;

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getPathwayNum() {
        return pathwayNum;
    }

    public void setPathwayNum(String pathwayNum) {
        this.pathwayNum = pathwayNum;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
