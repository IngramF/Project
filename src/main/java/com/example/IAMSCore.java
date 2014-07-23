package com.example;

import java.util.List;

import com.example.models.MusterStatus;
import com.example.models.Person;
import com.example.models.Muster;

/***
 * The core AMS Business logic interface. This performs high level operations of the AMS system as atomic transactions.
 * @author matthewgalligan
 *
 */
public interface IAMSCore {	
	/***
	 * Create a new muster, Authenticated users only
	 */
	void Muster(Person initiator,String message);
	/***
	 * Cancel the current muster. Authenticated users only
	 */
	void Cancel(Person canceller);
	/***
	 * Get the current muster status, or absence of muster
	 */
	Muster GetMusterStatus();
	
	/***
	 * Report someone's muster status.
	 * @return 
	 */
	void ReportIn(MusterStatus status);
	/***
	 * Lookup a person by ID, or return null if not found
	 * @param id Numeric ID of the person
	 * @return
	 */
	Person GetPeronByID(int id);
	
	/***
	 * Return all people in the system, so that a user can select themselves by ID
	 * If no people are in the system, returns an empty list
	 * @return
	 */
	List<Person> GetPeople();
	
}