package com.khelenyuk.dao;

import com.khelenyuk.model.User;

import java.util.List;

public interface UserDAO extends CrudDAO<User> {
    User get(String login);

    boolean updateStatus(User user);

    List<User> getAll(int limit, int offset);

    int getUsersCount();
}
