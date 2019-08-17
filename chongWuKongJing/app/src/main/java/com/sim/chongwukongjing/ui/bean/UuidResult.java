package com.sim.chongwukongjing.ui.bean;

public class UuidResult {
    /**
     * code : 10000
     * msg : UUID生成成功！
     * data : {"uuid":"37691006f4284b43b98d624b89aa6192"}
     * optime : 4ms
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
         * uuid : 37691006f4284b43b98d624b89aa6192
         */

        private String uuid;

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }
    }
}
