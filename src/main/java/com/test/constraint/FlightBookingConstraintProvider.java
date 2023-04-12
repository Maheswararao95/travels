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
            onlyOnePassengerPerSeatConstraint(constraintFactory)
            ,
            onlyOneSeatPerPassengerConstraint(constraintFactory)
            ,
            allocatePrefSeat(constraintFactory)
        };
    }

    /**
     * Max one seat per booking.
     * @param constraintFactory
     * @return
     */
    private Constraint onlyOneSeatPerPassengerConstraint(ConstraintFactory constraintFactory) {
       return constraintFactory.forEachUniquePair(TravellerSeatAllocation.class,
            Joiners.equal(TravellerSeatAllocation::getBooking)
        )
        .penalize(HardSoftScore.ONE_HARD).asConstraint("Only one passenger per seat");
    }

    private Constraint onlyOnePassengerPerSeatConstraint(ConstraintFactory constraintFactory) {
        return constraintFactory.forEachUniquePair(TravellerSeatAllocation.class,
            Joiners.equal(TravellerSeatAllocation::getSeat)
        )
        .penalize(HardSoftScore.ONE_HARD).asConstraint("Only one seat per passenger");
    }

    private Constraint allocatePrefSeat(ConstraintFactory constraintFactory) {
        return constraintFactory.forEach(TravellerSeatAllocation.class)
        .filter(allocation -> allocation.getBooking().getPrefType() != null 
            && allocation.getBooking().getPrefType() != allocation.getSeat().getType())
        .penalize(HardSoftScore.ONE_SOFT).asConstraint("Preffered seats");
    }
    
}

