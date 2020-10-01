package com.dexsys.tgbot.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("prototype")
@Getter
@Setter
@ToString
public class Client {
    private int id;
    private String name;
    private boolean isMale;
}
