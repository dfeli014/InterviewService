package com.revature.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.revature.models.*;

public interface InterviewRepo extends JpaRepository<Interview, Integer> {
	
	Interview findById(int id);
	List<Interview> findByAssociateEmail(String email);

}
