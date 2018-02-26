package com.psa.application.emailTemplate;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.psa.application.model.ChangeComm;
import com.psa.application.model.DailyCallIncidentTracker;
import com.psa.application.model.IncidentComm;
import com.psa.application.repositories.EmailTemplateRepository;

@Component
public class IncMIMCommTemplate implements EmailTemplate{
	
	@Autowired
	EmailTemplateRepository emailTemplateRepository;
	
	

	@Override
	public String generateBody(String templateId, Object obj)
			throws JSONException {
		String bodyTemp ="";
		String bodyFinal="";
		bodyTemp = (emailTemplateRepository.getEmailTemplateByTemplateId(templateId)).getTemplate();
		bodyFinal = replacePlaceHolder(bodyTemp,(IncidentComm) obj);
		return bodyFinal;
		
	}

	@Override
	public String replacePlaceHolder(String body, DailyCallIncidentTracker newDailyCallIncidentTracker)
			throws JSONException {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public String replacePlaceHolder(String body, IncidentComm newIncidentComm) throws JSONException {
		
		String template="";
		template=body.replaceAll("@@incNum@@", newIncidentComm.getIncidentNum());
		template=template.replaceAll("@@incSeverity@@", newIncidentComm.getIncidentSeverity());
		template=template.replaceAll("@@date@@", newIncidentComm.getIncidentDate().toString());
		template=template.replaceAll("@@startTime@@", newIncidentComm.getImpactStartTime().toString());
		if(newIncidentComm.getImpactEndTime()!=null)
		{
			template=template.replaceAll("@@endTime@@", newIncidentComm.getImpactEndTime().toString());
		}
		else
		{
			template=template.replaceAll("@@endTime@@","TBD");
		}
		template=template.replaceAll("@@region@@", newIncidentComm.getImpactedRegion());
		String formattedImpactedCountry="";
		for(int i=0;i<newIncidentComm.getImpactedCountry().length;i++)
		{
			formattedImpactedCountry+=newIncidentComm.getImpactedCountry()[i]+",";
		}
		template=template.replaceAll("@@countries@@", formattedImpactedCountry);
		String formattedImpactedSector="";
		for(int i=0;i<newIncidentComm.getImpactedSector().length;i++)
		{
			formattedImpactedSector+=newIncidentComm.getImpactedSector()[i]+",";
		}
		template=template.replaceAll("@@sector@@", formattedImpactedSector);
		
		String formattedImpactedLob="";
		for(int i=0;i<newIncidentComm.getImpactedLob().length;i++)
		{
			formattedImpactedLob+=newIncidentComm.getImpactedLob()[i]+",";
		}
		template=template.replaceAll("@@lob@@", formattedImpactedLob);
		template=template.replaceAll("@@title@@", newIncidentComm.getTitle());
		template=template.replaceAll("@@description@@", newIncidentComm.getDescription());
		String[] impactArr=newIncidentComm.getImpact();
		String impact ="";
		for (int i = 0; i < impactArr.length; i++)
		{
			JSONObject obj = new JSONObject(impactArr[i]);
			impact += "<tr><td>"+obj.getString("channel")+"</td><td>"+obj.getString("natureOfImpact")+"</td><td>"+obj.get("volumeOfImpact").toString()+"</td></tr>";
		    
		}
		template=template.replaceAll("@@impact@@", impact);
		if(newIncidentComm.getFix()==null)
		{
		template=template.replaceAll("@@fix@@", "TBD");
		}
		else
		{
			template=template.replaceAll("@@fix@@",newIncidentComm.getFix() );
		}
		
		
		return template;
	}

	@Override
	public String replacePlaceHolder(String body, ChangeComm changeComm) throws JSONException {
		// TODO Auto-generated method stub
		return null;
	}

}
