package com.dexsys.tgbot.models;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
public class App {

    @Autowired
    private ClientsDB db;

}
