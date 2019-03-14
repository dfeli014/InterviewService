package com.revature.services;

import java.util.List;

import com.revature.dtos.NewAssociateInput;
import com.revature.models.AssociateInput;
import com.revature.models.Interview;
import com.revature.repos.AssociateInputRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AssociateInputServiceImpl implements AssociateInputService {

    @Autowired
    private AssociateInputRepo associateRepo;
    @Autowired
    private InterviewService interviewService;

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

    @Override
    public Interview addAssociateInput(NewAssociateInput a) {
        
        // int interviewNumber = a.getInterviewId();
        // Interview temp = interviewService.findById(interviewNumber);

        // AssociateInput ai = new AssociateInput(0, a.getReceivedNotifications(), a.isDescriptionProvided(), temp, a.getInterviewFormat(), 
        // a.getProposedFormat());

        // temp.setAssociateInput(ai);
        // System.out.println(temp.toString());

        // interviewService.save(temp);
        // return temp;
        return new Interview();
    }
}