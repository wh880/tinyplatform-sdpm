package org.tinygroup.sdpm.system.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.common.util.common.NameUtil;
import org.tinygroup.sdpm.system.biz.inter.ActionManager;
import org.tinygroup.sdpm.system.biz.util.ActionUtil;
import org.tinygroup.sdpm.system.dao.SystemActionDao;
import org.tinygroup.sdpm.system.dao.impl.ActionEnum;
import org.tinygroup.sdpm.system.dao.pojo.SystemAction;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.tinysqldsl.base.Condition;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ActionManagerImpl implements ActionManager {
    @Autowired
    private SystemActionDao systemActionDao;

    public SystemAction add(SystemAction systemAction) {
        return systemActionDao.add(systemAction);
    }

    public SystemAction updata(SystemAction systemAction) {
        systemActionDao.edit(systemAction);
        return systemAction;
    }

    public Integer delete(SystemAction systemAction) {
        int pk = systemAction.getActionId();
        return systemActionDao.deleteByKey(pk);
    }

    public List<SystemAction> find(SystemAction SystemAction, String orderBy, boolean asc) {
        List<SystemAction> actions;
        if (StringUtil.isBlank(orderBy)) {
            actions = systemActionDao.query(SystemAction);
        } else {
            actions = systemActionDao.query(SystemAction, new OrderBy(NameUtil.resolveNameDesc(orderBy), asc));
        }
        List<SystemAction> systemActions = new ArrayList<SystemAction>();
        if (actions.size() > 0) {

            for (SystemAction s : actions) {
                SystemAction action = systemActionDao.getActionAndObject(s);
                action.setActorName(s.getActorName());
                action.setUrl(ActionEnum.getUrl(s.getActionObjectType()) + s.getActionObjectId());
                systemActions.add(action);
            }
        }
        return systemActions;
    }

    public Pager<SystemAction> findByPage(int start, int limit, SystemAction systemAction, String order,
                                          String ordertype) {
        Pager<SystemAction> pager;
        if (order == null) {
            pager = systemActionDao.queryPager(start, limit, systemAction);
        } else {
            pager = systemActionDao.queryPager(start, limit, systemAction, new OrderBy(NameUtil.resolveNameDesc(order), !("desc".equals(ordertype)) ? true : false));
        }
        return mergePager(pager);
    }

    public Pager<SystemAction> queryPager(int start, int limit, String chooseDate, SystemAction systemAction, String order, String ordertype) {
        Condition condition = ActionUtil.getActionDateCondition(chooseDate);
        Pager<SystemAction> pager;
        if (order == null) {
            pager = systemActionDao.queryPager(start, limit, condition, systemAction);
        } else {
            pager = systemActionDao.queryPager(start, limit, condition, systemAction, new OrderBy(NameUtil.resolveNameDesc(order), !("desc".equals(ordertype)) ? true : false));
        }
        return mergePager(pager);
    }

    public Pager<SystemAction> queryBetweenDate(int start, int limit,
                                                SystemAction action, String startDate, String endDate, String sortName,
                                                boolean asc) {
        Pager<SystemAction> pager;
        if (StringUtil.isBlank(sortName)) {
            pager = systemActionDao.findByDate(start, limit, action, startDate, endDate);
            return mergePager(pager);
        }
        OrderBy orderBy = new OrderBy(sortName, asc);
        pager = systemActionDao.findByDate(start, limit, action, startDate, endDate, orderBy);
        return mergePager(pager);
    }

    private Pager<SystemAction> mergePager(Pager<SystemAction> systemActionPager) {
        List<SystemAction> actions = new ArrayList<SystemAction>();
        if (systemActionPager.getRecords().size() > 0) {
            for (SystemAction s : systemActionPager.getRecords()) {
                SystemAction action = systemActionDao.getActionAndObject(s);
                if (action != null) {
                    action.setActorName(s.getActorName());
                    action.setUrl(ActionEnum.getUrl(s.getActionObjectType()) + s.getActionObjectId());
                    actions.add(action);
                }
            }
        }
        systemActionPager.setRecords(actions);
        return systemActionPager;
    }

    @Override
    public List<SystemAction> findActionListByIdList(List<Integer> idList) {
        return systemActionDao.findActionListByIdList(idList);
    }

    @Override
    public List<SystemAction> findActionListByTypeList(List<SystemAction> bugs, List<SystemAction> stories, List<SystemAction> tasks, List<SystemAction> cases, List<SystemAction> releases, List<SystemAction> docs) {
        List<SystemAction> list = new ArrayList<SystemAction>();
        List<SystemAction> bugList = null;
        List<SystemAction> storiesList = null;
        List<SystemAction> taskList = null;
        List<SystemAction> caseList = null;
        List<SystemAction> releaseList = null;
        List<SystemAction> docList = null;
        if (!CollectionUtil.isEmpty(bugs)) {
            List<Integer> temp = new ArrayList<Integer>();
            for (SystemAction systemAction : bugs) {
                temp.add(systemAction.getActionId());
            }
            bugList = systemActionDao.findBugByBugList(temp);
            list.addAll(bugList);
        }
        if (!CollectionUtil.isEmpty(stories)) {
            List<Integer> temp = new ArrayList<Integer>();
            for (SystemAction systemAction : stories) {
                temp.add(systemAction.getActionId());
            }
            storiesList = systemActionDao.findStoryByStoryList(temp);
            list.addAll(storiesList);
        }
        if (!CollectionUtil.isEmpty(cases)) {
            List<Integer> temp = new ArrayList<Integer>();
            for (SystemAction systemAction : cases) {
                temp.add(systemAction.getActionId());
            }
            caseList = systemActionDao.findCaseByCaseList(temp);
            list.addAll(caseList);
        }
        if (!CollectionUtil.isEmpty(releases)) {
            List<Integer> temp = new ArrayList<Integer>();
            for (SystemAction systemAction : releases) {
                temp.add(systemAction.getActionId());
            }
            releaseList = systemActionDao.findReleaseByReleaseList(temp);
            list.addAll(releaseList);
        }
        if (!CollectionUtil.isEmpty(tasks)) {
            List<Integer> temp = new ArrayList<Integer>();
            for (SystemAction systemAction : tasks) {
                temp.add(systemAction.getActionId());
            }
            taskList = systemActionDao.findTaskByTaskList(temp);
            list.addAll(taskList);
        }
        if (!CollectionUtil.isEmpty(docs)) {
            List<Integer> temp = new ArrayList<Integer>();
            for (SystemAction systemAction : docs) {
                temp.add(systemAction.getActionId());
            }
            docList = systemActionDao.findDocByCaseList(temp);
            list.addAll(docList);
        }
        return list;
    }

    @Override
    public List<SystemAction> findDiaryActionListByUserAndDate(String userId, Date beginDate, Date endDate) {
        return systemActionDao.findActionListByUserIdAndDate(userId, beginDate, endDate);
    }
}
