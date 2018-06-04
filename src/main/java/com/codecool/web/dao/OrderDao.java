package com.codecool.web.dao;

import com.codecool.web.model.Order;
import com.codecool.web.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao {

    List<Order> findOrdersByUser(String userId) throws SQLException;

    List<Order> findDetailedOrdersByUser(String userId) throws SQLException;


}
