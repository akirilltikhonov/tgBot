package com.dexsys.tgbot.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.telegram.telegrambots.meta.TelegramBotsApi;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;

@Configuration
@PropertySource("classpath:bot.properties")
public class BirthdayBotConfig {


    @Bean
    public DateFormat setDateFormat(@Value("${bot.dateFormat}") String pattern) {
        return new SimpleDateFormat(pattern);
    }

    @Bean
    public HashMap newHashMap() {
        return new HashMap<>();
    }

    @Bean
    public TelegramBotsApi telegramBotsApi() {
        return new TelegramBotsApi();
    }
}
