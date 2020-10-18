package com.dexsys.tgbot.handlers;

import com.dexsys.tgbot.BotState;
import com.dexsys.tgbot.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class UserListHandler implements MessageHandler {

    @Autowired
    private DBService DBService;

    @Override
    public SendMessage handle(Message message) {
        SendMessage replyMessage = new SendMessage();
        StringBuffer stringBuffer = new StringBuffer();
        DBService.getUsers().values().stream().
                forEach(x ->
                        stringBuffer.append(String.format("User: %s; ChatId: %s; Birthday: %s\n",
                                x.getUserName(), x.getChatId(),
                                x.getBirthDate() == null ? "no info" : DBService.getDateFormat().format(x.getBirthDate()))));
        replyMessage.setText(stringBuffer.toString());
        return replyMessage;
    }

    @Override
    public BotState getHandlerState() {
        return BotState.SHOW_USERS;
    }
}
