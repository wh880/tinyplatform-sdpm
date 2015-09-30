package org.tinygroup.sdpm.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.product.biz.inter.StoryManager;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.project.biz.inter.ProjectStoryManager;
import org.tinygroup.sdpm.project.biz.inter.TaskManager;
import org.tinygroup.sdpm.project.dao.pojo.ProjectStory;
import org.tinygroup.sdpm.project.service.inter.ProjectStoryService;
import org.tinygroup.tinysqldsl.Pager;

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

    public Pager<ProductStory> findStoryByProject(Integer projectId, Integer start, Integer limit, String order, String ordertype) {
        List<ProjectStory> storyList = projectStoryManager.findSrotys(projectId);
        String condition = " story_id in (";
        String storys = "";
        for (ProjectStory story : storyList) {
            if (StringUtil.isBlank(storys)) {
                storys = storys + story.getStoryId().toString();
            } else {
                storys = "," + story.getStoryId().toString();
            }
        }
        condition = condition + storys + ")";
        boolean asc = ordertype == "asc" ? true : false;
        Pager<ProductStory> pager = storyManager.findPager(start, limit, null, condition, null, null, order, asc);
        for (ProductStory s : pager.getRecords()) {
            s.setTaskNumber(taskManager.getTaskSumByStory(s.getStoryId()));
        }
        return pager;
    }
}
