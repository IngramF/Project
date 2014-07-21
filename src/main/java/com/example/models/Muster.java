/***
 * 
 */
package com.example.models;

public class Muster {
	
	private String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getWho() {
		return who;
	}
	public void setWho(String who) {
		this.who = who;
	}
	private String date;
	private String time;
	private String who;
	private boolean staus;
	public boolean isStaus() {
		return staus;
	}
	public void setStaus(boolean staus) {
		this.staus = staus;
	} 
}
