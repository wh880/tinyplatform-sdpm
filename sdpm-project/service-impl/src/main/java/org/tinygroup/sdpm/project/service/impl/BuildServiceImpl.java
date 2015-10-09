package org.tinygroup.sdpm.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.project.biz.inter.BuildManager;
import org.tinygroup.sdpm.project.dao.pojo.ProjectBuild;
import org.tinygroup.sdpm.project.service.inter.BuildService;
import org.tinygroup.tinysqldsl.Pager;

import java.util.Date;

/**
 * Created by shenly13343 on 2015-09-20.
 */
@Component
public class BuildServiceImpl implements BuildService {
    @Autowired
    private BuildManager buildManager;

    public Integer softDeleteBuild(Integer buildId) {
        if (buildId == null || buildId <= 0) {
            return 0;
        } else {
            ProjectBuild build = new ProjectBuild();
            build.setBuildId(buildId);
            build.setBuildDeleted(build.DELETE_YES);
            return buildManager.softDelete(build);
        }
    }
    public ProjectBuild add(ProjectBuild build) {
        build.setBuildStories("");
        build.setBuildBugs("");
        build.setBuildDeleted(build.DELETE_NO);
        return buildManager.add(build);
    }

    public Pager<ProjectBuild> findPager(Integer projectId, Integer start, Integer limit, String order, boolean asc) {
        ProjectBuild build = new ProjectBuild();
        build.setBuildProject(projectId);
        build.setBuildDeleted(build.DELETE_NO);
        return buildManager.findPager(build, start, limit, order, asc);
    }

    public int updateBuild(ProjectBuild build) {
        return buildManager.update(build);
    }

    public Integer deleteBuild(Integer buildId) {
        return null;
    }

    public ProjectBuild findBuild(Integer id) {
        return buildManager.find(id);
    }
}
