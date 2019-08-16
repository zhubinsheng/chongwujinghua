package com.sim.chongwukongjing.ui.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @author binshengzhu
 *///ID 名称 类型 读写 说明 转化用ｍａｐ实际
public class ControVo {
    /**
     * 0 : 1
     * 1 : 1
     */

    //开关 bool 上报/控制 0：关 1：开 打开电源 {"0",1} 关闭电源 {"0",0}
    @SerializedName("0")
    private int _$0;

    //智能模式 bool 上报/控制 0：连续运转模式 1：智能模式 连续运转模式 {"1",0} 智能模式 {"1",1}
    @SerializedName("1")
    private int _$1;

    //盘锁 bool 上报/控制 0：解锁 1：上锁 解锁 {"2",0} 上锁 {"2",1}
    @SerializedName("2")
    private int _$2;

    public int get_$0() {
        return _$0;
    }

    public void set_$0(int _$0) {
        this._$0 = _$0;
    }

    public int get_$1() {
        return _$1;
    }

    public void set_$1(int _$1) {
        this._$1 = _$1;
    }

    public int get_$2() {
        return _$2;
    }

    public void set_$2(int _$2) {
        this._$2 = _$2;
    }

    public int get_$3() {
        return _$3;
    }

    public void set_$3(int _$3) {
        this._$3 = _$3;
    }

    public int get_$4() {
        return _$4;
    }

    public void set_$4(int _$4) {
        this._$4 = _$4;
    }

    public int get_$5() {
        return _$5;
    }

    public void set_$5(int _$5) {
        this._$5 = _$5;
    }

    //风力强度 u8 上报/控制 01最弱,02中,03最强
    @SerializedName("3")
    private int _$3;

    //定时设置 u8 上报/控制 0/无定时，01/3小时,02/6小时,03/12小时
    @SerializedName("4")
    private int _$4;

    //水位值 U16 上报 0-1000
    @SerializedName("5")
    private int _$5;




}
