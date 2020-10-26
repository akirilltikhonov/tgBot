package com.dexsys.tgbot.handlers.impl;

import com.dexsys.tgbot.BotState;
import com.dexsys.tgbot.handlers.BotMessageHandler;
import com.dexsys.tgbot.services.MainMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class SetUserPhoneNumberHandler implements BotMessageHandler {

    @Autowired
    private MainMenuService mainMenuService;

    @Autowired
    private com.dexsys.tgbot.services.UsersDBService UsersDBService;

    @Override
    public BotState getHandlerState() {
        return BotState.SET_PHONE_NUMBER;
    }

    @Override
    public SendMessage handle(Message message) {

        SendMessage replyMessage = new SendMessage();
        String phoneNumber = message.getText().replaceAll("\\s+", "");

        if (phoneNumber.matches("[\\d]{10}")) {
            UsersDBService.getUsersMap().get(getUserName(message)).setPhoneNumber(phoneNumber);
            replyMessage.setText("ok");
        } else {
            replyMessage.setText("Incorrect phone number. Try again");
        }

        mainMenuService.setKeyboard(replyMessage);
        mainMenuService.setAllowedEnterPhoneNumber(false);
        return replyMessage;
    }
}
