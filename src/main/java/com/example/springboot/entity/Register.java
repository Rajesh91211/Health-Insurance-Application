package com.example.springboot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "register")
@Data
public class Register {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="Full_Name", nullable=false)
    private String fullname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name="Policy_Number")
    private String policyNumber;

    @Column(name="Contact_Info")
    private String contactInfo;

    @Column(name="Plan_Type")
    private String planType;

    @Column(name="Coverage_Details")
    private String coverageDetails;

    @Column(name="Expiry_Date")
    private String expiryDate;

    // Getters and Setters
    // toString method
}
