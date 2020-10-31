package com.dexsys.tgbot.domain.handlers.impl;

import com.dexsys.tgbot.domain.BotState;
import com.dexsys.tgbot.domain.handlers.BotMessageHandler;
import com.dexsys.tgbot.domain.services.MainMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class MainMenuHandler implements BotMessageHandler {

    @Autowired
    private MainMenuService mainMenuService;

    @Override
    public BotState getHandlerState() {
        return BotState.MAIN_MENU;
    }

    @Override
    public SendMessage handle(Message message) {
        SendMessage replyMessage = new SendMessage();
        replyMessage.setText(message.getText());
        mainMenuService.setKeyboard(replyMessage);
        return replyMessage;
    }
}
