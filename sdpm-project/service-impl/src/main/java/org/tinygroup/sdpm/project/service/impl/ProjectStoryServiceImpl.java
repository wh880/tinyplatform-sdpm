package org.tinygroup.sdpm.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.common.condition.ConditionCarrier;
import org.tinygroup.sdpm.common.condition.ConditionUtils;
import org.tinygroup.sdpm.common.util.ComplexSearch.SearchInfos;
import org.tinygroup.sdpm.product.biz.inter.StoryManager;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.project.biz.inter.BuildManager;
import org.tinygroup.sdpm.project.biz.inter.ProjectManager;
import org.tinygroup.sdpm.project.biz.inter.ProjectStoryManager;
import org.tinygroup.sdpm.project.biz.inter.TaskManager;
import org.tinygroup.sdpm.project.dao.ProjectBuildDao;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.dao.pojo.ProjectStory;
import org.tinygroup.sdpm.project.service.inter.ProjectStoryService;
import org.tinygroup.tinysqldsl.Pager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shenly13343 on 2015-09-20.
 */
@Component
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
    private ProjectBuildDao projectBuildDao;
    @Autowired
    private BuildManager buildManager;

    public int[] updateLink(List<ProjectStory> projectStoryList) {
        return projectStoryManager.updaeLink(projectStoryList);
    }

    public List<Project> findProjectsByStory(Integer storyId) {
        if (storyId == null) {
            return null;
        }
        ProjectStory projectStory = new ProjectStory();
        projectStory.setStoryId(storyId);
        List<ProjectStory> projectStoryList = projectStoryManager.findList(projectStory);
        if (projectStoryList.isEmpty() || projectStoryList == null) {
            return null;
        }
        List<Integer> ids = new ArrayList<Integer>();
        for (ProjectStory t : projectStoryList) {
            ids.add(t.getStoryId());
        }
        return projectManager.findListByIds(ids);
    }

    public List<ProjectStory> findByProjectStory(ProjectStory projectStory) {
        return projectStoryManager.findList(projectStory);
    }

    public Integer batchDel(Integer projectId, Integer[] storyIds) {
        return projectStoryManager.batchDel(storyIds, projectId);
    }

    public int[] addLink(List<ProjectStory> projectStoryList) {
        return projectStoryManager.linkStory(projectStoryList);
    }

    public List<ProductStory> findStoryByProject(Integer projectId) {
        List<ProjectStory> projectStoryList = projectStoryManager.findSrotys(projectId);
        if (projectStoryList == null || projectStoryList.isEmpty()) {
            return new ArrayList<ProductStory>();
        }
        List<Integer> storyList = new ArrayList<Integer>();
        for (ProjectStory projectStory : projectStoryList) {
            storyList.add(projectStory.getStoryId());
        }
        Integer[] i = new Integer[storyList.size()];
        List<ProductStory> list = storyManager.findList(storyList.toArray(i));
        return list;
    }

    public Integer deleteProjectStory(Integer projectId, Integer storyId) {
        return projectStoryManager.deleteByProjectStory(projectId, storyId);
    }

    public Pager<ProductStory> findStoryToLink(Integer projectId, Integer start, Integer limit, String order, String ordertype) {
        return projectStoryManager.findStoryToLink(projectId, start, limit, order, ordertype);
    }

    public Pager<ProductStory> findStoryPager(int start, int limit, int id, SearchInfos conditions, String groupOperate) {
        return buildManager.findBuildStory(start, limit, id);
    }

    public Pager<ProductStory> findNoStoryPager(int start, int limit, int id, String condition, SearchInfos conditions, String groupOperate) {
        return buildManager.findnoBuildStory(start, limit, condition, id);
    }

    public Pager<ProductStory> findStoryByProject(Integer projectId, Integer start, Integer limit, String order, String ordertype, String moduleId) {
        boolean asc = "asc".equals(ordertype) ? true : false;
        List<ProjectStory> storyList = projectStoryManager.findSrotys(projectId);
        String[] ids = new String[storyList.size()];
        for(int i=0;i<storyList.size();i++){
            ids[i] = String.valueOf(storyList.get(i).getStoryId());
        }
        ConditionCarrier carrier;
        if(!StringUtil.isBlank(moduleId)){
            carrier = ConditionUtils.mergeCarrier("productStory.moduleId",moduleId,"productStory.storyId",ids);
        }else{
            carrier = ConditionUtils.mergeCarrier("productStory.storyId",ids);
        }
        ProductStory story = new ProductStory();
        story.setDeleted(0);
        Pager<ProductStory> pager = storyManager.findStoryByCondition(start,limit,story,carrier,order,"asc".equals(ordertype)?true:false);
        for (ProductStory s : pager.getRecords()) {
            s.setTaskNumber(taskManager.getTaskSumByStory(s.getStoryId()));
        }
        return pager;
    }
}
