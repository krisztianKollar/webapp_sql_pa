package com.codecool.web.dao.database;

import com.codecool.web.dao.OrderDao;
import com.codecool.web.model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class DatabaseOrderDao extends AbstractDao implements OrderDao {

    public DatabaseOrderDao(Connection connection) {
        super(connection);
    }


    @Override
    public List<Order> findOrdersByUser(String userId) throws SQLException {
        String sql = "select od.order_id, sum(quantity) as numberofproductordered, " +
            "sum(unit_price*quantity) as totalprice " +
            "from order_details as od " +
            "join orders as o on od.order_id=o.order_id " +
            "where o.customer_id = ? " +
            "group by od.order_id;";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, userId);
            ps.executeQuery();
            ResultSet rs = ps.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (rs.next()) {
                orders.add(fetchorder(rs));
            }
            return orders;
        }
    }

    @Override
    public List<Order> findDetailedOrdersByUser(String userId) throws SQLException {
        String sql = "select od.product_id, p.product_name, od.unit_price " +
            "from order_details as od " +
            "join products as p on od.product_id = p.product_id " +
            "join orders as o on od.order_id=o.order_id " +
            "where o.customer_id = ? " +
            "group by od.order_id, od.product_id, p.product_name, od.unit_price;";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, userId);
            ps.executeQuery();
            ResultSet rs = ps.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (rs.next()) {
                orders.add(fetchdetailedorder(rs));
            }
            return orders;
        }
    }

    private Order fetchorder(ResultSet resultSet) throws SQLException {
        int orderId = resultSet.getInt("order_id");
        int numberOfProductsOrdered = resultSet.getInt("numberofproductordered");
        float totalPrice = resultSet.getFloat("totalprice");
        return new Order(orderId, numberOfProductsOrdered, totalPrice);
    }

    private Order fetchdetailedorder(ResultSet resultSet) throws SQLException {
        int productId = resultSet.getInt("product_id");
        String productName = resultSet.getString("product_name");
        float unitPrice = resultSet.getFloat("unit_price");
        return new Order(productId, productName, unitPrice);
    }
}
