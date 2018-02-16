package com.psa.application.utilities;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

@Component
public class CommonUtilities 
{
	public Date getSQLDate(String date) throws ParseException
	{
		//String startDate="01-02-2013";
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date utilDate = sdf1.parse(date);
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime()); 
		return sqlDate;
	}
}
