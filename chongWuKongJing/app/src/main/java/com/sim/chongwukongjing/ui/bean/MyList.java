package com.sim.chongwukongjing.ui.bean;

import java.util.List;

/**
 * @author binshengzhu
 */
public class MyList {

    /**
     * code : 10000
     * msg : 我的设备列表获取成功！
     * data : [{"owner":"1","panelid":2010,"isvideoin":"","dvcgs":"1","dvcnm":"AIR MEDIC-AIR MEDIC S1","attr6":107,"pic":"http://asjdflkajflkdasjkljfaklsjdflk.jpg","isnative":"1","tid":75,"isonline":"1","select_img":"http://asjdflkajflkdasjkljfaklsjdflk.jpg","bhome":"1","ison":null,"tmallflag":"0","did":"905277ea4aceeee0","unselect_img":"http://asjdflkajflkdasjkljfaklsjdflk.jpg"}]
     * other : {"airQuality":"NA","intemp":"NA","indumi":"NA","inpm25":"NA"}
     * optime : 21ms
     */

    private String code;
    private String msg;
    private OtherBean other;
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

    public OtherBean getOther() {
        return other;
    }

    public void setOther(OtherBean other) {
        this.other = other;
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

    public static class OtherBean {
        /**
         * airQuality : NA
         * intemp : NA
         * indumi : NA
         * inpm25 : NA
         */

        private String airQuality;
        private String intemp;
        private String indumi;
        private String inpm25;

        public String getAirQuality() {
            return airQuality;
        }

        public void setAirQuality(String airQuality) {
            this.airQuality = airQuality;
        }

        public String getIntemp() {
            return intemp;
        }

        public void setIntemp(String intemp) {
            this.intemp = intemp;
        }

        public String getIndumi() {
            return indumi;
        }

        public void setIndumi(String indumi) {
            this.indumi = indumi;
        }

        public String getInpm25() {
            return inpm25;
        }

        public void setInpm25(String inpm25) {
            this.inpm25 = inpm25;
        }
    }

    public static class DataBean {
        /**
         * owner : 1
         * panelid : 2010
         * isvideoin :
         * dvcgs : 1
         * dvcnm : AIR MEDIC-AIR MEDIC S1
         * attr6 : 107
         * pic : http://asjdflkajflkdasjkljfaklsjdflk.jpg
         * isnative : 1
         * tid : 75
         * isonline : 1
         * select_img : http://asjdflkajflkdasjkljfaklsjdflk.jpg
         * bhome : 1
         * ison : null
         * tmallflag : 0
         * did : 905277ea4aceeee0
         * unselect_img : http://asjdflkajflkdasjkljfaklsjdflk.jpg
         */

        private String owner;
        private int panelid;
        private String isvideoin;
        private String dvcgs;
        private String dvcnm;
        private int attr6;
        private String pic;
        private String isnative;
        private int tid;
        private String isonline;
        private String select_img;
        private String bhome;
        private Object ison;
        private String tmallflag;
        private String did;
        private String unselect_img;

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }

        public int getPanelid() {
            return panelid;
        }

        public void setPanelid(int panelid) {
            this.panelid = panelid;
        }

        public String getIsvideoin() {
            return isvideoin;
        }

        public void setIsvideoin(String isvideoin) {
            this.isvideoin = isvideoin;
        }

        public String getDvcgs() {
            return dvcgs;
        }

        public void setDvcgs(String dvcgs) {
            this.dvcgs = dvcgs;
        }

        public String getDvcnm() {
            return dvcnm;
        }

        public void setDvcnm(String dvcnm) {
            this.dvcnm = dvcnm;
        }

        public int getAttr6() {
            return attr6;
        }

        public void setAttr6(int attr6) {
            this.attr6 = attr6;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getIsnative() {
            return isnative;
        }

        public void setIsnative(String isnative) {
            this.isnative = isnative;
        }

        public int getTid() {
            return tid;
        }

        public void setTid(int tid) {
            this.tid = tid;
        }

        public String getIsonline() {
            return isonline;
        }

        public void setIsonline(String isonline) {
            this.isonline = isonline;
        }

        public String getSelect_img() {
            return select_img;
        }

        public void setSelect_img(String select_img) {
            this.select_img = select_img;
        }

        public String getBhome() {
            return bhome;
        }

        public void setBhome(String bhome) {
            this.bhome = bhome;
        }

        public Object getIson() {
            return ison;
        }

        public void setIson(Object ison) {
            this.ison = ison;
        }

        public String getTmallflag() {
            return tmallflag;
        }

        public void setTmallflag(String tmallflag) {
            this.tmallflag = tmallflag;
        }

        public String getDid() {
            return did;
        }

        public void setDid(String did) {
            this.did = did;
        }

        public String getUnselect_img() {
            return unselect_img;
        }

        public void setUnselect_img(String unselect_img) {
            this.unselect_img = unselect_img;
        }
    }
}
