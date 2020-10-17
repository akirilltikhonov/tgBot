package com.dexsys.tgbot.handlers;

import com.dexsys.tgbot.BotState;
import com.dexsys.tgbot.repository.UserDB;
import com.dexsys.tgbot.services.MainKeyboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.text.ParseException;

@Component
public class SetUserBirthdayHandler implements MessageHandler {

    @Autowired
    private UserDB userDB;

    @Autowired
    private MainKeyboardService mainKeyboardService;

    @Override
    public SendMessage handle(Message message) {
        SendMessage replyMessage = new SendMessage();
        try {
            userDB.getUsers().get(getUserName(message)).setBirthDate(userDB.getDateFormat().parse(message.getText()));
            replyMessage.setText("ok");
        } catch (ParseException e) {
            replyMessage.setText("Incorrect date. Try again");
        }
        mainKeyboardService.setKeyboard(replyMessage);
        mainKeyboardService.setAllowedEnterBirthday(false);
        return replyMessage;
    }

    @Override
    public BotState getHandlerState() {
        return BotState.SET_USER_BIRTHDAY;
    }
}
