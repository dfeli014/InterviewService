package com.revature.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.revature.models.*;

public interface FeedbackRepo extends JpaRepository<InterviewFeedback, Integer> {
	
}
