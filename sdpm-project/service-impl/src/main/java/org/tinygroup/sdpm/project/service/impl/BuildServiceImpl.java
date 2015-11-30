package org.tinygroup.sdpm.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.commons.tools.ArrayUtil;
import org.tinygroup.sdpm.common.util.ComplexSearch.SearchInfos;
import org.tinygroup.sdpm.project.biz.inter.BuildManager;
import org.tinygroup.sdpm.project.dao.pojo.ProjectBuild;
import org.tinygroup.sdpm.project.service.inter.BuildService;
import org.tinygroup.sdpm.quality.dao.pojo.QualityBug;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

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

    public ProjectBuild addBuild(ProjectBuild build) {
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

    public Integer updateBuild(ProjectBuild build) {
        return buildManager.update(build);
    }

    public Integer deleteBuildByProductId(Integer productId) {
        return buildManager.deleteBuildByProductId(productId);
    }

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

    public List<ProjectBuild> findListBuild(ProjectBuild projectBuild) {
        return buildManager.findList(projectBuild);
    }

    public Pager<ProjectBuild> findPagerBuild(ProjectBuild projectBuild, Integer start, Integer limit, String order, boolean asc) {
        return buildManager.findPager(projectBuild, start, limit, order, asc);
    }

    public Integer deleteBuildStory(Integer storyId, Integer buildId) {
        return buildManager.deleteBuildStory(storyId, buildId);
    }

    public Integer linkBuildStory(Integer storyId, Integer buildId) {
        return buildManager.linkBuildStory(storyId, buildId);
    }

    public Integer deleteBuildBug(Integer bugId, Integer buildId) {
        return buildManager.deleteBuildBug(bugId, buildId);
    }

    public Pager<QualityBug> findBugPager(int start, int limit, int id, SearchInfos conditions, String groupOperate) {
        return buildManager.findBuildBug(start, limit, id);
    }

    public Pager<QualityBug> findBugLegacyPager(int start, int limit, int id, SearchInfos conditions, String groupOperate) {
        return buildManager.findBuildLegacyBug(start, limit, id);
    }

    public List<ProjectBuild> getBuildByIds(String... ids) {
        return buildManager.getBuildByIds(ids);
    }

    public List<ProjectBuild> getBuildByProducts(Integer... ids) {
        return buildManager.getBuildByProducts(ids);
    }

    public List<ProjectBuild> buildInCondition(String condition, Integer productId, Integer projectId) {
        return buildManager.buildInCondition(condition, productId, projectId);
    }

    public Pager<QualityBug> findNoBuildBug(int start, int limit, int id, String condition, SearchInfos conditions, String groupOperate) {
        return buildManager.findNoBuildBug(start, limit, condition, id);
    }

}
