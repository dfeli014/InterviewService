package com.revature.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.revature.models.*;

public interface InterviewRepo extends JpaRepository<Interview, Integer> {
	
	Interview findById(int id);

}
