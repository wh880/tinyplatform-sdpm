package org.tinygroup.sdpm.quality.biz.inter;

import org.tinygroup.sdpm.quality.dao.pojo.QualityCaseStep;

import java.util.List;
/**
 * Created by chenpeng15668 on 2015-9-24
 */

public interface CaseStepManager {
		
		/**
		 * 通过主键id查询
		 * @param id
		 * @return
		 */
		QualityCaseStep find(Integer id);
		/**
		 * 添加用例步骤
		 * @param casestep
		 * @return
		 */
		QualityCaseStep add(QualityCaseStep casestep);
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
		List<QualityCaseStep> findList(QualityCaseStep casestep);

		int[] batchAdd(List<QualityCaseStep> qualityCaseSteps);

	Integer getMaxVersion(Integer caseId);
}
