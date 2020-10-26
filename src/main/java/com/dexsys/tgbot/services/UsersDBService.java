package com.dexsys.tgbot.services;

import com.dexsys.tgbot.entities.User;
import com.dexsys.tgbot.exception.NotFoundException;
import com.dexsys.tgbot.repository.IUsersDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UsersDBService {

    @Autowired
    private IUsersDB usersDB;

    public void addUser(Message message) {
        long chatId = message.getChatId();
        String userName = getUserName(message);
        usersDB.addUser(new User(userName, chatId));
    }

    public Map<Long, User> getUsersMap() {
        return usersDB.getUsersMap();
    }

    public List<User> getUsers() {
        return new ArrayList<>(usersDB.getUsersMap().values());
    }

    public User getUserByPhoneNumber(String phoneNumber) {
        return getUsers().stream().filter(user -> user.getPhoneNumber().equals(phoneNumber))
                .findFirst().orElseThrow(NotFoundException::new);
    }

    public void deleteUserByPhoneNumber(String phoneNumber) {
        long chatId = getUserByPhoneNumber(phoneNumber).getChatId();
        if (getUsersMap().remove(chatId) == null) {
            throw new NotFoundException();
        }
    }

    public DateFormat getDateFormat() {
        return usersDB.getDateFormat();
    }

    private String getUserName(Message msg) {
        String userName;
        if ((userName = msg.getChat().getUserName()) == null) {
            userName = msg.getChat().getFirstName() + " " + msg.getChat().getLastName();
        }
        return userName;
    }
}
