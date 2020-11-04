package com.dexsys.tgbot.app.userMock;

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

public class UserDto {

    private UUID id;
    private String firstName;
    private String secondName;
    private String middleName;
    private Date birthDay;
    private String phone;
    private String chatId;
    private boolean male;

    public UserDto(String id) {
        this.id = UUID.fromString(id);
    }

}
