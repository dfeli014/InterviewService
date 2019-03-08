package com.revature.services;

import java.util.List;

import com.revature.models.AssociateInput;
import com.revature.repos.AssociateInputRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssociateInputServiceImpl implements AssociateInputService {

    @Autowired
    private AssociateInputRepo associateRepo;
    
    public AssociateInput save(AssociateInput a) {
        return associateRepo.save(a);
    }

    @Override
    public AssociateInput update(AssociateInput a) {
        return null;
    }

    @Override
    public AssociateInput delete(AssociateInput a) {
        return null;
    }

    @Override
    public List<AssociateInput> findAll() {
        return null;
    }


}