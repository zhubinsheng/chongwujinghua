package com.sim.chongwukongjing.ui.bean;

public class ConfigResult {

    /**
     * code : 10000
     * msg : MQTT初始信息获取成功！
     * data : {"code":"10000","msg":"sign succ","zone":8,"zonename":"Shanghai","time":1566032838,"salt":"6cc3c949dc19af62","clientid":"INX2bd34731488b4ae2","server":"qq.airmedic.cn","prefix":"ssl","port":"8775","sslport":"8785","ver":"3.1.1","lang":"CN","keepalive":30,"yieldtime":20,"lvl2":2,"lvl1":1,"lvl0":0,"delaytime":3000}
     */

    private String code;
    private String msg;
    private DataBean data;

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

    public static class DataBean {
        /**
         * code : 10000
         * msg : sign succ
         * zone : 8
         * zonename : Shanghai
         * time : 1566032838
         * salt : 6cc3c949dc19af62
         * clientid : INX2bd34731488b4ae2
         * server : qq.airmedic.cn
         * prefix : ssl
         * port : 8775
         * sslport : 8785
         * ver : 3.1.1
         * lang : CN
         * keepalive : 30
         * yieldtime : 20
         * lvl2 : 2
         * lvl1 : 1
         * lvl0 : 0
         * delaytime : 3000
         */

        private String code;
        private String msg;
        private int zone;
        private String zonename;
        private int time;
        private String salt;
        private String clientid;
        private String server;
        private String prefix;
        private String port;
        private String sslport;
        private String ver;
        private String lang;
        private int keepalive;
        private int yieldtime;
        private int lvl2;
        private int lvl1;
        private int lvl0;
        private int delaytime;

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

        public int getZone() {
            return zone;
        }

        public void setZone(int zone) {
            this.zone = zone;
        }

        public String getZonename() {
            return zonename;
        }

        public void setZonename(String zonename) {
            this.zonename = zonename;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public String getSalt() {
            return salt;
        }

        public void setSalt(String salt) {
            this.salt = salt;
        }

        public String getClientid() {
            return clientid;
        }

        public void setClientid(String clientid) {
            this.clientid = clientid;
        }

        public String getServer() {
            return server;
        }

        public void setServer(String server) {
            this.server = server;
        }

        public String getPrefix() {
            return prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public String getPort() {
            return port;
        }

        public void setPort(String port) {
            this.port = port;
        }

        public String getSslport() {
            return sslport;
        }

        public void setSslport(String sslport) {
            this.sslport = sslport;
        }

        public String getVer() {
            return ver;
        }

        public void setVer(String ver) {
            this.ver = ver;
        }

        public String getLang() {
            return lang;
        }

        public void setLang(String lang) {
            this.lang = lang;
        }

        public int getKeepalive() {
            return keepalive;
        }

        public void setKeepalive(int keepalive) {
            this.keepalive = keepalive;
        }

        public int getYieldtime() {
            return yieldtime;
        }

        public void setYieldtime(int yieldtime) {
            this.yieldtime = yieldtime;
        }

        public int getLvl2() {
            return lvl2;
        }

        public void setLvl2(int lvl2) {
            this.lvl2 = lvl2;
        }

        public int getLvl1() {
            return lvl1;
        }

        public void setLvl1(int lvl1) {
            this.lvl1 = lvl1;
        }

        public int getLvl0() {
            return lvl0;
        }

        public void setLvl0(int lvl0) {
            this.lvl0 = lvl0;
        }

        public int getDelaytime() {
            return delaytime;
        }

        public void setDelaytime(int delaytime) {
            this.delaytime = delaytime;
        }
    }
}
