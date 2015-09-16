package sdpm.common.service.inter;

import java.util.Date;
import java.util.List;

import sdpm.common.dao.inter.pojo.Effort;

public interface EffortService {
	/**
	 * 
	 * @param 添加日志
	 * @return
	 */
	public void add(Effort effort);
	/**
	 * 根据日期查询，返回日志信息List
	 * @param date
	 * @return
	 */
	public List<Effort> queryByDate(Date date);
	/**
	 * 保存日志
	 */
	
	public void save();
	
}
