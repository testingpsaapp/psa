package com.psa.application.service;


import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
	
	@Autowired
	Environment environment;
	
	public String saveDailyCallIncidentTracker (DailyCallIncidentTracker dailyCallIncidentTracker) throws MessagingException, JSONException
	{
		String result ="{\"message\":\"Daily Call Incident Tracker Save Failed\"}";
		String mailResult ="Mail sending failed";
		DailyCallIncidentTracker newDailyCallIncidentTracker = dailyCallIncidentTrackerRepository.saveAndFlush(dailyCallIncidentTracker);
		
		//add mailing logic here 
		
		String body = emailTemplateGenerator.getEmailBody("dcit_temp",newDailyCallIncidentTracker);
		String[] toPP = newDailyCallIncidentTracker.getPpInvolved();
		List<String> toEmailIds =new ArrayList<String> ();
		List<String> ccEmailIds =new ArrayList<String> ();
		List<String> bccEmailIds =new ArrayList<String> ();
		String subject =newDailyCallIncidentTracker.getIncNum()+"-"+newDailyCallIncidentTracker.getIncidentPriority()+"-"+
				newDailyCallIncidentTracker.getImpactingCtry()+"-"+"Business Critical Incident";
		
		for(int i =0; i<toPP.length;i++)
		{
			toEmailIds.add(appConfigRepository.getAppConfigByAppName(toPP[i]).getdList());
			ccEmailIds.add(appConfigRepository.getAppConfigByAppName(toPP[i]).getLobLead()+"@"+environment.getProperty("psa.mailing.domain"));
			bccEmailIds.add(appConfigRepository.getAppConfigByAppName(toPP[i]).getPsm()+"@"+environment.getProperty("psa.mailing.domain"));
			bccEmailIds.add(appConfigRepository.getAppConfigByAppName(toPP[i]).getPssm()+"@"+environment.getProperty("psa.mailing.domain"));
		}
		
		String[] to = new String[toEmailIds.size()];
		to = toEmailIds.toArray(to);
		String[] cc = new String[ccEmailIds.size()];
		cc = ccEmailIds.toArray(cc);
		String[] bcc = new String[bccEmailIds.size()];
		bcc = bccEmailIds.toArray(bcc);
		mailResult = sendMail.sendMailToMultiple(to, cc, bcc, subject, body);
		
		if(newDailyCallIncidentTracker!=null && mailResult.equals("Mail Sending Successful"))
		{
			result ="{\"message\":\"Daily Call Incident Tracker Save Successful\"}";
		}
		System.out.println(result);
		return result;
	}
	
	public DailyCallIncidentTracker getDailyCallIncidentTrackerByIncNum(String incNum)
	{
		DailyCallIncidentTracker newDailyCallIncidentTracker= dailyCallIncidentTrackerRepository.getDailyCallIncidentTrackerByIncNum(incNum);
		return newDailyCallIncidentTracker;
	}
}
