package org.tinygroup.sdpm.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.project.biz.inter.ProjectStoryManager;
import org.tinygroup.sdpm.project.service.inter.ProjectStoryService;

/**
 * Created by shenly13343 on 2015-09-20.
 */
@Component
public class ProjectStoryServiceImpl implements ProjectStoryService {
    @Autowired
    private ProjectStoryManager projectStoryManager;
}
