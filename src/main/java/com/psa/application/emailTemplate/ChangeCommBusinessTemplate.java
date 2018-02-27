package com.psa.application.emailTemplate;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.psa.application.model.ChangeComm;
import com.psa.application.model.DailyCallIncidentTracker;
import com.psa.application.model.IncidentComm;
import com.psa.application.repositories.EmailTemplateRepository;

@Component
public class ChangeCommBusinessTemplate implements EmailTemplate{

	@Autowired
	EmailTemplateRepository emailTemplateRepository;
	
	@Override
	public String generateBody(String templateId, Object obj) throws JSONException {
		String bodyTemp ="";
		String bodyFinal="";
		bodyTemp = emailTemplateRepository.getEmailTemplateByTemplateId(templateId).getTemplate();
		bodyFinal = replacePlaceHolder(bodyTemp,(ChangeComm)obj);
		return bodyFinal;
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
		String bodyTemp = body.replace("@@ChangeOwner@@", changeComm.getChangeOwner());
		bodyTemp = bodyTemp.replace("@@ChangeNumber@@", changeComm.getChangNum());
		bodyTemp = bodyTemp.replace("@@ChangeTitle@@", changeComm.getChangeTitle());
		bodyTemp = bodyTemp.replace("@@ChangeDescription@@", changeComm.getChangeDesc());
		bodyTemp = bodyTemp.replace("@@ChangeDate@@", changeComm.getChangDate());
		bodyTemp = bodyTemp.replace("@@ChangeStartTime@@", changeComm.getChangStartTime());
		bodyTemp = bodyTemp.replace("@@ChangeEndTime@@", changeComm.getChangeEndTime());
		
		return bodyTemp;
	}

}
