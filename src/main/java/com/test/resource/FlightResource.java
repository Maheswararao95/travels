package com.test.resource;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.test.model.Booking;
import com.test.model.Flight;
import com.test.model.Seat;
import com.test.service.FlightBookingService;

@Path("")
public class FlightResource {

    @Inject
    FlightBookingService flightBookingService;

    @GET
    @Path("/bookings")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Booking> getBookings() {
       return flightBookingService.getAllBookings();
    }

    @GET
    @Path("/availabe-seats")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Seat> getAvailabSeats() {
        return flightBookingService.getAvailabSeats();
    }
    
    @GET
    @Path("/allocate-seats")
    @Produces(MediaType.APPLICATION_JSON)
    public Flight allocateSeats() {
        return flightBookingService.solve();
    }
}
