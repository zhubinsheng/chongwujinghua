package com.sim.chongwukongjing.ui.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import me.goldze.mvvmhabit.utils.StringUtils;

/**
 * @author binshengzhu
 */
public class Md5 {
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
}
