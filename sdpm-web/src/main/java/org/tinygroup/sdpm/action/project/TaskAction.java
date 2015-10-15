package org.tinygroup.sdpm.action.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.action.project.util.TaskStatusUtil;
import org.tinygroup.sdpm.action.system.ProfileUtil;
import org.tinygroup.sdpm.common.util.CookieUtils;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.product.service.StoryService;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTask;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTeam;
import org.tinygroup.sdpm.project.service.inter.ProjectService;
import org.tinygroup.sdpm.project.service.inter.ProjectStoryService;
import org.tinygroup.sdpm.project.service.inter.TaskService;
import org.tinygroup.sdpm.project.service.inter.TeamService;
import org.tinygroup.sdpm.system.dao.pojo.SystemAction;
import org.tinygroup.sdpm.system.dao.pojo.SystemModule;
import org.tinygroup.sdpm.system.service.inter.ModuleService;
import org.tinygroup.sdpm.util.ModuleUtil;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by wangying14938 on 2015-09-22.任务
 */
@Controller
@RequestMapping("/a/project/task")
public class TaskAction extends BaseController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private ProjectStoryService storyService;
    @Autowired
    private StoryService productStoryService;


    @RequestMapping("index")
    public String index(@CookieValue(required = false) Integer cookie_projectId, HttpServletResponse response, HttpServletRequest request, Model model, String moduleId) {
        List<Project> list = projectService.findList();
        Project selProject = new Project();
        if (list == null || list.isEmpty()) {
            return "redirect:" + adminPath + "/project/add";
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
        if (moduleId != null) {
            model.addAttribute("moduleId", moduleId);
            return "project/task/index.page";
        } else {
            return "project/task/index.page";
        }

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
    public String call(Integer taskId, Model model, HttpServletRequest request) {
        if (taskId != null) {
            Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, "cookie_projectId"));
            ProjectTask task = taskService.findTask(taskId);
            List<ProjectTeam> team = teamService.findTeamByProjectId(projectId);

            model.addAttribute("teamList", team);
            model.addAttribute("task", task);
            //还需要查询其他相关任务剩余时间的信息
            return "project/task/call.page";
        }
        return "error";
    }

    @RequestMapping("/finish")
    public String finish(Integer taskId, Model model, HttpServletRequest request) {
        if (taskId != null) {
            Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, "cookie_projectId"));
            List<ProjectTeam> teamList = teamService.findTeamByProjectId(projectId);
            ProjectTask task = taskService.findTask(taskId);
            model.addAttribute("task", task);
            model.addAttribute("teamList", teamList);
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
    public String findPager(Integer start, Integer limit, String order, String ordertype, String statu, String choose, String group, Integer projectId, Model model, HttpServletRequest request, String moduleId) {
        boolean asc = true;
        if ("desc".equals(ordertype)) {
            asc = false;
        }
        ProjectTask task = new ProjectTask();
        task.setTaskProject(projectId);
        String moduleIds = "";
        if (!StringUtil.isBlank(moduleId)) {
            if (moduleId.contains("p")) {
                moduleIds = ModuleUtil.getConditionByRoot(Integer.parseInt(moduleId.substring(1)), moduleService);
            } else {
                moduleIds = ModuleUtil.getCondition(Integer.parseInt(moduleId), moduleService);
            }
        }


        String condition = TaskStatusUtil.getCondition(statu, choose, request, moduleIds);
        Pager<ProjectTask> taskPager = taskService.findPagerTask(start, limit, task, order, asc, condition, group);
        model.addAttribute("taskPager", taskPager);
        model.addAttribute("statu", statu);
        model.addAttribute("choose", choose);
        model.addAttribute("group", group);
        return "project/task/datalist.pagelet";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(ProjectTask task,  @RequestParam(value="file",required=false)MultipartFile file,Model model) {
        if (task.getTaskId() == null) {
          ProjectTask newtask=  taskService.addTask(task);
          ProfileUtil profileUtil = new ProfileUtil();
          try {
			profileUtil.uploadNoTitle(file, newtask.getTaskId(), "task");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        } else {
            taskService.updateTask(task);
        }
        model.addAttribute("task", task);
        return "project/task/index.page";
    }

    @RequestMapping(value = "/closesave", method = RequestMethod.POST)
    public String closesave(ProjectTask task,Model model, SystemAction systemAction) {
        taskService.updateCloseTask(task);

        systemAction.setActionObjectId(task.getTaskId());
        systemAction.setActionProject(task.getTaskProject());
        systemAction.setActionObjectType("task");
        systemAction.setActionActor("close");
        logService.log(systemAction);
        return "project/task/index.page";
    }

    @RequestMapping(value = "/editsave", method = RequestMethod.POST)
    public String editSave(ProjectTask task, Model model, SystemAction systemAction) {
        taskService.updateEditTask(task);

        systemAction.setActionObjectId(task.getTaskId());
        systemAction.setActionProject(task.getTaskProject());
        systemAction.setActionObjectType("task");
        systemAction.setActionActor("close");
        logService.log(systemAction);
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
    public String preadd(HttpServletRequest request, Model model, Integer storyId) {
        Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, "cookie_projectId"));

        model.addAttribute("team", teamService.findTeamByProjectId(projectId));
        SystemModule module = new SystemModule();
        module.setModuleType("project");
        module.setModuleRoot(projectId);
        List<SystemModule> moduleList = moduleService.findModuleList(module);
        List<ProductStory> storyList = storyService.findStoryByProject(projectId);
        ProductStory story = new ProductStory();
        if (storyId != null) {
            story = productStoryService.findStory(storyId);

        }
        model.addAttribute("story", story);

        model.addAttribute("moduleList", moduleList);
        model.addAttribute("storyList", storyList);

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

    @RequestMapping("/basicInfoEdit")
    public String basicInfoEdit(Integer taskId, Model model) {
        ProjectTask task = taskService.findTask(taskId);
        String projectName = projectService.findById(task.getTaskProject()).getProjectName();
        List<ProjectTeam> teamList = teamService.findTeamByProjectId(task.getTaskProject());
        SystemModule systemModule = new SystemModule();
        systemModule.setModuleRoot(task.getTaskProject());
        systemModule.setModuleType("project");
        List<SystemModule> modulesList = moduleService.findModuleList(systemModule);

        List<ProductStory> storyList = storyService.findStoryByProject(task.getTaskProject());

        model.addAttribute("task", task);
        model.addAttribute("projectName", projectName);
        model.addAttribute("teamList", teamList);
        model.addAttribute("modulesList", modulesList);
        model.addAttribute("storyList", storyList);
        return "project/task/basicInfoEdit.pagelet";
    }

    @RequestMapping("/basicInformation")
    public String basicInformation(Integer taskId, Model model) {
        ProjectTask task = taskService.findTask(taskId);
        Project project = projectService.findById(task.getTaskProject());
        model.addAttribute("task", task);
        model.addAttribute("project", project);

        //查询所属模块string
        //moduleService.findById(task.getTaskModule());
        //查询相关需求名字
        String storyTitle = productStoryService.findStory(task.getTaskStory()).getStoryTitle();
        model.addAttribute("storyTitle", storyTitle);
        return "project/task/basicInformation.pagelet";
    }

    @RequestMapping("/preBatchAdd")
    public String preBatchAdd(Model model, HttpServletRequest request) {
        Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, "cookie_projectId"));
        model.addAttribute("teamList", teamService.findTeamByProjectId(projectId));

        List<ProductStory> storyList = storyService.findStoryByProject(projectId);
        model.addAttribute("storyList", storyList);

        SystemModule systemModule = new SystemModule();
        systemModule.setModuleRoot(projectId);
        systemModule.setModuleType("project");
        List<SystemModule> modulesList = moduleService.findModuleList(systemModule);
        model.addAttribute("modulesList", modulesList);

        return "project/task/batchAdd.page";
    }

    @RequestMapping("/batchAdd")
    public String batchAdd(Tasks tasks, HttpServletRequest request) {
        List<ProjectTask> taskList = tasks.getTaskList();
        for (int i = 0; i < taskList.size(); i++) {
            if (StringUtil.isBlank(taskList.get(i).getTaskName())) {
                taskList.remove(i);
                i--;
            }
        }
        if (taskList.isEmpty()) {
            return "project/task/index.page";
        } else {
            Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, "cookie_projectId"));
            taskService.batchAdd(taskList, projectId);
            return "project/task/index.page";
        }
    }


}
