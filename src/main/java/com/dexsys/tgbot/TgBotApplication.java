package com.dexsys.tgbot;

import com.dexsys.tgbot.models.App;
import com.dexsys.tgbot.models.Client;
import com.dexsys.tgbot.models.ClientsDB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

@SpringBootApplication
@Slf4j
public class TgBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(TgBotApplication.class, args);

		log.info("Start main");
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
//		ctx.register(App.class, ClientsDB.class, Client.class);
		ctx.scan("com.dexsys.tgbot.models");
		ctx.refresh();
		log.info("Init app context");

		App app = ctx.getBean(App.class);
		app.getDb().setNameDB("Clients Data Base");
		System.out.println(app.getDb().getNameDB());

		app.getDb().initDB(ctx.getBean(Client.class), ctx.getBean(Client.class), ctx.getBean(Client.class));

		System.out.println(app.getDb().toString());

		ApiContextInitializer.init();
		BirthdayBot bot = new BirthdayBot();
		TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
		try {
			telegramBotsApi.registerBot(bot);
		} catch (TelegramApiRequestException e) {
			e.printStackTrace();
		}
		log.info("Finish main");
	}

}
