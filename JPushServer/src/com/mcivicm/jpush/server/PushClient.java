package com.mcivicm.jpush.server;

import cn.jiguang.common.ClientConfig;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;

public class PushClient {

	private static final String MASTER_SECRET = "7e76b3e02b3697d4ca177bd9";
	private static final String APP_KEY = "12a90ccc628778adbd34d7b6";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, ClientConfig.getInstance());

		    // For push, all you need do is to build PushPayload object.
		    PushPayload payload = buildPushObject_all_all_alert();

		    try {
		        PushResult result = jpushClient.sendPush(payload);
		        LOG.info("Got result - " + result);

		    } catch (APIConnectionException e) {
		        // Connection error, should retry later
		        LOG.error("Connection error, should retry later", e);

		    } catch (APIRequestException e) {
		        // Should review the error, and fix the request
		        LOG.error("Should review the error, and fix the request", e);
		        LOG.info("HTTP Status: " + e.getStatus());
		        LOG.info("Error Code: " + e.getErrorCode());
		        LOG.info("Error Message: " + e.getErrorMessage());
		    }
	}

}
