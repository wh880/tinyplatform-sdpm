package org.tinygroup.sdpm.system.biz.inter;

import org.tinygroup.sdpm.system.dao.pojo.SystemAction;
import org.tinygroup.tinysqldsl.Pager;

import java.util.Date;
import java.util.List;

public interface ActionManager {
    /**
     * 增加动态
     *
     * @param systemAction
     * @return
     */
    SystemAction add(SystemAction systemAction);

    /**
     * 修改动态
     *
     * @param systemAction
     * @return
     */
    SystemAction updata(SystemAction systemAction);

    /**
     * 删除动态
     *
     * @param systemAction
     * @return
     */
    Integer delete(SystemAction systemAction);

    /**
     * 查询动态
     *
     * @param systemAction
     * @param orderBy
     * @param asc
     * @return
     */
    List<SystemAction> find(SystemAction systemAction, String orderBy, boolean asc);

    /**
     * 分页查询
     *
     * @param start
     * @param limit
     * @param systemAction
     * @param order
     * @param orderType
     * @return
     */
    Pager<SystemAction> findByPage(int start, int limit, SystemAction systemAction, String order, String orderType);

    /**
     * 自定义条件查询
     *
     * @param start
     * @param limit
     * @param chooseDate
     * @param systemAction
     * @param order
     * @param orderType
     * @return
     */
    Pager<SystemAction> queryPager(int start, int limit, String chooseDate, SystemAction systemAction, String order, String orderType);

    /**
     * 通过时间段查询
     *
     * @param start
     * @param limit
     * @param action
     * @param startDate
     * @param endDate
     * @param sortName
     * @param asc
     * @return
     */
    Pager<SystemAction> queryBetweenDate(int start, int limit, SystemAction action, String startDate, String endDate,
                                         String sortName, boolean asc);

    /**
     * 通过一组系统日志Id查找相对应的系统日志
     *
     * @param idList
     * @return
     */
    List<SystemAction> findActionListByIdList(List<Integer> idList);


    List<SystemAction> findActionListByTypeList(List<SystemAction> bugs, List<SystemAction> stories, List<SystemAction> tasks, List<SystemAction> cases, List<SystemAction> releases, List<SystemAction> docs);

    /**
     * 查询某人某一时间段内（任务，bug，需求的日志信息）
     *
     * @param userId
     * @param beginDate
     * @param endDate
     * @return
     */
    List<SystemAction> findDiaryActionListByUserAndDate(String userId, Date beginDate, Date endDate);
}
