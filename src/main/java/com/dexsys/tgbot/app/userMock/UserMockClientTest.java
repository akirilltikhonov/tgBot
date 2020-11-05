package com.dexsys.tgbot.app.userMock;

import com.dexsys.tgbot.adapters.IUserClient;
import org.springframework.http.HttpMethod;

import java.util.List;
import java.util.Set;

public class UserMockClientTest implements IUserClient {
    @Override
    public List<UserDto> getUsers() {
        throw new RuntimeException("Method not supported");
    }

    @Override
    public UserDto getUser(String userId) {
        throw new RuntimeException("Method not supported");
    }

    @Override
    public Set<HttpMethod> getUserOptions(String userId) {
        throw new RuntimeException("Method not supported");
    }

    @Override
    public void generateUser() {
        throw new RuntimeException("Method not supported");
    }

    @Override
    public void createUser(UserDto userDtoPost) {
        throw new RuntimeException("Method not supported");
    }
}
