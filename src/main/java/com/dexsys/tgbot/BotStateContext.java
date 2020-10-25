package com.dexsys.tgbot;

import com.dexsys.tgbot.handlers.BotMessageHandler;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BotStateContext {

    private final Map<BotState, BotMessageHandler> messageHandlers = new HashMap<>();

    public BotStateContext(List<BotMessageHandler> botMessageHandlers) {
        botMessageHandlers.forEach(handler -> this.messageHandlers.put(handler.getHandlerState(), handler));
    }

    public SendMessage setState(BotState currentState, Message message) {
        BotMessageHandler currentHandler = findMessageHandler(currentState);
        return currentHandler.handle(message);
    }

    public BotMessageHandler findMessageHandler(BotState currentState) {
        return messageHandlers.get(currentState);
    }
}
