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

}
