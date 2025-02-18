package com.example.springboot.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot.entity.FamilyClaim;


@Repository
public interface FamilyClaimRepository extends JpaRepository<FamilyClaim, String> 
{
	

}
