package com.example.models;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
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
	
	
	public AMSCore Setup()
	{
	AMSCore core;
    	
    	
    	
    	
    	
    	List<Person> employeeList = new ArrayList<Person>();
    	
    	Person samplePerson = new Person("Test","Person",999,true);
    	Person sampleBoss = new Person("Cancel","Boss",9,true);
    	
    	employeeList.add(samplePerson);
    	employeeList.add(sampleBoss);
    	
    	
    	//for (Person pp : employeeList)
    	//	System.out.println(pp);
    	
    	
    	core = new AMSCore(employeeList);
    	return core;
	}
	
	//Test that an employee can report in, and that his/her status it here
	@Test 
	public void TestReportIn2(){		
		AMSCore core = Setup();
	}
	
	//Test that our boss can cancel, but no one else can
		@Test 
		public void TestCancel2(){		
			//Then try to cancel with The boss
			
			AMSCore core = Setup();
			DoMuster(core);
			Person sampleBoss = core.GetPersonByID(9);
			System.out.println("Canceling current Muster");
			core.Cancel(sampleBoss);
			Muster muster = core.GetMusterStatus();		
			assertEquals(false, muster.isActive());
			
			
			//create a muster
	//		DoMuster(core);
		//	Person sampleBoss = core.GetPersonByID(9);
			//core.Muster(sampleBoss, "What is your current status?");
			//Muster muster = core.GetMusterStatus();
	
			
		}

		private void DoMuster(AMSCore core) {
			Person samplePerson = core.GetPersonByID(999);
			core.Muster(samplePerson, "Please Report Your Status.");
	    	Muster muster = core.GetMusterStatus();
		}
		
		//Test that no one can report in when there is no muster
		@Test(expected=IllegalArgumentException.class) 
		public void TestReportInNoMuster(){		
			AMSCore core = Setup();
			MusterStatus testMusterPerson2 = new MusterStatus(core.GetPersonByID(4), MusterStatus.StatusCodes.TDY);
	    	core.ReportIn(testMusterPerson2);												
	    	
	    	{
				throw new IllegalArgumentException("There is no muster to cancel.");
			}
			
		}
	
	@Test
	public void TestRightNumberOfEmployees()
	{
		AMSCore core = Setup();
		//Make sure the size is what we expect
		core.GetPeople().size();
		assertEquals(12,core.GetPeople().size());
		
		
	}
	
	
	@Test
	public void TestDidMyMusterWork()
	{
		AMSCore core = Setup();
		DoMuster(core);
		assertEquals(core.GetMusterStatus().getMessage(),"Please Report Your Status.");
    	
		Date when = core.GetMusterStatus().getdate();
		Date now = new Date();
		
		//check the date is the same
		assertEquals(when.getDay(),now.getDay());
		assertEquals(when.getYear(),now.getYear());
		Person samplePerson = core.GetPersonByID(999);
		Muster muster = core.GetMusterStatus();
		assertEquals(samplePerson,muster.getWho());
		assertEquals(muster.isActive(),true);
	}
	
	@Test
	public void TestIsDefaultMusterCancelled()
	{
		AMSCore core = Setup();
		Muster muster = core.GetMusterStatus();
		assertEquals(muster.isActive(),false);
	}
	
	@Test
	public void TestGetPersonByID()
	{
		
		AMSCore core = Setup();
		//Try a bad number
		Person person = core.GetPersonByID(-1);
		assertNull(person);
		//Try another number that doesn't exist
		person = core.GetPersonByID(818);
		assertNull(person);
		person = core.GetPersonByID(2);
		assertEquals(person.getFirstName(),"Jim");
		assertEquals(person.getLastName(),"Doe");
		assertEquals(person.isSupervisor(),false);
		assertEquals(person.getIdNumber(),2);
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
