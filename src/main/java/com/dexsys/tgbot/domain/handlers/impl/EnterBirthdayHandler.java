package com.dexsys.tgbot.domain.handlers.impl;

import com.dexsys.tgbot.domain.BotState;
import com.dexsys.tgbot.domain.handlers.BotMessageHandler;
import com.dexsys.tgbot.domain.services.MainMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;

@Component
public class EnterBirthdayHandler implements BotMessageHandler {

    @Autowired
    private MainMenuService mainMenuService;

    @Override
    public BotState getHandlerState() {
        return BotState.ENTER_BIRTHDAY;
    }

    @Override
    public SendMessage handle(Message message) {
        SendMessage replyMessage = new SendMessage();
        replyMessage.setText("Enter your date of birth in format 'dd.MM.yyyy'. For example: 01.01.2001");
        replyMessage.setReplyMarkup(new ReplyKeyboardRemove());
        mainMenuService.setAllowedEnterBirthday(true);
        return replyMessage;
    }
}
