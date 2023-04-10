package com.test.model;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

/**
 * Planning entity.
 */
@PlanningEntity
public class TravellerSeatAllocation {
   
    private Long id;
    
    @PlanningVariable(valueRangeProviderRefs = "bookingList")
    private Booking booking;

    @PlanningVariable(valueRangeProviderRefs = "availableSeats")
    private Seat seat;

    public TravellerSeatAllocation() {}
    public TravellerSeatAllocation(Long id, Booking booking, Seat seat){
        this.id = id;
        this.booking = booking;
        this.seat = seat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Booking getBooking() {
        return booking;
    }
    public void setBooking(Booking booking) {
        this.booking = booking;
    }
    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }
}