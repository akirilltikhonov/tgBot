package com.dexsys.tgbot.repository;

import com.dexsys.tgbot.entities.User;
import lombok.Getter;
import org.springframework.stereotype.Repository;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.util.Map;

@Repository
@Getter
public class UserDB {

    @Resource(name = "setDateFormat")
    private DateFormat dateFormat;

    @Resource(name = "newHashMap")
    private Map<String, User> users;

    public synchronized void addUser(User user) {
        if (!users.containsKey(user.getUserName())) {
            users.put(user.getUserName(), user);
        }
    }

}
