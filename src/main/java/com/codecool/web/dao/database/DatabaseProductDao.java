package com.codecool.web.dao.database;

import com.codecool.web.dao.ProductDao;
import com.codecool.web.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class DatabaseProductDao extends AbstractDao implements ProductDao {

    public DatabaseProductDao(Connection connection) {
        super(connection);
    }

    public List<Product> findAll() throws SQLException {
        String sql = "select p.product_id, p.product_name, p.unit_price, " +
            "p.units_in_stock, c.category_name, s.company_name " +
            "from products as p " +
            "join categories as c on p.category_id = c.category_id " +
            "join suppliers as s on s.supplier_id = p.supplier_id;";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                products.add(fetchproduct(resultSet));
            }
            return products;
        }
    }


    private Product fetchproduct(ResultSet resultSet) throws SQLException {
        int productId = resultSet.getInt("product_id");
        String productName = resultSet.getString("product_name");
        float unitPrice = resultSet.getFloat("unit_price");
        int unitsInStock = resultSet.getInt("units_in_stock");
        String categoryName = resultSet.getString("category_name");
        String supplierName = resultSet.getString("company_name");
        return new Product(productId, productName, unitPrice, unitsInStock, categoryName, supplierName);
    }
}
