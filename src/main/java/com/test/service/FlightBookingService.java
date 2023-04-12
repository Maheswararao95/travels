package com.test.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.optaplanner.core.api.solver.SolverJob;
import org.optaplanner.core.api.solver.SolverManager;

import com.test.model.Booking;
import com.test.model.Flight;
import com.test.model.Seat;
import com.test.model.Traveller;
import com.test.model.TravellerSeatAllocation;
import com.test.model.Trip;
import com.test.model.Seat.Type;
import com.test.repository.BookingRepository;

@ApplicationScoped
public class FlightBookingService {
    @Inject
    BookingRepository bookingRepository;
    
    @Inject
    SolverManager<Flight, UUID> solverManager;

    // available seats.
    static List<Seat> seats = Arrays.asList(
        new Seat("1", Seat.Type.WINDOW),
        new Seat("2", Seat.Type.OTHER),
        new Seat("3", Seat.Type.AISLE),
        new Seat("4", Seat.Type.AISLE),
        new Seat("5", Seat.Type.WINDOW)
    );

    public List<Seat> getAvailabSeats() {
        return seats;
    }

    public Booking confirmBooking(Booking booking) {
        bookingRepository.save(booking);
        return booking;
    }
    
    public Flight solve() {
        Flight problem = setupProblem();
        UUID problemId = UUID.randomUUID();
        SolverJob<Flight, UUID> solverJob = solverManager.solve(problemId, problem);
        Flight solution = null;

        try {
            solution = solverJob.getFinalBestSolution();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return solution;
    }

    private Flight setupProblem() {
        
        Random rd = new Random();

        List<Booking> bookings = bookingRepository.getAll();
        // Booking booking1 = new Booking();
        // Traveller traveller1 = new Traveller("uma", "Italian", true);
        // Trip trip1 = new Trip();
        // trip1.setFrom("Italy");
        // trip1.setTo("US");
        // booking1.setBookingDateTime(LocalDateTime.now());
        // booking1.setId(Math.abs(rd.nextLong()));
        // booking1.setTraveller(traveller1);
        // booking1.setTrip(trip1);

        // Booking booking2 = new Booking();
        // Traveller traveller2 = new Traveller("ram", "Italian", true);
        // Trip trip2 = new Trip();
        // trip2.setFrom("Italy");
        // trip2.setTo("US");
        // booking2.setBookingDateTime(LocalDateTime.now());
        // booking2.setId(Math.abs(rd.nextLong()));
        // booking2.setTraveller(traveller2);
        // booking2.setTrip(trip2);
        // booking2.setPrefType(Type.WINDOW);

        // Booking booking3 = new Booking();
        // Traveller traveller3 = new Traveller("raj", "Italian", true);
        // Trip trip3 = new Trip();
        // trip3.setFrom("Italy");
        // trip3.setTo("US");
        // booking3.setBookingDateTime(LocalDateTime.now());
        // booking3.setId(Math.abs(rd.nextLong()));
        // booking3.setTraveller(traveller3);
        // booking3.setTrip(trip3);
        // booking3.setPrefType(Type.WINDOW);

        // List<Booking> bookings = Arrays.asList(booking1, booking2, booking3);
        List<TravellerSeatAllocation> tempSeatAllocations = new ArrayList<>();
        int index = 0;
        Long n = 1L;
        // for (Booking booking: bookings) {
        //     tempSeatAllocations.add(new TravellerSeatAllocation(Math.abs(rd.nextLong()), booking, seats.get(index)));
        //     index++;
        // }
        for (;index<bookings.size();index++) {
            TravellerSeatAllocation allocation = new TravellerSeatAllocation();
            allocation.setId(Math.abs(rd.nextLong()));
            tempSeatAllocations.add(allocation);
        }

        Flight problem = new Flight();
        problem.setBookings(bookings);
        problem.setSeats(seats);
        problem.setAllocationList(tempSeatAllocations);
        return problem;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.getAll();
    }
}
