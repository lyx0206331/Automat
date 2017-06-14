package com.adrian.automat.pojo.request;

import org.json.JSONObject;

/**
 * Created by adrian on 17-6-14.
 */

public class LoginReq {

    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
