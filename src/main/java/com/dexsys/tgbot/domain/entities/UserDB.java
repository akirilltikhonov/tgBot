package com.dexsys.tgbot.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "usersDB")
public class UserDB {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "birthday")
    private Date birthDay;

    @Column(name = "phone")
    private String phone;

    @Column(name = "chat_id")
    private String chatId;

    @Column(name = "male")
    private boolean male;
}
