package com.sim.chongwukongjing.ui.bean;

import java.util.List;

public class TtinmingResult {

    /**
     * code : 10000
     * msg : 定时设定成功！
     * data : [{"no":1,"startime":"01:02","endtime":"03:59","attr":"{\"1\":1}"}]
     * optime : 144ms
     */

    private String code;
    private String msg;
    private String optime;
    private List<DataBean> data;

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

    public String getOptime() {
        return optime;
    }

    public void setOptime(String optime) {
        this.optime = optime;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * no : 1
         * startime : 01:02
         * endtime : 03:59
         * attr : {"1":1}
         */

        private int no;
        private String startime;
        private String endtime;
        private String attr;

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public String getStartime() {
            return startime;
        }

        public void setStartime(String startime) {
            this.startime = startime;
        }

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }

        public String getAttr() {
            return attr;
        }

        public void setAttr(String attr) {
            this.attr = attr;
        }
    }
}
