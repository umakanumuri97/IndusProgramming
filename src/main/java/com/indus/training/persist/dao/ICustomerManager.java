package com.indus.training.persist.dao;

import com.indus.training.persist.entity.Customer;
import com.indus.training.persist.exception.CustomerException;

public interface ICustomerManager {

	public Boolean insert(Customer obj) throws CustomerException;

	public Customer read(int id) throws CustomerException;
	
	public Boolean delete(int id) throws CustomerException;
}
