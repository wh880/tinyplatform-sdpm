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
    QualityCaseStep findCaseStepById(int id);

    /**
     * 添加用例步骤
     *
     * @param caseStep
     * @return
     */
    QualityCaseStep addCaseStep(QualityCaseStep caseStep);

    /**
     * 删除步骤
     *
     * @param id
     * @return
     */
    int deleteCaseStepById(int id);

    /**
     * 通过条件查询步骤
     *
     * @param caseStep
     * @return
     */
    List<QualityCaseStep> findCaseStepList(QualityCaseStep caseStep);

    /**
     * 获取case当前最大版本
     *
     * @param caseId
     * @return
     */
    Integer getCaseMaxVersion(Integer caseId);
}