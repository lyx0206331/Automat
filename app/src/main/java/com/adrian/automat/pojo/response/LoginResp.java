package com.adrian.automat.pojo.response;

/**
 * Created by adrian on 17-6-14.
 */

public class LoginResp {
    /**
     * code : 0
     * data : 5ab420f8-3e9d-4391-a2b4-d3713512f84b
     * msg :
     */

    private int code;
    private String data;    //token
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
