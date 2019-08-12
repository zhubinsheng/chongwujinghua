package com.sim.chongwukongjing.ui.bean;

import java.util.List;

/**
 * @author binshengzhu
 */
public class ProductlistResult {

    /**
     * code : 10000
     * msg : 产品型号列表获取成功！
     * data : [{"img":"http://7niu.aslkdfjaslkfjkjaskdf,jpg","pid":2010,"bid":40416,"type":"5678","version":"S1","netprotocol":"1","cid":211,"nm":"AIR MEDIC S1"}]
     * optime : 27ms
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
         * img : http://7niu.aslkdfjaslkfjkjaskdf,jpg
         * pid : 2010
         * bid : 40416
         * type : 5678
         * version : S1
         * netprotocol : 1
         * cid : 211
         * nm : AIR MEDIC S1
         */

        private String img;
        private int pid;
        private int bid;
        private String type;
        private String version;
        private String netprotocol;
        private int cid;
        private String nm;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public int getBid() {
            return bid;
        }

        public void setBid(int bid) {
            this.bid = bid;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getNetprotocol() {
            return netprotocol;
        }

        public void setNetprotocol(String netprotocol) {
            this.netprotocol = netprotocol;
        }

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }

        public String getNm() {
            return nm;
        }

        public void setNm(String nm) {
            this.nm = nm;
        }
    }
}
