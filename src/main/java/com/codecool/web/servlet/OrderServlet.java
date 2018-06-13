package com.codecool.web.servlet;

import com.codecool.web.dao.OrderDao;
import com.codecool.web.dao.database.DatabaseOrderDao;
import com.codecool.web.model.Order;
import com.codecool.web.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/protected/ordersservlet")
public class OrderServlet extends AbstractServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection c = getConnection(req.getServletContext())) {
            OrderDao orderDao = new DatabaseOrderDao(c);
            String customerId = ((User)req.getSession().getAttribute("user")).getId();
            List<Order> orders = orderDao.findOrdersByUser(customerId);

            resp.setContentType("application/json");
            sendMessage(resp, HttpServletResponse.SC_OK, orders);

        } catch (SQLException e) {
            handleSqlError(resp, e);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection c = getConnection(req.getServletContext())) {
            OrderDao orderDao = new DatabaseOrderDao(c);
            String customerId = ((User)req.getSession().getAttribute("user")).getId();
            List<Order> orders = orderDao.findDetailedOrdersByUser(customerId);

            resp.setContentType("application/json");
            sendMessage(resp, HttpServletResponse.SC_OK, orders);

        } catch (SQLException e) {
            handleSqlError(resp, e);
        }
    }
}

