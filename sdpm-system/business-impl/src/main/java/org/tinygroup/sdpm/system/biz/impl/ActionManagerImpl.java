package org.tinygroup.sdpm.system.biz.impl;

import com.thoughtworks.xstream.io.xml.BEAStaxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
@Transactional
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
    public List<SystemAction> findActionListByTypeList(List<String> bugs, List<String> stories, List<String> tasks) {
        return systemActionDao.findActionListByIdListAndType(bugs, tasks, stories);
    }

    @Override
    public List<SystemAction> findDiaryActionListByUserAndDate(String userId, Date beginDate, Date endDate) {
        return systemActionDao.findActionListByUserIdAndDate(userId, beginDate, endDate);
    }
}
