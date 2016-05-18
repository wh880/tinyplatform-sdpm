package org.tinygroup.sdpm.system.biz.inter;

import org.tinygroup.sdpm.system.dao.pojo.Holiday;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

public interface HolidayManager {
    /**
     * 添加假日
     *
     * @param holiday
     * @return
     */
    Holiday add(Holiday holiday);

    /**
     * 修改假期
     *
     * @param holiday
     * @return
     */
    Holiday updata(Holiday holiday);

    /**
     * 删除假期
     *
     * @param holiday
     * @return
     */
    Holiday delete(Holiday holiday);

    /**
     * 查询假日
     *
     * @param holiday
     * @return
     */
    List<Holiday> find(Holiday holiday);

    /**
     * 分页查询
     *
     * @param start
     * @param limit
     * @param holiday
     * @param sortName
     * @param asc
     * @return
     */

    Pager<Holiday> findByPage(int start, int limit, Holiday holiday, String sortName, boolean asc);

    /**
     * Holiday分页查询修改
     * @param start
     * @param limit
     * @param holiday
     * @param sortName
     * @param asc
     * @return
     */
    Pager<Holiday> findByHolidayDeleted(Integer start, Integer limit, Holiday holiday, String sortName, boolean asc);

    /**
     * 批量增加
     *
     * @param holidayList
     * @return
     */
    List<Holiday> batchadd(List<Holiday> holidayList);

    /**
     * 通过Id查询
     *
     * @param id
     * @return
     */
    Holiday findById(int id);

    /**
     * 通过Id批量查询
     *
     * @param ids
     * @return
     */
    List<Holiday> findByIds(Integer... ids);

    /**
     * 批量软删除
     *
     * @param list
     * @return
     */
    int[] batchSoftDelete(List<Holiday> list);
}
