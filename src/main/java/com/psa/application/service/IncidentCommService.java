package com.psa.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

import org.json.*;

import com.psa.application.mail.SendMail;
import com.psa.application.model.EmailTemplate;
import com.psa.application.model.IncidentComm;
import com.psa.application.model.Worklist;
import com.psa.application.repositories.EmailTemplateRepository;
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
	EmailTemplateRepository emailTemplateRepository;
	
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
			newWorklist.setTaskOwner("AS03881");
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
				EmailTemplate emailTemplate=emailTemplateRepository.getEmailTemplateByTemplateId("inc_comm");
				String template = emailTemplate.getTemplate();
				template=template.replaceAll("@@incNum@@", newIncidentComm.getIncidentNum());
				template=template.replaceAll("@@incSeverity@@", newIncidentComm.getIncidentSeverity());
				template=template.replaceAll("@@date@@", newIncidentComm.getIncidentDate().toString());
				template=template.replaceAll("@@startTime@@", newIncidentComm.getImpactStartTime().toString());
				template=template.replaceAll("@@endTime@@", newIncidentComm.getImpactEndTime().toString());
				template=template.replaceAll("@@region@@", newIncidentComm.getImpactedRegion());
				template=template.replaceAll("@@countries@@", newIncidentComm.getImpactedCountry());
				String formattedImpactedSector="";
				for(int i=0;i<newIncidentComm.getImpactedSector().length;i++)
				{
					formattedImpactedSector+=newIncidentComm.getImpactedSector()[i]+",";
				}
				template=template.replaceAll("@@sector@@", formattedImpactedSector);
				
				String formattedImpactedLob="";
				for(int i=0;i<newIncidentComm.getImpactedLob().length;i++)
				{
					formattedImpactedLob+=newIncidentComm.getImpactedLob()[i]+",";
				}
				template=template.replaceAll("@@lob@@", formattedImpactedLob);
				template=template.replaceAll("@@title@@", newIncidentComm.getTitle());
				template=template.replaceAll("@@description@@", newIncidentComm.getDescription());
				String[] impactArr=newIncidentComm.getImpact();
				String impact ="";
				for (int i = 0; i < impactArr.length; i++)
				{
					JSONObject obj = new JSONObject(impactArr[i]);
					impact += "<tr><td>"+obj.getString("channel")+"</td><td>"+obj.getString("natureOfImpact")+"</td><td>"+obj.get("volumeOfImpact").toString()+"</td></tr>";
				    
				}
				template=template.replaceAll("@@impact@@", impact);
				if(newIncidentComm.getFix()==null)
				{
				template=template.replaceAll("@@fix@@", "TBD");
				}
				else
				{
					template=template.replaceAll("@@fix@@",newIncidentComm.getFix() );
				}
				template=template.replaceAll("@@link@@", savedWorklist.getLink());
				System.out.println("Step 3 executed::"+template);
				String subject = newIncidentComm.getIncidentNum()+"|"+newIncidentComm.getIncidentSeverity()+"|"+newIncidentComm.getTitle()+"|"+newIncidentComm.getCommTyp();
				//Step 4-> Send Mail
				//hardcoding of mail ids to be replaced during final code submission
				String mailingMessage=sendMail.sendMail("abhisheksengupta1003@gmail.com", "abhishek10sengupta@gmail.com", "abhishek10sengupta@gmail.com", subject, template);
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
}
