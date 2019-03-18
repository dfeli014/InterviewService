package com.revature.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Date;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.revature.cognito.dtos.CognitoRegisterBody;
import com.revature.cognito.dtos.CognitoRegisterResponse;
import com.revature.cognito.dtos.CognitoTokenClaims;
import com.revature.cognito.utils.CognitoUtil;
import com.revature.dtos.NewInterviewData;
import com.revature.feign.IUserClient;

import com.revature.dtos.AssociateInterview;
import com.revature.dtos.NewAssociateInput;
import com.revature.models.AssociateInput;
import com.revature.models.Client;
import com.revature.models.Interview;
import com.revature.repos.AssociateInputRepo;
import com.revature.dtos.FeedbackData;
import com.revature.dtos.Interview24Hour;
import com.revature.models.FeedbackStatus;
import com.revature.models.Interview;
import com.revature.models.InterviewFeedback;
import com.revature.models.User;
import com.revature.repos.FeedbackRepo;
import com.revature.repos.InterviewRepo;
import com.revature.utils.ListToPage;

@Service
public class InterviewServiceImpl implements InterviewService {

	@Autowired
	private InterviewRepo interviewRepo;

	@Autowired
	private AssociateInputRepo associateRepo;

	@Autowired
	private IUserClient userClient;

	@Autowired
	private CognitoUtil cognitoUtil;

	@Autowired
	private FeedbackRepo feedbackRepo;
	
	public Interview save(Interview i) {
		return interviewRepo.save(i);
	}

	@Override
	public Interview update(Interview i) {
		return interviewRepo.save(i);
	}

	public Interview delete(Interview i) {
		return null;
	}

	@Override
	public List<Interview> findAll() {
		// TODO Auto-generated method stub
		return interviewRepo.findAll();
	}


	public Interview addNewInterview(NewInterviewData i) {		
		try {
			String managerEmail = cognitoUtil.getRequesterClaims().getEmail();
			String associateEmail = i.getAssociateEmail();
			Date scheduled = new Date(i.getDate());// TODO: check this is valid date
			String location = i.getLocation();
			Interview newInterview = new Interview(0, managerEmail, associateEmail, scheduled, null, null, location, null, null, new Client(1, "Dell"));//new Interview(0, managerEmail, associateId, scheduled, null, null, i.getLocation(), null, null);	
			return save(newInterview);
		} catch (Exception e) {
			System.out.println("exception: " + e);
			return null;
		}
	}


	public Page<Interview> findAll(Pageable page) {
		// TODO Auto-generated method stub
		return interviewRepo.findAll(page);
	}

	public List<AssociateInterview> findInterviewsPerAssociate() {
		List<Interview> interviews = interviewRepo.findAll();
		List<AssociateInterview> associates = new ArrayList<AssociateInterview>();

		for (Interview I : interviews) {
			AssociateInterview A = new AssociateInterview(I);
			int index = associates.indexOf(A);
			System.out.println("New: " + A);
			if (index > 0) {
				A = associates.get(index);
				A.incrementInterviewCount();
				associates.set(index, A);
				System.out.println("Incremented: " + A);
			} else {
				associates.add(A);
			}
		}
		System.out.println("List created");
		for(AssociateInterview A: associates) {
			try{
				User U = userClient.findByEmail(A.getAssociateEmail()).getBody();
				String Name = U.getFirstName();
				if(Name=="") {
					Name=U.getLastName();
				} else {
					Name+=" "+ U.getLastName();
				}
				System.out.println(Name);
				A.setAssociateName(Name);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		Collections.sort(associates);
		return associates;
	}

	public Page<AssociateInterview> findInterviewsPerAssociate(Pageable page) {
		PageImpl PI = ListToPage.getPage(findInterviewsPerAssociate(), page);
		return PI;
	}

	@Override
	public Interview findById(int i) {
		List<Interview> listInt = interviewRepo.findAll();
		Interview found = new Interview();

		for (Interview it : listInt) {
			if (it.getId() == i) {
					found = it;
			}
		}

		return found;
	}

	@Override
    public Interview addAssociateInput(NewAssociateInput a) {
        
        int interviewNumber = a.getInterviewId();
        Interview temp = this.findById(interviewNumber);     
        AssociateInput ai = new AssociateInput(0, a.getReceivedNotifications(), a.isDescriptionProvided(), temp, a.getInterviewFormat(), 
        a.getProposedFormat());

		temp.setAssociateInput(ai);
		associateRepo.save(ai);
	
		return temp;
    }

	public Interview setFeedback(FeedbackData f) {
		InterviewFeedback interviewFeedback = new InterviewFeedback(0, new Date(f.getFeedbackRequestedDate()), f.getFeedbackText(), new Date(f.getFeedbackReceivedDate()), new FeedbackStatus(1, "Pending"));
		System.out.println("interviewFeedback\n" + interviewFeedback.toString());
		System.out.println("interviewStatus\n" + interviewFeedback.getStatus());
		Interview i = interviewRepo.findById(f.getInterviewId());
		if(i != null) {
			interviewFeedback = feedbackRepo.save(interviewFeedback);
			i.setFeedback(interviewFeedback);	
			return save(i);
		}
		else 
			return null;
  }
  
	@Override
	public List<User> getAssociateNeedFeedback() {
		List<Interview> interviews = interviewRepo.findAll();
		Set<String> needFeedback = new TreeSet<String>();
		List<User> associates = new ArrayList<User>();
		
		for(Interview I: interviews) {
			if(I.getFeedback() != null && I.getFeedback().getFeedbackDelivered() == null) {
				needFeedback.add(I.getAssociateEmail());
			}
		}
		for(String N: needFeedback) {
			try {
				associates.add(userClient.findByEmail(N).getBody());
			} catch (Exception E) {
				System.out.println(E);
			}
		}
		return associates;
	}

	@Override
	public Page<User> getAssociateNeedFeedback(Pageable page) {
		PageImpl PI = ListToPage.getPage(getAssociateNeedFeedback(), page);
		return PI;
	}

	@Override
	public Integer[] getAssociateNeedFeedbackChart() {
		List<Interview> interviews = interviewRepo.findAll();
		Integer[] feedbackChart = {0,0,0,0,0};
		
		feedbackChart[0] = interviews.size();		// [0] is the total number of interviews
		
		for(Interview I: interviews) {

			if(I.getFeedback() == null) {
				feedbackChart[1]++;					// [1] is the number of interviews with no feedback requested
			} else {
				feedbackChart[2]++;					// [2] is the number of interviews with feedback requested
				
				if(I.getFeedback().getFeedbackReceived() != null) {
					
					if(I.getFeedback().getFeedbackDelivered() != null) {
						feedbackChart[3]++;			// [3] is the number of interviews that received feedback that hasn't been delivered to associate
					} else {
						feedbackChart[4]++;			// [4] is the number of interviews that received feedback that has been delivered to associate
					}
				}
			}
		}
		return feedbackChart;
	}

	public List<Interview> getAllInterviewsWithin24HourNoticeAssociate(){
		//find all interviews
		List<Interview> allUsers = interviewRepo.findAll();
		//find all interviews where the users were notified in advance
		ArrayList<Interview> allNotifiedUsers = new ArrayList<Interview>();
		
		//count all interviews
		int countAll = allUsers.size();

		//build a new list iteratively for allNotifiedUsers
		for (Interview i : allUsers)
		{
			if (i.getAssociateInput() == null)
				System.out.println("This interview has no associate input");
			if (i.getAssociateInput() != null)
				if (i.getAssociateInput().getReceivedNotifications() == null)
					System.out.println("This interview has associate input but no received notifications date");
			
			//check of non null
			if (i.getAssociateInput() != null)
				//check of non null
				if (i.getAssociateInput().getReceivedNotifications() != null)
				{
					//Singleton Calendar
					Calendar cal = Calendar.getInstance();
					//Set time on calendar to current receivedNotifications date
					cal.setTime(i.getScheduled());
					Date curDate = cal.getTime();
					//Add 24 Hours to the current date
					cal.add(Calendar.DATE, -1);
					//Calculate a new date, one day from the receivedNotifications
					Date oneDayBefore = cal.getTime();
					//If meets criteria, push to new list
					if (i.getAssociateInput().getReceivedNotifications().before(oneDayBefore) || !(i.getAssociateInput().getReceivedNotifications().after(oneDayBefore))) {
						allNotifiedUsers.add(i);
					}
					System.out.println("getScheduled: "+i.getScheduled()+" oneDayBefore: "+oneDayBefore+" Associate: "+i.getAssociateInput().getReceivedNotifications());
					System.out.println(i.getAssociateInput().getReceivedNotifications().before(oneDayBefore)+" vs "+(!(i.getAssociateInput().getReceivedNotifications().after(oneDayBefore))));
				}
		}
        return allNotifiedUsers;
	}
	
	public List<Integer> getInterviewsWithin24HourNoticeAssociate(){
		//find all interviews
		List<Interview> allNotifiedUsers = getAllInterviewsWithin24HourNoticeAssociate();
		//return
		List<Integer> returning;		
		//count only interviews that are within 24 hour notice
		int countNotified = allNotifiedUsers.size();
		returning = Arrays.asList(interviewRepo.findAll().size(), countNotified);
        return returning;
	}
	
	public List<Interview> getAllInterviewsWithin24HourNoticeManager(){
		//find all interviews
		List<Interview> allUsers = interviewRepo.findAll();
		//find all interviews where the users were notified in advance
		ArrayList<Interview> allNotifiedUsers = new ArrayList<Interview>();
		
		//count all interviews
		int countAll = allUsers.size();

		//build a new list iteratively for allNotifiedUsers
		for (Interview i : allUsers)
		{
			if (i.getNotified() == null)
			{
				System.out.println("This interview has no manager input");
			}
			
			
			//check of non null
				if (i.getNotified() != null)
				{
					//Singleton Calendar
					Calendar cal = Calendar.getInstance();
					//Set time on calendar to current receivedNotifications date
					cal.setTime(i.getScheduled());
					Date curDate = cal.getTime();
					//Add 24 Hours to the current date
					cal.add(Calendar.DATE, -1);
					//Calculate a new date, one day from the receivedNotifications
					Date oneDayBefore = cal.getTime();
					//If meets criteria, push to new list
					if (i.getNotified().before(oneDayBefore) || !(i.getNotified().after(oneDayBefore))){
						allNotifiedUsers.add(i);
					}
			
					System.out.println("getScheduled: "+i.getScheduled()+" oneDayBefore: "+oneDayBefore+" Manager: "+i.getNotified());
					System.out.println(i.getNotified().before(oneDayBefore)+" vs "+(!(i.getNotified().after(oneDayBefore))));
				}
				
		}
        return allNotifiedUsers;
	}
	
	public List<Integer> getInterviewsWithin24HourNoticeManager() {
		//find all interviews
		List<Interview> allNotifiedUsers = getAllInterviewsWithin24HourNoticeManager();
		//return
		List<Integer> returning;		
		//count only interviews that are within 24 hour notice
		int countNotified = allNotifiedUsers.size();
		returning = Arrays.asList(interviewRepo.findAll().size(), countNotified);
        return returning;
    }
	
	private List<Interview24Hour> getAll24HourNoticeWithoutName(){
		List<Interview> DataIn = interviewRepo.findAll();
		System.out.println(DataIn);
		List<Interview24Hour> DataOut = new ArrayList<Interview24Hour>();
		for(Interview I: DataIn) {
			DataOut.add(new Interview24Hour(I));
		}
		return DataOut;
	}
	
	public List<Interview24Hour> getAll24HourNotice(){
		List<Interview24Hour> Data= getAll24HourNoticeWithoutName();
		
		for(Interview24Hour I: Data) {
			try {
				User U = userClient.findByEmail(I.getAssocEmail()).getBody();
				System.out.println(U);
				I.setAssocName(U.getFirstName()+" "+U.getLastName());
			} catch (Exception E){
				System.out.println(E);
			}
		}
		
		return Data;
	}
	
	public Page<Interview24Hour> getAll24HourNotice(Pageable page) {
		PageImpl PI = ListToPage.getPage(getAll24HourNotice(), page);
		return PI;
	}
	
	@Override
	public InterviewFeedback getInterviewFeedbackByInterviewID(int interviewId) {
		return interviewRepo.findById(interviewId).getFeedback();
	}

	@Override
	public Interview markReviewed(int interviewId) {
		Interview I = interviewRepo.findById(interviewId);
		I.setReviewed(new Date(System.currentTimeMillis()));
		return interviewRepo.save(I);
	}

	@Override
	public Interview findByAssociateEmail(String s) {
		return null;
	}

	@Override
	public Interview findByManagerEmail(String s) {
		return null;
	}
}
