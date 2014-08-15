package com.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cedarsoftware.util.io.JsonReader;
import com.cedarsoftware.util.io.JsonWriter;
import com.example.models.MusterStatus;
import com.example.models.Person;
import com.example.models.Muster;
import com.example.models.Phone;
import com.example.TwilioCredentials;
import com.twilio.sdk.TwilioRestException;

/***
 * Standard AMS core implementation
 * 
 * @author matthewgalligan
 *
 */
public class AMSCore implements IAMSCore {
	/***
	 * This is the internal list of people. This will use a database or etc8 in a
	 * full implementation.
	 */
	private List<Person> employeeList;
	/***
	 * This is the current muster, or nothing.
	 */
	private Muster currentMuster;
	
	
	public AMSCore()
	{
		employeeList = new ArrayList<Person>();
		employeeList.add(new Person("Jim","Doe",2,false));
    	employeeList.add(new Person("Tom","Tomlison",3,true));
    	employeeList.add(new Person("Mary","Thomas",4,true));
    	employeeList.add(new Person("Samsun", "Jackson" ,5, false));
    	employeeList.add(new Person("Reginald", "Pierce",6,false));
    	employeeList.add(new Person("Becky", "Anderson",7,false));
    	employeeList.add(new Person("Ann", "Louis",8,true));
    	employeeList.add(new Person("Micheal", "Knight",88,false));
    	employeeList.add(new Person("Johnny", "James", 14, true ));
    	employeeList.add(new Person("Micheal", "Upshaw",-99,false));
    	Person matt = new Person("Matt","G",101,true);
    	Phone phone = new Phone("(856) 492-1273");
    	matt.getPhoneNumbers().add(phone);
    	employeeList.add(matt);
    	
		currentMuster = new Muster("No Muster", new Date(),null ,false);
	}
	
	
	public AMSCore(List<Person> peopleList) {
		// initialize people list, and put some default data in here.
		employeeList = peopleList;
		currentMuster = new Muster("No Muster", new Date(),null ,false);
	}
	public void Serialize(String fileName)
	{
		JsonWriter jw = null;
		
		try
		{
		FileOutputStream fout = new FileOutputStream(fileName);
	    jw = new JsonWriter(fout);
	    jw.write(this);
	    
		}
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
		finally
		{
			if(jw != null)
			{
			 jw.close();
			}
		}
	   
	}
	
	public static AMSCore DeSerialize(String fileName)
	{
		 FileInputStream fin = null;
		 JsonReader jr = null;
		 
		 AMSCore core = null;
			try
			{
			fin = new FileInputStream(fileName);
			jr = new JsonReader(fin);
		   
		    
		    core = (AMSCore) jr.readObject();
		    }
		    catch(Exception e)
		    {
		    	e.printStackTrace();
		    	core = new AMSCore();
		    }
			finally
			{
				if(jr != null)
				{
					jr.close();
				}
			}
		    return core;
	}

	public void Muster(Person person, String message) {

		// TODO Auto-generated method stub

		// Check to make sure person is not null

		// And that the person is a supervisor

		// and that the message is not null, or empty
		
		
		if (person == null) {
			throw new IllegalArgumentException("Person is null");
		}

		if (!person.isSupervisor()) {
			throw new IllegalArgumentException("The person is not a supervisor");
		}

		if (message == null || message == "") {
			throw new IllegalArgumentException("The message is null");
		}

		if (this.currentMuster != null && this.currentMuster.isStatus()) {
			throw new IllegalArgumentException(
					"Can't start a muster becuase one is already active.");
		}

		this.currentMuster = new Muster(message, new Date(), person, true);
		
		//If I just mustered, call everybody.
		
		for(Person emp : this.GetPeople())
		{
			for(Phone phone : emp.getPhoneNumbers())
			{
				System.out.println("Calling " + phone.getPhoneNumber());
				try {
					TwilioCaller.MakeCall(phone.getPhoneNumber(), this.GetMusterStatus(),emp);
				} catch (TwilioRestException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}			
		
	}

	public void Cancel(Person canceller) {
		// TODO Auto-generated method stub

		// check if person is null

		// check if supervisor

		// then check if muster is null

		// is muster active?

		if (canceller == null) {
			throw new IllegalArgumentException("Canceller was null.");
		}
		if (!canceller.isSupervisor()) {
			throw new IllegalArgumentException(
					"The person is not a supervisor.");
		}
		if (currentMuster == null) {
			throw new IllegalArgumentException("There is no current muster.");
		}
		if (!currentMuster.isActive()) {
			throw new IllegalArgumentException(
					"There is no muster to cancel right now");
		}
		currentMuster.setActive(false);
	}

	public Muster GetMusterStatus() {
		// return the current muster
		Person musterBy, tempPerson;
		Date startMusterDate;		
		ArrayList<MusterStatus> statuses;
		
		System.out.println("CURRENT MUSTER STATUS:");
		if (currentMuster.isActive())
		{
			System.out.println("There is an active Mustering");
			musterBy = currentMuster.getWho();
			System.out.println("Muster"+ "r started by: " + musterBy.getFirstName() + " " + musterBy.getLastName());
			startMusterDate = currentMuster.getdate();
			System.out.println("Muster started on: " + startMusterDate.toString());
			System.out.println("Muster message: " + currentMuster.getMessage());
			System.out.println("Employee status");
			
			statuses = currentMuster.getStatuses();
			for(MusterStatus stat : statuses)
			{
			    System.out.println(stat);
			}
			
		}
		else
		{
			System.out.println("There is no active Mustering");
		}
		
		
		return currentMuster;
	}

	public void ReportIn(MusterStatus status) {
		// Is the status object null?

		// is the status's person null?

		// Is the status's person on our list of people ?

		// Is the status's statusCode valid?

		// add the person's status to the muster
		
		
		if (status == null) {
			throw new IllegalArgumentException("MusterStatus was null.");
		}
		if (status.getPerson() == null) {
			throw new IllegalArgumentException("Person is null.");
		}
		boolean foundPerson = false;
		for (Person person : employeeList) {
			if (person == status.getPerson()) {
				foundPerson = true;
			}
		}

		if (!foundPerson) {
			throw new IllegalArgumentException("That Person is on our list");
		}

		currentMuster.AddStatus(status);
		{
	
		}

	}

	public Person GetPersonByID(int id) {
		// Look through the list of people, and return the first one with
		// a matching ID number

		for (Person person : employeeList) {
			if (person.getIdNumber() == id) {
				return person;
			}
		}

		return null;

	}

	public List<Person> GetPeople() {
		// return all the people in the people list
		return employeeList;
	}

}
