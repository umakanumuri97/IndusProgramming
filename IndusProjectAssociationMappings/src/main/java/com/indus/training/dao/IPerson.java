package com.indus.training.dao;

import com.indus.training.persist.entity.Person;

import java.util.List;

public interface IPerson {
    // Create a new Person
    Boolean create(Person person) throws Exception;

    // Read a Person by ID
    Person read(Long id) throws Exception;

    // Update an existing Person
    Boolean update(Person person) throws Exception;

    // Delete a Person by ID
    Boolean delete(Long id) throws Exception;

    // List all Persons
    List<Person> listAllPersons() throws Exception;
}
