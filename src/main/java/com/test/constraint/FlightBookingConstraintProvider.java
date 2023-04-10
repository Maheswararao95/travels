package com.test.constraint;

import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;
import org.optaplanner.core.api.score.stream.Joiners;

import com.test.model.TravellerSeatAllocation;

public class FlightBookingConstraintProvider implements ConstraintProvider {

    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[] {
            onlyOnePassengerPerSeatConstraint(constraintFactory),
            allocatePrefSeat(constraintFactory)
        };
    }

    private Constraint onlyOnePassengerPerSeatConstraint(ConstraintFactory constraintFactory) {
        return constraintFactory.forEach(TravellerSeatAllocation.class)
        .join(TravellerSeatAllocation.class, 
            Joiners.equal(TravellerSeatAllocation::getBooking),
            Joiners.equal(TravellerSeatAllocation::getSeat),
            Joiners.lessThan(TravellerSeatAllocation::getId))
            .penalize(HardSoftScore.ONE_HARD).asConstraint("Only one seat per passenger");
    }

    private Constraint allocatePrefSeat(ConstraintFactory constraintFactory) {
        return constraintFactory.forEach(TravellerSeatAllocation.class)
        .join(TravellerSeatAllocation.class, 
            Joiners.equal(TravellerSeatAllocation::getBooking),
            Joiners.equal(TravellerSeatAllocation::getSeat))
        .filter((b, s) -> b.getBooking().getPrefType() != s.getSeat().getType())
        .penalize(HardSoftScore.ONE_SOFT).asConstraint("Preffered seats");
    }
    
}
