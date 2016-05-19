package org.tinygroup.sdpm.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.dao.complexsearch.SearchInfos;
import org.tinygroup.sdpm.dao.condition.ConditionCarrier;
import org.tinygroup.sdpm.product.biz.inter.StoryManager;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.project.biz.inter.BuildManager;
import org.tinygroup.sdpm.project.biz.inter.ProjectManager;
import org.tinygroup.sdpm.project.biz.inter.ProjectStoryManager;
import org.tinygroup.sdpm.project.biz.inter.TaskManager;
import org.tinygroup.sdpm.project.dao.pojo.ProjectStory;
import org.tinygroup.sdpm.project.service.inter.ProjectStoryService;
import org.tinygroup.tinysqldsl.Pager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shenly13343 on 2015-09-20.
 */
@Component
@Transactional
public class ProjectStoryServiceImpl implements ProjectStoryService {
    @Autowired
    private ProjectStoryManager projectStoryManager;
    @Autowired
    private StoryManager storyManager;
    @Autowired
    private TaskManager taskManager;
    @Autowired
    private ProjectManager projectManager;
    @Autowired
    private BuildManager buildManager;

    public int[] updateProjectStoryLink(List<ProjectStory> projectStoryList) {
        return projectStoryManager.updateLink(projectStoryList);
    }
    @Transactional(readOnly = true)
    public List<ProjectStory> findByProjectStory(ProjectStory projectStory) {
        return projectStoryManager.findList(projectStory);
    }

    public Integer batchDelStory(Integer projectId, Integer[] storyIds) {
        return projectStoryManager.batchDel(storyIds, projectId);
    }

    public int[] addProjectStoryLink(List<ProjectStory> projectStoryList) {
        return projectStoryManager.linkStory(projectStoryList);
    }
    @Transactional(readOnly = true)
    public List<ProductStory> findStoryByProject(Integer projectId) {
        if (getStoryIdInProject(projectId).length == 0) {
            return new ArrayList<ProductStory>();
        }
        List<ProductStory> list = storyManager.findList(false, getStoryIdInProject(projectId));
        return list;
    }


    @Override
    @Transactional(readOnly = true)
    public List<ProductStory> findStoryByProjectAndModule(Integer projectId, ProductStory story) {
        if (getStoryIdInProject(projectId).length == 0) {
            return new ArrayList<ProductStory>();
        }
        List<ProductStory> list = storyManager.findList(false, story, getStoryIdInProject(projectId));
        return list;
    }

    public Integer deleteProjectStory(Integer projectId, Integer storyId) {
        return projectStoryManager.deleteByProjectStory(projectId, storyId);
    }
    @Transactional(readOnly = true)
    public Pager<ProductStory> findStoryToLink(Integer projectId, Integer start, Integer limit, String order, String orderType, Boolean isNotInProjectStory) {
        return projectStoryManager.findStoryToLink(projectId, start, limit, order, orderType, isNotInProjectStory);
    }
    @Transactional(readOnly = true)
    public Pager<ProductStory> findStoryPager(int start, int limit, int id, SearchInfos conditions, String groupOperate) {
        return buildManager.findBuildStory(start, limit, id);
    }
    @Transactional(readOnly = true)
    public Pager<ProductStory> findStoryPagerByProject(Integer projectId, Integer start, Integer limit, String order, String orderType, String moduleId) {
        List<ProjectStory> storyList = projectStoryManager.findStoryList(projectId);
        String[] ids = new String[storyList.size()];
        for (int i = 0; i < storyList.size(); i++) {
            ids[i] = String.valueOf(storyList.get(i).getStoryId());
        }
        ConditionCarrier carrier = new ConditionCarrier();
        carrier.putIns("productStory.storyId", ids);
        if (!StringUtil.isBlank(moduleId)) {
            carrier.putModuleIn("productStory.moduleId", moduleId);
        }
        ProductStory story = new ProductStory();
        story.setDeleted(0);
        Pager<ProductStory> pager = storyManager.findStoryByCondition(start, limit, story, carrier, order, "asc".equals(orderType) ? true : false);
        for (ProductStory s : pager.getRecords()) {
            s.setTaskNumber(taskManager.getTaskSumByStory(s.getStoryId()));
        }
        return pager;
    }
    @Transactional(readOnly = true)
    private Integer[] getStoryIdInProject(Integer projectId) {
        List<ProjectStory> projectStoryList = projectStoryManager.findStoryList(projectId);
        if (projectStoryList == null || projectStoryList.isEmpty()) {
            return new Integer[0];
        }
        List<Integer> storyList = new ArrayList<Integer>();
        for (ProjectStory projectStory : projectStoryList) {
            storyList.add(projectStory.getStoryId());
        }
        Integer[] i = new Integer[storyList.size()];
        return storyList.toArray(i);
    }
}
