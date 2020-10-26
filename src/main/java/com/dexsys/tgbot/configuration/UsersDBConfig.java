package com.dexsys.tgbot.configuration;

import com.dexsys.tgbot.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;

@Configuration
@PropertySource("classpath:bot.properties")
public class UsersDBConfig {

    @Bean
    public DateFormat setDateFormat(@Value("${bot.dateFormat}") String pattern) {
        return new SimpleDateFormat(pattern);
    }

    @Bean
    public HashMap<Long, User> newHashMap() {
        return new HashMap<>();
    }
}
