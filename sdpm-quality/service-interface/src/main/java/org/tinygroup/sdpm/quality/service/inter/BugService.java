package org.tinygroup.sdpm.quality.service.inter;

import org.tinygroup.sdpm.quality.dao.pojo.BugCount;
import org.tinygroup.sdpm.quality.dao.pojo.QualityBug;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;
import java.util.Map;

public interface BugService {
		
	/**
	* 根据主键ID查询
	 * @param id
	 * @return
	 */
	QualityBug findById(Integer id);
	
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
	 * 批量删除
	 * @param bugIds
	 * @return
	 */
	int[]  batchDeleteBug(List<QualityBug> bugIds);
	/**
	 * 分页查询
	 * @param start
	 * @param limit
	 * @param bug
	 * @param sortName
	 * @param asc
	 * @return
	 */
	Pager<QualityBug> findBugListPager(Integer start,Integer limit, String conditions, QualityBug bug,String sortName,boolean asc);
	
	/**
	 * 删除
	 * @param bugId
	 * @return
	 */
	Integer deleteBug(Integer bugId);

	Map<String,List<BugCount>> report(String code, Integer productId);

	Pager<QualityBug> findStoryChangedBugs(Integer start,Integer limit, String conditions, QualityBug bug,String sortName,boolean asc);
}