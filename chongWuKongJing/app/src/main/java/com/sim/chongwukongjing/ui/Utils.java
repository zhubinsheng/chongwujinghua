package com.sim.chongwukongjing.ui;

/**
 * Created by Administrator on 2017/12/23 0023.
 */

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.UUID;


/**
 * 项目工具类
 */
public class Utils {

    public static boolean isMainProcess(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return context.getApplicationInfo().packageName.equals(appProcess.processName);
            }
        }
        return false;
    }

    /**
     * 显示一条toast
     *
     * @param msg
     */
    public static void showToastMsg(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * Activity跳转方法
     *
     * @param context
     * @param cls
     * @return 返回Activity对象
     */
    public static Activity goActivity(Context context, Class cls) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
        return (Activity) context;
    }

    /**
     * Activity跳转方法
     *
     * @param view
     * @param cls
     * @return 返回Activity对象
     */
    public static Activity goActivity(View view, Class cls) {
        Intent intent = new Intent(view.getContext(), cls);
        view.getContext().startActivity(intent);
        return (Activity) view.getContext();
    }

    /**
     * 获得指定数目的UUID
     *
     * @param number int 需要获得的UUID数量
     * @return String[] UUID数组
     */
    public static String[] getUUID(int number) {
        if (number < 1) {
            return null;
        }
        String[] retArray = new String[number];
        for (int i = 0; i < number; i++) {
            retArray[i] = getUUID();
        }
        return retArray;
    }

    /**
     * 获得一个UUID
     *
     * @return String UUID
     */
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        //去掉“-”符号
        return uuid.replaceAll("-", "");
    }

    //敏感词过滤


    //打开外部浏览器
    public static void openWeb(Context context, String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(intent);
    }

    /**
     * 验证手机格式
     */
    public static boolean isMobile(String number) {
        //"[1]"代表第1位为数字1，"[34578]"代表第二位可以为3、4、5、7、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        String num = "[1][34578]\\d{9}";
        if (TextUtils.isEmpty(number)) {
            return false;
        } else {
            //matches():字符串是否在给定的正则表达式匹配
            return number.matches(num);
        }
    }



    public static void getSign(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    "com.eliaovideo.videoline",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }






    public static Bitmap onCut(Activity activity) {
        //获取window最底层的view
        View view = activity.getWindow().getDecorView();
        view.buildDrawingCache();

        //状态栏高度
        Rect rect = new Rect();
        view.getWindowVisibleDisplayFrame(rect);
        int stateBarHeight = rect.top;
        Display display = activity.getWindowManager().getDefaultDisplay();

        //获取屏幕宽高
        int widths = display.getWidth();
        int height = display.getHeight();

        //设置允许当前窗口保存缓存信息
        view.setDrawingCacheEnabled(true);

        //去掉状态栏高度
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache(), 0, stateBarHeight, widths, height - stateBarHeight);

        view.destroyDrawingCache();
        return bitmap;

    }

    public static String sHA1(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), PackageManager.GET_SIGNATURES);
            byte[] cert = info.signatures[0].toByteArray();
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] publicKey = md.digest(cert);
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < publicKey.length; i++) {
                String appendString = Integer.toHexString(0xFF & publicKey[i])
                        .toUpperCase(Locale.US);
                if (appendString.length() == 1) {
                    hexString.append("0");
                }
                hexString.append(appendString);
                hexString.append(":");
            }
            String result = hexString.toString();
            return result.substring(0, result.length() - 1);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }


}
