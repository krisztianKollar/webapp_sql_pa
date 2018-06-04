package com.codecool.web.servlet;

import com.codecool.web.dao.ProductDao;
import com.codecool.web.dao.database.DatabaseProductDao;
import com.codecool.web.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/protected/productsservlet")
public class ProductServlet extends AbstractServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection c = getConnection(req.getServletContext())) {
            ProductDao db = new DatabaseProductDao(c);

            List<Product> products = db.findAll();

            resp.setContentType("application/json");
            sendMessage(resp, HttpServletResponse.SC_OK, products);

        } catch (SQLException e) {
            handleSqlError(resp, e);
        }
    }
}
