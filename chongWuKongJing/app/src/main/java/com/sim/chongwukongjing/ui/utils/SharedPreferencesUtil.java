package com.sim.chongwukongjing.ui.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author Administrator
 */
public class SharedPreferencesUtil {
    /**
     * 使用SharedPreferences保存用户登录信息
     *
     * @param context
     * @param username
     * @param password
     */
    public static void saveLoginInfo(Context context, String username, String password) {
        // 获取SharedPreferences对象
        SharedPreferences sharedPre = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        // 获取Editor对象
        SharedPreferences.Editor editor = sharedPre.edit();
        // 设置参数
        editor.putString("username", username);
        editor.putString("password", password);
        // 提交
        editor.apply();
    }
    /**
     * 删除用户信息
     */
    public static void deleteUser(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        /*  peopleJson = sp.getString("KEY_PEOPLE_DATA","");*/
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }

    public static void getUser(Context context) {
        SharedPreferences sharedPreferences= context.getSharedPreferences("config", Context.MODE_PRIVATE);
        // 使用getString方法获得value，注意第2个参数是value的默认值
        if (sharedPreferences != null){
            String username =sharedPreferences.getString("username", "");
            String password =sharedPreferences.getString("password", "");
        }
    }


}
