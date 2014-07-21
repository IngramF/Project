package com.example;

import java.util.ArrayList;
import java.util.List;

import com.example.models.MusterStatus;
import com.example.models.Person;
import com.example.models.Muster;

/***
 * Standard AMS core implementation
 * 
 * @author matthewgalligan
 *
 */
public class AMSCore implements IAMSCore {

	public Person Authenticate() {
		throw new UnsupportedOperationException();

	}

	public void Muster(Person initiator, String message) {
		throw new UnsupportedOperationException();
		// TODO Auto-generated method stub

	}

	public void Cancel(Person canceller) {
		// TODO Auto-generated method stub
		
		//check if person is null
		
		//check if supervisor
		
		//then check if muster is null
		
		//is muster active?
		 
		
		if (canceller == null){
			throw new IllegalArgumentException("Canceller was null.");
		}
		if(!canceller.isSupervisor())
			{
			throw new IllegalArgumentException("The person is not a supervisor.");
			}
		if(currentMuster == null)
		{
		throw new IllegalArgumentException("There is no current muster.");
		}
	if(!currentMuster.isActive())
	{
		throw new IllegalArgumentException("There is no muster to cancel right now");
	}
	currentMuster.setActive(false)
	}

	public Muster GetMusterStatus() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	public void ReportIn(MusterStatus status) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	public Person GetPeronByID(int id) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	public List<Person> GetPeople() {
		throw new UnsupportedOperationException();
	}

	/***
	 * This is the internal list of people. This will use a database or etc in a
	 * full implementation.
	 */
	private List<Person> people;
	/***
	 * This is the current muster, or nothing.
	 */
	private Muster currentMuster;

	public AMSCore() {
		// initialize people list, and put some default data in here.
		people = new ArrayList<Person>();

		// We don't have a muster when AMS starts
		currentMuster = null;
	}

}
