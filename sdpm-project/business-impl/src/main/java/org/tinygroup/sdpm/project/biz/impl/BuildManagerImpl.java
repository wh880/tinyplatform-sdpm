package org.tinygroup.sdpm.project.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.common.util.ComplexSearch.SearchInfos;
import org.tinygroup.sdpm.product.dao.pojo.ProductAndLine;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.productLine.dao.pojo.ProductLine;
import org.tinygroup.sdpm.project.biz.inter.BuildManager;
import org.tinygroup.sdpm.project.dao.ProjectBuildDao;
import org.tinygroup.sdpm.project.dao.pojo.ProjectBuild;
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
        return projectBuildDao.edits(build);
    }

    public Integer delete(int id) {
        ProjectBuild build = new ProjectBuild();
        build.setBuildId(id);
        build.setBuildDeleted(build.DELETE_YES);
        return projectBuildDao.edit(build);
    }
    public int[] batchDelBuildByIds(List<ProjectBuild> keys) {
        //
        return projectBuildDao.batchUpdateDel(keys);
    }

	public List<ProductAndLine> getProductLineTree(ProductLine t) {

		return projectBuildDao.getProductLineTree(t);
	}
    public List<ProjectBuild> findStoryList(ProjectBuild projectBuild) {
//        projectBuildDao.findBuildStory(projectBuild.getBuildId());
        return projectBuildDao.query(projectBuild);
    }

    public Pager<ProductStory> findBuildStory(int start, int limit, Integer buildId) {
        return projectBuildDao.findBuildStorys(start,limit,buildId);
    }


}
