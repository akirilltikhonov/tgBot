package com.dexsys.tgbot.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor

public class UserDtoDB {

    private UUID id;
    private String firstName;
    private String secondName;
    private String middleName;
    private Date birthDay;
    private String phone;
    private String chatId;
    private boolean male;

    public UserDtoDB(String id) {
        this.id = UUID.fromString(id);
    }

}
