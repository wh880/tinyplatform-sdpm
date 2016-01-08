package org.tinygroup.sdpm.org.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.org.biz.inter.DiaryManager;
import org.tinygroup.sdpm.org.biz.inter.UserManager;
import org.tinygroup.sdpm.org.dao.pojo.OrgDiary;
import org.tinygroup.sdpm.org.dao.pojo.OrgDiaryDetail;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.org.service.inter.DiaryService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangdl16860 on 2016/1/7.
 */
@Component
public class DiaryServiceImpl implements DiaryService{
    @Autowired
    private DiaryManager diaryManager;

    @Autowired
    private UserManager userManager;

    @Override
    public OrgDiary add(OrgDiary orgDiary, List<OrgDiaryDetail> list) {
        return diaryManager.add(orgDiary,list);
    }

    @Override
    public Integer delete(Integer id) {
        return diaryManager.delete(id);
    }

    @Override
    public Integer update(OrgDiary orgDiary, List<OrgDiaryDetail> list) {
        return diaryManager.update(orgDiary,list);
    }

    @Override
    public OrgDiary findByKey(Integer id) {
        return diaryManager.findByKey(id);
    }

    @Override
    public List<OrgDiary> findByUserId(String id) {
        return diaryManager.findByUserId(id);
    }

    @Override
    public List<OrgDiaryDetail> findByDiaryId(Integer id) {
        return diaryManager.findByDiaryId(id);
    }

    @Override
    public List<OrgDiary> findSubordinate(String id) {
        List<OrgUser> list=userManager.getDirectStaffByLeader(id);
        return diaryManager.findSubordinate(list);
    }

    @Override
    public OrgDiary find(Integer year, Integer week, String userId) {
        return diaryManager.find(year,week,userId);
    }

    @Override
    public List<OrgDiary> findListOneWeek(Integer year, Integer week) {
        return diaryManager.findListOneWeek(year,week);
    }

    @Override
    public List<OrgDiary> findSubordinateOneWeek(String userId, Integer year, Integer week) {
        if(userId==null||year==null||week==null){
            return null;
        }
        List<OrgUser> list=userManager.getDirectStaffByLeader(userId);
        return diaryManager.findSubordinateOneWeek(list,year,week);
    }

    @Override
    public List<OrgUser> findUser(String userId,Integer year,Integer week){
        List<OrgUser> list=userManager.getDirectStaffByLeader(userId);
        List<String> list1=new ArrayList<String>();
        for (OrgUser orgUser:list) {
            list1.add(orgUser.getOrgUserId());
        }
        List<String> list2=diaryManager.findUser(list1,year,week);
        list1.removeAll(list2);
        return userManager.getUserListById(list1);
    }
}
