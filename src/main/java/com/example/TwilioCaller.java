package com.example;

import java.util.HashMap;
import java.util.Map;

import com.example.models.Muster;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.CallFactory;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.resource.instance.Call;

public class TwilioCaller {

	public static void MakeCall(String phoneNumber,Muster muster) throws TwilioRestException
	{
		TwilioRestClient client = new TwilioRestClient(TwilioCredentials.ACCOUNT_SID, TwilioCredentials.AUTH_TOKEN);
        Account mainAccount = client.getAccount();
        CallFactory callFactory = mainAccount.getCallFactory();
        Map<String, String> callParams = new HashMap<String, String>();
        callParams.put("To", phoneNumber); // Replace with your phone number
        callParams.put("From", "(215) 214-8118"); // Replace with a Twilio number
        callParams.put("Url", "https://raw.githubusercontent.com/IngramF/Project/master/twilioscript.xml");
        // Make the call
        Call call = callFactory.create(callParams);
        // Print the call SID (a 32 digit hex like CA123..)
        System.out.println(call.getSid());
	}
}
