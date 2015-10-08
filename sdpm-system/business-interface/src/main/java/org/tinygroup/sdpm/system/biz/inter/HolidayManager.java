package org.tinygroup.sdpm.system.biz.inter;

import java.util.List;

import org.tinygroup.sdpm.system.dao.pojo.Holiday;
import org.tinygroup.tinysqldsl.Pager;

public interface HolidayManager {
	/**
	 * 添加假日
	 * @param holiday
	 * @return
	 */
	Holiday add(Holiday holiday);
	/**
	 * 修改假期
	 * @param holiday
	 * @return
	 */
	Holiday updata(Holiday holiday);
	/**
	 * 删除假期
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
	
	Pager<Holiday> findByPage(int start,int limit,Holiday holiday,String sortName, boolean asc);
    
}
