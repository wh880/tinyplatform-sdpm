package org.tinygroup.sdpm.org.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.org.biz.inter.DiaryManager;
import org.tinygroup.sdpm.org.dao.OrgDiaryDao;
import org.tinygroup.sdpm.org.dao.OrgDiaryDetailDao;
import org.tinygroup.sdpm.org.dao.pojo.OrgDiary;
import org.tinygroup.sdpm.org.dao.pojo.OrgDiaryAndUserDO;
import org.tinygroup.sdpm.org.dao.pojo.OrgDiaryDetail;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.tinysqldsl.Pager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by wangdl16860 on 2016/1/7.
 */
@Service
@Transactional
public class DiaryManagerImpl implements DiaryManager {
    @Autowired
    private OrgDiaryDao orgDiaryDao;

    @Autowired
    private OrgDiaryDetailDao orgDiaryDetailDao;

    @Override
    public OrgDiary add(OrgDiary orgDiary, List<OrgDiaryDetail> list) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(orgDiary.getOrgDiaryCreateDate());
        orgDiary.setOrgDiaryYear(ca.get(Calendar.YEAR));
        orgDiary.setOrgDiaryWeek(ca.get(Calendar.WEEK_OF_YEAR));
        int dayOfWeek = ca.get(ca.DAY_OF_WEEK);
        ca.add(Calendar.DATE, -dayOfWeek + 1);
        Date beginDate = ca.getTime();
        ca.add(Calendar.DATE, 7);
        Date endDate = ca.getTime();
        orgDiary.setOrgDiaryBeginDate(beginDate);
        orgDiary.setOrgDiaryEndDate(endDate);
        OrgDiary diary = orgDiaryDao.add(orgDiary);


        if (list == null) {
        } else {
            for (int i = 0; i < list.size(); i++) {
                OrgDiaryDetail orgDiaryDetail = list.get(i);
                orgDiaryDetail.setOrgDiaryId(diary.getOrgDiaryId());
            }
            orgDiaryDetailDao.batchInsert(list);
        }
        return diary;
    }

    @Override
    public Integer delete(Integer id) {
        orgDiaryDetailDao.batchDeleteByDiaryId(id);
        return orgDiaryDao.deleteByKey(id);
    }

    @Override
    public Integer update(OrgDiary orgDiary, List<OrgDiaryDetail> list) {
        List<OrgDiaryDetail> details = orgDiaryDetailDao.findByDiaryId(orgDiary.getOrgDiaryId());
        if (details != null && details.size() > 0) {
            orgDiaryDetailDao.batchDeleteByDiaryId(orgDiary.getOrgDiaryId());
        }
        if (list != null) {
            orgDiaryDetailDao.batchInsert(list);
        }

        orgDiary.setOrgDiaryModifyDate(new Date());
        return orgDiaryDao.edit(orgDiary);
    }

    @Override
    public OrgDiary findByKey(Integer id) {
        return orgDiaryDao.getByKey(id);
    }

    @Override
    public Pager<OrgDiaryAndUserDO> findByUserId(String id,Integer start,Integer limit) {
        return orgDiaryDao.findPagerByUserId(id,start,limit);
    }

    @Override
    public List<OrgDiaryDetail> findByDiaryId(Integer diaryId) {
        return orgDiaryDetailDao.findByDiaryId(diaryId);
    }

    @Override
    public List<OrgDiary> findSubordinate(List<OrgUser> list) {
        List<String> list1 = new ArrayList<String>();
        for (OrgUser orgUser : list) {
            list1.add(orgUser.getOrgUserId());
        }
        return orgDiaryDao.findListByUser(list1);
    }

    @Override
    public OrgDiary find(Integer year, Integer week, String userId) {
        List<OrgDiary> list = orgDiaryDao.getByTimeAndUser(userId, year, week);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<OrgDiary> findListOneWeek(Integer year, Integer week) {
        return orgDiaryDao.findListOneWeek(year, week);
    }

    @Override
    public List<OrgDiary> findSubordinateOneWeek(List<OrgUser> list, Integer year, Integer week) {
        List<String> list1 = new ArrayList<String>();
        for (OrgUser orgUser : list) {
            list1.add(orgUser.getOrgUserId());
        }
        return orgDiaryDao.findSubordinateOneWeek(list1, year, week);
    }

    @Override
    public List<OrgUser> findUser(String userId, Integer year, Integer week) {
        return orgDiaryDao.findUser(userId, year, week);
    }

    @Override
    public OrgDiary findDiaryByUserLatest(String userId, Integer year, Integer week) {
        List<OrgDiary> list = orgDiaryDao.findDiaryByUserLatest(userId, year, week);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public Pager<OrgDiaryAndUserDO> findPagerSubordinateOneWeek(List<OrgUser> list, Integer year, Integer week, Integer start, Integer limit) {
        List<String> list1 = new ArrayList<String>();
        for (OrgUser orgUser : list) {
            list1.add(orgUser.getOrgUserId());
        }
        return orgDiaryDao.findPagerSubordinateOneWeek(list1,year,week,start,limit);
    }

    @Override
    public Pager<OrgDiaryAndUserDO> findPagerSubAndSelf(String userId, List<OrgUser> list, Integer year, Integer week, Integer start, Integer limit) {
        List<String> list1 = new ArrayList<String>();
        list1.add(userId);
        for (OrgUser orgUser : list) {
            list1.add(orgUser.getOrgUserId());
        }
        return orgDiaryDao.findPagerSubordinateOneWeek(list1,year,week,start,limit);
    }
}
