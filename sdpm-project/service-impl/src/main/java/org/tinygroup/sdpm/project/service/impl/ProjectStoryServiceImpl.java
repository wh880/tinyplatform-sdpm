package org.tinygroup.sdpm.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.common.util.ComplexSearch.SearchInfos;
import org.tinygroup.sdpm.product.biz.inter.StoryManager;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.project.biz.inter.ProjectManager;
import org.tinygroup.sdpm.project.biz.inter.ProjectStoryManager;
import org.tinygroup.sdpm.project.biz.inter.TaskManager;
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

    public List<Project> findProjectsByStory(Integer storyId) {
        ProjectStory projectStory = new ProjectStory();
        projectStory.setStoryId(storyId);
        List<ProjectStory> projectStoryList = projectStoryManager.findList(projectStory);
        List<Integer> ids = new ArrayList<Integer>();
        for (ProjectStory t : projectStoryList) {
            ids.add(t.getStoryId());
        }
        return projectManager.findList(ids);
    }

    public List<ProjectStory> findByProjectStory(ProjectStory projectStory) {
        return projectStoryManager.findList(projectStory);
    }

    public Integer batchtDel(String condition) {
        return projectStoryManager.batchtDel(condition);
    }

    public int[] addLink(List<ProjectStory> projectStoryList) {
        return projectStoryManager.linkStory(projectStoryList);
    }


    public List<ProductStory> findStoryByProject(Integer projectId) {
        List<ProjectStory> projectStoryList = projectStoryManager.findSrotys(projectId);
        List<Integer> storyList = new ArrayList<Integer>();
        if (!projectStoryList.isEmpty()) {
            for (ProjectStory projectStory : projectStoryList) {
                storyList.add(projectStory.getStoryId());
            }
            Integer[] i = new Integer[storyList.size()];
            List<ProductStory> list = storyManager.findList(storyList.toArray(i));
            return list;
        } else {
            return null;
        }


    }

    public Integer deleteProjectStory(Integer projectId, Integer storyId) {
        return projectStoryManager.deleteByProjectStory(projectId, storyId);
    }

    public Pager<ProductStory> findStoryToLink(Integer projectId, Integer start, Integer limit, String order, String ordertype) {
        return projectStoryManager.findStoryToLink(projectId, start, limit, order, ordertype);
    }

    public Pager<ProductStory> findStoryByProject(Integer projectId, Integer start, Integer limit, String order, String ordertype) {

        List<ProjectStory> storyList = projectStoryManager.findSrotys(projectId);
        String condition = "";
        if (storyList == null || storyList.isEmpty()) {
            condition = null;
        } else {
            condition = " story_id in (";
            String storys = "";
            for (ProjectStory story : storyList) {
                if (StringUtil.isBlank(storys)) {
                    storys = storys + story.getStoryId().toString();
                } else {
                    storys = storys + "," + story.getStoryId().toString();
                }
            }
            condition = condition + storys + ")";
        }

        boolean asc = "asc".equals(ordertype) ? true : false;
        Pager<ProductStory> pager = storyManager.findPager(start, limit, null, condition, null, null, order, asc);
        for (ProductStory s : pager.getRecords()) {
            s.setTaskNumber(taskManager.getTaskSumByStory(s.getStoryId()));
        }
        return pager;
    }

    public Pager<ProductStory> findStoryPager(int start, int limit, ProductStory story, String statusCondition, SearchInfos conditions, String groupOperate, String columnName, boolean asc) {

        return storyManager.findPager(start, limit, story, statusCondition, conditions, groupOperate, columnName, asc);
    }
}
