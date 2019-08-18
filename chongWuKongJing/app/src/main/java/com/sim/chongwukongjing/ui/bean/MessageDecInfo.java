package com.sim.chongwukongjing.ui.bean;

public class MessageDecInfo {

    private String recode;
    private DvcInfoResult result;

    public String getRecode() {
        return recode;
    }

    public void setRecode(String recode) {
        this.recode = recode;
    }

    public DvcInfoResult getResult() {
        return result;
    }

    public void setResult(DvcInfoResult result) {
        this.result = result;
    }

    public static MessageDecInfo getInstance(String message , DvcInfoResult data) {
        return new MessageDecInfo(message,data);
    }

    public MessageDecInfo(String recode, DvcInfoResult result) {
        this.recode = recode;
        this.result = result;
    }
}