package com.codecool.web.dao.database;

import com.codecool.web.dao.UserDao;
import com.codecool.web.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class DatabaseUserDao extends AbstractDao implements UserDao {

    public DatabaseUserDao(Connection connection) {
        super(connection);
    }

    public List<User> findAll() throws SQLException {
        String sql = "SELECT c.customerid, c.firstname, c.lastname, c.company, c.address, c.city, c.country, c.postalcode, " +
            "c.company, e.email as salesrepresentativecontact, count(i.customerid) as numberofpurchases, c.email FROM customer as c " +
            "inner join employee as e on c.supportrepid = e.employeeid " +
            "inner join invoice as i on i.customerid = c.customerid " +
            "group by c.customerid, c.firstname, c.lastname, c.company, c.address, c.city, c.country, " +
            "c.postalcode, c.company, e.email, c.email " +
            "order by firstname, lastname;";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(fetchuser(resultSet));
            }
            return users;
        }
    }


    @Override
    public User findById(String id) throws SQLException {
        if (id == null || "".equals(id)) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        String sql = "select customer_id, company_name, contact_name, contact_title from customers where customer_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {


                if (resultSet.next()) {
                    return fetchuser(resultSet);
                }
            }
        }
        return null;
    }

    private User fetchuser(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("customer_id");
        String companyName = resultSet.getString("company_name");
        String contactName = resultSet.getString("contact_name");
        String contactTitle = resultSet.getString("contact_title");
        return new User(id, companyName, contactName, contactTitle);
    }
}
