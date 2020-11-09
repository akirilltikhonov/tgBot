package com.dexsys.tgbot.domain.handlers.impl;

import com.dexsys.tgbot.adapters.IUsersRepositoryService;
import com.dexsys.tgbot.domain.BotState;
import com.dexsys.tgbot.domain.handlers.BotMessageHandler;
import com.dexsys.tgbot.domain.services.MainMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class SetUserPhoneNumberHandler implements BotMessageHandler {

    @Autowired
    private MainMenuService mainMenuService;

    @Autowired
    private IUsersRepositoryService usersRepositoryService;

    @Override
    public BotState getHandlerState() {
        return BotState.SET_PHONE_NUMBER;
    }

    @Override
    public SendMessage handle(Message message) {

        SendMessage replyMessage = new SendMessage();
        String phoneNumber = message.getText().replaceAll("\\s+", "");

        if (phoneNumber.matches("[\\d]{10}")) {
            usersRepositoryService.setUserPhoneNumber(message.getChatId(), phoneNumber);
            replyMessage.setText("ok");
        } else {
            replyMessage.setText("Incorrect phone number. Try again");
        }

        mainMenuService.setKeyboard(replyMessage);
        mainMenuService.setAllowedEnterPhoneNumber(false);
        return replyMessage;
    }
}
