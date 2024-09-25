package com.indus.training.dao;

import com.indus.training.persist.entity.Employee;

public interface IEmployee {
	
	public Boolean create(Employee employee) throws Exception;

	public Employee read(int id) throws Exception;

	public Boolean delete(int id);

	Employee findByName(String name);

	Boolean update(Employee employee);

}
