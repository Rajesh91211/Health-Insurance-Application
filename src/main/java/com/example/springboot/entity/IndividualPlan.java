package com.example.springboot.entity;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class IndividualPlan 
{
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long planId;

	    @NotBlank(message = "Plan type is required")
	    private String planType;

	    @NotBlank(message = "Plan price is required")
	    private String planPrice;

	    @NotBlank(message = "First name is required")
	    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
	    private String firstName;

	    @NotBlank(message = "Last name is required")
	    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
	    private String lastName;

	    
	    @Min(value = 18, message = "Age must be at least 18")
	    @Max(value = 120, message = "Age must be less than 120")
	    private Integer age;

	    @NotBlank(message = "Email is required")
	    @Email(message = "Invalid email format")
	    private String email;

	    @NotBlank(message = "Date of birth is required")
	    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date of birth must be in YYYY-MM-DD format")
	    private String dateOfBirth;

	    @NotBlank(message = "Phone number is required")
	    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
	    private String phone;

	    @NotBlank(message = "Address is required")
	    @Size(min = 5, max = 255, message = "Address must be between 5 and 255 characters")
	    private String address;

	    @NotBlank(message = "City is required")
	    @Size(min = 2, max = 100, message = "City must be between 2 and 100 characters")
	    private String city;

	    @NotBlank(message = "State is required")
	    @Size(min = 2, max = 100, message = "State must be between 2 and 100 characters")
	    private String state;

	    @NotBlank(message = "PIN code is required")
	    @Pattern(regexp = "^[0-9]{6}$", message = "PIN code must be 6 digits")
	    private String pinCode;

	    // Constructors (default and parameterized)
	    public IndividualPlan() {
	    }

	    public IndividualPlan(String planType, String planPrice, String firstName, String lastName, Integer age, String email, String dateOfBirth, String phone, String address, String city, String state, String pinCode) {
	        this.planType = planType;
	        this.planPrice = planPrice;
	        this.firstName = firstName;
	        this.lastName = lastName;
	        this.age = age;
	        this.email = email;
	        this.dateOfBirth = dateOfBirth;
	        this.phone = phone;
	        this.address = address;
	        this.city = city;
	        this.state = state;
	        this.pinCode = pinCode;
	    }
	}
	


