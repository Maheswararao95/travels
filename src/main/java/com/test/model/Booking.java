package com.test.model;

import java.time.LocalDateTime;

import org.optaplanner.core.api.domain.lookup.PlanningId;

/**
 * Problem fact.
 */
public class Booking {
    @PlanningId
    private Long id;
    private LocalDateTime bookingDateTime;
    private Traveller traveller;
    private Trip trip;
    private Seat.Type prefType;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDateTime getBookingDateTime() {
        return bookingDateTime;
    }
    public void setBookingDateTime(LocalDateTime bookingDateTime) {
        this.bookingDateTime = bookingDateTime;
    }
    public Traveller getTraveller() {
        return traveller;
    }
    public void setTraveller(Traveller traveller) {
        this.traveller = traveller;
    }
    public Trip getTrip() {
        return trip;
    }
    public void setTrip(Trip trip) {
        this.trip = trip;
    }
    public Seat.Type getPrefType() {
        return prefType;
    }
    public void setPrefType(Seat.Type prefType) {
        this.prefType = prefType;
    }
    
}
