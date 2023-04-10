package com.test.service;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.optaplanner.core.api.solver.SolverJob;
import org.optaplanner.core.api.solver.SolverManager;

import com.test.model.Booking;
import com.test.model.Flight;
import com.test.model.Seat;
import com.test.model.Traveller;
import com.test.model.TravellerSeatAllocation;
import com.test.model.Trip;
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
        

        List<Booking> bookings = bookingRepository.getAll();

        List<TravellerSeatAllocation> tempSeatAllocations = new ArrayList<>();
        int index = 0;
        for (Booking booking: bookings) {
            Random rd = new Random();
            tempSeatAllocations.add(new TravellerSeatAllocation(rd.nextLong(), booking, seats.get(index++)));
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
