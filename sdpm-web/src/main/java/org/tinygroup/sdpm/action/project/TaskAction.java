package org.tinygroup.sdpm.action.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tinygroup.sdpm.action.project.util.TaskStatusUtil;
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

    @RequestMapping("edit")
    public String form(Integer taskId, Model model) {
        if (taskId != null) {
            ProjectTask task = taskService.findTask(taskId);
            model.addAttribute("task", task);
            //还需要查询其他相关任务剩余时间的信息
            return "project/task/edit.page";
        }
        return "error";
    }
    @RequestMapping("call")
    public String call(Integer taskId, Model model) {
        if (taskId != null) {
            ProjectTask task = taskService.findTask(taskId);
            model.addAttribute("task", task);
            //还需要查询其他相关任务剩余时间的信息
            return "project/task/call.page";
        }
        return "error";
    }
    @RequestMapping("finish")
    public String finish(Integer taskId, Model model) {
        if (taskId != null) {
            ProjectTask task = taskService.findTask(taskId);
            model.addAttribute("task", task);
            //还需要查询其他相关任务剩余时间的信息
            return "project/task/finish.page";
        }
        return "error";
    }
    @RequestMapping("note")
    public String note(Integer taskId, Model model) {
        if (taskId != null) {
            ProjectTask task = taskService.findTask(taskId);
            model.addAttribute("task", task);
            //还需要查询其他相关任务剩余时间的信息
            return "project/task/note.page";
        }
        return "error";
    }
    @RequestMapping("close")
    public String close(Integer taskId, Model model) {
        if (taskId != null) {
            ProjectTask task = taskService.findTask(taskId);
            model.addAttribute("task", task);
            //还需要查询其他相关任务剩余时间的信息
            return "project/task/close.page";
        }
        return "error";
    }
    @RequestMapping("start")
    public String start(Integer taskId, Model model) {
        if (taskId != null) {
            ProjectTask task = taskService.findTask(taskId);
            model.addAttribute("task", task);
            //还需要查询其他相关任务剩余时间的信息
            return "project/task/start.page";
        }
        return "error";
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
    public String findPager(Integer start, Integer limit, String order, String ordertype, String statu, String choose, String group, Integer projectId, Model model) {
        boolean asc = true;
        if ("desc".equals(ordertype)) {
            asc = false;
        }
        ProjectTask task = new ProjectTask();
        task.setTaskProject(projectId);
        Pager<ProjectTask> taskPager = taskService.findPagerTask(start, limit, task, order, asc, TaskStatusUtil.getCondition(statu, choose), group);
        model.addAttribute("taskPager", taskPager);
        model.addAttribute("statu", statu);
        model.addAttribute("choose", choose);
        model.addAttribute("group", group);
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
