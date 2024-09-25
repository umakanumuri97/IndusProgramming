package com.indus.training.core.persist.dao;

import com.indus.training.persist.entity.Events;
import com.indus.training.persist.exception.EventsException;

public interface IEvents {
    // Method to create a new event
    Boolean createEvent(Events event) throws EventsException ;
    
    // Method to read an event by ID
    Events readEventById(int eventid) throws EventsException;
    
    // Method to update an existing event
    Boolean updateEvent(Events event) throws EventsException;
    
    // Method to delete an event by ID
    Boolean deleteEvent(int eventid) throws EventsException;



}
