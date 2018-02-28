package com.psa.application.service;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.psa.application.emailTemplate.EmailTemplateGenerator;
import com.psa.application.mail.SendMail;
import com.psa.application.model.AppConfig;
import com.psa.application.model.BriefPapPub;
import com.psa.application.model.Worklist;
import com.psa.application.repositories.AppConfigRepository;
import com.psa.application.repositories.BriefPapPubRepository;
import com.psa.application.repositories.CountryRepository;
import com.psa.application.repositories.WorklistRepository;

@Service
public class BriefingPaperPublisherService {
	
	@Autowired
	BriefPapPubRepository briefPapPubRepository;
	
	@Autowired
	WorklistRepository worklistRepository;
	
	@Autowired
	AppConfigRepository appConfigRepository;
	
	@Autowired
	Environment environment;
	
	@Autowired
	EmailTemplateGenerator emailTemplateGenerator;
	
	@Autowired
	CountryRepository countryRepository;
	
	@Autowired
	SendMail sendMail;
	
	public String review(BriefPapPub briefPapPub) throws JSONException, MessagingException {
		// TODO Auto-generated method stub
		String result ="{\"message\":\"Briefing Paper Submission Failed\"}";
		//Step 1 briefing paper table entry
		BriefPapPub newBriefPapPub = briefPapPubRepository.saveAndFlush(briefPapPub);
		String[] impact = newBriefPapPub.getImpact();
		String to = "";	
		String cc = null;	
		String bcc = null;
		String body ="";
		String subject = "";
		for(int i=0;i< impact.length;i++)
		{
			//Step 2 worklist creation
			JSONObject obj = new JSONObject(impact[i]);
			String impactedApp = obj.getString("channel");
			Worklist worklist = new Worklist();
			worklist.setStatus("Active");
			worklist.setTaskId("Pending LOB Lead Review - "+newBriefPapPub.getMimNum());
			worklist.setTaskName("Pending LOB Lead Review - "+newBriefPapPub.getMimNum());
			AppConfig appConfig = appConfigRepository.getAppConfigByAppName(impactedApp);
			worklist.setTaskOwner(appConfig.getLobLead());
			worklist.setLink("http://localhost:8080/index.html#!/briefingPaperPublisher?action=briefingPaperReviewLobLead&mimNum="+newBriefPapPub.getMimNum()+"&loblead="+appConfig.getLobLead()+"&appName="+impactedApp);
			worklistRepository.saveAndFlush(worklist);
			//Step 3 mailing
			to= appConfig.getLobLead()+"@"+environment.getProperty("psa.mailing.domain");
			body=emailTemplateGenerator.getEmailBody("briefing_paper_review", newBriefPapPub.getMimNum());
			body=body.replaceAll("@@link@@", worklist.getLink());
			subject="Action Needed - Review Briefing Paper For "+newBriefPapPub.getMimNum();
			String mailResult=sendMail.sendMail(to, cc, bcc, subject, body);
			if(("Mail Sending Successful").equalsIgnoreCase(mailResult))
			{
				result="{\"message\":\"Briefing Paper Submission Successful\"}";
			}
		}
		
		return result;
	}

	public BriefPapPub getBriefingPaper(String mimNum) {
		BriefPapPub newBriefPapPub= briefPapPubRepository.findBriefPapPubByMimNum(mimNum);
		return newBriefPapPub;
	}

	public String reviewLobLeadSubmit(BriefPapPub briefPapPub,String mimNum, String reviewer, String appName) throws JSONException, MessagingException {
		String result ="{\"message\":\"Briefing Paper Submission Failed\"}";
		//Step 1 briefing paper table entry
		BriefPapPub newBriefPapPub = briefPapPubRepository.saveAndFlush(briefPapPub);
		List<Worklist> listOfWorklist = worklistRepository.getBriefingPaperTaskForReviewByLOBLead("Pending LOB Lead Review - "+newBriefPapPub.getMimNum());
		for(Worklist worklist : listOfWorklist)
		{
			if(worklist.getLink().contains(appName))
			{
				worklist.setStatus("COMPLETED");
				worklistRepository.saveAndFlush(worklist);
				break;
			}
		}
		String psm = appConfigRepository.getAppConfigByAppName(appName).getPsm();
		Worklist newWorkList = new Worklist();
		newWorkList.setStatus("Active");
		newWorkList.setTaskId("Pending PSM Review - "+newBriefPapPub.getMimNum());
		newWorkList.setTaskName("Pending PSM Review - "+newBriefPapPub.getMimNum());
		newWorkList.setTaskOwner(psm);
		newWorkList.setLink("http://localhost:8080/index.html#!/briefingPaperPublisher?action=briefingPaperReviewPSM&mimNum="+newBriefPapPub.getMimNum()+"&loblead="+psm+"&appName="+appName);
		worklistRepository.saveAndFlush(newWorkList);
		String to = "";	
		String cc = null;	
		String bcc = null;
		String body ="";
		String subject = "";
		
		to= psm+"@"+environment.getProperty("psa.mailing.domain");
		body=emailTemplateGenerator.getEmailBody("briefing_paper_review", newBriefPapPub.getMimNum());
		body=body.replaceAll("@@link@@", newWorkList.getLink());
		subject="Action Needed - Review Briefing Paper For "+newBriefPapPub.getMimNum();
		String mailResult=sendMail.sendMail(to, cc, bcc, subject, body);
		if(("Mail Sending Successful").equalsIgnoreCase(mailResult))
		{
			result="{\"message\":\"Briefing Paper Review Successful. Its Pending Approval From Your PSM\"}";
		}
		return result;
	}
	
	public String reviewPSMSubmit(BriefPapPub briefPapPub,String mimNum, String reviewer, String appName) throws JSONException, MessagingException {
		String result ="{\"message\":\"Briefing Paper Submission Failed\"}";
		//Step 1 briefing paper table entry
		BriefPapPub newBriefPapPub = briefPapPubRepository.saveAndFlush(briefPapPub);
		List<Worklist> listOfWorklist = worklistRepository.getBriefingPaperTaskForReviewByLOBLead("Pending PSM Review - "+newBriefPapPub.getMimNum());
		for(Worklist worklist : listOfWorklist)
		{
			if(worklist.getLink().contains(appName))
			{
				worklist.setStatus("COMPLETED");
				worklistRepository.saveAndFlush(worklist);
				break;
			}
		}
		String pssm = appConfigRepository.getAppConfigByAppName(appName).getPssm();
		Worklist newWorkList = new Worklist();
		newWorkList.setStatus("Active");
		newWorkList.setTaskId("Pending PSSM Review - "+newBriefPapPub.getMimNum());
		newWorkList.setTaskName("Pending PSSM Review - "+newBriefPapPub.getMimNum());
		newWorkList.setTaskOwner(pssm);
		newWorkList.setLink("http://localhost:8080/index.html#!/briefingPaperPublisher?action=briefingPaperReviewPSSM&mimNum="+newBriefPapPub.getMimNum()+"&loblead="+pssm+"&appName="+appName);
		worklistRepository.saveAndFlush(newWorkList);
		String to = "";	
		String cc = null;	
		String bcc = null;
		String body ="";
		String subject = "";
		
		to= pssm+"@"+environment.getProperty("psa.mailing.domain");
		body=emailTemplateGenerator.getEmailBody("briefing_paper_review", newBriefPapPub.getMimNum());
		body=body.replaceAll("@@link@@", newWorkList.getLink());
		subject="Action Needed - Review Briefing Paper For "+newBriefPapPub.getMimNum();
		String mailResult=sendMail.sendMail(to, cc, bcc, subject, body);
		if(("Mail Sending Successful").equalsIgnoreCase(mailResult))
		{
			result="{\"message\":\"Briefing Paper Review Submission Successful. Its pending approval from your PSSM\"}";
		}
		return result;
	}
	public String reviewPSSMSubmit(BriefPapPub briefPapPub,String mimNum, String reviewer, String appName) throws JSONException, MessagingException
	{
		String result ="{\"message\":\"Briefing Paper Submission Failed\"}";
		BriefPapPub newBriefPapPub = briefPapPubRepository.saveAndFlush(briefPapPub);
		List<Worklist> listOfWorklist = worklistRepository.getBriefingPaperTaskForReviewByLOBLead("Pending PSSM Review - "+newBriefPapPub.getMimNum());
		for(Worklist worklist : listOfWorklist)
		{
			if(worklist.getLink().contains(appName))
			{
				worklist.setStatus("COMPLETED");
				worklistRepository.saveAndFlush(worklist);
				result ="{\"message\":\"Briefing Paper Submission Completed. Briefing Paper is yet to be published to business.\"}";
				break;
			}
		}
		List<Worklist> listOfNewWorklist = worklistRepository.findAllActive();
		int flag=0;
		for(Worklist worklist:listOfNewWorklist)
		{
			if(worklist.getTaskId().contains(mimNum) && worklist.getLink().contains("briefingPaper"))
			{
				flag=1;
				break;
			}
		}
		if(flag==0)
		{
			List<String> to =new ArrayList<String>();
			List<String> cc =new ArrayList<String>();
			List<String> bcc =new ArrayList<String>();
			String subject="Briefing Paper - "+briefPapPub.getMimNum();
			String body=emailTemplateGenerator.getEmailBody("briefing_paper_business", briefPapPub);
			String[] impactedCountries = briefPapPub.getImpactedCountry();
			for(int i =0; i<impactedCountries.length;i++)
			{
				to.add(countryRepository.findByCountryName(impactedCountries[i]).getCountryDlist());
				cc.add(countryRepository.findByCountryName(impactedCountries[i]).getTechHead()+"@"+environment.getProperty("psa.mailing.domain"));
				bcc.add(countryRepository.findByCountryName(impactedCountries[i]).getOntHead()+"@"+environment.getProperty("psa.mailing.domain"));
				bcc.add(countryRepository.findByCountryName(impactedCountries[i]).getCbmHead()+"@"+environment.getProperty("psa.mailing.domain"));
			}
			String[] toMailId = new String[to.size()];
			toMailId = to.toArray(toMailId);
			String[] ccMailId = new String[cc.size()];
			ccMailId = cc.toArray(ccMailId);
			String[] bccMailId = new String[bcc.size()];
			bccMailId = bcc.toArray(bccMailId);
			String message=sendMail.sendMailToMultiple(toMailId, ccMailId, bccMailId, subject, body);
			if(("Mail Sending Successful").equals(message))
			{
				result ="{\"message\":\"Briefing Paper Submission Successful. Briefing is published to business.\"}";
			}
		}
		return result;
	}
}
