package com.indus.training.core.persist.dao;

import com.indus.training.persist.entity.Persons;
import com.indus.training.persist.exception.EventsException;

public interface IPersons {
	// Method to create a new person
	Boolean createPerson(Persons person) throws EventsException;
		    
	 // Method to read a person by ID
	Persons readPersonById(int personid)throws EventsException;
		    
	// Method to update an existing person
	Boolean updatePerson(Persons person)throws EventsException;
		    
	// Method to delete a person by ID
	Boolean deletePerson(int personid)throws EventsException;

}
