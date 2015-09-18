package org.tinygroup.sdpm.project.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.project.biz.inter.TaskRelationManager;
import org.tinygroup.sdpm.project.dao.TaskrelationDao;

/**
 * Created by shenly13343 on 2015-09-18.
 */
@Service
@Transactional
public class TaskRelationManagerImpl implements TaskRelationManager {
    @Autowired
    private TaskrelationDao taskrelationDao;
}
