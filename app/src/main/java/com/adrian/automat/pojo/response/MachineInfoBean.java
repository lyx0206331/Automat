package com.adrian.automat.pojo.response;

import java.util.List;

/**
 * Created by ranqing on 2017/7/7.
 */

public class MachineInfoBean {
    /**
     * code : 0
     * data : {"address":"风华一路123号","area":"370702","franchiseeId":1,"franchiseeName":"山东潍坊丰华药业连锁公司","grids":[{"batch":"","gridId":1,"machineId":"2e9bc3db-e2d5-4116-99d7-0c6f5f092bea","maxNum":11,"minNum":6,"nowGoodsId":6,"nowGoodsPrice":12,"ordinal":"1-1-1","validity":1560960000000},{"batch":"","gridId":2,"machineId":"2e9bc3db-e2d5-4116-99d7-0c6f5f092bea","maxNum":13,"minNum":8,"nowGoodsId":6,"nowGoodsPrice":12,"ordinal":"1-1-2","validity":1505145600000},{"batch":"","gridId":3,"machineId":"2e9bc3db-e2d5-4116-99d7-0c6f5f092bea","maxNum":1,"minNum":2,"nowGoodsId":6,"nowGoodsPrice":12,"nowNum":12,"ordinal":"1-1-3","validity":1560960000000},{"batch":"5556655","gridId":4,"machineId":"2e9bc3db-e2d5-4116-99d7-0c6f5f092bea","maxNum":10,"minNum":2,"nowGoodsId":5,"nowGoodsPrice":45,"ordinal":"1-2-1","validity":1499529600000},{"gridId":5,"machineId":"2e9bc3db-e2d5-4116-99d7-0c6f5f092bea","maxNum":10,"nowNum":9,"ordinal":"1-2-2"}],"hardwareInfo":"{\"latitude\":22.123,\"longitude\":31.12}","lnglat":"31.12 22.123","machineId":"2e9bc3db-e2d5-4116-99d7-0c6f5f092bea","machineTemplateId":3,"name":"京通大药房","serial":"ZR201700001","status":"正常","tel":"188088800","templateName":"图书机"}
     * msg :
     */

    private int code;
    private DataBean data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
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
        private List<GridsBean> grids;

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

        public List<GridsBean> getGrids() {
            return grids;
        }

        public void setGrids(List<GridsBean> grids) {
            this.grids = grids;
        }

        public static class GridsBean {
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
        }
    }
}
