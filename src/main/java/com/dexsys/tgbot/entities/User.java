package com.dexsys.tgbot.entities;

import lombok.RequiredArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;

@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@Setter
@Getter
public class User {

     private final String userName;
     private final long  chatId;
     private String phoneNumber;
     private Date birthDate;

}
