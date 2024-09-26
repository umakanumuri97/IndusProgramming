package com.indus.exam.persist.svc;

import com.indus.exam.persist.entity.Orders;
import com.indus.exam.persist.exception.CustomerException;

import java.math.BigDecimal;
import java.util.List;

public interface ICustomerDao {
    
    void updateOrderAmount(Long orderId, BigDecimal newAmount) throws CustomerException;

    List<Orders> findOrdersByCustomerId(Long customerId) throws CustomerException;

    List<Object[]> getCustomerOrderStatistics() throws CustomerException;
}
