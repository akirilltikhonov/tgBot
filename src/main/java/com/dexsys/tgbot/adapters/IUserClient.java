package com.dexsys.tgbot.adapters;

import com.dexsys.tgbot.app.userMock.UserDto;
import org.springframework.http.HttpMethod;

import java.util.List;
import java.util.Set;

public interface IUserClient {

    List<UserDto> getUsers();

    UserDto getUser(String userId);

    Set<HttpMethod> getUserOptions(String userId);

    void generateUser();

    void createUser(UserDto userDtoPost);

}
