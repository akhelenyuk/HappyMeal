package com.khelenyuk.controller.service.impl;


import com.khelenyuk.dao.CrudDAO;
import com.khelenyuk.dao.LifestyleDAO;
import com.khelenyuk.dao.SexDAO;
import com.khelenyuk.dao.UserDAO;
import com.khelenyuk.dao.mysql.factory.DAOFactory;
import com.khelenyuk.dao.mysql.impl.LifestyleDAOImpl;
import com.khelenyuk.dao.mysql.impl.SexDAOImpl;
import com.khelenyuk.dao.mysql.impl.UserDAOImpl;
import com.khelenyuk.model.Lifestyle;
import com.khelenyuk.model.Sex;
import com.khelenyuk.model.User;
import com.khelenyuk.controller.service.IUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserServiceImpl implements IUserService {
    private static final Logger logger = LogManager.getLogger(LoginRegistrationServiceImpl.class);

    private static UserDAO userDAO = DAOFactory.getUserMethods();
    private static SexDAO sexDAO = DAOFactory.getSexMethods();
    private static LifestyleDAO lifestyleDAO = DAOFactory.getLifestyleMethods();

    private static UserServiceImpl instance = new UserServiceImpl();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public User getUser(String login) {
        return userDAO.get(login);
    }

    @Override
    public List<User> getAll(){
        return userDAO.getAll();
    }

    @Override
    public User getUser(int id) {
        return userDAO.get(id);
    }

    @Override
    public List<Sex> getSex() {
        return sexDAO.getAll();
    }

    @Override
    public List<Lifestyle> getLifestyles() {
        return lifestyleDAO.getAll();
    }

    @Override
    public boolean addUser(User user) {
        return userDAO.add(user);
    }
}
