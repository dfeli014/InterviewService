package com.revature.services;

import java.util.List;

import com.revature.dtos.NewAssociateInput;
import com.revature.models.AssociateInput;
import com.revature.models.Interview;

public interface AssociateInputService {

    AssociateInput save(AssociateInput a);
    AssociateInput update(AssociateInput a);
    AssociateInput delete(AssociateInput a);

    List<AssociateInput> findAll();
    Interview addAssociateInput(NewAssociateInput a);

}