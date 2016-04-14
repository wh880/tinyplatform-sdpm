package org.tinygroup.sdpm.quality.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinygroup.sdpm.quality.biz.inter.TestResultManager;
import org.tinygroup.sdpm.quality.dao.QualityTestResultDao;
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestResult;

import java.util.List;

@Service
public class TestResultManagerImpl implements TestResultManager {

    @Autowired
    private QualityTestResultDao testresultdao;

    public List<QualityTestResult> findList(QualityTestResult testresult) {
        return testresultdao.query(testresult);
    }

    public QualityTestResult find(int id) {
        return testresultdao.getByKey(id);
    }

    public int update(QualityTestResult testresult) {
        return testresultdao.edit(testresult);
    }

    public QualityTestResult add(QualityTestResult qualityTestResult) {
        return testresultdao.add(qualityTestResult);
    }
}
