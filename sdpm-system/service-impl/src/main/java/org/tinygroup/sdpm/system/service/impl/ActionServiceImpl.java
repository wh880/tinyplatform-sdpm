package org.tinygroup.sdpm.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.sdpm.system.biz.inter.ActionManager;
import org.tinygroup.sdpm.system.dao.pojo.SystemAction;
import org.tinygroup.sdpm.system.service.inter.ActionService;
import org.tinygroup.tinysqldsl.Pager;

import java.util.Date;
import java.util.List;

@Component
@Transactional
public class ActionServiceImpl implements ActionService {
    @Autowired
    private ActionManager actionManager;

    @Transactional(readOnly = true)
    public List<SystemAction> findAction(SystemAction systemAction, String orderby, boolean asc) {
        return actionManager.find(systemAction, orderby, asc);
    }
    @Transactional(readOnly = true)
    public Pager<SystemAction> findSystemActionPager(int page, int pageSize, SystemAction action, String order,
                                                     String ordertype) {

        return actionManager.findByPage(page, pageSize, action, order, ordertype);
    }

    public Pager<SystemAction> queryActionPager(int start, int limit, String chooseDate, SystemAction systemAction, String order,
                                                String orderType) {
        return actionManager.queryPager(start, limit, chooseDate, systemAction, order, orderType);
    }

    public Pager<SystemAction> queryActionBetweenDate(int start, int limit,
                                                      SystemAction action, String startDate, String endDate, String sortName,
                                                      boolean asc) {
        return actionManager.queryBetweenDate(start, limit, action, startDate, endDate, sortName, asc);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SystemAction> findActionListByIdList(List<Integer> idList) {
        if (CollectionUtil.isEmpty(idList)) {
            return null;
        }
        return actionManager.findActionListByIdList(idList);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SystemAction> findActionListByTypeList(List<SystemAction> bugs, List<SystemAction> stories, List<SystemAction> tasks, List<SystemAction> cases, List<SystemAction> releases, List<SystemAction> docList) {
        return actionManager.findActionListByTypeList(bugs, stories, tasks, cases, releases, docList);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SystemAction> findDiaryActionListByUserAndDate(String userId, Date beginDate, Date endDate) {
        return actionManager.findDiaryActionListByUserAndDate(userId, beginDate, endDate);
    }
}
