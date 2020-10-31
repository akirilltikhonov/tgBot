package com.dexsys.tgbot.adapters;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface ITelegramFacade {

    SendMessage handleUpdate(Update update);

    SendMessage handleInputMessage(Message message);
}
