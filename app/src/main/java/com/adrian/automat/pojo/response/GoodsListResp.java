package com.adrian.automat.pojo.response;

import java.util.List;

/**
 * Created by adrian on 17-6-15.
 */

public class GoodsListResp {
    /**
     * code : 0
     * msg :
     * data : [{"ordinal":"1-1-2","gridId":2,"machineId":null,"nowNum":null,"maxNum":13,"goodsId":6,"goodsTypeId":1,"name":"复方草珊瑚含片","code":"301619","price":12,"summary":"疏风清热，消肿止痛，清利咽喉。用于外感风热所致的喉痹，症见咽喉肿痛、声哑失音；急性咽喉炎见上述证候者。疏风清热，消肿止痛，清利咽","factory":"江中药业股份有限公司","unit":"盒","standard":"1.0g*24片","img":"zj82fdCDpi.jpg","weight":1},{"ordinal":"1-1-3","gridId":3,"machineId":null,"nowNum":12,"maxNum":1,"goodsId":6,"goodsTypeId":1,"name":"复方草珊瑚含片","code":"301619","price":12,"summary":"疏风清热，消肿止痛，清利咽喉。用于外感风热所致的喉痹，症见咽喉肿痛、声哑失音；急性咽喉炎见上述证候者。疏风清热，消肿止痛，清利咽","factory":"江中药业股份有限公司","unit":"盒","standard":"1.0g*24片","img":"zj82fdCDpi.jpg","weight":1},{"ordinal":"1-2-1","gridId":4,"machineId":null,"nowNum":null,"maxNum":10,"goodsId":5,"goodsTypeId":2,"name":"杰士邦情趣润滑剂（火热快感）","code":"701893","price":45,"summary":"成人保健用品","factory":"武汉杰士邦卫生用品有限公司","unit":"盒","standard":"50g","img":"k8PBFajJT3.jpg","weight":1},{"ordinal":"1-1-1","gridId":1,"machineId":null,"nowNum":null,"maxNum":11,"goodsId":6,"goodsTypeId":1,"name":"复方草珊瑚含片","code":"301619","price":12,"summary":"疏风清热，消肿止痛，清利咽喉。用于外感风热所致的喉痹，症见咽喉肿痛、声哑失音；急性咽喉炎见上述证候者。疏风清热，消肿止痛，清利咽","factory":"江中药业股份有限公司","unit":"盒","standard":"1.0g*24片","img":"zj82fdCDpi.jpg","weight":1},{"ordinal":"1-2-2","gridId":5,"machineId":null,"nowNum":9,"maxNum":10,"goodsId":null,"goodsTypeId":null,"name":null,"code":null,"price":null,"summary":null,"factory":null,"unit":null,"standard":null,"img":null,"weight":null}]
     */

    private int code;
    private String msg;
    private List<GoodsBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<GoodsBean> getData() {
        return data;
    }

    public void setData(List<GoodsBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GoodsListResp{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
