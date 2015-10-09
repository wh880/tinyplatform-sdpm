package org.tinygroup.sdpm.system.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.system.biz.inter.HolidayManager;
import org.tinygroup.sdpm.system.dao.HolidayDao;
import org.tinygroup.sdpm.system.dao.pojo.Holiday;
import org.tinygroup.tinysqldsl.Pager;
@Component
public class HolidayManagerImpl implements HolidayManager {
	@Autowired
    private HolidayDao holidayDao;
	public Holiday add(Holiday holiday) {
		// TODO Auto-generated method stub
		return holidayDao.add(holiday);
	}

	public Holiday updata(Holiday holiday) {
		// TODO Auto-generated method stub
		holidayDao.edit(holiday);
		return holiday;
	}

	public Holiday delete(Holiday holiday) {
		// TODO Auto-generated method stub
		holiday.setHolidayDeleted(holiday.DELETE_YES);
		holidayDao.softDelete(holiday);
		return holiday;
	}

	public List<Holiday> find(Holiday holiday) {
		// TODO Auto-generated method stub
		return holidayDao.query(holiday);
	}

	public Pager<Holiday> findByPage(int start, int limit, Holiday holiday,
			String sortName, boolean asc) {
		// TODO Auto-generated method stub
		
//		
		OrderBy orderBy= new OrderBy(sortName, asc);
		Pager<Holiday> pagerHoliday= holidayDao.queryPager(start,limit,holiday,orderBy);
		

		return pagerHoliday;
		
	}

	public int[] batchadd(List<Holiday> holidayList) {
		// TODO Auto-generated method stub
		return holidayDao.batchInsert(holidayList);
	}

	public Holiday findById(int id) {
		// TODO Auto-generated method stub
		return holidayDao.getByKey(id);
	}

	public List<Holiday> findByIds(Integer... ids) {
		// TODO Auto-generated method stub
		return holidayDao.findByKeys(ids);
	}

	public int[] batchSoftDelete(List<Holiday> list) {
		// TODO Auto-generated method stub
		return holidayDao.batchsoftdelete(list);
	}

}
