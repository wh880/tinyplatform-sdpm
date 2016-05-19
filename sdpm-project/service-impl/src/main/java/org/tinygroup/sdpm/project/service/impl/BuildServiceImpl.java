package org.tinygroup.sdpm.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.commons.tools.ArrayUtil;
import org.tinygroup.sdpm.project.biz.inter.BuildManager;
import org.tinygroup.sdpm.project.dao.pojo.ProjectBuild;
import org.tinygroup.sdpm.project.service.inter.BuildService;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

/**
 * Created by shenly13343 on 2015-09-20.
 */
@Component
@Transactional
public class BuildServiceImpl implements BuildService {
    @Autowired
    private BuildManager buildManager;

    public Integer softDeleteBuild(Integer buildId) {
        if (buildId == null || buildId <= 0) {
            return 0;
        } else {
            ProjectBuild build = new ProjectBuild();
            build.setBuildId(buildId);
            build.setBuildDeleted(ProjectBuild.DELETE_YES);
            return buildManager.softDelete(build);
        }
    }

    public ProjectBuild addBuild(ProjectBuild build) {
        build.setBuildStories("");
        build.setBuildBugs("");
        build.setBuildDeleted(ProjectBuild.DELETE_NO);
        return buildManager.add(build);
    }
    @Transactional(readOnly = true)
    public Pager<ProjectBuild> findBuildPagerWithOrder(Integer projectId, Integer start, Integer limit, String order, boolean asc) {
        ProjectBuild build = new ProjectBuild();
        build.setBuildProject(projectId);
        build.setBuildDeleted(ProjectBuild.DELETE_NO);
        return buildManager.findPager(build, start, limit, order, asc);
    }

    public Integer updateBuild(ProjectBuild build) {
        return buildManager.update(build);
    }

    @Transactional(readOnly = true)
    public ProjectBuild findBuild(Integer id) {
        return buildManager.find(id);
    }

    public Integer deleteBuildByIds(List<ProjectBuild> ids) {

        int[] del = buildManager.batchDelBuildByIds(ids);
        if (ArrayUtil.isEmptyArray(del)) {
            return 0;
        }
        return del.length;
    }
    @Transactional(readOnly = true)
    public List<ProjectBuild> findListBuild(ProjectBuild projectBuild) {
        return buildManager.findList(projectBuild);
    }
    @Transactional(readOnly = true)
    public Pager<ProjectBuild> findPagerBuild(ProjectBuild projectBuild, Integer start, Integer limit, String order, boolean asc) {
        return buildManager.findPager(projectBuild, start, limit, order, asc);
    }

    public List<ProjectBuild> getBuildByIds(String... ids) {
        return buildManager.getBuildByIds(ids);
    }

    public List<ProjectBuild> getBuildByProducts(Integer... ids) {
        return buildManager.getBuildByProducts(ids);
    }
    @Transactional(readOnly = true)
    public List<ProjectBuild> buildInCondition(String condition, Integer limit, Integer productId, Integer projectId) {
        return buildManager.buildInCondition(condition, limit, productId, projectId);
    }

}
