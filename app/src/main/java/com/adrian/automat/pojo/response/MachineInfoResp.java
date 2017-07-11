package com.adrian.automat.pojo.response;

import com.adrian.automat.pojo.FranchiseeBean;
import com.adrian.automat.pojo.MachineBean;

import java.util.List;

/**
 * Created by ranqing on 2017/7/7.
 */

public class MachineInfoResp extends BaseResp {
    /**
     * code : 0
     * data : {"address":"风华一路123号","area":"370702","franchiseeId":1,"franchiseeName":"山东潍坊丰华药业连锁公司","grids":[{"batch":"","gridId":1,"machineId":"2e9bc3db-e2d5-4116-99d7-0c6f5f092bea","maxNum":11,"minNum":6,"nowGoodsId":6,"nowGoodsPrice":12,"ordinal":"1-1-1","validity":1560960000000},{"batch":"","gridId":2,"machineId":"2e9bc3db-e2d5-4116-99d7-0c6f5f092bea","maxNum":13,"minNum":8,"nowGoodsId":6,"nowGoodsPrice":12,"ordinal":"1-1-2","validity":1505145600000},{"batch":"","gridId":3,"machineId":"2e9bc3db-e2d5-4116-99d7-0c6f5f092bea","maxNum":1,"minNum":2,"nowGoodsId":6,"nowGoodsPrice":12,"nowNum":12,"ordinal":"1-1-3","validity":1560960000000},{"batch":"5556655","gridId":4,"machineId":"2e9bc3db-e2d5-4116-99d7-0c6f5f092bea","maxNum":10,"minNum":2,"nowGoodsId":5,"nowGoodsPrice":45,"ordinal":"1-2-1","validity":1499529600000},{"gridId":5,"machineId":"2e9bc3db-e2d5-4116-99d7-0c6f5f092bea","maxNum":10,"nowNum":9,"ordinal":"1-2-2"}],"hardwareInfo":"{\"latitude\":22.123,\"longitude\":31.12}","lnglat":"31.12 22.123","machineId":"2e9bc3db-e2d5-4116-99d7-0c6f5f092bea","machineTemplateId":3,"name":"京通大药房","serial":"ZR201700001","status":"正常","tel":"188088800","templateName":"图书机"}
     * msg :
     */

    private FranchiseeBean data;

    public FranchiseeBean getData() {
        return data;
    }

    public void setData(FranchiseeBean data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MachineInfoResp{" +
                "code=" + getCode() +
                ", data=" + data +
                ", msg='" + getMsg() + '\'' +
                '}';
    }
}
