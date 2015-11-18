package org.tinygroup.sdpm.project.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.project.biz.inter.BuildManager;
import org.tinygroup.sdpm.project.dao.ProjectBuildDao;
import org.tinygroup.sdpm.project.dao.pojo.ProjectBuild;
import org.tinygroup.sdpm.quality.dao.pojo.QualityBug;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

/**
 * Created by shenly13343 on 2015-09-18.
 */
@Service
@Transactional
public class BuildManagerImpl implements BuildManager {
    @Autowired
    private ProjectBuildDao projectBuildDao;

    public Integer softDelete(ProjectBuild build) {
        return projectBuildDao.softDelete(build);
    }

    public Integer deleteBuildByProductId(Integer productId) {
        return projectBuildDao.deleteBuildByProductId(productId);
    }

    public ProjectBuild find(Integer id) {
        return projectBuildDao.getByKey(id);
    }

    public Pager<ProjectBuild> findPager(ProjectBuild build, Integer start, Integer limit, String order, boolean asc) {
        if (StringUtil.isBlank(order)) {
            return projectBuildDao.queryPager(start, limit, build);
        } else {
            OrderBy orderBy = new OrderBy(order, asc);
            return projectBuildDao.queryPager(start, limit, build, orderBy);
        }

    }

    public List<ProjectBuild> findList(ProjectBuild build) {
        return projectBuildDao.query(build);
    }

    public ProjectBuild add(ProjectBuild build) {
        return projectBuildDao.add(build);
    }

    public int update(ProjectBuild build) {
        return projectBuildDao.edit(build);
    }

    public Integer delete(int id) {
        ProjectBuild build = new ProjectBuild();
        build.setBuildId(id);
        build.setBuildDeleted(build.DELETE_YES);
        return projectBuildDao.edit(build);
    }
    public int[] batchDelBuildByIds(List<ProjectBuild> keys) {
        return projectBuildDao.batchUpdateDel(keys);
    }

    public List<ProjectBuild> findStoryList(ProjectBuild projectBuild) {
        return projectBuildDao.query(projectBuild);
    }

    public Pager<ProductStory> findBuildStory(int start, int limit, Integer buildId) {
        return projectBuildDao.findBuildStoryList(start,limit,buildId);
    }

    public Pager<QualityBug> findBuildBug(int start, int limit, Integer buildId) {
        return projectBuildDao.findBuildBugs(start,limit,buildId);
    }

    public Pager<QualityBug> findBuildLegacyBug(int start, int limit, Integer buildId) {
        return projectBuildDao.findBuildLegacyBugs(start,limit,buildId);
    }

    public List<ProjectBuild> getBuildByIds(String... ids) {
        return projectBuildDao.getBuildByKeys(ids);
    }

    public Pager<ProductStory> findNoBuildStory(int start, int limit, String condition, Integer buildId) {
        return projectBuildDao.findNoBuildStoryList(start, limit, condition, buildId);
    }

    public Pager<QualityBug> findNoBuildBug(int start, int limit, String condition, Integer buildId) {
        return projectBuildDao.findNoBuildBugs(start,limit,condition,buildId);
    }

    public Integer deleteBuildStory(Integer storyId, Integer buildId){
        return projectBuildDao.deleteBuildStory(storyId,buildId);
    }

    public Integer deleteBuildBug(Integer bugId, Integer buildId){
        return projectBuildDao.deleteBuildBug(bugId,buildId);
    }

    public Integer linkBuildStory(Integer storyId, Integer buildId){
        return projectBuildDao.linkBuildStory(storyId, buildId);
    }

    public Integer linkBuildBug(Integer bugId, Integer buildId){
        return projectBuildDao.linkBuildBug(bugId, buildId);
    }

}
