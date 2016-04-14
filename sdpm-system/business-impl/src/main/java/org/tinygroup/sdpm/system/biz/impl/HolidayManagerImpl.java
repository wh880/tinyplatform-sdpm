package org.tinygroup.sdpm.system.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.common.util.common.NameUtil;
import org.tinygroup.sdpm.system.biz.inter.HolidayManager;
import org.tinygroup.sdpm.system.dao.HolidayDao;
import org.tinygroup.sdpm.system.dao.pojo.Holiday;
import org.tinygroup.tinysqldsl.Pager;

import java.util.ArrayList;
import java.util.List;

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
        holiday.setHolidayDeleted(Holiday.DELETE_YES);
        holidayDao.softDelete(holiday);
        return holiday;
    }

    public List<Holiday> find(Holiday holiday) {
        // TODO Auto-generated method stub
        return holidayDao.query(holiday);
    }

    public Pager<Holiday> findByPage(int start, int limit, Holiday holiday,
                                     String sortName, boolean asc) {
        if (StringUtil.isBlank(sortName)) {
            return holidayDao.queryPager(start, limit, holiday);
        }
        return holidayDao.queryPager(start, limit, holiday, new OrderBy(NameUtil.resolveNameDesc(sortName), asc));

    }

    @Override
    public Pager<Holiday> findByHolidayDeleted(Integer start, Integer limit, Holiday holiday, String sortName, boolean asc) {
        if (StringUtil.isBlank(sortName)) {
            return holidayDao.findByHolidayDeleted(start, limit, holiday);
        }
        return holidayDao.findByHolidayDeleted(start, limit, holiday, new OrderBy(NameUtil.resolveNameDesc(sortName), asc));
    }

    public List<Holiday> batchadd(List<Holiday> holidayList) {
        // TODO Auto-generated method stub
        List<Holiday> holidays = new ArrayList<Holiday>();
        for (int i = 0, n = holidayList.size(); i < n; i++) {
            Holiday h = holidayDao.add(holidayList.get(i));
            holidays.add(h);
        }
        return holidays;
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
