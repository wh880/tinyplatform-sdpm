package org.tinygroup.sdpm.quality.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.quality.biz.inter.TestResultManager;
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestResult;
import org.tinygroup.sdpm.quality.service.inter.TestResultService;

import java.util.List;

@Component
public class TestResultServiceImpl implements TestResultService {

    @Autowired
    private TestResultManager testResultManager;

    public List<QualityTestResult> findTestResultList(QualityTestResult qualityTestResult) {
        return testResultManager.findList(qualityTestResult);
    }

    public QualityTestResult addTestResult(QualityTestResult qualityTestResult) {
        return testResultManager.add(qualityTestResult);
    }

}
