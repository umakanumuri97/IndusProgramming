package com.indus.training.persist.dao;

import com.indus.training.persist.entity.Orders;
import com.indus.training.persist.exception.OrderException;

public interface IOrders {
	
	public Boolean create(Orders order)throws OrderException;
	
	public int read(int id)throws OrderException;
	
	public Boolean update(int id,Orders updateorder)throws OrderException;
	
	public Boolean delete(int id)throws OrderException;

}
