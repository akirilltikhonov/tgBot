package com.dexsys.tgbot.domain.entities;

import lombok.Getter;
import lombok.NonNull;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@RequiredArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "users")
public class User {

     @Column(name = "user_name")
     @NonNull
     private String userName;

     @Id
     @Column(name = "chat_id")
     @NonNull
     private long chatId;

     @Column(name = "phone_number")
     private String phoneNumber;

     @Column(name = "birthdate")
     private Date birthdate;

}
