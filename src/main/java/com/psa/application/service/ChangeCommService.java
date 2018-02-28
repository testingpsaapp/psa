package com.psa.application.service;

import java.util.List;

import javax.mail.MessagingException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.psa.application.emailTemplate.EmailTemplateGenerator;
import com.psa.application.mail.SendMail;
import com.psa.application.model.ChangeComm;
import com.psa.application.model.Countries;
import com.psa.application.model.Worklist;
import com.psa.application.repositories.AppConfigRepository;
import com.psa.application.repositories.ChangeCommRepository;
import com.psa.application.repositories.CountryRepository;
import com.psa.application.repositories.WorklistRepository;

@Service
public class ChangeCommService {
	
	@Autowired
	ChangeCommRepository changeCommRepository;
	
	@Autowired
	AppConfigRepository appConfigRepository;
	
	@Autowired
	WorklistRepository worklistRepository;
	
	@Autowired
	EmailTemplateGenerator emailTemplateGenerator;
	
	@Autowired
	SendMail sendMail;
	
	@Autowired
	Environment environment;
	
	@Autowired
	CountryRepository countryRepository;
	
	public String submitForCompletion(ChangeComm changeComm) throws JSONException, MessagingException
	{
		String result ="{\"message\":\"Change Communication Submission Failed\"}";
		String messageResult="Failed";
		//Step 1 Save Comm in db
		ChangeComm newChangeComm = null;
		newChangeComm = changeCommRepository.saveAndFlush(changeComm);
		System.out.println("Save Comm Step 1 Completed");
		//Step 2 Worklist entries
		Worklist newWorklist;
		if(newChangeComm !=null)
		{
			for(int i=0;i<newChangeComm.getImpactedPp().length;i++)
			{
				String taskOwner=appConfigRepository.getAppConfigByAppName(newChangeComm.getImpactedPp()[i]).getLobLead();
				Worklist worklist =new Worklist();
				newWorklist =new Worklist();
				worklist.setTaskId(newChangeComm.getChangNum());
				worklist.setTaskName(newChangeComm.getChangNum()+"- Sanity Scope Submission Pending");
				worklist.setStatus("ACTIVE");
				worklist.setTaskOwner(taskOwner);
				worklist.setLink("http://localhost:8080/index.html#!/ChangeCommunication/SanityScope?appName="+newChangeComm.getImpactedPp()[i]+"&changeNum="+newChangeComm.getChangNum());
				newWorklist=worklistRepository.saveAndFlush(worklist);
				worklist=null;
				
				if(newWorklist!=null)
				{
					System.out.println("Save Comm Step 2 Completed");
					//Step 3 Mail Stake Holders
					String body= emailTemplateGenerator.getEmailBody("change_comm_sanity", newChangeComm);
					body = body.replaceAll("@@Link@@", newWorklist.getLink());
					body = body.replaceAll("@@AppName@@", newChangeComm.getImpactedPp()[i]);
					body = body.replaceAll("@@ImpactedPP@@", newChangeComm.getImpactedPp()[i]);
					System.out.println(body);
					String subject =newChangeComm.getChangNum()+"- Pending Action Item";
					messageResult=sendMail.sendMail(appConfigRepository.getAppConfigByAppName(newChangeComm.getImpactedPp()[i]).getdList()+"@"+environment.getProperty("psa.mailing.domain"), 
							appConfigRepository.getAppConfigByAppName(newChangeComm.getImpactedPp()[i]).getLobLead()+"@"+environment.getProperty("psa.mailing.domain"), 
							appConfigRepository.getAppConfigByAppName(newChangeComm.getImpactedPp()[i]).getPsm()+"@"+environment.getProperty("psa.mailing.domain"), 
							subject, body);
					
					if(("Mail Sending Failed").equals(messageResult) || ("Failed").equals(messageResult))
					{
						worklistRepository.delete(newWorklist);
						changeCommRepository.delete(newChangeComm);
						return result;
					}
					newWorklist=null;
				}
				else
				{
					changeCommRepository.delete(newChangeComm);
					return result;
				}
			}
			result = "{\"message\":\"Change Communication Submitted And Communicated To Stakeholders For Completion\"}";
		}
		
				
		return result;
	}

	public String[] getChangeCountryForSanityScope(String changeNum) {
		// TODO Auto-generated method stub
		String countries[];
		countries = changeCommRepository.findChangeCommByChangeNum(changeNum).getImpactedCountry();
		
		return countries;
	}

	public String submitScope(String[] sanityScope, String changeNum, String appName) throws MessagingException, JSONException {
		// TODO Auto-generated method stub
		String result="{\"message\":\"Sanity Scope Submission Failed\"}";
		//step 1 extract worklist with change num and appowner and look if active or not
		String taskOwner=appConfigRepository.getAppConfigByAppName(appName).getLobLead();
		System.out.println("'%"+appName+"%'");
		List<Worklist> listOfworklist = worklistRepository.findWorklistByTaskIdTaskOwnerAppName(changeNum,taskOwner);
		Worklist tempWorkList=null;
		if(listOfworklist!=null)
		{
			//step 2 if worklist active extract changecomm with changenumm and append sanity scope and update
			ChangeComm changeComm= changeCommRepository.findChangeCommByChangeNum(changeNum);
			for(Worklist worklist : listOfworklist)
			{
				if(worklist.getLink().contains("appName="+appName))
				{
					tempWorkList=worklist;
					break;
				}
			}
			
			int newSize =changeComm.getSanityScope().length + sanityScope.length;
			String[] sanityScope_temp = new String[ newSize ];
			for (int i=0; i < changeComm.getSanityScope().length; i++)
			{
				sanityScope_temp[i] = changeComm.getSanityScope()[i];
			}
			int j =0;
			for (int i=changeComm.getSanityScope().length; i < (changeComm.getSanityScope().length+sanityScope.length); i++)
			{
				sanityScope_temp[i] = sanityScope[j];
				j+=1;
			}
			changeComm.setSanityScope(sanityScope_temp);
			changeCommRepository.saveAndFlush(changeComm);
			//step 3 set worklist to completed
			tempWorkList.setStatus("COMPLETED");
			worklistRepository.saveAndFlush(tempWorkList);
			//step 4 check if any worklist for changenum is active
			if(worklistRepository.findWorklistByTaskIdStatus(changeNum)== null || worklistRepository.findWorklistByTaskIdStatus(changeNum).size()<=0)		
			{
				//step 5 if all worklist completed trigger mail to business
				//Step 5.1 find all impacted country from change
				String[] countriesInChange = changeComm.getImpactedCountry();
				//Step 5.2 for each country prepare separate body
				for(int i=0;i<countriesInChange.length;i++)
				{
					String country =countriesInChange[i];
					Countries countryObj=countryRepository.findByCountryName(country);
					String to = countryObj.getCountryDlist();
					String cc = countryObj.getTechHead()+"@"+environment.getProperty("psa.mailing.domain");
					String bcc = appConfigRepository.getAppConfigByAppName(changeComm.getChangeOwner()).getLobLead()+"@"+environment.getProperty("psa.mailing.domain");
					String subject = changeComm.getChangNum()+"- Business Sanity Communication";
					String body =emailTemplateGenerator.getEmailBody("change_comm_biz_sanity", changeComm);
					body=body.replace("@@Country@@", country);
					String countrySpecificSanity="<table><thead><tr><th>Application</th><th>Sanity Step</th>";//table header to be put in here
					String[] tempSanity =changeComm.getSanityScope();
					for(int k=0;k<tempSanity.length;k++)
					{
						JSONObject obj = new JSONObject(tempSanity[k]);
						String countryTemp=obj.getString("country");
						if(country.equals(countryTemp))
						{
							countrySpecificSanity+="<tr>"
									+ "<td>"+obj.getString("application")+"</td>"
									+ "<td>"+obj.getString("sanityStep")+"</td>"
									+ "</tr>";
						}
						//impact += "<tr><td>"+obj.getString("channel")+"</td><td>"+obj.getString("natureOfImpact")+"</td><td>"+obj.get("volumeOfImpact").toString()+"</td></tr>";
					}
					countrySpecificSanity+="</table></thead>";
					body=body.replaceAll("@@SanityScope@@", countrySpecificSanity);
					//Step 5.3 send mail
					sendMail.sendMail(to, cc, bcc, subject, body);
				}
			}
			result="{\"message\":\"Sanity Scope Submitted Successfully\"}";
		}
		else //step 1 else return corresponding workitem already completed
		{
			result="{\"message\":\"Sanity Scope Already Submitted\"}";
		}
		
		
		return result;
	}

	public String ignoreScope(String changeNum, String appName) throws MessagingException, JSONException {
		// TODO Auto-generated method stub
		
		String result="{\"message\":\"Sanity Scope Ignore Failed\"}";
		List<Worklist> listOfWorkList=null;
		listOfWorkList=worklistRepository.findWorklistByTaskIdStatus(changeNum);
		int flag=0;
		for(Worklist worklist:listOfWorkList)
		{
			if(worklist.getLink().contains("appName="+appName))
			{
				worklist.setStatus("COMPLETED");
				worklistRepository.saveAndFlush(worklist);
				result="{\"message\":\"Sanity Scope Ignore Successful. Workflow Updated.\"}";
				flag=1;
				break;
			}
		}
		if(flag==1)
		{
			List<Worklist> listOfNewWorkList=worklistRepository.findWorklistByTaskIdStatus(changeNum);
			if(listOfNewWorkList == null || listOfNewWorkList.size()==0)
			{
				ChangeComm changeComm= changeCommRepository.findChangeCommByChangeNum(changeNum);
				String[] countriesInChange = changeComm.getImpactedCountry();
				//Step 5.2 for each country prepare separate body
				for(int i=0;i<countriesInChange.length;i++)
				{
					String country =countriesInChange[i];
					Countries countryObj=countryRepository.findByCountryName(country);
					String to = countryObj.getCountryDlist();
					String cc = countryObj.getTechHead()+"@"+environment.getProperty("psa.mailing.domain");
					String bcc = appConfigRepository.getAppConfigByAppName(changeComm.getChangeOwner()).getLobLead()+"@"+environment.getProperty("psa.mailing.domain");
					String subject = changeComm.getChangNum()+"- Business Sanity Communication";
					String body =emailTemplateGenerator.getEmailBody("change_comm_biz_sanity", changeComm);
					body=body.replace("@@Country@@", country);
					String countrySpecificSanity="<table><thead><tr><th>Application</th><th>Sanity Step</th>";//table header to be put in here
					String[] tempSanity =changeComm.getSanityScope();
					for(int k=0; k<tempSanity.length;k++)
					{
						JSONObject obj = new JSONObject(tempSanity[k]);
						String countryTemp=obj.getString("country");
						if(country.equals(countryTemp))
						{
							countrySpecificSanity+="<tr>"
									+ "<td>"+obj.getString("application")+"</td>"
									+ "<td>"+obj.getString("sanityStep")+"</td>"
									+ "</tr>";
						}
						//impact += "<tr><td>"+obj.getString("channel")+"</td><td>"+obj.getString("natureOfImpact")+"</td><td>"+obj.get("volumeOfImpact").toString()+"</td></tr>";
					}
					countrySpecificSanity+="</table></thead>";
					body=body.replaceAll("@@SanityScope@@", countrySpecificSanity);
					//Step 5.3 send mail
					System.out.println(body);
					sendMail.sendMail(to, cc, bcc, subject, body);
				}
			}
		}
		return result;
	}

}
