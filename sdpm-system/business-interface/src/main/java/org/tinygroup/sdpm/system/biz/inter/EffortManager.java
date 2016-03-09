package org.tinygroup.sdpm.system.biz.inter;

import org.tinygroup.sdpm.system.dao.pojo.SystemEffort;
import org.tinygroup.tinysqldsl.Pager;

import java.util.Date;
import java.util.List;

public interface EffortManager {
    /**
     * 添加日志
     *
     * @param systemEffort
     * @return
     */
    SystemEffort add(SystemEffort systemEffort);

    /**
     * 批量添加
     *
     * @param list
     * @return
     */
    Integer batchAdd(List<SystemEffort> list);

    /**
     * 更新日志
     *
     * @param systemEffort
     * @return
     */
    SystemEffort update(SystemEffort systemEffort);

    /**
     * 查询所有的日志
     *
     * @param systemEffort
     * @return
     */
    List<SystemEffort> find(SystemEffort systemEffort);

    /**
     * 通过当前时间查询
     *
     * @param date
     * @return
     */

    List<SystemEffort> findByDate(Date date);

    /**
     * 删除日志
     *
     * @param systemEffort
     * @return
     */
    Integer delete(SystemEffort systemEffort);

    /**
     * 通过时间段查询
     *
     * @param t
     * @param beginDate
     * @param endDate
     * @return
     */
    List<SystemEffort> findBetweenDate(SystemEffort t, Date beginDate, Date endDate);

    /**
     * 根据登记人查询
     *
     * @param account
     * @return
     */
    List<SystemEffort> findByAccount(String account);

    /**
     * 通过projectID查询所有数据
     *
     * @param projectId
     * @return
     */
    List<SystemEffort> findByProject(int projectId);

    /**
     * 分页查询
     *
     * @param start
     * @param limit
     * @param systemEffort
     * @param sortName
     * @param asc
     * @return
     */
    Pager<SystemEffort> findByPage(int start, int limit, SystemEffort systemEffort, String sortName, boolean asc);

    /**
     * 排序查询
     *
     * @param systemEffort
     * @param order
     * @param orderType
     * @return
     */

    List<SystemEffort> findList(SystemEffort systemEffort, String order, String orderType);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    int batchDelete(Integer... ids);

    /**
     * 通过时间段查询
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
    Pager<SystemEffort> findByDate(int start, int limit, SystemEffort effort,
                                   Date startDate, Date endDate, String sortName, boolean asc);

    /**
     * 通过Id查询
     *
     * @param id
     * @return
     */
    SystemEffort findById(Integer id);

    /**
     * 根据时间段以及登记人查询
     */
    List<SystemEffort> findByUserAndDate(String userId, Date beginDate, Date endDate);

    /**
     * 查询一组ID的结果
     */
    List<SystemEffort> findEffortListByIdList(List<Integer> list);
}
