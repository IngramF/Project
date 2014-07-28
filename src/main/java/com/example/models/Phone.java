package com.example.models;

public class Phone {
	private String phoneNumber;
	
	boolean isSMS;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean isSMS() {
		return isSMS;
	}

	public void setSMS(boolean isSMS) {
		this.isSMS = isSMS;
	}

	public Phone(String phonenum) {

	}
}
