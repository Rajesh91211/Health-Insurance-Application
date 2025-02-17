package com.example.springboot.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot.entity.IndividualClaim;

@Repository
public interface IndividualClaimRepository extends JpaRepository<IndividualClaim, String>
{

}
