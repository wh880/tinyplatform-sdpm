package org.tinygroup.sdpm.action.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tinygroup.sdpm.action.project.util.TaskStatusUtil;
import org.tinygroup.sdpm.common.util.CookieUtils;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTask;
import org.tinygroup.sdpm.project.service.inter.ProjectService;
import org.tinygroup.sdpm.project.service.inter.TaskService;
import org.tinygroup.sdpm.project.service.inter.TeamService;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by wangying14938 on 2015-09-22.任务
 */
@Controller
@RequestMapping("/project/task")
public class TaskAction extends BaseController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private TeamService teamService;

    @RequestMapping("index")
    public String index(@CookieValue(required = false) Integer cookie_projectId, HttpServletResponse response, HttpServletRequest request, Model model) {
        List<Project> list = projectService.findList();
        Project selProject = new Project();
        if (list == null || list.isEmpty()) {
            return "redirect:/project/add";
        } else {
            if (cookie_projectId == null) {
                selProject = list.get(0);
                //maxAge=-1意为永久
                CookieUtils.setCookie(response, "cookie_projectId", selProject.getProjectId().toString(), -1);
            } else {
                boolean flag = false;
                for (Project p : list) {
                    if (p.getProjectId() == cookie_projectId) {
                        selProject = p;
                        CookieUtils.setCookie(response, "cookie_projectId", cookie_projectId.toString(), -1);
                        flag = true;
                        break;
                    }
                }
                //若数据库中无cookies中projectId对应的项目，则返回选中第一条
                if (!flag) {
                    selProject = list.get(0);
                    CookieUtils.setCookie(response, "cookie_projectId", selProject.getProjectId().toString(), -1);
                }
            }
        }
        //model.addAttribute("selProject", selProject);
        //model.addAttribute("projectList", list);
        request.getSession().setAttribute("selProject", selProject);
        request.getSession().setAttribute("projectList", list);
        return "project/task/index.page";
    }

    @RequestMapping("/edit")
    public String form(Integer taskId, Model model) {
        if (taskId != null) {
            ProjectTask task = taskService.findTask(taskId);
            model.addAttribute("task", task);
            //还需要查询其他相关任务剩余时间的信息
            return "project/task/edit.page";
        }
        return "error";
    }

    @RequestMapping("/call")
    public String call(Integer taskId, Model model) {
        if (taskId != null) {
            ProjectTask task = taskService.findTask(taskId);
            model.addAttribute("task", task);
            //还需要查询其他相关任务剩余时间的信息
            return "project/task/call.page";
        }
        return "error";
    }

    @RequestMapping("/finish")
    public String finish(Integer taskId, Model model) {
        if (taskId != null) {
            ProjectTask task = taskService.findTask(taskId);
            model.addAttribute("task", task);
            //还需要查询其他相关任务剩余时间的信息
            return "project/task/finish.page";
        }
        return "error";
    }

    @RequestMapping("/note")
    public String note(Integer taskId, Model model) {
        if (taskId != null) {
            ProjectTask task = taskService.findTask(taskId);
            model.addAttribute("task", task);
            //还需要查询其他相关任务剩余时间的信息
            return "project/task/note.page";
        }
        return "error";
    }

    @RequestMapping("/close")
    public String close(Integer taskId, Model model) {
        ProjectTask task = taskService.findTask(taskId);
        model.addAttribute("task", task);
        return "project/task/close.page";
    }

    @RequestMapping("/start")
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
    public String findPager(Integer start, Integer limit, String order, String ordertype, String statu, String choose, String group, Integer projectId, Model model, HttpServletRequest request) {
        boolean asc = true;
        if ("desc".equals(ordertype)) {
            asc = false;
        }
        ProjectTask task = new ProjectTask();
        task.setTaskProject(projectId);
        Pager<ProjectTask> taskPager = taskService.findPagerTask(start, limit, task, order, asc, TaskStatusUtil.getCondition(statu, choose, request), group);
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

    @RequestMapping(value = "/closesave", method = RequestMethod.POST)
    public String closesave(ProjectTask task, Model model) {
        if (task.getTaskId() == null) {
            taskService.addTask(task);
        } else {
            taskService.updateCloseTask(task);
        }
        model.addAttribute("task", task);
        return "project/task/index.page";
    }

    @RequestMapping(value = "/editsave", method = RequestMethod.POST)
    public String editSave(ProjectTask task, Model model) {
        if (task.getTaskId() == null) {
            taskService.addTask(task);
        } else {
            taskService.updateEditTask(task);
        }
        model.addAttribute("task", task);
        return "project/task/index.page";
    }

    @RequestMapping(value = "/callsave", method = RequestMethod.POST)
    public String callSave(ProjectTask task, Model model) {
        if (task.getTaskId() == null) {
            taskService.addTask(task);
        } else {
            taskService.updateCallTask(task);
        }
        model.addAttribute("task", task);
        return "project/task/index.page";
    }

    @RequestMapping(value = "/finishsave", method = RequestMethod.POST)
    public String finishsave(ProjectTask task, Model model) {
        if (task.getTaskId() == null) {
            taskService.addTask(task);
        } else {
            taskService.updateFinishTask(task);
        }
        model.addAttribute("task", task);
        return "project/task/index.page";
    }

    @RequestMapping(value = "/startsave", method = RequestMethod.POST)
    public String startsave(ProjectTask task, Model model) {
        if (task.getTaskId() == null) {
            taskService.addTask(task);
        } else {
            taskService.updateStartTask(task);
        }
        model.addAttribute("task", task);
        return "project/task/index.page";
    }

    @RequestMapping("/preadd")
    public String preadd(HttpServletRequest request, Model model) {
        Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, "cookie_projectId"));

        model.addAttribute("team", teamService.findTeamByProjectId(projectId));
        return "project/task/add.page";
    }

    @RequestMapping("/batchadd")
    public String batchadd(Integer taskId, Model model) {
        if (taskId != null) {
            ProjectTask task = taskService.findTask(taskId);
            model.addAttribute("task", task);
            //还需要查询其他相关任务剩余时间的信息
            return "project/task/batchAdd.page";
        }
        return "project/task/batchAdd.page";
    }

    @RequestMapping("/findTask")
    public String findTask(Model model, Integer taskId) {
        ProjectTask task = taskService.findTask(taskId);
        model.addAttribute("task", task);

        return "project/task/IDLink.page";
    }

}
