package org.tinygroup.sdpm.org.biz.inter;

import org.tinygroup.sdpm.org.dao.pojo.OrgDiary;
import org.tinygroup.sdpm.org.dao.pojo.OrgDiaryDetail;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;

import java.util.List;

/**
 * Created by wangdl16860 on 2016/1/7.
 */
public interface DiaryManager {
    /**
     * 新增周报,及其详单
     */
    OrgDiary add(OrgDiary orgDiary, List<OrgDiaryDetail> list);

    /**
     * 根据ID删除周报
     */
    Integer delete(Integer id);

    /**
     * 修改更新周报
     */
    Integer update(OrgDiary orgDiary, List<OrgDiaryDetail> list);

    /**
     * 查看指定ID的周报
     */
    OrgDiary findByKey(Integer id);

    /**
     * 查找某人的周报
     */
    List<OrgDiary> findByUserId(String id);

    /**
     * 查看周报详情
     */
    List<OrgDiaryDetail> findByDiaryId(Integer id);

    /**
     *查看所有下属的所有周报
     */
    List<OrgDiary> findSubordinate(List<OrgUser> list);

    /**
     * 根据年、周查询某一周的周报(某人)
     */
    OrgDiary find(Integer year,Integer week,String userId);

    /**
     * 根据某年、某周查询那一周的周报
     */
    List<OrgDiary> findListOneWeek(Integer year,Integer week);

    /**
     * 查询所有下属某周的周报
     */
    List<OrgDiary> findSubordinateOneWeek(List<OrgUser> list,Integer year,Integer week);

    /**
     * 查询某一周提交周报的下属
     */
    List<String> findUser(List<String> list,Integer year,Integer week);
}