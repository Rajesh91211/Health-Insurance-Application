package com.example.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.springboot.entity.Register;
import com.example.springboot.repostory.RegisterRepository;

@Service
public class RegisterService
{
@Autowired	
private RegisterRepository registerRepository;

 
public Register saveDetails(Register user)
{
	return registerRepository.save(user);
	
}

public Register findByEmail(String email) 
{ // Implement the method
    return registerRepository.findByEmail(email);
}

public boolean existsByEmail(String email) {
	// TODO Auto-generated method stub
	return registerRepository.existsByEmail(email);
}

//@Autowired
//private BCryptPasswordEncoder bCryptPasswordEncoder;

//public String registerUser(Register register) {
//    if (registerRepository.findByEmail(register.getEmail())!= null) {
//        return "Email already exists"; // Indicate failure
//    }
//
//    // Hash the password before saving to the database
//    String hashedPassword = bCryptPasswordEncoder.encode(register.getPassword());
//    register.setPassword(hashedPassword);
//
//    registerRepository.save(register);
//    return "Registration successful"; // Indicate success
//}
}
