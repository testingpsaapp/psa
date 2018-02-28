package com.psa.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

import org.json.*;

import com.psa.application.emailTemplate.EmailTemplateGenerator;
import com.psa.application.mail.SendMail;
import com.psa.application.model.EmailList;
import com.psa.application.model.IncidentComm;
import com.psa.application.model.Worklist;
import com.psa.application.repositories.IncidentCommRepository;
import com.psa.application.repositories.WorklistRepository;

@Service
public class IncidentCommService 
{
	@Autowired
	IncidentCommRepository incidentCommRepository;
	
	@Autowired
	WorklistRepository worklistRepository;
	
	@Autowired
	CLAAccessConfigService cLAAccessConfigService;
	
	@Autowired
	EmailTemplateGenerator emailTemplateGenerator;
	
	@Autowired
	private Environment environment;
	
	@Autowired
	SendMail sendMail;
	
	public String submitIncidentCommunicationForReview(IncidentComm incidentComm) throws JSONException, MessagingException
	{
		String result="{\"message\":\"Incident Communication Submission Failed\"}";
		System.out.println("Inside Service");
		//Step 1 -> Entry in incident_comm table
		IncidentComm newIncidentComm = incidentCommRepository.saveAndFlush(incidentComm);
		System.out.println("Step 1 executed");
		Worklist newWorklist = new Worklist();
		if(newIncidentComm==null)
		{
			return result;
		}
		else
		{
			//Step 2 -> Entry in worklist table
			newWorklist.setLink("http://localhost:8080/index.html#!/incidentMIMComm?incNum="+newIncidentComm.getIncidentNum()+"&action=review");
			newWorklist.setTaskName("Review Incident Communication For "+newIncidentComm.getIncidentNum());
			newWorklist.setTaskOwner(cLAAccessConfigService.getCLAAcessConfigBySubModule("Incident - MIM Communication","Reviewer").getSoeId());
			newWorklist.setTaskId(newIncidentComm.getIncidentNum());
			Worklist savedWorklist=worklistRepository.saveAndFlush(newWorklist);
			if(savedWorklist==null)
			{
				return result;
			}
			else
			{
				System.out.println("Step 2 executed");
				//Step 3 -> Retrieve Mail Template and Replace placeholders
				String template = emailTemplateGenerator.getEmailBody("inc_comm",newIncidentComm);
				template=template.replaceAll("@@link@@", savedWorklist.getLink());
				System.out.println("Step 3 executed::"+template);
				
				
				String subject = newIncidentComm.getIncidentNum()+"|"+newIncidentComm.getIncidentSeverity()+"|"+newIncidentComm.getTitle()+"|"+newIncidentComm.getCommTyp();
				
				//Step 4-> Send Mail
				//domain name from properties file
				
				String mailingMessage=sendMail.sendMail(savedWorklist.getTaskOwner()+"@"+environment.getProperty("psa.mailing.domain"), 
						savedWorklist.getTaskOwner()+"@"+environment.getProperty("psa.mailing.domain"), 
						savedWorklist.getTaskOwner()+"@"+environment.getProperty("psa.mailing.domain"), subject, template);
				if(("Mail Sending Successful").equals(mailingMessage))
				{
					System.out.println("Step 4 executed");
					result="{\"message\":\"Incident Communication Submission Successful\"}";
				}
			}
			
		}
		return result;
	}

	public IncidentComm getIncCommForReview(String incNum) {
		// TODO Auto-generated method stub
		IncidentComm incidentComm = null;
		incidentComm = incidentCommRepository.findIncCommByIncNum(incNum);
		return incidentComm;
	}

	public IncidentComm updateIncidentCommPostApprove(IncidentComm incComm) {
		// TODO Auto-generated method stub
		IncidentComm newIncidentComm =null;
		newIncidentComm=incidentCommRepository.saveAndFlush(incComm);
		return newIncidentComm;
	}

	public String finalApproveIncidentCommunication(EmailList emailList, String incNum) throws JSONException, MessagingException {
		// TODO Auto-generated method stub
		String result="{\"message\":\"Incident Communication Approval Failed\"}";
		//Step 1 Get Inc Details
		IncidentComm incidentComm=incidentCommRepository.findIncCommByIncNum(incNum);
		//Step 2 Prepare Mail Body
		String template = emailTemplateGenerator.getEmailBody("inc_comm_a",incidentComm);
		//Step 3 Mail to Stake holders
		String subject = incidentComm.getIncidentNum()+"|"+incidentComm.getIncidentSeverity()+"|"+incidentComm.getTitle()+"|"+incidentComm.getCommTyp();
		String[] toMailId = emailList.getListOfToMailId();
		String[] ccMailId =  emailList.getListOfCCMailId();
		String[] bccMailId = emailList.getListOfBCCMailId();
		System.out.println(toMailId.toString());
		
		String mailingMessage=sendMail.sendMailToMultiple(toMailId,ccMailId ,bccMailId , subject, template);
		if(("Mail Sending Successful").equals(mailingMessage))
		{
			System.out.println("Step 4 executed");
			result="{\"message\":\"Incident Communication Submission Successful\"}";
		}
		
		return result;
	}
}
