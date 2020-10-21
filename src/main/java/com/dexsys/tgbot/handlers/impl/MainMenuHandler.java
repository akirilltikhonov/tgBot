package com.dexsys.tgbot.handlers.impl;

import com.dexsys.tgbot.BotState;
import com.dexsys.tgbot.handlers.MessageHandler;
import com.dexsys.tgbot.services.MainMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class MainMenuHandler implements MessageHandler {

    @Autowired
    private MainMenuService mainMenuService;

    @Override
    public SendMessage handle(Message message) {
        SendMessage replyMessage = new SendMessage();
        replyMessage.setText(message.getText());
        mainMenuService.setKeyboard(replyMessage);
        return replyMessage;
    }

    @Override
    public BotState getHandlerState() {
        return BotState.MAIN_MENU;
    }

}