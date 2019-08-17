package com.sim.chongwukongjing.ui.bean;

public class MessageWrap {

    public final String message;
    public final int data;

    public static MessageWrap getInstance(String message ,int data) {
        return new MessageWrap(message,data);
    }

    private MessageWrap(String message ,int data) {
        this.message = message;
        this.data = data;
    }
}