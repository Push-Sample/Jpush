package com.mcivicm.jpushjcenter;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

/**
 * 自定义app类
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
}
