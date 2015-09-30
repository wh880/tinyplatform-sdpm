package org.tinygroup.sdpm.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.project.biz.inter.BuildManager;
import org.tinygroup.sdpm.project.dao.pojo.ProjectBuild;
import org.tinygroup.sdpm.project.service.inter.BuildService;
import org.tinygroup.tinysqldsl.Pager;

/**
 * Created by shenly13343 on 2015-09-20.
 */
@Component
public class BuildServiceImpl implements BuildService {
    @Autowired
    private BuildManager buildManager;

    public ProjectBuild add(ProjectBuild build) {
        return null;
    }

    public Pager<ProjectBuild> findPager(Integer projectId, Integer start, Integer limit, String order, boolean asc) {
        ProjectBuild build = new ProjectBuild();
        build.setBuildProject(projectId);
        return buildManager.findPager(build, start, limit, order, asc);
    }

    public ProjectBuild updateBuild(ProjectBuild build) {
        return null;
    }

    public Integer deleteBuild(Integer buildId) {
        return null;
    }
}
