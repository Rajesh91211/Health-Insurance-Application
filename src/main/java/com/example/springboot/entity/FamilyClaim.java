package com.example.springboot.entity;

import java.time.LocalDate;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Family_claims")
@Data  //for getter and setter creation (copy jar file inside the eclipse folder.. )
public class FamilyClaim 
{
	@Id
    @Column(nullable = false)
    private String claimId;
    
    @Column(nullable = false)
    private String primaryInsuredName;
    
   
    @Column(nullable = false)
    private String patientName;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private RelationPrimaryInsured relationPrimaryInsured;

    @Column(nullable = false)
    private String hospitalName;

    @Column(nullable = false)
    private LocalDate dateOfAdmission;

    @Column(nullable = false)
    private String diagnosis;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TreatmentType treatmentType;

    @Column(nullable = false)
    private Double claimAmount;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private byte[] hospitalBills;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private byte[] dischargeSummary;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private byte[] relationshipProof;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private byte[] patientIdProof;

    @Column(nullable = false)
    private LocalDate submissionDate = LocalDate.now();

    @Enumerated(EnumType.STRING)
    private ClaimStatus status = ClaimStatus.PENDING;

    
	

}
