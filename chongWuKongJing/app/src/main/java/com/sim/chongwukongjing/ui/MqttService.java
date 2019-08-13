package com.sim.chongwukongjing.ui;

import android.content.Context;
import android.util.Log;

import com.sim.chongwukongjing.ui.utils.Md5Util;
import com.zs.easy.mqtt.EasyMqttService;
import com.zs.easy.mqtt.IEasyMqttCallBack;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import java.util.HashMap;
import java.util.Map;

/**
 * @author binshengzhu
 */
public class MqttService {


    private static EasyMqttService mqttService;





    //例: 连接密码算出示例子
    public static void makePassword(Context context){
        Map map = new HashMap<>();
        String userName = "INX2bd34731488b4ae2";
        String salt = "4986bb9b3e011c00";
        map.put("clientid", userName);
        String password = Md5Util.signMD5(salt, map);
        buildEasyMqttService(context,userName,password);
    }



    /**
     * 构建EasyMqttService对象
     */
    private static void buildEasyMqttService(Context context, String userName, String password) {
        mqttService = new EasyMqttService.Builder()
                //设置自动重连
                .autoReconnect(true)
                //设置不清除回话session 可收到服务器之前发出的推送消息
                .cleanSession(false)
                .userName(userName)
                .passWord(password)
                //唯一标示 保证每个设备都唯一就可以 建议 imei
                .clientId("INX2bd34731488b4ae2")
                //mqtt服务器地址 格式例如：tcp://10.0.261.159:1883
                .serverUrl("ssl://qq.airmedic.cn:8785")
                //心跳包默认的发送间隔
                .keepAliveInterval(30)
                //构建出EasyMqttService 建议用application的context
                .bulid(context);
        connect();
    }

    /**
     * 连接Mqtt服务器
     */
    private static void connect() {
        mqttService.connect(new IEasyMqttCallBack() {
            @Override
            public void messageArrived(String topic, String message, int qos) {
                //推送消息到达
            }

            @Override
            public void connectionLost(Throwable arg0) {
                //连接断开
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken arg0) {

            }

            @Override
            public void connectSuccess(IMqttToken arg0) {
                //连接成功
                Log.e("zbs连接成功", String.valueOf(arg0));
            }

            @Override
            public void connectFailed(IMqttToken arg0, Throwable arg1) {
                //连接失败
                Log.e("zbs连接失败", String.valueOf(arg1));
            }

        });
    }

    /**
     * 订阅主题 这里订阅三个主题分别是"a", "b", "c"
     */
    private void subscribe() {
        String[] topics = new String[]{"a", "b", "c"};
        //主题对应的推送策略 分别是0, 1, 2 建议服务端和客户端配置的主题一致
        // 0 表示只会发送一次推送消息 收到不收到都不关心
        // 1 保证能收到消息，但不一定只收到一条
        // 2 保证收到切只能收到一条消息
        int[] qoss = new int[]{0, 1, 2};
        mqttService.subscribe(topics, qoss);
    }
}
