package org.tinygroup.sdpm.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.project.biz.inter.BurnManager;
import org.tinygroup.sdpm.project.dao.pojo.ProjectBurn;
import org.tinygroup.sdpm.project.service.inter.BurnService;

import java.util.List;

/**
 * Created by wangying14938 on 2015-09-18.
 */
@Component
public class BurnServiceImpl implements BurnService {
    @Autowired
    private BurnManager burnManager;

    public List<ProjectBurn> findById(int projectId) {
        return null;
    }

    public int addBurn(ProjectBurn burn) {
        return 0;
    }

    public List<ProjectBurn> findBurnByProjectId(int projectId) {
        return null;
    }

    public Integer deleteBurnByProjectDate(int projectId, String date) {
        return null;
    }
}
