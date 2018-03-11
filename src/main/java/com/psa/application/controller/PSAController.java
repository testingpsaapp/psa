package com.psa.application.controller;

import java.text.ParseException;
import java.util.List;

import javax.mail.MessagingException;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.psa.application.mail.SendMail;
import com.psa.application.model.AccessModule;
import com.psa.application.model.AppConfig;
import com.psa.application.model.BriefPapPub;
import com.psa.application.model.CLAAcessConfig;
import com.psa.application.model.ChangeComm;
import com.psa.application.model.CommDlistConfig;
import com.psa.application.model.Countries;
import com.psa.application.model.DailyCallIncidentTracker;
import com.psa.application.model.DailyIncAct;
import com.psa.application.model.EmailList;
import com.psa.application.model.IncidentComm;
import com.psa.application.model.LobConfig;
import com.psa.application.model.TriageLeadConfig;
import com.psa.application.model.User;
import com.psa.application.model.Worklist;
import com.psa.application.service.AccessModuleService;
import com.psa.application.service.AppConfigService;
import com.psa.application.service.BriefingPaperPublisherService;
import com.psa.application.service.CLAAccessConfigService;
import com.psa.application.service.ChangeCommService;
import com.psa.application.service.CommDlistConfigService;
import com.psa.application.service.CountryService;
import com.psa.application.service.DailyCallIncidentTrackerService;
import com.psa.application.service.DailyIncActService;
import com.psa.application.service.EmailListService;
import com.psa.application.service.IncidentCommService;
import com.psa.application.service.LobConfigService;
import com.psa.application.service.TriageLeadConfigService;
import com.psa.application.service.UserService;
import com.psa.application.service.WorklistService;
import com.psa.application.utilities.CommonUtilities;

@RestController
public class PSAController {
	
	@Autowired
	CommonUtilities commonUtilities; 
	
	@Autowired
	CommDlistConfigService commDlistConfigService;
	
	@Autowired
	AppConfigService appConfigService;
	
	@Autowired
	LobConfigService lobConfigService;
	
	@Autowired
	CLAAccessConfigService cLAAccessConfigService;
	
	@Autowired
	DailyIncActService dailyIncActService;
	
	@Autowired
	DailyCallIncidentTrackerService dailyCallIncidentTrackerService;
	
	@Autowired
	SendMail sendMail;
	
	@Autowired
	CountryService countryService;
	
	@Autowired
	TriageLeadConfigService triageLeadConfigService;
	
	@Autowired
	IncidentCommService incidentCommService;
	
	@Autowired
	AccessModuleService accessModuleService;
	
	@Autowired
	EmailListService emailListService;
	
	@Autowired 
	ChangeCommService changeCommService;
	
	@Autowired
	BriefingPaperPublisherService briefingPaperPublisherService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	WorklistService worklistService;
	
	@RequestMapping("/testing")
	public String test()
	{
		return "Testing PSA API";
	}
	
	
	/*
	 * CommDlistConfig service url mapping starts below
	 * */
	@RequestMapping(path="/commDlistConfig", method = RequestMethod.POST)
	public String saveCommDlistConfig(@RequestBody CommDlistConfig commDlistConfig)
	{
		String result = "";
		result = commDlistConfigService.save(commDlistConfig);
		return result;
	}
	
	@RequestMapping(path="/commDlistConfig/check", method = RequestMethod.POST)
	public String getCommDlistConfig(@RequestBody CommDlistConfig commDlistConfig)
	{
		String result = "";
		result = commDlistConfigService.getCommDlistConfig(commDlistConfig.getIncType(),
				commDlistConfig.getIncPriority(), commDlistConfig.getCommType(), commDlistConfig.getImpactedLob(), 
				commDlistConfig.getImpactedCtry());
		
		return result;
	}
	
	@RequestMapping(path="/commDlistConfig/all", method = RequestMethod.GET)
	public List<CommDlistConfig> getAllCommDlistConfig()
	{
		List<CommDlistConfig> listOfCommDlistConfig = null;
		listOfCommDlistConfig = commDlistConfigService.getAllCommDlistConfig();
		
		return listOfCommDlistConfig;
	}
	
	@RequestMapping(path="/commDlistConfig", method = RequestMethod.PUT)
	public String updateCommDlistConfig(@RequestBody CommDlistConfig commDlistConfig)
	{
		String result = "";
		result = commDlistConfigService.save(commDlistConfig);
		return result;
	}
	
	/*
	 * CommDlistConfig service url mapping ends
	 * */
	
	/*
	 * AppConfig service url mapping starts below
	 * */
	@RequestMapping(path="/appConfig", method = RequestMethod.POST)
	public String saveAppConfig(@RequestBody AppConfig appConfig)
	{
		String result = "";
		result = appConfigService.saveAppConfig(appConfig);
		return result;
	}
	
	@RequestMapping(path="/appConfig", method = RequestMethod.PUT)
	public String updateAppConfig(@RequestBody AppConfig appConfig)
	{
		String result = "";
		result = appConfigService.saveAppConfig(appConfig);
		return result;
	}
	
	@RequestMapping(path="/appConfig/all", method = RequestMethod.GET)
	public List<AppConfig> getAllAppConfig()
	{
		List<AppConfig> listOfAppConfig= null;
		listOfAppConfig = appConfigService.getAllAppConfig();
		return listOfAppConfig;
	}
	
	@RequestMapping(path="/appConfig", method = RequestMethod.GET)
	public String getAppConfig(@RequestBody AppConfig appConfig)
	{
		String result = "";
		result = appConfigService.getAppConfigByLobAppName(appConfig.getLob(),appConfig.getAppName());
		return result;
	}
	
	@RequestMapping(path="/appConfig/{lob}", method = RequestMethod.GET)
	public List<AppConfig> getAppConfigByLob(@PathVariable String lob)
	{
		List<AppConfig> listOfAppConfig= null;
		listOfAppConfig = appConfigService.getAppConfigByLobName(lob);
		return listOfAppConfig;
	}
	
	@RequestMapping(path="/appConfig/multiple", method = RequestMethod.POST)
	public List<AppConfig> getAppConfigByMultipleLob(@RequestBody String[] lob)
	{
		List<AppConfig> listOfAppConfig= null;
		listOfAppConfig = appConfigService.getAppConfigByMultipleLobName(lob);
		return listOfAppConfig;
	}
	
	/*
	 * AppConfig service url mapping ends
	 * */
	
	
	/*
	 * LobConfig service url mapping starts below
	 * */
	@RequestMapping(path="/lobConfig", method = RequestMethod.POST)
	public String saveLobConfig(@RequestBody LobConfig lobConfig)
	{
		String result = "";
		result = lobConfigService.saveLobConfig(lobConfig);
		return result;
	}
	
	@RequestMapping(path="/lobConfig/{soeId}", method = RequestMethod.GET)
	public LobConfig getLobConfigBySoeId(@PathVariable String soeId)
	{
		LobConfig lobConfig = null;
		lobConfig = lobConfigService.getLobConfigBySoeId(soeId);
		return lobConfig;
	}
	@RequestMapping(path="/lobConfig/all", method = RequestMethod.GET)
	public List<LobConfig> getAllLobConfig()
	{
		List<LobConfig> listOfLobConfig = null;
		listOfLobConfig = lobConfigService.getAllLobConfig();
		return listOfLobConfig;
	}
	
	@RequestMapping(path="/lobConfig", method = RequestMethod.PUT)
	public String updateLobConfig(@RequestBody LobConfig lobConfig)
	{
		String result = "";
		result = lobConfigService.saveLobConfig(lobConfig);
		return result;
	}
	
	
	/*
	 * LobConfig service url mapping ends
	 * */
	
	/*
	 * CLAAcessConfig service url mapping starts below
	 * */
	
	@RequestMapping(path="/cLAAcessConfig", method = RequestMethod.POST)
	public String saveCLAAcessConfig(@RequestBody CLAAcessConfig cLAAccessConfig)
	{
		String result = "";
		result = cLAAccessConfigService.saveCLAAcessConfig(cLAAccessConfig);
		System.out.println(result);
		return result;
	}
	
	@RequestMapping(path="/cLAAccessConfig/{soeId}", method = RequestMethod.GET)
	public CLAAcessConfig getcLAAccessConfigBySoeId(@PathVariable String soeId)
	{
		CLAAcessConfig cLAAccessConfig = null;
		cLAAccessConfig = cLAAccessConfigService.getCLAAcessConfigBySoeId(soeId);
		return cLAAccessConfig;
	}
	
	@RequestMapping(path="/cLAAcessConfig", method = RequestMethod.PUT)
	public String updateCLAAcessConfig(@RequestBody CLAAcessConfig cLAAccessConfig)
	{
		String result = "";
		result = cLAAccessConfigService.saveCLAAcessConfig(cLAAccessConfig);
		return result;
	}
	
	@RequestMapping(path="/cLAAcessConfig/all", method = RequestMethod.GET)
	public List<CLAAcessConfig> getCLAAcessConfig()
	{
		List<CLAAcessConfig> listOfAllClaAccessConfig=null;
		listOfAllClaAccessConfig = cLAAccessConfigService.getAllCLAAcessConfig();
		return listOfAllClaAccessConfig;
	}
	/*
	 * CLAAcessConfig service url mapping ends
	 * */
	
	/*
	 * DailyIncAct service url mapping starts below
	 * */
	
	@RequestMapping(path="/dailyIncAct", method = RequestMethod.POST)
	public String saveDailyIncAct(@RequestBody DailyIncAct dailyIncAct)
	{
		String result = "";
		result = dailyIncActService.saveDailyIncAct(dailyIncAct);
		return result;
	}
	
	@RequestMapping(path="/dailyIncAct", method = RequestMethod.PUT)
	public String updateDailyIncAct(@RequestBody DailyIncAct dailyIncAct)
	{
		String result = "";
		result = dailyIncActService.saveDailyIncAct(dailyIncAct);
		return result;
	}
	
	@RequestMapping(path="/dailyIncAct/incNum/{incNum}", method = RequestMethod.GET)
	public DailyIncAct searchDailyIncActByIncNum(@PathVariable("incNum") String incNum)
	{
		DailyIncAct dailyIncAct = dailyIncActService.searchDailyIncActByIncNum(incNum) ;
		return dailyIncAct;
	}
	
	@RequestMapping(path="/dailyIncAct/{soeId}/{date}", method = RequestMethod.GET)
	public List<DailyIncAct> searchDailyIncActByDate(@PathVariable("soeId") String date, @PathVariable("date") String soeId) throws ParseException
	{
		List<DailyIncAct> listOfDdailyIncAct = dailyIncActService.searchAllDailyIncActByDate(commonUtilities.getSQLDate(date),soeId);
		
		return listOfDdailyIncAct;
	}
	
	@RequestMapping(path="/dailyIncAct/{soeId}", method = RequestMethod.GET)
	public List<DailyIncAct> searchAllDailyIncActBySoeId(@PathVariable("soeId") String soeId) throws ParseException
	{
		List<DailyIncAct> listOfDdailyIncAct = dailyIncActService.searchAllDailyIncActBySoeId(soeId);
		
		return listOfDdailyIncAct;
	}
	
	@RequestMapping(path="/dailyIncAct", method = RequestMethod.GET)
	public List<DailyIncAct> searchAllDailyIncAct() throws ParseException
	{
		List<DailyIncAct> listOfDdailyIncAct = dailyIncActService.searchAllDailyIncAct();
		
		return listOfDdailyIncAct;
	}
	
	/*
	 * DailyIncAct service url mapping ends
	 * */
	
	/*
	 * DailyCallIncidentTracker service url mapping starts below
	 * */
	
	@RequestMapping(path="/DailyCallIncidentTracker", method = RequestMethod.POST)
	public String saveDailyCallIncidentTracker(@RequestBody DailyCallIncidentTracker dailyCallIncidentTracker) throws MessagingException, JSONException
	{
		String result = "";
		result = dailyCallIncidentTrackerService.saveDailyCallIncidentTracker(dailyCallIncidentTracker);
		return result;
	}
	
	@RequestMapping(path="/DailyCallIncidentTracker/{incNum}", method = RequestMethod.GET)
	public DailyCallIncidentTracker getDailyCallIncidentTrackerByIncNum(@PathVariable String incNum)
	{
		DailyCallIncidentTracker newDailyCallIncidentTracker = dailyCallIncidentTrackerService.getDailyCallIncidentTrackerByIncNum(incNum);
		return newDailyCallIncidentTracker;
	}
	
	/*
	 * DailyCallIncidentTracker service url mapping ends
	 * */
	
	/*
	 * Mail Service url mapping starts below
	 * */
	@RequestMapping(path="/testMail")
	public String sendMail() throws MessagingException
	{
		String result ="Mail Service Not Working";
		result=sendMail.sendMail("testingpsaapp@gmail.com", 
				"abhishek10sengupta@gmail.com", "abhisheksengupta1003@gmail.com", "Test Mail", "Testing PSA APP-Please Ignore");
		return result;
	}
	
	/*
	 * Mail Service url mapping ends 
	 * */
	
	/*
	 * Country Url Mapping starts
	 * */
	
	@RequestMapping(path="/countries", method = RequestMethod.GET)
	public List<Countries> getAllCountries()
	{
		return countryService.getAllCountry();
	}
	
	@RequestMapping(path="/countries/{region}", method = RequestMethod.GET)
	public List<Countries> getAllCountriesByRegion(@PathVariable String region)
	{
		return countryService.getAllCountryRegion(region);
	}
	
	@RequestMapping(path="/countries", method = RequestMethod.POST)
	public List<Countries> saveCountry(@RequestBody Countries country)
	{
		return countryService.saveCountry(country);
	}
	
	@RequestMapping(path="/countries/save", method = RequestMethod.POST)
	public String saveCountryConfig(@RequestBody Countries country)
	{
		String result = "";
		result=countryService.saveCountryConfig(country);
		return result;
	}
	/*
	 * Triage Lead COnfig URL mapping starts 
	 * */
	
	@RequestMapping(path="/triageLeadConfig", method = RequestMethod.POST)
	public String saveTriageLeadConfig(@RequestBody TriageLeadConfig triageLeadConfig)
	{
		String message="";
		message=triageLeadConfigService.saveTriageLeadConfiguration(triageLeadConfig);
		return message;
	}
	
	@RequestMapping(path="/triageLeadConfig/all", method = RequestMethod.GET)
	public List<TriageLeadConfig> getAllTriageLeadConfig()
	{
		List<TriageLeadConfig> listOfTriageLeadConfig=null;
		listOfTriageLeadConfig=triageLeadConfigService.getAllTriageLeadConfiguration();
		return listOfTriageLeadConfig;
	}
	
	/*
	 * Incident Communication Service Starts
	 * */
	@RequestMapping(path="/incidentCommunication/review", method = RequestMethod.POST)
	public String submitIncidentCommunicationForReview(@RequestBody IncidentComm incidentComm) throws JSONException, MessagingException
	{
		String result="{\"message\":\"Incident Communication Submission Failed\"}";
		System.out.println("Inside Controller");
		result=incidentCommService.submitIncidentCommunicationForReview(incidentComm); 
		return result;
	}
	
	@RequestMapping(path="/incidentCommunication/{incNum}", method = RequestMethod.GET)
	public IncidentComm getIncidentCommunicationForApprove(@PathVariable String incNum)
	{
		IncidentComm incidentComm =null;
		System.out.println("Inside Controller");
		incidentComm=incidentCommService.getIncCommForReview(incNum);
		return incidentComm;
	}
	
	@RequestMapping(path="/incidentCommunication", method = RequestMethod.PUT)
	public IncidentComm updateIncidentCommunicationPostApprove(@RequestBody IncidentComm incComm)
	{
		IncidentComm newIncidentComm =null;
		System.out.println("Inside Controller");
		newIncidentComm=incidentCommService.updateIncidentCommPostApprove(incComm);
		return newIncidentComm;
	}
	
	@RequestMapping(path="/incidentCommunication/approve/{incNum}", method = RequestMethod.POST)
	public String finalApproveIncidentCommunication(@RequestBody EmailList emailList,@PathVariable("incNum") String incNum) throws JSONException, MessagingException
	{
		String message ="";
		System.out.println("Inside Controller");
		message=incidentCommService.finalApproveIncidentCommunication(emailList,incNum);
		return message;
	}
	
	@RequestMapping(path="/incidentCommunication/{incNum}/emailList", method = RequestMethod.GET)
	public EmailList getIncidentCommunicationMailList(@PathVariable("incNum") String incNum)
	{
		EmailList emailList = null;
		emailList=emailListService.getEmailListForIncidentCommunication(incNum);
		return emailList;
	}
	
	/*
	 * Access Module Starts
	 * 
	 * */
	@RequestMapping(path="/accessModule/all", method = RequestMethod.GET)
	public List<AccessModule> getAllAccessModule()
	{
		List<AccessModule> listOfAccessModule =null;
		System.out.println("Inside Controller");
		listOfAccessModule=accessModuleService.getAllAccessModule();
		return listOfAccessModule;
	}
	
	
	@RequestMapping(path="/accessModule/{mainModule}", method = RequestMethod.GET)
	public List<AccessModule> getAllAccessModuleByMainModule(@PathVariable String mainModule)
	{
		List<AccessModule> listOfAccessModule =null;
		System.out.println("Inside Controller");
		listOfAccessModule=accessModuleService.getAllAccessModuleByMainModule(mainModule);
		return listOfAccessModule;
	}
	
	@RequestMapping(path="/accessModule/{mainModule}/{subModule}", method = RequestMethod.GET)
	public List<AccessModule> getAllAccessModuleByMainModuleSubModule(@PathVariable("mainModule") String mainModule,@PathVariable("subModule") String subModule)
	{
		List<AccessModule> listOfAccessModule =null;
		System.out.println("Inside Controller");
		listOfAccessModule=accessModuleService.getAllAccessModuleByMainModuleAndSubModule(mainModule, subModule);
		return listOfAccessModule;
	}
	
	/*
	 * Change Communication starts
	 * */
	@RequestMapping(path="/changeComm/submitForSanityScope", method = RequestMethod.POST)
	public String submitChangeForSanityScope(@RequestBody ChangeComm changeComm) throws JSONException, MessagingException
	{
		String result="";
		System.out.println("Inside Controller");
		result=changeCommService.submitForCompletion(changeComm);
		return result;
	}
	@RequestMapping(path="/changeComm/{changeNum}/country", method = RequestMethod.GET)
	public String[] getChangeCountryForSanityScope(@PathVariable("changeNum") String changeNum)
	{
		String[] countries;
		countries=changeCommService.getChangeCountryForSanityScope(changeNum);
		return countries;
	}
	
	@RequestMapping(path="/changeComm/submitSanityScope/{changeNum}/{appName}/", method = RequestMethod.POST)
	public String submitSanityScope(@PathVariable("changeNum") String changeNum, @PathVariable("appName") String appName, @RequestBody String[] sanityScope) throws JSONException, MessagingException
	{
		String result="";
		System.out.println("Inside Controller");
		result=changeCommService.submitScope(sanityScope,changeNum,appName);
		return result;
	}
	
	@RequestMapping(path="/changeComm/ignoreSanity/{changeNum}/{appName}/", method = RequestMethod.POST)
	public String ignoreSanityScope(@PathVariable("changeNum") String changeNum, @PathVariable("appName") String appName) throws JSONException, MessagingException
	{
		String result="";
		System.out.println("Inside Controller");
		result=changeCommService.ignoreScope(changeNum,appName);
		return result;
	}
	
	/*
	 * Briefing paper starts here
	 * */
	@RequestMapping(path="/briefingPaper/review/lobleads", method = RequestMethod.POST)
	public String reviewBriefingPaper(@RequestBody BriefPapPub briefPapPub) throws JSONException, MessagingException
	{
		String result="";
		System.out.println("Inside Controller");
		result=briefingPaperPublisherService.review(briefPapPub);
		return result;
	}
	
	@RequestMapping(path="/briefingPaper/{mimNum}", method = RequestMethod.GET)
	public BriefPapPub getBriefingPaper(@PathVariable("mimNum")String briefPapPub) throws JSONException, MessagingException
	{
		//String result="";
		System.out.println("Inside Controller");
		BriefPapPub briefPapPubObj=null;
		briefPapPubObj=briefingPaperPublisherService.getBriefingPaper(briefPapPub);
		return briefPapPubObj;
	}
	
	@RequestMapping(path="/briefingPaper/review/{mimNum}/lobleads/{reviewer}/{appName}", method = RequestMethod.PUT)
	public String reviewBriefingPaperSubmitLobLead(@PathVariable(value="reviewer") String reviewer,@PathVariable(value="mimNum")String mimNum,@PathVariable(value="appName") String appName,@RequestBody BriefPapPub briefPapPub) throws JSONException, MessagingException
	{
		String result="";
		System.out.println("Inside Controller");
		result=briefingPaperPublisherService.reviewLobLeadSubmit(briefPapPub,mimNum, reviewer, appName);
		return result;
	}
	
	@RequestMapping(path="/briefingPaper/review/{mimNum}/psm/{reviewer}/{appName}", method = RequestMethod.PUT)
	public String reviewBriefingPaperSubmitPSM(@PathVariable(value="reviewer") String reviewer,@PathVariable(value="mimNum")String mimNum,@PathVariable(value="appName") String appName,@RequestBody BriefPapPub briefPapPub) throws JSONException, MessagingException
	{
		String result="";
		System.out.println("Inside Controller");
		result=briefingPaperPublisherService.reviewPSMSubmit(briefPapPub,mimNum, reviewer, appName);
		return result;
	}
	
	@RequestMapping(path="/briefingPaper/review/{mimNum}/pssm/{reviewer}/{appName}", method = RequestMethod.POST)
	public String reviewBriefingPaperSubmitPSSM(@PathVariable("reviewer") String reviewer,@PathVariable("mimNum")String mimNum,@PathVariable("appName") String appName,@RequestBody BriefPapPub briefPapPub) throws JSONException, MessagingException
	{
		String result="";
		System.out.println("Inside Controller");
		result=briefingPaperPublisherService.reviewPSSMSubmit(briefPapPub, mimNum, reviewer, appName);
		return result;
	}
	
	
	/*
	 * User registration starts here
	 * */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registration(@RequestBody User user) {
		String message = "{\"message\":\"Registration Failed\"}";
		//System.out.println("Inside MVC Controller: /register");
		System.out.println("User Received:" +user.getSoeId());
		message=userService.registerUser(user);
        return message;
    }
	
	/*
	 * Worklist starts here
	 * */
	
	@RequestMapping(value = "/worklist/{user}/all", method = RequestMethod.GET)
    public List<Worklist> getWorklist(@PathVariable("user") String user) {
		List<Worklist> listOfWorklist=null;
		//System.out.println("Inside MVC Controller: /register");
		System.out.println("User Received:" +user);
		listOfWorklist=worklistService.getUserWorklist(user);
        return listOfWorklist;
    }
}
