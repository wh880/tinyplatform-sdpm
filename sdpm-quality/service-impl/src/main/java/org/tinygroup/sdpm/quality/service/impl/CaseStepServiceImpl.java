package org.tinygroup.sdpm.quality.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.quality.biz.inter.CaseStepManager;
import org.tinygroup.sdpm.quality.dao.pojo.QualityCaseStep;
import org.tinygroup.sdpm.quality.service.inter.CaseStepService;

import java.util.List;

@Component
@Transactional
public class CaseStepServiceImpl implements CaseStepService {

    @Autowired
    private CaseStepManager caseStepManager;
    @Transactional(readOnly = true)
    public QualityCaseStep findCaseStepById(int id) {
        return caseStepManager.find(id);
    }

    public QualityCaseStep addCaseStep(QualityCaseStep caseStep) {

        return caseStepManager.add(caseStep);
    }

    public int deleteCaseStepById(int id) {
        return caseStepManager.delete(id);
    }

    public List<QualityCaseStep> findCaseStepList(QualityCaseStep caseStep) {
        return caseStepManager.findList(caseStep);
    }
    @Transactional(readOnly = true)
    public Integer getCaseMaxVersion(Integer caseId) {
        return caseStepManager.getMaxVersion(caseId);
    }
}
