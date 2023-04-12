package com.test.repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import com.test.model.Booking;

@ApplicationScoped
public class BookingRepository {
    static Map<Long, Booking> data = new HashMap<>();

    public Booking save(Booking booking) {
        Random rd = new Random();
        booking.setId(Math.abs(rd.nextLong()));
        booking.setBookingDateTime(LocalDateTime.now());
        data.put(booking.getId(), booking);
        return booking;
    }

    public List<Booking> getAll() {
        return data.values().stream().collect(Collectors.toList());
    }
}
