package org.tinygroup.sdpm.quality.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinygroup.sdpm.quality.biz.inter.CaseStepManager;
import org.tinygroup.sdpm.quality.dao.QualityCaseStepDao;
import org.tinygroup.sdpm.quality.dao.pojo.QualityCaseStep;

import java.util.List;

@Service
public class CaseStepManagerImpl implements CaseStepManager {
    @Autowired
    private QualityCaseStepDao qualityCaseStepDao;

    public QualityCaseStep find(Integer id) {
        return qualityCaseStepDao.getByKey(id);
    }

    public QualityCaseStep add(QualityCaseStep caseStep) {

        return qualityCaseStepDao.add(caseStep);
    }

    public Integer delete(Integer id) {

        return qualityCaseStepDao.deleteByKeys(id);
    }

    public List<QualityCaseStep> findList(QualityCaseStep caseStep) {
        return qualityCaseStepDao.query(caseStep);
    }

    public int[] batchAdd(List<QualityCaseStep> qualityCaseSteps) {
        return qualityCaseStepDao.batchInsert(qualityCaseSteps);
    }

    public Integer getMaxVersion(Integer caseId) {
        return qualityCaseStepDao.getMaxVersion(caseId);
    }
}
