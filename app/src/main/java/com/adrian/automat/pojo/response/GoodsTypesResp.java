package com.adrian.automat.pojo.response;

import com.adrian.automat.pojo.GoodsTypeListBean;

import java.util.List;

/**
 * Created by qing on 2017/6/23.
 */

public class GoodsTypesResp {
    /**
     * code : 0
     * msg :
     * data : {"pageNum":1,"pageSize":10,"size":10,"orderBy":null,"startRow":1,"endRow":10,"total":10,"pages":1,"list":[{"goodsTypeId":1,"franchiseeId":1,"name":"五官用药","img":"W22FktyY3p.jpg","summary":"五官用药"},{"goodsTypeId":2,"franchiseeId":1,"name":"呼吸系统用药","img":"i6WmTZK34x.png","summary":"呼吸系统用药"},{"goodsTypeId":3,"franchiseeId":1,"name":"外用药","img":"5TF2TQe4kR.png","summary":"外用药"},{"goodsTypeId":4,"franchiseeId":1,"name":"计生用品","img":"nASBNBKkM2.png","summary":"计生用品"},{"goodsTypeId":6,"franchiseeId":1,"name":"f1","img":"nASBNBKkM2.png","summary":"bvcvb"},{"goodsTypeId":7,"franchiseeId":1,"name":"x","img":"nASBNBKkM2.png","summary":"x"},{"goodsTypeId":9,"franchiseeId":1,"name":"ccc","img":"nASBNBKkM2.png","summary":"fsd"},{"goodsTypeId":11,"franchiseeId":1,"name":"感冒药","img":"nASBNBKkM2.png","summary":"xcv"},{"goodsTypeId":12,"franchiseeId":1,"name":"121212","img":"nASBNBKkM2.png","summary":"sdf"},{"goodsTypeId":14,"franchiseeId":1,"name":"testimg","img":"8FtWTePJGp.jpg","summary":"添加图片"}],"firstPage":1,"prePage":0,"nextPage":0,"lastPage":1,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1]}
     */

    private int code;
    private String msg;
    private GoodsTypeListBean data;

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

    public GoodsTypeListBean getData() {
        return data;
    }

    public void setData(GoodsTypeListBean data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GoodsTypesResp{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
