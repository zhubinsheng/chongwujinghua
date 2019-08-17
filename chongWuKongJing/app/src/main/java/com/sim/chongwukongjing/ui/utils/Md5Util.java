package com.sim.chongwukongjing.ui.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.codec.digest.DigestUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import me.goldze.mvvmhabit.utils.StringUtils;

/**
 * @author binshengzhu
 */
public class Md5Util {
    public static String signMD5(String appKey, Map<String, String> map) {
        List<String> keys = new ArrayList<String>(map.keySet());
        Collections.sort(keys, String.CASE_INSENSITIVE_ORDER);
        StringBuilder builder = new StringBuilder();
        for (String key : keys) {
            String val = map.get(key);
            if (!"sign".equals(key)) {
                //如果参数值是,直接返回
                if (StringUtils.isEmpty(val) || "null".equals(val)) val = "";
                //拼接
                builder.append(key + "=" + val);
                builder.append("&");
            }
        }
        builder.append(appKey);
        String content = builder.toString();
        System.out.println(content);
        //MD5
        String sign = DigestUtils.md5Hex(content);
        return sign;
    }

    public static Map<String, Integer> mapTojson(String json) {
        /*final Map<Integer, Integer> map = new LinkedHashMap<Integer, Integer>();
        map.put(1, 11);
        map.put(2, 10);
        map.put(3, 10);
        final Gson gson = new Gson();
        final String string = gson.toJson(map);*/
        final Gson gson = new Gson();
        final Type type = new TypeToken<Map<String, Integer>>() {}.getType();
        final Map<String, Integer> map2 = gson.fromJson(json, type);
        return map2;
       /* for (final Map.Entry<Integer, Integer> entry : map2.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }*/
    }

}
