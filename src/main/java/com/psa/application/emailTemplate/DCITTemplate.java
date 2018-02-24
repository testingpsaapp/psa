package com.psa.application.emailTemplate;

import org.json.JSONException;
import org.json.JSONObject;
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
		String actionItem = "<table border=\"1\"><tbody><tr><td>Action Item</td><td>Owner</td><td>ETA</td></tr>"; 
		JSONObject obj = new JSONObject(newDailyCallIncidentTracker.getActionItem());
		String[] actItems = obj.get("ActionItem").toString().split("||");
		String[] owners = obj.get("Owner").toString().split("||");
		String[] etas = obj.get("ETA").toString().split("||");
		String actionTable="";
		for(int i = 0; i<actItems.length;i++)
		{
			actionTable+="<tr><td>"+actItems[i]+"</td><td>"+owners[i]+"</td><td>"+etas[i]+"</td></tr>";
		}
		actionItem+=actionTable+"</tbody></table>";
		bodyTemp=bodyTemp.replace("@@ActionItems@@", actionItem);
		return bodyTemp;
	}

	@Override
	public String replacePlaceHolder(String body, IncidentComm newIncidentComm) throws JSONException {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
