package com.indus.training.persist.dao;

import java.util.List;

import com.indus.training.persist.entity.Orders;
import com.indus.training.persist.exception.CustomerException;

public interface IQueryManager {

	public List<Orders> read(int id) throws CustomerException;

	public List<Object[]> read() throws CustomerException;
}
