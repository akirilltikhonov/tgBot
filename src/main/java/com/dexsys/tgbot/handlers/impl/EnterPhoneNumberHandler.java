package com.dexsys.tgbot.handlers.impl;

import com.dexsys.tgbot.BotState;
import com.dexsys.tgbot.handlers.BotMessageHandler;
import com.dexsys.tgbot.services.MainMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;

@Component
public class EnterPhoneNumberHandler implements BotMessageHandler {

    @Autowired
    private MainMenuService mainMenuService;

    @Override
    public BotState getHandlerState() {
        return BotState.ENTER_PHONE_NUMBER;
    }

    @Override
    public SendMessage handle(Message message) {
        SendMessage replyMessage = new SendMessage();
        replyMessage.setText("Enter your phone number. Only number after +7. For example: 912 123 45 67");
        replyMessage.setReplyMarkup(new ReplyKeyboardRemove());
        mainMenuService.setAllowedEnterPhoneNumber(true);
        return replyMessage;
    }
}
