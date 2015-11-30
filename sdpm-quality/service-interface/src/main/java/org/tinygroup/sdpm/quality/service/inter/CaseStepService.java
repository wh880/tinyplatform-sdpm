package org.tinygroup.sdpm.quality.service.inter;

import org.tinygroup.sdpm.quality.dao.pojo.QualityCaseStep;

import java.util.List;

public interface CaseStepService {

    /**
     * 通过主键id查询
     *
     * @param id
     * @return
     */
    QualityCaseStep findById(int id);

    /**
     * 添加用例步骤
     *
     * @param casestep
     * @return
     */
    QualityCaseStep addCaseStep(QualityCaseStep casestep);

    /**
     * 删除步骤
     *
     * @param id
     * @return
     */
    int deleteById(int id);

    /**
     * 通过条件查询步骤
     *
     * @param casestep
     * @return
     */
    List<QualityCaseStep> findCaseStepList(QualityCaseStep casestep);

    int[] batchAdd(List<QualityCaseStep> qualityCaseSteps);

    Integer getMaxVersion(Integer caseId);
}