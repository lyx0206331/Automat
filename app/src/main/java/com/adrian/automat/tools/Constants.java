package com.adrian.automat.tools;

/**
 * Created by adrian on 17-6-14.
 */

public class Constants {

    public static final String TEST_ACOUNT = "ZR201700001";
    public static final String TEST_PWD = "123456";
    public static final String MAP_KEY = "854821c29d97efc5cebe44464b3d1d72";

    //Ali sandbox
//    沙箱环境账号
//    买家账号    vuthgf6615@sandbox.com
//    登录密码    111111
//    支付密码    111111
//    用户名称     沙箱环境
//    证件类型    身份证(IDENTITY_CARD)
//    证件号码    265040198709063456
//
//    沙箱环境支付宝下载地址
//    https://sandbox.alipaydev.com/user/downloadApp.htm
    public static final String buyer_acount = "vuthgf6615@sandbox.com";
    public static final String login_pwd = "111111";
    public static final String pay_pwd = "111111";
    public static final String usr_name = "沙箱环境";
    public static final String id_type = "身份证(IDENTITY_CARD)";
    public static final String id_number = "265040198709063456";

    public static final int LOGIN_TAG = 0;
    public static final int GOODS_LIST_TAG = 1;
    public static final int GOODS_TYPE_LIST_TAG = 2;
    public static final int MACHINE_INFO_TAG = 3;
    public static final int MACHINE_REPORT_TAG = 4;
    public static final int MODIFY_PATHWAY_DATA_TAG = 5;
    public static final int GET_GOODS_INFO_TAG = 6;
    public static final int CREATE_ORDER_TAG = 7;
    public static final int GET_ORDER_INFO_TAG = 8;
    public static final int CHOOSE_PAY_TYPE_TAG = 9;
    public static final int REQUEST_TAKE_DELIVERY_TAG = 10;
    public static final int TAKE_DELIVERY_TAG = 11;
    public static final int GET_QRCODE_TAG = 12;
    public static final int CANCEL_ORDER_TAG = 13;

    public static final String SERVER_URL = "http://www.zrvend.cn";
    public static final String IMG_DOMAIN = "http://zhirui.oss-cn-shanghai.aliyuncs.com";

    public static final String LOGIN_URL = SERVER_URL + "/login";   //登录
    public static final String GOODS_LIST_URL = SERVER_URL + "/sys/goods/sale/list";    //商品列表
    public static final String GOODS_TYPE_LIST_URL = SERVER_URL + "/sys/goodstype/query";    //商品类型列表
    public static final String MACHINE_INFO_URL = SERVER_URL + "/sys/machine/get";    //售货机详细信息
    public static final String MACHINE_REPORT_URL = SERVER_URL + "/sys/machine/report";    //售货机上报信息
    public static final String MODIFY_PATHWAY_DATA_URL = SERVER_URL + "/sys/machinegrid/change";    //批量修改轨道当前商品数据及最大商品数量
    public static final String GET_GOODS_INFO_URL = SERVER_URL + "/sys/goods/get";  //获取商品详细信息
    public static final String CREATE_ORDER_URL = SERVER_URL + "/sys/orderinfo/create"; //创建订单
    public static final String GET_ORDER_INFO_URL = SERVER_URL + "/sys/orderinfo/get";  //查看订单
    public static final String CHOOSE_PAY_TYPE_URL = SERVER_URL + "/sys/orderinfo/pay"; //选择支付类型
    public static final String REQUEST_TAKE_DELIVERY_URL = SERVER_URL + "/sys/orderinfo/preTake";    //请求出货
    public static final String TAKE_DELIVERY_URL = SERVER_URL + "/sys/orderinfo/take";  //出货
    public static final String GET_QRCODE_URL = SERVER_URL + "/qrcode"; //生成二维码
    public static final String CANCEL_ORDER_URL = SERVER_URL + "/sys/orderinfo/cancel"; //取消订单

    public static final String PARAM_GRIDID = "gridId";
    public static final String PARAM_GOODSID = "goods_id";
    public static final String PARAM_GOODSTYPE = "goods_type";

}
