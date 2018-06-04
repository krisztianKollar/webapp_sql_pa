package com.codecool.web.dao;

import com.codecool.web.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    User findById(String id) throws SQLException;

    List<User> findAll() throws SQLException;



}
