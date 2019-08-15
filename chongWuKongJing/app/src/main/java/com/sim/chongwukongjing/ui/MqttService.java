package com.sim.chongwukongjing.ui;

import android.content.Context;
import android.util.Log;

import com.sim.chongwukongjing.ui.http.MyTrust;
import com.sim.chongwukongjing.ui.utils.Md5Util;
import com.zs.easy.mqtt.EasyMqttService;
import com.zs.easy.mqtt.IEasyMqttCallBack;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * @author binshengzhu
 */
public class MqttService {


    private static EasyMqttService mqttService;

    private static MqttClient client;
    // 单向跳过证书连接
    public static SocketFactory skipSSLSocktet() throws Exception {
        TrustManager[] trustAllCerts = new TrustManager[1];
        TrustManager tm = new MyTrust();
        trustAllCerts[0] = tm;
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        SocketFactory factory = sc.getSocketFactory();
        return factory;
    }


   /* public static void go() throws Exception {
        String host = "ssl://" + brokerProperties.getServer() + ":" + brokerProperties.getSslport();
        String clientid = brokerProperties.getClientId();
        client = new MqttClient(host, clientid, new MemoryPersistence());
        // MQTT的连接设置
        MqttConnectOptions options = new MqttConnectOptions();
        // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
        options.setCleanSession(true);
        // 设置连接的用户名
        options.setUserName(userName);
        // 设置连接的密码
        options.setPassword(passWord.toCharArray());
        // 设置超时时间 单位为秒
        options.setConnectionTimeout(10);
        // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
        options.setKeepAliveInterval(20);
        // 设置回调
        client.setCallback(new PushCallback(clientid));
        MqttTopic topic = client.getTopic(TOPIC);
        //setWill方法，如果项目中需要知道客户端是否掉线可以调用该方法。设置最终端口的通知消息
        options.setWill(topic, "close".getBytes(), 0, true);
        //设置要使用的socketFactory；这允许应用程序围绕创建网路套接字应用自己的策略，如果使用SSL连接，则可以使用SSLSocketFactory提供特定于应用程序的安全策略。
        //options.setSocketFactory(getSSLSocktet());//使用证书
        options.setSocketFactory(skipSSLSocktet());//跳过证书
        client.connect(options);
        //订阅消息
        //int[] Qos = {1, 0};
        //String[] topic1 = {TOPIC + "/abc", TOPIC + "/" + clientid};
        //client.subscribe(topic1, Qos);
    }*/


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



















    static class miTM implements TrustManager, X509TrustManager {
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public boolean isServerTrusted(X509Certificate[] certs) {
            return true;
        }

        public boolean isClientTrusted(X509Certificate[] certs) {
            return true;
        }

        public void checkServerTrusted(X509Certificate[] certs, String authType)
                throws CertificateException {
            return;
        }

        public void checkClientTrusted(X509Certificate[] certs, String authType)
                throws CertificateException {
            return;
        }
    }
}
