package org.tinygroup.sdpm.quality.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.dao.condition.ConditionCarrier;
import org.tinygroup.sdpm.quality.biz.inter.TestTaskManager;
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestTask;
import org.tinygroup.sdpm.quality.service.inter.TestTaskService;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

@Component
public class TestTaskServiceImpl implements TestTaskService {

    @Autowired
    private TestTaskManager testTaskManager;

    public List<QualityTestTask> findTestTaskList(QualityTestTask testTask) {
        return testTaskManager.findList(testTask);
    }

    public QualityTestTask findTestTaskById(Integer id) {
        return testTaskManager.find(id);
    }

    public QualityTestTask addTestTask(QualityTestTask testTask) {
        return testTaskManager.add(testTask);
    }

    public int updateTestTask(QualityTestTask testTask) {
        return testTaskManager.update(testTask);
    }

    public Pager<QualityTestTask> findTestTaskPagerWithConditionCarrier(Integer start, Integer limit, QualityTestTask testTask, ConditionCarrier carrier, String sortName, boolean asc) {
        return testTaskManager.findPager(start, limit, testTask, carrier, sortName, asc);
    }

    public Pager<QualityTestTask> findTestTaskPager(Integer start, Integer limit, QualityTestTask qualityTestTask, String sortName, boolean asc) {
        return testTaskManager.findPager(start, limit, qualityTestTask, null, sortName, asc);
    }
}
