package com.example.springboot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "register") // Maps to the "users" table in Oracle
@Data
public class Register 
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generated primary key
    private Long id;

    
    @Column(name="Full_Name", nullable=false)
    private String fullname;

    @Column(nullable = false, unique = true) // Email cannot be null and must be unique
    private String email;

    @Column(nullable = false)
    private String password; // Store the hashed password
	
   
	    
	

	@Override
	public String toString() {
		return "Register [id=" + id + ", fullName=" + fullname + ", email=" + email + ", password=" + password + "]";
	}
    

}
