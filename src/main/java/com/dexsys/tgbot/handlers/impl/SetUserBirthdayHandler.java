package com.dexsys.tgbot.handlers.impl;

import com.dexsys.tgbot.BotState;
import com.dexsys.tgbot.handlers.BotMessageHandler;
import com.dexsys.tgbot.services.UsersRepositoryService;
import com.dexsys.tgbot.services.MainMenuService;
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
            UsersRepositoryService.getUsersMap().get(message.getChatId())
                    .setBirthDate(UsersRepositoryService.getDateFormat().parse(message.getText()));
            replyMessage.setText("ok");
        } catch (ParseException e) {
            replyMessage.setText("Incorrect date. Try again");
        }
        mainMenuService.setKeyboard(replyMessage);
        mainMenuService.setAllowedEnterBirthday(false);
        return replyMessage;
    }
}
