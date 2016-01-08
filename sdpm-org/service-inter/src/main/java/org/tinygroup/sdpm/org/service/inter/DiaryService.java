package org.tinygroup.sdpm.org.service.inter;

import org.tinygroup.sdpm.org.dao.pojo.OrgDiary;
import org.tinygroup.sdpm.org.dao.pojo.OrgDiaryDetail;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;

import java.util.List;

/**
 * Created by wangdl16860 on 2016/1/7.
 */
public interface DiaryService {
    /**
     * 添加周报
     */
    OrgDiary add(OrgDiary orgDiary, List<OrgDiaryDetail> list);

    /**
     * 删除周报
     */
    Integer delete(Integer id);

    /**
     * 更新周报
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
     *查看下属的周报
     */
    List<OrgDiary> findSubordinate(String id);

    /**
     * 根据年、周查询某一周的周报(某人)
     */
    OrgDiary find(Integer year,Integer week,String userId);

    /**
     * 根据某年、某周查询那一周的周报
     */
    List<OrgDiary> findListOneWeek(Integer year,Integer week);

    List<OrgDiary> findSubordinateOneWeek(String userId,Integer year,Integer week);

    /**
     * 查询本周未提交周报的下属
     * @param userId
     * @param year
     * @param week
     * @return
     */
    List<OrgUser> findUser(String userId, Integer year, Integer week);
}
