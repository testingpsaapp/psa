package com.psa.application.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.psa.application.model.EmailList;
import com.psa.application.model.IncidentComm;
import com.psa.application.model.IncidentCommImpact;
import com.psa.application.repositories.AppConfigRepository;
import com.psa.application.repositories.CountryRepository;
import com.psa.application.repositories.IncidentCommRepository;



@Service
public class EmailListService {
	
	@Autowired
	IncidentCommRepository incidentCommRepository;
	
	@Autowired
	CountryRepository countryRepository;
	
	@Autowired
	AppConfigRepository appConfigRepository;
	
	@Autowired
	Environment environment;
	
	
	public EmailList getEmailListForIncidentCommunication(String incNum)
	{
		EmailList emailList = new EmailList();
		List<String> listOfToMailId = new ArrayList<String>();
		List<String> listOfCCMailId = new ArrayList<String>();
		List<String> listOfBCCMailId = new ArrayList<String>();
		
		//Step 1 retrieve inc details 
		IncidentComm incidentComm= incidentCommRepository.findIncCommByIncNum(incNum);
		
		//Step 2 extract country from inc details and respective tech/ont/cbm details
		for(int i=0; i<incidentComm.getImpactedCountry().length;i++)
		{
			//Country DList Set
			listOfToMailId.add((countryRepository.findByCountryName(incidentComm.getImpactedCountry()[i])).getCountryDlist());
			
			//Tech Head Set
			listOfCCMailId.add((countryRepository.findByCountryName(incidentComm.getImpactedCountry()[i])).getTechHead()+"@"+environment.getProperty("psa.mailing.domain"));
			
			//ONT Head Set
			listOfBCCMailId.add((countryRepository.findByCountryName(incidentComm.getImpactedCountry()[i])).getOntHead()+"@"+environment.getProperty("psa.mailing.domain"));
			
			//CBM Set
			listOfBCCMailId.add((countryRepository.findByCountryName(incidentComm.getImpactedCountry()[i])).getCbmHead()+"@"+environment.getProperty("psa.mailing.domain"));
		}
		
		//Step 3 extract list of impacted application DList / LOB Lead / PSM / PSSM
		for(int i=0;i<incidentComm.getImpact().length;i++)
		{
			Gson gsonObj = new Gson(); 
			IncidentCommImpact incidentCommImpact = gsonObj.fromJson(incidentComm.getImpact()[i], IncidentCommImpact.class);
			listOfToMailId.add((appConfigRepository.getAppConfigByAppName(incidentCommImpact.getChannel())).getdList()+"@"+environment.getProperty("psa.mailing.domain"));
			
			listOfCCMailId.add((appConfigRepository.getAppConfigByAppName(incidentCommImpact.getChannel())).getLobLead()+"@"+environment.getProperty("psa.mailing.domain"));
			listOfCCMailId.add((appConfigRepository.getAppConfigByAppName(incidentCommImpact.getChannel())).getPsm()+"@"+environment.getProperty("psa.mailing.domain"));
			listOfCCMailId.add((appConfigRepository.getAppConfigByAppName(incidentCommImpact.getChannel())).getPssm()+"@"+environment.getProperty("psa.mailing.domain"));
			
			/*Application Owner to be added*/
					
		}
		
		//Step 4 set default CC and BCC Mail Ids
		//listOfCCMailId.add(environment.getProperty("psa.mailing.default.ccemail"));
		//listOfBCCMailId.add(environment.getProperty("psa.mailing.default.bccemail"));
		
		String[] toMailId = new String[listOfToMailId.size()];
		toMailId = listOfToMailId.toArray(toMailId);
		String[] ccMailId = new String[listOfCCMailId.size()];
		ccMailId = listOfCCMailId.toArray(ccMailId);
		String[] bccMailId = new String[listOfBCCMailId.size()];
		bccMailId = listOfBCCMailId.toArray(bccMailId);
		
		emailList.setListOfToMailId(toMailId);
		emailList.setListOfCCMailId(ccMailId);
		emailList.setListOfBCCMailId(bccMailId);
		return emailList;
	}

}
