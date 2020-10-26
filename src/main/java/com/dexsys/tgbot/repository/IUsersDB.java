package com.dexsys.tgbot.repository;

import com.dexsys.tgbot.entities.User;

import java.text.DateFormat;
import java.util.Map;

public interface IUsersDB {
    DateFormat getDateFormat();
    Map<Long, User> getUsersMap();
    void addUser(User user);
}
