package org.tinygroup.sdpm.quality.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.common.util.common.NameUtil;
import org.tinygroup.sdpm.dao.condition.CallBackFunction;
import org.tinygroup.sdpm.dao.condition.ConditionCarrier;
import org.tinygroup.sdpm.dao.condition.ConditionUtils;
import org.tinygroup.sdpm.quality.biz.inter.BugManager;
import org.tinygroup.sdpm.quality.dao.QualityBugDao;
import org.tinygroup.sdpm.quality.dao.pojo.BugCount;
import org.tinygroup.sdpm.quality.dao.pojo.QualityBug;
import org.tinygroup.sdpm.system.dao.impl.util.ModuleUtil;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.tinysqldsl.base.Condition;
import org.tinygroup.tinysqldsl.expression.FragmentExpressionSql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BugManagerImpl implements BugManager {
    @Autowired
    private QualityBugDao bugdao;

    public List<QualityBug> findList(QualityBug bug) {
        return bugdao.query(bug);
    }

    public QualityBug add(QualityBug bug) {
        Integer no = bugdao.getMaxNo(bug.getProductId());
        bug.setNo(no == null ? 1 : no + 1);
        return bugdao.add(bug);
    }

    public QualityBug find(Integer id) {
        return bugdao.getByKey(id);
    }

    public Integer update(QualityBug bug) {

        return bugdao.edit(bug);
    }

    public int[] batchUpdate(List<QualityBug> bugs) {

        return bugdao.batchUpdate(bugs);
    }

    public Pager<QualityBug> findPager(Integer start, Integer limit, String conditions, QualityBug bug, String sortName, boolean asc) {
        if (StringUtil.isBlank(sortName)) {
            return bugdao.queryPager(start, limit, (((conditions != null && !"".equals(conditions)) ? FragmentExpressionSql.fragmentCondition(conditions) : null)), bug);
        } else {
            OrderBy orderby = new OrderBy(NameUtil.resolveNameDesc(sortName), asc);
            return bugdao.queryPager(start, limit, (((conditions != null && !"".equals(conditions)) ? FragmentExpressionSql.fragmentCondition(conditions) : null)), bug, orderby);
        }
    }

    public Integer delete(Integer id) {

        return bugdao.softDelete(id);
    }

    public Map<String, List<BugCount>> report(String code, Integer productId) {
        if (StringUtil.isBlank(code)) return new HashMap<String, List<BugCount>>();
        String[] codes = code.split(",");
        if (codes != null && codes.length > 0) {
            Map<String, List<BugCount>> bugCountMap = new HashMap<String, List<BugCount>>();
            for (String countCode : codes) {
                String[] sqlCodes = countCode.split("_");
                List<BugCount> result = bugdao.getCount(sqlCodes[0], productId);
                bugCountMap.put(countCode, result);
            }
            return bugCountMap;
        }
        return new HashMap<String, List<BugCount>>();
    }

    public Pager<QualityBug> queryStoryChangedBugs(Integer start, Integer limit, ConditionCarrier carrier, QualityBug bug, String sortName, boolean asc) {
        if (StringUtil.isBlank(sortName)) {
            return bugdao.queryStoryChangedBugs(start, limit, mergeCondition(carrier), bug);
        }
        return bugdao.queryStoryChangedBugs(start, limit, mergeCondition(carrier), bug, new OrderBy(NameUtil.resolveNameDesc("qualityBug." + sortName), asc));
    }

    public Pager<QualityBug> findBugListPager(Integer start, Integer limit, ConditionCarrier carrier, QualityBug bug, String sortName, boolean asc) {
        if (StringUtil.isBlank(sortName)) {
            return bugdao.queryPager(start, limit, mergeCondition(carrier), bug);
        } else {
            OrderBy orderby = new OrderBy(NameUtil.resolveNameDesc(sortName), asc);
            return bugdao.queryPager(start, limit, mergeCondition(carrier), bug, orderby);
        }
    }

    public List<QualityBug> getBugsInReleaseDoc(QualityBug bug) {
        return bugdao.getBugsInReleaseDoc(bug);
    }

    public List<QualityBug> bugInCondition(String condition, Integer limit, Integer productId) {
        return bugdao.bugInCondition(condition, limit, productId);
    }

    public int[] batchDelete(List<QualityBug> bugIds) {

        return bugdao.batchUpdateDel(bugIds);
    }

    private Condition mergeCondition(ConditionCarrier carrier) {
        return ConditionUtils.mergeCondition(carrier, new CallBackFunction() {
            public String moduleRoot(String moduleId) {
                return ModuleUtil.getConditionByRootWithoutOperate(Integer.parseInt(moduleId.substring(1)), "bug");
            }

            public String module(String moduleId) {
                return ModuleUtil.getConditionWithoutOperate(Integer.parseInt(moduleId));
            }
        });
    }

    @Override
    public QualityBug findBugByBugId(Integer bugId) {
        return bugdao.findBugByBugId(bugId);
    }

    @Override
    public List<QualityBug> findBugByProductIdAndBugTitle(String bugTitle, Integer productId, String status) {
        return bugdao.findBugByProductIdAndBugTitle(bugTitle,productId,status);
    }
}
