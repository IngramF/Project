package com.example.services;

import java.io.FileReader;
import java.util.Date;

import com.example.AMSCore;
import com.example.TwilioCredentials;
import com.example.models.Muster;
import com.example.models.MusterStatus;
import com.example.models.Person;
import com.example.models.MusterStatus.StatusCodes;

import javax.net.ssl.SSLEngineResult.Status;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.omg.CosNaming.NamingContextExtPackage.InvalidAddress;

@Path("/ams")
@Produces(MediaType.APPLICATION_JSON)
public class AMSService {

	private AMSCore ams = null;
	
	public AMSCore GetAMSCore()
	{
		if(ams == null)
		{
			ams = AMSCore.DeSerialize("ams.dat");
		
		}
		return ams;
	}
	
	public void SaveAMSCore()
	{
			ams.Serialize(fileName);
	}
	
	private static String fileName = "ams.dat";
	
	
	/***
	 * Delete the AMS database and create a new one.
	 * @param req
	 * @return
	 */
	@DELETE
	@Path("/database")
	public boolean RegenerateDatabase(@Context HttpServletRequest req){
		if(!CheckIP(req))
		{
			return false;
		}
		ams = new AMSCore();
		SaveAMSCore();
		return true;
	}
	
	
    @GET
    @Path("/status")
    public Muster getStatus(@Context HttpServletRequest req) {
    	if(!CheckIP(req))
    	{
    		return new Muster("Your host:" + req.getRemoteAddr(),new Date(),null,false);
    	}
    	AMSCore ams = GetAMSCore();
        return ams.GetMusterStatus();
    }

    
    @POST
    @Path("/startmuster")
    public Muster startMuster(@FormParam("id") int personId, @FormParam("message") String message,@Context HttpServletRequest req) 
    {   
    	if(!CheckIP(req))
    	{
    		return null;
    	}
	    	AMSCore ams = GetAMSCore();
	    	Person person = ams.GetPersonByID(personId);
	    	
	    	ams.Muster(person, message);
	    	SaveAMSCore();
	    	return ams.GetMusterStatus();    	
    	
    }
    
    private boolean CheckIP(HttpServletRequest req)
    {
    	String host = req.getHeader("x-forwarded-for");    	
    	if(host == null)
    		host =req.getRemoteHost();
    	return(host.equals("198.253.70.51") || host.equals("127.0.0.1") || host.equals("0:0:0:0:0:0:0:1"));    		
    }
    
    @POST
    @Path("/cancelmuster")
    public Muster cancelMuster(@FormParam("id") int personId,@Context HttpServletRequest req)
    {
    	if (!CheckIP(req))
    	{
    		return null;
    	}
	    	AMSCore ams = GetAMSCore();
	    	Person person = ams.GetPersonByID(personId);
	    	ams.Cancel(person);
	    	SaveAMSCore();
	    	return ams.GetMusterStatus();    	
    }

    
    @POST
    @Path("/callscript/{id}")
    @Produces("application/xml")
    public String GetCallScript(@PathParam("id") int id)
    {
    	AMSCore ams = GetAMSCore();
    	Person person = ams.GetPersonByID(id);
    	
    	if(person == null)
    	{
    		return "<Response><Say>Employee not found -- An Error has Occured</Say></Response>";
    	}
    	String script = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+
    			"<Response>\n"+
    			"<Say voice=\"woman\">\n"+
    			"Hello, " + person.getFirstName() + " " + person.getLastName() + "</Say>" +    		
    			"<Say voice=\"woman\">This is the A M S System. There is an active Muster. </Say><Pause length=\"1\" /> <Say voice=\"woman\">The Muster Message is: "+ ams.GetMusterStatus().getMessage() +"<Pause length=\"1\" />Please report your status.\n</Say>"+
    			"<Gather timeout=\"10\" finishOnKey=\"#\" numDigits=\"1\" method=\"POST\" action=\"http://"+TwilioCredentials.GetHerokuAppName()+".herokuapp.com/services/ams/report/"+ id + "\">\n"+    			
    			"        \n"+
    			        "<Say voice=\"woman\">\n"+
    			        "Press One for AT WORK\n"+
    			        "</Say>\n"+
    			        "<Pause length=\"1\"/>\n"+
    			        "<Say voice=\"woman\">\n"+
    			        "Press Two for AT HOME\n"+
    			        "</Say>\n"+    			        
    			        "<Pause length=\"1\"/>\n"+
    			        "<Say voice=\"woman\">\n"+
    			        "Press Three for IN TRANSIT\n"+
    			        "</Say>\n"+
    			        "<Pause length=\"1\"/>\n"+
    			        "<Say voice=\"woman\">\n"+
    			        "Press Four for OTHER\n"+
    			        "</Say>\n"+
    			        "<Pause length=\"1\"/>\n"+    			
    			"<Say voice=\"woman\">\n"+
    			"Please enter your muster status.        \n"+
    			"</Say>\n"+
    			"</Gather>\n"+
    			"<Say voice=\"woman\">Sorry, I didn't get your response.</Say>\n"+
    			"<Redirect method=\"POST\">http://"+TwilioCredentials.GetHerokuAppName()+".herokuapp.com/services/ams/callscript/"+ id + "></Redirect>\n"+
    			"</Response>\n";
    	return script;
    	
    	
    }
        
    @POST
    @Path("/report/{id}")
    @Produces("application/xml")
    public String reportIn(@PathParam("id") int employeeID, @FormParam("Digits") int statusIntValue)
    {
    	AMSCore ams = GetAMSCore();
    
    	Person emp = ams.GetPersonByID(employeeID);    	
    	
    	StatusCodes status = null;
    	
    	//convert status int value to status enum
    	for(StatusCodes code : StatusCodes.values() )
    	{
    		if(code.getValue() == statusIntValue)
    		{
    			status = code;
    		}
    	}
    	
    	if(status == null)
    	{
    		throw new IllegalArgumentException("Invalid status code passed.");
    	}
    	
    	MusterStatus musterStatus = new MusterStatus(emp,status);
    	ams.ReportIn(musterStatus);
    	SaveAMSCore();
    	
    	return "<Response><Say voice=\"woman\">Thank you. Your response has been saved.</Say></Response>";
    
    }
    
    	/*return "To use AMS, the following commands can be used.\n"
    	+" To Obtain Status, GET /ams/status\n "
    	+" To Muster, PUT /ams/report\n "
    	+" To Create a new muster, POST /ams/muster\n "
    	+" To Cancel a muster, POST /ams/cancel\n ";*/    
    
}

