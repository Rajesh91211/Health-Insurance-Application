package com.example.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.example.springboot.entity.IndividualClaim;
import com.example.springboot.entity.IndividualPlan;
import com.example.springboot.repostory.IndividualPlanRepository;

import jakarta.validation.Valid;

@Service
public class IndividualPlanService 
{

	@Autowired
	private IndividualPlanRepository individualPlanRepository;
	
	
	// Save the claim to the database
    public IndividualPlan savePlan(IndividualPlan plan) 
    {
        return individualPlanRepository.save(plan);
    }
    
    //get details by claim id
    public IndividualPlan getPlanById(Long planId) {
        return individualPlanRepository.findById(planId).orElse(null);
    }
	
	
}
