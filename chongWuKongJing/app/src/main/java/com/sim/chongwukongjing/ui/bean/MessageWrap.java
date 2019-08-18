package com.sim.chongwukongjing.ui.bean;

import java.util.Map;

public class MessageWrap {

    public final String message;
    public final int data;
    public Map<String, Integer> map;

    public static MessageWrap getInstance(String message ,int data,Map<String, Integer> map) {
        return new MessageWrap(message,data,map);
    }

    private MessageWrap(String message ,int data,Map<String, Integer> map) {
        this.message = message;
        this.data = data;
        this.map=map;
    }

    public Map<String, Integer> getMap() {
        return map;
    }

    public void setMap(Map<String, Integer> map) {
        this.map = map;
    }
}