package com.sim.chongwukongjing.ui.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.util.Log;
import android.widget.TextView;

import com.sim.chongwukongjing.R;
import com.sim.chongwukongjing.ui.Base.BaseActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;
import io.fogcloud.sdk.easylink.api.EasylinkP2P;

/**
 * @author binshengzhu
 */
public class InputPasswordActivity extends BaseActivity {

    private static final String TAG = "InputPasswordActivity";
    @BindView(R.id.ssid_name)
    TextView ssidName;

    EasylinkP2P elp2p;

    @Override
    protected int getLayoutRes() {
        return R.layout.input_password;
    }

    @Override
    protected void initView() {
        elp2p = new EasylinkP2P(this);
        elp2p.getSSID();

/*
        ConnectivityManager ctm = (ConnectivityManager) this.
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = ctm.getActiveNetworkInfo();
        String ssid2 = networkInfo.getExtraInfo();*/
    }

    @Override
    protected void initSet() {

    }

    @Override
    protected void initData() {
        requestPermissions();
        String ssid = getWIFISSID(this);
        ssidName.setText(ssid);
    }



    /**
     * 获取SSID
     * @param activity 上下文
     * @return  WIFI 的SSID
     */
    public String getWIFISSID(Activity activity) {
        String ssid="unknown id";

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O||Build.VERSION.SDK_INT==Build.VERSION_CODES.P) {

            WifiManager mWifiManager = (WifiManager) activity.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

            assert mWifiManager != null;
            WifiInfo info = mWifiManager.getConnectionInfo();

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                return info.getSSID();
            } else {
                return info.getSSID().replace("\"", "");
            }
        } else if (Build.VERSION.SDK_INT==Build.VERSION_CODES.O_MR1){

            ConnectivityManager connManager = (ConnectivityManager) activity.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            assert connManager != null;
            NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
            if (networkInfo.isConnected()) {
                if (networkInfo.getExtraInfo()!=null){
                    return networkInfo.getExtraInfo().replace("\"","");
                }
            }
        }
        return ssid;
    }

    @SuppressLint("CheckResult")
    private void requestPermissions() {
        RxPermissions rxPermission = new RxPermissions(this);
        rxPermission
                .requestEach(Manifest.permission.INTERNET,
                        Manifest.permission.ACCESS_WIFI_STATE,
                        //Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.ACCESS_NETWORK_STATE,
                        //Manifest.permission.READ_EXTERNAL_STORAGE,
                        //Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CHANGE_WIFI_MULTICAST_STATE,
                        Manifest.permission.CHANGE_WIFI_STATE,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION)
                .subscribe(permission -> {
                    if (permission.granted) {
                        // 用户已经同意该权限
                        Log.d(TAG, permission.name + " is granted.");
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时。还会提示请求权限的对话框
                        Log.d(TAG, permission.name + " is denied. More info should be provided.");
                    } else {
                        // 用户拒绝了该权限，而且选中『不再询问』
                        Log.d(TAG, permission.name + " is denied.");
                    }
                });


    }

}
