package com.indus.training.persist.dao;

import com.indus.training.persist.entity.Customer;
import com.indus.training.persist.exception.OrderException;

public interface ICustomer {
	public Boolean create(Customer customer)throws OrderException;
	
	public int read(int id)throws OrderException;
	
	public Boolean update(int id,Customer updatecustomer)throws OrderException;
	
	public Boolean delete(int id)throws OrderException;
}
