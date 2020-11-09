package com.dexsys.tgbot.adapters;

import com.dexsys.tgbot.domain.entities.User;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IUsersRepositoryService {

    void addUser(Message message);

    List<User> getUsers();

    void setUserPhoneNumber(Long chatId, String phoneNumber);

    void setUserBirthday(Long chatId, Date birthday);

    User getUserByPhoneNumber(String phoneNumber);

    void deleteUserByPhoneNumber(String phoneNumber);

    DateFormat getDateFormat();

}
