package com.adrian.automat.pojo.response;

import com.adrian.automat.pojo.GoodsDetailBean;
import com.adrian.automat.pojo.response.BaseResp;

/**
 * Created by qing on 2017/7/14 0014.
 */

public class GoodsDetailResp extends BaseResp {
    /**
     * data : {"code":"301619","factory":"江中药业股份有限公司","franchiseeId":1,"goodsId":6,"goodsTypeId":1,"img":"zj82fdCDpi.jpg","name":"复方草珊瑚含片","price":12,"standard":"1.0g*24片","summary":"疏风清热，消肿止痛，清利咽喉。用于外感风热所致的喉痹，症见咽喉肿痛、声哑失音；急性咽喉炎见上述证候者。疏风清热，消肿止痛，清利咽","unit":"盒","weight":1}
     */

    private GoodsDetailBean data;

    public GoodsDetailBean getData() {
        return data;
    }

    public void setData(GoodsDetailBean data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GoodsListResp{" +
                "code=" + getCode() +
                ", msg='" + getMsg() + '\'' +
                ", data=" + data +
                '}';
    }
}
