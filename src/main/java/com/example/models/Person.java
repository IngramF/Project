/***
 * 
 */
package com.example.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;

import com.example.AMSCore;

public class Person {
	
	private ArrayList phoneNumbers = new ArrayList<Phone>();
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
		
	}
	
	public Person(boolean isSupervisor)
	{
		this.isSupervisor = isSupervisor;
	}
	private Integer pin;
 	public Integer getPin() {
		return pin;
	}
	public void setPin(Integer pin) {
		this.pin = pin;
	}
	public ArrayList getPhoneNumbers() {
		return phoneNumbers;
	}
	public void setPhoneNumbers(ArrayList phoneNumbers) {
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


