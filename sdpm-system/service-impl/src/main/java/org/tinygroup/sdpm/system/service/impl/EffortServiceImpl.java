package org.tinygroup.sdpm.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.sdpm.project.biz.inter.TaskManager;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTask;
import org.tinygroup.sdpm.system.biz.inter.EffortManager;
import org.tinygroup.sdpm.system.dao.pojo.SystemEffort;
import org.tinygroup.sdpm.system.service.inter.EffortService;
import org.tinygroup.tinysqldsl.Pager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
@Transactional
public class EffortServiceImpl implements EffortService {
    @Autowired
    private EffortManager effortManager;
    @Autowired
    private TaskManager taskManager;

    public Integer batchEffortSave(List<SystemEffort> list) {
        return effortManager.batchAdd(list);
    }

    public SystemEffort saveSystemEffort(SystemEffort systemEffort) {
        if (systemEffort.getEffortId() == null) {
            systemEffort.setEffortDate(new Date());
            if (systemEffort.getEffortBegin() == null && systemEffort.getEffortEnd() == null) {
                ProjectTask task = taskManager.find(systemEffort.getEffortObjectId());
                if (task.getTaskCanceledDate() != null || task.getTaskFinishedDate() != null) {
                    if (task.getTaskCloseDate() == null && task.getTaskFinishedDate() != null) {
                        systemEffort.setEffortEnd(new SimpleDateFormat("yyyy-MM-dd").format(task.getTaskFinishedDate()));
                    }
                    if (task.getTaskCloseDate() != null && task.getTaskFinishedDate() == null) {
                        systemEffort.setEffortEnd(new SimpleDateFormat("yyyy-MM-dd").format(task.getTaskCloseDate()));
                    }
                    if (task.getTaskCloseDate() != null && task.getTaskFinishedDate() != null) {
                        systemEffort.setEffortEnd(new SimpleDateFormat("yyyy-MM-dd").format(task.getTaskCloseDate()));
                    }
                }
                return effortManager.add(systemEffort);
            }
            return effortManager.add(systemEffort);
        } else {
            return effortManager.update(systemEffort);
        }
    }
    @Transactional(readOnly = true)
    public List<SystemEffort> findSystemEffortByAccount(String account) {
        return effortManager.findByAccount(account);
    }
    @Transactional(readOnly = true)
    public List<SystemEffort> findSystemEffortList(SystemEffort systemEffort) {
        return effortManager.find(systemEffort);
    }
    @Transactional(readOnly = true)
    public List<SystemEffort> findSystemEffortBetweenDate(SystemEffort systemEffort, Date beginDate, Date endDate) {
        return effortManager.findBetweenDate(systemEffort, beginDate, endDate);
    }
    @Transactional(readOnly = true)
    public List<SystemEffort> findSystemEffortByProjectId(Integer projectId) {
        return effortManager.findByProject(projectId);
    }
    @Transactional(readOnly = true)
    public Pager<SystemEffort> findSystemEffortPage(Integer start, Integer limit, SystemEffort SystemEffort, String sortName, boolean asc) {
        return effortManager.findByPage(start, limit, SystemEffort, sortName, asc);
    }
    @Transactional(readOnly = true)
    public List<SystemEffort> findSystemEffortListByOrder(SystemEffort systemEffort, String order, String orderType) {
        return effortManager.findList(systemEffort, order, orderType);
    }

    public Integer batchDeleteEffort(Integer... ids) {
        return effortManager.batchDelete(ids);
    }
    @Transactional(readOnly = true)
    public Pager<SystemEffort> findSystemEffortPagerByDate(Integer start, Integer limit,
                                                           SystemEffort effort, Date startDate, Date endDate,
                                                           String sortName, boolean asc) {
        return effortManager.findByDate(start, limit, effort, startDate, endDate, sortName, asc);
    }
    @Transactional(readOnly = true)
    public SystemEffort findSystemEffortById(Integer id) {
        return effortManager.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SystemEffort> findEffortListByUserAndDate(String userId, Date beginDate, Date endDate) {
        return effortManager.findByUserAndDate(userId, beginDate, endDate);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SystemEffort> findEffortListByIdList(List<Integer> list) {
        if (CollectionUtil.isEmpty(list)) {
            return null;
        }
        return effortManager.findEffortListByIdList(list);
    }
}
