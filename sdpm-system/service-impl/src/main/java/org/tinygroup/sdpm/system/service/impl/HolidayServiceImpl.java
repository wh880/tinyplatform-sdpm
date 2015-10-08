package org.tinygroup.sdpm.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.system.biz.inter.HolidayManager;
import org.tinygroup.sdpm.system.dao.pojo.Holiday;
import org.tinygroup.sdpm.system.service.inter.HolidayService;
import org.tinygroup.tinysqldsl.Pager;
@Component
public class HolidayServiceImpl implements HolidayService {
	@Autowired
	private HolidayManager holidayManager;
	public Holiday add(Holiday holiday) {
		// TODO Auto-generated method stub
		return holidayManager.add(holiday);
	}

	public Holiday update(Holiday holiday) {
		// TODO Auto-generated method stub
		holidayManager.updata(holiday);
		return holiday;
	}

	public Holiday delete(Holiday holiday) {
		// TODO Auto-generated method stub
		
		return holidayManager.delete(holiday);
	}

	public List<Holiday> find(Holiday holiday) {
		// TODO Auto-generated method stub
		return holidayManager.find(holiday);
	}

	public Pager<Holiday> findByPage(int start, int limit, Holiday holiday,
			String sortName, boolean asc) {
		// TODO Auto-generated method stub
		return holidayManager.findByPage(start, limit, holiday, sortName, asc);
	}

	public int[] batchAdd(List<Holiday> holidayList) {
		// TODO Auto-generated method stub
		return holidayManager.batchadd(holidayList);
	}

	public Holiday findById(int id) {
		// TODO Auto-generated method stub
		return holidayManager.findById(id);
	}

	public List<Holiday> findByIds(Integer... ids) {
		// TODO Auto-generated method stub
		return holidayManager.findByIds(ids);
	}

	public int[] batchSofeDelete(List<Holiday> list) {
		// TODO Auto-generated method stub
		return holidayManager.batchSoftDelete(list);
	}

}
