package com.example.services;

import java.io.FileReader;

import com.example.AMSCore;
import com.example.models.Muster;
import com.example.models.MusterStatus;
import com.example.models.Person;
import com.example.models.MusterStatus.StatusCodes;

import javax.net.ssl.SSLEngineResult.Status;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
	
	
    @GET
    @Path("/status")
    public Muster getStatus() {
    	AMSCore ams = GetAMSCore();
        return ams.GetMusterStatus();
    }

    
    
    @POST
    @Path("/startmuster")
    public Muster startMuster(@FormParam("id") int personId, @FormParam("message") String message)
    {   
    	AMSCore ams = GetAMSCore();
    	Person person = ams.GetPersonByID(personId);
    	
    	ams.Muster(person, message);
    	SaveAMSCore();
    	return ams.GetMusterStatus();
    	
    	
    }
    
    @POST
    @Path("/cancelmuster")
    public Muster cancelMuster(@FormParam("id") int personId)
    {
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
    			"<Say voice=\"woman\">This is the A M S System. There is an active Muster. Please report your status.\n"+
    			"<Gather timeout=\"10\" finishOnKey=\"#\" numDigits=\"1\" method=\"POST\" action=\"http://sleepy-harbor-5712.herokuapp.com/services/ams/report/"+ id + "\">\n"+
    			"</Say>\n"+
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
    			"<Redirect method=\"POST\">/services/ams/callscript/"+ id + "</Redirect>\n"+
    			"</Response>\n";
    	return script;
    	
    	
    }
    
    @PUT
    @POST
    @Path("/report/{id}")
    public MusterStatus reportIn(@PathParam("id") int employeeID, @FormParam("status") int statusIntValue)
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
    	
    	return musterStatus;
    
    }
    
    	/*return "To use AMS, the following commands can be used.\n"
    	+" To Obtain Status, GET /ams/status\n "
    	+" To Muster, PUT /ams/report\n "
    	+" To Create a new muster, POST /ams/muster\n "
    	+" To Cancel a muster, POST /ams/cancel\n ";*/    
    
}

