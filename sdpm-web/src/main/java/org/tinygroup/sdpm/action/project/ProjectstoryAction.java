package org.tinygroup.sdpm.action.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.sdpm.common.util.CookieUtils;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.project.service.inter.ProjectService;
import org.tinygroup.sdpm.project.service.inter.ProjectStoryService;
import org.tinygroup.sdpm.project.service.inter.TaskService;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangying14938 on 2015-09-22.需求
 */
@Controller
@RequestMapping("/project/demand")
public class ProjectstoryAction extends BaseController {
    @Autowired
    private ProjectStoryService projectStoryService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ProjectService projectService;

    @RequestMapping("/find")
    public String find(Model model, HttpServletRequest request, Integer start, Integer limit, String order, String ordertype) {
        Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, "cookie_projectId"));
        Pager<ProductStory> story = projectStoryService.findStoryByProject(projectId, start, limit, order, ordertype);

        model.addAttribute("storys", story);
        //story.getTotalCount()
        return "project/demand/demandTableData.pagelet";

    }
//    @RequestMapping("/add")
//    public String form(Integer taskId, Model model) {
//        if (taskId != null) {
//            ProjectTask task = taskService.findTask(taskId);
//            model.addAttribute("task", task);
//            return "project/task/add.page";
//        }
//        return null;
//    }
//
//    @RequestMapping("/batchadd")
//    public String call(Integer taskId, Model model) {
//        if (taskId != null) {
//            ProjectTask task = taskService.findTask(taskId);
//            model.addAttribute("task", task);
//            return "project/task/batchAdd.page";
//        }
//        return null;
//    }

    @ResponseBody
    @RequestMapping("/delete")
    public Map<String, String> delete(Integer id, HttpServletRequest request) {
        Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, "cookie_projectId"));
        //根据id进行软删
        Map<String, String> map = new HashMap<String, String>();
        Integer result = projectStoryService.deleteProjectStory(projectId, id);
        if (result > 0) {
            map.put("status", "y");
            map.put("info", "删除成功");
        } else {
            map.put("status", "n");
            map.put("info", "删除失败");
        }
        return map;
    }

    @RequestMapping("/preLinkStory")
    public String preLinkStory() {

        return "project/demand/relateDemand.page";
    }

    @RequestMapping("/findStory")
    public String findStory(Model model, Integer start, Integer limit, String order, String ordertype, HttpServletRequest request) {
        Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, "cookie_projectId"));
        Pager<ProductStory> storyList = projectStoryService.findStoryToLink(projectId, start, limit, order, ordertype);
        model.addAttribute("storyList", storyList);
        return "project/demand/relateDemandTableData.pagelet";
    }

    @RequestMapping("/linkStory")
    public String linkStory(Integer[] storyIds, HttpServletRequest request) {
        return "project/demand/index.page";
    }
}
