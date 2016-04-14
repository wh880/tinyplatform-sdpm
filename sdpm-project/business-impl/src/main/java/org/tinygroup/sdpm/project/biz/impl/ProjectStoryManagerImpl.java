package org.tinygroup.sdpm.project.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.common.util.common.NameUtil;
import org.tinygroup.sdpm.dao.complexsearch.SearchInfos;
import org.tinygroup.sdpm.dao.complexsearch.SqlUtil;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.project.biz.inter.ProjectStoryManager;
import org.tinygroup.sdpm.project.dao.ProjectStoryDao;
import org.tinygroup.sdpm.project.dao.pojo.ProjectStory;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

/**
 * Created by shenly13343 on 2015-09-21.
 */
@Service
public class ProjectStoryManagerImpl implements ProjectStoryManager {
    @Autowired
    private ProjectStoryDao projectStoryDao;

    public Integer batchDel(Integer[] storyIds, Integer projectId) {
        return projectStoryDao.batchDel(projectId, storyIds);
    }

    public int[] linkStory(List<ProjectStory> projectStoryList) {
        return projectStoryDao.batchInsert(projectStoryList);
    }

    public int[] updateLink(List<ProjectStory> projectStoryList) {
        return projectStoryDao.batchUpdate(projectStoryList);
    }

    public Pager<ProductStory> findStoryToLink(Integer projectId, Integer start, Integer limit, String order, String orderType, boolean isNotInProjectStory) {
        if (order == null) {
            return projectStoryDao.findStory(projectId, start, limit, isNotInProjectStory);
        } else {
            OrderBy orderBy = new OrderBy(NameUtil.resolveNameDesc(order), "asc".equals(orderType) ? true : false);
            return projectStoryDao.findStory(projectId, start, limit, isNotInProjectStory, orderBy);
        }
    }

    public List<ProjectStory> findStoryList(Integer projectId) {
        return projectStoryDao.findByProjectID(projectId);
    }

    public List<ProjectStory> findList(ProjectStory projectStory) {
        return projectStoryDao.query(projectStory);
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
