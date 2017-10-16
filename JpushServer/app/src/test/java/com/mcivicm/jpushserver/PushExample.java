package com.mcivicm.jpushserver;

import org.junit.Test;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.SMS;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.Notification;

/**
 * 推送例子
 */

public class PushExample {

    private String TITLE = "Title";
    private String ALERT = "String";
    private String CONTENT = "Content";

    private String master = "1361c5b76760cd1b631fb268";
    private String key = "8d97ec8a040e365e2820a769";

    @Test
    public void name() throws Exception {
        JPushClient jPushClient = new JPushClient(master, key, null, ClientConfig.getInstance());
        PushPayload payload = buildPushObject_android_audienceMore_messageWithExtras();
        PushResult pushResult = jPushClient.sendPush(payload);
        System.out.println(pushResult.getResponseCode());
    }

    private PushPayload buildPushObject_all_all_alert() {
        return PushPayload.alertAll("String");
    }

    private PushPayload buildPushObject_all_alias_alert() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias("alias1"))
                .setNotification(Notification.alert(ALERT))
                .build();
    }

    private PushPayload buildPushObject_android_tag_alertWithTitle() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.tag("tag1"))
                .setNotification(Notification.android(ALERT, TITLE, null))
                .build();
    }

    private PushPayload buildPushObject_android_tagAnd_alertWithExtrasAndMessage() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.all())
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setTitle("警报")
                                .setAlert(ALERT)
                                .addExtra("from", "JPush")
                                .build())
                        .build())
                .setMessage(Message.content(CONTENT))
                .setOptions(Options.newBuilder()
                        .setApnsProduction(true)
                        .build())
                .build();
    }

    public PushPayload buildPushObject_android_audienceMore_messageWithExtras() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.all())
                .setMessage(Message.newBuilder()
                        .setMsgContent(CONTENT)
                        .addExtra("from", "JPush")
                        .build())
                .build();
    }

    @Test
    public void sms() throws Exception {
        JPushClient jpushClient = new JPushClient(master, key);
        try {
            SMS sms = SMS.content("Test SMS", 10);
            PushResult result = jpushClient.sendMessageAll("sms", sms);
            System.out.println(result.getResponseCode());
        } catch (APIConnectionException e) {
            System.out.println(e.getMessage());
        } catch (APIRequestException e) {
            System.out.println(e.getMessage());
        }

    }

}
