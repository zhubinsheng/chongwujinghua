package com.sim.chongwukongjing.ui.utils;

public class ShuiweiUtil {
    public static String shuiwei(int i){
        if (i>40&&i<86){
            return "100%";
        }
        if (i>86&&i<112){
            return "80%";
        }
        if (i>112&&i<138){
            return "60%";
        }
        if (i>138&&i<164){
            return "40%";
        }
        if (i>164&&i<191){
            return "0%";
        }
        return "0%";
    }
}
