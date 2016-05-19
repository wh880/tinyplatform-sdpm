package org.tinygroup.sdpm.quality.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.dao.condition.ConditionCarrier;
import org.tinygroup.sdpm.quality.biz.inter.BugManager;
import org.tinygroup.sdpm.quality.dao.pojo.BugCount;
import org.tinygroup.sdpm.quality.dao.pojo.QualityBug;
import org.tinygroup.sdpm.quality.service.inter.BugService;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;
import java.util.Map;

@Component
@Transactional
public class BugServiceImpl implements BugService {
    @Autowired
    private BugManager bugmanager;
    @Transactional(readOnly = true)
    public List<QualityBug> findBugList(QualityBug qualityBug) {
        return bugmanager.findList(qualityBug);
    }

    public QualityBug addBug(QualityBug bug) {
        bug.setBugStatus("1");
        return bugmanager.add(bug);
    }
    @Transactional(readOnly = true)
    public QualityBug findQualityBugById(Integer id) {
        return bugmanager.find(id);
    }

    public int updateBug(QualityBug bug) {
        return bugmanager.update(bug);
    }

    public Integer deleteBug(Integer bugId) {

        return bugmanager.delete(bugId);
    }
    @Transactional(readOnly = true)
    public Map<String, List<BugCount>> bugReport(String code, Integer productId) {
        return bugmanager.report(code, productId);
    }
    @Transactional(readOnly = true)
    public Pager<QualityBug> findStoryChangedBugs(Integer start, Integer limit, ConditionCarrier carrier, QualityBug bug, String sortName, boolean asc) {
        return bugmanager.queryStoryChangedBugs(start, limit, carrier, bug, sortName, asc);
    }
    @Transactional(readOnly = true)
    public Pager<QualityBug> findBugListPager(Integer start, Integer limit, ConditionCarrier carrier, QualityBug bug, String sortName, boolean asc) {
        return bugmanager.findBugListPager(start, limit, carrier, bug, sortName, asc);
    }
    @Transactional(readOnly = true)
    public List<QualityBug> getBugsInReleaseDoc(QualityBug bug) {
        return bugmanager.getBugsInReleaseDoc(bug);
    }
    @Transactional(readOnly = true)
    public List<QualityBug> bugInCondition(String condition, Integer limit, Integer productId) {
        return bugmanager.bugInCondition(condition, limit, productId);
    }

    public int[] batchDeleteBug(List<QualityBug> bugIds) {
        return bugmanager.batchDelete(bugIds);
    }

    @Override
    @Transactional(readOnly = true)
    public QualityBug findBugByBugId(Integer bugId) {
        return bugmanager.findBugByBugId(bugId);
    }

    @Override
    public List<QualityBug> findBugByProductIdAndBugTitle(String bugTitle, Integer productId, String status) {
        return bugmanager.findBugByProductIdAndBugTitle(bugTitle,productId,status);
    }
}