package org.tinygroup.sdpm.org.service.inter;

import org.tinygroup.sdpm.org.dao.pojo.OrgDiary;
import org.tinygroup.sdpm.org.dao.pojo.OrgDiaryAndUserDO;
import org.tinygroup.sdpm.org.dao.pojo.OrgDiaryDetail;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.template.rumtime.convert.IntegerFloat;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

/**
 * Created by wangdl16860 on 2016/1/7.
 */
public interface DiaryService {
    /**
     * 添加周报
     */
    OrgDiary addDiary(OrgDiary orgDiary, List<OrgDiaryDetail> list);

    /**
     * 删除周报
     */
    Integer deleteDiary(Integer id);

    /**
     * 更新周报
     */
    Integer updateDiary(OrgDiary orgDiary, List<OrgDiaryDetail> list);

    /**
     * 查看指定ID的周报
     */
    OrgDiary findDiaryByKey(Integer id);

    /**
     * 查找某人的周报
     */
    Pager<OrgDiaryAndUserDO> findPagerDiaryByUserId(String id,Integer start,Integer limit);

    /**
     * 查找某人的周报
     */
    List<OrgDiaryAndUserDO> findListDiaryByUserId(String id);

    /**
     * 查看周报详情
     */
    List<OrgDiaryDetail> findDetailListByDiaryId(Integer id);

    /**
     *查看下属的周报
     */
    List<OrgDiary> findDiaryListSubordinate(String id);

    /**
     * 根据年、周查询某一周的周报(某人)
     */
    OrgDiary findDiaryByUserAndDate(Integer year, Integer week, String userId);

    /**
     * 根据某年、某周查询那一周的周报
     */
    List<OrgDiary> findDiaryListOneWeek(Integer year, Integer week);

    List<OrgDiary> findDiaryListSubordinateOneWeek(String userId, Integer year, Integer week);

    Pager<OrgDiaryAndUserDO> findDiaryPagerSubordinateOneWeek(String userId, Integer year, Integer week, Integer start, Integer limit);

    /**
     * 查询本周未提交周报的下属
     * @param userId
     * @param year
     * @param week
     * @return
     */
    List<OrgUser> findUserNoSubmit(String userId, Integer year, Integer week);

    /**
     * 查询某人最新的周报
     * @param userId
     * @return
     */
    OrgDiary findDiaryByUserLatest(String userId,Integer year,Integer week);

    /**
     * 查询直接下属及自己的本周的周报+分页
     */
    Pager<OrgDiaryAndUserDO> findPagerDiarySubAndSelf(String userId, Integer year, Integer week, Integer start, Integer limit);

    List<OrgDiaryAndUserDO> findListDiarySubAndSelf(String userId, Integer year, Integer week);
}
