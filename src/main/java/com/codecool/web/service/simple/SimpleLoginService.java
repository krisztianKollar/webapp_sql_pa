package com.codecool.web.service.simple;

import com.codecool.web.dao.UserDao;
import com.codecool.web.model.User;
import com.codecool.web.service.LoginService;
import com.codecool.web.service.exception.ServiceException;

import java.sql.SQLException;

public final class SimpleLoginService implements LoginService {

    private final UserDao userDao;

    public SimpleLoginService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User loginUser(String id) throws SQLException, ServiceException {
        try {
            User user = userDao.findById(id);
            if (user == null) {
                throw new ServiceException("Bad login");
            }
            return user;
        } catch (IllegalArgumentException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }
}
