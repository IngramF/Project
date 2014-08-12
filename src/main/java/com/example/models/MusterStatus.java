package com.example.models;

/***
 * A MusterStatus is a combination of a particular person and their muster status
 * @author matthewgalligan
 *
 */
public class MusterStatus {
	/***
	 * Who has mustered
	 */
	private Person person;
	/***
	 * Status of muster
	 */
	private StatusCodes status;
	
	/***
	 * Possible options for status codes
	 * @author matthewgalligan
	 *
	 */
	public enum StatusCodes
	{
		WORK(0),
		HOME(1),
		TDY(2);
		
		private final int value;
		
		public int getValue()
		{
			return value;
		}
		
		private StatusCodes(int value)
		{
			this.value = value;
		}
	}
	
	@Override
	public  String toString()
	{
		return "===========================\n" +
			   "MusterStatus Class:\n" +
			   "===========================\n" +
			   "Person Name " + person.getFirstName() + " " + person.getLastName() + "\n" +
			   "Status: " + status + "\n";	   
	}
	
	public MusterStatus(Person inPerson, StatusCodes inStatus)
	{
		person = inPerson;
		status = inStatus;
	}
	
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public StatusCodes getStatus() {
		return status;
	}

	public void setStatus(StatusCodes status) {
		this.status = status;
	}
	
}
