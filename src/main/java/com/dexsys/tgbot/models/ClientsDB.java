package com.dexsys.tgbot.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@Setter
@Getter
@ToString
@Slf4j
public class ClientsDB {

    private String nameDB;
    private List<Client> clients;

    public void initDB(Client ... clients) {

        this.clients = new ArrayList<>();
        int i = 0;
        Random random = new Random();
        for (Client client : clients) {
            client.setName("Client" + i);
            client.setId(i);
            client.setMale(random.nextBoolean());
            this.clients.add(client);
            i++;
        }
        log.info("Init clients DB");
    }

}
