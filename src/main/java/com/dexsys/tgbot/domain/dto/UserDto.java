package com.dexsys.tgbot.domain.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserDto {

    private String userName;
    private long  chatId;
    private String phoneNumber;
    private Date birthdate;

}
