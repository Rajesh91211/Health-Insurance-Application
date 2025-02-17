package com.example.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.springboot.entity.IndividualClaim;
import com.example.springboot.repostory.IndividualClaimRepository;

@Service
public class IndividualClaimService 
{
	@Autowired
	private IndividualClaimRepository individualClaimRepository;

    // Save the claim to the database
    public IndividualClaim saveClaim(IndividualClaim claim) 
    {
        return individualClaimRepository.save(claim);
    }
    
    public IndividualClaim getClaimById(String claimId) {
        return individualClaimRepository.findById(claimId).orElse(null);
    }

}
