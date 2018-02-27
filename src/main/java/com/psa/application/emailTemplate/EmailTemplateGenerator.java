package com.psa.application.emailTemplate;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.psa.application.model.ChangeComm;
import com.psa.application.model.DailyCallIncidentTracker;
import com.psa.application.model.IncidentComm;

@Component
public class EmailTemplateGenerator{ 
	
	@Autowired
	IncMIMCommTemplate newIncMIMCommTemplate;
	
	@Autowired
	DCITTemplate newDCITTemplate;
	
	@Autowired
	ChangeCommTemplate changeCommTemplate;
	
	@Autowired 
	ChangeCommBusinessTemplate changeCommBusinessTemplate;
	
	
	public String getEmailBody(String templateId,Object obj) throws JSONException
	{
		String body ="";
		if(templateId.equals("dcit_temp"))
		{
			EmailTemplate emailTemplate = newDCITTemplate;
			DailyCallIncidentTracker dcit = (DailyCallIncidentTracker)obj;
			body = emailTemplate.generateBody(templateId,dcit);
		}
		else if(templateId.equals("inc_comm"))
		{
			EmailTemplate emailTemplate = newIncMIMCommTemplate;
			IncidentComm newIncidentComm=(IncidentComm)obj;
			body = emailTemplate.generateBody(templateId,newIncidentComm);
		}
		else if(templateId.equals("inc_comm_a"))
		{
			EmailTemplate emailTemplate = newIncMIMCommTemplate;
			IncidentComm newIncidentComm=(IncidentComm)obj;
			body = emailTemplate.generateBody(templateId,newIncidentComm);
		}
		else if(templateId.equals("change_comm_sanity"))
		{
			EmailTemplate emailTemplate = changeCommBusinessTemplate;
			ChangeComm newChangeComm=(ChangeComm)obj;
			body = emailTemplate.generateBody(templateId,newChangeComm);
		}
		else if(templateId.equals("change_comm_biz_sanity"))
		{
			EmailTemplate emailTemplate = changeCommBusinessTemplate;
			ChangeComm newChangeComm=(ChangeComm)obj;
			body = emailTemplate.generateBody(templateId,newChangeComm);
		}
		return body;
	}
}
