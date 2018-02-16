package com.psa.application.emailTemplate;

import org.json.JSONException;
import org.springframework.stereotype.Component;

import com.psa.application.model.DailyCallIncidentTracker;

@Component
public class EmailTemplateGenerator{

	public String getEmailBody(String templateId,DailyCallIncidentTracker newDailyCallIncidentTracker) throws JSONException
	{
		String body ="";
		if(templateId.equals("dcit_temp"))
		{
			EmailTemplate emailTemplate = new DCITTemplate();
			body = emailTemplate.generateBody(templateId,newDailyCallIncidentTracker);
		}
		return body;
	}
}
