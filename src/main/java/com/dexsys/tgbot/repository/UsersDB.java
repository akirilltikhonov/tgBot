package com.dexsys.tgbot.repository;

import com.dexsys.tgbot.entities.User;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.util.Map;

@Repository
@Getter
public class UsersDB implements IUserDB {

    @Resource(name = "setDateFormat")
    private DateFormat dateFormat;

    @Resource(name = "newHashMap")
    private Map<String, User> usersMap;

    public void addUser(User user) {
        if (!usersMap.containsKey(user.getUserName())) {
            usersMap.put(user.getUserName(), user);
        }
    }

}
