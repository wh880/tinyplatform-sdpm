package org.tinygroup.sdpm.project.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.project.biz.inter.ProjectStoryManager;
import org.tinygroup.sdpm.project.dao.ProjectStoryDao;
import org.tinygroup.sdpm.project.dao.pojo.ProjectProduct;
import org.tinygroup.sdpm.project.dao.pojo.ProjectStory;

import java.util.List;

/**
 * Created by shenly13343 on 2015-09-21.
 */
@Service
@Transactional
public class ProjectStoryManagerImpl implements ProjectStoryManager {
    @Autowired
    private ProjectStoryDao projectStoryDao;

    public List<ProjectStory> findSrotys(Integer projectId) {
        return projectStoryDao.findByProjectID(projectId);
    }

    public List<ProjectProduct> findList(ProjectStory projectStory) {
        return null;
    }

    public ProjectProduct add(ProjectProduct projectProduct) {
        return null;
    }

    public Integer update(ProjectProduct projectproduct) {
        return null;
    }

    public Integer delete(int id) {
        return null;
    }
}
