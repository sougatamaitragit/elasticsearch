package com.casestudy.searchmetrics.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static Date appendHourMinuteSecondTillDayEnd(Date dateInput) {
		Date convertedDate = null;
		try {
			
			SimpleDateFormat sdf = new SimpleDateFormat(GenericServiceConstant.PLAIN_DATE_FORMAT);
			String fromdatedString = sdf.format(dateInput);
			// Append hours , minute and seconds to it till end of the day
			fromdatedString = fromdatedString+GenericServiceConstant.APPEND_HOUR_MIN_SEC;
			SimpleDateFormat dateFormat = new SimpleDateFormat(GenericServiceConstant.DATE_FORMAT_TILL_SEC);
			convertedDate = dateFormat.parse(fromdatedString); 
		}catch(Exception e) {
			
		}
		return convertedDate;
	}

}
