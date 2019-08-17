package com.sim.chongwukongjing.ui.http;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.sim.chongwukongjing.ui.bean.ConfigResult;
import com.sim.chongwukongjing.ui.bean.MessageWrap;
import com.sim.chongwukongjing.ui.utils.Md5Util;
import com.zs.easy.mqtt.EasyMqttService;
import com.zs.easy.mqtt.IEasyMqttCallBack;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.greenrobot.eventbus.EventBus;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import static com.sim.chongwukongjing.ui.http.MyMqttService.MqttService.skipSSLSocktet;

/**
 * Author       wildma
 * Github       https://github.com/wildma
 * CreateDate   2018/11/08
 * Desc         ${MQTT服务}
 */

public class MyMqttService extends Service {


    Map<String, Integer> mapCopy = new LinkedHashMap<String, Integer>();

    private MessageWrap messageEvent;

    private static final String TOPIC = "";
    Map map = new HashMap<>();
    private static String userName = "INX2bd34731488b4ae2";
    private static String salt = "4986bb9b3e011c00";

    public String getPASSWORD() {
        Map map = new HashMap<>();
        map.put("clientid", userName);
        String password = Md5Util.signMD5(salt, map);

        return password;
    }

    public static final String ACTION = "com.sim.chongwukongjing.ui.http.MyMqttService";
    public final String TAG = MyMqttService.class.getSimpleName();
    private static MqttAndroidClient  mqttAndroidClient;
    private        MqttConnectOptions mMqttConnectOptions;
    //服务器地址（协议+地址+端口号）
    public        String HOST           = "ssl://qq.airmedic.cn:8785";
    //public        String USERNAME       = "INX2bd34731488b4ae2";//用户名
    public        String PASSWORD       = getPASSWORD();
    public static String PUBLISH_TOPIC  = "tourist_enter";//发布主题
    public static String RESPONSE_TOPIC = "message_arrived";//响应主题
    @SuppressLint("MissingPermission")
    @RequiresApi(api = 26)
    public        String CLIENTID       = "INX2bd34731488b4ae2";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * 开启服务
     */
    public static void startService(Context mContext, ConfigResult.DataBean data) {
        userName = data.getClientid();
        salt = data.getSalt();
        mContext.startService(new Intent(mContext, MyMqttService.class));
    }


    /**
     * 发布 （模拟其他客户端发布消息）
     *
     * @param message 消息
     */
    public static void publish(String message) {
        String topic = PUBLISH_TOPIC;
        Integer qos = 2;
        Boolean retained = false;
        try {
            //参数分别为：主题、消息的字节数组、服务质量、是否在服务器保留断开连接后的最后一条消息
            mqttAndroidClient.publish(topic, message.getBytes(), qos.intValue(), retained.booleanValue());
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    /**
     * 响应 （收到其他客户端的消息后，响应给对方告知消息已到达或者消息有问题等）
     *
     * @param message 消息
     */
    public void response(String message) {
        String topic = RESPONSE_TOPIC;
        Integer qos = 2;
        Boolean retained = false;
        try {
            //参数分别为：主题、消息的字节数组、服务质量、是否在服务器保留断开连接后的最后一条消息
            mqttAndroidClient.publish(topic, message.getBytes(), qos.intValue(), retained.booleanValue());
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void init() throws Exception {
        String serverURI = HOST; //服务器地址（协议+地址+端口号）
        mqttAndroidClient = new MqttAndroidClient(this, serverURI, userName);
        mqttAndroidClient.setCallback(mqttCallback); //设置监听订阅消息的回调
        mMqttConnectOptions = new MqttConnectOptions();
        mMqttConnectOptions.setCleanSession(true); //设置是否清除缓存
        mMqttConnectOptions.setConnectionTimeout(10); //设置超时时间，单位：秒
        mMqttConnectOptions.setKeepAliveInterval(20); //设置心跳包发送间隔，单位：秒
        mMqttConnectOptions.setUserName(userName); //设置用户名
        mMqttConnectOptions.setPassword(PASSWORD.toCharArray()); //设置密码
        mMqttConnectOptions.setSocketFactory(skipSSLSocktet());//跳过证书
        // last will message
        boolean doConnect = true;
        String message = "{\"terminal_uid\":\"" + userName + "\"}";
        String topic = PUBLISH_TOPIC;
        Integer qos = 2;
        Boolean retained = false;
        if ((!message.equals("")) || (!topic.equals(""))) {
            // 最后的遗嘱
            try {
                mMqttConnectOptions.setWill(topic, message.getBytes(), qos.intValue(), retained.booleanValue());
            } catch (Exception e) {
                Log.i(TAG, "Exception Occured", e);
                doConnect = false;
                iMqttActionListener.onFailure(null, e);
            }
        }


        if (doConnect) {
            doClientConnection();
        }


    }

    /**
     * 连接MQTT服务器
     */
    private void doClientConnection() {
        if (!mqttAndroidClient.isConnected() && isConnectIsNomarl()) {
            try {
                mqttAndroidClient.connect(mMqttConnectOptions, null, iMqttActionListener);
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 判断网络是否连接
     */
    private boolean isConnectIsNomarl() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null && info.isAvailable()) {
            String name = info.getTypeName();
            Log.i(TAG, "当前网络名称：" + name);
            return true;
        } else {
            Log.i(TAG, "没有可用网络");
            /*没有可用网络的时候，延迟3秒再尝试重连*/
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doClientConnection();
                }
            }, 3000);
            return false;
        }
    }

    //MQTT是否连接成功的监听
    private IMqttActionListener iMqttActionListener = new IMqttActionListener() {

        @Override
        public void onSuccess(IMqttToken arg0) {
            Log.i(TAG, "连接成功 ");
            try {
                //订阅消息 提供了一次订阅多个主题的方法
                //smt/$clientid/upd_status
                //int[] Qos = {1, 0};
                int Qos = 1;
                //String[] topic1 = {TOPIC + "/abc", TOPIC + "/" + userName};
                String topic1 = "smt/fa8c3d972bcddea4/upd_status";
                mqttAndroidClient.subscribe(topic1, Qos);
                //mqttAndroidClient.subscribe(PUBLISH_TOPIC, 2);//订阅主题，参数：主题、服务质量
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(IMqttToken arg0, Throwable arg1) {
            arg1.printStackTrace();
            Log.i(TAG, "连接失败 ");
            doClientConnection();//连接失败，重连（可关闭服务器进行模拟）
        }
    };

    //订阅主题的回调
    private MqttCallback mqttCallback = new MqttCallback() {

        @Override
        public void messageArrived(String topic, MqttMessage message) throws Exception {
            Log.i("zbs", "收到消息： " + new String(message.getPayload()));
            //收到消息，这里弹出Toast表示。如果需要更新UI，可以使用广播或者EventBus进行发送
            //Toast.makeText(getApplicationContext(), "messageArrived: " + new String(message.getPayload()), Toast.LENGTH_LONG).show();
            //EventBus.getDefault().post(messageEvent);
            Map<String, Integer> map = new LinkedHashMap<String, Integer>();
            map= Md5Util.mapTojson(message.toString());
            Log.i("zbs", "收到消息： " + map.get("0").toString());
            if (mapCopy!=null){
               if (!map.get("0").equals(mapCopy.get("0"))){
                   EventBus.getDefault().post(MessageWrap.getInstance("0",map.get("0")));
                   Log.i("zbs", "收到消息： " + map.get("0").toString());
               }
                if (!map.get("1").equals(mapCopy.get("1"))){
                    EventBus.getDefault().post(MessageWrap.getInstance("1",map.get("0")));
                    Log.i("zbs", "收到消息： " + map.get("0").toString());
                }
                if (!map.get("2").equals(mapCopy.get("2"))){
                    EventBus.getDefault().post(MessageWrap.getInstance("2",map.get("0")));
                    Log.i("zbs", "收到消息： " + map.get("0").toString());
                }
                if (!map.get("3").equals(mapCopy.get("3"))){
                    EventBus.getDefault().post(MessageWrap.getInstance("3",map.get("0")));
                    Log.i("zbs", "收到消息： " + map.get("0").toString());
                }
                if (!map.get("4").equals(mapCopy.get("4"))){
                    EventBus.getDefault().post(MessageWrap.getInstance("4",map.get("0")));
                    Log.i("zbs", "收到消息： " + map.get("0").toString());
                }
                if (!map.get("5").equals(mapCopy.get("5"))){
                    EventBus.getDefault().post(MessageWrap.getInstance("5",map.get("0")));
                    Log.i("zbs", "收到消息： " + map.get("0").toString());
                }
                if (!map.get("6").equals(mapCopy.get("6"))){
                    EventBus.getDefault().post(MessageWrap.getInstance("6",map.get("0")));
                    Log.i("zbs", "收到消息： " + map.get("0").toString());
                }

            }
            mapCopy = map;
            //收到其他客户端的消息后，响应给对方告知消息已到达或者消息有问题等
            response("message arrived");
        }

        @Override
        public void deliveryComplete(IMqttDeliveryToken arg0) {

        }

        @Override
        public void connectionLost(Throwable arg0) {
            Log.i(TAG, "连接断开 ");
            doClientConnection();//连接断开，重连
        }
    };

    @Override
    public void onDestroy() {
        try {
            mqttAndroidClient.disconnect(); //断开连接
        } catch (MqttException e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    /**
     * @author binshengzhu
     */
    public static class MqttService {


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

    public static class MyTrust implements TrustManager, X509TrustManager {
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
        @Override
        public void checkServerTrusted(X509Certificate[] certs, String authType)
                throws CertificateException {
        }
        @Override
        public void checkClientTrusted(X509Certificate[] certs, String authType)
                throws CertificateException {
        }
    }
}
