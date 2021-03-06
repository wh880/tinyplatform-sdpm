package org.tinygroup.sdpm.org.biz.inter;

import org.tinygroup.sdpm.org.dao.pojo.OrgDiary;
import org.tinygroup.sdpm.org.dao.pojo.OrgDiaryAndUserDO;
import org.tinygroup.sdpm.org.dao.pojo.OrgDiaryDetail;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.tinysqldsl.Pager;

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
    Pager<OrgDiaryAndUserDO> findByUserId(String id, Integer start, Integer limit);

    /**
     * 通过用户Id查找周报
     *
     * @param id
     * @return
     */
    List<OrgDiaryAndUserDO> findListByUserId(String id);

    /**
     * 查看周报详情
     */
    List<OrgDiaryDetail> findByDiaryId(Integer id);

    /**
     * 查看所有下属的所有周报
     */
    List<OrgDiary> findSubordinate(List<OrgUser> list);

    /**
     * 根据年、周查询某一周的周报(某人)
     */
    OrgDiary find(Integer year, Integer week, String userId);

    /**
     * 根据某年、某周查询那一周的周报
     */
    List<OrgDiary> findListOneWeek(Integer year, Integer week);

    /**
     * 查询所有下属某周的周报
     */
    List<OrgDiary> findSubordinateOneWeek(List<OrgUser> list, Integer year, Integer week);

    /**
     * 获取下属的某一周的周报+分页
     *
     * @param list
     * @param year
     * @param week
     * @param start
     * @param limit
     * @return
     */
    Pager<OrgDiaryAndUserDO> findPagerSubordinateOneWeek(List<OrgUser> list, Integer year, Integer week, Integer start, Integer limit);

    /**
     * 查询某一周提交周报的下属
     */
    List<OrgUser> findUser(String userId, Integer year, Integer week);

    /**
     * 查询某人最新的周报
     */
    OrgDiary findDiaryByUserLatest(String userId, Integer year, Integer week);

    /**
     * 查询自己及下属的某一周的周报+分页
     */
    Pager<OrgDiaryAndUserDO> findPagerSubAndSelf(String userId, List<OrgUser> list, Integer year, Integer week, Integer start, Integer limit);

    /**
     * 通过年月以及自己ID,与下属ID LIST查找相应的周报List
     *
     * @param userId
     * @param list
     * @param year
     * @param week
     * @return
     */
    List<OrgDiaryAndUserDO> findListSubAndSelf(String userId, List<OrgUser> list, Integer year, Integer week);

    /**
     * 通过周报ID的List获得对应的详情List
     *
     * @param list
     * @return
     */
    List<OrgDiaryDetail> findDetailListByDiaryList(List<Integer> list);

    /**
     * 查找用户白名单内的人某一周的周报
     *
     * @param userId
     * @param year
     * @param week
     * @return
     */
    List<OrgDiaryAndUserDO> findDiaryListByWhiteList(String userId, Integer year, Integer week);

    Pager<OrgDiaryAndUserDO> findPagerDiaryByWhiteList(String userId, Integer year, Integer week, Integer start, Integer limit);

}
