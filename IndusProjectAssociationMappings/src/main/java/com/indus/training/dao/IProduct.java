package com.indus.training.dao;

import java.util.List;

import com.indus.training.persist.entity.Product;

public interface IProduct {

	Boolean create(Product product) throws Exception;

	Product read(Long id) throws Exception;

	Boolean update(Product product) throws Exception;

	Boolean delete(Long id) throws Exception;

	List<Product> listAll() throws Exception;
	
	

}
