package com.dexsys.tgbot.handlers.impl;

import com.dexsys.tgbot.BotState;
import com.dexsys.tgbot.handlers.MessageHandler;
import com.dexsys.tgbot.services.DBService;
import com.dexsys.tgbot.services.MainMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SetUserBirthdayHandler implements MessageHandler {

    @Autowired
    private MainMenuService mainMenuService;

    @Autowired
    private DBService DBService;

    List<User> users = new ArrayList<>();

    @Override
    public BotState getHandlerState() {
        return BotState.SET_USER_BIRTHDAY;
    }

    @Override
    public SendMessage handle(Message message) {

        SendMessage replyMessage = new SendMessage();
        try {
            DBService.getUsersMap().get(getUserName(message)).setBirthDate(DBService.getDateFormat().parse(message.getText()));
            replyMessage.setText("ok");
        } catch (ParseException e) {
            replyMessage.setText("Incorrect date. Try again");
        }
        mainMenuService.setKeyboard(replyMessage);
        mainMenuService.setAllowedEnterBirthday(false);
        return replyMessage;
    }

}
