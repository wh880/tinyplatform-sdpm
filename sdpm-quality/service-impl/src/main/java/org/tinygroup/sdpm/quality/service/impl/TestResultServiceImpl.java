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

    public List<QualityTestResult> findTestResultList(QualityTestResult testResult) {
        return testResultManager.findList(testResult);
    }

    public QualityTestResult findTestResultById(int id) {
        return testResultManager.find(id);
    }

    public int updateTestResult(QualityTestResult testResult) {
        return testResultManager.update(testResult);
    }

    public QualityTestResult addTestResult(QualityTestResult qualityTestResult) {
        return testResultManager.add(qualityTestResult);
    }

}
