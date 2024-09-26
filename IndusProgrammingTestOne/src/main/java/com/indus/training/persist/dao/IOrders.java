package com.indus.training.persist.dao;

import java.util.List;

import com.indus.training.persist.entity.OrderEntity;
import com.indus.training.persist.exception.IndusProgrammingTestOneException;

public interface IOrders {
	Boolean createOrder(OrderEntity order) throws IndusProgrammingTestOneException;

	Boolean updateOrder(OrderEntity order) throws IndusProgrammingTestOneException;

	Boolean deleteOrder(OrderEntity order) throws IndusProgrammingTestOneException;

	List<OrderEntity> getAllOrders() throws IndusProgrammingTestOneException;

	List<Double> executeSpecificJPQLQuery(String jpql, double orderTotalAmount) throws IndusProgrammingTestOneException;

	List<OrderEntity> executeJPQLQuery(String jpql, int customerId) throws IndusProgrammingTestOneException;
}
