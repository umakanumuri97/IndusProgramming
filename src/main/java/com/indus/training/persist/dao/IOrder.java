package com.indus.training.persist.dao;

import java.util.List;

import com.indus.training.persist.entity.Orders;
import com.indus.training.persist.exception.JPAException;

public interface IOrder {

	// Creates a new order in the database.
	Boolean create(Orders order) throws JPAException;

	// Updates an existing order in the database.

	Boolean update(int orderId, Orders updateOrders) throws JPAException;

	// Retrieves all orders for a specific customer.

	List<Orders> read(int customerId) throws JPAException;
}
