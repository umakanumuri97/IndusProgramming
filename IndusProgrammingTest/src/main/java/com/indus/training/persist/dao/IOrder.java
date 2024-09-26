package com.indus.training.persist.dao;

import com.indus.training.persist.entity.Order;
import com.indus.training.persist.exception.JPAException;

public interface IOrder {
	// method to create
	public Boolean create(Order order) throws JPAException;

//method to read order
	public Order read(int orderId) throws JPAException;

//method to update
	public Boolean update(Order order) throws JPAException;

//method to delete
	public Boolean delete(int orderId) throws JPAException;

}
