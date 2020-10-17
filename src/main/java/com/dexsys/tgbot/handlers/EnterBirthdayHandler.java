package com.dexsys.tgbot.handlers;

import com.dexsys.tgbot.BotState;
import com.dexsys.tgbot.services.MainKeyboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;

@Component
public class EnterBirthdayHandler implements MessageHandler {

    @Autowired
    private MainKeyboardService mainKeyboardService;

    @Override
    public SendMessage handle(Message message) {
        SendMessage replyMessage = new SendMessage();
        replyMessage.setText("Enter your date of birth");
        replyMessage.setReplyMarkup(new ReplyKeyboardRemove());
        mainKeyboardService.setAllowedEnterBirthday(true);
        return replyMessage;
    }

    @Override
    public BotState getHandlerState() {
        return BotState.ENTER_BIRTHDAY;
    }
}
