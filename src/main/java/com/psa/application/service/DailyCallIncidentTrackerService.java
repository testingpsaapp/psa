package com.psa.application.service;


import javax.mail.MessagingException;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psa.application.emailTemplate.EmailTemplateGenerator;
import com.psa.application.mail.SendMail;
import com.psa.application.model.DailyCallIncidentTracker;
import com.psa.application.repositories.AppConfigRepository;
import com.psa.application.repositories.DailyCallIncidentTrackerRepository;

@Service
public class DailyCallIncidentTrackerService 
{
	@Autowired
	DailyCallIncidentTrackerRepository dailyCallIncidentTrackerRepository;
	
	@Autowired
	EmailTemplateGenerator emailTemplateGenerator;
	
	@Autowired
	SendMail sendMail;
	
	@Autowired
	AppConfigRepository appConfigRepository;
	
	public String saveDailyCallIncidentTracker (DailyCallIncidentTracker dailyCallIncidentTracker) throws MessagingException, JSONException
	{
		String result ="DailyCallIncidentTracker save failed!!";
		String mailResult ="Mail sending failed";
		DailyCallIncidentTracker newDailyCallIncidentTracker = dailyCallIncidentTrackerRepository.saveAndFlush(dailyCallIncidentTracker);
		
		//add mailing logic here 
		
		String body = emailTemplateGenerator.getEmailBody("dcit_temp",newDailyCallIncidentTracker);
		String[] toPP = newDailyCallIncidentTracker.getPpInvolved().split("|");
		String to="";
		String cc="";
		String bcc="";
		String subject =newDailyCallIncidentTracker.getIncNum()+"-"+newDailyCallIncidentTracker.getIncidentPriority()+"-"+
				newDailyCallIncidentTracker.getImpactingCtry()+"-"+"Business Critical Incident";
		
		for(int i =0; i<toPP.length;i++)
		{
			to = to + appConfigRepository.getAppConfigByAppName(toPP[i]).getLobLead()+",";
			cc = cc + appConfigRepository.getAppConfigByAppName(toPP[i]).getPsm()+",";
		}
		
		mailResult = sendMail.sendMail(to, cc, bcc, subject, body);
		
		if(newDailyCallIncidentTracker!=null && mailResult.equals("Mail sending successful"))
		{
			result ="DailyCallIncidentTracker save successful!!";
		}
		return result;
	}
	
	public DailyCallIncidentTracker getDailyCallIncidentTrackerByIncNum(String incNum)
	{
		DailyCallIncidentTracker newDailyCallIncidentTracker= dailyCallIncidentTrackerRepository.getDailyCallIncidentTrackerByIncNum(incNum);
		return newDailyCallIncidentTracker;
	}
}
