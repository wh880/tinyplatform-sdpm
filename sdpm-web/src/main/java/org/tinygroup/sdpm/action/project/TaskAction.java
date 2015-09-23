package org.tinygroup.sdpm.action.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTask;
import org.tinygroup.sdpm.project.service.inter.TaskService;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

/**
 * Created by wangying14938 on 2015-09-22.任务
 */
@Controller
@RequestMapping("/project/task")
public class TaskAction extends BaseController {
    @Autowired
    private TaskService taskService;

    @RequestMapping("form")
    public String form(Integer taskId) {
        if (taskId != null) {
            //ProjectTask task = taskService.
            return null;
        }
        return null;
    }

    @RequestMapping("/findList")
    public String findList(Integer projectId, Model model) {
        if (projectId != null) {
            ProjectTask task = new ProjectTask();
            task.setTaskProject(projectId);
            List<ProjectTask> list = taskService.findListTask(task);
            model.addAttribute("tasksList", list);
        }
        return "project/task/datalist.pagelet";
    }

    @RequestMapping("/findPager")
    public String findPager(Integer start, Integer limit, String order, String ordertype, Integer projectId, Model model) {
        boolean asc = true;
        if ("desc".equals(ordertype)) {
            asc = false;
        }
        ProjectTask task = new ProjectTask();
        task.setTaskProject(projectId);
        Pager<ProjectTask> taskPager = taskService.findPagerTask(start, limit, task, order, asc);
        model.addAttribute("taskPager", taskPager);
        return "project/task/datalist.pagelet";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(ProjectTask task, Model model) {
        if (task.getTaskId() == null) {
            taskService.addTask(task);
        } else {
            taskService.updateTask(task);
        }
        model.addAttribute("task", task);
        return "project/task/index.page";
    }
}
