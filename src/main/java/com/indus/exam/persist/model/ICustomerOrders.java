package com.indus.exam.persist.model;

import com.indus.exam.persist.excp.CustomerOrdersException;

import java.util.List;

import com.indus.exam.persist.entity.Orders;

public interface ICustomerOrders {
	
	public Boolean deleteCustomer (int cId) throws CustomerOrdersException;
	
	public Boolean updateOrder (int oId, double totAmount) throws CustomerOrdersException;
	
	public List<Orders> retrieveOrdersforCustomer ();
	
	public List<Object[]> calculateTotalOrdersAndTotalAmountForCustomer ();
	

}