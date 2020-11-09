package com.dexsys.tgbot.domain.services;

import com.dexsys.tgbot.adapters.IUsersRepositoryService;
import com.dexsys.tgbot.domain.entities.User;
import com.dexsys.tgbot.app.exception.NotFoundException;
import com.dexsys.tgbot.adapters.IUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UsersRepositoryService implements IUsersRepositoryService {

    @Autowired
    private IUsersRepository usersDB;

    @Value("${bot.dateFormat}")
    private String dateFormat;

    public void addUser(Message message) {
        long chatId = message.getChatId();
        String userName = getUserName(message);
        if (!usersDB.existsById(chatId)) {
            usersDB.save(new User(userName, chatId));
        }
    }

    public List<User> getUsers() {
        return usersDB.findAll();
    }

    @Override
    public void setUserPhoneNumber(Long chatId, String phoneNumber) {
        User user = usersDB.findById(chatId).get();     // not to be null
        user.setPhoneNumber(phoneNumber);
        usersDB.save(user);
    }

    @Override
    public void setUserBirthday(Long chatId, Date birthday) {
        User user = usersDB.findById(chatId).get();     // not to be null
        user.setBirthdate(birthday);
        usersDB.save(user);
    }

    public User getUserByPhoneNumber(String phoneNumber) {
        return getUsers().stream().filter(user -> user.getPhoneNumber().equals(phoneNumber))
                .findFirst().orElseThrow(NotFoundException::new);
    }

    public void deleteUserByPhoneNumber(String phoneNumber) {
        long chatId = getUserByPhoneNumber(phoneNumber).getChatId();
        if (usersDB.existsById(chatId)) {
            usersDB.deleteById(chatId);
        } else {
            throw new NotFoundException();
        }
    }

    public DateFormat getDateFormat() {
        return usersDB.getDateFormat(dateFormat);
    }

    private String getUserName(Message msg) {
        String userName;
        if ((userName = msg.getChat().getUserName()) == null) {
            userName = msg.getChat().getFirstName() + " " + msg.getChat().getLastName();
        }
        return userName;
    }
}
