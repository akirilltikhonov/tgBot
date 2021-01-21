package com.dexsys.tgbot.adapters;

import com.dexsys.tgbot.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public interface IUsersRepository extends JpaRepository<User, Long> {

    default DateFormat getDateFormat(String dateFormat) {
        return new SimpleDateFormat(dateFormat);
    }

    User findByPhoneNumber(String phoneNumber);
}
