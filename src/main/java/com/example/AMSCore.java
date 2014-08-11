package com.example;

import java.util.ArrayList;
import java.util.Date;
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
		currentMuster = new Muster("No Muster", new Date(),null ,false);
	}
	public AMSCore(List<Person> peopleList) {
		// initialize people list, and put some default data in here.
		employeeList = peopleList;
		currentMuster = new Muster("No Muster", new Date(),null ,false);
	}

	public void Muster(Person person, String message) {

		// TODO Auto-generated method stub

		// Check to make sure person is not null

		// And that the person is a supervisor

		// and that the message is not null, or empty
		
		
		if (person == null) {
			throw new IllegalArgumentException("Muster is not null");
		}

		if (!person.isSupervisor()) {
			throw new IllegalArgumentException("The person is a supervisor");
		}

		if (message == null || message == "") {
			throw new IllegalArgumentException("The message is not null");
		}

		if (this.currentMuster != null && this.currentMuster.isStatus()) {
			throw new IllegalArgumentException(
					"Can't start a muster becuase one is already active.");
		}

		this.currentMuster = new Muster(message, new Date(), person, true);

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
