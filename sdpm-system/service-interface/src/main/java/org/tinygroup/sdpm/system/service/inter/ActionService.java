package org.tinygroup.sdpm.system.service.inter;

import org.tinygroup.sdpm.system.dao.pojo.SystemAction;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.tinysqldsl.base.Condition;

import java.util.List;

public interface ActionService {

    /**
     * 查询动态
     *
     * @param SystemAction
     * @return
     */
    List<SystemAction> findAction(SystemAction SystemAction, String orderBy, boolean asc);

    /**
     * 分页查询
     *
     * @param page
     * @param pageSize
     * @param action
     * @param order
     * @param orderType
     * @return
     */
    Pager<SystemAction> findSystemActionPager(int page, int pageSize,
                                              SystemAction action, String order, String orderType);

    /**
     * 自定义条件查询
     *
     * @param start
     * @param limit
     * @param condition
     * @param systemAction
     * @param order
     * @param orderType
     * @return
     */
    Pager<SystemAction> queryActionPager(int start, int limit,
                                         Condition condition, SystemAction systemAction, String order,
                                         String orderType);

    /**
     * 通过日期查询
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
    Pager<SystemAction> queryActionBetweenDate(int start, int limit, SystemAction action, String startDate, String endDate,
                                               String sortName, boolean asc);
}
