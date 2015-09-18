package org.tinygroup.sdpm.project.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.project.biz.inter.TaskEsTimateManager;
import org.tinygroup.sdpm.project.dao.TaskestimateDao;

/**
 * Created by shenly13343 on 2015-09-18.
 */
@Service
@Transactional
public class TaskEsTimatesManagerImpl implements TaskEsTimateManager {
    @Autowired
    private TaskestimateDao taskestimateDao;
}
