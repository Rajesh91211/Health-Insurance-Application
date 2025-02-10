package com.example.springboot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "register") // Maps to the "users" table in Oracle
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
	
	    
	
// public Register(Long id, String fullName, String email, String password) {
//		super();
//		this.id = id;
//		this.fullName = fullName;
//		this.email = email;
//		this.password = password;
//	}

// ... getters and setters ...
	
	



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getFullname() {
		return fullname;
	}



	public void setFullname(String fullname) {
		this.fullname = fullname;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Register [id=" + id + ", fullName=" + fullname + ", email=" + email + ", password=" + password + "]";
	}
    

}
