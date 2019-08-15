package com.sim.chongwukongjing.ui.bean;

public class ControlResult {

    /**
     * code : 10000
     * msg : 设备控制成功！
     * data : {"msg":"OK","code":200,"data":"send success","uid":51}
     * optime : 8ms
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
         * msg : OK
         * code : 200
         * data : send success
         * uid : 51
         */

        private String msg;
        private int code;
        private String data;
        private int uid;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

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

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }
    }
}
