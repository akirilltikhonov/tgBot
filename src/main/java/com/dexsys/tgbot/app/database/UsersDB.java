package com.dexsys.tgbot.app.database;

import com.dexsys.tgbot.domain.entities.User;
import com.dexsys.tgbot.adapters.IUsersRepository;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.util.Map;

@Repository
@Getter
public class UsersDB implements IUsersRepository {

    @Resource(name = "setDateFormat")
    private DateFormat dateFormat;

    @Resource(name = "newHashMap")
    private Map<Long, User> usersMap;

    public void addUser(User user) {
        if (!usersMap.containsKey(user.getChatId())) {
            usersMap.put(user.getChatId(), user);
        }
    }

}
