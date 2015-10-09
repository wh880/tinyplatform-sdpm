package org.tinygroup.sdpm.system.service.inter;

import java.util.List;

import org.tinygroup.sdpm.system.dao.pojo.Holiday;
import org.tinygroup.tinysqldsl.Pager;

public interface HolidayService {
	/**
	 * 添加假日
	 * @param holiday
	 * @return
	 */
	Holiday add(Holiday holiday);
	/**
	 * 更新假日
	 * @param holiday
	 * @return
	 */
	
	Holiday update(Holiday holiday);
	/**
	 * 删除假日
	 * @param holiday
	 * @return
	 */
	Holiday delete(Holiday holiday);
	/**
	 * 查询假日
	 * @param holiday
	 * @return
	 */
	List<Holiday> find(Holiday holiday);
	/**
	 * 分页查询
	 * @param start
	 * @param limit
	 * @param holiday
	 * @param sortName
	 * @param asc
	 * @return
	 */
	Pager<Holiday> findByPage(int start,int limit,Holiday holiday,String sortName,boolean asc);
	/**
	 * 批量增加
	 * @param holidayList
	 * @return
	 */
	int[] batchAdd(List<Holiday> holidayList);
	/**
	 * 通过Id 查询
	 * @param id
	 * @return
	 */
	Holiday findById(int id);
	/**
	 * 通过Ids查询
	 * @param ids
	 * @return
	 */
	List<Holiday> findByIds(Integer...ids);
	/**
	 * 批量软删除
	 * @param list
	 * @return
	 */
	int[] batchSofeDelete(List<Holiday> list);
}
