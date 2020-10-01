package com.dexsys.tgbot;

import com.dexsys.tgbot.models.App;
import com.dexsys.tgbot.models.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
@Slf4j
public class TgBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(TgBotApplication.class, args);
		log.info("Start main");
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
//		ctx.register(App.class, DataBase.class, Client.class);
		ctx.scan("com.dexsys.tgbot.models");
		ctx.refresh();
		log.info("Init app context");

		App app = ctx.getBean(App.class);
		app.getDb().setNameDB("Clients Data Base");
		System.out.println(app.getDb().getNameDB());

		app.getDb().initDB(ctx.getBean(Client.class), ctx.getBean(Client.class), ctx.getBean(Client.class));

		System.out.println(app.getDb().toString());
		log.info("Finish main");
	}

}
