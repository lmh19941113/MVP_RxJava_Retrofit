package com.example.mvpdemo.model.bean;

/**
 * Created by admin on 2016/6/22.
 */
public final class PhoneBean {
    /**
     * "error_code": 0,
     * "reason": "Succes",
     * "result": {
     * "mobilenumber": "1302167",
     * "mobilearea": "山东 青岛市",
     * "mobiletype": "联通如意通卡",
     * "areacode": "0532",
     * "postcode": "266000"
     * }
     */
    public int error_code;
    public String reason;
    public ResultBean result;

    public static class ResultBean {
        public String mobilenumber;
        public String mobilearea;
        public String mobiletype;
        public String areacode;
        public String postcode;



    }
}
