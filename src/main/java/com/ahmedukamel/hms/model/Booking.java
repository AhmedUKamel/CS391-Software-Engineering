package com.ahmedukamel.hms.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.userdetails.User;

import java.util.Date;

@Data
@Builder
public class Booking {
    private Room room;
    private Integer number;
    private User guest;
    private Date checkin;
    private Integer days;
}
