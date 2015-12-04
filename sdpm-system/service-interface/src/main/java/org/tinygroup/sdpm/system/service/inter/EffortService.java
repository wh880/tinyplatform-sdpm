package org.tinygroup.sdpm.system.service.inter;

import org.tinygroup.sdpm.system.dao.pojo.SystemEffort;
import org.tinygroup.tinysqldsl.Pager;

import java.util.Date;
import java.util.List;

public interface EffortService {
    /**
     * 批量保存
     *
     * @param list
     * @return
     */
    Integer batchEffortSave(List<SystemEffort> list);

    /**
     * 保存日志
     *
     * @param systemEffort
     * @return
     */
    SystemEffort saveSystemEffort(SystemEffort systemEffort);

    /**
     * 根据登记人查询
     *
     * @param account
     * @return
     */
    List<SystemEffort> findSystemEffortByAccount(String account);

    /**
     * 查询所有字段
     *
     * @return
     */
    List<SystemEffort> findSystemEffortList(SystemEffort systemEffort);

    /**
     * 根据时间段查询
     *
     * @param systemEffort
     * @param beginDate
     * @param endDate
     * @return
     */
    List<SystemEffort> findSystemEffortBetweenDate(SystemEffort systemEffort, Date beginDate, Date endDate);

    /**
     * 通过projectID查询所有数据
     *
     * @param projectId
     * @return
     */
    List<SystemEffort> findSystemEffortByProjectId(Integer projectId);

    /**
     * 排序查询
     *
     * @param systemEffort
     * @param order
     * @param orderType
     * @return
     */
    List<SystemEffort> findSystemEffortListByOrder(SystemEffort systemEffort, String order, String orderType);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    Integer batchDeleteEffort(Integer... ids);

    /**
     * @param start
     * @param limit
     * @param systemEffort
     * @param sortName
     * @param asc
     * @return
     */
    Pager<SystemEffort> findSystemEffortPage(Integer start, Integer limit, SystemEffort systemEffort, String sortName, boolean asc);


    /**
     * 根据时间段查询
     *
     * @param start
     * @param limit
     * @param effort
     * @param startDate
     * @param endDate
     * @param sortName
     * @param asc
     * @return
     */
    Pager<SystemEffort> findSystemEffortPagerByDate(Integer start, Integer limit, SystemEffort effort, Date startDate,
                                                    Date endDate, String sortName, boolean asc);

    /**
     * 通过ID查询
     *
     * @param id
     * @return
     */
    SystemEffort findSystemEffortById(Integer id);
}
