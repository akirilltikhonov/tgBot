package com.dexsys.tgbot.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserDTO {

    private String userName;
    private long  chatId;
    private String phoneNumber;
    private Date birthdate;

}
