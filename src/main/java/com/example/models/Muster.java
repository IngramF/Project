/***
 * 
 */
package com.example.models;

import java.util.ArrayList;
import java.util.Date;

public class Muster {
	private ArrayList<MusterStatus> statuses;
	private Date date;
	private Person who;
	private boolean status;
	private String message;
	
	@Override
	public  String toString()
	{
		return "===========================\n" +
			   "Muster Class:\n" + 
			   "===========================\n" +
			   "Initiator Name: " + who.getFirstName() + " " + who.getLastName() + "\n" +
			   "Date: " + date + "\n" +
			   "Message: " + message + "\n" +
			   "Status: " + status + "\n";
			   
			   
	}
	
	public Muster(String message, Date date, Person who, boolean status){
		this.message = message;
	
		this.date = date;
		
		this.who = who;
		
		this.status = status;
		
		this.statuses = new ArrayList<MusterStatus>();
				
	}
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getdate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	public Person getWho() {
		return who;
	}
	public void setWho(Person who) {
		this.who = who;
	}
	
	public boolean isStatus() {
		return status;
	}
	public void setStaus(boolean status) {
		this.status = status;
	}
	public ArrayList<MusterStatus> getStatuses() {
		return statuses;
	}
	public void setStatuses(ArrayList<MusterStatus> statuses) {
		this.statuses = statuses;
	}
	
	/***
	 * Add a single muster status to the muster status list
	 * @param status
	 */
	public void AddStatus(MusterStatus status)
	{
		this.statuses.add(status);
	}
	
	public boolean isActive() {
		return status;
	}
	public void setActive(boolean b) {
		// TODO Auto-generated method stub
		
		this.status = b;
	} 

}

