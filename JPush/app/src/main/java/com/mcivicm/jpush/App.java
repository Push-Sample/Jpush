package com.mcivicm.jpush;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

/**
 * 程序类
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //注册极光服务
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
}
