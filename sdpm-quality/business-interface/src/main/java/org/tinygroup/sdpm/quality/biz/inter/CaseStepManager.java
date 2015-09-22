package org.tinygroup.sdpm.quality.biz.inter;

import java.util.List;

import org.tinygroup.sdpm.quality.dao.pojo.CaseStep;

public interface CaseStepManager {
		
		/**
		 * 通过主键id查询
		 * @param id
		 * @return
		 */
		CaseStep find(Integer id);
		/**
		 * 添加用例步骤
		 * @param casestep
		 * @return
		 */
		CaseStep add(CaseStep casestep);
		/**
		 * 删除步骤
		 * @param id
		 * @return
		 */
		Integer delete(Integer id);
		/**
		 * 通过条件查询步骤
		 * @param casestep
		 * @return
		 */
		List<CaseStep> findList(CaseStep casestep);
}
