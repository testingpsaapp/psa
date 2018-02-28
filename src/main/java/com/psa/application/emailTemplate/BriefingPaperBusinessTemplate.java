package com.psa.application.emailTemplate;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.psa.application.model.BriefPapPub;
import com.psa.application.model.ChangeComm;
import com.psa.application.model.DailyCallIncidentTracker;
import com.psa.application.model.IncidentComm;
import com.psa.application.repositories.EmailTemplateRepository;
@Component
public class BriefingPaperBusinessTemplate implements EmailTemplate{

	@Autowired
	EmailTemplateRepository emailTemplateRepository;
	
	@Override
	public String generateBody(String templateId, Object obj) throws JSONException {
		String bodyTemp ="";
		String bodyFinal="";
		bodyTemp = (emailTemplateRepository.getEmailTemplateByTemplateId(templateId)).getTemplate();
		bodyFinal = replacePlaceHolder(bodyTemp,(BriefPapPub) obj);
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
		return null;
	}

	@Override
	public String replacePlaceHolder(String body, BriefPapPub briefPapPub) throws JSONException {
		String template="";
		template=body.replaceAll("@@incNum@@", briefPapPub.getMimNum());
		template=template.replaceAll("@@incSeverity@@", briefPapPub.getSeverity());
		template=template.replaceAll("@@date@@", briefPapPub.getMimDate());
		template=template.replaceAll("@@startTime@@", briefPapPub.getMimStartDate());
		if(briefPapPub.getMimEndDate()!=null)
		{
			template=template.replaceAll("@@endTime@@", briefPapPub.getMimEndDate());
		}
		else
		{
			template=template.replaceAll("@@endTime@@","TBD");
		}
		template=template.replaceAll("@@region@@", briefPapPub.getImpactedRegion());
		String formattedImpactedCountry="";
		for(int i=0;i<briefPapPub.getImpactedCountry().length;i++)
		{
			formattedImpactedCountry+=briefPapPub.getImpactedCountry()[i]+",";
		}
		template=template.replaceAll("@@countries@@", formattedImpactedCountry);
		String formattedImpactedSector="";
		for(int i=0;i<briefPapPub.getImpactedSector().length;i++)
		{
			formattedImpactedSector+=briefPapPub.getImpactedSector()[i]+",";
		}
		template=template.replaceAll("@@sector@@", formattedImpactedSector);
		
		String formattedImpactedLob="";
		for(int i=0;i<briefPapPub.getImpactedLob().length;i++)
		{
			formattedImpactedLob+=briefPapPub.getImpactedLob()[i]+",";
		}
		template=template.replaceAll("@@lob@@", formattedImpactedLob);
		template=template.replaceAll("@@title@@", briefPapPub.getTitle());
		template=template.replaceAll("@@description@@", briefPapPub.getDescription());
		String[] impactArr=briefPapPub.getImpact();
		String impact ="";
		for (int i = 0; i < impactArr.length; i++)
		{
			JSONObject obj = new JSONObject(impactArr[i]);
			impact += "<tr><td>"+obj.getString("channel")+"</td><td>"+obj.getString("natureOfImpact")+"</td><td>"+obj.get("volumeOfImpact").toString()+"</td></tr>";
		    
		}
		template=template.replaceAll("@@impact@@", impact);
		if(briefPapPub.getFix()==null)
		{
		template=template.replaceAll("@@fix@@", "TBD");
		}
		else
		{
			template=template.replaceAll("@@fix@@",briefPapPub.getFix() );
		}
		
		
		return template;
	}

}
