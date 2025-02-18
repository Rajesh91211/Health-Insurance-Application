package com.example.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.entity.FamilyClaim;
import com.example.springboot.repostory.FamilyClaimRepository;

@Service
public class FamilyClaimService 
{

	@Autowired
    private FamilyClaimRepository familyClaimRepository;

	 // Save the claim to the database
    public FamilyClaim saveFamilyClaim(FamilyClaim claim) 
    {
    	
        return familyClaimRepository.save(claim);
    }
    
    //get details by claim id
    public FamilyClaim getClaimById(String claimId) {
        return familyClaimRepository.findById(claimId).orElse(null);
    }
}
