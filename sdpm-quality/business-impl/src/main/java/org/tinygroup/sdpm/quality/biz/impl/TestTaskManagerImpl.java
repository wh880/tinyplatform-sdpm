package org.tinygroup.sdpm.quality.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.common.util.common.NameUtil;
import org.tinygroup.sdpm.dao.condition.ConditionCarrier;
import org.tinygroup.sdpm.dao.condition.ConditionUtils;
import org.tinygroup.sdpm.quality.biz.inter.TestTaskManager;
import org.tinygroup.sdpm.quality.dao.QualityTestTaskDao;
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestTask;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.tinysqldsl.base.Condition;

import java.util.List;

@Service
public class TestTaskManagerImpl implements TestTaskManager {

    @Autowired
    private QualityTestTaskDao testtaskdao;

    public List<QualityTestTask> findList(QualityTestTask testtask) {
        return testtaskdao.query(testtask);
    }

    public QualityTestTask add(QualityTestTask testtask) {
        Integer no = testtaskdao.getMaxNo(testtask.getProductId());
        testtask.setNo(no == null ? 1 : no + 1);
        return testtaskdao.add(testtask);
    }

    public int update(QualityTestTask testtask) {
        return testtaskdao.edit(testtask);
    }

    public int delete(int id) {
        QualityTestTask testtask = new QualityTestTask();
        testtask.setTestversionId(id);
        testtask.setDeleted(QualityTestTask.DELETE_YES);
        return testtaskdao.edit(testtask);
    }

    public Pager<QualityTestTask> findPager(Integer start, Integer limit, QualityTestTask testtask, ConditionCarrier carrier, String sortName, boolean asc) {
        if (StringUtil.isBlank(sortName)) {
            return testtaskdao.queryPager(start, limit, testtask, mergeCondition(carrier));
        }
        OrderBy order = new OrderBy(NameUtil.resolveNameDesc(sortName), asc);
        return testtaskdao.queryPager(start, limit, testtask, mergeCondition(carrier), order);
    }

    public QualityTestTask find(Integer id) {
        if (id != null) {
            return testtaskdao.getByKey(id);
        }
        return null;
    }

    private Condition mergeCondition(ConditionCarrier carrier) {
        return ConditionUtils.mergeCondition(carrier, null);
    }
}
