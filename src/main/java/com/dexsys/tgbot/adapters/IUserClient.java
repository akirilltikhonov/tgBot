package com.dexsys.tgbot.adapters;

import com.dexsys.tgbot.domain.dto.UserDtoDB;
import org.springframework.http.HttpMethod;

import java.util.List;
import java.util.Set;

public interface IUserClient {

    List<UserDtoDB> getUsers();

    UserDtoDB getUser(String userId);

    Set<HttpMethod> getUserOptions(String userId);

    void generateUser();

    void createUser(UserDtoDB userDtoDBPost);

}
