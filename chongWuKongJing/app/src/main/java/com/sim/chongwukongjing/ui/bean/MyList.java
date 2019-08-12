package com.sim.chongwukongjing.ui.bean;

import java.util.List;

/**
 * @author binshengzhu
 */
public class MyList {

    /**
     * code : 10000
     * msg : 我的设备列表获取成功！
     * data : [{"owner":"1","panelid":2010,"isvideoin":"","dvcgs":"1","dvcnm":"我的小乖乖","pic":"http://asjdflkajflkdasjkljfaklsjdflk.jpg","isnative":"1","tid":75,"isonline":"0","select_img":"http://asjdflkajflkdasjkljfaklsjdflk.jpg","bhome":"1","ison":"0","tmallflag":"0","did":"4db9dce34105c025","unselect_img":"http://asjdflkajflkdasjkljfaklsjdflk.jpg"}]
     * optime : 28ms
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
         * owner : 1
         * panelid : 2010
         * isvideoin :
         * dvcgs : 1
         * dvcnm : 我的小乖乖
         * pic : http://asjdflkajflkdasjkljfaklsjdflk.jpg
         * isnative : 1
         * tid : 75
         * isonline : 0
         * select_img : http://asjdflkajflkdasjkljfaklsjdflk.jpg
         * bhome : 1
         * ison : 0
         * tmallflag : 0
         * did : 4db9dce34105c025
         * unselect_img : http://asjdflkajflkdasjkljfaklsjdflk.jpg
         */

        private String owner;
        private int panelid;
        private String isvideoin;
        private String dvcgs;
        private String dvcnm;
        private String pic;
        private String isnative;
        private int tid;
        private String isonline;
        private String select_img;
        private String bhome;
        private String ison;
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

        public String getIson() {
            return ison;
        }

        public void setIson(String ison) {
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
