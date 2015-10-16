package org.tinygroup.sdpm.action.system;

import org.tinygroup.sdpm.common.util.DateUtils;
import org.tinygroup.tinysqldsl.base.Condition;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.tinygroup.sdpm.system.dao.constant.SystemActionTable.SYSTEM_ACTIONTABLE;


enum DateChoice {
	today, yesterday, beforeYesterday, thisWeek, lastWeek, thisMonth, lastMonth, all
}

public class ActionUtil {

	public static Condition getActionDateCondition(String choice){
		if(choice==null||"".equals(choice)){
			return  null;
		}
		Calendar calendarBegin = Calendar.getInstance();
		calendarBegin.setTime(new Date());
		calendarBegin.set(calendarBegin.get(Calendar.YEAR), calendarBegin.get(Calendar.MONTH), calendarBegin.get(Calendar.DATE));

		Calendar calendarEnd =  Calendar.getInstance();
		calendarEnd.setTime(new Date());
		calendarEnd.set(calendarEnd.get(Calendar.YEAR), calendarEnd.get(Calendar.MONTH), calendarEnd.get(Calendar.DATE));

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Condition dateCon = null;


		switch (DateChoice.valueOf(DateChoice.class, choice)) {
		case today:
			calendarEnd.add(Calendar.DATE, 1);
			dateCon = SYSTEM_ACTIONTABLE.ACTION_DATE.between(format.format(calendarBegin.getTime()), format.format(calendarEnd.getTime()));
			break;
		case yesterday:
			calendarEnd.roll(Calendar.DATE, -1);
			dateCon = SYSTEM_ACTIONTABLE.ACTION_DATE.between(format.format(calendarEnd.getTime()), format.format(calendarBegin.getTime()));
			break;
		case beforeYesterday:
			calendarBegin.roll(Calendar.DATE, -2);
			calendarEnd.roll(Calendar.DATE, -1);
			dateCon = SYSTEM_ACTIONTABLE.ACTION_DATE.between(format.format(calendarBegin.getTime()), format.format(calendarEnd.getTime()));
			break;
		case thisWeek:
			calendarEnd.add(Calendar.DATE, 1);
			dateCon = SYSTEM_ACTIONTABLE.ACTION_DATE.between(format.format(DateUtils.getFirstDayOfWeek(calendarBegin.getTime())), format.format(calendarEnd.getTime()));
			break;
		case lastWeek:
			calendarBegin.setTime(DateUtils.getFirstDayOfWeek(calendarBegin.getTime()));
			calendarBegin.roll(Calendar.DATE, -7);
			dateCon = SYSTEM_ACTIONTABLE.ACTION_DATE.between(format.format(calendarBegin.getTime()), format.format(DateUtils.getFirstDayOfWeek(calendarEnd.getTime())));
			break;
		case thisMonth:
			calendarBegin.setTime(DateUtils.getFirstDayOfMonth(calendarBegin.getTime()));
			calendarEnd.add(Calendar.DATE, 1);
			dateCon = SYSTEM_ACTIONTABLE.ACTION_DATE.between(format.format(calendarBegin.getTime()),format.format(calendarEnd.getTime()));
			break;
		case lastMonth:
			calendarBegin.setTime(DateUtils.getFirstDayOfMonth(calendarBegin.getTime()));
			calendarBegin.roll(Calendar.MONTH, -1);
			calendarEnd.setTime(DateUtils.getFirstDayOfMonth(calendarEnd.getTime()));

			dateCon = SYSTEM_ACTIONTABLE.ACTION_DATE.between(format.format(calendarBegin.getTime()),format.format(calendarEnd.getTime()));
			break;
		default:
			dateCon = null;
			break;
		}

		return dateCon;
	}


}


