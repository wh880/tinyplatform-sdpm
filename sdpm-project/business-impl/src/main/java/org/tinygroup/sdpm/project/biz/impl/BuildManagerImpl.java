package org.tinygroup.sdpm.project.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.common.util.common.NameUtil;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.project.biz.inter.BuildManager;
import org.tinygroup.sdpm.project.dao.ProjectBuildDao;
import org.tinygroup.sdpm.project.dao.pojo.ProjectBuild;
import org.tinygroup.tinysqldsl.Pager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shenly13343 on 2015-09-18.
 */
@Service
public class BuildManagerImpl implements BuildManager {
    @Autowired
    private ProjectBuildDao projectBuildDao;

    public Integer softDelete(ProjectBuild build) {
        return projectBuildDao.softDelete(build);
    }

    public ProjectBuild find(Integer id) {
        return projectBuildDao.getByKey(id);
    }

    public Pager<ProjectBuild> findPager(ProjectBuild build, Integer start, Integer limit, String order, boolean asc) {
        if (StringUtil.isBlank(order)) {
            return projectBuildDao.queryPager(start, limit, build);
        } else {
            OrderBy orderBy = new OrderBy(NameUtil.resolveNameDesc(order), asc);
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
        build.setBuildDeleted(ProjectBuild.DELETE_YES);
        return projectBuildDao.edit(build);
    }

    public int[] batchDelBuildByIds(List<ProjectBuild> keys) {
        return projectBuildDao.batchUpdateDel(keys);
    }

    public Pager<ProductStory> findBuildStory(int start, int limit, Integer buildId) {
        return projectBuildDao.findBuildStoryList(start, limit, buildId);
    }

    public List<ProjectBuild> getBuildByIds(String... ids) {
        return projectBuildDao.getBuildByKeys(ids);
    }

    public List<ProjectBuild> getBuildByProducts(Integer... ids) {
        if (ids == null || ids.length == 0) return new ArrayList<ProjectBuild>();
        return projectBuildDao.getBuildByProducts(ids);
    }

    public List<ProjectBuild> buildInCondition(String condition, Integer limit, Integer productId, Integer projectId) {
        return projectBuildDao.buildInCondition(condition, limit, productId, projectId);
    }


}
