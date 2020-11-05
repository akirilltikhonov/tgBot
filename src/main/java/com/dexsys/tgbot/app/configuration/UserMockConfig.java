package com.dexsys.tgbot.app.configuration;

import com.dexsys.tgbot.adapters.IUserClient;
import com.dexsys.tgbot.app.userMock.UserMockClient;
import com.dexsys.tgbot.app.userMock.UserMockClientTest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:bot.properties")
public class UserMockConfig {

    @Bean
    public IUserClient iUserClient(@Value("${bot.testUserMock}") boolean testUserMock) {
        return testUserMock ? new UserMockClientTest() : new UserMockClient();
    }

}
