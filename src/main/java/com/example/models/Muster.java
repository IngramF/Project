/***
 * 
 */
package com.example.models;

public class Muster {
	
	public Muster(String date)
	{
		this.date = date;
	}
	public Muster(String time)
	{
		this.time = time;
	}
	public Muster(String who)
	{
		this.who = who;
	}
	public (boolean status)
	{
		this.status = status;
	}
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
	private boolean status;
	public boolean isStatus() {
		return status;
	}
	public void setStaus(boolean status) {
		this.status = status;
	} 
}
