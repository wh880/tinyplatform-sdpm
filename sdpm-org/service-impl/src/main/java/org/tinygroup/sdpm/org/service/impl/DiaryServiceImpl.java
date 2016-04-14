package org.tinygroup.sdpm.org.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.org.biz.inter.DiaryManager;
import org.tinygroup.sdpm.org.biz.inter.UserManager;
import org.tinygroup.sdpm.org.dao.pojo.OrgDiary;
import org.tinygroup.sdpm.org.dao.pojo.OrgDiaryAndUserDO;
import org.tinygroup.sdpm.org.dao.pojo.OrgDiaryDetail;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.org.service.inter.DiaryService;
import org.tinygroup.tinysqldsl.Pager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangdl16860 on 2016/1/7.
 */
@Component
@Transactional
public class DiaryServiceImpl implements DiaryService {
    @Autowired
    private DiaryManager diaryManager;

    @Autowired
    private UserManager userManager;

    @Override
    public OrgDiary addDiary(OrgDiary orgDiary, List<OrgDiaryDetail> list) {
        return diaryManager.add(orgDiary, list);
    }

    /*
        @Override
        public Integer deleteDiary(Integer id) {
            return diaryManager.delete(id);
        }
    */
    @Override
    public Integer updateDiary(OrgDiary orgDiary, List<OrgDiaryDetail> list) {
        return diaryManager.update(orgDiary, list);
    }

    /*
        @Override
        public OrgDiary findDiaryByKey(Integer id) {
            return diaryManager.findByKey(id);
        }
        */

    @Override
    @Transactional(readOnly = true)
    public Pager<OrgDiaryAndUserDO> findPagerDiaryByUserId(String id, Integer start, Integer limit) {
        return diaryManager.findByUserId(id, start, limit);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrgDiaryAndUserDO> findListDiaryByUserId(String id) {
        return diaryManager.findListByUserId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrgDiaryDetail> findDetailListByDiaryId(Integer id) {
        return diaryManager.findByDiaryId(id);
    }

    /*
        @Override
        public List<OrgDiary> findDiaryListSubordinate(String id) {
            List<OrgUser> list=userManager.getDirectStaffByLeader(id);
            return diaryManager.findSubordinate(list);
        }
        */
/*
    @Override
    public OrgDiary findDiaryByUserAndDate(Integer year, Integer week, String userId) {
        return diaryManager.find(year,week,userId);
    }
    */
/*
    @Override
    public List<OrgDiary> findDiaryListOneWeek(Integer year, Integer week) {
        return diaryManager.findListOneWeek(year,week);
    }
*/
    @Override
    @Transactional(readOnly = true)
    public List<OrgDiary> findDiaryListSubordinateOneWeek(String userId, Integer year, Integer week) {
        if (userId == null || year == null || week == null) {
            return null;
        }
        List<OrgUser> list = userManager.getDirectStaffByLeader(userId);
        return diaryManager.findSubordinateOneWeek(list, year, week);
    }

    /*
        @Override
        public List<OrgUser> findUserNoSubmit(String userId, Integer year, Integer week){
            return diaryManager.findUser(userId,year,week);
        }
    */
    @Override
    @Transactional(readOnly = true)
    public OrgDiary findDiaryByUserLatest(String userId, Integer year, Integer week) {
        return diaryManager.findDiaryByUserLatest(userId, year, week);
    }

    /*
        @Override
        public Pager<OrgDiaryAndUserDO> findDiaryPagerSubordinateOneWeek(String userId, Integer year, Integer week, Integer start, Integer limit) {
            List<OrgUser> list=userManager.getDirectStaffByLeader(userId);
            return diaryManager.findPagerSubordinateOneWeek(list,year,week,start,limit);
        }
    */
    /*
    @Override
    public Pager<OrgDiaryAndUserDO> findPagerDiarySubAndSelf(String userId, Integer year, Integer week, Integer start, Integer limit) {
        List<OrgUser> list=userManager.getDirectStaffByLeader(userId);
        return diaryManager.findPagerSubAndSelf(userId,list,year,week,start,limit);
    }
*/
    @Override
    @Transactional(readOnly = true)
    public List<OrgDiaryAndUserDO> findListDiarySubAndSelf(String userId, Integer year, Integer week) {
        List<OrgUser> list = userManager.getDirectStaffByLeader(userId);
        return diaryManager.findListSubAndSelf(userId, list, year, week);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrgDiaryDetail> findDetailListByDiaryList(List<OrgDiaryAndUserDO> list) {
        List<Integer> list1 = new ArrayList<Integer>();
        for (int i = 0; i < list.size(); i++) {
            list1.add(list.get(i).getOrgDiaryId());
        }
        return diaryManager.findDetailListByDiaryList(list1);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrgDiaryAndUserDO> findDiaryListByWhiteList(String userId, Integer year, Integer week) {
        return diaryManager.findDiaryListByWhiteList(userId, year, week);
    }

    @Override
    @Transactional(readOnly = true)
    public Pager<OrgDiaryAndUserDO> findPagerDiaryByWhiteList(String userId, Integer year, Integer week, Integer start, Integer limit) {
        return diaryManager.findPagerDiaryByWhiteList(userId, year, week, start, limit);
    }
}
