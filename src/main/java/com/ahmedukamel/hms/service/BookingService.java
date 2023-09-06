package com.ahmedukamel.hms.service;

import com.ahmedukamel.hms.model.Booking;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {
    private final List<Booking> BOOKINGS = new ArrayList<>();

    public void add(Booking booking) {
        BOOKINGS.add(booking);
    }

    public List<Booking> getAll() {
        return BOOKINGS;
    }

    public Booking findByNumber(Integer number) {
        return BOOKINGS.stream().filter(booking -> booking.getNumber() == number).findFirst().orElseThrow();
    }
}
