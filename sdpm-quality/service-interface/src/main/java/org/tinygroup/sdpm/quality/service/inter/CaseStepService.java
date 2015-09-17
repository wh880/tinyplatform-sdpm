package org.tinygroup.sdpm.quality.service.inter;

import java.util.List;

import org.tinygroup.sdpm.quality.dao.pojo.CaseStep;

public interface CaseStepService {
	
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
	int deleteById(Integer id);
	/**
	 * 通过对象查询步骤
	 * @param casestep
	 * @return
	 */
	List<CaseStep> findByCaseStep(CaseStep casestep);
}
