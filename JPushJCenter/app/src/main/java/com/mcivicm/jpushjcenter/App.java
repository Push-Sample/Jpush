package com.mcivicm.jpushjcenter;

import android.app.Application;
import android.app.Notification;

import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.CustomPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.MultiActionsNotificationBuilder;

/**
 * 自定义app类
 */

public class App extends Application {

    public static final String TAG = "bdgk";

    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        initNotification();
        initAlias();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        if (!JPushInterface.isPushStopped(this)) {
            JPushInterface.stopPush(this);
        }
    }

    private void initNotification() {
        /**
         * 基本通知，可用
         */
        BasicPushNotificationBuilder builder0 = new BasicPushNotificationBuilder(this);
        builder0.statusBarDrawable = R.mipmap.pic_login;
        builder0.notificationFlags = Notification.FLAG_AUTO_CANCEL
                | Notification.FLAG_SHOW_LIGHTS;  //设置为自动消失和呼吸灯闪烁
        builder0.notificationDefaults = Notification.DEFAULT_SOUND
                | Notification.DEFAULT_VIBRATE
                | Notification.DEFAULT_LIGHTS;  // 设置为铃声、震动、呼吸灯闪烁都要
        JPushInterface.setPushNotificationBuilder(1, builder0);
        /**
         *多个按钮的通知，可用
         */
        MultiActionsNotificationBuilder builder2 = new MultiActionsNotificationBuilder(this);
        //添加按钮，参数(按钮图片、按钮文字、扩展数据)
        builder2.addJPushAction(R.mipmap.pic_login, "确定", "confirm");
        builder2.addJPushAction(R.mipmap.pic_login, "取消", "cancel");
        JPushInterface.setPushNotificationBuilder(2, builder2);
        /**
         * 自定义通知，可用
         */
        CustomPushNotificationBuilder builder = new
                CustomPushNotificationBuilder(this,
                R.layout.notification_layout,
                R.id.icon,
                R.id.title,
                R.id.text);
        // 指定定制的 Notification Layout
        builder.statusBarDrawable = R.mipmap.pic_login;
        // 指定最顶层状态栏小图标
        builder.layoutIconDrawable = R.mipmap.pic_login;
        // 指定下拉状态栏时显示的通知图标
        JPushInterface.setPushNotificationBuilder(3, builder);
    }

    /**
     * 可以设置为手机号
     */
    private void initAlias() {
        JPushInterface.setAlias(this, 1, "13538193368");
        Set<String> set = new HashSet<>(0);
        set.add("fun");
        JPushInterface.addTags(this, 1, set);
    }
}
