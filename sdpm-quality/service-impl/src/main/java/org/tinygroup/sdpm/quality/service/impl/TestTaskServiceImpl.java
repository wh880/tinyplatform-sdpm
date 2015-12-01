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
    private TestTaskManager testtaskmanager;

    public List<QualityTestTask> findTestTaskList(QualityTestTask testtask) {
        return testtaskmanager.findList(testtask);
    }

    public QualityTestTask findById(Integer id) {
        return testtaskmanager.find(id);
    }

    public QualityTestTask addTestTask(QualityTestTask testtask) {
        return testtaskmanager.add(testtask);
    }

    public int updateTestTask(QualityTestTask testtask) {
        return testtaskmanager.update(testtask);
    }

    public int deleteById(int id) {
        return testtaskmanager.delete(id);
    }

    public Pager<QualityTestTask> findTestTaskPager(Integer start, Integer limit, QualityTestTask testtask, ConditionCarrier carrier, String sortName, boolean asc) {
        return testtaskmanager.findPager(start, limit, testtask, carrier, sortName, asc);
    }

    public Pager<QualityTestTask> findTestTaskPager(Integer start, Integer limit, QualityTestTask testtask, String sortName, boolean asc) {
        return testtaskmanager.findPager(start, limit, testtask, null, sortName, asc);
    }
}
