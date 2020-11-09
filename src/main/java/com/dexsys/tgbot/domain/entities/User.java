package com.dexsys.tgbot.domain.entities;

import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Entity;

import javax.persistence.Column;
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

     @Column(name = "userName")
     @NonNull
     private String userName;

     @Id
     @Column(name = "chatId")
     @NonNull
     private long  chatId;

     @Column(name = "chatId")
     private String phoneNumber;

     @Column(name = "birthdate")
     private Date birthdate;

}
