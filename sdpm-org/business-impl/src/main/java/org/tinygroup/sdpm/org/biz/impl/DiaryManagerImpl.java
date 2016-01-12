package org.tinygroup.sdpm.org.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.org.biz.inter.DiaryManager;
import org.tinygroup.sdpm.org.dao.OrgDiaryDao;
import org.tinygroup.sdpm.org.dao.OrgDiaryDetailDao;
import org.tinygroup.sdpm.org.dao.pojo.OrgDiary;
import org.tinygroup.sdpm.org.dao.pojo.OrgDiaryDetail;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by wangdl16860 on 2016/1/7.
 */
@Service
@Transactional
public class DiaryManagerImpl implements DiaryManager{
    @Autowired
    private OrgDiaryDao orgDiaryDao;

    @Autowired
    private OrgDiaryDetailDao orgDiaryDetailDao;

    @Override
    public OrgDiary add(OrgDiary orgDiary, List<OrgDiaryDetail> list) {
        Calendar ca=Calendar.getInstance();
        ca.setTime(orgDiary.getOrgDiaryCreateDate());
        orgDiary.setOrgDiaryYear(ca.get(Calendar.YEAR));
        orgDiary.setOrgDiaryWeek(ca.get(Calendar.WEEK_OF_YEAR));
        orgDiary.setOrgDiaryStatus("0");
        OrgDiary diary=orgDiaryDao.add(orgDiary);

        for (int i=0;i<list.size();i++){
            OrgDiaryDetail orgDiaryDetail=list.get(i);
            orgDiaryDetail.setOrgDiaryId(diary.getOrgDiaryId());
        }

        orgDiaryDetailDao.batchInsert(list);
        return diary;
    }

    @Override
    public Integer delete(Integer id) {
        orgDiaryDetailDao.batchDeleteByDiaryId(id);
        return orgDiaryDao.deleteByKey(id);
    }

    @Override
    public Integer update(OrgDiary orgDiary, List<OrgDiaryDetail> list) {
        orgDiaryDetailDao.batchDeleteByDiaryId(orgDiary.getOrgDiaryId());
        orgDiaryDetailDao.batchInsert(list);

        orgDiary.setOrgDiaryModifyDate(new Date());
        return orgDiaryDao.edit(orgDiary);
    }

    @Override
    public OrgDiary findByKey(Integer id) {
        return orgDiaryDao.getByKey(id);
    }

    @Override
    public List<OrgDiary> findByUserId(String id) {
        return orgDiaryDao.findListByUserId(id);
    }

    @Override
    public List<OrgDiaryDetail> findByDiaryId(Integer diaryId) {
        return orgDiaryDetailDao.findByDiaryId(diaryId);
    }

    @Override
    public List<OrgDiary> findSubordinate(List<OrgUser> list) {
        List<String> list1=new ArrayList<String>();
        for (OrgUser orgUser:list) {
            list1.add(orgUser.getOrgUserId());
        }
        return orgDiaryDao.findListByUser(list1);
    }

    @Override
    public OrgDiary find(Integer year, Integer week,String userId) {
        return orgDiaryDao.getByTimeAndUser(userId,year,week);
    }

    @Override
    public List<OrgDiary> findListOneWeek(Integer year, Integer week) {
        return orgDiaryDao.findListOneWeek(year, week);
    }

    @Override
    public List<OrgDiary> findSubordinateOneWeek(List<OrgUser> list, Integer year, Integer week) {
        List<String> list1=new ArrayList<String>();
        for (OrgUser orgUser:list) {
            list1.add(orgUser.getOrgUserId());
        }
        return orgDiaryDao.findSubordinateOneWeek(list1,year,week);
    }

    @Override
    public List<String> findUser(List<String> list,Integer year,Integer week){
        if(list==null){
            return null;
        }
        return orgDiaryDao.findUser(list,year,week);
    }
}
