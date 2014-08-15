package com.example;
/***
 * Twilio credentials, don't post this to github!
 *
 */
public class TwilioCredentials {
	
	public static String GetSID()
	{
		String envVal = System.getenv("TWILIO_ACCOUNT_SID");
		if(envVal == null)
		{
			return TwilioCredentials.ACCOUNT_SID;
		}
		return envVal;
	}
	
	public static String GetToken()
	{
		String envVal = System.getenv("TWILIO_AUTH_TOKEN");
		if(envVal == null)
		{
			return TwilioCredentials.AUTH_TOKEN;
		}
		return envVal;
	}
	
	public static String GetHerokuAppName()
	{
		String envVal = System.getenv("HEROKU_NAME");
		if(envVal == null)
		{
			return TwilioCredentials.HEROKU_NAME;
		}
		return envVal;
	}
	
	
	public static final String ACCOUNT_SID = "";
	public static final String AUTH_TOKEN = "";
	public static final String HEROKU_NAME = "";
}
