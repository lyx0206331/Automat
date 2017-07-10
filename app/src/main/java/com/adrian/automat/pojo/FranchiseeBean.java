package com.adrian.automat.pojo;

import java.util.List;

/**
 * Created by qing on 2017/7/10 0010.
 */

public class FranchiseeBean {
    /**
     * address : 风华一路123号
     * area : 370702
     * franchiseeId : 1
     * franchiseeName : 山东潍坊丰华药业连锁公司
     * grids : [{"batch":"","gridId":1,"machineId":"2e9bc3db-e2d5-4116-99d7-0c6f5f092bea","maxNum":11,"minNum":6,"nowGoodsId":6,"nowGoodsPrice":12,"ordinal":"1-1-1","validity":1560960000000},{"batch":"","gridId":2,"machineId":"2e9bc3db-e2d5-4116-99d7-0c6f5f092bea","maxNum":13,"minNum":8,"nowGoodsId":6,"nowGoodsPrice":12,"ordinal":"1-1-2","validity":1505145600000},{"batch":"","gridId":3,"machineId":"2e9bc3db-e2d5-4116-99d7-0c6f5f092bea","maxNum":1,"minNum":2,"nowGoodsId":6,"nowGoodsPrice":12,"nowNum":12,"ordinal":"1-1-3","validity":1560960000000},{"batch":"5556655","gridId":4,"machineId":"2e9bc3db-e2d5-4116-99d7-0c6f5f092bea","maxNum":10,"minNum":2,"nowGoodsId":5,"nowGoodsPrice":45,"ordinal":"1-2-1","validity":1499529600000},{"gridId":5,"machineId":"2e9bc3db-e2d5-4116-99d7-0c6f5f092bea","maxNum":10,"nowNum":9,"ordinal":"1-2-2"}]
     * hardwareInfo : {"latitude":22.123,"longitude":31.12}
     * lnglat : 31.12 22.123
     * machineId : 2e9bc3db-e2d5-4116-99d7-0c6f5f092bea
     * machineTemplateId : 3
     * name : 京通大药房
     * serial : ZR201700001
     * status : 正常
     * tel : 188088800
     * templateName : 图书机
     */

    private String address;
    private String area;
    private int franchiseeId;
    private String franchiseeName;
    private String hardwareInfo;
    private String lnglat;
    private String machineId;
    private int machineTemplateId;
    private String name;
    private String serial;
    private String status;
    private String tel;
    private String templateName;
    private List<MachineBean> grids;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public String getHardwareInfo() {
        return hardwareInfo;
    }

    public void setHardwareInfo(String hardwareInfo) {
        this.hardwareInfo = hardwareInfo;
    }

    public String getLnglat() {
        return lnglat;
    }

    public void setLnglat(String lnglat) {
        this.lnglat = lnglat;
    }

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public int getMachineTemplateId() {
        return machineTemplateId;
    }

    public void setMachineTemplateId(int machineTemplateId) {
        this.machineTemplateId = machineTemplateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public List<MachineBean> getGrids() {
        return grids;
    }

    public void setGrids(List<MachineBean> grids) {
        this.grids = grids;
    }

    @Override
    public String toString() {
        return "FranchiseeBean{" +
                "address='" + address + '\'' +
                ", area='" + area + '\'' +
                ", franchiseeId=" + franchiseeId +
                ", franchiseeName='" + franchiseeName + '\'' +
                ", hardwareInfo='" + hardwareInfo + '\'' +
                ", lnglat='" + lnglat + '\'' +
                ", machineId='" + machineId + '\'' +
                ", machineTemplateId=" + machineTemplateId +
                ", name='" + name + '\'' +
                ", serial='" + serial + '\'' +
                ", status='" + status + '\'' +
                ", tel='" + tel + '\'' +
                ", templateName='" + templateName + '\'' +
                ", grids=" + grids +
                '}';
    }
}
