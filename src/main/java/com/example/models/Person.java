/***
 * 
 */
package com.example.models;

import java.util.ArrayList;

public class Person {
	
	
	public Person(String firstName)
	{
		this.firstName = firstName;
	}
	public Person(String lastName)
	{
		this.lastName = lastName;
	}
	public Person(String idNumber)
	{
		this.idNumber = idNumber;
	}
	public Person(boolean isSupervisor)
	{
		this.isSupervisor = isSupervisor;
	}
	private String pin;
 	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
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
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
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
	private ArrayList phoneNumbers = new ArrayList<Phone>();
 	private boolean isSupervisor;
 	private String idNumber;
 	private String lastName;
 	private String firstName;
 	
 	
 }


