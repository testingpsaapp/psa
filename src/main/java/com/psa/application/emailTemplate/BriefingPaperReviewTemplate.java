package com.psa.application.emailTemplate;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.psa.application.model.BriefPapPub;
import com.psa.application.model.ChangeComm;
import com.psa.application.model.DailyCallIncidentTracker;
import com.psa.application.model.IncidentComm;
import com.psa.application.repositories.EmailTemplateRepository;

@Component
public class BriefingPaperReviewTemplate implements EmailTemplate{

	@Autowired
	EmailTemplateRepository emailTemplateRepository;
	
	@Override
	public String generateBody(String templateId, Object obj) throws JSONException {
		// TODO Auto-generated method stub
		String body =emailTemplateRepository.getEmailTemplateByTemplateId(templateId).getTemplate();
		body=body.replaceAll("@@MIMNum@@", (String)obj);
		return body;
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

	@Override
	public String replacePlaceHolder(String body, ChangeComm changeComm) throws JSONException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String replacePlaceHolder(String body, BriefPapPub briefPapPub) throws JSONException {
		return null;
	}

}
