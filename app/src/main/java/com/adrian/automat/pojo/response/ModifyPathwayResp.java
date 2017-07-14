package com.adrian.automat.pojo.response;

import java.util.List;

/**
 * Created by qing on 2017/7/11 0011.
 */

public class ModifyPathwayResp extends BaseResp {
    private List<Integer> data;

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }
}
