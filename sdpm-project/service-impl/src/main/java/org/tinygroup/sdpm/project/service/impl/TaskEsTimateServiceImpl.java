package org.tinygroup.sdpm.project.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.project.biz.inter.TaskEsTimateManager;
import org.tinygroup.sdpm.project.service.inter.TaskEsTimateService;

/**
 * Created by wangying14938 on 2015-09-18.
 */

@Component
public class TaskEsTimateServiceImpl implements TaskEsTimateService {
    @Autowired
    private TaskEsTimateManager taskEsTimateManager;
}
