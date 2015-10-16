package org.tinygroup.sdpm.action.system;

import static org.tinygroup.sdpm.system.dao.constant.SystemActionTable.SYSTEM_ACTIONTABLE;

import java.util.Calendar;
import java.util.Date;
import org.tinygroup.tinysqldsl.base.Condition;


public class ActionUtil {
	
	public static Condition getActionDateCondition(String choice){
		Calendar calendarBegin = Calendar.getInstance();
		calendarBegin.setTime(new Date());
		calendarBegin.set(calendarBegin.get(Calendar.YEAR), calendarBegin.get(Calendar.MONDAY), calendarBegin.get(Calendar.DATE));
		Calendar calendarEnd = calendarBegin;
		
		Condition dateCon = null;

		
		switch (DateChoice.valueOf(DateChoice.class, choice)) {
		case today:
			calendarEnd.add(Calendar.DATE, 1);
			dateCon = SYSTEM_ACTIONTABLE.ACTION_DATE.between(calendarBegin.getTime(), calendarEnd.getTime());
			break;
		case yesterday:
			calendarEnd.roll(Calendar.DATE, -1);
			dateCon = SYSTEM_ACTIONTABLE.ACTION_DATE.between(calendarEnd.getTime(), calendarBegin.getTime());
			break;
		case beforeYesterday:
			calendarBegin.roll(Calendar.DATE, -2);
			calendarEnd.roll(Calendar.DATE, -1);
			dateCon = SYSTEM_ACTIONTABLE.ACTION_DATE.between(calendarBegin.getTime(), calendarEnd.getTime());
			break;
		case thisWeek:
			dateCon = SYSTEM_ACTIONTABLE.ACTION_DATE.between(DateUtil.getFirstDayOfWeek(calendarBegin.getTime()), calendarEnd.getTime());
			break;
		case lastWeek:
			calendarBegin.setTime(DateUtil.getFirstDayOfWeek(calendarBegin.getTime()));
			calendarBegin.roll(Calendar.DATE, -7);
			dateCon = SYSTEM_ACTIONTABLE.ACTION_DATE.between(calendarBegin, DateUtil.getFirstDayOfWeek(calendarEnd.getTime()));
			break;
		case thisMonth:
			calendarBegin.setTime(DateUtil.getFirstDayOfMonth(calendarBegin.getTime()));
			dateCon = SYSTEM_ACTIONTABLE.ACTION_DATE.between(calendarBegin,calendarEnd);
			break;
		case lastMonth:
			calendarBegin.setTime(DateUtil.getFirstDayOfMonth(calendarBegin.getTime()));
			calendarBegin.roll(Calendar.MONDAY, -1);
			
			calendarEnd.setTime(DateUtil.getFirstDayOfMonth(calendarEnd.getTime()));
			
			dateCon = SYSTEM_ACTIONTABLE.ACTION_DATE.between(calendarBegin,calendarEnd);
			break;
		default:
			dateCon = null;
			break;
		}
		
		return dateCon;
	}
	
	
}

enum DateChoice{
	today,yesterday,beforeYesterday,thisWeek,lastWeek,thisMonth,lastMonth,all
}


