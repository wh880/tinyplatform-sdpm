package org.tinygroup.sdpm.project.service.impl;

import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.project.dao.pojo.ProjectBuild;
import org.tinygroup.sdpm.project.service.inter.BuildService;
import org.tinygroup.tinysqldsl.Pager;

/**
 * Created by shenly13343 on 2015-09-20.
 */
@Component
public class BuildServiceImpl implements BuildService {

    public ProjectBuild add(ProjectBuild build) {
        return null;
    }

    public Pager<ProjectBuild> findPager(int page, int pagesize, int projectId) {
        return null;
    }

    public ProjectBuild updateBuild(ProjectBuild build) {
        return null;
    }

    public Integer deleteBuild(Integer buildId) {
        return null;
    }
}
