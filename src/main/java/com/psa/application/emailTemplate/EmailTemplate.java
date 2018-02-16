package com.psa.application.emailTemplate;

import org.json.JSONException;

import com.psa.application.model.DailyCallIncidentTracker;

public interface EmailTemplate 
{
	public String generateBody(String templateId,DailyCallIncidentTracker newDailyCallIncidentTracker) throws JSONException;
	public String replacePlaceHolder(String body,DailyCallIncidentTracker newDailyCallIncidentTracker) throws JSONException;
}
