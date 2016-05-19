package org.tinygroup.sdpm.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.system.biz.inter.HolidayManager;
import org.tinygroup.sdpm.system.dao.pojo.Holiday;
import org.tinygroup.sdpm.system.service.inter.HolidayService;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

@Component
@Transactional
public class HolidayServiceImpl implements HolidayService {
    @Autowired
    private HolidayManager holidayManager;

    public Holiday updateHoliday(Holiday holiday) {
        holidayManager.updata(holiday);
        return holiday;
    }

    public Holiday deleteHoliday(Holiday holiday) {
        return holidayManager.delete(holiday);
    }
    @Transactional(readOnly = true)
    public List<Holiday> findHolidayList(Holiday holiday) {
        return holidayManager.find(holiday);
    }
    @Transactional(readOnly = true)
    public Pager<Holiday> findByPage(Integer start, Integer limit, Holiday holiday,
                                     String sortName, boolean asc) {
        return holidayManager.findByPage(start, limit, holiday, sortName, asc);
    }

    @Override
    public Pager<Holiday> findByHolidayDeleted(Integer start, Integer limit, Holiday holiday, String sortName, boolean asc) {
        return holidayManager.findByHolidayDeleted(start, limit, holiday, sortName, asc);
    }

    public List<Holiday> batchAddHoliday(List<Holiday> holidayList) {
        return holidayManager.batchadd(holidayList);
    }
    @Transactional(readOnly = true)
    public Holiday findHolidayById(Integer id) {
        return holidayManager.findById(id);
    }
    @Transactional(readOnly = true)
    public List<Holiday> findHolidayByIds(Integer... ids) {
        return holidayManager.findByIds(ids);
    }

    public void batchSoftDeleteHoliday(List<Holiday> list) {
        holidayManager.batchSoftDelete(list);
    }

}
