package com.indus.training.persist.dao;

import com.indus.training.persist.entity.Orders;
import com.indus.training.persist.exception.CustomerException;

public interface IOrderManager {

	public Boolean insert(Orders obj) throws CustomerException;

	public Boolean updateTotalAmount(int id, Orders obj) throws CustomerException;

	public Boolean delete(int id) throws CustomerException;
}
