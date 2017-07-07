package com.adrian.automat.tools;

/**
 * Created by adrian on 17-6-14.
 */

public class Constants {

    public static final String TEST_ACOUNT = "ZR201700001";
    public static final String TEST_PWD = "123456";
    public static final String MAP_KEY = "854821c29d97efc5cebe44464b3d1d72";

    public static final int LOGIN_TAG = 0;
    public static final int GOODS_LIST_TAG = 1;
    public static final int GOODS_TYPE_LIST_TAG = 2;
    public static final int MACHINE_INFO_TAG = 3;
    public static final int MACHINE_REPORT_TAG = 4;
    public static final int MODIFY_PATHWAY_DATA_TAG = 5;

    public static final String SERVER_URL = "http://www.zrvend.cn";
    public static final String IMG_DOMAIN = "http://zhirui.oss-cn-shanghai.aliyuncs.com";

    public static final String LOGIN_URL = SERVER_URL + "/login";   //登录
    public static final String GOODS_LIST_URL = SERVER_URL + "/sys/goods/sale/list";    //商品列表
    public static final String GOODS_TYPE_LIST_URL = SERVER_URL + "/sys/goodstype/query";    //商品类型列表
    public static final String MACHINE_INFO_URL = SERVER_URL + "/sys/machine/get";    //售货机详细信息
    public static final String MACHINE_REPORT_URL = SERVER_URL + "/sys/machine/report";    //售货机上报信息
    public static final String MODIFY_PATHWAY_DATA_URL = SERVER_URL + "/sys/machinegrid/change";    //批量修改轨道当前商品数据及最大商品数量

    public static final String PARAM_ORDINAL = "ordinal";
    public static final String PARAM_GOODSID = "goods_id";
    public static final String PARAM_GOODSTYPE = "goods_type";

}
