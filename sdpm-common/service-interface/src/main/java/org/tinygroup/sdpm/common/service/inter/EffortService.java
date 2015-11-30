package org.tinygroup.sdpm.common.service.inter;

import org.tinygroup.sdpm.common.dao.pojo.Effort;
import org.tinygroup.tinysqldsl.Pager;

import java.util.Date;
import java.util.List;

public interface EffortService {

    /**
     * 根据日期查询，返回日志信息List
     *
     * @param date
     * @return
     */
    List<Effort> findByDate(Date date);

    /**
     * 保存日志
     *
     * @param effort
     * @return
     */

    Effort save(Effort effort);

    /**
     * 根据登记人查询
     *
     * @param account
     * @return
     */
    List<Effort> findByAccount(String account);

    /**
     * 查询所有字段
     *
     * @return
     */
    List<Effort> find(Effort effort);

    /**
     * 根据时间段查询
     *
     * @param lastdate
     * @param nowdate
     * @return
     */

    List<Effort> findBetweenDate(Date begindate, Date enddate);

    /**
     * 通过projectID查询所有数据
     *
     * @param projectId
     * @return
     */
    List<Effort> findByProject(int projectId);

    /**
     * 分页查询
     *
     * @param start
     * @param limit
     * @param effort
     * @return
     */
    Pager<Effort> findByPage(int start, int limit, Effort effort);


}
