package org.tinygroup.sdpm.system.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinygroup.commons.tools.ArrayUtil;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.system.biz.inter.EffortManager;
import org.tinygroup.sdpm.system.dao.SystemEffortDao;
import org.tinygroup.sdpm.system.dao.pojo.SystemEffort;
import org.tinygroup.tinysqldsl.Pager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class EffortManagerImpl implements EffortManager {
    @Autowired
    private SystemEffortDao systemEffortDao;

    public SystemEffort add(SystemEffort systemEffort) {
        return systemEffortDao.add(systemEffort);
    }

    public Integer batchAdd(List<SystemEffort> list) {
        int[] result = systemEffortDao.batchInsert(list);
        if (ArrayUtil.isEmptyArray(result)) {
            return 0;
        }
        return result.length;
    }

    public SystemEffort update(SystemEffort systemEffort) {
        systemEffortDao.edit(systemEffort);
        return systemEffort;
    }

    public Integer delete(SystemEffort SystemEffort) {
        int pk = SystemEffort.getEffortId();
        return systemEffortDao.deleteByKey(pk);
    }

    public List<SystemEffort> findByAccount(String effortAccount) {
        SystemEffort systemEffort = new SystemEffort();
        systemEffort.setEffortAccount(effortAccount);
        return systemEffortDao.query(systemEffort);
    }

    public List<SystemEffort> findByProject(int project) {
        SystemEffort systemEffort = new SystemEffort();
        systemEffort.setEffortProject(project);
        return systemEffortDao.query(systemEffort);
    }

    public Pager<SystemEffort> findByPage(int start, int limit, SystemEffort SystemEffort, String sortName, boolean asc) {
        if (StringUtil.isBlank(sortName)) {
            return systemEffortDao.queryPager(start, limit, SystemEffort);
        }
        OrderBy orderBy = new OrderBy(sortName, asc);
        return systemEffortDao.queryPager(start, limit, SystemEffort, orderBy);
    }

    public List<SystemEffort> findByDate(Date date) {
        SystemEffort systemEffort = new SystemEffort();
        systemEffort.setEffortDate(date);
        return systemEffortDao.query(systemEffort);
    }

    public List<SystemEffort> find(SystemEffort systemEffort) {
        return systemEffortDao.query(systemEffort);
    }

    public List<SystemEffort> findBetweenDate(SystemEffort t, Date beginDate, Date endDate) {
        return systemEffortDao.findBetweenDate(t, beginDate, endDate);
    }

    public List<SystemEffort> findList(SystemEffort systemEffort, String order,
                                       String orderType) {
        return systemEffortDao.query(systemEffort, new OrderBy(order, !("desc".equals(orderType)) ? true : false));
    }

    public int batchDelete(Integer... ids) {
        return systemEffortDao.deleteByKeys(ids);
    }

    public Pager<SystemEffort> findByDate(int start, int limit,
                                          SystemEffort effort, Date startDate, Date endDate, String sortName,
                                          boolean asc) {
        if (StringUtil.isBlank(sortName)) {
            return systemEffortDao.findByDate(start, limit, effort, startDate, endDate);
        }
        OrderBy orderBy = new OrderBy(sortName, asc);
        return systemEffortDao.findByDate(start, limit, effort, startDate, endDate, orderBy);
    }

    public SystemEffort findById(Integer id) {
        return systemEffortDao.getByKey(id);
    }

    @Override
    public List<SystemEffort> findByUserAndDate(String userId, Date beginDate, Date endDate) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String bTime = format.format(beginDate);
        String eTime = format.format(endDate);
        return systemEffortDao.findByUserAndDate(userId, bTime, eTime);
    }

    @Override
    public List<SystemEffort> findEffortListByIdList(List<Integer> list) {
        return systemEffortDao.findListByIdList(list);
    }
}
