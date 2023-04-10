package com.test.rules;

import javax.inject.Singleton;

import org.drools.ruleunits.api.DataSource;
import org.drools.ruleunits.api.RuleUnitData;
import org.drools.ruleunits.api.SingletonStore;

import com.test.model.Traveller;
import com.test.model.Trip;

public class VisaCheck implements RuleUnitData {
    private SingletonStore<Trip> trip;
    private SingletonStore<Traveller> traveller;

    public VisaCheck(){
        this(DataSource.createSingleton(), DataSource.createSingleton());
    }

    public VisaCheck(SingletonStore<Trip> trip, SingletonStore<Traveller> traveller) {
        this.trip = trip;
        this.traveller = traveller;
    }

    public SingletonStore<Trip> getTrip() {
        return this.trip;
    }

    public void setTrip(SingletonStore<Trip> trip) {
        this.trip = trip;
    }

    public SingletonStore<Traveller> getTraveller() {
        return traveller;
    }

    public void setTraveller(SingletonStore<Traveller> traveller) {
        this.traveller = traveller;
    }
}