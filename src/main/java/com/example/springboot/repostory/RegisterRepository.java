package com.example.springboot.repostory;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot.entity.Register;

@Repository
public interface RegisterRepository extends JpaRepository<Register, Long> {

	boolean existsByEmail(String email);
	Register findByEmail(String email);
}
