package com.revature.services;

import java.util.List;

import com.revature.models.AssociateInput;

public interface AssociateInputService {

    AssociateInput save(AssociateInput a);
    AssociateInput update(AssociateInput a);
    AssociateInput delete(AssociateInput a);

    List<AssociateInput> findAll();

}