package org.tinygroup.sdpm.action.system;

import java.util.Date;

public class HolidayHistory {
     private String holidayHistoryActor;
     private String holidayHistoryAction;
     private String holidayHistoryName;
     private Date holidayHistoryDate;
	public Date getHolidayHistoryDate() {
		return holidayHistoryDate;
	}
	public void setHolidayHistoryDate(Date holidayHistoryDate) {
		this.holidayHistoryDate = holidayHistoryDate;
	}
	public String getHolidayHistoryActor() {
		return holidayHistoryActor;
	}
	public void setHolidayHistoryActor(String holidayHistoryActor) {
		this.holidayHistoryActor = holidayHistoryActor;
	}
	public String getHolidayHistoryAction() {
		return holidayHistoryAction;
	}
	public void setHolidayHistoryAction(String holidayHistoryAction) {
		this.holidayHistoryAction = holidayHistoryAction;
	}
	public String getHolidayHistoryName() {
		return holidayHistoryName;
	}
	public void setHolidayHistoryName(String holidayHistoryName) {
		this.holidayHistoryName = holidayHistoryName;
	}
}
