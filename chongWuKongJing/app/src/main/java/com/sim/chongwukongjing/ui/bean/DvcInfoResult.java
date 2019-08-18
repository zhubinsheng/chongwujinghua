package com.sim.chongwukongjing.ui.bean;

import com.google.gson.annotations.SerializedName;

public class DvcInfoResult {

    /**
     * code : 10000
     * msg : 设备信息获取成功！
     * data : {"0":1,"uid":13,"1":1,"rssi":-43,"2":1,"3":3,"4":3,"5":2,"6":0,"type":5678,"isonline":"1"}
     * optime : 32ms
     */

    private String code;
    private String msg;
    private DataBean data;
    private String optime;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getOptime() {
        return optime;
    }

    public void setOptime(String optime) {
        this.optime = optime;
    }

    public static class DataBean {
        /**
         * 0 : 1
         * uid : 13
         * 1 : 1
         * rssi : -43
         * 2 : 1
         * 3 : 3
         * 4 : 3
         * 5 : 2
         * 6 : 0
         * type : 5678
         * isonline : 1
         */

        @SerializedName("0")
        private int _$0;
        private int uid;
        @SerializedName("1")
        private int _$1;
        private int rssi;
        @SerializedName("2")
        private int _$2;
        @SerializedName("3")
        private int _$3;
        @SerializedName("4")
        private int _$4;
        @SerializedName("5")
        private int _$5;
        @SerializedName("6")
        private int _$6;
        private int type;
        private String isonline;

        public int get_$0() {
            return _$0;
        }

        public void set_$0(int _$0) {
            this._$0 = _$0;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int get_$1() {
            return _$1;
        }

        public void set_$1(int _$1) {
            this._$1 = _$1;
        }

        public int getRssi() {
            return rssi;
        }

        public void setRssi(int rssi) {
            this.rssi = rssi;
        }

        public int get_$2() {
            return _$2;
        }

        public void set_$2(int _$2) {
            this._$2 = _$2;
        }

        public int get_$3() {
            return _$3;
        }

        public void set_$3(int _$3) {
            this._$3 = _$3;
        }

        public int get_$4() {
            return _$4;
        }

        public void set_$4(int _$4) {
            this._$4 = _$4;
        }

        public int get_$5() {
            return _$5;
        }

        public void set_$5(int _$5) {
            this._$5 = _$5;
        }

        public int get_$6() {
            return _$6;
        }

        public void set_$6(int _$6) {
            this._$6 = _$6;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getIsonline() {
            return isonline;
        }

        public void setIsonline(String isonline) {
            this.isonline = isonline;
        }
    }
}
