package com.mcivicm.jpushjcenter;

import android.content.Context;
import android.util.Log;

import cn.jpush.android.api.JPushMessage;
import cn.jpush.android.service.JPushMessageReceiver;

/**
 * 别名和标签接收者
 */

public class AliasAndTagReceiver extends JPushMessageReceiver {
    private static final String TAG = App.TAG;

    @Override
    public void onAliasOperatorResult(Context context, JPushMessage jPushMessage) {
        super.onAliasOperatorResult(context, jPushMessage);
        Log.d(TAG, "alias: " + jPushMessage.getAlias());
    }

    @Override
    public void onTagOperatorResult(Context context, JPushMessage jPushMessage) {
        super.onTagOperatorResult(context, jPushMessage);
        Log.d(TAG, "tag size: " + jPushMessage.getTags().size() + "");
    }
}
