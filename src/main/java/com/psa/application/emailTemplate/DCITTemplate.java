package com.psa.application.emailTemplate;

import org.json.JSONException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.psa.application.model.DailyCallIncidentTracker;
import com.psa.application.model.IncidentComm;
import com.psa.application.repositories.EmailTemplateRepository;

@Component
public class DCITTemplate implements EmailTemplate{

	@Autowired
	EmailTemplateRepository emailTemplateRepository;
	
	
	@Override
	public String generateBody(String templateId,Object obj) throws JSONException {
		
		String bodyTemp ="";
		String bodyFinal="";
		bodyTemp = emailTemplateRepository.getEmailTemplateByTemplateId(templateId).getTemplate();
		bodyFinal = replacePlaceHolder(bodyTemp,(DailyCallIncidentTracker)obj);
		return bodyFinal;
	}

	@Override
	public String replacePlaceHolder(String body,DailyCallIncidentTracker newDailyCallIncidentTracker) throws JSONException {
		
		String bodyTemp = body.replace("@@IncNum@@", newDailyCallIncidentTracker.getIncNum());
		bodyTemp=bodyTemp.replace("@@Country@@", newDailyCallIncidentTracker.getImpactingCtry());
		bodyTemp=bodyTemp.replace("@@Department@@",newDailyCallIncidentTracker.getImpactingDept());
		bodyTemp=bodyTemp.replace("@@ImpactStatement@@",newDailyCallIncidentTracker.getImpact());
		bodyTemp=bodyTemp.replace("@@ReasonOfCriticality@@", newDailyCallIncidentTracker.getReasonOfCriticality());
		bodyTemp=bodyTemp.replace("@@IncPriority@@", newDailyCallIncidentTracker.getIncidentPriority());
		bodyTemp=bodyTemp.replace("@@ActionItems@@", newDailyCallIncidentTracker.getActionItem());
		return bodyTemp;
	}

	@Override
	public String replacePlaceHolder(String body, IncidentComm newIncidentComm) throws JSONException {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
