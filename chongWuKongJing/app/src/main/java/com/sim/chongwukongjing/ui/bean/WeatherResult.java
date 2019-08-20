package com.sim.chongwukongjing.ui.bean;

public class WeatherResult {

    /**
     * msg : 天气接口调用成功！
     * code : 10000
     * data : {"area":"101190111","temp":"26","pm25":"18","today":"08月10日 周六 农历七月初十","areanm":"南京鼓楼","ip":"115.197.41.13","weath":"暴雨转大雨","quality":"优","wind":"北风6-7级"}
     * optime : 45ms
     */

    private String msg;
    private String code;
    private DataBean data;
    private String optime;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
         * area : 101190111
         * temp : 26
         * pm25 : 18
         * today : 08月10日 周六 农历七月初十
         * areanm : 南京鼓楼
         * ip : 115.197.41.13
         * weath : 暴雨转大雨
         * quality : 优
         * wind : 北风6-7级
         */

        private String area;
        private String temp;
        private String temp2;
        private String pm25;
        private String today;
        private String areanm;
        private String ip;
        private String weath;
        private String quality;
        private String wind;

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getTemp() {
            return temp;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        public String getPm25() {
            return pm25;
        }

        public void setPm25(String pm25) {
            this.pm25 = pm25;
        }

        public String getToday() {
            return today;
        }

        public void setToday(String today) {
            this.today = today;
        }

        public String getAreanm() {
            return areanm;
        }

        public void setAreanm(String areanm) {
            this.areanm = areanm;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getWeath() {
            return weath;
        }

        public void setWeath(String weath) {
            this.weath = weath;
        }

        public String getQuality() {
            return quality;
        }

        public void setQuality(String quality) {
            this.quality = quality;
        }

        public String getWind() {
            return wind;
        }

        public void setWind(String wind) {
            this.wind = wind;
        }

        public String getTemp2() {
            return temp2;
        }

        public void setTemp2(String temp2) {
            this.temp2 = temp2;
        }
    }
}
