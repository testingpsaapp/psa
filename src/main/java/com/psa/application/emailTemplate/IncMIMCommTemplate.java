package com.psa.application.emailTemplate;

import org.json.JSONException;

import com.psa.application.model.DailyCallIncidentTracker;
import com.psa.application.model.IncidentComm;

public class IncMIMCommTemplate implements EmailTemplate{

	@Override
	public String generateBody(String templateId, DailyCallIncidentTracker newDailyCallIncidentTracker)
			throws JSONException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String replacePlaceHolder(String body, DailyCallIncidentTracker newDailyCallIncidentTracker)
			throws JSONException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String replacePlaceHolder(String body, IncidentComm newIncidentComm) throws JSONException {
		// TODO Auto-generated method stub
		return null;
	}

}
