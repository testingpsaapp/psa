package com.psa.application.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psa.application.model.DailyIncAct;
import com.psa.application.repositories.DailyIncActRepository;

@Service
public class DailyIncActService {
	
	@Autowired
	DailyIncActRepository dailyIncActRepository;
	
	public String saveDailyIncAct(DailyIncAct dailyIncAct) 
	{
		String result="DailyIncAct save failed";
		DailyIncAct newDailyIncAct = null;
		newDailyIncAct=dailyIncActRepository.saveAndFlush(dailyIncAct);
		if(newDailyIncAct!=null)
		{
			result = "DailyIncAct save successful";
		}
		return result;
	}
	public List<DailyIncAct> searchAllDailyIncActBySoeId(String tlSoeid)
	{
		List<DailyIncAct> listOfDailyIncAct = dailyIncActRepository.findByTLSoeid(tlSoeid);
		return listOfDailyIncAct;
	}
	
	public List<DailyIncAct> searchAllDailyIncActByDate(Date date , String soeId)
	{
		List<DailyIncAct> listOfDailyIncAct = dailyIncActRepository.findByDate(date,soeId);
		return listOfDailyIncAct;
	}
	
	public DailyIncAct searchDailyIncActByIncNum(String incNum)
	{
		DailyIncAct dailyIncAct = dailyIncActRepository.findByIncNum(incNum);
		return dailyIncAct;
	}
	
}
