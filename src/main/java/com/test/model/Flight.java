package com.test.model;

import java.util.List;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

@PlanningSolution
public class Flight {
    private String id;
    
    @ProblemFactCollectionProperty
    @ValueRangeProvider(id="bookingList")
    private List<Booking> bookings;

    @ProblemFactCollectionProperty
    @ValueRangeProvider(id="availableSeats")
    private List<Seat> seats;

    @PlanningEntityCollectionProperty
    public List<TravellerSeatAllocation> allocationList;

    @PlanningScore
    private HardSoftScore score;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public List<TravellerSeatAllocation> getAllocationList() {
        return allocationList;
    }

    public void setAllocationList(List<TravellerSeatAllocation> allocationList) {
        this.allocationList = allocationList;
    }

    
}
