package com.psa.application.emailTemplate;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.psa.application.model.DailyCallIncidentTracker;
import com.psa.application.model.IncidentComm;

@Component
public class EmailTemplateGenerator{
	
	@Autowired
	IncMIMCommTemplate newIncMIMCommTemplate;
	
	@Autowired
	DCITTemplate newDCITTemplate;

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
		return body;
	}
}
