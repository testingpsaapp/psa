package com.psa.application.service;

import javax.mail.MessagingException;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.psa.application.emailTemplate.EmailTemplateGenerator;
import com.psa.application.mail.SendMail;
import com.psa.application.model.ChangeComm;
import com.psa.application.model.Worklist;
import com.psa.application.repositories.AppConfigRepository;
import com.psa.application.repositories.ChangeCommRepository;
import com.psa.application.repositories.WorklistRepository;

@Service
public class ChangeCommService {
	
	@Autowired
	ChangeCommRepository changeCommRepository;
	
	@Autowired
	AppConfigRepository appConfigRepository;
	
	@Autowired
	WorklistRepository worklistRepository;
	
	@Autowired
	EmailTemplateGenerator emailTemplateGenerator;
	
	@Autowired
	SendMail sendMail;
	
	@Autowired
	Environment environment;
	
	public String submitForCompletion(ChangeComm changeComm) throws JSONException, MessagingException
	{
		String result ="{\"message\":\"Change Communication Submission Failed\"}";
		String messageResult="Failed";
		//Step 1 Save Comm in db
		ChangeComm newChangeComm = null;
		newChangeComm = changeCommRepository.saveAndFlush(changeComm);
		System.out.println("Save Comm Step 1 Completed");
		//Step 2 Worklist entries
		Worklist newWorklist;
		if(newChangeComm !=null)
		{
			for(int i=0;i<newChangeComm.getImpactedPp().length;i++)
			{
				String taskOwner=appConfigRepository.getAppConfigByAppName(newChangeComm.getImpactedPp()[i]).getLobLead();
				Worklist worklist =new Worklist();
				newWorklist =new Worklist();
				worklist.setTaskId(newChangeComm.getChangNum());
				worklist.setTaskName(newChangeComm.getChangNum()+"- Sanity Scope Submission Pending");
				worklist.setStatus("ACTIVE");
				worklist.setTaskOwner(taskOwner);
				worklist.setLink("http://localhost:8080/index.html#!/ChangeCommunication/SanityScope?appName="+newChangeComm.getImpactedPp()[i]+"&changeNum="+newChangeComm.getChangNum());
				newWorklist=worklistRepository.saveAndFlush(worklist);
				worklist=null;
				
				if(newWorklist!=null)
				{
					System.out.println("Save Comm Step 2 Completed");
					//Step 3 Mail Stake Holders
					String body= emailTemplateGenerator.getEmailBody("change_comm_sanity", newChangeComm);
					body = body.replaceAll("@@Link@@", newWorklist.getLink());
					body = body.replaceAll("@@AppName@@", newChangeComm.getImpactedPp()[i]);
					body = body.replaceAll("@@ImpactedPP@@", newChangeComm.getImpactedPp()[i]);
					System.out.println(body);
					String subject =newChangeComm.getChangNum()+"- Pending Action Item";
					messageResult=sendMail.sendMail(appConfigRepository.getAppConfigByAppName(newChangeComm.getImpactedPp()[i]).getdList(), 
							appConfigRepository.getAppConfigByAppName(newChangeComm.getImpactedPp()[i]).getLobLead()+"@"+environment.getProperty("psa.mailing.domain"), 
							appConfigRepository.getAppConfigByAppName(newChangeComm.getImpactedPp()[i]).getPsm()+"@"+environment.getProperty("psa.mailing.domain"), 
							subject, body);
					if(("Mail Sending Failed").equals(messageResult) || ("Failed").equals(messageResult))
					{
						worklistRepository.delete(newWorklist);
						changeCommRepository.delete(newChangeComm);
						return result;
					}
					newWorklist=null;
				}
				else
				{
					changeCommRepository.delete(newChangeComm);
					return result;
				}
			}
			result = "{\"message\":\"Change Communication Submitted And Communicated To Stakeholders For Completion\"}";
		}
		
				
		return result;
	}

}
