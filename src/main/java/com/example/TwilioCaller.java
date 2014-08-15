package com.example;

import java.util.HashMap;
import java.util.Map;

import com.example.models.Muster;
import com.example.models.Person;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.CallFactory;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.resource.instance.Call;

public class TwilioCaller {

	public static void MakeCall(String phoneNumber,Muster muster,Person person) throws TwilioRestException
	{
		TwilioRestClient client = new TwilioRestClient(TwilioCredentials.GetSID(), TwilioCredentials.GetToken());
        Account mainAccount = client.getAccount();
        CallFactory callFactory = mainAccount.getCallFactory();
        Map<String, String> callParams = new HashMap<String, String>();
        callParams.put("To", phoneNumber); // Replace with your phone number
        callParams.put("From", "(856) 979-8700"); // Replace with a Twilio number       
        callParams.put("Url", "http://sleepy-harbor-5712.herokuapp.com/services/ams/callscript/" + person.getIdNumber());                
        // Make the call
        Call call = callFactory.create(callParams);
        // Print the call SID (a 32 digit hex like CA123..)
        System.out.println(call.getSid());
	}
}
