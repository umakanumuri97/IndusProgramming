package com.indus.training.dao;

import java.util.List;

import com.indus.training.persist.entity.ImmutablePerson;

public interface IIPerson {
	// Create a new ImmutablePerson
	Boolean create(ImmutablePerson person) throws Exception;

	// Read an ImmutablePerson by ID
	ImmutablePerson read(Long id) throws Exception;

	// Update an existing ImmutablePerson
	Boolean update(ImmutablePerson person) throws Exception;

	// Delete an ImmutablePerson by ID
	Boolean delete(Long id) throws Exception;

	// List all ImmutablePersons
	List<ImmutablePerson> listAll() throws Exception;
}
