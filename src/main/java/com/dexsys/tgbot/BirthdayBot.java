package com.dexsys.tgbot;

import com.dexsys.tgbot.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.validation.constraints.AssertFalse;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class BirthdayBot extends TelegramLongPollingBot {

    @Value("${bot.username}")
    private String USERNAME;

    @Value("${bot.token}")
    private String TOKEN;

    @Resource(name = "setDateFormat")
    private DateFormat dateFormat;

    @Resource(name = "newHashMap")
    private Map<String, User> users;

    @AssertFalse
    private boolean allowedEnterBirthday;

    @Autowired
    private TelegramBotsApi telegramBotsApi;

    @PostConstruct
    public void startBirthdayBot() {
        BirthdayBot bot = this;
        try {
            telegramBotsApi.registerBot(bot);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String getBotUsername() {
        return USERNAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            execute(formResponse(update));
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public synchronized SendMessage formResponse(Update update) {

        Message msg = update.getMessage();
        String msgText = msg.getText();
        long chatId = msg.getChatId();
        String userName = getUserName(msg);

        addUser(new User(userName, chatId));

        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);

        System.out.println(users);
        System.out.println(msgText);

        if (msgText.equals("Enter my date of birth")) {
            sendMessage.setText("Enter your date of birth");
            sendMessage.setReplyMarkup(new ReplyKeyboardRemove());
            allowedEnterBirthday = true;
        } else if (allowedEnterBirthday) {
            setUserBirthday(msg, sendMessage);
        } else if (msgText.equals("List of users")) {
            showUsers(sendMessage);
        } else {
            sendMessage.setText(msgText);
            setKeyboard(sendMessage);
        }
        System.out.println(users);
        return sendMessage;
    }

    public synchronized String getUserName(Message msg) {
        String userName;
        if ((userName = msg.getChat().getUserName()) == null) {
            userName = msg.getChat().getFirstName() + " " + msg.getChat().getLastName();
        }
        return userName;
    }

    public synchronized void addUser(User user) {
        if (!users.containsKey(user.getUserName())) {
            users.put(user.getUserName(), user);
        }
    }

    public synchronized void showUsers(SendMessage sendMessage) {
        StringBuffer stringBuffer = new StringBuffer();
        users.values().stream().
                forEach(x ->
                        stringBuffer.append(String.format("User: %s; ChatId: %s; Birthday: %s\n",
                                x.getUserName(), x.getChatId(),
                                x.getBirthDate() == null ? "no info" : dateFormat.format(x.getBirthDate()))));
        sendMessage.setText(stringBuffer.toString());
    }

    public synchronized void setUserBirthday(Message msg, SendMessage sendMessage) {
        try {
            users.get(getUserName(msg)).setBirthDate(dateFormat.parse(msg.getText()));
            sendMessage.setText("ok");
        } catch (ParseException e) {
            sendMessage.setText("Incorrect date. Try again");
        }
        setKeyboard(sendMessage);
        allowedEnterBirthday = false;
    }

    public synchronized void setKeyboard(SendMessage sendMessage) {

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("Enter my date of birth"));
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add(new KeyboardButton("List of users"));

        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);

        replyKeyboardMarkup.setKeyboard(keyboard);

        sendMessage.setReplyMarkup(replyKeyboardMarkup);
    }

}
