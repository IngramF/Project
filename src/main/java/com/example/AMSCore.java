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

	public void Muster(Person initiator,String message) {
		
		// TODO Auto-generated method stub
		
		//Check to make sure person is not null
		
		//And that the person is a supervisor
		
		//and that the message is not null, or empty
		
		if (initiator == null) {
		throw  new IllegalArgumentException("Muster is not null");
		}
		if (!initiator.isSupervisor())
		{
			throw new IllegalArgumentException("The person is a supervisor");
		}
		if (message == null || message == "") {
			throw new IllegalArgumentException("The message is not null");
		}
	
		if(this.currentMuster !=null &&this.currentMuster.isStatus()){
			throw new IllegalArgumentException("Can't start a muster becuase one is already active.");
		}
		this.currentMuster = new Muster(message,new Date(),initiator,true);
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
		currentMuster.setActive(false);
		}
	
	
	public Muster GetMusterStatus() {
		//return the current muster
	
		
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

		public void ReportIn(MusterStatus status) {
		//Is the status object null?
		
		//is the status's person null?

		//Is the status's person on our list of people ?
		
		//Is the status's statusCode valid?
		
		//add the person's status to the muster
		
		
		if (status == null)
		{
			throw new IllegalArgumentException("MusterStatus was null.");
		}
		if (status.getPerson() == null)
		{
		throw new IllegalArgumentException("Person is null.");
		}
		boolean foundPerson = false;
		for(Person person : people)
		{
			if(person == status.getPerson())
			{
				foundPerson = true;
			}
		}
		
		if (!foundPerson )
		{
			throw new IllegalArgumentException("That Person is on our list");
		}

		currentMuster.AddStatus(status);
		{
			throw new IllegalArgumentException("The Status is vaild");
		}

		
	}

	public Person GetPersonByID(int id) {
		//Look through the list of people, and return the first one with
		//a matching ID number
		
		for(Person person : people)
		{
			if(person.getIdNumber() == id)
			{
			return person;
			}
		}
		
		return null;
	}

	public List<Person> GetPeople() {
		//return all the people in the people list
		throw new IllegalArgumentException("The list of people.");
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

		Person employee = new Person("John","Doe",001,true);
		people.add(employee);
		// We don't have a muster when AMS starts
		currentMuster = null;
		Person employee1 = new Person("Bob","Wilson",002,false);
		people.add(employee1);
		Person employee2 = new Person("Angelia","Jones",003,false);
		people.add(employee2);
		Person employee3 = new Person("Brandy","Marshall",004,true);
		people.add(employee3);
		Person employee4 = new Person("Rick","Barry",005,false);
		people.add(employee4);
		Person employee5 = new Person("Mary","Thomas",006,true);
		people.add(employee5);
	}
	
	

}
