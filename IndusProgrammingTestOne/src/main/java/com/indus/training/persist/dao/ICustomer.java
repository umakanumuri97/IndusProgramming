package com.indus.training.persist.dao;

import java.util.List;

import com.indus.training.persist.entity.CustomerEntity;
import com.indus.training.persist.exception.IndusProgrammingTestOneException;

public interface ICustomer {
	Boolean createCustomer(CustomerEntity customer) throws IndusProgrammingTestOneException;

	Boolean updateCustomer(CustomerEntity customer) throws IndusProgrammingTestOneException;

	Boolean deleteCustomer(CustomerEntity customer) throws IndusProgrammingTestOneException;

	List<CustomerEntity> getAllCustomers() throws IndusProgrammingTestOneException;

	List<Object[]> executeSpecificJPQLQuery(String jpql) throws IndusProgrammingTestOneException;

	List<CustomerEntity> executeJPQLQuery(String jpql) throws IndusProgrammingTestOneException;
}
