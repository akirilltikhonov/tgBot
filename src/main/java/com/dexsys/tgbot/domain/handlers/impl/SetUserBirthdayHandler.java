package com.dexsys.tgbot.domain.handlers.impl;

import com.dexsys.tgbot.domain.BotState;
import com.dexsys.tgbot.domain.handlers.BotMessageHandler;
import com.dexsys.tgbot.domain.services.UsersRepositoryService;
import com.dexsys.tgbot.domain.services.MainMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.text.ParseException;

@Component
public class SetUserBirthdayHandler implements BotMessageHandler {

    @Autowired
    private MainMenuService mainMenuService;

    @Autowired
    private UsersRepositoryService UsersRepositoryService;

    @Override
    public BotState getHandlerState() {
        return BotState.SET_USER_BIRTHDAY;
    }

    @Override
    public SendMessage handle(Message message) {

        SendMessage replyMessage = new SendMessage();
        try {
            UsersRepositoryService.setUserBirthday(
                    message.getChatId(),
                    UsersRepositoryService.getDateFormat().parse(message.getText())
            );
            replyMessage.setText("ok");
        } catch (ParseException e) {
            replyMessage.setText("Incorrect date. Try again");
        }
        mainMenuService.setKeyboard(replyMessage);
        mainMenuService.setAllowedEnterBirthday(false);
        return replyMessage;
    }
}
