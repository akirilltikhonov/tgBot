package com.dexsys.tgbot.services;

import com.dexsys.tgbot.entities.User;
import com.dexsys.tgbot.repository.UserDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.text.DateFormat;
import java.util.Map;

@Service
public class DBService {

    @Autowired
    private UserDB userDB;

    public void addUser(Message message) {
        long chatId = message.getChatId();
        String userName = getUserName(message);
        userDB.addUser(new User(userName, chatId));
    }

    private String getUserName(Message msg) {
        String userName;
        if ((userName = msg.getChat().getUserName()) == null) {
            userName = msg.getChat().getFirstName() + " " + msg.getChat().getLastName();
        }
        return userName;
    }

    public Map<String, User> getUsers() {
        return userDB.getUsers();
    }

    public DateFormat getDateFormat() {
        return userDB.getDateFormat();
    }
}
