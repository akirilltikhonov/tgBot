package com.dexsys.tgbot.adapters;

import com.dexsys.tgbot.app.exception.NotFoundException;
import com.dexsys.tgbot.domain.entities.User;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface IUsersRepositoryService {

    void addUser(Message message);

    Map<Long, User> getUsersMap();

    List<User> getUsers();

    User getUserByPhoneNumber(String phoneNumber);

    void deleteUserByPhoneNumber(String phoneNumber);

    DateFormat getDateFormat();

}
