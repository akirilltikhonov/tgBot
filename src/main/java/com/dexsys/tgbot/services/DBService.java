package com.dexsys.tgbot.services;

import com.dexsys.tgbot.entities.User;
import com.dexsys.tgbot.repository.IUserDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.text.DateFormat;
import java.util.Map;

@Service
public class DBService {

    @Autowired
    private IUserDB usersDB;

    public void addUser(Message message) {
        long chatId = message.getChatId();
        String userName = getUserName(message);
        usersDB.addUser(new User(userName, chatId));
    }

    public Map<String, User> getUsersMap() {
        return usersDB.getUsersMap();
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
