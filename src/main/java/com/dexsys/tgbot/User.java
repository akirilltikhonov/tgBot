package com.dexsys.tgbot;

import lombok.*;

import java.util.Date;

@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@Setter
@Getter
public class User {

     private final String userName;
     private final long  chatId;
     private Date birthDate;

}
