package org.tinygroup.sdpm.project.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.common.util.ComplexSearch.SearchInfos;
import org.tinygroup.sdpm.common.util.ComplexSearch.SqlUtil;
import org.tinygroup.sdpm.common.util.common.NameUtil;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.project.biz.inter.ProjectStoryManager;
import org.tinygroup.sdpm.project.dao.ProjectProductDao;
import org.tinygroup.sdpm.project.dao.ProjectStoryDao;
import org.tinygroup.sdpm.project.dao.pojo.ProjectProduct;
import org.tinygroup.sdpm.project.dao.pojo.ProjectStory;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

/**
 * Created by shenly13343 on 2015-09-21.
 */
@Service
@Transactional
public class ProjectStoryManagerImpl implements ProjectStoryManager {
    @Autowired
    private ProjectStoryDao projectStoryDao;
    @Autowired
    private ProjectProductDao projectProductDao;

    public Integer batchDel(Integer[] storyIds, Integer projectId) {
        return projectStoryDao.batchDel(projectId, storyIds);
    }

    public int[] linkStory(List<ProjectStory> projectStoryList) {
        return projectStoryDao.batchInsert(projectStoryList);
    }

    public int[] updaeLink(List<ProjectStory> projectStoryList) {

        return projectStoryDao.batchUpdate(projectStoryList);
    }

    public Pager<ProductStory> findStoryToLink(Integer projectId, Integer start, Integer limit, String order, String oredertype) {
        if (order == null) {
            return projectProductDao.findStory(projectId, start, limit);
        } else {
            OrderBy orderBy = new OrderBy(order, "asc".equals(oredertype) ? true : false);
            return projectProductDao.findStory(projectId, start, limit, orderBy);
        }

    }

    public List<ProjectStory> findSrotys(Integer projectId) {
        return projectStoryDao.findByProjectID(projectId);
    }

    public List<ProjectStory> findList(ProjectStory projectStory) {
        return projectStoryDao.query(projectStory);
    }

    public ProjectProduct add(ProjectProduct projectProduct) {
        return null;
    }

    public Integer update(ProjectProduct projectproduct) {
        return null;
    }

    public Integer delete(int id) {
        return projectStoryDao.deleteByKey(id);
    }

    public Integer deleteByProjectStory(Integer projectId, Integer storyId) {
        return projectStoryDao.deleteByProjectStory(projectId, storyId);
    }

    public Pager<ProjectStory> findPager(int start, int limit, ProjectStory story, String statusCondition, SearchInfos conditions,
                                         String groupOperate, String columnName, boolean asc) {
        String condition = conditions != null ? SqlUtil.toSql(conditions.getInfos(), groupOperate) : "";
        condition = condition != null && !"".equals(condition) ? (statusCondition != null && !"".equals(statusCondition) ? condition + " and "
                + statusCondition : condition)
                : statusCondition;
        OrderBy orderBy = null;
        if (columnName != null && !"".equals(columnName)) {
            orderBy = new OrderBy(NameUtil.resolveNameDesc(columnName), asc);
        }
        if (condition != null && !"".equals(condition)) {
            return projectStoryDao.complexQuery(start, limit, story, condition,
                    orderBy);
        }
        return projectStoryDao.queryPager(start, limit, story, orderBy);
    }
}
