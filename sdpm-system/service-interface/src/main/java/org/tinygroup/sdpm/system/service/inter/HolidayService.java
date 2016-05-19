package org.tinygroup.sdpm.system.service.inter;

import org.tinygroup.sdpm.system.dao.pojo.Holiday;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

public interface HolidayService {
    /**
     * 更新假日
     *
     * @param holiday
     * @return
     */

    Holiday updateHoliday(Holiday holiday);

    /**
     * 删除假日
     *
     * @param holiday
     * @return
     */
    Holiday deleteHoliday(Holiday holiday);

    /**
     * 查询假日
     *
     * @param holiday
     * @return
     */
    List<Holiday> findHolidayList(Holiday holiday);

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
    Pager<Holiday> findByPage(Integer start, Integer limit, Holiday holiday, String sortName, boolean asc);

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
    List<Holiday> batchAddHoliday(List<Holiday> holidayList);

    /**
     * 通过Id 查询
     *
     * @param id
     * @return
     */
    Holiday findHolidayById(Integer id);

    /**
     * 通过Ids查询
     *
     * @param ids
     * @return
     */
    List<Holiday> findHolidayByIds(Integer... ids);

    /**
     * 批量软删除
     *
     * @param list
     * @return
     */
    void batchSoftDeleteHoliday(List<Holiday> list);
}
