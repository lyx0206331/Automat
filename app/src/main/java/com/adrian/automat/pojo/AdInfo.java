package com.adrian.automat.pojo;

/**
 * Created by ranqing on 2017/6/6.
 */

public class AdInfo {
    private int id;
    private String adUrl;
    private String adIntro;
    private int type;

    public String getAdUrl() {
        return adUrl;
    }

    public void setAdUrl(String adUrl) {
        this.adUrl = adUrl;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdIntro() {
        return adIntro;
    }

    public void setAdIntro(String adIntro) {
        this.adIntro = adIntro;
    }
}
