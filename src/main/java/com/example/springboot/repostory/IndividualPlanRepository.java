package com.example.springboot.repostory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.entity.IndividualPlan;

public interface IndividualPlanRepository extends JpaRepository<IndividualPlan, Long> 
{

}
