package com.revature.repos;

import com.revature.models.AssociateInput;

import org.hibernate.mapping.Any;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssociateInputRepo extends JpaRepository<AssociateInput, Any> {

}
