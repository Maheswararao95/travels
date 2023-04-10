package com.test.model;

import org.optaplanner.core.api.domain.lookup.PlanningId;

/**
 * Problem fact.
 */
public class Seat {
    @PlanningId
    private String seatId;
    private Type type;

    public Seat() {}
    public Seat(String seatId, Type type) {
        this.seatId = seatId;
        this.type = type;
    }

    public enum Type {
        WINDOW, AISLE, OTHER
    }

    public String getSeatId() {
        return seatId;
    }

    public void setSeatId(String seatId) {
        this.seatId = seatId;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    
}
