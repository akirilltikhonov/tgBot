package com.dexsys.tgbot.adapters;

import com.dexsys.tgbot.domain.entities.User;

import java.text.DateFormat;
import java.util.Map;

public interface IUsersRepository {
    DateFormat getDateFormat();
    Map<Long, User> getUsersMap();
    void addUser(User user);
}
