package com.example.models;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.Assert;
import com.example.AMSCore;

public class AMSCoreTest {

	@Test
	public void TestGetPeople() {
		AMSCore core = new AMSCore();
		List<Person> people = core.GetPeople();
		
	
	}
	@Test
	public void TestGetPersonByID()
	{
		AMSCore core = new AMSCore();
		Person person = core.GetPersonByID(-1);
		assertNull(person);
		person = core.GetPersonByID(88);
		assertNull(person);
		person = core.GetPersonByID(1);
		assertEquals(person.getFirstName(),"John");
		assertEquals(person.getLastName(),"Doe");
		assertEquals(person.isSupervisor(),true);
		assertEquals(person.getIdNumber(),1);
	}
	
	
	//Should fail cancelling with no muster, and no person
	@Test(expected=IllegalArgumentException.class)
	public void TestCancel()
	{
		AMSCore core = new AMSCore();
		core.Cancel(null);
	}
	//Should fail mustering with no authenticated person
	@Test(expected=IllegalArgumentException.class)
	public void TestMuster()
	{
		AMSCore core = new AMSCore();
		core.Muster(null, "Test!");
	}
	
	//Report in should fail, if I pass in no employee or status
	@Test(expected=IllegalArgumentException.class)
	public void TestReportIn()
	{
		MusterStatus status = new MusterStatus(null,null);
		assertNull(status.getPerson());
		assertNull(status.getStatus());
		
		AMSCore core = new AMSCore();
		core.ReportIn(status);
	}
	
	//I should get no muster returned, since there is no muster.
	public void TestGetMusterStatus()
	{
		AMSCore core = new AMSCore();
		Muster muster = core.GetMusterStatus();
		assertNull(muster);
		
	}
}
