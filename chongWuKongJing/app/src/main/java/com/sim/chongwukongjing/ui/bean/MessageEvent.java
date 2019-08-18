package com.sim.chongwukongjing.ui.bean;

import java.util.Map;

public class MessageEvent {

    private String recode;
    private WeatherResult result;

    public String getRecode() {
        return recode;
    }

    public void setRecode(String recode) {
        this.recode = recode;
    }

    public WeatherResult getResult() {
        return result;
    }

    public void setResult(WeatherResult result) {
        this.result = result;
    }


    public static MessageEvent getInstance(String message , WeatherResult data) {
        return new MessageEvent(message,data);
    }


    public MessageEvent(String recode, WeatherResult result) {
        this.recode = recode;
        this.result = result;
    }
}