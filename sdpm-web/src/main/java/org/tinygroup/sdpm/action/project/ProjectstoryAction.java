package org.tinygroup.sdpm.action.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.project.dao.pojo.ProjectStory;
import org.tinygroup.sdpm.project.service.inter.ProjectService;
import org.tinygroup.sdpm.project.service.inter.ProjectStoryService;
import org.tinygroup.sdpm.project.service.inter.TaskService;
import org.tinygroup.sdpm.util.CookieUtils;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangying14938 on 2015-09-22.需求
 */
@Controller
@RequestMapping("/a/project/demand")
public class ProjectstoryAction extends BaseController {
    @Autowired
    private ProjectStoryService projectStoryService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ProjectService projectService;

    @RequestMapping("/find")
    public String find(Model model, HttpServletRequest request, Integer start, Integer limit, String order, String ordertype) {
        Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, TaskAction.COOKIE_PROJECT_ID));
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
        Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, TaskAction.COOKIE_PROJECT_ID));
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
        Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, TaskAction.COOKIE_PROJECT_ID));
        Pager<ProductStory> storyList = projectStoryService.findStoryToLink(projectId, start, limit, order, ordertype);
        model.addAttribute("storyList", storyList);
        return "project/demand/relateDemandTableData.pagelet";
    }


    @ResponseBody
    @RequestMapping("/linkStory")
    public Map<String, String> linkStory(String ids, HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        String[] id = ids.split(",");
        List<ProjectStory> projectStoryList = new ArrayList<ProjectStory>();
        Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, TaskAction.COOKIE_PROJECT_ID));
        for (int i = 0; i < id.length; i++) {
            ProjectStory projectStory = new ProjectStory();
            projectStory.setProjectId(projectId);
            projectStory.setStoryId(Integer.parseInt(id[i]));
            projectStoryList.add(projectStory);
        }
        int[] res = projectStoryService.addLink(projectStoryList);
        if (res.length > 0) {
            map.put("statu", "y");
            map.put("info", "关联成功");
            map.put("url", "project/demand/index.page");
        } else {
            map.put("statu", "n");
            map.put("info", "关联失败");
        }
        return map;
    }

    @ResponseBody
    @RequestMapping("/batchDel")
    public Map<String, String> batchDel(String ids, HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        String[] id = ids.split(",");
        if (id.length > 0) {
            String condition = "";
            for (int i = 0; i < id.length; i++) {
                if (StringUtil.isBlank(condition)) {
                    condition = condition + "story_id in (" + id[i];
                } else {
                    condition = condition + "," + id[i];
                }
            }
            condition = condition + ")";


            Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, TaskAction.COOKIE_PROJECT_ID));
            condition = condition + " and ";
            condition = condition + "project_id=" + projectId;
            Integer res = projectStoryService.batchtDel(condition);
            if (res > 0) {
                map.put("status", "y");
                map.put("info", "删除成功");
            } else {
                map.put("status", "n");
                map.put("info", "删除失败");
            }
        } else {
            map.put("status", "n");
            map.put("info", "未选择");
        }

        return map;
    }


//    @RequestMapping("/search/{relate}")
//    public String storyListAction(@PathVariable(value="relate")String relate, Integer start, Integer limit, @RequestParam(required = false, defaultValue = "storyId") String order, @RequestParam(required = false, defaultValue = "asc")String ordertype, Model model, HttpServletRequest request){
//        Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, TaskAction.COOKIE_PROJECT_ID));
//        Pager<ProductStory> story = projectStoryService.findStoryByProject(projectId, start, limit, order, ordertype);
//        model.addAttribute("story", story);
//
//        if("reRelateStory".equals(relate)){
//            return "/project/task/relation-release/product-al-req.pagelet";
//        }else if ("noRelateStory".equals(relate)) {
//            return "/project/task/relation-release/product-al-no-req-data.pagelet";
//        }else if ("reRelateStoryRelease".equals(relate)) {
//            return "/project/task/relation-release/product-al-req-data.pagelet";
//        }else if ("noRelateStoryRelease".equals(relate)) {
//            return "/project/task/relation-release/product-al-no-req-data.pagelet";
//        }
//
//        return "";
//    }
}
