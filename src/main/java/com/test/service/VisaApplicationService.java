package com.test.service;

import javax.enterprise.context.ApplicationScoped;

import com.test.model.Traveller;

@ApplicationScoped
public class VisaApplicationService {
    public Traveller getVisaApproval(Traveller traveller) {
        traveller.setElgibleForTravelling(true);
        return traveller;
    }
}
