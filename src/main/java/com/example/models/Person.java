/***
 * 
 */
package com.example.models;
import com.example.models.Phone;


import java.util.ArrayList;

import com.example.AMSCore;

public class Person {
	
	private ArrayList phoneNumbers;
 	private boolean isSupervisor;
 	private int idNumber;
 	private String lastName;
 	private String firstName;
 
	@Override
	public  String toString()
	{
		return "===========================\n" +
			   "Person Class:\n" +
			   "===========================\n" +
			   "ID Number " + idNumber + "\n" +
			   "First Name: " + firstName + "\n" +
			   "Last Name: " + lastName + "\n" +
			   "Is Supervisor: " + isSupervisor + "\n";
			     
	}
	
	public Person (String fName, String lName, Integer idNum, boolean isBoss)
	{
		firstName = fName;
		lastName  = lName;
		idNumber = idNum;
		isSupervisor = isBoss;
		phoneNumbers = new ArrayList<Phone>();
		
	}
	
	
	private Integer pin;
 	public Integer getPin() {
		return pin;
	}
	public void setPin(Integer pin) {
		this.pin = pin;
	}
	public ArrayList<Phone> getPhoneNumbers() {
		return phoneNumbers;
	}
	public void setPhoneNumbers(ArrayList<Phone> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
	public boolean isSupervisor() {
		return isSupervisor;
	}
	public void setSupervisor(boolean isSupervisor) {
		this.isSupervisor = isSupervisor;
	}
	public int getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(int idNumber) {
		this.idNumber = idNumber;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	

}


