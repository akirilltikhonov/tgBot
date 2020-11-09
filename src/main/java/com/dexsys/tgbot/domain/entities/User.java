package com.dexsys.tgbot.domain.entities;

import lombok.*;

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
@Table(name = "old_users")
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
