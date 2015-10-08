package org.tinygroup.sdpm.quality.service.inter;

import java.util.List;
import org.tinygroup.sdpm.quality.dao.pojo.QualityBug;
import org.tinygroup.tinysqldsl.Pager;

public interface BugService {
		
	/**
	* 根据主键ID查询
	 * @param id
	 * @return
	 */
	QualityBug findById(int id);
	
	/**
	 * 根据条件查询d
	 * @param bug
	 * @return
	 */
	List<QualityBug> findBugList(QualityBug type);	
	
	/**
	 * 提Bug
	 * @param bug
	 * @return
	 */
	QualityBug addBug(QualityBug bug);

	/**
	 * 修改
	 * @param bug
	 * @return
	 */
	int updateBug(QualityBug bug);
	
	/**
	 * 批量修改
	 * @param bugs
	 * @return
	 */
	int[] batchUpdateBug(List<QualityBug> bugs);
	
	/**
	 * 分页查询
	 * @param start
	 * @param limit
	 * @param bug
	 * @param sortName
	 * @param asc
	 * @return
	 */
	Pager<QualityBug> findBugListPager(Integer start,Integer limit,QualityBug bug,String sortName,boolean asc);
	
	/**
	 * 删除
	 * @param bugId
	 * @return
	 */
	Integer deleteBug(Integer bugId);
	
}